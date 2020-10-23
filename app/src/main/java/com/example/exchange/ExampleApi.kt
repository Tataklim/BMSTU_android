package com.example.exchange

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val API_URL = "https://min-api.cryptocompare.com"

val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(API_URL)
    .build()

interface ExampleApiService {
    @GET("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,JPY,EUR")
    suspend fun getData(): List<ExampleData>
}

object ExampleApi {
    val retrofitService: ExampleApiService by lazy {
        retrofit.create(ExampleApiService::class.java)
    }
}