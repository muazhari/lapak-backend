package com.muazhari.findcomputerws.dao;

import com.muazhari.findcomputerws.models.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    User create(User user);

    User getById(UUID userId);

    List<User> getAll();

    Integer updateById(UUID userId, User userToUpdate);

    Integer deleteById(UUID userId);

    Integer updateByUsername(String username, User userToUpdate);

    Integer updateByUsernameAndPassword(String username, User userToUpdate);

    //========================================================
    User getByUsernameAndPassword(String username, String password);

    User getByUsername(String username);
}
