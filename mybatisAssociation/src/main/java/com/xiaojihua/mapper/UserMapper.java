package com.xiaojihua.mapper;

import com.xiaojihua.pojo.Orders;
import com.xiaojihua.pojo.OrdersCustomer;

import java.util.List;

public interface UserMapper {
    public List<OrdersCustomer> findOrdersList() throws Exception;
    public List<Orders> findOrdersListResultMap() throws Exception;
}
