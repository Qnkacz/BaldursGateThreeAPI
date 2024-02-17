package wasik.api.mapper.item

import wasik.api.restResource.items.model.DieType
import model.misc.DieType as DomainDIe

interface DieMapper {
    suspend fun mapDie(apiDie: DieType): DomainDIe
}