package com.enwu.mb.service.jdbc;

import com.enwu.mb.common.cache.annoation.CacheData;
import com.enwu.mb.model.User;
import com.enwu.mb.repository.IUserRepository;
import com.enwu.mb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 16/7/6.
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.getAllUser();
    }

    @Override
    @CacheData(cacheName = "home", key="#id + '#' + #user.id", condition = "#id != 0", unless = "#result.id != 1")
    @Transactional(readOnly = true)
    public User getUser(int id, String name, User user) {
        List<User> users = userDao.getAllUser();

        User ret = null;
        for(User u : users) {
            ret  = u;
        }
        return ret;
    }
}
