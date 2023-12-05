package com.example.library.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.library.controller.request.BaseRequest;
import com.example.library.controller.request.UserPageRequest;
import com.example.library.entity.Admin;
import com.example.library.entity.User;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {
    User selectUser(String username);

    Integer add(User user);

    Integer existEmail(String email);

    List<User> listByCondition(BaseRequest baseRequest);

    Integer UpdateData(User user);

    Integer deleteById(Integer id);
}
