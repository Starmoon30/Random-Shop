package org.random_shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.random_shop.vo.Goods;
import org.random_shop.vo.Seller;

public class SellerDaoImpl implements SellerDao {
    public static final String URL = "jdbc:sqlite:C:\\Users\\小假\\Desktop\\random-shop.db";
    public static final String JDBC = "org.sqlite.JDBC";

    @Override
    public void updatePwd(String account, String pwd) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC);
            con = DriverManager.getConnection(URL);
            String sql = "UPDATE Seller SET spassword = ? WHERE saccount = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, pwd);
            ps.setString(2, account);
            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("密码更新成功。");
            } else {
                System.out.println("密码更新失败。");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Seller selectByAccount(String account) throws SQLException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
            try{
                try {
					Class.forName(JDBC);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
                con = DriverManager.getConnection(URL);
                String sql = "SELECT * FROM Seller where saccount = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,account);
                res = ps.executeQuery();
                if (res.next()) {
                    Seller s = new Seller(
                        res.getString("saccount"),
                        res.getString("spassword")
                    );
                    return s;
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (res != null)
                    res.close();
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // 用户不存在

}
}
