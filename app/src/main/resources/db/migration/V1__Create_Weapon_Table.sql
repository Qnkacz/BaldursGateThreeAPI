create table if not exists weapons
(
    id          serial unique primary key,
    name        varchar(254) NOT NULL,
    rarity      integer      not null,
    value       real         not null,
    weight      real         not null,
    description varchar(254),
    class       integer      not null,
    proficiency integer      not null,
    hand_type   integer      not null,
    type        integer      not null,
    upgrade     integer
);
-- DAMAGE AND CROSS TABLE
create table if not exists damage
(
    id     serial unique primary key,
    type   integer not null,
    amount varchar(10)
);

create table if not exists weapon_damage
(
    weapon_id int references weapons (id) on delete cascade,
    damage_id int references damage (id) on delete cascade,
    PRIMARY KEY (weapon_id, damage_id)
);
-- ACTION AND CROSS TABLE
create table if not exists actions
(
    id          serial unique primary key,
    name        varchar(64)   not null,
    description varchar(2544) not null
);

create table if not exists weapon_actions
(
    weapon_id  int references weapons (id) on delete cascade,
    actions_id int references actions (id) on delete cascade,
    PRIMARY KEY (weapon_id, actions_id)
);

-- PROPERTY AND CROSS TABLE

create table if not exists property
(
    id          serial unique primary key,
    name        varchar(64)   not null,
    description varchar(2544) not null
);

create table if not exists weapon_properties
(
    weapon_id  int references weapons (id) on delete cascade,
    property_id int references property (id) on delete cascade,
    PRIMARY KEY (weapon_id, property_id)
);