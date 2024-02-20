package wasik.domain.logic.misc

import kotlinx.coroutines.flow.Flow
import domain.model.misc.Action
import domain.model.misc.Property
import wasik.infrastructure.model.entity.PropertyEntity

interface PropertyService {

    suspend fun postProperty(property: Property)
    suspend fun postProperties(properties: Collection<Property>): Flow<PropertyEntity>
    suspend fun getProperty(name: String): Action
    suspend fun updateProperty(property: Property): Property
}