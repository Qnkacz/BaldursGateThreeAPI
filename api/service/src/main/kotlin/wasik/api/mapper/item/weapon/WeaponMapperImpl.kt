package wasik.api.mapper.item.weapon

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import model.damage.Damage
import model.item.Property
import model.item.weapon.Action
import org.springframework.stereotype.Component
import wasik.api.mapper.item.CommonItemDataMapper
import wasik.api.mapper.item.PropertyMapper
import wasik.api.mapper.item.RarityMapper
import wasik.api.mapper.item.damage.DamageMapper
import wasik.api.restResource.items.model.weapon.Weapon
import model.item.weapon.Weapon as DomainWeapon

@Component
class WeaponMapperImpl(
    private val commonItemDataMapper: CommonItemDataMapper,
    private val actionMapper: ActionMapper,
    private val classMapper: ClassMapper,
    private val handTypeMapper: HandTypeMapper,
    private val proficiencyMapper: ProficiencyMapper,
    private val typeMapper: TypeMapper,
    private val propertyMapper: PropertyMapper,
    private val rarityMapper: RarityMapper,
    private val damageMapper: DamageMapper
) : WeaponMapper {
    override suspend fun mapToWeapon(weapon: Weapon): DomainWeapon = coroutineScope {
        val commonItemData = async { commonItemDataMapper.mapToCommonItemInfo(weapon) }
        val actions: Collection<Action> = weapon.actions
            .map {
                async {
                    actionMapper.mapToAction(it)
                }
            }
            .awaitAll()
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
        val rarity = async { rarityMapper.mapToRarity(weapon.rarity) }
        val damage: Collection<Damage> = weapon.damage
            .map {
                async {
                    damageMapper.mapToDamage(it)
                }
            }
            .awaitAll()
        DomainWeapon(
            commonData = commonItemData.await(),
            rarity = rarity.await(),
            weaponClass = weaponClass.await(),
            proficiency = proficiency.await(),
            type = weaponType.await(),
            damage = damage.toSet(),
            actions = actions.toSet(),
            handType = handType.await(),
            properties = properties.toSet()
        )
    }
}