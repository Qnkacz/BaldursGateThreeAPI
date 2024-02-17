package wasik.items

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import model.item.weapon.Weapon
import org.springframework.stereotype.Service
import wasik.entity.ActionEntity
import wasik.entity.DamageEntity
import wasik.entity.PropertyEntity
import wasik.entity.item.weapon.WeaponActionEntity
import wasik.entity.item.weapon.WeaponDamageEntity
import wasik.entity.item.weapon.WeaponEntity
import wasik.entity.item.weapon.WeaponPropertyEntity
import wasik.items.mapper.weapon.WeaponInfraMapper
import wasik.misc.ActionService
import wasik.misc.DamageService
import wasik.misc.PropertyService
import wasik.repository.weapon.WeaponActionRepository
import wasik.repository.weapon.WeaponDamageRepository
import wasik.repository.weapon.WeaponPropertyRepository
import wasik.repository.weapon.WeaponRepository

@Service
class WeaponServiceImpl(
    private val weaponInfraMapper: WeaponInfraMapper,
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
        //todo add validator for things link if weapon doesn't have like 10 dmg types and other nonsenses
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
        val weaponEntity = async { weaponInfraMapper.mapToWeaponEntity(weapon) }
        return@coroutineScope weaponRepository.save(weaponEntity.await())
    }
}