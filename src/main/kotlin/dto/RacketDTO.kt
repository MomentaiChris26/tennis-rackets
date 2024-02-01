package dto

import kotlinx.serialization.Serializable
import models.Racket.Companion.DEFAULT_IMAGE
import java.time.LocalDateTime

@Serializable
data class RacketRequest(
    val brand: String,
    val model: String,
    val price: Double,
    val numberTennisPlayers: Int,
    val image: String = DEFAULT_IMAGE
)

@Serializable
data class RacketResponse(
    val id: Long,
    val brand: String,
    val model: String,
    val price: Double,
    val numberTennisPlayers: Int,
    val image: String,
    @Serializable(with = serializers.LocalDateSerializer::class)
    val createdAt: LocalDateTime,
    val updatedAt: String,
    val isDeleted: Boolean = false
)
