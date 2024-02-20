package wasik.infrastructure.logic.repository.weapon

import wasik.infrastructure.model.entity.item.weapon.WeaponPropertyEntity

interface WeaponPropertyRepository {

    suspend fun saveWeaponProperty(weaponProperty: WeaponPropertyEntity)
}