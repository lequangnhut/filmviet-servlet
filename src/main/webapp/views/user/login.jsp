<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>FilmViet - Đăng Nhập</title>
    <%@ include file="/views/common/head.jsp" %>
</head>

<body>
<%@ include file="/views/common/header.jsp" %>

<!-- Normal Breadcrumb Begin -->
<section class="normal-breadcrumb set-bg"
         data-setbg="views/template/user/img/normal-breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="normal__breadcrumb__text">
                    <h2>Đăng Nhập</h2>
                    <p>Chào Mừng Bạn Đến Blog Chính Thức Của FilmViet.</p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Normal Breadcrumb End -->

<!-- Login Section Begin -->
<section class="login spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="login__form">
                    <h3>Đăng Nhập</h3>
                    <form onsubmit="return validateLoginForm()" action="login"
                          method="POST">
                        <div class="input__item">
                            <input type="text" name="email" placeholder="Email"> <span
                                class="icon_mail"></span>
                        </div>
                        <div class="input__item">
                            <input type="password" name="pass" placeholder="Mật khẩu">
                            <span class="icon_lock"></span>
                        </div>
                        <button type="submit" class="site-btn">Đăng Nhập Ngay</button>
                    </form>
                    <a href="forgotpass" class="forget_pass">Quên mật khẩu ?</a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login__register">
                    <h3>Không có tài khoản ?</h3>
                    <a href="register" class="primary-btn">Đăng ký ngay</a>
                </div>
            </div>
        </div>
        <div class="login__social">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-6">
                    <div class="login__social__links">
                        <span>Hoặc</span>
                        <ul>
                            <li><a href="#" class="facebook"><i
                                    class="fa fa-facebook"></i> Đăng nhập với Facebook</a></li>
                            <li><a
                                    href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/BackEnd/login&response_type=code
    &client_id=794386492125-ihgk5oo0mg850vefp61rctp97m3pede9.apps.googleusercontent.com&approval_prompt=force"
                                    class="google"><i class="fa fa-google"></i> Đăng nhập với
                                Google</a></li>
                            <li><a href="#" class="twitter"><i
                                    class="fa fa-twitter"></i> Đăng nhập với Twitter</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Login Section End -->

<%@ include file="/views/common/footer.jsp" %>

<%
    Boolean changePassSuccess = (Boolean) session.getAttribute("changePassSuccess");

    if (changePassSuccess != null) {
        if (changePassSuccess) {
%>
<script>
    showCenterAlert('success', 'Thành Công !',
        'Lấy lại mật khẩu thành công !');
</script>
<%
        }
        session.removeAttribute("changePassSuccess");
    }

    session.removeAttribute("otp");
%>

</body>

</html>