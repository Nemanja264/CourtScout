package com.example.courtscout.presentation.services.auth

import android.net.Uri
import com.example.courtscout.presentation.models.User
import com.example.courtscout.presentation.repositories.Resource

interface AuthService {
    val currentUser: User?
    suspend fun login(email:String, password:String): AuthState
    suspend fun register(email: String, password: String, fullName: String, phoneNumber: String, profileImage: Uri): AuthState
    suspend fun signOut()
    suspend fun getUser(): Resource<User>
}