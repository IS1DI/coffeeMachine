create table if not exists order_entity
(
    coffee_status_order integer,
    duration            numeric(21),
    close_timestamp     timestamp(6),
    creation_timestamp  timestamp(6),
    start_timestamp     timestamp(6),
    id                  uuid not null
        primary key,
    name                varchar(255),
    status              varchar(255)
);

alter table order_entity
    owner to postgres;