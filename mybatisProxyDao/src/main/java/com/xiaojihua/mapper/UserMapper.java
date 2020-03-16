package com.xiaojihua.mapper;

import com.xiaojihua.pojo.QueryVo;
import com.xiaojihua.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 根据用户id查询用户信息
    public User findUserById(int id) throws Exception;
    // 查询用户列表
    public List<User> findUserByUsername(String username) throws Exception;
    // 添加用户信息
    public void insertUser(User user)throws Exception;
    // 输入参数为map作为查询条件
    public List<User> findUserByMap(Map<String,Object> map) throws Exception;
    // 传入的参数为pojo包装类
    public List<User> findUserByVo(QueryVo queryVo) throws Exception;
    // 输出类型为基础类型包括string
    public int findCount();
    // 根据用户id查询用户信息，输出类型为map
    public Map findUserByIdMap(int id) throws Exception;
    // 使用resultmap来解决表列和类属性的对应问题
    public User findUserResultMap(int id) throws Exception;
    // 使用动态语句查询
    public List<User> findListByUser(User user) throws Exception;
    // 使用foreach来处理传入的list或者array参数
    public List<User> findListByArray(QueryVo queryVo) throws Exception;
    // 使用SQL片段来查询
    public List<User> findListBySQLpd(User user) throws Exception;
    // set\\update标签的使用
    public void updateUser(User user) throws Exception;
}
