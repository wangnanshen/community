package com.wang.nowcoderCom.community.util;

import com.wang.nowcoderCom.community.entity.LoginTicket;
import com.wang.nowcoderCom.community.entity.User;
import com.wang.nowcoderCom.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    // 用来存User的（代替session）
    private ThreadLocal<User> users = new ThreadLocal<>();


    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }

}
