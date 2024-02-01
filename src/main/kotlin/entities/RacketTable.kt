package entities

import models.Racket.Companion.DEFAULT_IMAGE
import org.ufoss.kotysa.postgresql.PostgresqlTable
import java.time.LocalDateTime

object RacketTable : PostgresqlTable<RacketEntity>("racket") {
    // Autoincrement and primary key
    val id = bigSerial(RacketEntity::id, "id").primaryKey()

    // Other fields
    val brand = varchar(RacketEntity::brand)
    val model = varchar(RacketEntity::model)
    val price = doublePrecision(RacketEntity::price)
    val numberTennisPlayers = integer(RacketEntity::numberTennisPlayers, "number_tennis_players")
    val image = varchar(RacketEntity::image, "image")

    // metadata
    val createdAt = timestamp(RacketEntity::createdAt, "created_at")
    val updatedAt = timestamp(RacketEntity::updatedAt, "updated_at")
    val isDeleted = boolean(RacketEntity::isDeleted, "is_deleted")
}


/**
 * Racket Entity
 * We can use this class to map from Entity Row to Model and viceversa
 * We use it because we can't use the same class for both (avoid id nullable)
 * Or adapt some fields type to the database
 */
data class RacketEntity(
    val id: Long?, //
    val brand: String,
    val model: String,
    val price: Double,
    val numberTennisPlayers: Int = 0,
    val image: String = DEFAULT_IMAGE,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val isDeleted: Boolean = false
)