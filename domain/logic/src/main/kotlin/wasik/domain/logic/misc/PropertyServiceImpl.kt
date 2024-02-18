package wasik.domain.logic.misc

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import domain.model.misc.Action
import domain.model.misc.Property
import org.springframework.stereotype.Service
import wasik.domain.logic.items.mapper.DomainPropertyMapper
import wasik.infrastructure.model.entity.PropertyEntity
import wasik.infrastructure.logic.repository.PropertyRepository

@Service
class PropertyServiceImpl(
    private val domainPropertyMapper: DomainPropertyMapper,
    private val propertyRepository: PropertyRepository
) : PropertyService {
    override suspend fun postProperty(property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun postProperties(properties: Collection<Property>): Flow<PropertyEntity> = coroutineScope {
        val weaponDamage = properties.map {
            async {
                domainPropertyMapper.mapToPropertyEntity(it)
            }
        }.awaitAll()
        return@coroutineScope propertyRepository.saveAll(weaponDamage)
    }

    override suspend fun getProperty(name: String): Action {
        TODO("Not yet implemented")
    }

    override suspend fun updateProperty(property: Property): Property {
        TODO("Not yet implemented")
    }
}