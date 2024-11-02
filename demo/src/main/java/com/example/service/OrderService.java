package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Goods;
import com.example.domain.Order;

import java.util.List;

/**
* @author 小假
* @description 针对表【Order】的数据库操作Service
* @createDate 2024-11-02 18:03:06
*/
public interface OrderService extends IService<Order> {
    List<Order> listAll();

}
