package com.example.testappforhedgehog.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {

    @GET("/jokes/random/{count}")
    suspend fun getJokeRandom(@Path("count") user: String) : JokesJson
}