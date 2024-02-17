package wasik.misc

import kotlinx.coroutines.flow.Flow
import model.misc.Action
import model.misc.Property
import wasik.entity.PropertyEntity

interface PropertyService {

    suspend fun postProperty(property: Property)
    suspend fun postProperties(properties: Collection<Property>): Flow<PropertyEntity>
    suspend fun getProperty(name: String): Action
    suspend fun updateProperty(property: Property): Property
}