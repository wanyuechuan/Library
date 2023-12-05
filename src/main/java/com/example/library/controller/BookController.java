package com.example.library.controller;

import com.example.library.common.Result;
import com.example.library.controller.request.BookPageRequest;
import com.example.library.controller.request.CategoryPageRequest;
import com.example.library.entity.Admin;
import com.example.library.entity.Book;
import com.example.library.service.IBookService;
import com.example.library.service.ICategoryService;
import com.example.library.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService bookService;
    


    @GetMapping("/data")
    public Result UserData(BookPageRequest bookPageRequest) {

        return Result.success(bookService.page(bookPageRequest));
    }


    @PutMapping("/changeData")
    public Result updateData(@RequestBody Book book) {
        System.out.println(book);

        int update = bookService.UpdateData(book);

        if (update >= 1) {
            return Result.success("修改成功", "修改成功");
        }

        return Result.error("修改失败", "修改失败");
    }

    @DeleteMapping("/deleteData")
    public Result deleteData(int id) {
        int i = bookService.deleteById(id);

        if (i >= 1) {
            return Result.success("删除成功", "删除成功");
        }

        return Result.error("删除失败", "删除失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Book book) {
        log.info("添加分类信息:{}",book);
        bookService.add(book);
        Admin currentAdmin = TokenUtils.getCurrentAdmin();

        log.info("当前管理员信息:{}", currentAdmin);

        return Result.success("添加成功", "添加成功");
    }

}
