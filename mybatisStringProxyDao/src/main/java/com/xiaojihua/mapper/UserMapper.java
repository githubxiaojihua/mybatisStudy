package com.xiaojihua.mapper;

import com.xiaojihua.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 根据用户id查询用户信息
    public User findUserById(int id) throws Exception;

}
