-- liquibase formatted sql

-- changeset Artyom:1
create table messages (
    id bigserial not null primary key,
    chat_id bigint not null,
    sender_id bigint not null,
    content text not null,
    created_at timestamp without time zone not null
);

create index idx_messages_chat_id on messages (chat_id);
create index idx_messages_sender_id on messages (sender_id);
create index idx_messages_chat_id_created_at on messages (chat_id, created_at desc);
create index idx_messages_created_at on messages (created_at);

-- rollback drop index if exists idx_messages_created_at;
-- rollback drop index if exists idx_messages_chat_id_created_at;
-- rollback drop index if exists idx_messages_sender_id;
-- rollback drop index if exists idx_messages_chat_id;
-- rollback drop table if exists messages cascade;