package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.domain.Goods;
import com.example.domain.Order;
import com.example.service.GoodsService;
import com.example.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private GoodsService goodsService;

    //查
    @RequestMapping("/list")
    public List<Order> Get_All_Order(){
        return orderService.Show_All();
    }
    @RequestMapping("/show_History")
    public List<Order> show_History(@RequestBody Map<String,Object> orderMap){
        String account = (String) orderMap.get("account");
        return orderService.get_History_By_Account(account);
    }

    @RequestMapping("/show_Buy")
    public List<Order> show_Buy(@RequestBody Map<String,Object> orderMap){
        String account = (String) orderMap.get("account");
        return orderService.get_History_By_Account_And_State(account,2);
    }

    @RequestMapping("/update_Oinfo")
    public boolean update_Oinfo(@RequestBody Map<String,Object> orderMap){
        int id = (int) orderMap.get("id");
        String phone = (String) orderMap.get("phone");
        String address = (String) orderMap.get("address");
        String remark = (String) orderMap.get("remark");
        return orderService.update_oinfo(id,phone,address,remark);
    }

    @RequestMapping("/update_Ostate")
    public boolean update_Ostate(@RequestBody Map<String,Object> orderMap){
        int id = (int) orderMap.get("id");
        int state = (int) orderMap.get("state");
        if (state==2){
            List<Order> orders = orderService.get_by_oid(id);
            Order order = orders.getFirst(); // 获取列表中的第一个元素
            int gid = order.getGid();
            LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Goods::getGid,gid);
            List<Goods> goodsList = goodsService.list(lambdaQueryWrapper);
            Goods goods = goodsList.getFirst();
            LambdaUpdateWrapper<Goods> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Goods::getGid,gid);
            Goods g = new Goods();
            g.setGstock(goods.getGstock()-1);
            if (goods.getGstock()==1) g.setGshelf(2);
            goodsService.update(g,updateWrapper);
        }
        return orderService.update_ostate(id,state);
    }
}
