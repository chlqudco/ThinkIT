package com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.chlqudco.develop.thinkit.databinding.ActivitySubjectiveQuizBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.quiz.subjective.result.SubjectiveResultActivity
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.QUIZ_SUBJECT_LIST
import com.chlqudco.develop.thinkit.utility.AppKey.SUBJECTIVE_QUIZ_TIME
import org.koin.android.ext.android.inject

internal class SubjectiveQuizActivity : BaseActivity<SubjectiveQuizViewModel, ActivitySubjectiveQuizBinding>() {

    private var startTime: Long = 0L
    private var quizCount : Int = 1

    override val viewModel: SubjectiveQuizViewModel by inject()

    //녹음과 재생을 위한 변수들
    private var recorder : MediaRecorder? = null
    private var player: MediaPlayer? = null

    //녹음 외부저장소 사용을 위한 저장소경로 저장
    private val recordingFilePath: String by lazy { "${externalCacheDir?.absolutePath}/recording.3gp" }

    //녹음 상태값 관리하기 위한 변수
    private var state = RecordState.BEFORE_RECORDING
        set(value) {
            field = value
            //언제 누르지 못하게 할건지
            binding.ActivitySubjectQuizResetRecordButton.isVisible = (value == RecordState.AFTER_RECORDING) || (value == RecordState.ON_PLAYING)
            //상태에 따라 아이콘 업데이트
            binding.ActivitySubjectiveQuizRecordButton.updateIconWithState(value)
        }

    override fun getViewBinding() = ActivitySubjectiveQuizBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.subjectiveQuizLiveData.observe(this){
            when(it){
                is SubjectiveQuizState.UnInitialized -> {
                    initViews()
                }
                is SubjectiveQuizState.Loading -> {
                    requestQuizList()
                }
                is SubjectiveQuizState.Success -> {
                    handleSuccessState(it)
                }
                is SubjectiveQuizState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleErrorState() {
        showToastMessage("오류가 발생했습니다")
        finish()
    }

    private fun handleSuccessState(state: SubjectiveQuizState.Success) {
        //그만 돌아
        binding.ActivitySubjectiveQuizProgressBar.isVisible = false
        binding.ActivitySubjectiveQuizGroup.isVisible = true

        binding.ActivitySubjectiveQuizBestAnswerTextView.isVisible = false

        //시작 시간 측정
        startTime = System.currentTimeMillis()

        //첫번째 문제 세팅하기
        setQuizByIndex(0)
    }

    private fun setQuizByIndex(index: Int) {
        try{
            val quiz = viewModel.getQuizText(index).split("@")[0]
            val answer1 = viewModel.getQuizText(index).split("@")[1]
            val answer2 = answer1.split(".")

            var answer: String = ""
            for (item in answer2){
                answer += (" $item.\n\n")
            }

            //뷰모델에 저장
            viewModel.addQuiz(quiz, answer.dropLast(3))

            binding.ActivitySubjectiveQuizQuizTextView.text = quiz
            binding.ActivitySubjectiveQuizBestAnswerTextView.text = answer.dropLast(3)
        } catch (e: Exception){
            showToastMessage("오류가 발생했습니다. 다시 시도해주세요.")
            finish()
        }
    }

    private fun requestQuizList() {
        //문제 받아오기
        val subjects = intent.getStringArrayListExtra(QUIZ_SUBJECT_LIST) ?: listOf<String>()
        viewModel.getSubjectiveQuizList(subjects.toList())
    }

    private fun initViews() {
        // 녹음 관련 작업
        binding.ActivitySubjectiveQuizRecordButton.updateIconWithState(RecordState.BEFORE_RECORDING)

        // 다음 문제 버튼
        binding.ActivitySubjectiveQuizNextButton.setOnClickListener {
            // 마지막 문제인 경우
            if (quizCount == 10){
                goResultActivity()
            } else{
                showNextQuiz()
            }

            //답 보는거 초기화
            binding.ActivitySubjectiveQuizShowBestAnswerButton.isVisible = true
            binding.ActivitySubjectiveQuizBestAnswerTextView.isVisible = false

            // 녹음 초기화
            initRecord()
        }

        // 정답 보기 버튼
        binding.ActivitySubjectiveQuizShowBestAnswerButton.setOnClickListener {
            binding.ActivitySubjectiveQuizShowBestAnswerButton.isVisible = false
            binding.ActivitySubjectiveQuizBestAnswerTextView.isVisible = true
        }

        // 녹음 버튼
        binding.ActivitySubjectiveQuizRecordButton.setOnClickListener {

            //녹음 버튼 상태 확인
            when(state){
                RecordState.BEFORE_RECORDING -> {
                    // 녹음 전인 경우 권한 부여 확인
                    if (checkSelfPermission(android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
                        //권한 부여 되어 있으면 녹음 실행
                        startRecording()
                    } else {
                        // 권한 없으면 권한 받으러 가기
                        requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO_PERMISSION)
                    }
                }
                RecordState.ON_RECORDING -> {
                    // 녹음 중인 경우 녹음을 멈춰야 함
                    stopRecording()
                }
                RecordState.AFTER_RECORDING -> {
                    // 녹음 다 했으면 재생 해야 함
                    startPlaying()
                }
                RecordState.ON_PLAYING -> {
                    // 녹음 중인 경우 멈춰야 함
                    stopPlaying()
                }
            }

        }

        // 초기화 버튼
        binding.ActivitySubjectQuizResetRecordButton.setOnClickListener {
            initRecord()
        }

    }

    private fun initRecord() {
        // 재생 멈춰
        stopPlaying()
        stopRecording()
        state = RecordState.BEFORE_RECORDING
    }

    private fun startRecording() {
        //상태 변경
        state = RecordState.ON_RECORDING

        //녹음기 초기설정 해주고
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(recordingFilePath)
            prepare()
        }
        //녹음 시작!
        recorder?.start()
    }

    private fun stopRecording() {
        //상태 변경
        state = RecordState.AFTER_RECORDING

        //녹음 중이던거 끄고 버려
        recorder?.run {
            stop()
            release()
        }
        recorder = null

    }
    private fun startPlaying() {
        //상태 변경
        state = RecordState.ON_PLAYING

        //녹음한거 들기 위한 초기설정
        player = MediaPlayer().apply {
            setDataSource(recordingFilePath)
            prepare()
        }
        //재생 다 끝나면 상태? 돌리기
        player?.setOnCompletionListener {
            stopPlaying()
        }
        //재생 시작!
        player?.start()
    }

    private fun stopPlaying() {
        //상태 변경
        state = RecordState.AFTER_RECORDING

        //재생 멈추려면 다버려
        player?.release()
        player = null

    }


    @SuppressLint("SetTextI18n")
    private fun showNextQuiz() {
        //퀴즈 갱신
        setQuizByIndex(quizCount)

        quizCount++
        binding.ActivitySubjectiveQuizTopTextView.text = "${quizCount}번째"
        if(quizCount==10){
            binding.ActivitySubjectiveQuizNextButton.text = "퀴즈 종료"
        }
    }

    private fun goResultActivity() {
        val intent = Intent(this, SubjectiveResultActivity::class.java)
        //걸린 시간
        intent.putExtra(SUBJECTIVE_QUIZ_TIME, System.currentTimeMillis() - startTime)
        //문제들
        intent.putStringArrayListExtra(AppKey.MULTIPLE_INCORRECT_QUIZ, viewModel.inCorrectQuizList)
        //정답들
        intent.putStringArrayListExtra(AppKey.MULTIPLE_INCORRECT_BOGI, viewModel.inCorrectBogiList)

        startActivity(intent)
        finish()
    }

    //권한 잘 받았냐
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //잘 받아왔냐?
        val audioRecordPermissionGranted = requestCode == REQUEST_RECORD_AUDIO_PERMISSION && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        //만약 잘 못받아 왔으면
        if (!audioRecordPermissionGranted) {
            //교육용 함수 안보여 줬으면
            if (!shouldShowRequestPermissionRationale(permissions.first())) {
                //보여 드려
                showPermissionExplanationDialog()
            }
            //그것도 아니면 토스트 메세지
            else {
                showPermissionExplanationDialog()
            }
        }
    }

    private fun showPermissionExplanationDialog() {
        //교육용 다이얼로그 팟!
        AlertDialog.Builder(this)
            .setMessage("녹음 권한이 반드시 필요합니다")
            .setPositiveButton("권한 변경하러 가기") { _, _ -> navigateToAppSetting() }
            .setNegativeButton("녹음 안해") { _, _ -> }
            .show()
    }

    //앱 환경설정으로 고!
    private fun navigateToAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    //상수 관리
    companion object{
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 201
    }
}