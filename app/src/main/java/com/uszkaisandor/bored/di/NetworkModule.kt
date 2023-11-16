package com.uszkaisandor.bored.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.uszkaisandor.bored.network.api.BoredApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBoredApi(retrofit: Retrofit) = retrofit.create(BoredApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ) = Retrofit.Builder()
        .baseUrl(BORED_API_URL)
        .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    private const val CONTENT_TYPE = "application/json"
    private const val BORED_API_URL = "http://www.boredapi.com"
    private const val CONNECT_TIMEOUT = 10L
    private const val READ_WRITE_TIMEOUT = 30L
    private const val CALL_TIMEOUT = 60L

}