package wasik.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.entity.ActionEntity

interface ActionRepository : CoroutineCrudRepository<ActionEntity, Int>