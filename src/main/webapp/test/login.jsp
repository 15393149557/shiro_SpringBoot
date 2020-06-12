<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shiro测试页面</title>
</head>
<body>

    <div align="center" >
        <h1>这是登录页面</h1>

        <form action="${path}/user/login" method="post">
            用户名：<input name="username" type="text"/><br><br>
            密&emsp;码：<input name="password" type="password"/><br><br>
            <button type="submit"  >点我登录</button><br><br>
        </form>
    </div>


</body>
</html>