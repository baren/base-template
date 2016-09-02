package com.enwu.mb.service;

import com.enwu.mb.model.User;

import java.util.List;

/**
 * Created by user on 16/7/6.
 */
public interface IUserService {

    List<User> getUsers();
    public User getUser(int id, String name, User u);
}
