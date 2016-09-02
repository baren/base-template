package com.enwu.mb.repository.jdbc;

import com.enwu.mb.common.cache.annoation.CacheData;
import com.enwu.mb.model.User;
import com.enwu.mb.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.jws.soap.SOAPBinding;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by user on 16/7/6.
 */
@Repository
public class UserRepositoryImpl implements IUserRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUser() {

        final List<User> users = this.jdbcTemplate.query(
                "SELECT id, telephone FROM owners",
                BeanPropertyRowMapper.newInstance(User.class));
        return users;
    }
}
