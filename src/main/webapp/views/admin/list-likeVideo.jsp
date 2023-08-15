<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/views/common/taglib.jsp" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FilmViet - Lượt Thích Theo Từng Video</title>

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

                    <h5 class="card-title fw-semibold mb-4 mt-2">Danh Sách Các
                        Lượt Yêu Thích Từng Video</h5>
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Tên phim</th>
                                        <th scope="col">Đạo diễn</th>
                                        <th scope="col">Số lượt thích</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${countlike}" var="item" varStatus="loop">
                                        <tr>
                                            <td scope="row">${loop.index + 1}</td>
                                            <td>${item.title}</td>
                                            <td>${item.daodien}</td>
                                            <td width="130px">${item.totalLike}</td>
                                            <td width="155px"><c:choose>
                                                <c:when test="${item.isActive}">Đang công chiếu</c:when>
                                                <c:otherwise>Ngưng công chiếu</c:otherwise>
                                            </c:choose></td>
                                            <td width="150px">
                                                <button type="button" data-bs-toggle="modal"
                                                        data-bs-target="#modalLiveDemo${loop.index}"
                                                        class="btn btn-success ms-2 rounded-2">Xem
                                                </button>
                                            </td>
                                        </tr>

                                        <!-- Modal -->
                                        <div class="modal fade" id="modalLiveDemo${loop.index}"
                                             tabindex="-1" aria-labelledby="exampleModalLabel"
                                             aria-hidden="true">
                                            <div class="modal-dialog modal-xl modal-dialog-centered">
                                                <div class="modal-content">
                                                    <iframe id="player" width="100%" height="600"
                                                            src="https://www.youtube.com/embed/${item.href}"
                                                            frameborder="0" allowfullscreen></iframe>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
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