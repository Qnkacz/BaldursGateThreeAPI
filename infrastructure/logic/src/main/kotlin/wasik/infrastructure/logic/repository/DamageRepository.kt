package wasik.infrastructure.logic.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.infrastructure.model.entity.DamageEntity

interface DamageRepository : CoroutineCrudRepository<DamageEntity, Int>