package wasik.api.service.mapper.item

import wasik.api.model.Property
import domain.model.misc.Property as DomainProperty

interface PropertyMapper {
    suspend fun mapToProperty(property: Property): DomainProperty
}