package com.example.library.impl;

import com.example.library.Mapper.AdminMapper;
import com.example.library.Mapper.UserMapper;
import com.example.library.controller.request.BaseRequest;
import com.example.library.entity.User;
import com.example.library.exception.ServiceException;
import com.example.library.service.IUserService;
import com.example.library.utils.Secure;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;

    @Override
    public User selectUser(String username) {
        return userMapper.selectUser(username);
    }

    @Override
    public Integer add(User user) {

        User tempuser = userMapper.selectUser(user.getUsername());
        if (tempuser != null){
            throw new ServiceException("用户已存在");
        }
        user.setPassword(Secure.secureSalt(user.getPassword()));

        user.setCreatetime(new Date());
        return userMapper.add(user);
    }


    @Override
    public Boolean existEmail(String email) {
        boolean flag = true;
        if (userMapper.existEmail(email) >= 1 || adminMapper.existEmail(email) >=1){
            flag = false;
        }


        return flag;
    }


    @Override
    public PageInfo<User> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<User> users = userMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public Integer UpdateData(User user) {
        user.setPassword(Secure.secureSalt(user.getPassword()));
        user.setUpdatetime(new Date());
        return userMapper.UpdateData(user);
    }

    @Override
    public Integer deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

}
