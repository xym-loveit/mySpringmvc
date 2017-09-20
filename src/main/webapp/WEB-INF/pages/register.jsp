<%--
  Created by IntelliJ IDEA.
  User: xym
  Date: 2017/9/20
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<form method="post" action="/user/register" align="center">
    <p>
        ${ERR_userName}
        <span>用户名：</span><input name="userName" type="text"/>
    </p>
    <p>
        ${ERR_password}
        <span>密  码：</span><input name="password" type="password"/>
    </p>
    <p>
        ${ERR_age}
        <span>年 龄：</span><input name="age" type="text"/>
    </p>
    <p>
        ${ERR_realName}
        <span>真实姓名：</span><input name="realName" type="text"/>
    </p>
    <p>
        ${ERR_email}
        <span>邮 箱：</span><input name="email" type="text"/>
    </p>
    <p>
        <input type="submit" value="注册"/>
    </p>

</form>
</body>
</html>
