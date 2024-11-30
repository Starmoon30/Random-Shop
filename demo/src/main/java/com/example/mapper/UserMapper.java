package com.example.mapper;

import com.example.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
* @author 小假
* @description 针对表【User】的数据库操作Mapper
* @createDate 2024-11-03 21:05:23
* @Entity com.example.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> Show_All();
    @Options(useGeneratedKeys = true, keyProperty = "uaccount")
    int Add_User(String account ,String password ,String phone ,String address, int category);
    User findUser(String account, String password);
    User select_By_Account(String account);
    int update_pwd(String account, String newPwd);
    int update_uinfo(String account,String phone,String address);
}




