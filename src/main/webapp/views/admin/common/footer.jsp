<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="<c:url value='/views/template/user/js/jquery-3.3.1.min.js'/>"></script>
<script src="<c:url value='/views/admin/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js'/>"></script>
<script src="<c:url value='/views/admin/assets/js/sidebarmenu.js'/>"></script>
<script src="<c:url value='/views/admin/assets/js/app.min.js'/>"></script>
<script src="<c:url value='/views/admin/assets/libs/apexcharts/dist/apexcharts.min.js'/>"></script>
<script src="<c:url value='/views/admin/assets/libs/simplebar/dist/simplebar.js'/>"></script>
<script src="<c:url value='/views/admin/assets/js/dashboard.js'/>"></script>
<script src="<c:url value='/views/admin/assets/js/validateAdmin.js'/>"></script>

<%
Boolean loginAdmin = (Boolean) session.getAttribute("loginAdmin");
Boolean loginAdminFail = (Boolean) session.getAttribute("loginAdminFail");

if (loginAdmin != null) {
	if (loginAdmin) {
%>
<script>
	showSwalAlert('success', 'Đăng nhập thành công !');
</script>
<%
} else {
%>
<script>
	showSwalAlert('error', 'Sai thông tin đăng nhập !');
</script>
<%
}
session.removeAttribute("loginAdmin");
}

if (loginAdminFail != null) {
if (loginAdminFail) {
%>
<script>
	showSwalAlert('warning', 'Tài khoản không hoạt động !');
</script>
<%
}
session.removeAttribute("loginAdminFail");
}
%>