package com.example.library.Mapper;

import com.example.library.controller.request.BaseRequest;
import com.example.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BookMapper {
    Integer add(Book Book);
    List<Book> listByCondition(BaseRequest baseRequest);
    Integer UpdateData(Book category);
    Integer deleteById(Integer id);
    Book getBookById(Integer id);

}
