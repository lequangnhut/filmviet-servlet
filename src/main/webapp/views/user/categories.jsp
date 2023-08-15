<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>FilmViet - Thể Loại Phim</title>
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
						<a href="index"><i class="fa fa-home"></i> Trang chủ</a> <span>Thể
							loại</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Product Section Begin -->
	<section class="product-page spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="product__page__content">
						<div class="product__page__title">
							<div class="row">
								<div class="col-lg-8 col-md-8 col-sm-6">
									<div class="section-title">
										<h4>Tất cả phim</h4>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6">
									<div class="product__page__filter">
										<p>Sắp xếp:</p>
										<select>
											<option value="">A-Z</option>
											<option value="">1-10</option>
											<option value="">10-50</option>
										</select>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<c:forEach items="${videos}" var="video">
								<div class="col-lg-4 col-md-6 col-sm-6">
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
												<li>${video.theloai}</li>
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

					<div class="product__pagination d-flex justify-content-center">
						<c:if test="${currenPage == 1}">
							<a href="#"><i class="fa fa-angle-double-left disabled"></i></a>
						</c:if>
						<c:if test="${currenPage > 1}">
							<a href="categories?page=${currenPage - 1}"><i
								class="fa fa-angle-double-left"></i></a>
						</c:if>
						<c:forEach varStatus="i" begin="1" end="${maxPage}">
							<a href="categories?page=${i.index}" style="margin-left: 10px"
								class="current-page ${currenPage == i.index ? 'active' : ''}">${i.index}</a>
						</c:forEach>
						<c:if test="${currenPage == maxPage}">
							<a href="#"><i class="fa fa-angle-double-right disabled"></i></a>
						</c:if>
						<c:if test="${currenPage < maxPage}">
							<a href="categories?page=${currenPage + 1}"><i
								class="fa fa-angle-double-right"></i></a>
						</c:if>
					</div>
				</div>

				<div class="col-lg-4 col-md-6 col-sm-8">
					<div class="product__sidebar">
						<div class="product__sidebar__view">
							<div class="section-title">
								<h5>TOP XEM HÀNG ĐẦU</h5>
							</div>
							<ul class="filter__controls">
								<li class="active" data-filter="*">Ngày</li>
								<li data-filter=".week">Tuần</li>
								<li data-filter=".month">Tháng</li>
								<li data-filter=".years">Năm</li>
							</ul>
							<div class="filter__gallery">
								<div class="product__sidebar__view__item set-bg mix day years"
									data-setbg="views/template/user/img/sidebar/tv-1.jpg">
									<div class="ep">1 Tập</div>
									<div class="view">
										<i class="fa fa-eye"></i> 9141
									</div>
									<h5>
										<a href="#">Mắt Biếc</a>
									</h5>
								</div>
								<div class="product__sidebar__view__item set-bg mix month week"
									data-setbg="views/template/user/img/sidebar/tv-2.jpg">
									<div class="ep">1 Tập</div>
									<div class="view">
										<i class="fa fa-eye"></i> 9141
									</div>
									<h5>
										<a href="#">Lật Mặt 48H</a>
									</h5>
								</div>
								<div class="product__sidebar__view__item set-bg mix week years"
									data-setbg="views/template/user/img/sidebar/tv-3.jpg">
									<div class="ep">1 Tập</div>
									<div class="view">
										<i class="fa fa-eye"></i> 9141
									</div>
									<h5>
										<a href="#">Con Nhót Mót Chồng</a>
									</h5>
								</div>
								<div class="product__sidebar__view__item set-bg mix years month"
									data-setbg="views/template/user/img/sidebar/tv-4.jpg">
									<div class="ep">1 Tập</div>
									<div class="view">
										<i class="fa fa-eye"></i> 9141
									</div>
									<h5>
										<a href="#">Fate/stay night: Heaven's Feel I. presage
											flower</a>
									</h5>
								</div>
								<div class="product__sidebar__view__item set-bg mix day"
									data-setbg="views/template/user/img/sidebar/tv-5.jpg">
									<div class="ep">5 Tập</div>
									<div class="view">
										<i class="fa fa-eye"></i> 9141
									</div>
									<h5>
										<a href="#">Hùng Long Phong Bá 2</a>
									</h5>
								</div>
							</div>
						</div>

						<div class="product__sidebar__comment">
							<div class="section-title">
								<h5>BÌNH LUẬN MỚI</h5>
							</div>
							<div class="product__sidebar__comment__item">
								<div class="product__sidebar__comment__item__pic">
									<img src="views/template/user/img/sidebar/comment-1.jpg" alt="">
								</div>
								<div class="product__sidebar__comment__item__text">
									<ul>
										<li>Hoạt động</li>
										<li>Phim</li>
									</ul>
									<h5>
										<a href="#">Phim hay quá mọi người ơi</a>
									</h5>
									<span><i class="fa fa-eye"></i> 19.141 Lượt xem</span>
								</div>
							</div>
							<div class="product__sidebar__comment__item">
								<div class="product__sidebar__comment__item__pic">
									<img src="views/template/user/img/sidebar/comment-1.jpg" alt="">
								</div>
								<div class="product__sidebar__comment__item__text">
									<ul>
										<li>Hoạt động</li>
										<li>Phim</li>
									</ul>
									<h5>
										<a href="#">Phim hay quá mọi người ơi</a>
									</h5>
									<span><i class="fa fa-eye"></i> 19.141 Lượt xem</span>
								</div>
							</div>
							<div class="product__sidebar__comment__item">
								<div class="product__sidebar__comment__item__pic">
									<img src="views/template/user/img/sidebar/comment-1.jpg" alt="">
								</div>
								<div class="product__sidebar__comment__item__text">
									<ul>
										<li>Hoạt động</li>
										<li>Phim</li>
									</ul>
									<h5>
										<a href="#">Phim hay quá mọi người ơi</a>
									</h5>
									<span><i class="fa fa-eye"></i> 19.141 Lượt xem</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Section End -->

	<%@ include file="/views/common/footer.jsp"%>

</body>

</html>