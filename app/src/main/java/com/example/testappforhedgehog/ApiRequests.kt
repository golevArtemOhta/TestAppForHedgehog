package com.example.testappforhedgehog

import com.example.testappforhedgehog.api.JokesJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {

    @GET("/jokes/random/{count}")
    fun getJokeRandom(@Path("count") user: String) : Call<JokesJson>

}