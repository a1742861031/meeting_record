package com.bobo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description 测试
 * @Date 2022/1/12 11:38
 * @Created by bobo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetingRecordMain.class)
public class MainTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void test(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }
}
