package wasik.items.mapper

import model.misc.Property
import wasik.entity.PropertyEntity

interface PropertyMapper {
    suspend fun mapToPropertyEntity(property: Property): PropertyEntity
}