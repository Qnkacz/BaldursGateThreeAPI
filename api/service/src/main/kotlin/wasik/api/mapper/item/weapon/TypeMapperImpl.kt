package wasik.api.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.restResource.items.model.weapon.WeaponType
import model.item.weapon.WeaponType as DomainWeaponType

@Component
class TypeMapperImpl : TypeMapper {
    override suspend fun mapToType(weaponType: WeaponType): DomainWeaponType {
        return when(weaponType) {
            WeaponType.MELEE, WeaponType.TWO_HANDED_MELEE -> DomainWeaponType.MELEE
            WeaponType.VERSATILE -> DomainWeaponType.VERSATILE
            WeaponType.TWO_HANDED_RANGED, WeaponType.RANGED -> DomainWeaponType.RANGED
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}