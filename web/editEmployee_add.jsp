<%@ page import="java.util.Map" %>
<%@ page import="com.github.javaweb.data.Djob" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.github.javaweb.data.Ddept" %>
<%@ page import="com.github.javaweb.data.Demployee" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/19
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>入职人员</title>
    <style>
        body{
            padding: 50px;
        }
        input,select{
            display: block;
        }
    </style>
</head>
<body onclick="seleteJob_Click()">
    <%
        Map<Integer, Djob> jobMap = Djob.getMap();
        request.setAttribute("jobMap",jobMap);
        Map<Integer, Demployee> demployeeMap = Demployee.getMap();
        request.setAttribute("demployeeMap",demployeeMap);
    %>
    <c:import url="NavigationBar.jsp"></c:import>
    <form action="/EditEmployeeServlet?editType=add" method="post">
        入职名称 <input name="name" type="text">
        入职职位
        <select id="job" name="jobId">
            <option value="" selected="true">请选择工作</option>
            <c:forEach var="job" items="${jobMap}">
                <option value="${job.key}">${job.value.getName()}</option>
            </c:forEach>
        </select>
        额外薪资<input name="money" type="number" value="0">
        <div id="adminJobDiv" style="display:none;">
            选择管理者
            <select id="adminJob" name="adminId">
                <option value="" selected="true">选择管理者</option>
                <c:forEach var="dept" items="${demployeeMap}">
                    <option value="${dept.key}">${dept.value.getDjob().getName()} >> ${dept.value.getName()}</option>
                </c:forEach>
            </select>
        </div>
        备注<input name="msg" type="text" value="实习生">
        <input type="submit">
    </form>

    <script>
        function seleteJob_Click(){
            var select = document.getElementById("job");
            var selectIndex = select.selectedIndex;
            var selectDiv = document.getElementById("adminJobDiv");
            var selectAdmin = document.getElementById("adminJob");
            if(select.options[selectIndex].value <= 10 && select.options[selectIndex].value >0){
                selectDiv.style.display = "block";
            }else{
                selectAdmin.options[0].selected = true;
                selectDiv.style.display = "none";
            }
        }

    </script>
</body>
</html>
