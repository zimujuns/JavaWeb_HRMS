<%@ page import="com.github.javaweb.pojo.Demployee" %><%-- Created by IntelliJ IDEA. --%>
<%@ page import="com.github.javaweb.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>主页</title>
  </head>
  <body>
    <%
      session.setAttribute("employee",Demployee.getMap());
    %>
    <table>
      <tr>
        <td>职员号</td>
        <td>名字</td>
        <td>当前职位</td>
        <td>所属部门</td>
        <td>领导职员号</td>
        <td>工薪</td>
        <td>备注</td>
        <td>操作</td>
      </tr>

      <tr>
        <c:forEach var="empe" items="${sessionScope.employee}">
          <td>${empe.id}</td>
          <td>${empe.name}</td>
          <td>${empe.djob.name}</td>
          <td>${empe.djob.ddept.name}</td>
          <td>${empe.adminId}</td>
          <td>${empe.money + empe.djob.money}</td>
          <td>${empe.msg}</td>
          <td><a>离职</a><br><a>修改</a></td>
        </c:forEach>
      </tr>
    </table>
  </body>
</html>