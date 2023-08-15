<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FilmViet - Chỉnh Sửa Video</title>

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
						<h5 class="card-title fw-semibold mb-4 mt-2">Chỉnh Sửa Phim</h5>
						<div class="card">
							<div class="card-body">
								<form id="ConfirmEditForm" action="editvideodisabled"
									onsubmit="return editVideo()" method="post">

									<div class="mb-3">
										<label for="title" class="form-label">Tên phim</label> <input
											type="text" class="form-control" value="${video.title}"
											name="title" id="title">
									</div>
									<fieldset>
										<div class="mb-3">
											<label for="link-phim" class="form-label">Link phim</label> <input
												type="text" class="form-control" value="${video.href}"
												name="href" readonly>
										</div>
									</fieldset>
									<div class="mb-3">
										<label for="dao-dien" class="form-label">Đạo diễn</label> <input
											type="text" class="form-control" value="${video.daodien}"
											name="daodien" id="dao-dien">
									</div>
									<div class="mb-3">
										<label for="dien-vien" class="form-label">Diễn viên</label> <input
											type="text" class="form-control" value="${video.dienvien}"
											name="dienvien" id="dien-vien">
									</div>
									<div class="mb-3">
										<label for="the-loai" class="form-label">Thể loại</label> <input
											type="text" class="form-control" value="${video.theloai}"
											name="category" id="the-loai">
									</div>
									<div class="mb-3">
										<label for="mo-ta" class="form-label">Mô tả</label> <input
											type="text" class="form-control" value="${video.mota}"
											name="mota" id="mo-ta">
									</div>
									<div class="mb-3">
										<label class="form-label">Giá</label> <input type="text"
											class="form-control" value="${formattedPrice}"
											oninput="formatPrice(this)" name="price" placeholder="Giá...">
									</div>
									<div class="mb-3">
										<label for="noted" class="form-label">Nội dung</label>
										<textarea class="form-control" id="noted" name="noted"
											rows="5">${video.description}</textarea>
									</div>

									<input type="hidden" id="confirmEdit" value="false" />

									<button type="submit" class="btn btn-success">Xác nhận</button>
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<a class="btn btn-danger float-end" href="videodisabled"
										role="button">Trở về</a>

								</form>
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