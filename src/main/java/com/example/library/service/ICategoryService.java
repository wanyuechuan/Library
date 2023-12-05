package com.example.library.service;


import com.example.library.controller.dto.LoginDTO;
import com.example.library.controller.request.BaseRequest;
import com.example.library.controller.request.LoginRequest;
import com.example.library.controller.request.PasswordRequest;
import com.example.library.entity.Category;
import com.example.library.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICategoryService {
    List<Category> list();
    Category selectCategory(String username);
    void add(Category obj);
    PageInfo<Category> page(BaseRequest baseRequest);
    Integer UpdateData(Category obj);
    Integer deleteById(Integer id);
    Category getCategoryById(Integer id);
}
