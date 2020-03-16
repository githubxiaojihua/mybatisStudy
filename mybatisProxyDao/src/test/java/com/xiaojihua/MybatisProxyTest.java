package com.xiaojihua;

import com.xiaojihua.mapper.UserMapper;
import com.xiaojihua.pojo.QueryVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 传入参数为map类型
    @Test
    public void testSelectByMap() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("username","张");
        map.put("sex","1");
        map.put("address","河南郑州");
        List<User> userList = mapper.findUserByMap(map);
        System.out.println(userList);
    }

    /**
     * 测试pojo包装类作为输入参数
     * @throws Exception
     */
    @Test
    public void testSelectByVo() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("张");
        queryVo.setUser(user);
        List<User> userList = mapper.findUserByVo(queryVo);
        System.out.println(userList);
    }

    /**
     * 测试输出类型为基础类型，包括string
     * @throws Exception
     */
    @Test
    public void testCount() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int count = mapper.findCount();
        System.out.println(count);
    }

    /**
     * 测试输出类型为map
     * @throws Exception
     */
    @Test
    public void testResultMap() throws Exception {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用代理对象
        Map map = mapper.findUserByIdMap(1);
        System.out.println(map);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 使用resultmap来解决表列和字段名称不匹配的问题
     * @throws Exception
     */
    @Test
    public void testFindUserResultMap() throws Exception {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 调用代理对象
        User user = mapper.findUserResultMap(1);
        System.out.println(user);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 动态SQL查询，根据字段是否为空来判断是否增加查询条件
     * @throws Exception
     */
    @Test
    public void testDynSql() throws Exception {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("张");
        user.setSex("1");
        user.setAddress("河南郑州");
        // 调用代理对象
        List<User> listByUser = mapper.findListByUser(user);
        System.out.println(listByUser);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 测试使用foreach来处理传入的数组或者list参数
     * @throws Exception
     */
    @Test
    public void testFindArray() throws Exception {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        QueryVo queryVo = new QueryVo();
        int[] ids = {1,10,22};
        queryVo.setIds(ids);
        // 调用代理对象
        List<User> listByUser = mapper.findListByArray(queryVo);
        System.out.println(listByUser);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 测试SQL片段
     * @throws Exception
     */
    @Test
    public void testFindBySQLpd() throws Exception {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("张");
        user.setSex("1");
        user.setAddress("河南郑州");
        // 调用代理对象
        List<User> listByUser = mapper.findListBySQLpd(user);
        System.out.println(listByUser);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 测试set update标签的使用
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(31);
        user.setUsername("李三张");
        /*user.setSex("1");
        user.setAddress("河南郑州");*/
        // 调用代理对象
        mapper.updateUser(user);
        sqlSession.commit();
        // 关闭session
        sqlSession.close();
    }
}
