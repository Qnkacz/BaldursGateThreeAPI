package wasik.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.entity.item.weapon.WeaponEntity

interface WeaponRepository : CoroutineCrudRepository<WeaponEntity, Int>