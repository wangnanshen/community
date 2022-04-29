package com.wang.nowcoderCom.community;

import com.wang.nowcoderCom.community.dao.DiscussPostMapper;
import com.wang.nowcoderCom.community.dao.LoginTicketMapper;
import com.wang.nowcoderCom.community.dao.MessageMapper;
import com.wang.nowcoderCom.community.dao.UserMapper;
import com.wang.nowcoderCom.community.entity.DiscussPost;
import com.wang.nowcoderCom.community.entity.LoginTicket;
import com.wang.nowcoderCom.community.entity.Message;
import com.wang.nowcoderCom.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("zhangfei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder103@sina.com");
        System.out.println(user);



    }

    @Test
    public void testInsertUser(){
        User newUser = new User();
        newUser.setUsername("WLJ");
        newUser.setPassword("WAP");
        newUser.setSalt("wwww");
        newUser.setHeaderUrl("http://www.nocoder.com/101.png");
        newUser.setEmail("WLJWAP@qq.com");
        newUser.setCreateTime(new Date());

        int insert = userMapper.insertUser(newUser);
        System.out.println(insert);
    }
    @Test
    public void testUpgrade(){

        int i = userMapper.updateHeader(150, "WLJ's Header");
        System.out.println(i);
        i = userMapper.updateStatus(150, 1);
        System.out.println(i);
        i = userMapper.updatePassWord(150, "WLJWAP");
        System.out.println(i);
    }
    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.seletDiscussPosts(149, 0, 10);
        for(DiscussPost post: list){
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setId(101);
        loginTicket.setStatus(0);
        loginTicket.setTicket("Test");

        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectAndUpdateLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("Test");
        System.out.println(loginTicket);
        loginTicketMapper.updateStatus("Test", 0);
        loginTicket = loginTicketMapper.selectByTicket("Test");
        System.out.println(loginTicket);
    }

    @Test
    public void testMessage(){
        List<Message> list = messageMapper.selectConversation(111,0,10);
        for(Message msg: list){
            System.out.println(msg.toString());
        }

        int cnt = messageMapper.selectConversationCount(111);
        System.out.println(cnt);

        list = messageMapper.selectLetters("111_112", 0, 10);
        for(Message msg: list){
            System.out.println(msg.toString());
        }

        cnt = messageMapper.selectLetterCount("111_112");
        System.out.println(cnt);

        cnt = messageMapper.selectLetterUnreadOnly(131, "111_131");
        System.out.println(cnt);
    }
}
