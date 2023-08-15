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
<title>FilmViet - ${video.title}</title>
<%@ include file="/views/common/head.jsp"%>
</head>

<body>
	<%@ include file="/views/common/header.jsp"%>

	<!-- Breadcrumb Begin -->
	<div class="breadcrumb-option">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb__links">
						<a href="index"><i class="fa fa-home"></i> Trang chủ</a> <a
							href="categories">Thể loại</a> <a
							href="<c:url value='/video?action=details&id=${video.href}'/>">Thông
							Tin Phim</a> <span>${video.title}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Anime Section Begin -->
	<section class="anime-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="anime__video__player">
						<iframe id="player" width="100%" height="600"
							src="https://www.youtube.com/embed/${video.href}" frameborder="0"
							allowfullscreen></iframe>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="anime__details__review">
						<div class="section-title">
							<h5>Bình Luận</h5>
						</div>

						<c:forEach items="${comment}" var="cmt">
							<div class="anime__review__item">
								<div class="anime__review__item__pic">
									<img src="views/template/user/img/anime/review-1.jpg">
								</div>
								<div class="anime__review__item__text">
									<c:forEach items="${user}" var="users">
										<h6>
											${cmt.user.name} - <span>${timeAgo}</span>
										</h6>
									</c:forEach>
									<p>${cmt.comment}</p>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Anime Section End -->

	<%@ include file="/views/common/footer.jsp"%>

</body>

</html>