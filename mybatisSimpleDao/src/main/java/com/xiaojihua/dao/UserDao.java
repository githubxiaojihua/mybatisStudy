package com.xiaojihua.dao;

import com.xiaojihua.pojo.User;

public interface UserDao {

    public User getUserById(int id) throws Exception;
    public void insertUser(User user) throws Exception;
}
