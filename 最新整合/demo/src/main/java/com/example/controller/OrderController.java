package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.domain.Goods;
import com.example.domain.Order;
import com.example.domain.Stockhistory;
import com.example.service.GoodsService;
import com.example.service.OrderService;
import com.example.service.StockhistoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private StockhistoryService stockhistoryService;

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
    @RequestMapping("/creat")
    public boolean creat(@RequestBody Order order){
        return orderService.creat(order);
    }
    @RequestMapping("/update_Ostate")
    public boolean update_Ostate(@RequestBody Map<String,Object> orderMap){
        int id = (int) orderMap.get("id");
        int state = (int) orderMap.get("state");
        if (state==2){//如果是完成订单
            //同步修改库存,如果修改后库存为0则自动下架
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
            //同步添加出库记录
            Stockhistory stockhistory = new Stockhistory();
            stockhistory.setGID(gid);
            stockhistory.setSHStockO(goods.getGstock());
            stockhistory.setSHStockN(goods.getGstock()-1);
            stockhistory.setSHTime(new Date());
            stockhistory.setSHReason(0);//0表示该库存记录变更原因为成交出库
            stockhistoryService.save(stockhistory);
        }
        return orderService.update_ostate(id,state);
    }
}
