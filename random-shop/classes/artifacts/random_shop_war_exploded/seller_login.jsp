<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>卖家登录平台</title>
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
            }

            .login {
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

            .reset {
                width: 100%;
                margin: 0px 0px 17px;
                padding: 8.5px;
                background-color: #f0f0f0;
                color: black;
			    border: 1px solid #ddd;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
                transition: background-color 0.3s;
                text-align: center;
                text-decoration: none;
            }
        </style>
</head>

<body>
    <% String error=request.getParameter("error"); %>
        <header>
            <h1>卖家登录平台</h1>
            <a href="home.jsp">返回首页</a>
        </header>
        <div class="login">
            <p>默认账号：123</p>
            <p>默认密码：123</p>
            <form action="<%=request.getContextPath()%>/seller?method=login" method="post">
                <label>账号<input type="text" name="account" required></label><br>
                <label>密码<input type="password" name="pwd" required></label><br><br>
                <input type="submit" class="submit" value="登录">
                <a href="<%=request.getContextPath()%>/seller?method=resetpwd" class="reset">重置密码</a>
            </form>
        </div>
        <script>
            function clearErrorSession() {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "ClearErrorSessionServlet", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // 会话已清除，可以在这里重定向或更新页面
                        window.location.reload(true); // 重新加载页面
                    }
                };
                xhr.send();
            }
            function a() {
                alert("密码修改成功，请重新登录！");
                clearErrorSession();
            }
            function b() {
                alert("登录失败，账号或密码错误！");
                clearErrorSession();
            }
            function c() {
                alert("密码重置成功！");
                clearErrorSession();
            }
            function all() {
                var error = "<%=error%>"
                if (error === "1") {
                    a();
                } else if (error === "2") {
                    b();
                } else if (error === "3") {
                    c();
                }
            }
            window.onload = all;
        </script>
</body>

</html>