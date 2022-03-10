package com.wang.nowcoderCom.community.service;

import com.wang.nowcoderCom.community.dao.UserMapper;
import com.wang.nowcoderCom.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public User findUserById(int id){

        return userMapper.selectById(id);
    }
}
