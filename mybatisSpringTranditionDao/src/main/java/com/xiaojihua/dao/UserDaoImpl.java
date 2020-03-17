package com.xiaojihua.dao;

import com.xiaojihua.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * mybatis与spring集成后传统DAO的开发方式需要继承
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    // 根据id查找用户
    public User getUserById(int id) throws Exception {
        SqlSession session = null;
        User user = null;
        try{
            // 可以直接从父类中获取SqlSession
            session = this.getSqlSession();
            user = session.selectOne("test.findUserById", id);
            System.out.println(user);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            // mybatis与spring集成以后不需要手动关闭了，由spring代为管理
            /*if(session != null){

                session.close();
            }*/
        }
        return user;
    }


    // 插入用户
    public void insertUser(User user) throws Exception {
        SqlSession session = this.getSqlSession();
        try{
            session.insert("test.insertUser",user);
            // 与spring集成以后不需要手工提交，由spring代为管理
            //session.commit();
        }finally{
            //session.close();
        }

    }
}
