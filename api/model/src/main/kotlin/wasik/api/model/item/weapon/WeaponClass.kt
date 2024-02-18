package wasik.api.model.model.weapon

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Weapon class",
    required = true,
    example = "BATTLEAXE"
)
enum class WeaponClass {
    BATTLEAXE,
    CLUB,
    DAGGER,
    DART,
    FLAIL,
    GLAIVE,
    GREATAXE,
    GREATCLUB,
    GREATSWORD,
    HALBERD,
    HANDAXE,
    HAND_CROSSBOW,
    HEAVY_CROSSBOW,
    JAVELIN,
    LIGHT_CROSSBOW,
    LIGHT_HAMMER,
    LONGBOW,
    LONGSWORD,
    MACE,
    MAUL,
    MORNINGSTAR,
    PIKE,
    QUARTERSTAVE,
    RAPIER,
    SCIMITAR,
    SHORTBOW,
    SHORTSWORD,
    SICKLE,
    SPEAR,
    TRIDENT,
    WAR_PICK,
    WARHAMMER
}