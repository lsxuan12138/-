package com.hitfriend.dc.mapper;

import com.hitfriend.dc.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 通过openid查找用户
     * */
    User findUserByOpenid(String openid);
    /**
     * 通过id查找用户
     * */
    User findUserById(int id);
    /**
     * 添加用户
     * */
    void addUser (User user);
    /**
     * 修改用户数据
     * */
    void updateUser (User user);
    void deleteUser (User user);
    /**
     * 返回符合条件的记录的数量
     * */
    int getUserByKeywordCount(@Param("keyword")String keyword);
    /**
     * 按条件分页查询
     * 返回符合条件的记录
     * */
    List<User> getUserByKeyword(@Param("begin")int begin, @Param("pageSize")int pageSize, @Param("keyword")String keyword);
}
