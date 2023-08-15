<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FilmViet - Người Dùng Thích Video</title>

<%@ include file="/views/admin/common/head.jsp"%>
</head>

<body>
	<!--  Body Wrapper -->
	<div class="page-wrapper" id="main-wrapper" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed">

		<!-- Sidebar Start -->
		<%@ include file="/views/admin/common/assied.jsp"%>
		<!--  Sidebar End -->

		<!--  Main wrapper -->
		<div class="body-wrapper">

			<!--  Header Start -->
			<%@ include file="/views/admin/common/header.jsp"%>
			<!--  Header End -->

			<div class="container-fluid">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title fw-semibold mb-4 mt-2">Danh Sách Người
							Dùng Yêu Thích Video</h5>
						<div class="card">
							<div class="card-body">
								<form action="userlike" method="get">
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
												<th scope="col">Email</th>
												<th scope="col">Họ tên</th>
												<th scope="col">Số điện thoại</th>
												<th scope="col">Trạng thái</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${user}" var="item" varStatus="loop">
												<tr>
													<td scope="row">${loop.index + 1}</td>
													<td>${item.email}</td>
													<td>${item.name}</td>
													<td>${item.phone}</td>
													<td><c:choose>
															<c:when test="${item.isActive}">Đang hoạt động</c:when>
															<c:otherwise>Ngưng hoạt động</c:otherwise>
														</c:choose></td>
												</tr>
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

	<%@ include file="/views/admin/common/footer.jsp"%>

</body>

</html>