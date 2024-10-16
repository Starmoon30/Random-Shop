package org.frisbeemall.service;

import org.frisbeemall.dao.GoodsDao;
import org.frisbeemall.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> getAllGoods() {
        return goodsDao.selectAll();
    }

    public Goods getGoodsById(int gid) {
        return goodsDao.selectById(gid);
    }

    @Transactional
    public int addGoods(Goods goods) {
        return goodsDao.insert(goods);
    }

    @Transactional
    public int updateGoods(Goods goods) {
        return goodsDao.updateById(goods);
    }

    @Transactional
    public int deleteGoods(int gid) {
        return goodsDao.deleteById(gid);
    }
}