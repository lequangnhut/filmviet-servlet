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
<title>FilmViet - Lịch Sử Yêu Thích</title>
<%@ include file="/views/common/head.jsp"%>
</head>

<body>
	<%@ include file="/views/common/header.jsp"%>

	<!-- Blog Section Begin -->
	<section class="blog spad" style="padding-top: 70px">
		<div class="container">
			<div class="text-white text-center">
				<h4 class="font-weight-bold">Lịch Sử Giao Dịch</h4>
				<br>
			</div>
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
			<div class="row">
				<c:forEach items="${videos}" var="video">
					<div class="col-lg-3 col-md-6 col-sm-6">
						<div class="product__item">
							<a
								href="<c:url value='/video?action=details&id=${video.href}' />">
								<div class="product__item__pic set-bg"
									data-setbg="${video.poster}">
									<!-- <div class="ep">18 / 18</div> -->
									<div class="comment">
										<i class="fa-solid fa-heart"></i> 11
									</div>
									<div class="view" style="margin-right: 50px">
										<i class="fa fa-eye"></i> ${video.shares }
									</div>
									<div class="view">
										<i class="fa fa-eye"></i> ${video.views }
									</div>
								</div>
							</a>
							<div class="product__item__text">
								<ul>
									<li>Tình cảm Gia đình</li>
								</ul>
								<h5>
									<a href="#">${video.title }</a>
								</h5>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Blog Section End -->

	<%@ include file="/views/common/footer.jsp"%>

</body>

</html>