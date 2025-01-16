<%--
  Created by IntelliJ IDEA.
  User: Minh
  Date: 09/01/2025
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    Employee List
</h1>

<table>
    <thead>
    <tr>
        <th>Employee ID</th>
        <th>Name</th>
        <th>Position</th>
        <th>Salary</th>
        <th>Department</th>
        <th>Hire Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employeeData}">
        <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.name}</td>
            <td>${employee.position}</td>
            <td>${employee.salary}</td>
            <td>${employee.departmentName}</td>
            <td>${employee.hireDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
