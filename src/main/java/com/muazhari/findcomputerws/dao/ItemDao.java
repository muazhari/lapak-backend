package com.muazhari.findcomputerws.dao;

import com.muazhari.findcomputerws.contracts.ItemContract;
import com.muazhari.findcomputerws.contracts.SearchItemContract;
import com.muazhari.findcomputerws.models.Item;

import java.util.List;
import java.util.UUID;

public interface ItemDao {
    Item create(Item item);

    Item getById(UUID itemId);

    List<Item> getAll();

    Integer updateById(UUID itemId, Item itemToUpdate);

    Integer deleteById(UUID itemId);

    List<SearchItemContract> getAllWithUsername();
}
