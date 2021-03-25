package com.github.novotnyr.presentr

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://ics.upjs.sk/~novotnyr/android/demo/presentr/index.php/"

interface PresentrApi {
    @GET("available-users")
    suspend fun getUsers(): List<User>

    @POST("available-users/{login}")
    suspend fun login(@Path("login") login: String)
}

val presentr: PresentrApi by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()
}