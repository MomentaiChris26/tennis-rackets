package dev

import dev.plugins.configureRouting
import dev.plugins.configureSerialization
import dev.plugins.configureStatusPages
import dev.plugins.configureValidation
import io.ktor.server.application.*

/*
 Has the code that launches our service and where the plugins to configure are indicated.
*/
fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureValidation()
    configureStatusPages()
}
