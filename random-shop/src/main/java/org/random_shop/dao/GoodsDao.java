package org.random_shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.random_shop.vo.Goods;

public interface GoodsDao {
    public void addGoods(String name,String desc,int value,byte[] img) throws SQLException;

    public void deleteById(int gid) throws SQLException;

    public void updateShelf(int gid, int shelf) throws SQLException;

    public List<Goods> showGoods() throws SQLException;

    public List<Goods> selectByshelf(int shelf) throws SQLException;

    public void setState(int Gid, int state) throws SQLException;
    
    public int countByshelf(int shelf) throws SQLException;
}