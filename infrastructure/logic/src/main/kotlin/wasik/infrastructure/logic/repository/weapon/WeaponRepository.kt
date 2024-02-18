package wasik.infrastructure.logic.repository.weapon

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.infrastructure.model.entity.item.weapon.WeaponEntity

interface WeaponRepository : CoroutineCrudRepository<WeaponEntity, Int>