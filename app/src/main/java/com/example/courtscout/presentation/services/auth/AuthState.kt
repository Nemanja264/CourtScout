package com.example.courtscout.presentation.services.auth

sealed class AuthState{
    data object Authenticated: AuthState()
    data object UnAuthenticated: AuthState()
    data object Loading: AuthState()
    data class Error(val message: String): AuthState()
}