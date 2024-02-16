package wasik.items.mapper

import model.item.Property
import wasik.entity.PropertyEntity

interface PropertyMapper {
    suspend fun mapToPropertyEntity(property: Property) : PropertyEntity
}