package com.example.courtscout.presentation.repositories;

import java.lang.Exception
import com.example.courtscout.presentation.services.auth.AuthState

sealed class Resource<out R> {
    data class Success<out R>(val result: R): Resource<R>()
    data class Error(val message: String): Resource<Nothing>()
    data object Loading: Resource<Nothing>()
}
