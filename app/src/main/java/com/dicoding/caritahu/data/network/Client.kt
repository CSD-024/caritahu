package com.dicoding.caritahu.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private const val baseUrl = "https://uneroapi.azurewebsites.net/"

    val retrofit: Endpoint by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)
        retrofit
    }
}