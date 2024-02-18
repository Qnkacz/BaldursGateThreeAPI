package wasik.api.service.mapper.item.weapon

import wasik.api.model.model.weapon.WeaponType

interface TypeMapper {
    suspend fun mapToType(weaponType: WeaponType) : domain.model.item.weapon.WeaponType
}