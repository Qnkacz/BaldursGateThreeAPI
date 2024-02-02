package wasik.items

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.map
import model.item.weapon.Weapon
import org.springframework.stereotype.Service
import wasik.DamageInfraMapper
import wasik.entity.DamageEntity
import wasik.entity.item.weapon.WeaponDamageEntity
import wasik.items.mapper.WeaponInfraMapper
import wasik.entity.item.weapon.WeaponEntity
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
    override suspend fun postWeapon(weapon: Weapon): WeaponEntity = coroutineScope {
        //todo add validator for things link if weapon doesn't have like 10 dmg types and other nonsenses
        val weaponEntity = async { weaponInfraMapper.mapToWeaponEntity(weapon) }
        val weaponDamage = weapon.damage.map {
            async {
                damageInfraMapper.mapToDamageEntity(it)
            }
        }
            .awaitAll()
        val savedWeaponEntity = weaponRepository.save(weaponEntity.await())
        val savedDamages = damageRepository.saveAll(weaponDamage)
        savedDamages
            .map {  createWeaponDamageEntity(savedWeaponEntity, it)}
            .collect { weaponDamageRepository.save(it)}


        return@coroutineScope savedWeaponEntity
    }

    override suspend fun getWeaponByName(name: String): Weapon {
        TODO("Not yet implemented")
    }

    override suspend fun updateWeapon(name: String, weapon: Weapon): Weapon {
        TODO("Not yet implemented")
    }

    private fun createWeaponDamageEntity(
        weaponEntity: WeaponEntity,
        damageEntity: DamageEntity
    ): WeaponDamageEntity {
        val weaponDamageEntityId = WeaponDamageEntity.WeaponDamageId(damageEntity.id!!, weaponEntity.id!!)
        return WeaponDamageEntity(weaponDamageEntityId, damageEntity.id!!, weaponEntity.id!!)
    }
}