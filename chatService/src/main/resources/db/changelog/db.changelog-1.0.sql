-- liquibase formatted sql

--changeset Artyom:1
create table chats (
   id bigserial primary key,
   name varchar(50) null,
   is_group boolean not null default false,
   created_at timestamp not null
);
--rollback drop table chats


--changeset Artyom:2
create table chat_members (
      id bigserial primary key,
      chat_id bigint not null,
      user_id bigint not null,
      joined_at timestamp not null,
      constraint uk_chat_members_chat_user unique (chat_id, user_id)
);
--rollback drop table chat_members

--changeset Artyom:3
alter table chat_members add constraint fk_chat_members_chat
foreign key (chat_id) references chats(id) on delete cascade;
-- rollback alter table chat_members drop constraint fk_chat_members_chat;

--changeset Artyom:4
create index idx_chat_members_chat_id on chat_members(chat_id);
create index idx_chat_members_user_id on chat_members(user_id);
-- rollback drop index idx_chat_members_user_id;
-- rollback drop index idx_chat_members_chat_id;