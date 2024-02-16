package wasik.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.entity.DamageEntity
import wasik.entity.PropertyEntity

interface PropertyRepository : CoroutineCrudRepository<PropertyEntity, Int> {
}