package com.zzkun.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sharksharding.sql.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.zzkun.model.User;

/**
 * Repository用于标注数据访问组件，即DAO组件
 * Created by kun on 2016/5/5.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PropertyPlaceholderConfigurer propertyPlaceholderConfigurer;

    public void register(User user) {
        String sql = propertyPlaceholderConfigurer.getSql("insert", user.getId());
        // String sql = "insert into user_info(id,username,password) values(" + user.getId() + ",?, ?)";
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

    public List<User> findUserByPage(int page, int pageSize) {
        String sql = "select * from user_info limit ?,?";
        final List<User> retList = new ArrayList();
        jdbcTemplate.query(sql, new Object[]{(page - 1) * pageSize, pageSize}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setAge(resultSet.getInt("age"));
                user.setPassword(resultSet.getString("password"));
                retList.add(user);
            }
        });
        return retList;
    }
}
