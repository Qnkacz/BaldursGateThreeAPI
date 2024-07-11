CREATE TABLE damage
(
    id     BIGSERIAL PRIMARY KEY,
    type   INTEGER NOT NULL,
    amount TEXT    NOT NULL
);

CREATE TABLE actions
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE properties
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE weapons
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT    NOT NULL,
    rarity      INTEGER NOT NULL,
    value       FLOAT   NOT NULL,
    weight      FLOAT   NOT NULL,
    description TEXT    NOT NULL,
    class       INTEGER NOT NULL,
    proficiency INTEGER NOT NULL,
    hand_type   INTEGER NOT NULL,
    type        INTEGER NOT NULL,
    upgrade     INTEGER NOT NULL,
    range       FLOAT   NOT NULL
);

CREATE TABLE weapon_properties
(
    weapon_id   BIGINT REFERENCES weapons (id),
    property_id BIGINT REFERENCES properties (id),
    PRIMARY KEY (weapon_id, property_id)
);

CREATE TABLE weapon_damage
(
    weapon_id BIGINT REFERENCES weapons (id),
    damage_id BIGINT REFERENCES damage (id),
    PRIMARY KEY (weapon_id, damage_id)
);

CREATE TABLE weapon_actions
(
    weapon_id  BIGINT REFERENCES weapons (id),
    actions_id BIGINT REFERENCES actions (id),
    PRIMARY KEY (weapon_id, actions_id)
);