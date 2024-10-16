<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>卖家平台</title>
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            body {
                display: flex;
                height: 100vh;
                flex-direction: column;
            }

            h1 {
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

            a {
                color: white;
                text-align: center;
                text-decoration: none;
            }

            .content {
                display: flex;
                flex: 1;
                background-color: beige;
                align-items: center;
                justify-content: center;
            }

            .choose {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 50%;
                text-align: center;
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
        <h1>卖家管理平台</h1><br>
        <div class="link">
            <a href="seller_login.jsp">返回登录页</a>
            <a href="home.jsp">返回首页</a>
        </div>
    </header>
    <div class="content">
        <div class="choose">
            <a href="goods_manage.jsp" class="button">商品管理</a><br>
            <a href="<%=request.getContextPath()%>/goods?method=showall" class="button">货架管理</a><br>
            <a href="<%=request.getContextPath()%>/order?method=show" class="button">订单管理</a><br>
            <a href="account_manage.jsp" class="button">账号管理</a>
        </div>
    </div>
</body>

</html>