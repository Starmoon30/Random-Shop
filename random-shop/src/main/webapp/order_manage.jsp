<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Iterator"%>
<%@page import="org.random_shop.vo.Order"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>订单管理</title>
<style>
    * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body{
            display: flex;
            flex-direction: column;
            height: 100vh;
        }
    header{
        background-color: #059530;
        color: rgb(161, 157, 233);
        padding: 1rem;
        height: 100px;
        position: relative;
    }
    .link {
                position: absolute;
                /* 绝对定位 */
                bottom: 0;
                /* 距离底部0，即底部对齐 */
                left: 0;
                /* 距离左侧0，即左侧对齐 */
                margin-bottom: 1rem;
            }
    .manage{
        flex: 1;
        background-color: beige;
    }
    a{
        color: white;
        text-decoration: none;
        }
    h1{
        color: white;
        text-align: center;
    }
    input {
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
    label {
        margin-bottom: 0.5rem;
        color: #555;
    }
    table{
        text-align: center;
        align-content: center;
    }
</style>
</head>
<body>
    <header>
        <h1>订单管理</h1>
        <div class="link">
            <a href="seller_login.jsp">返回登录页</a>
            <a href="home.jsp">返回首页</a>
            <a href="seller.jsp">返回</a>
        </div>
    </header>
    <div class="manage">
        <table border="1">
            <tr>
                <td>订单号</td>
                <td>备注信息</td>
                <td>商品名</td>
                <td>商品描述</td>
                <td>商品价格</td>
                <td>订单状态</td>
                <td>操作</td>
            </tr>
<%
	List<Order> olist = (List<Order>)session.getAttribute("olist");
	if (olist != null) {
		Order o = null;
		Iterator<Order> oIter = null;
        oIter = olist.iterator();
		while (oIter.hasNext()) {
			o = oIter.next();
		%>
            <tr>
                <td><%=o.getOid()%></td>
                <td><%=o.getoDesc()%></td>
                <td><%=o.getgName()%></td>
                <td><%=o.getgDesc()%></td>
                <td>¥<%=o.getGvalue()%></td>
                <td><%if(o.getOstate()==0){%>
                    未接受</td>
                <td>
                    <a href="<%=request.getContextPath()%>/order?method=updatestate&state=1&id=<%=o.getOid()%>" style="color: blue;">接受</a>
                </td>
                    <%}else if(o.getOstate()==1){%>
                    已接受</td>
                <td>
                    <a href="<%=request.getContextPath()%>/order?method=updatestate&state=0&id=<%=o.getOid()%>" style="color: blue;">取消接受</a>
                </td>
                    <%}%>
            </tr>
            <%} %>
        </div>
        <%}
        else{%>
            <h1>none</h1>
        <%}%>
        </table>
    </div>
</body>
</html>