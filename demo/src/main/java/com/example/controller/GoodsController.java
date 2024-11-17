package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.domain.Goods;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.GoodsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @RequestMapping("/update_Ginfo")
    public boolean update_Ginfo(@RequestBody Map<String,Object> goodsMap){
        int gid = (int) goodsMap.get("gid");
        String name = (String) goodsMap.get("name");
        String desc = (String) goodsMap.get("desc");
        int stock = (int) goodsMap.get("stock");
        int value = (int) goodsMap.get("value");
        int cid = (int) goodsMap.get("cid");
        LambdaUpdateWrapper<Goods> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Goods::getGid,gid);
        Goods goods = new Goods();
        goods.setGname(name);
        goods.setGdesc(desc);
        goods.setGstock(stock);
        goods.setGvalue(value);
        goods.setCid(cid);
        return goodsService.update(goods,updateWrapper);
    }

    @RequestMapping("/get_info")
    public List<Goods> get_info(@RequestBody Map<String,Object> goodsMap){
        int gid = (int) goodsMap.get("gid");
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getGid,gid);
        return goodsService.list(lambdaQueryWrapper);
    }
    @RequestMapping("/list_By_Category")
    public List<Goods> list_By_Category(@RequestBody Map<String,Object> goodsMap){
        int cid = (int) goodsMap.get("cid");
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getCid,cid);
        return goodsService.list(lambdaQueryWrapper);
    }
}
