<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/19
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body{
            padding: 50px;
        }
    </style>
</head>
<body>
    <c:import url="NavigationBar.jsp"></c:import>
    <div>
        <h1>
            ${sessionScope.msg}
        </h1>
    </div>
</body>
</html>
