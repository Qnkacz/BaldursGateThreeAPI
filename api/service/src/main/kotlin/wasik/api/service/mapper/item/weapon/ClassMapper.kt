package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import wasik.api.model.item.weapon.WeaponClass
import domain.model.item.weapon.WeaponClass as DomainWeaponClass

@Component
class ClassMapper {
    fun mapToClass(weaponClass: WeaponClass): DomainWeaponClass {
        return when (weaponClass) {
            WeaponClass.BATTLEAXE -> DomainWeaponClass.BATTLEAXE
            WeaponClass.CLUB -> DomainWeaponClass.CLUB
            WeaponClass.DAGGER -> DomainWeaponClass.DAGGER
            WeaponClass.DART -> DomainWeaponClass.DART
            WeaponClass.FLAIL -> DomainWeaponClass.FLAIL
            WeaponClass.GLAIVE -> DomainWeaponClass.GLAIVE
            WeaponClass.GREATAXE -> DomainWeaponClass.GREATAXE
            WeaponClass.GREATCLUB -> DomainWeaponClass.GREATCLUB
            WeaponClass.GREATSWORD -> DomainWeaponClass.GREATSWORD
            WeaponClass.HALBERD -> DomainWeaponClass.HALBERD
            WeaponClass.HANDAXE -> DomainWeaponClass.HANDAXE
            WeaponClass.HAND_CROSSBOW -> DomainWeaponClass.HAND_CROSSBOW
            WeaponClass.HEAVY_CROSSBOW -> DomainWeaponClass.HEAVY_CROSSBOW
            WeaponClass.JAVELIN -> DomainWeaponClass.JAVELIN
            WeaponClass.LIGHT_CROSSBOW -> DomainWeaponClass.LIGHT_CROSSBOW
            WeaponClass.LIGHT_HAMMER -> DomainWeaponClass.LIGHT_HAMMER
            WeaponClass.LONGBOW -> DomainWeaponClass.LONGBOW
            WeaponClass.LONGSWORD -> DomainWeaponClass.LONGSWORD
            WeaponClass.MACE -> DomainWeaponClass.MACE
            WeaponClass.MAUL -> DomainWeaponClass.MAUL
            WeaponClass.MORNINGSTAR -> DomainWeaponClass.MORNINGSTAR
            WeaponClass.PIKE -> DomainWeaponClass.PIKE
            WeaponClass.QUARTERSTAVE -> DomainWeaponClass.QUARTERSTAVE
            WeaponClass.RAPIER -> DomainWeaponClass.RAPIER
            WeaponClass.SCIMITAR -> DomainWeaponClass.SCIMITAR
            WeaponClass.SHORTBOW -> DomainWeaponClass.SHORTBOW
            WeaponClass.SHORTSWORD -> DomainWeaponClass.SHORTSWORD
            WeaponClass.SICKLE -> DomainWeaponClass.SICKLE
            WeaponClass.SPEAR -> DomainWeaponClass.SPEAR
            WeaponClass.TRIDENT -> DomainWeaponClass.TRIDENT
            WeaponClass.WAR_PICK -> DomainWeaponClass.WAR_PICK
            WeaponClass.WARHAMMER -> DomainWeaponClass.WARHAMMER
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MESSAGE)
        }
    }

    fun mapToApiWeaponClass(weaponClass: DomainWeaponClass): WeaponClass {
        return when (weaponClass) {
            DomainWeaponClass.BATTLEAXE -> WeaponClass.BATTLEAXE
            DomainWeaponClass.CLUB -> WeaponClass.CLUB
            DomainWeaponClass.DART -> WeaponClass.DART
            DomainWeaponClass.MACE -> WeaponClass.MACE
            DomainWeaponClass.MAUL -> WeaponClass.MAUL
            DomainWeaponClass.PIKE -> WeaponClass.PIKE
            DomainWeaponClass.DAGGER -> WeaponClass.DAGGER
            DomainWeaponClass.GLAIVE -> WeaponClass.GLAIVE
            DomainWeaponClass.RAPIER -> WeaponClass.RAPIER
            DomainWeaponClass.SICKLE -> WeaponClass.SICKLE
            DomainWeaponClass.FLAIL -> WeaponClass.FLAIL
            DomainWeaponClass.SPEAR -> WeaponClass.SPEAR
            DomainWeaponClass.HALBERD -> WeaponClass.HALBERD
            DomainWeaponClass.HANDAXE -> WeaponClass.HANDAXE
            DomainWeaponClass.JAVELIN -> WeaponClass.JAVELIN
            DomainWeaponClass.LONGBOW -> WeaponClass.LONGBOW
            DomainWeaponClass.TRIDENT -> WeaponClass.TRIDENT
            DomainWeaponClass.GREATAXE -> WeaponClass.GREATAXE
            DomainWeaponClass.SCIMITAR -> WeaponClass.SCIMITAR
            DomainWeaponClass.SHORTBOW -> WeaponClass.SHORTBOW
            DomainWeaponClass.WAR_PICK -> WeaponClass.WAR_PICK
            DomainWeaponClass.GREATCLUB -> WeaponClass.GREATCLUB
            DomainWeaponClass.LONGSWORD -> WeaponClass.LONGSWORD
            DomainWeaponClass.WARHAMMER -> WeaponClass.WARHAMMER
            DomainWeaponClass.GREATSWORD -> WeaponClass.GREATSWORD
            DomainWeaponClass.SHORTSWORD -> WeaponClass.SHORTSWORD
            DomainWeaponClass.MORNINGSTAR -> WeaponClass.MORNINGSTAR
            DomainWeaponClass.LIGHT_HAMMER -> WeaponClass.LIGHT_HAMMER
            DomainWeaponClass.QUARTERSTAVE -> WeaponClass.QUARTERSTAVE
            DomainWeaponClass.HAND_CROSSBOW -> WeaponClass.HAND_CROSSBOW
            DomainWeaponClass.HEAVY_CROSSBOW -> WeaponClass.HEAVY_CROSSBOW
            DomainWeaponClass.LIGHT_CROSSBOW -> WeaponClass.LIGHT_CROSSBOW
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MESSAGE)
        }
    }

    private companion object {
        const val MAPPING_ERROR_MESSAGE = "Could not map weapon class"
    }
}