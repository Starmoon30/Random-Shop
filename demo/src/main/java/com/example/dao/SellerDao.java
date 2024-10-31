package com.example.dao;

import com.example.entity.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerDao {
    List<Seller> findAll();
}
