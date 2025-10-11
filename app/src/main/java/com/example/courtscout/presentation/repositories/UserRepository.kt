package com.example.courtscout.presentation.repositories

import com.example.courtscout.presentation.models.User
interface UserRepository {
    suspend fun getAllUsers(): Resource<List<User>>
    suspend fun getUser(id: String): Resource<User>
    suspend fun addPoints(id: String, points: Int): Resource<String>
}