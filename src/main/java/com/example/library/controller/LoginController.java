package com.example.library.controller;

import com.example.library.common.Result;
import com.example.library.controller.dto.LoginDTO;
import com.example.library.controller.request.LoginRequest;
import com.example.library.entity.User;
import com.example.library.service.IAdminService;
import com.example.library.service.IUserService;
import com.example.library.service.MailService;
import com.example.library.utils.RandomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    IUserService userService;
    @Autowired
    IAdminService adminService;

    @Autowired
    MailService mailService;

    private String email_code = RandomCode.RecreateCode();



    @PostMapping("/login")
        public Result Login(@RequestBody LoginRequest loginRequest) {

        LoginDTO login = adminService.login(loginRequest);

        return  Result.success(login,"登录成功");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user,@RequestParam String code){


        if (code.equals(email_code)){
            userService.add(user);
            email_code = RandomCode.RecreateCode();

            return Result.success("注册成功","注册成功");
        }
        return  Result.error("验证码错误");
    }


    @PostMapping("/api/auth/valid-register-email")
    public Result validRegisterEmail(@RequestParam("email") String email, HttpSession session){

        System.out.println(email);

        Boolean exist = userService.existEmail(email);

        if (exist){
            email_code =RandomCode.RecreateCode();
            System.out.println(email_code);
            try {
                mailService.sendSimpleMail(email, "智慧图书管理系统-验证码", "您的验证码是:"+ email_code);
                return Result.success(null, "该邮箱可注册");
            }catch (MailException e){
                return Result.success("邮件发送失败");
            }
        }else {
            return Result.error("该邮箱已注册");
        }
    }
}

