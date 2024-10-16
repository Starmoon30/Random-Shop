<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.random_shop.vo.Seller"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>账号管理</title>
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
        background-color: beige;
        text-align: center;
        align-content: center;
        height: 90vh;
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
</style>
</head>
<body>
<% 
	Seller seller = (Seller)session.getAttribute("seller");
	String error = (String)session.getAttribute("error");
	%>
    <header>
        <h1>账号管理</h1>
        <div class="link">
            <a href="seller_login.jsp">返回登录页</a>
            <a href="home.jsp">返回首页</a>
            <a href="seller.jsp">返回</a>
        </div>
    </header>
    <div class="manage">
        <form action="<%=request.getContextPath()%>/seller?method=updatePwd" method="post">
            <p>当前帐号：<%=seller.getAccount()%></p><br>
            <label>旧密码：<input type="text" name="old_pwd" required></label><br>
            <label>新密码：<input type="text" name="new_pwd" required></label><br>
            <input type="submit" class="submit" value="提交">
        </form>
    </div>
<script>
    function all() {
        var error = "<%=error%>"
        if (error === "3") {
        	alert("修改失败，原密码错误");
        }
    }
    window.onload = all;
</script>
</body>
</html>