CREATE SCHEMA IF NOT EXISTS public;

CREATE DATABASE PLANT_STORE WITH OWNER postgres;
ALTER SCHEMA public OWNER TO postgres;

create table role
(
    id    uuid,
    title varchar(10),
    primary key (id)
);
alter table role
    owner to postgres;

create table users
(
    id         uuid,
    email      varchar(50) unique,
    password   varchar(20),
    first_name varchar(10),
    last_name  varchar(10),
    role_id    uuid,
    primary key (id),
    foreign key (role_id)
        references role (id)
);
alter table users
    owner to postgres;

create table manufacturer
(
    id    uuid,
    title varchar(20) unique,
    primary key (id)
);
alter table manufacturer
    owner to postgres;

create table product
(
    id              uuid,
    title           varchar(20),
    price           decimal,
    manufacturer_id uuid,
    primary key (id),
    foreign key (manufacturer_id)
        references manufacturer (id)
);
alter table product
    owner to postgres;

alter table users
    alter column password type varchar(100);

create extension "uuid-ossp";

alter table users
    alter column id set default uuid_generate_v4();

alter table manufacturer
    alter column id set default uuid_generate_v4();

alter table product
    alter column id set default uuid_generate_v4();

alter table role
    alter column id set default uuid_generate_v4();

alter table product
    alter column title type varchar(100);

alter table manufacturer
    alter column title type varchar(100);
