CREATE SCHEMA IF NOT EXISTS public;

CREATE DATABASE plant_store;

create table role
(
    id    uuid,
    title varchar(10),
    primary key (id)
);

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

create table manufacturer
(
    id    uuid,
    title varchar(20) unique,
    primary key (id)
);

create table product
(
    id              uuid,
    title           varchar(20) unique ,
    price           decimal,
    manufacturer_id uuid,
    primary key (id),
    foreign key (manufacturer_id)
        references manufacturer (id)
);

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

insert into role (title) values ('ROLE_ADMIN');
insert into role (title) values ('ROLE_USER');

insert into users (email, password, first_name, last_name, role_id)
values ('admin@mail.com', '$2y$12$MQsWjw7Peb4G9NLaryR9wujZ9OXEC5AYr7uZb8E7TNcC3sK9peDru', 'Admin', 'Admin',
        (select id from role where title = 'ROLE_ADMIN'));
insert into users (email, password, first_name, last_name, role_id)
values ('aushev@mail.com', '$2a$12$1qQpvqztm4/cIYQlhH.eJuPsgxredHKY.bDRlO8r31qGTK5tUnnrq', 'Dima', 'Aushev',
        (select id from role where title = 'ROLE_USER'));

insert into manufacturer (title) values ('Bromeliaceae');
insert into manufacturer (title) values ('Marantaceae');
insert into manufacturer (title) values ('Araceae');

insert into product (title, price, manufacturer_id)
values ('Stromanthe triostar', 15, (select id from manufacturer where title = 'Marantaceae'));
insert into product (title, price, manufacturer_id)
values ('Maranta tricolor', 10, (select id from manufacturer where title = 'Marantaceae'));
insert into product (title, price, manufacturer_id)
values ('Philodendron micans', 20, (select id from manufacturer where title = 'Araceae'));
insert into product (title, price, manufacturer_id)
values ('Monstera adansonii', 14.95, (select id from manufacturer where title = 'Araceae'));