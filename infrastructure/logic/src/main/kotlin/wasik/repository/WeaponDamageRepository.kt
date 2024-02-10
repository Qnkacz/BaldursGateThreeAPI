package wasik.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import reactor.core.publisher.Flux
import wasik.entity.item.weapon.WeaponDamageEntity

interface WeaponDamageRepository : CoroutineCrudRepository<WeaponDamageEntity, Pair<Int, Int>> {

    suspend fun saveWeaponDamage(weaponDamage: WeaponDamageEntity): Flux<MutableMap<String, Any>>

    suspend fun getWeaponDamage(weaponId: Int, damageId: Int): WeaponDamageEntity?
}