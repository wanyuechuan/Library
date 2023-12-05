package com.example.library.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.library.common.Result;
import com.example.library.controller.request.CategoryPageRequest;
import com.example.library.entity.Admin;
import com.example.library.entity.Category;
import com.example.library.service.ICategoryService;
import com.example.library.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;


    @GetMapping("/data")
    public Result UserData(CategoryPageRequest categoryPageRequest) {

        return Result.success(iCategoryService.page(categoryPageRequest));
    }


    @PutMapping("/changeData")
    public Result updateData(@RequestBody Category category) {
        System.out.println(category);

        int update = iCategoryService.UpdateData(category);

        if (update >= 1) {
            return Result.success("修改成功", "修改成功");
        }

        return Result.error("修改失败", "修改失败");
    }

    @DeleteMapping("/deleteData")
    public Result deleteData(int id) {
        int i = iCategoryService.deleteById(id);

        if (i >= 1) {
            return Result.success("删除成功", "删除成功");
        }

        return Result.error("删除失败", "删除失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Category category) {
        log.info("添加分类信息:{}", category);
        iCategoryService.add(category);
        Admin currentAdmin = TokenUtils.getCurrentAdmin();

        log.info("当前管理员信息:{}", currentAdmin);

        return Result.success("添加成功", "添加成功");
    }

    @GetMapping("/tree")
    public Result tree() {
        List<Category> list = iCategoryService.list();

        return Result.success(createTree(null, list));
    }

    private List<Category> createTree(Integer pid, List<Category> categories) {
        List<Category> treeList = new ArrayList<>();

        for (Category category : categories) {
            if (pid == null){
                // 这就是一级节点 没有pid
                if (category.getPid() == null) {
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }else {
                if (pid.equals(category.getPid())){
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }
            if (CollUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }
        }

        return treeList;
    }

}
