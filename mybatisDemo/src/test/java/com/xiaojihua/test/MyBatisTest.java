package com.xiaojihua.test;

import com.xiaojihua.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    //会话工厂
    private SqlSessionFactory sqlSessionFactory;

    // 创建MyBatis相关主要的类sqlSessionFactory
    @Before
    public void createSqlSessionFactory() throws IOException {
        // 配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);

    }

    // 根据 id查询用户信息
    // SqlSession为非线程安全的因此放在方法中调用比较合适
    // 禁止作为共享变量
    @Test
    public void testFindUserById() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 查询单个记录，根据用户id查询用户信息
            User user = sqlSession.selectOne("test.findUserById", 10);
            // 输出用户信息
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    // 根据用户名模糊查询用户，返回List
    @Test
    public void testFindByUsername(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            List<User> list = sqlSession.selectList("test.findUserByUsername", "张");
            System.out.println(list.size());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            User user = new User();
            user.setUsername("小张");
            user.setAddress("河南郑州");
            user.setBirthday(new Date());
            user.setSex("1");
            sqlSession.insert("test.insertUser",user);
            //提交事务
            sqlSession.commit();
            System.out.println(user);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }


    @Test
    public void testDelete(){
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("test.deleteUserById",30);
            //提交事务
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = null;
        try{

            sqlSession = sqlSessionFactory.openSession();
            User user = new User();
            user.setId(28);
            user.setUsername("小小张");
            user.setAddress("北京");
            user.setSex("2");
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2019-05-06"));
            sqlSession.delete("test.updateUser",user);
            //提交事务
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

}
