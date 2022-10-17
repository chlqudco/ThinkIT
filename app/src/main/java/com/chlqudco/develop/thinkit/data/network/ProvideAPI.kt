package com.chlqudco.develop.thinkit.data.network

import com.chlqudco.develop.thinkit.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal fun provideThinkITApiService(retrofit: Retrofit): ConceptApiService {
    return retrofit.create(ConceptApiService::class.java)
}

internal fun provideThinkITQuizApiService(retrofit: Retrofit): QuizApiService {
    return retrofit.create(QuizApiService::class.java)
}

internal fun provideThinkITFeedbackApiService(retrofit: Retrofit): FeedbackApiService {
    return retrofit.create(FeedbackApiService::class.java)
}

internal fun provideThinkITRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.THINK_IT_BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

internal fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create(
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    )
}

internal fun buildOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}
