package wasik.api.service.mapper.item.damage

import wasik.api.model.model.damage.Damage

interface DamageMapper {
    suspend fun mapToDamage(damage: Damage) : domain.model.damage.Damage
}