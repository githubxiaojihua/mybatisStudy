package com.xiaojihua;

import com.xiaojihua.mapper.UserMapper;
import com.xiaojihua.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisSpringProxyDaoTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void test1() throws Exception {
        User user = mapper.findUserById(31);
        System.out.println(user);
    }
}
