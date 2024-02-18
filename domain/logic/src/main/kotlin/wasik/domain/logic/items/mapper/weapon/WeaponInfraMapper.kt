package wasik.domain.logic.items.mapper.weapon

import kotlinx.coroutines.coroutineScope
import domain.model.item.weapon.Weapon
import org.springframework.stereotype.Component
import wasik.infrastructure.model.entity.item.weapon.WeaponEntity

@Component
class WeaponInfraMapper {
    suspend fun mapToWeaponEntity(weapon: Weapon): WeaponEntity = coroutineScope {
        WeaponEntity(
            null,
            weapon.commonData.name,
            rarity = weapon.rarity.ordinal,
            value = weapon.commonData.value,
            weight = weapon.commonData.weight,
            description = weapon.commonData.description,
            weaponClass = weapon.weaponClass.ordinal,
            proficiency = weapon.proficiency.ordinal,
            handType = weapon.handType.ordinal,
            type = weapon.type.ordinal,
            upgrade = null
        )
    }
}