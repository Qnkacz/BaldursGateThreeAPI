package wasik.api.service.mapper.item.weapon

import domain.model.item.weapon.HandType
import wasik.api.model.model.weapon.WeaponType

interface HandTypeMapper {
    suspend fun mapToHandType(weaponType: WeaponType) : HandType
}