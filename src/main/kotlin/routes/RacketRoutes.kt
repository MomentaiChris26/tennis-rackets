package routes

import dto.RacketRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mapping.toModel
import repositories.RacketRepository

private const val ENDPOINT = "api/rackets"

private val logger = KotlinLogging.logger {}

fun Application.racketRoutes() {

    // Repository
    val rackets = RacketRepository()

    routing {
        get("/$ENDPOINT") {
            logger.debug { "findAll" }
            val racketsList = rackets.findAll()
            call.respond(HttpStatusCode.OK,racketsList)
        }

        get("/$ENDPOINT/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            logger.debug { "findById: $id" }
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val racket = rackets.findById(id)
            if (racket == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(HttpStatusCode.OK,racket)
        }

        get("/$ENDPOINT/brand/{brand}") {
            val brand = call.parameters["brand"]?.lowercase()
            logger.debug { "findByBrand: $brand" }
            if (brand == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val racketsList = rackets.findByBrand(brand)
            call.respond(HttpStatusCode.OK,racketsList)
        }

        post("/$ENDPOINT") {
            val racket = call.receive<RacketRequest>().toModel()
            logger.debug { "save: $racket" }
            val newRacket = rackets.save(racket)
            call.respond(HttpStatusCode.Created,newRacket)
        }

        put("/$ENDPOINT/{id}") {
            logger.debug { "PUT /$ENDPOINT/{id}" }
            val id = call.parameters["id"]?.toLongOrNull()

            id?.let {
                val old = rackets.findById(id)
                if(old == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@put
                }

                try {
                    val racket = call.receive<RacketRequest>().toModel()
                    val newRacket = rackets.save(racket.copy(id = id))
                    call.respond(HttpStatusCode.OK, newRacket)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message ?: "Error: ${e::class.simpleName}")
                }
            } ?: run {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/$ENDPOINT/{id}") {
            call.parameters["id"]?.toLong()?.let { id ->
                logger.debug { "delete: $id" }
                val isDeleted = rackets.delete(id)
                if (!isDeleted) {
                    call.respond(HttpStatusCode.NotFound)
                    return@delete
                }
                call.respond(HttpStatusCode.OK)
            } ?: run {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}