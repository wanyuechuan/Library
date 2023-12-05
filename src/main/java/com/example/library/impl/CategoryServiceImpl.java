package com.example.library.impl;

import com.example.library.Mapper.CategoryMapper;
import com.example.library.controller.request.BaseRequest;
import com.example.library.entity.Category;
import com.example.library.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

    @Override
    public Category selectCategory(String username) {
        return null;
    }

    @Override
    public void add(Category obj) {
        categoryMapper.add(obj);
    }

    @Override
    public PageInfo<Category> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Category> categories = categoryMapper.listByCondition(baseRequest);
        return new PageInfo<>(categories);
    }

    @Override
    public Integer UpdateData(Category obj) {
        obj.setUpdatetime(LocalDate.now());
        return categoryMapper.UpdateData(obj);
    }

    @Override
    public Integer deleteById(Integer id) {
        return categoryMapper.deleteById(id);
    }


    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }
}
