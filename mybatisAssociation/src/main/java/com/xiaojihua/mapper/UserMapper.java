package com.xiaojihua.mapper;

import com.xiaojihua.pojo.Orders;
import com.xiaojihua.pojo.OrdersCustomer;
import com.xiaojihua.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<OrdersCustomer> findOrdersList() throws Exception;
    public List<Orders> findOrdersListResultMap() throws Exception;
    public List<User> getUserOrderList() throws Exception;
}
