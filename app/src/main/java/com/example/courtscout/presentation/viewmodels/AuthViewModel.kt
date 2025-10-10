package com.example.courtscout.presentation.viewmodels

import androidx.lifecycle.ViewModel
import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.courtscout.presentation.services.auth.AuthState
import com.example.courtscout.presentation.services.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AuthViewModel(private val authService: AuthService): ViewModel(){
    private val _authState = MutableStateFlow<AuthState?>(null)
    val authState = _authState.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        _authState.value = AuthState.Loading
        val result = authService.login(email, password)
        _authState.value = result
    }

    fun signOut() = viewModelScope.launch {
        authService.signOut()
        _authState.value = AuthState.UnAuthenticated
    }

    fun register(
        email: String,
        password: String,
        fullName: String,
        phoneNumber: String,
        profileImage: Uri
    ) = viewModelScope.launch {

        _authState.value = AuthState.Loading
        try {
            val result = authService.register(email, password, fullName, phoneNumber, profileImage)
            _authState.value = result
        } catch (e: Exception) {
            _authState.value = AuthState.Error(
                e.localizedMessage ?: "Registration failed."
            )
        }
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }
}