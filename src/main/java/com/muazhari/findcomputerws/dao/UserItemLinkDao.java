package com.muazhari.findcomputerws.dao;

import com.muazhari.findcomputerws.models.Item;
import com.muazhari.findcomputerws.models.UserItemLink;

import java.util.List;
import java.util.UUID;

public interface UserItemLinkDao {
    UserItemLink create(UserItemLink userItemLink);

    UserItemLink getById(UUID userItemLinkId);

    List<UserItemLink> getAll();

    Integer updateById(UUID userItemLinkId, UserItemLink userItemLinkToUpdate);

    Integer deleteById(UUID userItemLinkId);

    List<Item> getAllItemByUsername(String username);

    UserItemLink getByUsernameAndItemId(String username, UUID itemId);
}
