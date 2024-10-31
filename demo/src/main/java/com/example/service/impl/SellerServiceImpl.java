package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Seller;
import com.example.mapper.SellerMapper;
import com.example.service.SellerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper , Seller> implements SellerService{

    @Resource
    private SellerMapper sellerMapper;

    @Override
    public List<Seller> selectAll() {
        return sellerMapper.selectAll();
    }

}
