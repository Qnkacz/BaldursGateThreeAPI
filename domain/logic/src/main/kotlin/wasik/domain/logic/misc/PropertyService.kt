package wasik.domain.logic.misc

import domain.model.misc.Property
import org.jetbrains.exposed.dao.id.EntityID

interface PropertyService {

    suspend fun postProperty(property: Property)
    suspend fun postProperties(properties: Collection<Property>): List<EntityID<Long>>
    suspend fun getProperty(id: Long): Property
    suspend fun updateProperty(property: Property): Property
}