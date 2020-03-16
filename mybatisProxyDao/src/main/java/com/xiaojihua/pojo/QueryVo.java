package com.xiaojihua.pojo;

/**
 * 用于测试pojo包装对象作为查询参数
 */
public class QueryVo {
    private User user;
    private int[] ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }
}
