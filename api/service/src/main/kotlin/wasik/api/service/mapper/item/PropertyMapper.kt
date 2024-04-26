package wasik.api.service.mapper.item

import org.springframework.stereotype.Component
import wasik.api.model.Property
import domain.model.misc.Property as DomainProperty

@Component
class PropertyMapper {
    fun mapToProperty(property: Property): DomainProperty {
        return DomainProperty(property.name, property.description)
    }

    fun mapToApiProperty(property: DomainProperty): Property {
        return Property(property.name, property.description)
    }
}