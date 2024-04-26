package wasik.api.service.mapper.item.weapon

import domain.model.damage.Damage
import domain.model.item.weapon.HandType
import domain.model.item.weapon.WeaponType
import domain.model.misc.Property
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component
import wasik.api.model.model.weapon.Weapon
import wasik.api.service.mapper.item.CommonItemDataMapper
import wasik.api.service.mapper.item.PropertyMapper
import wasik.api.service.mapper.item.damage.DamageMapper
import domain.model.item.weapon.Weapon as DomainWeaponModel
import wasik.api.model.model.Damage as ApiWeaponDamage
import wasik.api.model.model.weapon.WeaponType as ApiWeaponType

@Component
class WeaponMapper(
    private val commonItemDataMapper: CommonItemDataMapper,
    private val actionMapper: ActionMapper,
    private val classMapper: ClassMapper,
    private val handTypeMapper: HandTypeMapper,
    private val proficiencyMapper: ProficiencyMapper,
    private val typeMapper: TypeMapper,
    private val propertyMapper: PropertyMapper,
    private val damageMapper: DamageMapper
) {
    suspend fun mapToWeapon(weapon: Weapon): DomainWeaponModel = coroutineScope {
        val commonItemData = async { commonItemDataMapper.mapToCommonItemInfo(weapon) }
        val weaponClass = async { classMapper.mapToClass(weapon.weaponClass) }
        val handType = async { handTypeMapper.mapToHandType(weapon.type) }
        val proficiency = async { proficiencyMapper.mapToProficiency(weapon.proficiency) }
        val weaponType = async { typeMapper.mapToType(weapon.type) }
        val properties: Collection<Property> = weapon.properties
            .map {
                async {
                    propertyMapper.mapToProperty(it)
                }
            }
            .awaitAll()
        val damage: Collection<Damage> = weapon.damage
            .map {
                async {
                    damageMapper.mapToDamage(it)
                }
            }
            .awaitAll()
        val actions = weapon.actions
            .map {
                async {
                    actionMapper.mapToAction(it)
                }
            }
            .awaitAll()
        DomainWeaponModel(
            commonData = commonItemData.await(),
            weaponClass = weaponClass.await(),
            proficiency = proficiency.await(),
            type = weaponType.await(),
            damage = damage.toSet(),
            actions = actions.toSet(),
            handType = handType.await(),
            properties = properties.toSet()
        )
    }

    suspend fun mapToWeaponResponse(weapon: DomainWeaponModel): Weapon = coroutineScope {

        val commonData = async { commonItemDataMapper.mapToApiItemCommonData(weapon.commonData) }
        val proficiency = async { proficiencyMapper.mapToApiProficiency(weapon.proficiency) }
        val apiWeaponType = async { mapApiWeaponType(weapon.handType, weapon.type) }
        val weaponClass = async { classMapper.mapToApiWeaponClass(weapon.weaponClass) }
        val damage: Collection<ApiWeaponDamage> = weapon.damage
            .map {
                async {
                    damageMapper.mapToApiDamage(it)
                }
            }
            .awaitAll()
        val actions = weapon.actions
            .map {
                async {
                    actionMapper.mapToApiAction(it)
                }
            }
            .awaitAll()
        val properties = weapon.properties
            .map {
                async {
                    propertyMapper.mapToApiProperty(it)
                }
            }
            .awaitAll()
        Weapon(
            name = commonData.await().name,
            rarity = commonData.await().rarity,
            proficiency = proficiency.await(),
            type = apiWeaponType.await(),
            weaponClass = weaponClass.await(),
            range = 0f, //TODO BW RANGE IS IGNORED
            weight = commonData.await().weight,
            value = commonData.await().value,
            damage = damage.toSet(),
            description = commonData.await().description,
            actions = actions.toSet(),
            properties = properties.toSet()
        )
    }

    private fun mapApiWeaponType(hand: HandType, weaponType: WeaponType): ApiWeaponType {
        return when (hand) {
            HandType.ONE_HANDED -> {
                when (weaponType) {
                    WeaponType.MELEE -> ApiWeaponType.MELEE
                    WeaponType.VERSATILE -> ApiWeaponType.VERSATILE
                    WeaponType.RANGED -> ApiWeaponType.RANGED
                }
            }

            HandType.TWO_HANDED -> {
                when (weaponType) {
                    WeaponType.MELEE -> ApiWeaponType.TWO_HANDED_MELEE
                    WeaponType.VERSATILE -> throw IllegalArgumentException("TWO_HANDED and VERSATILE combination is not valid")
                    WeaponType.RANGED -> ApiWeaponType.TWO_HANDED_RANGED
                }
            }
        }
    }
}