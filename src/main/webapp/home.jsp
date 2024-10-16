<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.io.File"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>首页</title>
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }
            body{
                display: flex;
                height: 100vh;
                flex-direction: column;
            }
            a {
                color: white;
                text-align: center;
                text-decoration: none;
            }
            h1{
                color: white;
                text-align: center;
            }
            header {
                display: flex;
                background-color: #059530;
                height: 100px;
                padding: 1rem;
                justify-content: center;
                text-align: center;
                align-items: center;
            }

            .enter {
                display: flex;
                flex: 1;
                background-color: beige;
                align-items: center;
                justify-content: center;
                
            }
            .choose{
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 30%;
            }
            .button {
                display: inline-block;
                /* 使链接表现为块级元素 */
                padding: 10px 20px;
                /* 设置内边距 */
                font-size: 32px;
                /* 设置字体大小 */
                color: #fff;
                /* 设置文字颜色 */
                background-color: #4CAF50;
                /* 设置背景颜色 */
                border: none;
                /* 去掉边框 */
                border-radius: 5px;
                /* 设置圆角 */
                transition: background-color 0.3s, box-shadow 0.3s;
                /* 设置过渡效果 */
                cursor: pointer;
                /* 设置鼠标指针为手形 */
                box-shadow: 0 2px 3px rgba(0, 0, 0, 0.2);
                /* 设置阴影 */
                width: 200px;
                height: 60px;
            }

            .button:hover {
                background-color: #45a049;
                /* 鼠标悬停时的背景颜色 */
                box-shadow: 0 4px 5px rgba(0, 0, 0, 0.3);
                /* 鼠标悬停时的阴影 */
            }
        </style>
</head>

<body>
    <header>
        <h1>随心商城</h1>
    </header>
    <div class="enter">
        <div class="choose">
            <a href="<%=request.getContextPath()%>/goods?method=show" class="button">买家平台</a><br>
            <a href="seller_login.jsp" class="button">卖家平台</a>
        </div>
    </div>
</body>

</html>