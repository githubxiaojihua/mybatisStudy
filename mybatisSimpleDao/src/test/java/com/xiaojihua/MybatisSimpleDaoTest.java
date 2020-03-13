package com.xiaojihua;

import com.xiaojihua.dao.UserDao;
import com.xiaojihua.dao.UserDaoImpl;
import com.xiaojihua.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisSimpleDaoTest {
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = sessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testGetUserById() throws Exception {
        UserDao dao = new UserDaoImpl(factory);
        User user = dao.getUserById(22);
        System.out.println(user);

    }
}
