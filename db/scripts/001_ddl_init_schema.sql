create table role (
    id serial primary key,
    name varchar(70) not null  unique
);

create table person (
    id serial primary key not null,
    name varchar(255) not null,
    email varchar(255) unique not null,
    password varchar(2000) not null
);

create table person_role (
    person_id integer not null references person(id),
    role_id integer not null references role(id)
);

create table room (
    id serial primary key,
    name varchar(255)
);

create table message (
    id serial pramary key,
    person_id integer not null references person(id),
    room_id integer not null references room(id),
    text text,
    created  timestamp without time zone not null default now()
)