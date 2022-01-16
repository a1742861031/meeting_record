package com.bobo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.mapper.RecordMapper;
import com.bobo.vo.RecordListVo;
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
    @Autowired
    private RecordMapper recordMapper;
    @Test
    public void test(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }
    @Test
    public void test1(){
        Page<RecordListVo> page = new Page<>(1, 5);
        recordMapper.getRecordList(page);

        System.out.println(page );
    }
}
