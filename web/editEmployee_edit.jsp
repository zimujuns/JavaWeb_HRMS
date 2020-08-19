<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.github.javaweb.data.Demployee" %>
<%@ page import="com.github.javaweb.data.Djob" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/20
  Time: 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑员工</title>
    <style>
        input,select{
            display: block;
        }
        body{
            padding: 50px;
        }
        td{
            font-family: 微軟正黑體;
            font-size: 18px;
            font-weight: 900;
            text-align: center;
            width: 180px;
            border: 1px black solid;
        }
    </style>
</head>
<body>
    <%
        int uid = Integer.parseInt(request.getParameter("uid"));
        Demployee demployee = Demployee.getMap().get(uid);
        request.setAttribute("demployee",demployee);
        request.setAttribute("jobId", Djob.getMap());
        request.setAttribute("dempAll",Demployee.getMap());
    %>
    <c:import url="NavigationBar.jsp"/>
    <table>
        <tr>
            <td>名称</td>
            <td>职业</td>
            <td>管理人</td>
            <td>额外薪水</td>
            <td>备注</td>
        </tr>
        <tr>
            <td>${demployee.getName()}</td>
            <td>${demployee.djob.getName()}</td>
            <td>${demployee.getAdmin()}</td>
            <td>${demployee.getMoney()}</td>
            <td>${demployee.getMsg()}</td>
        </tr>
    </table>
    <form action="/EditEmployeeServlet?editType=edit&oldId=${demployee.getId()}" method="post">
        名称修改<input name="name" type="text">
        职业修改
        <select name="jobId">
            <option value="" selected="true">请选择工作</option>
            <c:forEach var="job" items="${jobId}">
                <option value="${job.key}" >${job.value.getName()}</option>
            </c:forEach>
        </select>
        管理人修改
        <select id="admindEmployee" name="adminId">
            <option value="" selected="true">选择管理者</option>
            <c:forEach var="demployee" items="${dempAll}">
                <option value="${demployee.key}">${demployee.value.getDjob().getName()} >> ${demployee.value.getName()}</option>
            </c:forEach>
        </select>
        额外薪水<input name="money" type="number" value="0">
        备注<input name="msg" type="text" min="0" max="100" alt="限制100字符">
        <input value="提交" type="submit">
    </form>


</body>
</html>
