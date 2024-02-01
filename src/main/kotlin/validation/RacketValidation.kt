package validation

import dto.RacketRequest
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.racketValidation() {
    validate<RacketRequest> {racket ->
        if(racket.brand.isBlank() || racket.brand.length < 3) {
            ValidationResult.Invalid("Brand must be at least 3 characters long")
        } else if (racket.model.isBlank())  {
            ValidationResult.Invalid("Model must not be blank")
        } else if (racket.price < 0.0) {
            ValidationResult.Invalid("Price must be greater than 0")
        } else if (racket.numberTennisPlayers < 0) {
            ValidationResult.Invalid("Number of tennis players must be greater than 0")
        }
        else {
            ValidationResult.Valid
        }
    }
}