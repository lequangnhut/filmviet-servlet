<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>FilmViet - Trang Cá Nhân</title>
<%@ include file="/views/common/head.jsp"%>

</head>

<body>
	<%@ include file="/views/common/header.jsp"%>

	<!-- Normal Breadcrumb Begin -->
	<section class="normal-breadcrumb set-bg"
		data-setbg="views/template/user/img/normal-breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="normal__breadcrumb__text">
						<h2>Trang Cá Nhân</h2>
						<p>Chào Mừng Bạn Đến Blog Chính Thức Của FilmViet.</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Normal Breadcrumb End -->

	<!-- Login Section Begin -->
	<section class="login spad container">
		<div class="rounded-lg">
			<div class="row bg-white p-5 m-1" style="border-radius: 6px">

				<div class="col-12 col-md-3 col-lg-3">

					<div class="img-profile">

						<img src="views/template/user/img/profile-logo.png"
							class="img-fluid rounded-top" width="60%">

					</div>

					<div class="link-profile">

						<h6 class="text-secondary font-weight-bold">WORD LINK</h6>
						<h6 class="text-dark mt-4 font-weight-bold mb-2">Website Link</h6>
						<h6 class="text-dark font-weight-bold mb-2">Bootsnipp Profile</h6>
						<h6 class="text-dark font-weight-bold mb-2">Bootply Profile</h6>

						<h6 class="text-secondary mt-4 font-weight-bold">SKILLS</h6>
						<h6 class="text-dark mt-4 font-weight-bold mb-2">Web Designer</h6>
						<h6 class="text-dark font-weight-bold mb-2">Web Developer</h6>
						<h6 class="text-dark font-weight-bold mb-2">WordPress</h6>
						<h6 class="text-dark font-weight-bold mb-2">WooCommere</h6>
						<h6 class="text-dark font-weight-bold mb-2">PHP.Net</h6>
						<h6 class="text-dark font-weight-bold mb-2">
							<a href="history" class="text-info font-weight-bold">Lịch sử
								truy cập</a>
						</h6>
						<h6 class="text-dark font-weight-bold mb-2">
							<a href="favorites" class="text-info font-weight-bold">Phim
								yêu thích</a>
						</h6>

					</div>

				</div>

				<div class="col-12 col-md-9 col-lg-9">

					<div class="profile-name">

						<div class="row">

							<div class="col-12 col-md-12 col-lg-9">

								<h4 class="text-dark font-weight-bold">FilmViet</h4>
								<span class="text-info font-weight-bold">BLOG Xem Phim
									Việt</span>

							</div>

							<div class="col-lg-3">

								<h4>
									<a href="editprofile"
										class="text-info font-weight-bold text-decoration-none h6">
										Chỉnh sửa thông tin </a>
								</h4>

							</div>

						</div>

						<hr class="text-dark">

					</div>

					<div class="info mt-5">

						<h4 class="text-dark mb-3 font-weight-bold">Thông tin</h4>

						<div class="row">

							<div class="col-lg-6">

								<h6 class="text-dark mt-4">Họ và tên:</h6>
								<h6 class="text-dark mt-4">Email:</h6>
								<h6 class="text-dark mt-4">Số điện thoại:</h6>
							</div>

							<div class="col-lg-6">

								<h6 class="text-dark mt-4">${fullname}</h6>
								<h6 class="text-dark mt-4">${email}</h6>
								<h6 class="text-dark mt-4">${phone}</h6>

							</div>

						</div>

					</div>

				</div>

			</div>
		</div>
	</section>
	<!-- Forgotpass Section End -->

	<%@ include file="/views/common/footer.jsp"%>

</body>

</html>