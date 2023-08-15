<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>FilmViet - Admin</title>
  <link rel="icon" href="../img/logo-admin.png" type="image/x-icon">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" href="../assets/css/styles.min.css" />
</head>

<body>
  <!--  Body Wrapper -->
  <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
    data-sidebar-position="fixed" data-header-position="fixed">
    <!-- Sidebar Start -->
    <aside class="left-sidebar">
      <!-- Sidebar scroll-->
      <div>
        <div class="brand-logo d-flex align-items-center justify-content-between">
          <a href="#" class="text-nowrap logo-img">
            <img src="../assets/images/logo.png" width="180" class="ps-4" />
          </a>
          <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
            <i class="ti ti-x fs-8"></i>
          </div>
        </div>
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
          <ul id="sidebarnav">

            <li class="nav-small-cap">
              <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
              <span class="hide-menu">Trang Chủ</span>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="dashboarh.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-layout-dashboard"></i>
                </span>
                <span class="hide-menu">Biểu đồ</span>
              </a>
            </li>

            <li class="nav-small-cap">
              <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
              <span class="hide-menu">QUẢN LÝ VIDEO</span>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="add-video.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-cards"></i>
                </span>
                <span class="hide-menu">Tạo Phim</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="category-video.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-file-description"></i>
                </span>
                <span class="hide-menu">Thể Loại Phim</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="list-video.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-clipboard-data"></i>
                </span>
                <span class="hide-menu">Danh Sách Phim</span>
              </a>
            </li>

            <li class="nav-small-cap">
              <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
              <span class="hide-menu">QUẢN LÝ NGƯỜI DÙNG</span>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="list-user.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-cards"></i>
                </span>
                <span class="hide-menu">Danh sách người dùng</span>
              </a>
            </li>

            <li class="nav-small-cap">
              <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
              <span class="hide-menu">BÁO CÁO - THỐNG KÊ</span>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="list-likeVideo.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-mood-happy"></i>
                </span>
                <span class="hide-menu">Lượt thích từng video</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="list-userLike.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-thumb-up"></i>
                </span>
                <span class="hide-menu">Người dùng yêu thích</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" href="list-userShare.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-share"></i>
                </span>
                <span class="hide-menu">Lọc người dùng gửi & nhận</span>
              </a>
            </li>
          </ul>
        </nav>
        <!-- End Sidebar navigation -->
      </div>
      <!-- End Sidebar scroll-->
    </aside>
    <!--  Sidebar End -->
    <!--  Main wrapper -->
    <div class="body-wrapper">
      <!--  Header Start -->
      <header class="app-header">
        <nav class="navbar navbar-expand-lg navbar-light">
          <ul class="navbar-nav">
            <li class="nav-item d-block d-xl-none">
              <a class="nav-link sidebartoggler nav-icon-hover" id="headerCollapse" href="#">
                <i class="ti ti-menu-2"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-icon-hover" href="#">
                <i class="ti ti-bell-ringing"></i>
                <div class="notification bg-primary rounded-circle"></div>
              </a>
            </li>
          </ul>
          <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
            <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
              <li class="nav-item dropdown">
                <a class="nav-link nav-icon-hover" id="drop2" data-bs-toggle="dropdown" aria-expanded="false">
                  <img src="../assets/images/profile/user-1.jpg" width="35" height="35" class="rounded-circle">
                </a>
                <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                  <div class="message-body">
                    <a href="info-user.jsp" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-user fs-6"></i>
                      <p class="mb-0 fs-3">Thông tin cá nhân</p>
                    </a>
                    <a href="changePass-form.jsp" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-replace fs-6"></i>
                      <p class="mb-0 fs-3">Đổi mật khẩu</p>
                    </a>
                    <a href="../admin.jsp" class="btn btn-outline-primary mx-3 mt-2 d-block">Đăng xuất</a>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!--  Header End -->
      <div class="container-fluid">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Thông Tin Cá Nhân</h5>
            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-12 col-md-6 col-lg-6">
                    <h6 class="pb-4">Họ và tên:</h6>
                    <h6 class="pb-4">Email:</h6>
                    <h6 class="pb-4">Tên đăng nhập:</h6>
                    <h6 class="pb-4">Chức vụ:</h6>
                  </div>
                  <div class="col-12 col-md-6 col-lg-6">
                    <h6 class="pb-4">Lê Quang Nhựt</h6>
                    <h6 class="pb-4">Email: nhut.thanthien17@gmail.com</h6>
                    <h6 class="pb-4">Tên đăng nhập: admin</h6>
                    <h6 class="pb-4">Chức vụ: Nhân Viên</h6>
                  </div>
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