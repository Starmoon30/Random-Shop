package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小假
* @description 针对表【User】的数据库操作Service实现
* @createDate 2024-11-03 21:05:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> Show_All(){
        return userMapper.Show_All();
    }

    @Override
    public boolean Add_User(User user) {
        int affectedRows = userMapper.Add_User(user.getUaccount(),user.getUpassword(),user.getUphone(),user.getUaddress(),1);
        return affectedRows > 0;
    }
    @Override
    public User findUser(User user) {
        return userMapper.findUser(user.getUaccount(),user.getUpassword());
    }

    @Override
    public User select_By_Account(String account) {
        return userMapper.select_By_Account(account);
    }

    @Override
    public boolean update_pwd(String account, String newPwd) {
        int affectedRows = userMapper.update_pwd(account,newPwd);
        return affectedRows > 0;
    }

    @Override
    public boolean reset_admin() {
        int affectedRows = userMapper.update_pwd("admin123","admin123");
        return affectedRows > 0;
    }

    @Override
    public boolean update_uinfo(String account,String phone,String address) {
        int affectedRows = userMapper.update_uinfo(account,phone,address);
        return affectedRows > 0;
    }
}




