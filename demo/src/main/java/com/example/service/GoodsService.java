package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Goods;

import java.util.List;

/**
* @author 小假
* @description 针对表【Goods】的数据库操作Service
* @createDate 2024-11-02 18:03:13
*/
public interface GoodsService extends IService<Goods> {

    List<Goods> listAll();
}
