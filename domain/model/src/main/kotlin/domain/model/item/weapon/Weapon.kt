package domain.model.item.weapon

import domain.model.damage.Damage
import domain.model.item.CommonItemData
import domain.model.misc.Action
import domain.model.misc.Property

data class Weapon(
    val commonData: CommonItemData,
    val weaponClass: WeaponClass,
    val proficiency: WeaponProficiency,
    val handType: HandType,
    val properties: Set<Property>,
    val actions: Set<Action>,
    val type: WeaponType,
    val damage: Set<Damage>
)
