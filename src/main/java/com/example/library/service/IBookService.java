package com.example.library.service;


import com.example.library.controller.request.BaseRequest;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBookService {
    void add(Book obj);
    PageInfo<Book> page(BaseRequest baseRequest);
    Integer UpdateData(Book obj);
    Integer deleteById(Integer id);
    Book getBookById(Integer id);
}
