<%--
  Created by IntelliJ IDEA.
  User: Minh
  Date: 23/01/2025
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h2>Thêm Nhân Viên</h2>
    <form action="insert_employee" method="post">
        <div class="form-group mt-5">
            <label for="name">Tên Nhân Viên:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group mt-5">
            <label for="position">Chức Vụ:</label>
            <input type="text" class="form-control" id="position" name="position" required>
        </div>
        <div class="form-group mt-5">
            <label for="salary">Lương:</label>
            <input type="number" class="form-control" id="salary" name="salary" required>
        </div>
        <div class="form-group mt-5">
            <label for="departmentName">Phòng Ban:</label>
            <select class="form-select" id="departmentName" name="departmentName" required>
                <option value="">Chọn Phòng Ban</option>
                <c:forEach var="dept" items="${departments}">
                    <option value="${dept.departmentId}">${dept.departmentName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group mt-5">
            <label for="hireDate">Ngày Tuyển Dụng:</label>
            <input type="date" class="form-control" id="hireDate" name="hireDate" required>
        </div>
        <button type="submit" class="btn btn-primary">Thêm Nhân Viên</button>
    </form>
</div>
</body>
</html>
