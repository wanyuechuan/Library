package com.example.library.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.library.Mapper.BookMapper;
import com.example.library.controller.request.BaseRequest;
import com.example.library.entity.Book;
import com.example.library.service.IBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    BookMapper bookMapper;


    @Override
    public void add(Book obj) {
        obj.setCategory(setCategories(obj.getCategories()));
        bookMapper.add(obj);
    }

    @Override
    public PageInfo<Book> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Book> books = bookMapper.listByCondition(baseRequest);

        return new PageInfo<>(books);
    }


    @Override
    public Integer UpdateData(Book obj) {
        obj.setCategory(setCategories(obj.getCategories()));
        obj.setUpdatetime(LocalDate.now());
        return bookMapper.UpdateData(obj);
    }

    @Override
    public Integer deleteById(Integer id) {
        return bookMapper.deleteById(id);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    private String setCategories(List<String> categories) {
        StringBuilder sb = new StringBuilder();
        if (CollUtil.isNotEmpty(categories)) {
            categories.forEach(v -> sb.append(v).append(" > "));
            return sb.substring(0, sb.lastIndexOf(" > "));
        }
        return sb.toString();
    }
}
