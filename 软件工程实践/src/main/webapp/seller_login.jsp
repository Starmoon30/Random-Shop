<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>卖家登录平台</title>
</head>
<body>
    <h1>卖家登录平台</h1>
    <form action="seller.jsp" method="post">
        账号：<input type="text" id="account" name="account" required><br>
        密码：<input type="password" id="pwd" name="pwd" required><br>
        <input type="submit" id="btn_submit" value="登录">
    </form>
</body>
</html>