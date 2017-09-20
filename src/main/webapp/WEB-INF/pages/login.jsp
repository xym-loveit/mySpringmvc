<%--
  Created by IntelliJ IDEA.
  User: xym
  Date: 2017/9/20
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>

<form method="post" action="/u/login" align="center">
    <p>
        用户名：<input type="text" name="username"/>
    </p>
    <p>
        密码：<input type="password" name="password"/>
    </p>
    <p>
        <input type="submit" value="登录">
    </p>
    <p><span style="color: red;">${error}</span></p>
</form>

</body>
</html>
