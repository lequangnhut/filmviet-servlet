<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FilmViet - Thêm Video</title>

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
						<h5 class="card-title fw-semibold mb-4 mt-2">Thêm Mới Phim</h5>
						<div class="card">
							<div class="card-body">
								<form onsubmit="return validateNewFilm()" action="videoadd"
									method="post">

									<div class="mb-3">
										<label for="title" class="form-label">Tên phim</label> <input
											type="text" class="form-control" name="title" id="title"
											placeholder="Tên phim...">
									</div>
									<div class="mb-3">
										<label for="link-phim" class="form-label">Link phim</label> <input
											type="text" class="form-control" name="href" id="link-phim"
											placeholder="Href..." onblur="fillHrefOnPoster()">
									</div>
									<div class="mb-3">
										<label for="dao-dien" class="form-label">Đạo diễn</label> <input
											type="text" class="form-control" name="daodien" id="dao-dien"
											placeholder="Dạo diễn...">
									</div>
									<div class="mb-3">
										<label for="dien-vien" class="form-label">Diễn viên</label> <input
											type="text" class="form-control" name="dienvien"
											id="dien-vien" placeholder="Diễn viên...">
									</div>
									<div class="mb-3">
										<label for="the-loai" class="form-label">Thể loại</label> <input
											type="text" class="form-control" name="category"
											id="the-loai" placeholder="Thể loại...">
									</div>
									<div class="mb-3">
										<label for="mo-ta" class="form-label">Mô tả</label> <input
											type="text" class="form-control" name="mota" id="mo-ta"
											placeholder="Mô tả...">
									</div>
									<div class="mb-3">
										<label class="form-label">Giá</label> <input type="text"
											class="form-control" oninput="formatPrice(this)" name="price"
											placeholder="Giá...">
									</div>
									<div class="mb-3">
										<label for="noted" class="form-label">Nội dung</label>
										<textarea class="form-control" id="noted" name="noted"
											rows="5" placeholder="Nội dung..."></textarea>
									</div>

									<input type="hidden" class="form-control" id="poster"
										name="poster">

									<button type="submit" class="btn btn-success">Xác nhận</button>
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<a class="btn btn-danger float-end" href="videoviews"
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

	<script type="text/javascript">
		function fillHrefOnPoster() {
			var href = document.getElementById("link-phim").value;
			var poster = document.getElementById("poster");
			poster.value = "https://img.youtube.com/vi/" + href
					+ "/maxresdefault.jpg";
		}
	</script>

</body>

</html>