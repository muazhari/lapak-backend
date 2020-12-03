create table Items
(
    ItemId          uuid primary key not null,
    ItemName        varchar          not null,
    ItemDescription varchar          not null,
    ItemCategory    varchar          not null,
    ItemPrice       varchar          not null
);