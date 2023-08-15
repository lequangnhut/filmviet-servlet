<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>FilmViet - Xác Minh Thành Công</title>
<%@ include file="/views/common/head.jsp"%>
</head>

<body>
	<%@ include file="/views/common/header.jsp"%>

	<!-- Blog Details Section Begin -->
	<section class="blog-details spad" style="min-height: 73vh">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-lg-8">
					<div class="blog__details__content">
						<div class="blog__details__text">
							<p>
								<i class="fa-solid fa-check"></i> Tài khoản ${email} đã được
								kích hoạt thành công !
							</p>
							<a role="button" href="login" class="site-btn">Đăng nhập</a>
						</div>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="blog__details__title">
						<jsp:useBean id="now" class="java.util.Date" />
						<h6>
							<fmt:formatDate value="${now}" pattern="EEE, HH:mm:ss, dd-MM-yyyy" />
						</h6>
						<div class="blog__details__social">
							<a href="#" class="facebook"><i class="fa fa-facebook-square"></i>
								Facebook</a> <a href="#" class="pinterest"><i
								class="fa fa-pinterest"></i> Pinterest</a> <a href="#"
								class="linkedin"><i class="fa fa-linkedin-square"></i>
								Linkedin</a> <a href="#" class="twitter"><i
								class="fa fa-twitter-square"></i> Twitter</a>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- Blog Details Section End -->

	<%@ include file="/views/common/footer.jsp"%>

</body>

</html>