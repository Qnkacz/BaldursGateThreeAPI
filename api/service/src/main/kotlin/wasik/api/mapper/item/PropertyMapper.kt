package wasik.api.mapper.item

import wasik.api.restResource.items.model.Property
import model.misc.Property as DomainProperty

interface PropertyMapper {
    suspend fun mapToProperty(property: Property): DomainProperty
}