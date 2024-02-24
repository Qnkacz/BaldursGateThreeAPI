package wasik.domain.logic.items

import domain.model.exception.DomainException
import domain.model.exception.DomainExceptionType
import domain.model.item.weapon.Weapon
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import wasik.domain.logic.items.mapper.weapon.WeaponInfrastructureMapper
import wasik.domain.logic.misc.ActionService
import wasik.domain.logic.misc.DamageService
import wasik.domain.logic.misc.PropertyService
import wasik.infrastructure.logic.repository.weapon.WeaponActionRepository
import wasik.infrastructure.logic.repository.weapon.WeaponDamageRepository
import wasik.infrastructure.logic.repository.weapon.WeaponPropertyRepository
import wasik.infrastructure.logic.repository.weapon.WeaponRepository
import wasik.infrastructure.model.entity.ActionEntity
import wasik.infrastructure.model.entity.DamageEntity
import wasik.infrastructure.model.entity.PropertyEntity
import wasik.infrastructure.model.entity.item.weapon.WeaponActionEntity
import wasik.infrastructure.model.entity.item.weapon.WeaponDamageEntity
import wasik.infrastructure.model.entity.item.weapon.WeaponEntity
import wasik.infrastructure.model.entity.item.weapon.WeaponPropertyEntity

@Service
class WeaponServiceImpl(
    private val weaponInfrastructureMapper: WeaponInfrastructureMapper,
    private val damageService: DamageService,
    private val propertyService: PropertyService,
    private val weaponRepository: WeaponRepository,
    private val weaponDamageRepository: WeaponDamageRepository,
    private val weaponPropertyRepository: WeaponPropertyRepository,
    private val actionService: ActionService,
    private val weaponActionRepository: WeaponActionRepository
) :
    WeaponService {
    override suspend fun postWeapon(weapon: Weapon): Unit = coroutineScope {
        if (weapon.damage.first().dieAmount < 1) { // Add dedicated validator and move it there
            throw DomainException(
                type = DomainExceptionType.VALIDATION_ERROR,
                message = "Damage die amount cannot be less than 1"
            )
        }
        val savedWeaponEntity: WeaponEntity = saveWeaponEntity(weapon)
        val savedDamages = async { damageService.postDamages(weapon.damage) }
        val savedProperties = async { propertyService.postProperties(weapon.properties) }
        val savedActions = async { actionService.postActions(weapon.actions) }
        saveWeaponDamage(savedDamages.await(), savedWeaponEntity)
        saveWeaponProperties(savedProperties.await(), savedWeaponEntity)
        saveWeaponActions(savedActions.await(), savedWeaponEntity)

    }

    override suspend fun getWeaponByName(name: String): Weapon {
        TODO("Not yet implemented")
    }

    override suspend fun updateWeapon(name: String, weapon: Weapon) {
        TODO("Not yet implemented")
    }

    private suspend fun saveWeaponDamage(
        savedDamages: Flow<DamageEntity>,
        savedWeaponEntity: WeaponEntity
    ) = coroutineScope {
        savedDamages
            .map { WeaponDamageEntity(it.id!!, savedWeaponEntity.id!!) }
            .collect { weaponDamageRepository.saveWeaponDamage(it) }
    }

    private suspend fun saveWeaponProperties(
        savedProperties: Flow<PropertyEntity>,
        savedWeaponEntity: WeaponEntity
    ) = coroutineScope {
        savedProperties
            .map { WeaponPropertyEntity(it.id!!, savedWeaponEntity.id!!) }
            .collect { weaponPropertyRepository.saveWeaponProperty(it) }
    }

    private suspend fun saveWeaponActions(
        savedActions: Flow<ActionEntity>,
        savedWeaponEntity: WeaponEntity
    ) = coroutineScope {
        savedActions
            .map { WeaponActionEntity(it.id!!, savedWeaponEntity.id!!) }
            .collect { weaponActionRepository.saveWeaponAction(it) }
    }

    private suspend fun saveWeaponEntity(weapon: Weapon): WeaponEntity = coroutineScope {
        val weaponEntity = async { weaponInfrastructureMapper.mapToWeaponEntity(weapon) }
        return@coroutineScope weaponRepository.save(weaponEntity.await())
    }
}