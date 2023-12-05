package com.example.library.service;


import com.example.library.controller.request.BaseRequest;
import com.example.library.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {
    User selectUser(String username);
    Integer add(User obj);
    Boolean existEmail(String email);
    PageInfo<User> page(BaseRequest baseRequest);
    Integer UpdateData(User obj);
    Integer deleteById(Integer id);
}
