package com.example.dao;

import com.example.entity.Seller;

import java.util.List;

@Mapper
public interface SellerDao {
    List<Seller> findAll();
}
