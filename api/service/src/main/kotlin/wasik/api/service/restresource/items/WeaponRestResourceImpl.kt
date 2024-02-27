package wasik.api.service.restresource.items

import jakarta.validation.Valid
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import wasik.api.model.model.weapon.Weapon
import wasik.api.service.mapper.item.weapon.WeaponMapper
import wasik.domain.logic.items.WeaponService

@RestController
@RequestMapping("/api/weapon")
open class WeaponRestResourceImpl(private val weaponMapper: WeaponMapper, private val weaponService: WeaponService) :
    WeaponRestResource {
    @GetMapping("/{name}")
    override suspend fun getWeaponByName(@PathVariable name: String): ResponseEntity<Weapon> {
        return ResponseEntity.ok(null)
    }

    @PostMapping
    override suspend fun postWeapon(@RequestBody @Valid weapon: Weapon): ResponseEntity<Void> = coroutineScope {
        val domainWeapon = async { weaponMapper.mapToWeapon(weapon) }
        weaponService.postWeapon(domainWeapon.await())
        ResponseEntity.ok().build()
    }

}