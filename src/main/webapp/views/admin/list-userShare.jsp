<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FilmViet - Danh Sách Người Dùng</title>

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
							Dùng Chia Sẽ</h5>
						<div class="card">
							<div class="card-body">
								<form action="usershare" method="get">
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
												<th scope="col">Họ và tên</th>
												<th scope="col">Gửi từ Email</th>
												<th scope="col">Đến Email</th>
												<th scope="col">Ngày gửi</th>
												<th scope="col">Giờ gửi</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="count" value="1" />
											<c:forEach items="${user}" var="users" varStatus="loop">
												<c:forEach items="${users.share}" var="shares">
													<tr>
														<td scope="row">${count}</td>
														<td>${users.name}</td>
														<td>${users.email}</td>
														<td>${shares.email}</td>
														<td><fmt:formatDate value="${shares.shareDate}"
																pattern="dd/MM/yyyy" /></td>
														<td><fmt:formatDate value="${shares.shareDate}"
																pattern="HH:mm:ss" /></td>
													</tr>
													<c:set var="count" value="${count + 1}" />
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

	<%@ include file="/views/admin/common/footer.jsp"%>
</body>

</html>