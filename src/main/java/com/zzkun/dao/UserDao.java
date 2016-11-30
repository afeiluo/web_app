package com.zzkun.dao;

import com.zzkun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Repository用于标注数据访问组件，即DAO组件
 * Created by kun on 2016/5/5.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void register(User user) {
        String sql = "insert into user_info(username,password) values(?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());

    }

    public User findUserByUserName(String userName) {
        String sql = "select id, username ,age,password from user_info where username=?";
        final User user = new User();
        jdbcTemplate.query(sql, new Object[]{userName}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setAge(resultSet.getInt("age"));
                user.setPassword(resultSet.getString("password"));
            }
        });
        return user;
    }
}
