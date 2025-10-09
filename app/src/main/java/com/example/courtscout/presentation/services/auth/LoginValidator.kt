package com.example.courtscout.presentation.services.auth

data class LoginInput(
    val email: String,
    val password: String
)

class LoginValidator(): FieldsValidator<LoginInput>{
    override fun validate(input: LoginInput): Boolean {
        if (input.email.isBlank() || input.password.isBlank())
        {
            return false
        }

        return true
    }
}