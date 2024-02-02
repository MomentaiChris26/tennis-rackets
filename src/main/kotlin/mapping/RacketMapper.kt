package mapping

import dto.RacketRequest
import dto.RacketResponse
import models.Racket

/**
 * Mapper for Racquet
 * With this we can map from DTO to Model and viceversa
 * In Kotlin we can use extension functions
 */

fun RacketRequest.toModel() = Racket(
    brand = this.brand,
    model = this.model,
    price = this.price,
    numberTennisPlayers = this.numberTennisPlayers,
    image = this.image
)

fun Racket.toResponse() = RacketResponse(
    id = this.id,
    brand = this.brand,
    model = this.model,
    price = this.price,
    numberTennisPlayers = this.numberTennisPlayers,
    image = this.image,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt.toString(),
    isDeleted = this.isDeleted
)

fun List<Racket>.toResponse() = this.map { it.toResponse() }

