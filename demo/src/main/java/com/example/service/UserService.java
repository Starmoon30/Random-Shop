package com.example.service;

import com.example.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 小假
* @description 针对表【User】的数据库操作Service
* @createDate 2024-11-03 21:05:23
*/
public interface UserService extends IService<User> {
    List<User> Show_All();
    boolean Add_User(User user);
    User findUser(String account,String password);
    User select_By_Account(String account);
    boolean update_pwd(String account, String newPwd);
    boolean reset_admin();

    boolean update_uinfo(String account,String phone,String address);
}
