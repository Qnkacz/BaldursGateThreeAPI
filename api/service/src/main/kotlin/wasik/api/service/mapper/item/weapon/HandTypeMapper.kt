package wasik.api.service.mapper.item.weapon

import domain.model.item.weapon.HandType
import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import wasik.api.model.model.weapon.WeaponType

@Component
class HandTypeMapper {
    suspend fun mapToHandType(weaponType: WeaponType): HandType {
        return when(weaponType) {
            WeaponType.MELEE, WeaponType.VERSATILE, WeaponType.RANGED -> HandType.ONE_HANDED
            WeaponType.TWO_HANDED_RANGED, WeaponType.TWO_HANDED_MELEE -> HandType.TWO_HANDED
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = "Could not map weapon type")
        }
    }
}