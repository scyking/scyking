package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SignController
 * @Description 注册控制器
 * @Author scyking
 * @Date 2018/11/21 15:59
 * @Version 1.0
 */
@Api(value = "SignController", tags = {"注册控制器"})
@Controller
public class SignController {

    @ApiOperation(value = "获取注册页面", notes = "通过该接口跳转到“注册页面”位置")
    @GetMapping("/signup")
    public String signup() {

        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public Object signup(HttpServletRequest request) {

        return "";
    }

    @GetMapping("/signin")
    public String signin() {
        StringBuffer sb = new StringBuffer();
        return "signin";
    }

    @PostMapping("/signin")
    @ResponseBody
    public Object signin(HttpServletRequest request) {

        return "";
    }

    @GetMapping("/signout")
    @ResponseBody
    public String index() {

        return "signout";
    }

    @GetMapping("/password_reset")
    public String passwordReset() {

        return "";
    }
}
