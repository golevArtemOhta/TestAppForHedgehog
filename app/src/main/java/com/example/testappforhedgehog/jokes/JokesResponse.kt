package com.example.testappforhedgehog.jokes

import com.example.testappforhedgehog.jokes.Joke
import com.google.gson.annotations.SerializedName

data class JokesResponse(
    val type: String,
    @SerializedName("value")
    val jokes: List<Joke>
)