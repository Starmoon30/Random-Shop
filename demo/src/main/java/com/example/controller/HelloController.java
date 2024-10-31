package com.example.controller;

import com.example.entity.Seller;
import com.example.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @GetMapping
    public String hello(){return  "hello ghb";}

    @Autowired
    private SellerService sellerService;

    @GetMapping("/list1")
    public List<Seller> list1(){
        return sellerService.listAll();
    }

    @GetMapping("/list")
    public List<Seller> list(){
        List<Seller> sellers = sellerService.list();
        System.out.println("Sellers: " + sellers);
        return sellers;
    }
    @GetMapping("/list_test")
    public ResponseEntity<String> list_test() throws JsonProcessingException {
        List<Seller> sellers = sellerService.list();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sellers);
        System.out.println("JSON: " + json);
        return ResponseEntity.ok(json);
    }
}
