package wasik.api.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.restResource.items.model.weapon.WeaponClass

@Component
class ClassMapperImpl : ClassMapper {
    override suspend fun mapToClass(weaponClass: WeaponClass): model.item.weapon.WeaponClass {
        TODO("Not yet implemented")
    }
}