package mapping

import entities.RacketEntity
import models.Racket

/**
 * Mapper for Racquet
 * With this we can map from DTO to Model and viceversa
 * In Kotlin we can use extension functions
 */

fun RacketEntity.toModel() = Racket(
    brand = this.brand,
    model = this.model,
    price = this.price,
    numberTennisPlayers = this.numberTennisPlayers,
    image = this.image
)

fun List<RacketEntity>.toModel() = this.map { it.toModel() }

fun Racket.toEntity() = RacketEntity(
    id = if (this.id == Racket.NEW_RACKET) null else this.id,
    brand = this.brand,
    model = this.model,
    price = this.price,
    numberTennisPlayers = this.numberTennisPlayers,
    image = this.image,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    isDeleted = this.isDeleted
)
