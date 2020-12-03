package com.muazhari.findcomputerws.services;

import com.muazhari.findcomputerws.contracts.SearchItemContract;
import com.muazhari.findcomputerws.dao.ItemDao;
import com.muazhari.findcomputerws.dao.UserDao;
import com.muazhari.findcomputerws.dao.UserItemLinkDao;
import com.muazhari.findcomputerws.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final UserItemLinkDao userItemLinkDao;
    private final UserDao userDao;
    private final ItemDao itemDao;

    @Autowired
    public ItemService(
            @Qualifier("UserItemLink") UserItemLinkDao userItemLinkDao,
            @Qualifier("User") UserDao userDao,
            @Qualifier("Item") ItemDao itemDao) {
        this.userItemLinkDao = userItemLinkDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    public Item create(Item item) {
        return itemDao.create(new Item(UUID.randomUUID(), item.getName(), item.getDescription(), item.getCategory(), item.getPrice()));
    }

    public List<Item> getAll() {
        return itemDao.getAll();
    }

    public Item getById(UUID itemId) {
        return itemDao.getById(itemId);
    }

    public Integer updateById(UUID itemId, Item itemToUpdate) {
        return itemDao.updateById(itemId, itemToUpdate);
    }

    public Integer deleteById(UUID itemId) {
        return itemDao.deleteById(itemId);
    }


    public List<SearchItemContract> getAllWithUsername() {
        return itemDao.getAllWithUsername();
    }
}
