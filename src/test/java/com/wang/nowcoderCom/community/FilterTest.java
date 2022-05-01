package com.wang.nowcoderCom.community;

import com.wang.nowcoderCom.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.security.Signer;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class FilterTest {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text = "赌博吸※毒，我要赌博，我要吸毒，我要赌博，我要成为三好学生赌博";
        System.out.println(sensitiveFilter.filter(text));
    }
}
