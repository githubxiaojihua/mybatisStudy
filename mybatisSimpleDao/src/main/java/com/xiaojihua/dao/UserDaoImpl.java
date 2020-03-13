package com.xiaojihua.dao;

import com.xiaojihua.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory factory;
    // 注入SqlSessionFactory
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        factory = sqlSessionFactory;
    }

    // 根据id查找用户
    public User getUserById(int id) throws Exception {
        SqlSession session = null;
        User user = null;
        try{
            session = factory.openSession();
            user = session.selectOne("test.findUserById", 1);
            System.out.println(user);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }
        return user;
    }


    // 插入用户
    public void insertUser(User user) throws Exception {
        SqlSession session = factory.openSession();
        try{
            session.insert("test.insertUser",user);
            session.commit();
        }finally{
            session.close();
        }

    }
}
