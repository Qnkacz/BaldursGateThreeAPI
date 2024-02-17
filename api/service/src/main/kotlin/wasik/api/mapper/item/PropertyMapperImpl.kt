package wasik.api.mapper.item

import org.springframework.stereotype.Component
import wasik.api.restResource.items.model.Property
import model.misc.Property as DomainProperty

@Component
class PropertyMapperImpl : PropertyMapper {
    override suspend fun mapToProperty(property: Property): DomainProperty {
        return DomainProperty(property.name, property.description)
    }
}