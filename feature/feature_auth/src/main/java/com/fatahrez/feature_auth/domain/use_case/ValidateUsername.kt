package com.fatahrez.feature_auth.domain.use_case

class ValidateUsername {

    operator fun invoke(username: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username can't be blank"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}