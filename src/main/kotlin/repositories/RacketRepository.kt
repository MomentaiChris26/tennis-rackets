package repositories

import data.racketsDemoData
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import models.Racket
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}


class RacketRepository {
    private val rackets = racketsDemoData()

    suspend fun findAll(): Flow<Racket> = withContext(Dispatchers.IO) {
        logger.debug { "findAll" }

        // Only return rackets that are not deleted
        return@withContext rackets.values.filter { !it.isDeleted }.asFlow()
    }

    suspend fun findById(id: Long): Racket? = withContext(Dispatchers.IO) {
        logger.debug { "findById: $id" }

        return@withContext rackets[id]
    }

    suspend fun findByBrand(brand: String): Flow<Racket> = withContext(Dispatchers.IO) {
        logger.debug { "findByBrand: $brand" }

        return@withContext rackets.values.filter { it.brand?.lowercase() == brand }.asFlow()
    }

    suspend fun save(entity: Racket): Racket = withContext(Dispatchers.IO) {
        logger.debug { "save: $entity" }

        if(entity.id == Racket.NEW_RACKET) {
            create(entity)
        } else {
            update(entity)
        }
    }

    suspend fun delete(id: Long): Boolean = withContext(Dispatchers.IO) {
        logger.debug { "delete: $id" }

        val racket = rackets[id] ?: return@withContext false
        rackets[id] = racket.copy(isDeleted = true, updatedAt = LocalDateTime.now())
        return@withContext true
    }

    private fun create(entity: Racket): Racket {
        logger.debug { "create: $entity" }
        val id = rackets.keys.maxOrNull()?.plus(1) ?: 1
        val newEntity = entity.copy(id = id)
        rackets[id] = newEntity
        return newEntity
    }

    private fun update(entity: Racket): Racket {
        logger.debug { "Update: $entity" }
        rackets[entity.id] = entity.copy(updatedAt = LocalDateTime.now())
        return entity
    }
}