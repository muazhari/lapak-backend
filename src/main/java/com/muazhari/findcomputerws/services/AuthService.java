package com.muazhari.findcomputerws.services;


import com.muazhari.findcomputerws.contracts.LoginContract;
import com.muazhari.findcomputerws.contracts.RegisterContract;
import com.muazhari.findcomputerws.dao.ItemDao;
import com.muazhari.findcomputerws.dao.UserDao;
import com.muazhari.findcomputerws.dao.UserItemLinkDao;
import com.muazhari.findcomputerws.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final UserItemLinkDao userItemLinkDao;
    private final UserDao userDao;
    private final ItemDao itemDao;

    @Autowired
    public AuthService(
            @Qualifier("UserItemLink") UserItemLinkDao userItemLinkDao,
            @Qualifier("User") UserDao userDao,
            @Qualifier("Item") ItemDao itemDao) {
        this.userItemLinkDao = userItemLinkDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    public ResponseEntity<User> login(LoginContract loginContract) {
        User findUser = null;
        HttpStatus status = null;

        try {
            findUser = userDao.getByUsernameAndPassword(
                    loginContract.getUsername(),
                    loginContract.getPassword());
            status = HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            findUser = new User();
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<User>(findUser, status);
    }

    public ResponseEntity<User> register(RegisterContract registerContract) {
        User findUser = null;
        HttpStatus status = null;

        try {
            findUser = userDao.getByUsernameAndPassword(
                    registerContract.getUsername(),
                    registerContract.getPassword());
            status = HttpStatus.OK;
        } catch (EmptyResultDataAccessException e) {
            findUser = new User(UUID.randomUUID(), registerContract.getUsername(), registerContract.getEmail(), registerContract.getPassword());
            userDao.create(findUser);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<User>(findUser, status);
    }
}
