package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Goods;
import com.example.mapper.GoodsMapper;
import com.example.service.GoodsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小假
* @description 针对表【Goods】的数据库操作Service实现
* @createDate 2024-11-02 18:03:13
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> listAll() {
        return goodsMapper.listAll(); // 查询所有记录
    }
}




