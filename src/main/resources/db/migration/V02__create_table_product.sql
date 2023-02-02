create table if not exists store.product
(
    id              integer not null default nextval('store.store_id_sequence'),
    name            text not null,
    description     text not null,
    price           numeric not null,
    primary key(id)
);