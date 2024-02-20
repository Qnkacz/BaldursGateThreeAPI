package wasik.infrastructure.logic.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.infrastructure.model.entity.PropertyEntity

interface PropertyRepository : CoroutineCrudRepository<PropertyEntity, Int>