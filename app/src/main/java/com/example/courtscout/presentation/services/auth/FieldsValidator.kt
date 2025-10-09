package com.example.courtscout.presentation.services.auth

interface FieldsValidator<T> {
    fun validate(input: T) : Boolean
}