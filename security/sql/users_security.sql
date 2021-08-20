drop database security_example;

create database security_example;

use security_example;

create table users
(
    id       int                not null primary key auto_increment,
    email    varchar(50) unique not null,
    password varchar(255)       not null,
    name     varchar(255)       not null,
    last_name  varchar(255)       not null,
    active   tinyint            not null default 0
);

create table roles
(
    id   tinyint     not null primary key auto_increment,
    role varchar(50) not null unique
);

create table users_roles
(
    user_id int     not null,
    role_id tinyint not null,
    constraint user_id_foreign_key foreign key (user_id) references users (id),
    constraint role_id_foreign_key foreign key (role_id) references roles (id)

);