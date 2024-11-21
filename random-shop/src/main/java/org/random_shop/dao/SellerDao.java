package org.random_shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.random_shop.vo.Seller;

public interface SellerDao {
    public Seller selectByAccount (String account) throws SQLException;

    public void updatePwd(String account, String pwd) throws SQLException;
}