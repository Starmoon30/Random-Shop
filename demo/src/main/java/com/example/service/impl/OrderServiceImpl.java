package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Order;
import com.example.service.OrderService;
import com.example.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 小假
* @description 针对表【Order】的数据库操作Service实现
* @createDate 2024-11-03 21:05:20
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




