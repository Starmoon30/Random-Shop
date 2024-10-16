<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Iterator" %>
    <%@page import="org.random_shop.vo.Goods" %>
        <%@page import="java.util.List" %>
            <html xmlns="http://www.w3.org/1999/xhtml">

            <head>
                <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                    <title>货架管理</title>
                    <style>
                        * {
                            box-sizing: border-box;
                            margin: 0;
                            padding: 0;
                        }

                        body {
                            display: flex;
                            flex-direction: column;
                            height: 100vh;
                        }

                        header {
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

                        .container {
                            display: flex;
                            justify-content: space-between;
                            padding: 20px;
                            /* 容器内边距 */
                        }
                        
                        .goods {
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                            overflow: hidden;
                            width: 300px;
                            /* 或者使用 flex-basis */
                            margin-top: 20px;
                        }

                        .goods-image {
                            width: 100%;
                            height: 200px;
                            object-fit: cover;
                        }

                        .goods-info {
                            padding: 1rem;
                        }

                        .goods-name {
                            font-size: 1.2rem;
                            font-weight: bold;
                            margin-bottom: 0.5rem;
                        }

                        .goods-description {
                            font-size: 0.9rem;
                            color: #666;
                            margin-bottom: 0.5rem;
                        }

                        .goods-price {
                            font-size: 1.1rem;
                            font-weight: bold;
                            color: #ff0000;
                            margin-bottom: 1rem;
                        }

                        .goods:last-child {
                            margin-right: 10px;
                            /* 最后一个商品的右边距 */
                        }

                        .ready,
                        .on,
                        .history {
                            display: flex;
                            justify-content: center;
                            /* 水平居中 */
                            align-items: start;
                        }
                        .on-button {
                            display: block;
                            width: 100%;
                            padding: 0.75rem;
                            background-color: #62a764;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            font-size: 1rem;
                            transition: background-color 0.3s;
                            text-align: center;
                            text-decoration: none;
                        }
                        .button{
                            display: flex;
                            flex-direction: row;
                            padding: 0;
                        }
                        .under-button,.fr-button{
                            display: block;
                            width: 50%;
                            padding: 0.75rem;
                            background-color: #62a764;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            font-size: 1rem;
                            transition: background-color 0.3s;
                            text-align: center;
                            text-decoration: none;
                            margin: 10px;
                        }
                        .on-button:hover,.under-button:hover,.fr-button:hover {
                            background-color: #457348;
                        }

                        .manage {
                            flex: 1;
                            background-color: beige;
                            text-align: center;
                            align-content: center;
                        }

                        a {
                            color: white;
                            text-decoration: none;
                        }

                        h1 {
                            color: white;
                            text-align: center;
                        }
                        h2 {
                            color: black;
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
                    </style>
            </head>

            <body>
                <header>
                    <h1>货架管理</h1>
                    <div class="link">
                        <a href="seller_login.jsp">返回登录页</a>
                        <a href="home.jsp">返回首页</a>
                        <a href="seller.jsp">返回</a>
                    </div>
                </header>
                <div class="container">
                    <div class="ready">
                        <h2>未上架</h2>
                        <% List<Goods> glist0 = (List<Goods>)session.getAttribute("ready");
                                if (glist0 != null) {
                                Goods g = null;
                                Iterator<Goods> gIter = null;
                                    gIter = glist0.iterator();
                                    while (gIter.hasNext()) {
                                    g = gIter.next();
                                    %>
                                    <div class="goods">
                                        <img src="<%=g.getGpic()%>" alt="商品1" class="goods-image">
                                        <div class="goods-info">
                                            <h2 class="goods-name">
                                                <%=g.getGname()%>
                                            </h2>
                                            <p class="goods-description">
                                                <%=g.getGdesc()%>
                                            </p>
                                            <p class="goods-price">¥<%=g.getGvalue()%>
                                            </p>
                                            <a href="<%=request.getContextPath()%>/goods?method=updateshelf&shelf=1&id=<%=g.getGid()%>" class="on-button" data-goods="<%=g.getGid()%>">立即上架</a>
                                        </div>
                                        <%} %>
                                    </div>
                                    <%} else{%>
                                        <h1>none</h1>
                                        <%}%>
                    </div>
                    <div class="on">
                        <h2>已上架</h2>
                        <% List<Goods> glist1 = (List<Goods>)session.getAttribute("on");
                                if (glist1 != null) {
                                Goods g = null;
                                Iterator<Goods> gIter = null;
                                    gIter = glist1.iterator();
                                    while (gIter.hasNext()) {
                                    g = gIter.next();
                                    %>
                                    <div class="goods">
                                        <img src="<%=g.getGpic()%>" alt="商品1" class="goods-image">
                                        <div class="goods-info">
                                            <h2 class="goods-name">
                                                <%=g.getGname()%>
                                            </h2>
                                            <p class="goods-description">
                                                <%=g.getGdesc()%>
                                            </p>
                                            <p class="goods-price">¥<%=g.getGvalue()%>
                                            </p>
                                            <%if (g.getGstate()==1){%>
                                            <p class="goods-state">冻结中
                                            </p>
                                            <%} %>
                                            <div class="button">
                                                <a href="<%=request.getContextPath()%>/goods?method=updateshelf&shelf=2&id=<%=g.getGid()%>" class="under-button" data-goods="<%=g.getGid()%>">立即下架</a>
                                                <%if (g.getGstate()==0){%>
                                                <a href="<%=request.getContextPath()%>/goods?method=updatestate&state=1&id=<%=g.getGid()%>" class="fr-button" data-goods="<%=g.getGid()%>">立即冻结</a>
                                                <%} 
                                                else if(g.getGstate()==1){%>
                                                <a href="<%=request.getContextPath()%>/goods?method=updatestate&state=0&id=<%=g.getGid()%>" class="fr-button" data-goods="<%=g.getGid()%>">立即解冻</a>
                                                <%}%>
                                            </div>
                                        </div>
                                        <%} %>
                                    </div>
                                    <%} else{%>
                                        <h1>none</h1>
                                        <%}%>
                    </div>
                    <div class="history">
                        <h2>历史货架</h2>
                        <% List<Goods> glist2 = (List<Goods>)session.getAttribute("history");
                                if (glist2 != null) {
                                Goods g = null;
                                Iterator<Goods> gIter = null;
                                    gIter = glist2.iterator();
                                    while (gIter.hasNext()) {
                                    g = gIter.next();
                                    %>
                                    <div class="goods">
                                        <img src="<%=g.getGpic()%>" alt="商品1" class="goods-image">
                                        <div class="goods-info">
                                            <h2 class="goods-name">
                                                <%=g.getGname()%>
                                            </h2>
                                            <p class="goods-description">
                                                <%=g.getGdesc()%>
                                            </p>
                                            <p class="goods-price">¥<%=g.getGvalue()%>
                                            </p>
                                            <a href="<%=request.getContextPath()%>/goods?method=updateshelf&shelf=1&id=<%=g.getGid()%>" class="on-button" data-goods="<%=g.getGid()%>">立即上架</a>
                                        </div>
                                        <%} %>
                                    </div>
                                    <%} else{%>
                                        <h1>none</h1>
                                        <%}%>
                    </div>
                </div>
            </body>

            </html>