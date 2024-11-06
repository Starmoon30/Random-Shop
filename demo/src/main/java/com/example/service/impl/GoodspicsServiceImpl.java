package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Goodspics;
import com.example.service.GoodspicsService;
import com.example.mapper.GoodspicsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小假
* @description 针对表【GoodsPics】的数据库操作Service实现
* @createDate 2024-11-06 18:47:05
*/
@Service
public class GoodspicsServiceImpl extends ServiceImpl<GoodspicsMapper, Goodspics>
    implements GoodspicsService{

    @Resource
    private GoodspicsMapper goodspicsMapper;

    @Override
    public List<Byte[]> find_data(int gid) {
        return goodspicsMapper.find_data(gid);
    }
}




