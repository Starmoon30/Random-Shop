package com.example.mapper;

import com.example.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 小假
* @description 针对表【Order】的数据库操作Mapper
* @createDate 2024-11-03 21:05:20
* @Entity com.example.domain.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




