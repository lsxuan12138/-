package com.hitfriend.dc.service;

import com.hitfriend.dc.exception.InvalidParamException;
import com.hitfriend.dc.mapper.UserMapper;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.util.D;
import com.hitfriend.dc.util.E;
import com.hitfriend.dc.util.Global;
import com.hitfriend.dc.util.Md5Util;
import com.hitfriend.dc.web.Page;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserMapper userMapper;
    private SqlSession session;
    public UserServiceImpl(){
        session= D.getConn();
        userMapper = session.getMapper(UserMapper.class);
    }
    @Override
    public User login(String openid){
        User user = userMapper.findUserByOpenid(openid);
        if(user == null){//用户不存在
            throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,"用户不存在");
        }
        return user;
    }

    @Override
    public User findUserById(int id) {
        User user = userMapper.findUserById(id);
        return user;
    }

    @Override
    public void addUser(User user) {

//        String encodeOpd = Md5Util.encode(user.getOpenid());
//        user.setOpenid(encodeOpd);
        User user1 = userMapper.findUserByOpenid(user.getOpenid());
        if(user1 != null){
            throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"用户已存在");
        }else{
            userMapper.addUser(user);
        }
    }

    @Override
    public void deleteUser(String openid) {
        User user = new User();
        user.setOpenid(openid);
        user.setTitle(Global.USER_STATUS_OFF);
        User existUser = userMapper.findUserByOpenid(openid);
        //查看用户是否存在或是否被删除
        if(existUser==null || existUser.getTitle()==Global.USER_STATUS_OFF)throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,"用户不存在或已被删除");
        userMapper.deleteUser(user);
    }
    @Override
    public void updateUser(User user) {
        User existUser = userMapper.findUserById(user.getId());
        //查看用户是否存在或是否被删除
        if(existUser==null || existUser.getTitle()==Global.USER_STATUS_OFF)throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,"用户不存在或已被删除");
        userMapper.updateUser(user);
    }

    @Override
    public Page<User> list(int pageNum, int pageSize, String keyword) {
        int total = userMapper.getUserByKeywordCount(keyword);
        int begin = (pageNum-1) * pageSize;
        List<User> datas= userMapper.getUserByKeyword(begin,pageSize,keyword);

        Page<User> PageData = new Page<User>(pageNum,pageSize,total,datas);
        return PageData;
    }
}
