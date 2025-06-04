package com.example.core.data.remote.model // Updated package

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "email")
    val email: String
)
