<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>

    <!-- CSS Styles -->
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .search-form {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 20px;
        }
        .search-form label {
            font-weight: bold;
        }
        .search-form input[type="text"],
        .search-form input[type="date"] {
            padding: 5px;
            width: 200px;
        }
        .search-form input[type="submit"] {
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .search-form input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>

<h1>Employee List</h1>

<!-- Form tìm kiếm với các trường hiển thị ngang -->
<form class="search-form" action="find_employees" method="get">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${param.name}" placeholder="Search by Name"/>
    </div>

    <div>
        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary" value="${param.salary}" placeholder="Search by Salary"/>
    </div>

    <div>
        <label for="fromHireDate">From Hire Date:</label>
        <input type="date" id="fromHireDate" name="fromHireDate" value="${param.fromDate}"/>
    </div>

    <div>
        <label for="toHireDate">To Hire Date:</label>
        <input type="date" id="toHireDate" name="toHireDate" value="${param.toDate}"/>
    </div>

    <div>
        <label for="position">Position:</label>
        <input type="text" id="position" name="position" value="${param.position}" placeholder="Search by Position"/>
    </div>

    <div>
        <label for="departmentName">Department Name:</label>
        <input type="text" id="departmentName" name="departmentName" value="${param.position}" placeholder="Search by Department Name"/>
    </div>

    <div>
        <input type="submit" value="Search"/>
    </div>
</form>

<!-- Bảng danh sách nhân viên -->
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
    <c:forEach var="employee" items="${find_employees}">
        <tr>
            <td>${employee.employeeID}</td>
            <td>${employee.name}</td>
            <td>${employee.position}</td>
            <td>${employee.salary}</td>
            <td>${employee.department_name}</td>
            <td>${employee.hireDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
