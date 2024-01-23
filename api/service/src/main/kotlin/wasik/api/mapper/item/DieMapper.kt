package wasik.api.mapper.item

import wasik.api.restResource.items.model.DieType
import model.DieType as DomainDIe

interface DieMapper {
    suspend fun mapDie(apiDie: DieType) : DomainDIe
}