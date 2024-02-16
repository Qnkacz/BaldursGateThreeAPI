package wasik.items

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import model.item.weapon.Weapon
import org.springframework.stereotype.Service
import wasik.entity.ActionEntity
import wasik.entity.DamageEntity
import wasik.entity.PropertyEntity
import wasik.entity.item.weapon.WeaponActionEntity
import wasik.entity.item.weapon.WeaponDamageEntity
import wasik.entity.item.weapon.WeaponEntity
import wasik.entity.item.weapon.WeaponPropertyEntity
import wasik.items.mapper.ActionMapper
import wasik.items.mapper.DomainActionMapperImpl
import wasik.items.mapper.DamageInfraMapper
import wasik.items.mapper.PropertyMapper
import wasik.items.mapper.weapon.WeaponInfraMapper
import wasik.repository.ActionRepository
import wasik.repository.DamageRepository
import wasik.repository.PropertyRepository
import wasik.repository.weapon.*

@Service
class WeaponServiceImpl(
    private val weaponInfraMapper: WeaponInfraMapper,
    private val damageInfraMapper: DamageInfraMapper,
    private val propertyMapper: PropertyMapper,
    private val domainActionMapperImpl: ActionMapper,
    private val weaponRepository: WeaponRepository,
    private val damageRepository: DamageRepository,
    private val weaponDamageRepository: WeaponDamageRepository,
    private val propertyRepository: PropertyRepository,
    private val weaponPropertyRepository: WeaponPropertyRepository,
    private val actionRepository: ActionRepository,
    private val weaponActionRepository: WeaponActionRepository
) :
    WeaponService {
    override suspend fun postWeapon(weapon: Weapon): Unit = coroutineScope {
        //todo add validator for things link if weapon doesn't have like 10 dmg types and other nonsenses
        val savedWeaponEntity: WeaponEntity = saveWeaponEntity(weapon)
        // DAMAGES
        val savedDamages: List<DamageEntity> = saveDamageEntities(weapon)
        saveWeaponDamage(savedDamages, savedWeaponEntity)
        // PROPERTIES
        val savedProperties: List<PropertyEntity> = savePropertyEntities(weapon)
        saveWeaponProperties(savedProperties, savedWeaponEntity)
        // ACTIONS
        val savedActions : List<ActionEntity> = saveActionEntities(weapon)
        saveWeaponActions(savedActions, savedWeaponEntity)
    }

    override suspend fun getWeaponByName(name: String): Weapon {
        TODO("Not yet implemented")
    }

    override suspend fun updateWeapon(name: String, weapon: Weapon) {
        TODO("Not yet implemented")
    }

    private suspend fun saveWeaponDamage(
        savedDamages: List<DamageEntity>,
        savedWeaponEntity: WeaponEntity
    ) = coroutineScope {
        val weaponDamageList: List<WeaponDamageEntity> = savedDamages.map {
            WeaponDamageEntity(it.id!!, savedWeaponEntity.id!!)
        }.toList()
        weaponDamageList.map {
            async { weaponDamageRepository.saveWeaponDamage(it) }
        }
            .awaitAll()
    }

    private suspend fun saveWeaponProperties(
        savedProperties: List<PropertyEntity>,
        savedWeaponEntity: WeaponEntity
    ) = coroutineScope {
        val weaponPropertyList: List<WeaponPropertyEntity> = savedProperties.map {
            WeaponPropertyEntity(it.id!!, savedWeaponEntity.id!!)
        }.toList()
        weaponPropertyList.map {
            async { weaponPropertyRepository.saveWeaponProperty(it) }
        }
            .awaitAll()
    }

    private suspend fun saveWeaponActions(
        savedActions: List<ActionEntity>,
        savedWeaponEntity: WeaponEntity
    ) = coroutineScope {
        val weaponActionList: List<WeaponActionEntity> = savedActions.map {
            WeaponActionEntity(it.id!!, savedWeaponEntity.id!!)
        }.toList()
        weaponActionList.map {
            async { weaponActionRepository.saveWeaponAction(it) }
        }
            .awaitAll()
    }

    private suspend fun saveDamageEntities(weapon: Weapon): List<DamageEntity> = coroutineScope {
        val weaponDamage = weapon.damage.map {
            async {
                damageInfraMapper.mapToDamageEntity(it)
            }
        }.awaitAll()
        return@coroutineScope damageRepository.saveAll(weaponDamage).toList()
    }
    private suspend fun savePropertyEntities(weapon: Weapon): List<PropertyEntity> = coroutineScope {
        val weaponDamage = weapon.properties.map {
            async {
                propertyMapper.mapToPropertyEntity(it)
            }
        }.awaitAll()
        return@coroutineScope propertyRepository.saveAll(weaponDamage).toList()
    }
    private suspend fun saveActionEntities(weapon: Weapon): List<ActionEntity> = coroutineScope {
        val weaponDamage = weapon.actions.map {
            async {
                domainActionMapperImpl.mapToActionEntity(it)
            }
        }.awaitAll()
        return@coroutineScope actionRepository.saveAll(weaponDamage).toList()
    }

    private suspend fun saveWeaponEntity(weapon: Weapon): WeaponEntity = coroutineScope {
        val weaponEntity = async { weaponInfraMapper.mapToWeaponEntity(weapon) }
        return@coroutineScope weaponRepository.save(weaponEntity.await())
    }
}