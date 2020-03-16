package com.xiaojihua.pojo;

public class OrdersCustomer extends Orders {
    private String username;// 用户名称
    private String address;// 用户地址

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
