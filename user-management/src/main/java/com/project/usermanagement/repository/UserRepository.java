package com.project.usermanagement.repository;


import com.project.usermanagement.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Integer createUser(User user) {
        String sql = "insert into users (name, email, password, last_login) values (?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getLastLogin());
            if (rowsAffected > 0) {
                return rowsAffected;
            }
            return -1;
        } catch (Exception e) {
            System.out.println("Exception occurred while creating user details for user : " + user.toString() + " " + e);
            return -1;
        }
    }

    public Integer updateUser(User user) {
        String sql = "update users set name=?, email=?, password=?, last_login=? where id=?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getLastLogin(), user.getId());
            if (rowsAffected > 0) {
                return rowsAffected;
            }
            return -1;
        } catch (Exception e) {
            System.out.println("Exception occurred while updating user details for id : " + user.getId() + " " + e);
            return -1;
        }
    }

    public Integer deleteUser(int id) {
        try {
            String sql = "delete from users where id=?";
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected > 0) {
                return id;
            }
            return -1;
        } catch (Exception e) {
            System.out.println("Exception occurred while deleting user for id : " + id + e);
            return -1;
        }
    }

    public List<User> listUsers() {
        String sql = "select * from users";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .lastLogin(rs.getTimestamp("last_login"))
                    .build());
        } catch (Exception e) {
            System.out.println("Exception occurred while getting list of users" + e);
            return Collections.emptyList();
        }
    }

    public User login(String email, String password) {
        String sql = "select * from users where email=? and password=?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .lastLogin(rs.getTimestamp("last_login"))
                    .build(), email, password);
        } catch (Exception e) {
            System.out.println("Exception occurred while getting login details for user" + e);
            return null;
        }
    }
}
