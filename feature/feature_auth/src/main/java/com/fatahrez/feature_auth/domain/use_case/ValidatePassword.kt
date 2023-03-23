package com.fatahrez.feature_auth.domain.use_case


class ValidatePassword {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password can't be blank"
            )
        }
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must be at least 6 characters"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}