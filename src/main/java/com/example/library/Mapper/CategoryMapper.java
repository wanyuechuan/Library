package com.example.library.Mapper;

import com.example.library.controller.request.BaseRequest;
import com.example.library.controller.request.LoginRequest;
import com.example.library.controller.request.PasswordRequest;
import com.example.library.entity.Category;
import com.example.library.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CategoryMapper {
    List<Category> list();
    Category selectCategory(String username);
    Integer add(Category Category);
    List<Category> listByCondition(BaseRequest baseRequest);
    Integer UpdateData(Category category);
    Integer deleteById(Integer id);
    Category getCategoryById(Integer id);

}
