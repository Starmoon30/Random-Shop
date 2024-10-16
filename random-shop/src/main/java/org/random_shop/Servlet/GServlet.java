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
import org.random_shop.vo.Order;

public class GServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GServlet() {
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
                List<Goods> glist = buyGoods(request, response);
                session.setAttribute("glist", glist);
                response.sendRedirect("buyer.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (method.equals("showall")) {
        	try {
                List<Goods> glist0 = manageGoods(request, response,0);
                List<Goods> glist1 = manageGoods(request, response,1);
                List<Goods> glist2 = manageGoods(request, response,2);
                session.setAttribute("ready", glist0);
                session.setAttribute("on", glist1);
                session.setAttribute("history", glist2);
                response.sendRedirect("shelf_manage.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(method.equals("updateshelf")){
        	int shelf = Integer.parseInt(request.getParameter("shelf"));
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
        		updateshelf(request, response,id,shelf);
				List<Goods> glist0 = manageGoods(request, response,0);
                List<Goods> glist1 = manageGoods(request, response,1);
                List<Goods> glist2 = manageGoods(request, response,2);
                session.setAttribute("ready", glist0);
                session.setAttribute("on", glist1);
                session.setAttribute("history", glist2);
                response.sendRedirect("shelf_manage.jsp");
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
        else if(method.equals("updatestate")){
        	int state = Integer.parseInt(request.getParameter("state"));
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
        		updatestate(request, response, id, state);
				List<Goods> glist0 = manageGoods(request, response,0);
                List<Goods> glist1 = manageGoods(request, response,1);
                List<Goods> glist2 = manageGoods(request, response,2);
                session.setAttribute("ready", glist0);
                session.setAttribute("on", glist1);
                session.setAttribute("history", glist2);
                response.sendRedirect("shelf_manage.jsp");
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

    protected List<Goods> buyGoods(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");
        // 后端提取数据，传入顾客

        GoodsDao goods = new GoodsDaoImpl();
        List<Goods> glist = goods.selectByshelf(1);

        return glist;
    }
    protected List<Goods> manageGoods(HttpServletRequest request, HttpServletResponse response,int shelf)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        GoodsDao goods = new GoodsDaoImpl();
        List<Goods> glist = goods.selectByshelf(shelf);

        return glist;
    }
    protected void updateshelf(HttpServletRequest request, HttpServletResponse response,int id,int shelf)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        GoodsDao goods = new GoodsDaoImpl();
        goods.updateShelf(id,shelf);
    }
    protected void updatestate(HttpServletRequest request, HttpServletResponse response,int id,int state)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        GoodsDao goods = new GoodsDaoImpl();
        goods.setState(id,state);
    }
}
