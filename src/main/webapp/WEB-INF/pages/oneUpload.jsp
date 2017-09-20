<%--
  Created by IntelliJ IDEA.
  User: xym
  Date: 2017/9/21
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单文件上传</title>
</head>
<body>

<form method="post" enctype="multipart/form-data" action="/oneUpload">
    <input type="file" name="imgfile"/>
    <input type="submit" value="上传"/>
</form>

<a href="/downindex">文件下载</a>

</body>
</html>
