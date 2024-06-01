package wasik.domain.logic.items

import domain.model.damage.Damage
import domain.model.item.weapon.WeaponCommand
import domain.model.misc.Action
import domain.model.misc.Property
import kotlinx.coroutines.async
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
        val savedDamages = async { damageService.postDamages(weaponCommand.damage) }
        val savedProperties = async { propertyService.postProperties(weaponCommand.properties) }
        val savedActions = async { actionService.postActions(weaponCommand.actions) }

        weaponRepository.saveWeaponDamages(savedDamages.await(), savedWeaponId)
        weaponRepository.saveWeaponProperties(savedProperties.await(), savedWeaponId)
        weaponRepository.saveWeaponActions(savedActions.await(), savedWeaponId)
    }

    override suspend fun getWeaponByName(name: String): List<WeaponCommand> = coroutineScope {
        weaponValidator.validateName(name)
        val listOfPairs = weaponRepository.findByName(name).await()
        val ids: List<EntityID<Long>> = listOfPairs.map { it.first }

        //do for one and then make it a loop
        val firstId = ids.get(0)
        val singleWeaponCommand = listOfPairs.map { it.second }.get(0)
        val actionId: EntityID<Long> = weaponRepository.getWeaponActionsIds(firstId).await().get(0)
        val damageId: EntityID<Long> = weaponRepository.getWeaponDamagesIds(firstId).await().get(0)
        val propertyId: EntityID<Long> = weaponRepository.getWeaponPropertiesIds(firstId).await().get(0)

        //services
        val damageById: Damage = damageService.getDamageById(damageId.value)
        val property: Property = propertyService.getProperty(propertyId.value)
        val action: Action = actionService.getAction(actionId.value)

        // TODO need to create it, maybe builder instead of data class?
        singleWeaponCommand.damage(damageById)
        singleWeaponCommand.actions(action)
        singleWeaponCommand.properties(property)


        return@coroutineScope listOf(singleWeaponCommand.build())
    }


    override suspend fun updateWeapon(name: String, weaponCommand: WeaponCommand) {
        TODO("Not yet implemented")
    }
}