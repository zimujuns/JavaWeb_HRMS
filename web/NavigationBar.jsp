<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/19
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <style>
            ul{
                background-color: coral;
                padding: 10px 10px 10px 20px;
            }
            li{
                font-size: 18px;
                font-weight: 900;
                margin-right: 100px;
                float: left
            }
        </style>
    </head>
    <body>
        <ul style="list-style-type: none;overflow: hidden">
            <li><a href="index.jsp">首页</a></li>
            <li><a href="editEmployee_add.jsp">添加人员</a></li>
            <li><a href="index.jsp">编辑部门</a></li>
        </ul>
    </body>
</html>
