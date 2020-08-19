<%@ page import="com.github.javaweb.data.Demployee" %><%-- Created by IntelliJ IDEA. --%>
<%@ page import="com.github.javaweb.*" %>
<%@ page import="com.github.javaweb.data.Data" %>
<%@ page import="com.github.javaweb.data.Djob" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.github.javaweb.data.Ddept" %>
<%@ page import="com.github.javaweb.util.Condition" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <style>
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
      a:hover{
        transition-duration: 1s;
        transition-property: all;
        color: cadetblue;
      }

    </style>
    <title>主页</title>

  </head>
  <body>
    <%
      Map<Integer, Djob> jobMap = Djob.getMap();
      request.setAttribute("jobMap",jobMap);
      Map<Integer, Ddept> deptMap = Ddept.getMap();
      request.setAttribute("deptMap",deptMap);
      Condition cd = new Condition(request.getParameter("id"),request.getParameter("name"),request.getParameter("jobId"),request.getParameter("admindEmployeeId"),request.getParameter("deptId"));
      request.setAttribute("employee",Demployee.getMap(cd));
      request.setAttribute("empAll",Demployee.getMap());
    %>
    <c:import url="NavigationBar.jsp"/>
    <form action="index.jsp" method="post">
      工号
      <input name="id" type="text">
      名字
      <input name="name" type="text">
      职业
      <select id="job" name="jobId">
        <option value="" selected="true">请选择工作</option>
        <c:forEach var="job" items="${jobMap}">
          <option value="${job.key}">${job.value.getName()}</option>
        </c:forEach>
      </select>
      管理人
      <select id="admindEmployee" name="admindEmployeeId">
        <option value="" selected="true">选择管理者</option>
        <c:forEach var="dept" items="${empAll}">
          <option value="${dept.key}">${dept.value.getDjob().getName()} >> ${dept.value.getName()}</option>
        </c:forEach>
      </select>
      部门
      <select id="dept" name="deptId">
        <option value="" selected="true">选择部门</option>
        <c:forEach var="dept" items="${deptMap}">
          <option value="${dept.key}">${dept.value.getName()}</option>
        </c:forEach>
      </select>
      <input value="查找" type="submit">
      <input value="重置" type="reset">
    </form>
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
      <c:forEach var="empe" items="${employee}">
        <tr>
            <td>${empe.value.getId()}</td>
            <td>${empe.value.getName()}</td>
            <td>${empe.value.getDjob().getName()}</td>
            <td>${empe.value.getDjob().getDdept().getName()}</td>
            <td>${empe.value.getAdminId()}</td>
            <td>${empe.value.getMoney() + empe.value.getDjob().getMoney()}</td>
            <td>${empe.value.getMsg()}</td>
            <td><a href="javascript:void(0)" onclick="a_post(${empe.value.getId()},'/EditEmployeeServlet','del')">离职</a><br><a href="javascript:void(0)" onclick="a_post(${empe.value.getId()},'/editEmployee_edit.jsp','edit')" >修改</a></td>
        </tr>
      </c:forEach>
      
    </table>
    <script>
      function a_post(id,url,type) {
        var form = document.createElement("form");
        form.action = url+"?editType="+type+"&uid="+id;
        form.method = "post"
        form.style.display = "none"
        document.body.appendChild(form);
        form.submit();
      }
    </script>
  </body>
</html>