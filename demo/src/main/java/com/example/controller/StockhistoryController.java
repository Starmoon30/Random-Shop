package com.example.controller;

import com.example.domain.Stockhistory;
import com.example.service.StockhistoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class StockhistoryController {
    @Resource
    private StockhistoryService stockhistoryService;

    @RequestMapping("/list")
    public List<Stockhistory> list(){
        return stockhistoryService.list();
    }
}
