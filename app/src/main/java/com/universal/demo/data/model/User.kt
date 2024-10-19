package com.universal.demo.data.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: Abdul Rehman
 */

data class User(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("login")
    val name: String?,
    @SerializedName("url")
    val email: String?,
    @SerializedName("avatar_url")
    val avatar: String
)