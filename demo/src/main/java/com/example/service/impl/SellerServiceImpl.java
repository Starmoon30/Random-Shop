package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Seller;
import com.example.mapper.SellerMapper;
import com.example.service.SellerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小假
* @description 针对表【Seller】的数据库操作Service实现
* @createDate 2024-11-02 18:02:31
*/
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {
    @Resource
    private SellerMapper sellerMapper;

    @Override
    public List<Seller> listAll() {
        return sellerMapper.listAll(); // 查询所有记录
    }
}




