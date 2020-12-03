create table Users
(
    UserId       uuid primary key not null,
    UserUsername varchar          not null,
    UserEmail    varchar          not null,
    UserPassword varchar          not null
);