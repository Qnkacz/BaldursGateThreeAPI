package wasik.infrastructure.logic.repository.weapon

import wasik.infrastructure.model.entity.item.weapon.WeaponDamageEntity

interface WeaponDamageRepository{

    suspend fun saveWeaponDamage(weaponDamage: WeaponDamageEntity)

    suspend fun getWeaponDamage(weaponId: Int, damageId: Int): WeaponDamageEntity?
}