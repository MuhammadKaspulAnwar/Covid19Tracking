package com.example.basickotlin.retrofit

import com.example.basickotlin.model.IndonesiaResponse
import com.example.basickotlin.model.ProvinsiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoronaEndPoint {
    @GET("indonesia")
    fun getIndonesia(): Call<ArrayList<IndonesiaResponse>>


    @GET("indonesia/provinsi")
    fun getProvinsi(
    ): Call<ArrayList<ProvinsiResponse>>
}