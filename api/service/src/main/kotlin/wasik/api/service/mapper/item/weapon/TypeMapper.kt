package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.model.weapon.WeaponType
import domain.model.item.weapon.WeaponType as DomainWeaponType

@Component
class TypeMapper {
    suspend fun mapToType(weaponType: WeaponType): DomainWeaponType {
        return when(weaponType) {
            WeaponType.MELEE, WeaponType.TWO_HANDED_MELEE -> DomainWeaponType.MELEE
            WeaponType.VERSATILE -> DomainWeaponType.VERSATILE
            WeaponType.TWO_HANDED_RANGED, WeaponType.RANGED -> DomainWeaponType.RANGED
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}