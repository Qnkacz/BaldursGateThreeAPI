package wasik.repository.weapon

import wasik.entity.item.weapon.WeaponPropertyEntity

interface WeaponPropertyRepository {

    suspend fun saveWeaponProperty(weaponProperty: WeaponPropertyEntity)
}