package wasik.domain.logic.items

import domain.model.damage.Damage
import domain.model.item.weapon.WeaponCommand
import domain.model.misc.Action
import domain.model.misc.Property
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.await
import org.jetbrains.exposed.dao.id.EntityID
import org.springframework.stereotype.Service
import wasik.domain.logic.items.validation.WeaponValidator
import wasik.domain.logic.misc.ActionService
import wasik.domain.logic.misc.DamageService
import wasik.domain.logic.misc.PropertyService
import wasik.infrastructure.logic.repository.weapon.WeaponRepository

@Service
class WeaponServiceImpl(
    private val weaponRepository: WeaponRepository,
    private val damageService: DamageService,
    private val propertyService: PropertyService,
    private val actionService: ActionService,
    private val weaponValidator: WeaponValidator
) :
    WeaponService {
    override suspend fun postWeapon(weaponCommand: WeaponCommand): Unit = coroutineScope {
        weaponValidator.validateWeapon(weaponCommand)
        val savedWeaponId = weaponRepository.saveWeapon(weaponCommand).await()
//        val savedDamages = async { damageService.postDamages(weaponCommand.damage) }
//        val savedProperties = async { propertyService.postProperties(weaponCommand.properties) }
//        val savedActions = async { actionService.postActions(weaponCommand.actions) }
//
//        weaponRepository.saveWeaponDamages(savedDamages.await(), savedWeaponId)
//        weaponRepository.saveWeaponProperties(savedProperties.await(), savedWeaponId)
//        weaponRepository.saveWeaponActions(savedActions.await(), savedWeaponId)
    }

    override suspend fun getWeaponByName(name: String): List<WeaponCommand> = coroutineScope {
        weaponValidator.validateName(name)
        val idWeaponPairs = weaponRepository.findByName(name).await()
        val weaponCommands: List<WeaponCommand> = idWeaponPairs.map {
            async { addMissingWeaponProperties(it.first, it.second) }
        }.awaitAll()

        return@coroutineScope weaponCommands
    }


    override suspend fun updateWeapon(name: String, weaponCommand: WeaponCommand) {
        TODO("Not yet implemented")
    }

    private suspend fun addMissingWeaponProperties(
        weaponId: EntityID<Long>,
        weaponCommandBuilder: WeaponCommand.Builder
    ): WeaponCommand =
        coroutineScope {
            val weaponDamages = async { getWeaponDamages(weaponId) }
            val weaponActions = async { getWeaponActions(weaponId) }
            val weaponProperties = async { getWeaponProperties(weaponId) }
            val result = awaitAll(weaponDamages, weaponActions, weaponProperties)
            weaponCommandBuilder.damage(result[0].filterIsInstance<Damage>())
            weaponCommandBuilder.actions(result[1].filterIsInstance<Action>())
            weaponCommandBuilder.properties(result[2].filterIsInstance<Property>())
            return@coroutineScope weaponCommandBuilder.build()
        }

    private suspend fun getWeaponActions(weaponId: EntityID<Long>): List<Action> = coroutineScope {
        val actionIds: List<EntityID<Long>> = weaponRepository.getWeaponActionsIds(weaponId).await()
        val weaponActions: List<Action> = actionIds.map {
            async {
                actionService.getAction(it.value)
            }
        }.awaitAll()
        return@coroutineScope weaponActions
    }

    private suspend fun getWeaponProperties(weaponId: EntityID<Long>): List<Property> = coroutineScope {
        val propertyIds: List<EntityID<Long>> = weaponRepository.getWeaponPropertiesIds(weaponId).await()
        val weaponProperties: List<Property> = propertyIds.map {
            async {
                propertyService.getProperty(it.value)
            }
        }.awaitAll()
        return@coroutineScope weaponProperties
    }

    private suspend fun getWeaponDamages(weaponId: EntityID<Long>): List<Damage> = coroutineScope {
        val damageIds: List<EntityID<Long>> = weaponRepository.getWeaponDamagesIds(weaponId).await()
        val weaponDamages: List<Damage> = damageIds.map {
            async {
                damageService.getDamageById(it.value)
            }
        }.awaitAll()
        return@coroutineScope weaponDamages
    }
}