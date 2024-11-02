package com.example.dao;

import com.example.domain.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerDao {
    List<Seller> findAll();
}
