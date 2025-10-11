package com.example.courtscout.presentation.models

data class ScoreBoardEntry(
    val rank: Int,
    val userId: String,
    val userName: String,
    val score: Int,
    val profilePictureUrl: String? = null
)
