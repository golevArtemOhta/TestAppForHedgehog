package com.example.testappforhedgehog.jokes

data class Joke(
    val categories: List<Any>,
    val id: Int,
    val joke: String
)