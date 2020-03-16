package com.dx340.fileinterconnectionweb.service;

import com.dx340.fileinterconnectionweb.bean.LoginInfo;
import com.dx340.fileinterconnectionweb.bean.User;
import com.dx340.fileinterconnectionweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    UserMapper userMapper;

    public boolean checkPassword(LoginInfo loginInfo){
        String username = loginInfo.getUsername();
        User user = userMapper.getUserByUsername(username);
        if(user == null){
            return false;
        }
        return user.getPassword().equals(loginInfo.getPassword());
    }

    public boolean addUser (User user){
        userMapper.addUser(user);
        return true;
    }

    public boolean delUser (User user){
        userMapper.delUser(user);
        return true;
    }

    public boolean updateUser (User user){
        userMapper.updateUser(user);
        return true;
    }

}
