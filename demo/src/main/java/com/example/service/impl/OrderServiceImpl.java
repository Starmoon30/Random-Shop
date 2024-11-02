package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Order;
import com.example.domain.Seller;
import com.example.mapper.OrderMapper;
import com.example.mapper.SellerMapper;
import com.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小假
* @description 针对表【Order】的数据库操作Service实现
* @createDate 2024-11-02 18:03:06
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> listAll() {
        return orderMapper.listAll(); // 查询所有记录
    }
}




