package com.ambrosio.plainreddit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {

        fun getRetroInstance(): Retrofit {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

            httpClient.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
    }
}