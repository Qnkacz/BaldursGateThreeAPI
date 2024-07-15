package wasik.domain.logic.items.validation

import domain.model.damage.Damage
import domain.model.exception.DomainException
import domain.model.exception.DomainExceptionType.VALIDATION_ERROR
import domain.model.item.weapon.WeaponCommand
import domain.model.item.weapon.WeaponClass
import domain.model.item.weapon.WeaponType
import domain.model.misc.Action
import domain.model.misc.Property
import io.klogging.Klogging
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component

@Component
class WeaponValidator: Klogging {

    private val allowedRangedWeaponClasses = setOf(
        WeaponClass.HAND_CROSSBOW,
        WeaponClass.HEAVY_CROSSBOW,
        WeaponClass.LIGHT_CROSSBOW,
        WeaponClass.LONGBOW,
        WeaponClass.SHORTBOW,
    )

    suspend fun validateWeapon(weaponCommand: WeaponCommand) {
        coroutineScope {
            val validations = mutableListOf(
                async { validateDamage(weaponCommand.damage) },
                async { validateActions(weaponCommand.actions) },
                async { validateProperties(weaponCommand.properties) },
                async { validateRange(weaponCommand.range)}
            )
            if (weaponCommand.type == WeaponType.RANGED) {
                validations.add(async { validateRangedWeaponClasses(weaponCommand.weaponClass) })
            }
            if (weaponCommand.type == WeaponType.MELEE) {
                validations.add(async { validateMeleeWeaponClasses(weaponCommand.weaponClass) })
            }
            validations.awaitAll()
        }
    }

    suspend fun validateName(name: String) {
        validateWeaponLength(name)
    }

    private suspend fun validateRange(range: Float) {
        if (range !in 1f..100f) {
            logger.error { "Range was $range" }
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "Provided weapon range must be between 1 and 100"
            )
        }
    }

    private suspend fun validateWeaponLength(name: String) {
        if (name.length !in 51 downTo 1) {
            logger.error("Weapon name length was: ${name.length}")
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "Provided weapon name length must be between 1 and 50 characters"
            )
        }
    }

    private suspend fun validateDamage(damage: Set<Damage>) {
        val maxAllowed = 4
        if (damage.isEmpty()) {
            logger.error("Damage is empty")
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "A weapon must have damage information"
            )
        }
        if (damage.count() > maxAllowed) {
            logger.error("Damage had: ${damage.count()} elements")
            throw DomainException(type = VALIDATION_ERROR, message = "A weapon must have at most 4 damage attributes")
        }
    }

    private suspend fun validateActions(actions: Set<Action>) {
        val maxAllowed = 4
        if (actions.count() > maxAllowed) {
            logger.error("Actions had ${actions.count()} elements")
            throw DomainException(type = VALIDATION_ERROR, message = "A weapon must have at most 4 actions")
        }
    }

    private suspend fun validateProperties(properties: Set<Property>) {
        val maxAllowed = 3
        if (properties.count() > maxAllowed) {
            logger.error("Properties had ${properties.count()} elements")
            throw DomainException(type = VALIDATION_ERROR, message = "A weapon must have at most 3 actions")
        }
    }

    private suspend fun validateRangedWeaponClasses(weaponClass: WeaponClass) {
        if (!allowedRangedWeaponClasses.contains(weaponClass)) {
            logger.error("provided weapon class was: $weaponClass")
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "Provided weapon class ${weaponClass.name} cannot be Ranged"
            )
        }
    }

    private suspend fun validateMeleeWeaponClasses(weaponClass: WeaponClass) {
        if (allowedRangedWeaponClasses.contains(weaponClass)) {
            logger.error("provided weapon class was: $weaponClass")
            throw DomainException(
                type = VALIDATION_ERROR,
                message = "Provided weapon class ${weaponClass.name} cannot be Melee"
            )
        }
    }
}