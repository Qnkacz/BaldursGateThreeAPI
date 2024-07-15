package wasik.domain.logic.misc

import domain.model.misc.Property
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.await
import org.springframework.stereotype.Service
import wasik.infrastructure.logic.repository.PropertyRepository
import wasik.infrastructure.model.table.PropertyEntity

@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository
) : PropertyService {
    override suspend fun postProperty(property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun postProperties(properties: Collection<Property>): List<PropertyEntity> = coroutineScope {
        properties.map { property ->
            async { propertyRepository.saveProperty(property).await() }
        }.awaitAll()
    }

    override suspend fun getProperty(id: Long): Property = coroutineScope {
        propertyRepository.findById(id).await()
    }

    override suspend fun updateProperty(property: Property): Property {
        TODO("Not yet implemented")
    }
}