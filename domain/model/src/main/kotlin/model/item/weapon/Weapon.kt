package model.item.weapon

import model.damage.Damage
import model.item.CommonItemData
import model.item.ItemRarity
import model.item.Property

data class Weapon(
    val commonData: CommonItemData,
    val rarity: ItemRarity,
    val weaponClass: WeaponClass,
    val proficiency: WeaponProficiency,
    val handType: HandType,
    val properties: Set<Property>,
    val actions: Set<Action>,
    val type: WeaponType,
    val damage: Set<Damage>
)
