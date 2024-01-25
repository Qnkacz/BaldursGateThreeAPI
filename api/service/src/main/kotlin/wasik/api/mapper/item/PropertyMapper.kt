package wasik.api.mapper.item

import wasik.api.restResource.items.model.Property
import model.item.Property as DomainProperty

interface PropertyMapper {
    suspend fun mapToProperty(property: Property): DomainProperty
}