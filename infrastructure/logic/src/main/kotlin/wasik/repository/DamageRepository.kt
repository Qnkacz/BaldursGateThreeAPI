package wasik.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.entity.DamageEntity

interface DamageRepository : CoroutineCrudRepository<DamageEntity, Int> {
}