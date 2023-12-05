package com.example.library.Mapper;

import com.example.library.controller.request.BaseRequest;
import com.example.library.controller.request.LoginRequest;
import com.example.library.controller.request.PasswordRequest;
import com.example.library.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AdminMapper {
    Admin selectAdmin(String username);
    Integer add(Admin admin);
    Integer existEmail(String email);
    List<Admin> listByCondition(BaseRequest baseRequest);
    Integer UpdateData(Admin admin);
    Integer deleteById(Integer id);
    Admin getByUsernameAndPassword(LoginRequest loginRequest);
    Admin getAdminById(Integer id);
    Integer changePassword(PasswordRequest passwordRequest);
}
