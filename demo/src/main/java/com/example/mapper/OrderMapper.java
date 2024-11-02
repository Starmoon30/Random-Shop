package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 小假
* @description 针对表【Order】的数据库操作Mapper
* @createDate 2024-11-02 18:03:06
* @Entity generator.domain.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> listAll();
}




