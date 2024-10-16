package org.random_shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.random_shop.vo.Goods;


public class GoodsDaoImpl implements GoodsDao {
    public static final String URL = "jdbc:sqlite:D:\\Eclipse\\random-shop\\src\\db\\random-shop.db";
    public static final String JDBC = "org.sqlite.JDBC";

    @Override
    public void addGoods(Goods Goods) throws SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        try {
//            Class.forName(JDBC);
//            con = DriverManager.getConnection(URL);
//            String sql = "insert into Goods(gname,gdesc,gvalue,gpic) values(?,?,?,?)";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, Goods.getGname());
//            ps.setString(2, Goods.getGdesc());
//            ps.setInt(3, Goods.getGvalue());
//            ps.setString(4, Goods.getGpic());
//            ps.executeUpdate();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public List<Goods> showGoods() throws SQLException {
        List<Goods> GoodsList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            Class.forName(JDBC);
            con = DriverManager.getConnection(URL);
            String sql = "SELECT * FROM Goods";
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();

            // 遍历结果集，并将每行数据转换为Goods对象
            while (res.next()) {
                int id = res.getInt("gid");
                String name = res.getString("gname");
                String desc = res.getString("gdesc");
                int value = res.getInt("gvalue");
                String img = res.getString("gpic");
                int shelf = res.getInt("gshelf");
                int state = res.getInt("gstate");
                Goods Goods = new Goods(id, name, desc, value, img,shelf,state);
                GoodsList.add(Goods);
            }
        } catch (ClassNotFoundException e) {
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
        return GoodsList;
    }

    @Override
    public void deleteById(int gid) throws SQLException {
    }

    @Override
    public List<Goods> selectByshelf(int shelf) throws SQLException {
        List<Goods> GoodsList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            Class.forName(JDBC);
            con = DriverManager.getConnection(URL);
            String sql = "SELECT * FROM Goods where Gshelf = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, shelf);
            res = ps.executeQuery();
            // 遍历结果集，并将每行数据转换为Goods对象
            while (res.next()) {
                int id = res.getInt("gid");
                String name = res.getString("gname");
                String desc = res.getString("gdesc");
                int value = res.getInt("gvalue");
                String img = res.getString("gpic");
                int state = res.getInt("gstate");
                Goods Goods = new Goods(id, name, desc, value, img,shelf,state);
                GoodsList.add(Goods);
            }
        } catch (ClassNotFoundException e) {
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
        return GoodsList;
    }

    @Override
    public void updateShelf(int gid, int shelf) throws SQLException {
    	Connection con = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC);
            con = DriverManager.getConnection(URL);
            String sql = "UPDATE Goods SET Gshelf = ? WHERE gid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, shelf);
            ps.setInt(2, gid);
            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("货架设置成功。");
            } else {
                System.out.println("货架设置失败。");
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
    public void setState(int gid, int state) throws SQLException {
        // TODO 自动生成的方法存根
        Connection con = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC);
            con = DriverManager.getConnection(URL);
            String sql = "UPDATE Goods SET Gstate = ? WHERE gid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, state);
            ps.setInt(2, gid);
            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("状态设置成功。");
            } else {
                System.out.println("状态设置失败。");
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
}
