package wasik.domain.logic.misc

import domain.model.misc.Property
import wasik.infrastructure.model.table.PropertyEntity

interface PropertyService {

    suspend fun postProperty(property: Property)
    suspend fun postProperties(properties: Collection<Property>): List<PropertyEntity>
    suspend fun getProperty(id: Long): Property
    suspend fun updateProperty(property: Property): Property
}