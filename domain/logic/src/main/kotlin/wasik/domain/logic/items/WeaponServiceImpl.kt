package wasik.domain.logic.items

import domain.model.item.weapon.WeaponCommand
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.await
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
        return@coroutineScope weaponRepository.findByName(name).await()
    }


    override suspend fun updateWeapon(name: String, weaponCommand: WeaponCommand) {
        TODO("Not yet implemented")
    }
}