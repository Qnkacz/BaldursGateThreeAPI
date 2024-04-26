package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import wasik.api.model.item.weapon.WeaponClass
import domain.model.item.weapon.WeaponClass as DomainWeaponCLass

@Component
class ClassMapper {
    fun mapToClass(weaponClass: WeaponClass): DomainWeaponCLass {
        return when (weaponClass) {
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
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MESSAGE)
        }
    }

    fun mapToApiWeaponClass(weaponClass: DomainWeaponCLass): WeaponClass {
        return when (weaponClass) {
            DomainWeaponCLass.BATTLEAXE -> WeaponClass.BATTLEAXE
            DomainWeaponCLass.CLUB -> WeaponClass.CLUB
            DomainWeaponCLass.DART -> WeaponClass.DART
            DomainWeaponCLass.MACE -> WeaponClass.MACE
            DomainWeaponCLass.MAUL -> WeaponClass.MAUL
            DomainWeaponCLass.PIKE -> WeaponClass.PIKE
            DomainWeaponCLass.DAGGER -> WeaponClass.DAGGER
            DomainWeaponCLass.GLAIVE -> WeaponClass.GLAIVE
            DomainWeaponCLass.RAPIER -> WeaponClass.RAPIER
            DomainWeaponCLass.SICKLE -> WeaponClass.SICKLE
            DomainWeaponCLass.FLAIL -> WeaponClass.FLAIL
            DomainWeaponCLass.SPEAR -> WeaponClass.SPEAR
            DomainWeaponCLass.HALBERD -> WeaponClass.HALBERD
            DomainWeaponCLass.HANDAXE -> WeaponClass.HANDAXE
            DomainWeaponCLass.JAVELIN -> WeaponClass.JAVELIN
            DomainWeaponCLass.LONGBOW -> WeaponClass.LONGBOW
            DomainWeaponCLass.TRIDENT -> WeaponClass.TRIDENT
            DomainWeaponCLass.GREATAXE -> WeaponClass.GREATAXE
            DomainWeaponCLass.SCIMITAR -> WeaponClass.SCIMITAR
            DomainWeaponCLass.SHORTBOW -> WeaponClass.SHORTBOW
            DomainWeaponCLass.WAR_PICK -> WeaponClass.WAR_PICK
            DomainWeaponCLass.GREATCLUB -> WeaponClass.GREATCLUB
            DomainWeaponCLass.LONGSWORD -> WeaponClass.LONGSWORD
            DomainWeaponCLass.WARHAMMER -> WeaponClass.WARHAMMER
            DomainWeaponCLass.GREATSWORD -> WeaponClass.GREATSWORD
            DomainWeaponCLass.SHORTSWORD -> WeaponClass.SHORTSWORD
            DomainWeaponCLass.MORNINGSTAR -> WeaponClass.MORNINGSTAR
            DomainWeaponCLass.LIGHT_HAMMER -> WeaponClass.LIGHT_HAMMER
            DomainWeaponCLass.QUARTERSTAVE -> WeaponClass.QUARTERSTAVE
            DomainWeaponCLass.HAND_CROSSBOW -> WeaponClass.HAND_CROSSBOW
            DomainWeaponCLass.HEAVY_CROSSBOW -> WeaponClass.HEAVY_CROSSBOW
            DomainWeaponCLass.LIGHT_CROSSBOW -> WeaponClass.LIGHT_CROSSBOW
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MESSAGE)
        }
    }

    private companion object {
        const val MAPPING_ERROR_MESSAGE = "Could not map weapon class"
    }
}