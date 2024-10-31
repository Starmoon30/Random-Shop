package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Seller;

import java.util.List;

public interface SellerService extends IService<Seller> {
    List<Seller> listAll();
}
