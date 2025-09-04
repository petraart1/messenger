-- liquibase formatted sql

--changeset Artyom:1
create table users (
    id bigserial primary key,
    username varchar(50) not null unique,
    email varchar(100) not null unique,
    password varchar(100) not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    is_active boolean not null default true
);

--changeset Artyom:2
create index idx_users_username on users(username);
create index idx_users_email on users(email);

--rollback drop table users