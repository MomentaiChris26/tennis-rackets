package dev.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import validation.racketValidation


fun Application.configureValidation() {
    install(RequestValidation) {
        racketValidation()
    }
}