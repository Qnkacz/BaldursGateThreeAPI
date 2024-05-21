package wasik.infrastructure.logic.mapper

import domain.model.damage.Damage
import domain.model.damage.DamageType
import domain.model.misc.DieType
import kotlinx.coroutines.coroutineScope
import org.jetbrains.exposed.sql.ResultRow
import org.springframework.stereotype.Component
import wasik.infrastructure.model.table.DamageTable

@Component
class DamageInfrastructureMapper {

    fun mapToDamage(it: ResultRow): Damage {
        val (damageAmount, dieType) = fromString(it[DamageTable.amount])
        val damageType = DamageType.entries.getOrElse(it[DamageTable.type]) { DamageType.BLUDGEONING }
        return Damage(dieAmount = damageAmount, dieType = dieType, damageType = damageType, bonus = 0)
    }

    suspend fun damageToString(dieType: DieType, dieAmount: Int) = coroutineScope {
        return@coroutineScope "$${dieAmount}$SEPARATOR$dieType"
    }

    private fun fromString(damageString: String): Pair<Int, DieType> {
        val parts = damageString.split(SEPARATOR)
        return Pair(parts[0].toInt(), DieType.valueOf(parts[1]))
    }

    companion object {
        private const val SEPARATOR = "-"
    }
}