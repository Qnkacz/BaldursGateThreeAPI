package wasik.infrastructure.logic.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.infrastructure.model.entity.ActionEntity

interface ActionRepository : CoroutineCrudRepository<ActionEntity, Int>