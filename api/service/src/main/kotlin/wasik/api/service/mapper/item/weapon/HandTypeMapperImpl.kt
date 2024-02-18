package wasik.api.service.mapper.item.weapon

import domain.model.item.weapon.HandType
import org.springframework.stereotype.Component
import wasik.api.model.model.weapon.WeaponType

@Component
class HandTypeMapperImpl : HandTypeMapper {
    override suspend fun mapToHandType(weaponType: WeaponType): HandType {
        return when(weaponType) {
            WeaponType.MELEE, WeaponType.VERSATILE, WeaponType.RANGED -> HandType.ONE_HANDED
            WeaponType.TWO_HANDED_RANGED, WeaponType.TWO_HANDED_MELEE -> HandType.TWO_HANDED
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}