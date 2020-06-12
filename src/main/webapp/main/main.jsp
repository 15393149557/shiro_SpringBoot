<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shiro测试页面</title>
</head>
<body>
<div align="center">
    <h1>欢迎来到主页面</h1>
</div>
<div align="right">

    <%--没有认证展示--%>
    <shiro:notAuthenticated>
        如果您想浏览更多详情请<a href="${path}/test/login.jsp">登录</a>
    </shiro:notAuthenticated>

    <shiro:authenticated>
        您好：<span style="color: red"><strong><shiro:principal/></strong></span>  欢迎来到主页面，
        <a href="${path}/user/logout">退出</a>

        <div align="left">
            <ul>
                    <%--判断该主体是否含有该角色--%>
                <shiro:hasRole name="user">
                    <li>轮播图管理</li><br>
                    <li>专辑管理</li><br>
                    <li>上师管理</li><br>
                </shiro:hasRole>

                    <%--判断该主体是否含有这些角色--%>
                <shiro:hasAnyRoles name="admin">
                    <li>用户管理</li><br>
                    <%--判断该主体是否含有该权限--%>
                    <shiro:hasPermission name="user:query">
                        <button >查</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:insert">
                        <button >增</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete">
                        <button >删</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update">
                        <button >改</button>
                    </shiro:hasPermission>
                </shiro:hasAnyRoles>

                    <%--判断该主体是否含有该角色--%>
                <shiro:hasRole name="super">
                    <li>管理员管理</li>
                </shiro:hasRole>
            </ul>
        </div>
    </shiro:authenticated>

</div>
</body>
</html>