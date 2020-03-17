package com.xiaojihua;

import com.xiaojihua.mapper.UserMapper;
import com.xiaojihua.pojo.Orders;
import com.xiaojihua.pojo.OrdersCustomer;
import com.xiaojihua.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AssociationTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
    }

    /**
     * 一对一关系的方式一：
     * 新建了一个pojo里面包含了查询字段，然后返回这个pojo列表
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<OrdersCustomer> ordersList = mapper.findOrdersList();
        System.out.println(ordersList);
        session.close();
    }

    /**
     * 一对一关系的方式二：
     * 使用resultmap配置一对一的关联关系，在Orders中有User类的对象
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<Orders> ordersList = mapper.findOrdersListResultMap();
        System.out.println(ordersList);
        session.close();
    }

    /**
     * 一对多关系的方式
     * 使用resultMap的collection配置一对多的关系
     * User中增加了List<></>
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserOrderList();
        System.out.println(userList);
        session.close();
    }


}
