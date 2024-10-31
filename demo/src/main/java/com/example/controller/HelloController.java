package com.example.controller;

import com.example.entity.Seller;
import com.example.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @GetMapping
    public String hello(){return  "hello ghb";}

    @Autowired
    private SellerService sellerService;

    @GetMapping("/list")
    public List<Seller> list(){
        return sellerService.list();
    }

}
