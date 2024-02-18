package wasik.infrastructure.logic.repository.weapon

import wasik.infrastructure.model.entity.item.weapon.WeaponActionEntity

interface WeaponActionRepository {

    suspend fun saveWeaponAction(weaponActionEntity: WeaponActionEntity)
}