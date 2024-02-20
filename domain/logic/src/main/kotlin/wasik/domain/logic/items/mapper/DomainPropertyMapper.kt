package wasik.domain.logic.items.mapper

import kotlinx.coroutines.coroutineScope
import domain.model.misc.Property
import org.springframework.stereotype.Component
import wasik.infrastructure.model.entity.PropertyEntity

@Component
class DomainPropertyMapper {
    suspend fun mapToPropertyEntity(property: Property): PropertyEntity = coroutineScope {
        PropertyEntity(id = null, name = property.name, description = property.description)
    }
}