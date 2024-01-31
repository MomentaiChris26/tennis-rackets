package dev.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import routes.racketRoutes

// Define the routing of our application
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!!")
        }
    }
    racketRoutes()
}
