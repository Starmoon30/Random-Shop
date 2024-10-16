package org.frisbeemall.controller;

import org.frisbeemall.domain.Goods;
import org.frisbeemall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

    @GetMapping("/{gid}")
    public Goods getGoodsById(@PathVariable int gid) {
        return goodsService.getGoodsById(gid);
    }

    @PostMapping
    public int addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    @PutMapping("/{gid}")
    public int updateGoods(@RequestBody Goods goods) {
        return goodsService.updateGoods(goods);
    }

    @DeleteMapping("/{gid}")
    public int deleteGoods(@PathVariable int gid) {
        return goodsService.deleteGoods(gid);
    }
}