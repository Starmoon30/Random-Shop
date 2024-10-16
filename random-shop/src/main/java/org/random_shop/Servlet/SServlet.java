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
import org.random_shop.dao.SellerDao;
import org.random_shop.dao.SellerDaoImpl;
import org.random_shop.vo.Goods;
import org.random_shop.vo.Seller;

public class SServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SServlet() {
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
        if (method.equals("login")) {
            String account = request.getParameter("account");
            String pwd = request.getParameter("pwd");
            try {
                Seller seller = getSeller(request, response, account);
                if (seller != null) {
                    if (pwd.equals(seller.getPwd())) {
                        session.setAttribute("seller", seller);
                        response.sendRedirect("seller.jsp");
                    } else {
                    	session.setAttribute("error", "2");
                        response.sendRedirect("seller_login.jsp");
                    }
                } else {
                    session.setAttribute("error", "2");
                    response.sendRedirect("seller_login.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (method.equals("updatePwd")) {
            Seller seller = (Seller) session.getAttribute("seller");
            String old_pwd = request.getParameter("old_pwd");
            String new_pwd = request.getParameter("new_pwd");
            if (old_pwd.equals(seller.getPwd())) {
                try {
                    updatePwd(request, response, seller.getAccount(), new_pwd);
//                    request.getSession().invalidate();
                    session.setAttribute("error", "1");
                    response.sendRedirect("seller_login.jsp");
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                session.setAttribute("error", "3");
                response.sendRedirect("account_manage.jsp");
            }
        }
    }

    protected Seller getSeller(HttpServletRequest request, HttpServletResponse response, String account)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        SellerDao s = new SellerDaoImpl();
        Seller seller = s.selectByAccount(account);

        return seller;
    }

    protected void updatePwd(HttpServletRequest request, HttpServletResponse response, String account, String new_pwd)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        SellerDao s = new SellerDaoImpl();
        s.updatePwd(account, new_pwd);
    }
}
