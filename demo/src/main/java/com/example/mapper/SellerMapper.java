package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 小假
* @description 针对表【Seller】的数据库操作Mapper
* @createDate 2024-11-02 18:02:31
* @Entity generator.domain.Seller
*/
@Mapper
public interface SellerMapper extends BaseMapper<Seller> {
    List<Seller> listAll();
}




