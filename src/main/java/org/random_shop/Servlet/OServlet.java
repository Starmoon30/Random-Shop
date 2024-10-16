package org.random_shop.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.random_shop.dao.GoodsDao;
import org.random_shop.dao.GoodsDaoImpl;
import org.random_shop.vo.Goods;
import org.random_shop.dao.OrderDao;
import org.random_shop.dao.OrderDaoImpl;
import org.random_shop.dao.SellerDao;
import org.random_shop.dao.SellerDaoImpl;
import org.random_shop.vo.Order;
import org.random_shop.vo.Seller;

public class OServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        if (method.equals("show")) {
        	try {
				List<Order> olist = show(request, response);
				session.setAttribute("olist", olist);
                response.sendRedirect("order_manage.jsp");
			} catch (ServletException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }
        else if(method.equals("updatestate")) {
        	int state = Integer.parseInt(request.getParameter("state"));
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
				updatestate(request, response,id,state);
				List<Order> olist = show(request, response);
				session.setAttribute("olist", olist);
                response.sendRedirect("order_manage.jsp");
			} catch (ServletException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }
        else if(method.equals("add")) {
        	int id = Integer.parseInt(request.getParameter("goodsID"));
        	String desc = request.getParameter("Desc");
        	try {
				add(request, response,id,desc);
                response.sendRedirect("buyer.jsp");
			} catch (ServletException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }
}
    protected List<Order> show(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        OrderDao o = new OrderDaoImpl();
        List<Order> olist = o.showOrdersWithGoods();

        return olist;
    }
    protected void updatestate(HttpServletRequest request, HttpServletResponse response,int id,int state)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        OrderDao o = new OrderDaoImpl();
        o.updateOrderState(id, state);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response,int id,String desc)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        OrderDao o = new OrderDaoImpl();
        o.addOrder(id, desc);
    }
}
