-- liquibase formatted sql

--changeset Artyom:1
create table chats (
   id bigserial primary key,
   name varchar(50) null,
   is_group boolean not null default false,
   members_count integer not null default 2,
   created_at timestamp not null
);

create index idx_chats_is_group on chats (is_group);
create index idx_chats_created_at on chats (created_at desc);

-- rollback drop index if exists idx_chats_created_at;
-- rollback drop index if exists idx_chats_is_group;
-- rollback drop table if exists chats cascade;


--changeset Artyom:2
create table chat_members (
      id bigserial primary key,
      chat_id bigint not null,
      user_id bigint not null,
      joined_at timestamp not null,
      constraint uk_chat_members_chat_user unique (chat_id, user_id)
);
-- rollback drop table if exists chat_members cascade;

alter table chat_members add constraint fk_chat_members_chat
foreign key (chat_id) references chats(id) on delete cascade;
-- rollback alter table chat_members drop constraint if exists fk_chat_members_chat;


-- changeset Artyom:3
create index idx_chat_members_chat_id on chat_members (chat_id);
create index idx_chat_members_user_id on chat_members (user_id);

create index idx_chat_members_joined_at on chat_members (joined_at desc);

-- rollback drop index if exists idx_chat_members_joined_at;
-- rollback drop index if exists idx_chat_members_user_id;
-- rollback drop index if exists idx_chat_members_chat_id;

-- changeset Artyom:4
update chats set members_count = (
    select count(*) from chat_members where chat_members.chat_id = chats.id
);

update chats set is_group = (members_count > 2);

create or replace function update_chat_members_count_inc()
    returns trigger as $$
begin
    update chats
    set members_count = members_count + 1
    where id = new.chat_id and is_group = true;
    return new;
end $$ language plpgsql;

create trigger trg_chat_members_inc
    after insert on chat_members
    for each row
execute function update_chat_members_count_inc();

create or replace function update_chat_members_count_dec()
    returns trigger as $$
begin
    update chats
    set members_count = members_count - 1
    where id = old.chat_id and is_group = true;
    return old;
end $$ language plpgsql;

create trigger trg_chat_members_dec
    after delete on chat_members
    for each row
execute function update_chat_members_count_dec();

-- rollback drop trigger if exists trg_chat_members_dec on chat_members;
-- rollback drop trigger if exists trg_chat_members_inc on chat_members;
-- rollback drop function if exists update_chat_members_count_dec();
-- rollback drop function if exists update_chat_members_count_inc();