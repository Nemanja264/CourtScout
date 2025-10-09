package com.example.courtscout.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.courtscout.presentation.services.auth.AuthService
import com.example.courtscout.presentation.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.courtscout.presentation.services.auth.FirebaseAuthService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val authService: AuthService = FirebaseAuthService(
                firebaseAuth = FirebaseAuth.getInstance(),
                firebaseDb = FirebaseFirestore.getInstance(),
                 firebaseStorage = FirebaseStorage.getInstance()
            )
            AuthViewModel(authService)
        }
    }
}