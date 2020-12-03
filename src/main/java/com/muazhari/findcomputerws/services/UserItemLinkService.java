package com.muazhari.findcomputerws.services;

import com.muazhari.findcomputerws.contracts.ItemContract;
import com.muazhari.findcomputerws.dao.ItemDao;
import com.muazhari.findcomputerws.dao.UserDao;
import com.muazhari.findcomputerws.dao.UserItemLinkDao;
import com.muazhari.findcomputerws.models.Item;
import com.muazhari.findcomputerws.models.User;
import com.muazhari.findcomputerws.models.UserItemLink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserItemLinkService {

    private final UserItemLinkDao userItemLinkDao;
    private final UserDao userDao;
    private final ItemDao itemDao;


    public UserItemLinkService(UserItemLinkDao userItemLinkDao, UserDao userDao, ItemDao itemDao) {
        this.userItemLinkDao = userItemLinkDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    public UserItemLink create(UserItemLink userItemLink) {
        return userItemLinkDao.create(new UserItemLink(UUID.randomUUID(), userItemLink.getUserId(), userItemLink.getItemId()));
    }

    public List<UserItemLink> getAll() {
        return userItemLinkDao.getAll();
    }

    public List<Item> getAllItemByUsername(String username) {
        return userItemLinkDao.getAllItemByUsername(username);
    }

    public Item getByUsernameAndItemId(String username, UUID itemId) {
        UserItemLink findUserItem = userItemLinkDao.getByUsernameAndItemId(username, itemId);
        return itemDao.getById(findUserItem.getItemId());
    }

    public Item createItem(String username, ItemContract itemContract) {
        User findUser = userDao.getByUsername(username);
        Item newItem = new Item(UUID.randomUUID(), itemContract.getName(), itemContract.getDescription(), itemContract.getCategory(), itemContract.getPrice());
        Item createdItem = itemDao.create(newItem);
        UserItemLink newUserItemLink = new UserItemLink(UUID.randomUUID(), findUser.getId(), createdItem.getId());
        UserItemLink createdUserItemLink = userItemLinkDao.create(newUserItemLink);
        return createdItem;
    }

    public Integer updateItem(String username, UUID itemId, ItemContract itemContract) {
        UserItemLink findUserItem = userItemLinkDao.getByUsernameAndItemId(username, itemId);
        Item item = new Item(findUserItem.getItemId(), itemContract.getName(), itemContract.getDescription(), itemContract.getCategory(), itemContract.getPrice());
        return itemDao.updateById(item.getId(), item);
    }

    public Integer deleteItem(String username, UUID itemId) {
        UserItemLink findUserItem = userItemLinkDao.getByUsernameAndItemId(username, itemId);
        return userItemLinkDao.deleteById(findUserItem.getId());
    }
}
