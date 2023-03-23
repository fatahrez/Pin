package com.fatahrez.feature_auth.domain.use_case

class ValidateName {

    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Name can't be blank"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}