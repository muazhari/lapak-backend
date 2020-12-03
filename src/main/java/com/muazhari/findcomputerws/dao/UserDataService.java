package com.muazhari.findcomputerws.dao;

import com.muazhari.findcomputerws.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("User")
public class UserDataService implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        final String sql = "insert into Users(UserId, UserUsername, UserEmail, UserPassword) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
        return new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Override
    public User getById(UUID userId) {
        final String sql = "select " +
                "UserId, " +
                "UserUsername, " +
                "UserEmail, " +
                "UserPassword " +
                "from " +
                "Users " +
                "where " +
                "UserId=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("UserId"));
            String username = resultSet.getString("UserUsername");
            String email = resultSet.getString("UserEmail");
            String password = resultSet.getString("UserPassword");
            return new User(id, username, email, password);
        }));
    }


    @Override
    public List<User> getAll() {
        final String sql = "select " +
                "UserId, " +
                "UserUsername, " +
                "UserEmail, " +
                "UserPassword " +
                "from " +
                "Users";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("UserId"));
            String username = resultSet.getString("UserUsername");
            String email = resultSet.getString("UserEmail");
            String password = resultSet.getString("UserPassword");
            return new User(id, username, email, password);
        }));
    }

    @Override
    public Integer updateById(UUID userId, User userToUpdate) {
        final String sql = "update Users set UserUsername=?, UserEmail=?, UserPassword=? where UserId=?";
        return jdbcTemplate.update(sql, userToUpdate.getUsername(), userToUpdate.getEmail(), userToUpdate.getPassword(), userId);
    }

    @Override
    public Integer deleteById(UUID userId) {
        final String sql = "delete from Users where UserId=?";
        return jdbcTemplate.update(sql, userId);
    }

    //========================================================

    @Override
    public Integer updateByUsername(String username, User userToUpdate) {
        final String sql = "update Users set UserUsername=?, UserEmail=?, UserPassword=? where UserUsername=?";
        return jdbcTemplate.update(sql, userToUpdate.getUsername(), userToUpdate.getEmail(), userToUpdate.getPassword(), username);
    }

    @Override
    public Integer updateByUsernameAndPassword(String username, User userToUpdate) {
        final String sql = "update Users set UserUsername=?, UserEmail=?, UserPassword=? where UserUsername=? and UserPassword=?";
        return jdbcTemplate.update(sql, userToUpdate.getUsername(), userToUpdate.getEmail(), userToUpdate.getPassword(), username, userToUpdate.getPassword());
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        final String sql = "select * from Users where UserUsername=? and UserPassword=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, (result, i) -> {
            UUID UserId = UUID.fromString(result.getString("UserId"));
            String UserUsername = result.getString("UserUsername");
            String UserEmail = result.getString("UserEmail");
            String UserPassword = result.getString("UserPassword");
            return new User(UserId, UserUsername, UserEmail, UserPassword);
        });
    }

    @Override
    public User getByUsername(String username) {
        final String sql = "select * from Users where UserUsername=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (result, i) -> {
            UUID UserId = UUID.fromString(result.getString("UserId"));
            String UserUsername = result.getString("UserUsername");
            String UserEmail = result.getString("UserEmail");
            String UserPassword = result.getString("UserPassword");
            return new User(UserId, UserUsername, UserEmail, UserPassword);
        });
    }

}
