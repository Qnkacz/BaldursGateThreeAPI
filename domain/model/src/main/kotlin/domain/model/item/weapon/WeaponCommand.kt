package domain.model.item.weapon

import domain.model.damage.Damage
import domain.model.item.CommonItemData
import domain.model.misc.Action
import domain.model.misc.Property

data class WeaponCommand (
    val commonData: CommonItemData,
    val weaponClass: WeaponClass,
    val proficiency: WeaponProficiency,
    val handType: HandType,
    val properties: Set<Property>,
    val actions: Set<Action>,
    val type: WeaponType,
    val damage: Set<Damage>,
    val range: Float
) {
    class Builder {
        private var commonData: CommonItemData? = null
        private var weaponClass: WeaponClass? = null
        private var proficiency: WeaponProficiency? = null
        private var handType: HandType? = null
        private var properties: MutableSet<Property> = mutableSetOf()
        private var actions: MutableSet<Action> = mutableSetOf()
        private var type: WeaponType? = null
        private var damage: MutableSet<Damage> = mutableSetOf()
        private var range: Float? = null

        fun commonData(commonData: CommonItemData) = apply { this.commonData = commonData }
        fun weaponClass(weaponClass: WeaponClass) = apply { this.weaponClass = weaponClass }
        fun proficiency(proficiency: WeaponProficiency) = apply { this.proficiency = proficiency }
        fun handType(handType: HandType) = apply { this.handType = handType }
        fun properties(properties: List<Property>) = apply { this.properties.addAll(properties) }
        fun actions(actions: List<Action>) = apply { this.actions.addAll(actions) }
        fun type(type: WeaponType) = apply { this.type = type }
        fun damage(damage: List<Damage>) = apply { this.damage.addAll(damage) }
        fun range(range: Float) = apply { this.range = range }

        fun build() = WeaponCommand(
            commonData = checkNotNull(commonData) { "commonData must be set" },
            weaponClass = checkNotNull(weaponClass) { "weaponClass must be set" },
            proficiency = checkNotNull(proficiency) { "proficiency must be set" },
            handType = checkNotNull(handType) { "handType must be set" },
            properties = properties.toSet(),
            actions = actions.toSet(),
            type = checkNotNull(type) { "type must be set" },
            damage = damage.toSet(),
            range = checkNotNull(range) { "range must be set" }
        )
    }
}
