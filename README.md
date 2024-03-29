- 버전 : Android Studio Dolphin | 2021.3.1  ->  Android Studio Electric Eel | 2022.1.1 Patch 2

- 기술 스택

1. 클린 아키텍처
2. MVVM + Live Data
3. Koin (의존성 주입)
4. Jetpack Navigation (화면 이동)
5. Coroutine (비동기 처리)
6. Retrofit2
7. Room DB
8. ViewBinding
9. DataStore

---

- 기능 설명

- 키워드 불러오기 기능
  - 선택한 과목을 Field로 서버에 GET 요청을 보낸다.
  - 서버는 키워드 목록을 List<String> 형태로 반환한다.

- 키워드 검색 기능
  - 서버에서 받아온 키워드 목록을 Room DB를 이용해 저장해놓는다.
  - 그 뒤 검색어가 바뀔 때마다 Room DB에서 검색어에 맞는 키워드들을 불러온다.

- 객관식 퀴즈 기능
  - 서버에서 객관식 퀴즈 10개를 받아온뒤 적절히 랜덤하여 보여준다.
  - 틀렸던 문제와 정답을 결과 창에서 조회할 수 있다.

- 주관식 퀴즈 기능
  - 서버에서 주관식 퀴즈 10개를 받아온뒤 적절히 랜덤하여 보여준다.
  

---

개발 순서
- 2022-10-03 : SetUp 및 스플래시 화면 구현
---

- 2022-10-04 : 메인 화면 제작
---

- 2022-10-05 : 메인화면 카드뷰로 변경 후 리소스값 추출
- 2022-10-05 : 키워드 화면 및 어댑터 제작
---

- 2022-10-10 : 개념 설명 화면 UI 제작 완료
---

- 2022-10-11 : 퀴즈 화면들 UI 제작 완료
- 2022-10-11 : UI 수정 및 ID 통일화
- 2022-10-11 : 키워드 통신 SetUp
- 2022-10-11 : 개념 받아오기 작업 완료
- 2022-10-11 : 프래그먼트 뒤로가기 구현
---

- 2022-10-12 : 객관식 통신 완료
---

- 2022-10-17 : 틀린문제 보기 기능 구현 완료
- 2022-10-17 : 면접 대비 구현 완료
- 2022-10-17 : 퀴즈 진입 창에 프로그래스바 만듬
---

- 2022-10-18 : 오류 제보 완료 및 Toast 수정
---

- 2022-10-29 : 출시 전 완성
---


- 2022-10-31 : 객관식 퀴즈에 스크롤뷰 적용
---

- 2022-11-01 : secrets-gradle-plugin 으로 URL 감추기
---

- 2022-11-04 : Room DB를 이용한 키워드 검색 기능 구현 완료
---

- 2022-11-05 : Jetpack Navi 재 적용 완료.
- 2022-11-05 : 개념 설명 화면 WebView로 전환
- 2022-11-05 : Jetpack Navi & Bottom Navi 화면전환 버그 수정
---

- 2022-11-08 : Framework 개념 화면 추가 및 퀴즈 목록에도 추가 완료
---

- 2022-11-15 : 면접대비 정답이 제대로 출력이 안되는 현상 해결
---

- 2022-11-17 : 면접대비 화면 따로 추출 후 인성면접 및 CS면접 기능 추가
---

- 2022-11-22 : compileSdk를 33으로 업그레이드
- 2022-11-22 : 주관식 답안 출력 오류 해결
- 2022-11-22 : 인공지능 과목 추가
---

- 2022-11-23 : 개념 화면 오류 수정
---

- 2022-12-01 : 컴퓨터구조 과목 추가 및 화면 오류 수정
---

- 이후는 업데이트 현황에서 확인 가능

## 화면 예시 ##
- ![1](https://user-images.githubusercontent.com/68932465/204839282-6499c1d9-3b8a-43cc-ba41-88c3c3dbb2ab.jpg)
![2](https://user-images.githubusercontent.com/68932465/204839289-99af6104-155c-41b2-a7e6-d15664abbe6a.jpg)
![3](https://user-images.githubusercontent.com/68932465/204839294-21757c2e-b062-4b7b-aeda-5ff228ba276a.jpg)
![4](https://user-images.githubusercontent.com/68932465/204839295-d6cf852f-926a-46ea-b57b-e702f975a5ad.jpg)
![5](https://user-images.githubusercontent.com/68932465/204839298-1a018e98-4173-409f-a4dd-249f353cd2ff.jpg)
![6](https://user-images.githubusercontent.com/68932465/204839302-3766f772-6efa-497d-93e1-87fefe52e5c2.jpg)
![7](https://user-images.githubusercontent.com/68932465/204839304-bef56d10-5a84-4926-9832-7a6c9664e911.jpg)
![8](https://user-images.githubusercontent.com/68932465/204839306-7f75482b-c208-48f2-937d-540a458b3a3e.jpg)
![9](https://user-images.githubusercontent.com/68932465/204839309-570f9285-4384-47d0-8596-fa6d2791561e.jpg)
![10](https://user-images.githubusercontent.com/68932465/204839311-5af44e8a-c7a8-49c9-b8c6-11c9321a69c0.jpg)
