package com.xiaojihua;

import com.xiaojihua.mapper.UserMapper;
import com.xiaojihua.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MybatisProxyTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
    }

    @Test
    public void testMybatisProxy() throws Exception {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用代理对象
        User user = mapper.findUserById(1);
        System.out.println(user);
        // 关闭session
        sqlSession.close();
    }

    @Test
    public void testFindUserByUsername() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.findUserByUsername("张");
        System.out.println(users.size());
    }

    @Test
    public void testInsertUser() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("李三");
        user.setBirthday(new SimpleDateFormat("yyyyMMdd").parse("19801211"));
        user.setSex("1");
        user.setAddress("济南市");
        // 通过mapper接口添加用户
        mapper.insertUser(user);
        // 提交
        session.commit();
        // 关闭session
        session.close();
    }
}
