package com.example.controller;

import com.example.domain.Goodspics;
import com.example.service.GoodspicsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pic")
public class PicController {
    @Resource
    private GoodspicsService goodspicsService;

    @RequestMapping("/add")
    public boolean add_Pic(@RequestBody Map<String,Object> picMap){
        int gid = (int) picMap.get("gid");
        byte[] data = Base64.getDecoder().decode((String) picMap.get("data"));
        Goodspics goodspics = new Goodspics();
        goodspics.setGid(gid);
        goodspics.setPicdata(data);
        return goodspicsService.save(goodspics);
    }
    @RequestMapping("/delete/{id}")
    public boolean delete(@PathVariable int id){
        return goodspicsService.removeById(id);
    }
    @RequestMapping("/get_pic")
    public List<String> get_pic(@RequestBody Map<String,Object> picMap){
        int gid = (int) picMap.get("gid");
        List<byte[]> data = goodspicsService.find_data(gid);
        return data.stream()
                .map(Base64.getEncoder()::encodeToString)
                .collect(Collectors.toList());
    }
}
