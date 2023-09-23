<%@page import="Controll.Dao.Impl.UserDaoImpl" %>
<%@page import="Controll.Dao.UserDao" %>
<%@page import="Controll.Service.Impl.HoaDonServiceImpl" %>
<%@page import="Controll.Service.HoaDonService" %>
<%@page import="Controll.Dao.Impl.HoaDonImpl" %>
<%@page import="Controll.Dao.HoaDonDao" %>
<%@page import="Controll.Entity.Hoadon" %>
<%@page import="Controll.Service.Impl.VideoServiceImpl" %>
<%@page import="Controll.Service.VideoService" %>
<%@page import="Controll.Entity.Video" %>
<%@page import="Controll.Contanst.SessionAtrr" %>
<%@page import="Controll.Entity.User" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="java.nio.charset.StandardCharsets" %>
<%@page import="Controll.VNPAY.Config" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/views/common/taglib.jsp" %>

<%@page import="java.util.Enumeration" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head contenust come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>VNPAY RESPONSE</title>

    <link rel="shortcut icon" type="text/html"
          href="<c:url value='/VNPAY//VNPAY//VNPAY/assets/favicon.png'/>">
    <!-- Bootstrap core CSS -->
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="sweetalert2.min.css">
    <link href="/VNPAY//VNPAY//VNPAY/assets/jumbotron-narrow.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

</head>
<style>
    body {
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f0f0f0;
    }

    .success-container {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        align-items: center;
        padding: 50px;
        animation: slideUp 2s linear;
        background: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }

    @keyframes slideUp {
        0% {

        }

        100% {
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
    }

    .tick-animation {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .payment-details {
        margin-top: 50px;
    }
</style>
<body>
<%
    //Begin process return from VNPAY
    Map fields = new HashMap();
    for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
        String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
        String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
        if ((fieldValue != null) && (fieldValue.length() > 0)) {
            fields.put(fieldName, fieldValue);
        }
    }

    String vnp_SecureHash = request.getParameter("vnp_SecureHash");
    if (fields.containsKey("vnp_SecureHashType")) {
        fields.remove("vnp_SecureHashType");
    }
    if (fields.containsKey("vnp_SecureHash")) {
        fields.remove("vnp_SecureHash");
    }
    String signValue = Config.hashAllFields(fields);
%>


<%
    HoaDonDao hoaDonDao = new HoaDonImpl();
    UserDao userDao = new UserDaoImpl();

    HoaDonService hoadonService = new HoaDonServiceImpl();
    VideoService videoService = new VideoServiceImpl();

    //lấy ra user đang đăng nhập
    User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);

    if (user != null) {
        //thêm data của VNPay trả về vào csdl
        String href = (String) session.getAttribute("hrefSession");
        //lấy ra được video
        Video video = videoService.findByHref(href);

        if (video != null && user != null) {
            String vnp_TxnRef = request.getParameter("vnp_TxnRef");
            String Vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
            String Vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            String Vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
            String Vnp_BankCode = request.getParameter("vnp_BankCode");
            String vnp_Amount = request.getParameter("vnp_Amount");
            String Vnp_PayDate = request.getParameter("vnp_PayDate");

            Hoadon hd = hoadonService.findHoaDonByUserIdAndVideoId(user.getId(), video.getId());
            if (hd != null) {
                hoadonService.remove(user.getId(), video.getId());
                hoadonService.create(vnp_TxnRef, video, user, Vnp_OrderInfo, Vnp_ResponseCode,
                        Vnp_TransactionNo, Vnp_BankCode, vnp_Amount);
            } else {
                hoadonService.create(vnp_TxnRef, video, user, Vnp_OrderInfo, Vnp_ResponseCode,
                        Vnp_TransactionNo, Vnp_BankCode, vnp_Amount);
            }
        }
    }
%>

<div class="success-container">
    <h5 class="paymentTitle mb-5 text-success" style="display: block;">Giao
        Dịch Thành Công</h5>
    <div class="animate__animated animate__tada tick-animation">
        <a href="http://localhost:8080/index"><img
                src="/VNPAY/assets/favicon.png" width="100px"
                alt="Success tick animation"></a>
    </div>
    <div class="payment-details" style="display: none;">
        <div class="d-flex justify-content-between">
            <p>Mã giao dịch: &nbsp;</p>
            <p class="fw-bold"><%=request.getParameter("vnp_TxnRef")%>
            </p>
        </div>
        <div class="d-flex justify-content-between">
            <p>Tên phim: &nbsp;</p>
            <p class="fw-bold"><%=session.getAttribute("titleSession")%>
            </p>
        </div>
        <div class="d-flex justify-content-between">
            <p>Số tiền: &nbsp;</p>
            <p id="price" class="fw-bold"></p>
        </div>
        <div class="d-flex justify-content-between">
            <p>Ngày thanh toán: &nbsp;</p>
            <p id="datePayment" class="fw-bold"></p>
            <input type="hidden" id=datePaymentInput name="datePayMent">
        </div>
        <div class="d-flex justify-content-between">
            <p>Trạng thái: &nbsp;</p>
            <p class="fw-bold"
               style="color: <%=signValue.equals(vnp_SecureHash) && "00".equals(request.getParameter("vnp_TransactionStatus"))
		? "green"
		: "red"%>;">
                <%
                    if (signValue.equals(vnp_SecureHash)) {
                        if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                            out.print("Thành Công");
                        } else {
                            out.print("Thất Bại");
                        }
                    } else {
                        out.print("invalid signature");
                    }
                %>
            </p>
        </div>
        <div class="d-flex justify-content-between">
            <p>Tên ngân hàng: &nbsp;</p>
            <p class="fw-bold"><%=request.getParameter("vnp_BankCode")%>
            </p>
        </div>
        <div class="d-flex justify-content-between">
            <p>Ghi chú: &nbsp;</p>
            <p class="fw-bold"><%=request.getParameter("vnp_OrderInfo")%>
            </p>
        </div>
    </div>
</div>

<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="/VNPAY/assets/jquery-1.11.3.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>

<script type="text/javascript">
    Swal.fire(
        'Cảnh Báo',
        'Chúng tôi sẽ chuyển hướng về trang chủ khi bạn click vào logo VNPAY...',
        'warning'
    )

    //format giờ
    const payDate = '<%=request.getParameter("vnp_PayDate")%>';
    const Time = moment.utc(payDate, "YYYYMMDDHHmmss").utcOffset(7);
    const formattedDateTime = Time.format("DD/MM/YYYY, HH:mm:ss");
    document.getElementById("datePayment").textContent = formattedDateTime;
    const inputElement = document.getElementById("datePaymentInput");
    inputElement.value = formattedDateTime;

    //format giá tiền
    const price = '<%=request.getParameter("vnp_Amount")%>';
    const formattedPrice = (price / 100000).toFixed(3).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    document.getElementById("price").textContent = formattedPrice + ' VNĐ';

    //animate tion thanh toán thành công
    setTimeout(() => {
        const paymentDetails = document.querySelector('.payment-details');
        const paymentTitle = document.querySelector('.paymentTitle');
        paymentDetails.style.display = 'block';
        paymentTitle.style.display = 'block';
    }, 2000);
</script>

</body>
</html>
