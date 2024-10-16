package org.random_shop.dao;

import java.sql.SQLException;
import java.util.List;
import org.random_shop.vo.Order;

public interface OrderDao {
    public void addOrder(int gid,String odesc) throws SQLException;

    public List<Order> showOrdersWithGoods() throws SQLException;

    public void updateOrderState(int oid, int ostate) throws SQLException;

    public void selectById(int gid) throws SQLException;
}