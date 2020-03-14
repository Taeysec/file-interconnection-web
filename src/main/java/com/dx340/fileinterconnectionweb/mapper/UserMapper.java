package com.dx340.fileinterconnectionweb.mapper;

import com.dx340.fileinterconnectionweb.bean.User;

public interface UserMapper {
    User getUserByUsername(String username);
    void addUser(User user);
    void delUser(User user);
    void updateUser(User user);
}
