package com.example.courtscout.data

data class User(
    val id: String = "",
    val username: String = "",
    val email: String = "",
    val fullName: String = "",
    val phoneNumber: String = "",
    val profilePictureUrl: String? = null
    )