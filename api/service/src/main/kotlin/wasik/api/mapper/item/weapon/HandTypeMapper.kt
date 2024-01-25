package wasik.api.mapper.item.weapon

import model.item.weapon.HandType
import wasik.api.restResource.items.model.weapon.WeaponType

interface HandTypeMapper {
    suspend fun mapToHandType(weaponType: WeaponType) : HandType
}