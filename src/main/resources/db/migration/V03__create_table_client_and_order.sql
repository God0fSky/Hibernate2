create table if not exists store.client
(
    id              integer not null default nextval('store.store_id_sequence'),
    name            text not null,
    email           text not null,
    phone           text not null,
    address         text not null,
    orders          text not null,
    primary key(id)
);

create table if not exists store.order
(
    id              integer not null default nextval('store.store_id_sequence'),
    client_id       integer constraint data_source_fk_connection_id_fkey references store.client,
    primary key(id)
);