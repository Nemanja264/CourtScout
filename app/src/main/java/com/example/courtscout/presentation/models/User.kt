package com.example.courtscout.presentation.models

import com.google.firebase.firestore.DocumentId

data class User(@DocumentId val id: String="",
    val email:String="",
    val fullName:String="",
    val phoneNumber:String="",
    val profileImage:String?=""
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val regex = query.split(" ").joinToString(".*") {
            Regex.escape(it)
        }.toRegex(RegexOption.IGNORE_CASE)
        return regex.containsMatchIn(fullName)
    }
}