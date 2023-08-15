<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/views/common/taglib.jsp" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FilmViet - Chỉnh Sửa Người Dùng</title>

    <%@ include file="/views/admin/common/head.jsp" %>
</head>

<body>
<!--  Body Wrapper -->
<div class="page-wrapper" id="main-wrapper" data-layout="vertical"
     data-navbarbg="skin6" data-sidebartype="full"
     data-sidebar-position="fixed" data-header-position="fixed">

    <!-- Sidebar Start -->
    <%@ include file="/views/admin/common/assied.jsp" %>
    <!--  Sidebar End -->

    <!--  Main wrapper -->
    <div class="body-wrapper">

        <!--  Header Start -->
        <%@ include file="/views/admin/common/header.jsp" %>
        <!--  Header End -->

        <div class="container-fluid">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title fw-semibold mb-4 mt-2">Chỉnh Sửa Người Dùng</h5>
                    <div class="card">
                        <div class="card-body">
                            <form id="ConfirmEditUser" action="edituser"
                                  onsubmit="return ConfirmEditUser()" method="post">

                                <div class="mb-3">
                                    <label class="form-label">Email</label>
                                    <input type="text" class="form-control" value="${user.email}" name="email" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Mật khẩu</label>
                                    <input type="password" class="form-control" value="${user.password}"
                                           name="password">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Số điện thoại</label>
                                    <input type="text" class="form-control" value="${user.phone}" name="phone">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Họ và tên</label>
                                    <input type="text" class="form-control" value="${user.name}" name="fullname" readonly>
                                </div>
                                <div class="mb-3">
                                    <div class="mb-3">
                                        <label class="form-label">Trạng thái</label> <select
                                            class="form-select form-select-lg rounded-2 fs-3"
                                            style="height: 38px;" name="status">
                                        <option disabled>---Chọn---</option>
                                        <option value="1" ${user.isActive == true ? 'selected' : ''}>Đang
                                            hoạt động
                                        </option>
                                        <option value="0"
                                        ${user.isActive == false ? 'selected' : ''}>Ngưng
                                            hoạt động
                                        </option>
                                    </select>
                                    </div>
                                </div>

                                <input type="hidden" id="EditUser" value="false"/>

                                <button type="submit" class="btn btn-success">Xác nhận</button>
                                <button type="reset" class="btn btn-primary">Làm mới</button>
                                <a class="btn btn-danger float-end" href="datauser"
                                   role="button">Trở về</a>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/views/admin/common/footer.jsp" %>

</body>

</html>