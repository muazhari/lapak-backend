create table UserItemLinks
(
    UserItemLinkId     uuid not null primary key,
    UserItemLinkUserId uuid not null,
    UserItemLinkItemId uuid not null,

    constraint FK_UserItemLinks_Users_UserItemLinkUserId
        FOREIGN KEY (UserItemLinkUserId) REFERENCES Users(UserId)
        on update cascade
        on delete cascade,
    constraint FK_UserItemLinks_Items_UserItemLinkItemId
        FOREIGN KEY (UserItemLinkItemId) REFERENCES Items(ItemId)
            on update cascade
            on delete cascade
);