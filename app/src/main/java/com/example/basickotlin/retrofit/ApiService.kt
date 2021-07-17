package com.example.basickotlin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val BASE_URL : String = "https://api.kawalcorona.com/"
    val instance: CoronaEndPoint by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(CoronaEndPoint::class.java)
    }
}