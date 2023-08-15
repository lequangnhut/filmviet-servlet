<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<aside class="left-sidebar">
	<!-- Sidebar scroll-->
	<div>
		<div
			class="brand-logo d-flex align-items-center justify-content-between">
			<a href="dashboarh" class="text-nowrap logo-img"> <img
				src="<c:url value='/views/admin/assets/images/logo.png' />"
				width="180" class="ps-4" />
			</a>
			<div
				class="close-btn d-xl-none d-block sidebartoggler cursor-pointer"
				id="sidebarCollapse">
				<i class="ti ti-x fs-8"></i>
			</div>
		</div>
		<!-- Sidebar navigation-->
		<nav class="sidebar-nav scroll-sidebar" data-simplebar="">
			<ul id="sidebarnav">

				<li class="nav-small-cap"><i
					class="ti ti-dots nav-small-cap-icon fs-4"></i> <span
					class="hide-menu">Trang Chủ</span></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="dashboarh" aria-expanded="false"> <span> <i
							class="ti ti-layout-dashboard"></i>
					</span> <span class="hide-menu">DashBoarh</span>
				</a></li>

				<li class="nav-small-cap"><i
					class="ti ti-dots nav-small-cap-icon fs-4"></i> <span
					class="hide-menu">QUẢN LÝ VIDEO</span></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="videoviews" aria-expanded="false"> <span> <i
							class="ti ti-cards"></i>
					</span> <span class="hide-menu">Tạo Phim</span>
				</a></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="videodisabled" aria-expanded="false"> <span> <i
							class="ti ti-list-details"></i>
					</span> <span class="hide-menu">Phim ngừng công chiếu</span>
				</a></li>

				<li class="nav-small-cap"><i
					class="ti ti-dots nav-small-cap-icon fs-4"></i> <span
					class="hide-menu">QUẢN LÝ NGƯỜI DÙNG</span></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="datauser" aria-expanded="false"> <span> <i
							class="ti ti-apps"></i>
					</span> <span class="hide-menu">Danh sách người dùng</span>
				</a></li>

				<li class="nav-small-cap"><i
					class="ti ti-dots nav-small-cap-icon fs-4"></i> <span
					class="hide-menu">BÁO CÁO - THỐNG KÊ</span></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="likeonevideo" aria-expanded="false"> <span> <i
							class="ti ti-mood-happy"></i>
					</span> <span class="hide-menu">Lượt thích từng video</span>
				</a></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="userlike" aria-expanded="false"> <span> <i
							class="ti ti-thumb-up"></i>
					</span> <span class="hide-menu">Người dùng yêu thích</span>
				</a></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="usershare" aria-expanded="false"> <span> <i
							class="ti ti-share"></i>
					</span> <span class="hide-menu">Lọc người dùng gửi & nhận</span>
				</a></li>

				<li class="sidebar-item"><a class="sidebar-link"
					href="doanhthu" aria-expanded="false"> <span> <i
							class="ti ti-align-box-right-top"></i>
					</span> <span class="hide-menu">Doanh thu</span>
				</a></li>

			</ul>
		</nav>
		<!-- End Sidebar navigation -->
	</div>
	<!-- End Sidebar scroll-->
</aside>