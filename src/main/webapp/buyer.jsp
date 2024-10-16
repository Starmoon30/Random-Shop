<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Iterator"%>
<%@page import="org.random_shop.vo.Goods"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>买家平台</title>
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            a {
                color: white;
                text-decoration: none;
            }

            body {
                display: flex;
                height: 100vh;
                flex-direction: column;
            }

            .container {
                flex: 1;
                background-color: beige;
            }

            header {
                background-color: #059530;
                color: white;
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

            h1 {
                text-align: center;
            }

            h2 {
                margin-bottom: 1rem;
            }

            .shelf {
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .goods {
                margin-top: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                width: 300px;
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

            .buy-button {
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

            .buy-button:hover {
                background-color: #457348;
            }

            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }

            .modal-content {
                background-color: white;
                margin: 15% auto;
                padding: 2rem;
                border-radius: 8px;
                width: 80%;
                max-width: 500px;
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            label {
                margin-bottom: 0.5rem;
                color: #555;
            }

            input {
                padding: 0.5rem;
                margin-bottom: 1rem;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            button[type="submit"] {
                padding: 0.75rem;
                background-color: #62a764;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
                transition: background-color 0.3s;
            }

            button[type="submit"]:hover {
                background-color: #457348;
            }
        </style>
</head>
<body>
    <header>
        <h1>商品浏览</h1>
        <div class="link">
            <a href="home.jsp">返回首页</a>
        </div>
    </header>
    <div class="container">
        <div class="shelf" id="shelf">
<%
	List<Goods> glist = (List<Goods>)session.getAttribute("glist");
	if (glist != null) {
		Goods g = null;
		Iterator<Goods> gIter = null;
        gIter = glist.iterator();
		while (gIter.hasNext()) {
			g = gIter.next();
		%>
        <div class="goods">
            <img src="<%=g.getGpic()%>" alt="商品1" class="goods-image">
            <div class="goods-info">
            <h2 class="goods-name"><%=g.getGname()%></h2>
            <p class="goods-description"><%=g.getGdesc()%></p>
            <p class="goods-price">¥<%=g.getGvalue()%></p>
            <%if (g.getGstate()==1){%>
                <p class="goods-state">冻结中
                </p>
            	<a href="#" class="buy-button" data-goods="<%=g.getGid()%>">申请候补</a>
                <%}
            else if(g.getGstate()==0){%>
                <a href="#" class="buy-button" data-goods="<%=g.getGid()%>">立即购买</a>
                <%}%>
         </div>
            <%} %>
        </div>
        <%}
        else{%>
            <h1>none</h1>
        <%}%>
    </div>
    <div id="buyModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>提交购买信息</h2>
            <form action="<%=request.getContextPath()%>/order?method=add" method="post">
           		<input type="hidden" id="goodsID" name="goodsID" value="">
                <label for="Desc">备注信息</label>
                <input type="text" id="Desc" name="Desc" required>
                <button type="submit">提交</button>
            </form>
        </div>
    </div>

    <script>
        const modal = document.getElementById("buyModal");
        const buyButtons = document.querySelectorAll(".buy-button");
        const closeBtn = document.querySelector(".close");
        const buyForm = document.getElementById("buyForm");
        const goodsID = document.getElementById("goodsID");

        buyButtons.forEach(button => {
            button.addEventListener("click", (e) => {
                e.preventDefault();
                modal.style.display = "block";
                goodsID.value = button.getAttribute("data-goods");
            });
        });

        closeBtn.addEventListener("click", () => {
            modal.style.display = "none";
        });

        window.addEventListener("click", (e) => {
            if (e.target === modal) {
                modal.style.display = "none";
            }
        });

        buyForm.addEventListener("submit", (e) => {
            e.preventDefault();
            
            alert("购买申请已提交");
            modal.style.display = "none";
        });
    </script>
</body>
</html>