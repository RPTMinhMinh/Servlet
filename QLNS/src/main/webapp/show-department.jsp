<%--
  Created by IntelliJ IDEA.
  User: Minh
  Date: 12/01/2025
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>SHOW ALL DEPARTMENT</h1>
<form action="search_department_by_name" method="get">
    <label for="departmentName">Department Name:</label>
    <input type="text" id="departmentName" name="departmentName">
    <button type="submit">Search</button>
</form>
<table>
    <thead>
    <tr>
        <th>Department ID</th>
        <th>Department Name</th>
        <th>Location</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="department" items="${showDepartments}">
        <tr>
            <td>${department.department_id}</td>
            <td>${department.department_name}</td>
            <td>${department.location}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>