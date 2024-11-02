package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Goods;
import com.example.domain.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 小假
* @description 针对表【Goods】的数据库操作Mapper
* @createDate 2024-11-02 18:03:13
* @Entity generator.domain.Goods
*/
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> listAll();
}




