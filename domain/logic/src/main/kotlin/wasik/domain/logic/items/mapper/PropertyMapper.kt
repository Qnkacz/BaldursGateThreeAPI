package wasik.domain.logic.items.mapper

import domain.model.misc.Property
import wasik.infrastructure.model.entity.PropertyEntity

interface PropertyMapper {
    suspend fun mapToPropertyEntity(property: Property): PropertyEntity
}