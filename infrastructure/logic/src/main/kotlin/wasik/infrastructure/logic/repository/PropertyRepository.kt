package wasik.infrastructure.logic.repository

import domain.model.misc.Property
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import wasik.infrastructure.model.exception.InfrastructureException
import wasik.infrastructure.model.exception.InfrastructureExceptionType
import wasik.infrastructure.model.table.PropertyEntity
import wasik.infrastructure.model.table.PropertyTable
import java.util.concurrent.CompletableFuture

@Repository
open class PropertyRepository {

    fun saveProperty(property: Property): CompletableFuture<PropertyEntity> {
        val result = CompletableFuture<PropertyEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                transaction {
                    val propertyEntity = PropertyEntity.new {
                        name = property.name
                        description = property.description ?: ""
                    }
                    result.complete(propertyEntity)
                }
            } catch (ex: Exception) {
                throw InfrastructureException(
                    "Cannot insert property",
                    InfrastructureExceptionType.DATABASE_CONNECTION_ISSUE
                )
            }
        }
        return result
    }

    fun findById(id: Long): CompletableFuture<Property> {
        val result = CompletableFuture<Property>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val property: Property? =
                    PropertyTable.selectAll().where { PropertyTable.id eq id }.firstOrNull()?.let {
                        Property(it[PropertyTable.name], it[PropertyTable.description])
                    }
                if (property == null) {
                    throw NullPointerException("Damage with id $id not found")
                }
                result.complete(property)
            }
        }
        return result
    }
}