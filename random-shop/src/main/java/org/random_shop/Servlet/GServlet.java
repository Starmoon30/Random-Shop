package org.random_shop.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.random_shop.dao.GoodsDao;
import org.random_shop.dao.GoodsDaoImpl;
import org.random_shop.vo.Goods;

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
        		
				if(updateshelf(request, response,id,shelf)==1) {
					List<Goods> glist0 = manageGoods(request, response,0);
	                List<Goods> glist1 = manageGoods(request, response,1);
	                List<Goods> glist2 = manageGoods(request, response,2);
	                session.setAttribute("ready", glist0);
	                session.setAttribute("on", glist1);
	                session.setAttribute("history", glist2);
					response.sendRedirect("shelf_manage.jsp?error=1");
				}
				else {
					List<Goods> glist0 = manageGoods(request, response,0);
	                List<Goods> glist1 = manageGoods(request, response,1);
	                List<Goods> glist2 = manageGoods(request, response,2);
	                session.setAttribute("ready", glist0);
	                session.setAttribute("on", glist1);
	                session.setAttribute("history", glist2);
					response.sendRedirect("shelf_manage.jsp");
				}
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
        else if(method.equals("add")) {
        	if (!ServletFileUpload.isMultipartContent(request)) {
                response.getWriter().println("Error: Form must has enctype=multipart/form-data.");
                return;
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            String name = "";
            String desc = "";
            int value = 0;

            try {
            	RequestContext context = new ServletRequestContext(request);
            	byte[] fileContent = new byte[0];
                // 解析请求的内容提取文件数据
                List<FileItem> items = upload.parseRequest(context);
                if (items != null && !items.isEmpty()) {
                    for (FileItem item : items) {
                        if (item.isFormField()) {
                            // 处理文本字段
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();
                            if(fieldName.equals("name")) {
                            	name = fieldValue;
                            }
                            if(fieldName.equals("desc")) {
                            	desc = fieldValue;
                            }
                            if(fieldName.equals("value")) {
                            	value = Integer.parseInt(fieldValue);
                            }
                        } else {
                            // 处理文件字段
                            String fileName = item.getName();
                            fileContent = getFileBytes(item.getInputStream());
                        }
                    }
                    String strname = new String(name.getBytes("ISO-8859-1"), "UTF-8");
                    String strdesc = new String(desc.getBytes("ISO-8859-1"), "UTF-8");
                    addGoods(request, response, strname, strdesc, value, fileContent);
                    response.sendRedirect("goods_manage.jsp");
                }
            } catch (Exception ex) {
                throw new ServletException("Cannot parse multipart request.", ex);
            }
        }
    }
    private byte[] getFileBytes(InputStream inputStream) throws IOException {
            byte[] buffer = new byte[1024];
            int bytesRead;
            int totalBytesRead = 0;
            byte[] fileBytes = new byte[0];

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                totalBytesRead += bytesRead;
                byte[] newFileBytes = new byte[totalBytesRead];
                System.arraycopy(fileBytes, 0, newFileBytes, 0, fileBytes.length);
                System.arraycopy(buffer, 0, newFileBytes, fileBytes.length, bytesRead);
                fileBytes = newFileBytes;
            }
            return fileBytes;
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
    protected int updateshelf(HttpServletRequest request, HttpServletResponse response,int id,int shelf)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");
        GoodsDao goods = new GoodsDaoImpl();
        int error = 0;
        if(shelf!=1||goods.countByshelf(1)!=1) {
        	goods.updateShelf(id,shelf);	
        }
        else {
        	error = 1;        	
        }
		return error;
    }
    protected void updatestate(HttpServletRequest request, HttpServletResponse response,int id,int state)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        GoodsDao goods = new GoodsDaoImpl();
        goods.setState(id,state);
    }
    protected void addGoods(HttpServletRequest request, HttpServletResponse response,String name,String desc,int value,byte[] img)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("utf-8");

        GoodsDao goods = new GoodsDaoImpl();
        goods.addGoods(name,desc,value,img);
    }
}
