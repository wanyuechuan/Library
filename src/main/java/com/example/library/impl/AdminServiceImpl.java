package com.example.library.impl;

import com.example.library.Mapper.AdminMapper;
import com.example.library.controller.dto.LoginDTO;
import com.example.library.controller.request.BaseRequest;
import com.example.library.controller.request.LoginRequest;
import com.example.library.controller.request.PasswordRequest;
import com.example.library.entity.Admin;
import com.example.library.exception.ServiceException;
import com.example.library.service.IAdminService;
import com.example.library.utils.Secure;
import com.example.library.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin selectAdmin(String username) {
        return adminMapper.selectAdmin(username);
    }

    @Override
    public void add(Admin admin) {
        // 数据库里面设置了不允许重复约束
        admin.setPassword(Secure.secureSalt(admin.getPassword()));
        admin.setCreatetime(new Date());

        try {
            adminMapper.add(admin);
        } catch (DuplicateKeyException e) {
            log.error("用户重复:{}", admin);
            throw new ServiceException("该用户已存在");
        }

    }

    @Override
    public Integer existEmail(String email) {
        return adminMapper.existEmail(email);
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Admin> admins = adminMapper.listByCondition(baseRequest);
        return new PageInfo<>(admins);
    }


    @Override
    public Integer UpdateData(Admin admin) {
        admin.setUpdatetime(new Date());
        return adminMapper.UpdateData(admin);
    }

    @Override
    public Integer deleteById(Integer id) {
        return adminMapper.deleteById(id);
    }


    @Override
    public LoginDTO login(LoginRequest loginRequest) {
        loginRequest.setPassword(Secure.secureSalt(loginRequest.getPassword()));
        Admin admin = adminMapper.getByUsernameAndPassword(loginRequest);

        if (admin == null){
            throw new ServiceException("用户或密码错误");
        }
        if (!admin.getStatus()){
            throw new ServiceException("当前用户被禁用，请联系管理员");
        }

        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin, loginDTO);


        // 生成token
        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());

        loginDTO.setToken(token);
        return loginDTO;

    }


    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.getAdminById(id);
    }

    @Override
    public void ChangePassword(PasswordRequest passwordRequest) {
        passwordRequest.setNewPass(Secure.secureSalt(passwordRequest.getNewPass()));

        Integer count = adminMapper.changePassword(passwordRequest);
        if (count <= 0){
            throw new ServiceException("修改失败");
        }


    }


}
