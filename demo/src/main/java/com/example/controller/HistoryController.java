package com.example.controller;

import com.example.domain.History;
import com.example.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Resource
    private HistoryService historyService;

    @RequestMapping("/list")
    public List<History> list(){
        return historyService.list();
    }
}
