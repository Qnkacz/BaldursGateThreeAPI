package wasik.domain.logic.items

import domain.model.item.weapon.Weapon
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
    override suspend fun postWeapon(weapon: Weapon): Unit = coroutineScope {
        weaponValidator.validateWeapon(weapon)
        val savedWeaponId = weaponRepository.saveWeapon(weapon).await()
        val savedDamages = async { damageService.postDamages(weapon.damage) }
        val savedProperties = async { propertyService.postProperties(weapon.properties) }
        val savedActions = async { actionService.postActions(weapon.actions) }

        weaponRepository.saveWeaponDamages(savedDamages.await(), savedWeaponId)
        weaponRepository.saveWeaponProperties(savedProperties.await(), savedWeaponId)
        weaponRepository.saveWeaponActions(savedActions.await(), savedWeaponId)
    }

    override suspend fun getWeaponByName(name: String): List<Weapon> = coroutineScope {
        weaponValidator.validateName(name)
        return@coroutineScope weaponRepository.findAll().await().filter { it.commonData.name == name }
    }


    override suspend fun updateWeapon(name: String, weapon: Weapon) {
        TODO("Not yet implemented")
    }
}