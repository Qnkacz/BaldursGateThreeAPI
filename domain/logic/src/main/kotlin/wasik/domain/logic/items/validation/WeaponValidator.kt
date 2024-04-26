package wasik.domain.logic.items.validation

import domain.model.damage.Damage
import domain.model.exception.DomainException
import domain.model.exception.DomainExceptionType.VALIDATION_ERROR
import domain.model.item.weapon.Weapon
import domain.model.item.weapon.WeaponClass
import domain.model.item.weapon.WeaponType
import domain.model.misc.Action
import domain.model.misc.Property
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component

@Component
class WeaponValidator {

    private val allowedRangedWeaponClasses = setOf(
        WeaponClass.HAND_CROSSBOW,
        WeaponClass.HEAVY_CROSSBOW,
        WeaponClass.LIGHT_CROSSBOW,
        WeaponClass.LONGBOW,
        WeaponClass.SHORTBOW,
    )

    suspend fun validateWeapon(weapon: Weapon) {
        coroutineScope {
            val validations = mutableListOf(
                async { validateDamage(weapon.damage) },
                async { validateActions(weapon.actions) },
                async { validateProperties(weapon.properties) },
            )
            if (weapon.type == WeaponType.RANGED) {
                validations.add(async { validateRangedWeaponClasses(weapon.weaponClass) })
            }
            if (weapon.type == WeaponType.MELEE) {
                validations.add(async { validateMeleeWeaponClasses(weapon.weaponClass) })
            }
            validations.awaitAll()
        }
    }

    fun validateName(name: String) {
        validateWeaponLength(name)
    }

    private fun validateWeaponLength(name: String) {
        if (name.length in 51 downTo 1) {
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "Provided weapon name length must be between 1 and 50 characters"
            )
        }
    }

    private fun validateDamage(damage: Set<Damage>) {
        val maxAllowed = 4
        if (damage.isEmpty()) {
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "A weapon must have damage information"
            )
        }
        if (damage.count() > maxAllowed) {
            throw DomainException(type = VALIDATION_ERROR, message = "A weapon must have at most 4 damage attributes")
        }
    }

    private fun validateActions(actions: Set<Action>) {
        val maxAllowed = 4
        if (actions.count() > maxAllowed) {
            throw DomainException(type = VALIDATION_ERROR, message = "A weapon must have at most 4 actions")
        }
    }

    private fun validateProperties(properties: Set<Property>) {
        val maxAllowed = 3
        if (properties.count() > maxAllowed) {
            throw DomainException(type = VALIDATION_ERROR, message = "A weapon must have at most 3 actions")
        }
    }

    private fun validateRangedWeaponClasses(weaponClass: WeaponClass) {
        if (!allowedRangedWeaponClasses.contains(weaponClass)) {
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "Provided weapon class ${weaponClass.name} cannot be Ranged"
            )
        }
    }

    private fun validateMeleeWeaponClasses(weaponClass: WeaponClass) {
        if (allowedRangedWeaponClasses.contains(weaponClass)) {
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "Provided weapon class ${weaponClass.name} cannot be Melee"
            )
        }
    }
}