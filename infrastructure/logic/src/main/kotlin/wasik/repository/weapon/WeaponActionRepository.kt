package wasik.repository.weapon

import wasik.entity.item.weapon.WeaponActionEntity

interface WeaponActionRepository {

    suspend fun saveWeaponAction(weaponActionEntity: WeaponActionEntity)
}