<%--
  Created by IntelliJ IDEA.
  User: xym
  Date: 2017/9/21
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>文件下载</title>
</head>
<body>

<h1>下载文件列表</h1>

<h5>第一种方式下载</h5>
<c:forEach items="${files}" var="file" varStatus="status">
    ${status.index +1} :<a href="/downloadfile?fileName=${file.name}">${file.name}</a>
    <br/>
</c:forEach>

<h5>第二种方式下载</h5>
<c:forEach items="${files}" var="file" varStatus="status">
    ${status.index +1} :<a href="/downloadfile2?fileName=${file.name}">${file.name}</a>
    <br/>
</c:forEach>

</body>
</html>
