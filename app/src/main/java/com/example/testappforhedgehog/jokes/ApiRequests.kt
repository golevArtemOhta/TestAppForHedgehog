package com.example.testappforhedgehog.jokes

import com.example.testappforhedgehog.jokes.JokesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {

    @GET("/jokes/random/{count}")
    suspend fun getJokeRandom(@Path("count") user: String) : JokesResponse
}