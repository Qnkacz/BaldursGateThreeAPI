package wasik.items

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import model.item.weapon.Weapon
import org.springframework.stereotype.Service
import wasik.DamageInfraMapper
import wasik.entity.DamageEntity
import wasik.entity.item.weapon.WeaponDamageEntity
import wasik.entity.item.weapon.WeaponEntity
import wasik.items.mapper.WeaponInfraMapper
import wasik.repository.DamageRepository
import wasik.repository.WeaponDamageRepository
import wasik.repository.WeaponRepository

@Service
class WeaponServiceImpl(
    private val weaponInfraMapper: WeaponInfraMapper,
    private val damageInfraMapper: DamageInfraMapper,
    private val weaponRepository: WeaponRepository,
    private val damageRepository: DamageRepository,
    private val weaponDamageRepository: WeaponDamageRepository
) :
    WeaponService {
    override suspend fun postWeapon(weapon: Weapon): Unit = coroutineScope {
        //todo add validator for things link if weapon doesn't have like 10 dmg types and other nonsenses
        val savedWeaponEntity: WeaponEntity = saveWeaponEntity(weapon)
        val savedDamages: List<DamageEntity> = saveDamageEntities(weapon)
        saveWeaponDamage(savedDamages, savedWeaponEntity)
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

    private suspend fun saveDamageEntities(weapon: Weapon): List<DamageEntity> = coroutineScope {
        val weaponDamage = weapon.damage.map {
            async {
                damageInfraMapper.mapToDamageEntity(it)
            }
        }
            .awaitAll()
        return@coroutineScope damageRepository.saveAll(weaponDamage).toList()
    }

    private suspend fun saveWeaponEntity(weapon: Weapon): WeaponEntity = coroutineScope {
        val weaponEntity = async { weaponInfraMapper.mapToWeaponEntity(weapon) }
        return@coroutineScope weaponRepository.save(weaponEntity.await())
    }
}