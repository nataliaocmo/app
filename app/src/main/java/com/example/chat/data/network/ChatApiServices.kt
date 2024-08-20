package com.example.chat.data.network

import PostResponse
import com.example.chat.data.model.PostRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL =
    "https://generativelanguage.googleapis.com"

val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface GeminiApiServices {
    @POST("/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyDixTv2DdQfuDCTia_RFVGGDZbALeGwXHU")
    suspend fun getMessage(@Body request: PostRequest) : Response<PostResponse>

}

object ChatApi{
    val retrofitService: GeminiApiServices by lazy{
        retrofit.create(GeminiApiServices::class.java)
    }
}