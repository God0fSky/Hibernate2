create table if not exists store.address
(
    id              integer not null default nextval('store.store_id_sequence'),
    client_id       integer constraint fk_address_id_fkey references store.client,
    country         text not null,
    city            text not null,
    street          text not null,
    house           text not null,
    primary key(id)
);

create table if not exists store.order_item
(
    id              integer not null default nextval('store.store_id_sequence'),
    fk_order_id     integer constraint fk1_order_item_id_fkey references store.order,
    fk_product_id   integer constraint fk2_order_item_id_fkey references store.product,
    count           integer not null,
    primary key(id)
);