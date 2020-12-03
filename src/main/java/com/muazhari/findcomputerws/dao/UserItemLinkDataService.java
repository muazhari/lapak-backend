package com.muazhari.findcomputerws.dao;

import com.muazhari.findcomputerws.models.Item;
import com.muazhari.findcomputerws.models.UserItemLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("UserItemLink")
public class UserItemLinkDataService implements UserItemLinkDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserItemLinkDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserItemLink create(UserItemLink userItemLink) {
        final String sql = "insert into UserItemLinks(UserItemLinkId, UserItemLinkUserId, UserItemLinkItemId) values(?, ?, ?)";
        jdbcTemplate.update(sql,
                userItemLink.getId(),
                userItemLink.getUserId(),
                userItemLink.getItemId());
        return new UserItemLink(userItemLink.getItemId(), userItemLink.getUserId(), userItemLink.getItemId());
    }


    @Override
    public UserItemLink getById(UUID userItemLinkId) {
        final String sql = "select " +
                "UserItemLinkId, " +
                "UserItemLinkUserId, " +
                "UserItemLinkItemId " +
                "from " +
                "UserItemLinks " +
                "where " +
                "UserItemLinkId=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userItemLinkId}, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("UserItemLinkId"));
            UUID userId = UUID.fromString(resultSet.getString("UserItemLinkUserId"));
            UUID itemId = UUID.fromString(resultSet.getString("UserItemLinkItemId"));
            return new UserItemLink(id, userId, itemId);
        }));
    }

    @Override
    public List<UserItemLink> getAll() {
        final String sql = "select " +
                "UserItemLinkId, " +
                "UserItemLinkUserId, " +
                "UserItemLinkItemId " +
                "from " +
                "UserItemLinks";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("UserItemLinkId"));
            UUID userId = UUID.fromString(resultSet.getString("UserItemLinkUserId"));
            UUID itemId = UUID.fromString(resultSet.getString("UserItemLinkItemId"));
            return new UserItemLink(id, userId, itemId);
        }));
    }

    @Override
    public Integer updateById(UUID userItemLinkId, UserItemLink userItemLinkToUpdate) {
        final String sql = "update UserItemLinks set UserItemLinkUserId=?, UserItemLinkItemId=? where UserItemLinkId=?";
        return jdbcTemplate.update(sql, userItemLinkToUpdate.getUserId(), userItemLinkToUpdate.getItemId(), userItemLinkId);
    }

    @Override
    public Integer deleteById(UUID userItemLinkId) {
        final String sql = "delete from UserItemLinks where UserItemLinkId=?";
        return jdbcTemplate.update(sql, userItemLinkId);
    }

    // ===================================================
    @Override
    public List<Item> getAllItemByUsername(String username) {
        final String sql = "select " +
                "Items.ItemId, " +
                "Items.ItemName, " +
                "Items.ItemDescription, " +
                "Items.ItemCategory, " +
                "Items.ItemPrice " +
                "from " +
                "UserItemLinks " +
                "inner join Users on UserItemLinks.UserItemLinkUserId=Users.UserId " +
                "inner join Items on UserItemLinks.UserItemLinkItemId=Items.ItemId " +
                "where " +
                "Users.UserUsername=? ";
        return jdbcTemplate.query(sql, new Object[]{username}, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("ItemId"));
            String name = resultSet.getString("ItemName");
            String description = resultSet.getString("ItemDescription");
            String category = resultSet.getString("ItemCategory");
            String price = resultSet.getString("ItemPrice");
            return new Item(id, name, description, category, price);
        }));
    }

    @Override
    public UserItemLink getByUsernameAndItemId(String username, UUID itemIdToFind) {
        final String sql = "select " +
                "UserItemLinks.UserItemLinkId, " +
                "UserItemLinks.UserItemLinkUserId, " +
                "UserItemLinks.UserItemLinkItemId " +
                "from " +
                "UserItemLinks " +
                "inner join Users on UserItemLinks.UserItemLinkUserId=Users.UserId " +
                "inner join Items on UserItemLinks.UserItemLinkItemId=Items.ItemId " +
                "where " +
                "Users.UserUsername=? and Items.ItemId=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, itemIdToFind}, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("UserItemLinkId"));
            UUID userId = UUID.fromString(resultSet.getString("UserItemLinkUserId"));
            UUID itemId = UUID.fromString(resultSet.getString("UserItemLinkItemId"));
            return new UserItemLink(id, userId, itemId);
        }));
    }
}