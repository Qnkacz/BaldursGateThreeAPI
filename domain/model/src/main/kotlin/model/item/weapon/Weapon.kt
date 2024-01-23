package model.item.weapon

import model.damage.Damage
import model.item.CommonItemData
import model.item.ItemRarity

data class Weapon(
    val commonData: CommonItemData,
    val rarity: ItemRarity,
    val weaponClass: WeaponClass,
    val proficiency: WeaponProficiency,
    val type: WeaponType,
    val damage: Set<Damage>
)
