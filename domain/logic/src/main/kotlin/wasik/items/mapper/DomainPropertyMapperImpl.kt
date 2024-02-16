package wasik.items.mapper

import kotlinx.coroutines.coroutineScope
import model.item.Property
import org.springframework.stereotype.Component
import wasik.entity.PropertyEntity

@Component
class DomainPropertyMapperImpl : PropertyMapper {
    override suspend fun mapToPropertyEntity(property: Property): PropertyEntity = coroutineScope {
        PropertyEntity(id = null, name = property.name, description = property.description)
    }
}