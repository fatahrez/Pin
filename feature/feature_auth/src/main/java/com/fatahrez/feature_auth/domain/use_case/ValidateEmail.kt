package com.fatahrez.feature_auth.domain.use_case

import com.fatahrez.common.util.isValidEmail

class ValidateEmail {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if (!email.isValidEmail()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter a valid Email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}