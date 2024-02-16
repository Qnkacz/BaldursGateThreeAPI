package wasik.repository.weapon

import reactor.core.publisher.Flux
import wasik.entity.item.weapon.WeaponDamageEntity

interface WeaponDamageRepository{

    suspend fun saveWeaponDamage(weaponDamage: WeaponDamageEntity)

    suspend fun getWeaponDamage(weaponId: Int, damageId: Int): WeaponDamageEntity?
}