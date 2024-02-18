package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.model.weapon.WeaponClass
import domain.model.item.weapon.WeaponClass as DomainWeaponCLass

@Component
class ClassMapperImpl : ClassMapper {
    override suspend fun mapToClass(weaponClass: WeaponClass): DomainWeaponCLass {
        return when(weaponClass) {
            WeaponClass.BATTLEAXE -> DomainWeaponCLass.BATTLEAXE
            WeaponClass.CLUB -> DomainWeaponCLass.CLUB
            WeaponClass.DAGGER -> DomainWeaponCLass.DAGGER
            WeaponClass.DART -> DomainWeaponCLass.DART
            WeaponClass.FLAIL -> DomainWeaponCLass.FLAIL
            WeaponClass.GLAIVE -> DomainWeaponCLass.GLAIVE
            WeaponClass.GREATAXE -> DomainWeaponCLass.GREATAXE
            WeaponClass.GREATCLUB -> DomainWeaponCLass.GREATCLUB
            WeaponClass.GREATSWORD -> DomainWeaponCLass.GREATSWORD
            WeaponClass.HALBERD -> DomainWeaponCLass.HALBERD
            WeaponClass.HANDAXE -> DomainWeaponCLass.HANDAXE
            WeaponClass.HAND_CROSSBOW -> DomainWeaponCLass.HAND_CROSSBOW
            WeaponClass.HEAVY_CROSSBOW -> DomainWeaponCLass.HEAVY_CROSSBOW
            WeaponClass.JAVELIN -> DomainWeaponCLass.JAVELIN
            WeaponClass.LIGHT_CROSSBOW -> DomainWeaponCLass.LIGHT_CROSSBOW
            WeaponClass.LIGHT_HAMMER -> DomainWeaponCLass.LIGHT_HAMMER
            WeaponClass.LONGBOW -> DomainWeaponCLass.LONGBOW
            WeaponClass.LONGSWORD -> DomainWeaponCLass.LONGSWORD
            WeaponClass.MACE -> DomainWeaponCLass.MACE
            WeaponClass.MAUL -> DomainWeaponCLass.MAUL
            WeaponClass.MORNINGSTAR -> DomainWeaponCLass.MORNINGSTAR
            WeaponClass.PIKE -> DomainWeaponCLass.PIKE
            WeaponClass.QUARTERSTAVE -> DomainWeaponCLass.QUARTERSTAVE
            WeaponClass.RAPIER -> DomainWeaponCLass.RAPIER
            WeaponClass.SCIMITAR -> DomainWeaponCLass.SCIMITAR
            WeaponClass.SHORTBOW -> DomainWeaponCLass.SHORTBOW
            WeaponClass.SHORTSWORD -> DomainWeaponCLass.SHORTSWORD
            WeaponClass.SICKLE -> DomainWeaponCLass.SICKLE
            WeaponClass.SPEAR -> DomainWeaponCLass.SPEAR
            WeaponClass.TRIDENT -> DomainWeaponCLass.TRIDENT
            WeaponClass.WAR_PICK -> DomainWeaponCLass.WAR_PICK
            WeaponClass.WARHAMMER -> DomainWeaponCLass.WARHAMMER
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}