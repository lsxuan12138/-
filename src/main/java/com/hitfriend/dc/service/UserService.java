package com.hitfriend.dc.service;

import com.hitfriend.dc.exception.InvalidParamException;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.web.Page;

public interface UserService {
    /**
    * 用户登录
    * */
    User login(String openid);
    /**
     * 由id查询用户信息
     * */
    User findUserById(int id);
    /**
     * 添加用户
     * */
    void addUser(User user);
    /**
     * 删除用户
     * */
    void deleteUser(String openid);
    /**
     * 修改用户数据
     * */
    void updateUser(User user);
    /**
     * 分页查询用户数据
     * @param PageNum 页码
     * @param PageSize 每页数据量
     * */
    Page<User> list(int PageNum, int PageSize , String keyword);
}
