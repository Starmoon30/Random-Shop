package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Seller;

import java.util.List;

/**
* @author 小假
* @description 针对表【Seller】的数据库操作Service
* @createDate 2024-11-02 18:02:31
*/
public interface SellerService extends IService<Seller> {
    List<Seller> listAll();
}
