package com.example.library.service;


import com.example.library.controller.dto.LoginDTO;
import com.example.library.controller.request.BaseRequest;
import com.example.library.controller.request.LoginRequest;
import com.example.library.controller.request.PasswordRequest;
import com.example.library.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAdminService {
    Admin selectAdmin(String username);
    void add(Admin obj);
    Integer existEmail(String email);
    PageInfo<Admin> page(BaseRequest baseRequest);
    Integer UpdateData(Admin obj);
    Integer deleteById(Integer id);
    LoginDTO login(LoginRequest loginRequest);
    Admin getAdminById(Integer id);
    void ChangePassword(PasswordRequest passwordRequest);
}
