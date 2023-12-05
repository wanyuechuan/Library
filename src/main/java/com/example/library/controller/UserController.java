package com.example.library.controller;

import com.example.library.common.Result;
import com.example.library.controller.request.UserPageRequest;
import com.example.library.entity.User;
import com.example.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    IUserService userService;


    @GetMapping("/data")
    public Result UserData(UserPageRequest userPageRequest) {

        return Result.success(userService.page(userPageRequest));
    }


    @PostMapping("/changeData")
    public Result updateData(@RequestBody User user){

        int update = userService.UpdateData(user);

        if (update >= 1){
            return Result.success("修改成功");
        }

        return Result.error("修改失败");
    }

    @DeleteMapping("/deleteData")
    public Result deleteData(int id){
        int i = userService.deleteById(id);

        if (i >= 1){
            return  Result.success("删除成功");
        }

        return Result.error("删除失败");
    }
}
