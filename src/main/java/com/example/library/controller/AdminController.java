package com.example.library.controller;

import com.example.library.common.Result;
import com.example.library.controller.dto.LoginDTO;
import com.example.library.controller.request.AdminPageRequest;
import com.example.library.controller.request.LoginRequest;
import com.example.library.controller.request.PasswordRequest;
import com.example.library.controller.request.UserPageRequest;
import com.example.library.entity.Admin;
import com.example.library.service.IAdminService;
import com.example.library.service.IUserService;
import com.example.library.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        LoginDTO login = adminService.login(loginRequest);

        System.out.println(login);

        return Result.success();
    }


    @GetMapping("/data")
    public Result UserData(AdminPageRequest adminPageRequest) {

        return Result.success(adminService.page(adminPageRequest));
    }


    @PutMapping("/changeData")
    public Result updateData(@RequestBody Admin admin) {
        System.out.println(admin);

        int update = adminService.UpdateData(admin);

        if (update >= 1) {
            return Result.success("修改成功", "修改成功");
        }

        return Result.error("修改失败", "修改失败");
    }

    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest passwordRequest) {

        log.info("用户信息{}", passwordRequest);

        adminService.ChangePassword(passwordRequest);
        return Result.success();
    }

    @DeleteMapping("/deleteData")
    public Result deleteData(int id) {
        int i = adminService.deleteById(id);

        if (i >= 1) {
            return Result.success("删除成功", "删除成功");
        }

        return Result.error("删除失败", "删除失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        log.info("添加用户信息:{}",admin);
        adminService.add(admin);
        Admin currentAdmin = TokenUtils.getCurrentAdmin();

        log.info("当前管理员信息:{}", currentAdmin);

        return Result.success("添加成功", "添加成功");
    }

    @GetMapping("/getid")
    public Result getadmin(Integer id) {
        return Result.success(adminService.getAdminById(id));
    }
}
