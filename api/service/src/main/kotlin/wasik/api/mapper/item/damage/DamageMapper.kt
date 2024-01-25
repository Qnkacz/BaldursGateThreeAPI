package wasik.api.mapper.item.damage

import wasik.api.restResource.items.model.damage.Damage

interface DamageMapper {
    suspend fun mapToDamage(damage: Damage) : model.damage.Damage
}