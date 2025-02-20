<%--
  Created by IntelliJ IDEA.
  User: Minh
  Date: 18/02/2025
  Time: 08:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Admin:  sản phẩm</title>

    <link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

    <!-- Bootstrap v5.0.1 -->
    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>

    <!-- Bootstrap Icons v1.5.0 -->
    <link href="../css/bootstrap-icons.css" type="text/css" rel="stylesheet">

    <!-- Custom Styles -->
    <link href="../css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-11 py-3">
                    <a class="text-body" href="../home.jsp">
                        <h3>Shop Bán Sách : Admin</h3>
                    </a>
                </div> <!-- col.// -->
                <div class="col-1">
                    <ul class="nav col-12 col-lg-auto my-2 my-lg-0 justify-content-center justify-content-lg-end text-small">
                        <li>
                            <a href="#" class="nav-link text-body">
                                <i class="bi bi-window d-block text-center fs-3"></i>
                                Client
                            </a>
                        </li>
                    </ul>
                </div> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- container.// -->
    </section> <!-- header-main.// -->
</header> <!-- section-header.// -->

<nav class="navbar navbar-main navbar-expand-lg navbar-light border-bottom">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-people"></i> Quản lý người dùng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-tags"></i> Quản lý thể loại</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#"><i class="bi bi-book"></i> Quản lý sản phẩm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-cart"></i> Quản lý giỏ hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="bi bi-inboxes"></i> Quản lý đơn hàng</a>
                </li>
            </ul>
            <a class="btn btn-primary" href="#" role="button">Đăng nhập</a>
        </div>
    </div> <!-- container.// -->
</nav> <!-- navbar-main.// -->

<section class="section-content">
    <div class="container">
        <header class="section-heading py-4 d-flex justify-content-between">
            <h3 class="section-title">Sửa sản phẩm</h3>
        </header> <!-- section-heading.// -->
        <main class="add-book-form mb-5">
            <form class="w-50" action="update-user" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <div class="mb-3">
                    <label for="add-book-title" class="form-label">Tên đăng nhập</label>
                    <input type="text" value="${user.username}" class="form-control" id="add-book-title" name="username">
                </div>
                <div class="mb-3">
                    <label for="add-book-author" class="form-label">Họ tên</label>
                    <input type="text" value="${user.fullName}" class="form-control" id="add-book-author" name="fullname">
                </div>
                <div class="mb-3">
                    <label for="add-book-pages" class="form-label">Email</label>
                    <input type="email" value="${user.email}" class="form-control" id="add-book-pages" name="email">
                </div>
                <div class="mb-3">
                    <label for="add-book-publisher" class="form-label">Số điện thoại</label>
                    <input type="number" value="${user.phone}" class="form-control" id="add-book-publisher" name="phone" maxlength="10">
                </div>
                <div class="mb-3">
                    <label for="add-book-yearPublishing" class="form-label">Địa chỉ</label>
                    <input type="text" value="${user.address}" class="form-control" id="add-book-yearPublishing" name="address">
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Role</label>
                    <select class="form-select" id="role" name="role" required>
                        <option value="">Chọn Role</option>
                        <c:if test="${user.role == 'CUSTOMER'}">
                            <option value="${user.role}" selected>${user.role}</option>
                            <option value="EMPLOYEE">EMPLOYEE</option>
                        </c:if>
                        <c:if test="${user.role == 'EMPLOYEE'}">
                            <option value="${user.role}" selected>${user.role}</option>
                            <option value="CUSTOMER">CUSTOMER</option>
                        </c:if>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="add-book-password" class="form-label">Mật khẩu</label>
                    <input type="password" value="${user.password}" class="form-control" id="add-book-password" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Sửa người dùng</button>
                <button type="reset" class="btn btn-warning ms-2">Tẩy trống</button>
                <button type="button" class="btn btn-light ms-2">Hủy</button>
            </form>
        </main> <!-- add-book-form.// -->
    </div> <!-- container.// -->
</section> <!-- section-content.// -->

<footer class="section-footer">
    <section class="footer-bottom text-center bg-light border-top py-3">
        <div class="container-fluid">© 2021 — Shop Bán Sách</div> <!-- container-fluid.// -->
    </section> <!-- footer-bottom.// -->
</footer> <!-- section-footer.// -->

</body>

</html>
