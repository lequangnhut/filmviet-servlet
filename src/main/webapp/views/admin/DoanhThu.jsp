<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/views/common/taglib.jsp" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FilmViet - Chỉnh Sửa Poster</title>

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
                    <h5 class="card-title fw-semibold mb-4 mt-2">Doanh thu bán
                        phim & Các thông tin hoá đơn</h5>
                    <div class="card">
                        <div class="card-body">
                            <form action="doanhthu" method="get">
                                <div class="mb-3">
                                    <label class="form-label">Tên phim:</label> <select
                                        name="href" id="selectVideo"
                                        class="form-select form-select-lg rounded-2 fs-3"
                                        style="height: 38.6px;">
                                    <option selected disabled>---Chọn tên phim---</option>
                                    <c:forEach items="${video}" var="item">
                                        <option value="${item.href}"
                                            ${videoHref == item.href ? 'selected' : ''}>${item.title}</option>
                                    </c:forEach>
                                </select>
                                </div>

                                <button type="submit" class="btn btn-success">Tìm kiếm</button>
                            </form>
                        </div>
                    </div>

                    <h5 class="card-title fw-semibold mb-4 mt-2">Danh Sách</h5>
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Mã giao dịch</th>
                                        <th scope="col">Người mua</th>
                                        <th scope="col">Ngân hàng</th>
                                        <th scope="col">Giá tiền</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="1"/>
                                    <c:forEach items="${paymentVnpay}" var="pay">
                                        <c:forEach items="${pay.hoadon}" var="hoadons">
                                            <tr>
                                                <td scope="row">${count}</td>
                                                <td width="130px">${hoadons.vnp_TxnRef}</td>
                                                <td width="150px">${pay.name}</td>
                                                <td width="200px">NGÂN HÀNG ${hoadons.vnp_BankCode}</td>
                                                <td><c:set var="amount" value="${hoadons.vnp_Amount}"/>
                                                    <c:set var="locale" value="vi_VN"/> <fmt:setLocale
                                                            value="${locale}"/> <fmt:formatNumber
                                                            value="${amount / 100}" type="currency"
                                                            currencyCode="VND"/></td>
                                                <td width="140px"><c:choose>
                                                    <c:when test="${hoadons.vnp_ResponseCode == '00'}">
                                                        <span class="text-success fw-bold">Thành công</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="text-danger fw-bold">Thất bại</span>
                                                    </c:otherwise>
                                                </c:choose></td>
                                                <td>
                                                    <button type="button" data-bs-toggle="modal"
                                                            data-bs-target="#modalLiveDemo${count}"
                                                            class="btn btn-success ms-2 rounded-2">Xem
                                                    </button>
                                                </td>
                                            </tr>

                                            <!-- Modal -->
                                            <div class="modal fade" id="modalLiveDemo${count}"
                                                 tabindex="-1" aria-labelledby="exampleModalLabel"
                                                 aria-hidden="true">
                                                <div class="modal-dialog modal-xl modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <iframe id="player" width="100%" height="600"
                                                                src="https://www.youtube.com/embed/${hoadons.video.href}"
                                                                frameborder="0" allowfullscreen></iframe>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:set var="count" value="${count + 1}"/>
                                        </c:forEach>
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

<script type="text/javascript">
    function fillHrefOnPoster(inputId, imageId) {
        var href = document.getElementById(inputId).value;
        var poster = document.getElementById(imageId);
        poster.src = "https://img.youtube.com/vi/" + href
            + "/maxresdefault.jpg";
    }
</script>

</body>

</html>