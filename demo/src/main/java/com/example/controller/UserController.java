package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.User;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    //查
    @RequestMapping("/list")
    public List<User> Get_All_Users(){
        return userService.Show_All();
    }
    @RequestMapping("/login")
    public boolean Login(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUaccount,user.getUaccount());
        lambdaQueryWrapper.eq(User::getUpassword,user.getUpassword());
        return !userService.list(lambdaQueryWrapper).isEmpty();
    }
    @RequestMapping("/uinfo")
    public User uinfo(@RequestBody Map<String,Object> userMap){
        String account = (String) userMap.get("account");
        return userService.select_By_Account(account);
    }
    //增
    @RequestMapping("/register")
    public boolean Register(@RequestBody User user){
        return userService.Add_User(user);
    }
    //改
    @RequestMapping("/update_pwd")
    public boolean Update_Pwd(@RequestBody Map<String,Object> userMap){
        String account = (String) userMap.get("account");
        String old_pwd = (String) userMap.get("old_pwd");
        String new_pwd = (String) userMap.get("new_pwd");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUaccount,account);
        lambdaQueryWrapper.eq(User::getUpassword,old_pwd);
        if(!userService.list(lambdaQueryWrapper).isEmpty()){
            return userService.update_pwd(account,new_pwd);
        }
        else {
            return false;
        }
    }
    @RequestMapping("/update_uinfo")
    public boolean Update_uinfo(@RequestBody Map<String,Object> userMap){
        String account = (String) userMap.get("account");
        String phone = (String) userMap.get("phone");
        String address = (String) userMap.get("address");
        return userService.update_uinfo(account,phone,address);
    }
    @RequestMapping("/reset_admin")
    public boolean Reset_admin(){
        return userService.reset_admin();
    }
}
