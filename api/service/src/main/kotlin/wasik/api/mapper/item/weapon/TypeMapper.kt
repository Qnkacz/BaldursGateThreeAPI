package wasik.api.mapper.item.weapon

import wasik.api.restResource.items.model.weapon.WeaponType

interface TypeMapper {
    suspend fun mapToType(weaponType: WeaponType) : model.item.weapon.WeaponType
}