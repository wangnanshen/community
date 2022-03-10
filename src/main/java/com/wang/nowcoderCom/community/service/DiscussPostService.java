package com.wang.nowcoderCom.community.service;

import com.wang.nowcoderCom.community.dao.DiscussPostMapper;
import com.wang.nowcoderCom.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit){
        return discussPostMapper.seletDiscussPosts(userId, offset, limit);
    }

    public int findDiscussPostRows(int userId){

        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
