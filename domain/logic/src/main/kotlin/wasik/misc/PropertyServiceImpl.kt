package wasik.misc

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import model.misc.Action
import model.misc.Property
import org.springframework.stereotype.Service
import wasik.entity.PropertyEntity
import wasik.items.mapper.PropertyMapper
import wasik.repository.PropertyRepository

@Service
class PropertyServiceImpl(
    private val propertyMapper: PropertyMapper,
    private val propertyRepository: PropertyRepository
) : PropertyService {
    override suspend fun postProperty(property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun postProperties(properties: Collection<Property>): Flow<PropertyEntity> = coroutineScope {
        val weaponDamage = properties.map {
            async {
                propertyMapper.mapToPropertyEntity(it)
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