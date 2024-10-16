package org.random_shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.random_shop.vo.Goods;
import org.random_shop.vo.Order;

public class OrderDaoImpl implements OrderDao {

	public static final String URL = "jdbc:sqlite:D:\\Eclipse\\random-shop\\src\\db\\random-shop.db";
	public static final String JDBC = "org.sqlite.JDBC";

	@Override
	public List<Order> showOrdersWithGoods() throws SQLException {
		List<Order> orderList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(URL);
			String sql = "SELECT \"O\".OID, \"O\".ODesc, \"O\".OState, \"G\".GName, \"G\".GDesc, \"G\".GValue FROM \"Order\" AS \"O\" INNER JOIN \"Goods\" AS \"G\" ON \"O\".GID = \"G\".GID;";
			ps = con.prepareStatement(sql);
			res = ps.executeQuery();

			// 遍历结果集，并将每行数据转换为Order对象
			while (res.next()) {
				int oid = res.getInt("OID");
				String oDesc = res.getString("ODesc");
				int oState = res.getInt("OState");
				String gName = res.getString("GName");
				String gDesc = res.getString("GDesc");
				int gvalue = res.getInt("GValue");
				Order order = new Order(oid, oDesc, gName, gDesc, gvalue,oState);
				orderList.add(order);
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
		return orderList;
	}

	@Override
	public void updateOrderState(int oid, int ostate) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(JDBC);
			con = DriverManager.getConnection(URL);
			String sql = "UPDATE \"Order\" SET OState = ? WHERE OID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, ostate);
			ps.setInt(2, oid);
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
	public void addOrder(int id,String desc) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(JDBC);
			con = DriverManager.getConnection(URL);
			String sql = "INSERT into \"Order\" (gid,odesc) values (?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, desc);
			int updatedRows = ps.executeUpdate();
			if (updatedRows > 0) {
				System.out.println("订单申请成功。");
			} else {
				System.out.println("订单申请失败。");
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
	public void selectById(int gid) throws SQLException {
		// TODO 自动生成的方法存根

	}

}