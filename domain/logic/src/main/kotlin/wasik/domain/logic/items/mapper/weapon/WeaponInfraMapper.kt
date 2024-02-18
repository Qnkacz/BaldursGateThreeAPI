package wasik.domain.logic.items.mapper.weapon

import domain.model.item.weapon.Weapon
import wasik.infrastructure.model.entity.item.weapon.WeaponEntity

interface WeaponInfraMapper {
    suspend fun mapToWeaponEntity(weapon: Weapon): WeaponEntity
}