package com.muazhari.findcomputerws.dao;

import com.muazhari.findcomputerws.contracts.ItemContract;
import com.muazhari.findcomputerws.contracts.SearchItemContract;
import com.muazhari.findcomputerws.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("Item")
public class ItemDataService implements ItemDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item create(Item item) {
        final String sql = "insert into Items(ItemId, ItemName, ItemDescription, ItemCategory, ItemPrice) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getPrice());
        return new Item(item.getId(), item.getName(), item.getDescription(), item.getCategory(), item.getPrice());
    }

    @Override
    public Item getById(UUID itemId) {
        final String sql = "select " +
                "ItemId, " +
                "ItemName, " +
                "ItemDescription, " +
                "ItemCategory, " +
                "ItemPrice " +
                "from " +
                "Items " +
                "where " +
                "ItemId=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{itemId}, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("ItemId"));
            String name = resultSet.getString("ItemName");
            String description = resultSet.getString("ItemDescription");
            String category = resultSet.getString("ItemCategory");
            String price = resultSet.getString("ItemPrice");
            return new Item(id, name, description, category, price);
        }));
    }

    @Override
    public List<Item> getAll() {
        final String sql = "select " +
                "ItemId, " +
                "ItemName, " +
                "ItemDescription, " +
                "ItemCategory, " +
                "ItemPrice " +
                "from " +
                "Items";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("ItemId"));
            String name = resultSet.getString("ItemName");
            String description = resultSet.getString("ItemDescription");
            String category = resultSet.getString("ItemCategory");
            String price = resultSet.getString("ItemPrice");
            return new Item(id, name, description, category, price);
        }));
    }

    @Override
    public Integer updateById(UUID itemId, Item itemToUpdate) {
        final String sql = "update Items set ItemDescription=?, ItemName=?, ItemCategory=?, ItemPrice=? where ItemId=?";
        return jdbcTemplate.update(sql, itemToUpdate.getDescription(), itemToUpdate.getName(), itemToUpdate.getCategory(), itemToUpdate.getPrice(), itemId);
    }

    @Override
    public Integer deleteById(UUID itemId) {
        final String sql = "delete from Items where ItemId=?";
        return jdbcTemplate.update(sql, itemId);
    }

    @Override
    public List<SearchItemContract> getAllWithUsername() {
        final String sql = "select " +
                "Users.UserUsername, " +
                "Items.ItemId, " +
                "Items.ItemName, " +
                "Items.ItemDescription, " +
                "Items.ItemCategory, " +
                "Items.ItemPrice " +
                "from " +
                "UserItemLinks " +
                "inner join Users on UserItemLinks.UserItemLinkUserId=Users.UserId " +
                "inner join Items on UserItemLinks.UserItemLinkItemId=Items.ItemId";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            String username = resultSet.getString("UserUsername");
            String name = resultSet.getString("ItemName");
            String description = resultSet.getString("ItemDescription");
            String category = resultSet.getString("ItemCategory");
            String price = resultSet.getString("ItemPrice");
            return new SearchItemContract(username, name, description, category, price);
        }));
    }
}
