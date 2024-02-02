package wasik.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import wasik.entity.item.weapon.WeaponDamageEntity
import kotlinx.coroutines.flow.Flow

interface WeaponDamageRepository : CoroutineCrudRepository<WeaponDamageEntity, WeaponDamageEntity.WeaponDamageId> {

}