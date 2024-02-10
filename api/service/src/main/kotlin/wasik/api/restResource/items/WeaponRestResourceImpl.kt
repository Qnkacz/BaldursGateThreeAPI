package wasik.api.restResource.items

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wasik.api.mapper.item.weapon.WeaponMapper
import wasik.api.restResource.items.model.weapon.Weapon
import wasik.items.WeaponService

@RestController
@RequestMapping("/api/weapon")
open class WeaponRestResourceImpl(private val weaponMapper: WeaponMapper, private val weaponService: WeaponService) : WeaponRestResource {
    @GetMapping("/{name}")
    override suspend fun getWeaponByName(@PathVariable name: String): ResponseEntity<Weapon> {
        return ResponseEntity.ok(null)
    }

    @PostMapping
    override suspend fun postWeapon(@RequestBody weapon: Weapon): ResponseEntity<Void> = coroutineScope {
        val weapon = async { weaponMapper.mapToWeapon(weapon) }
        weaponService.postWeapon(weapon.await())
        ResponseEntity.ok().build()
    }

}