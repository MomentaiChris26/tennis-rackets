package models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import serializers.LocalDateSerializer
import java.time.LocalDateTime

/**
 * The `id` field of the `Racket` class.
 *
 * This field is marked as `Transient` which means it will be ignored during serialization and deserialization.
 * This is useful when you want to hide certain data from the client, for example, when sending a `Racket` object as
 * a response to a `GET` request, the `id` field will not be included in the JSON representation of the `Racket` object.
 */

@Serializable
data class Racket(
    @Transient val id: Long = NEW_RACKET,
    val brand: String,
    val model: String,
    val price: Double,
    val numberTennisPlayers: Int = 0,
    val image: String = DEFAULT_IMAGE,
    @Serializable(with = LocalDateSerializer::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Serializable(with = LocalDateSerializer::class)
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    @Transient val isDeleted: Boolean = false
) {
    companion object {
        const val NEW_RACKET = -1L
        const val DEFAULT_IMAGE = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Tennis_Racket_and_Balls.jpg/800px-Tennis_Racket_and_Balls.jpg"

    }
}
