package com.example.courtscout.presentation.services.auth

data class RegisterInput(
    val fullName: String,
    val email: String,
    val password: String,
    val confirm: String
)

class RegisterValidator() : FieldsValidator<RegisterInput>{
    override fun validate(input: RegisterInput): Boolean {
        if (input.email.isBlank() || input.password.isBlank() || input.fullName.isBlank() || input.confirm.isBlank())
        {
            return false
        }

        if (input.password != input.confirm) { return false }

        return true
    }
}