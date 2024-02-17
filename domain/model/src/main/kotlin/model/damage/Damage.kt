package model.damage

import model.misc.DieType

data class Damage(
    val damageType: DamageType,
    val dieAmount: Int,
    val dieType: DieType,
    val bonus: Int
)
