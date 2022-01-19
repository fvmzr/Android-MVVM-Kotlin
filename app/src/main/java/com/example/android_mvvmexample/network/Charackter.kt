package com.example.android_mvvmexample.network


import com.squareup.moshi.Json

data class Character(
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String
)

data class Info(
    @Json(name = "count")
    val count: Int,
    @Json(name = "pages")
    val pages: Int
)

data class CharacterResponse(
    @Json(name = "results")
    val result: List<Character>,
    @Json(name = "info")
    val info: Info
)