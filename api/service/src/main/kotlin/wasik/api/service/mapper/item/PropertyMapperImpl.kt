package wasik.api.service.mapper.item

import org.springframework.stereotype.Component
import wasik.api.model.Property
import domain.model.misc.Property as DomainProperty

@Component
class PropertyMapperImpl : PropertyMapper {
    override suspend fun mapToProperty(property: Property): DomainProperty {
        return DomainProperty(property.name, property.description)
    }
}