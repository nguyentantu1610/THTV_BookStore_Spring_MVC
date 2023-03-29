<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>THTV BookStore - Table</title>
<meta name="description" content="THTV BookStore - Table">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
<link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
<link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
<link rel="stylesheet"
	href="assets/css/lib/datatable/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="assets/css/style.css">

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<!-- range slider -->

<!-- font awesome style -->
<link href="css/font-awesome.min.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="css/style1.css" rel="stylesheet" />
<!-- responsive style -->
<link href="css/responsive.css" rel="stylesheet" />

<style>
.dropdown1 {
	float: left;
	overflow: hidden;
}

.dropdown1 .dropbtn1 {
	font-size: 16px;
	border: none;
	outline: none;
	color: white;
	background-color: inherit;
	font-family: inherit;
	margin: 0;
}

.dropdown1:hover .dropbtn1 {
	color: #f3c93e;
}

.dropdown1-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown1-content a {
	float: none;
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	text-align: left;
}

.dropdown1-content a:hover {
	background-color: #ddd;
	color: black;
}

.dropdown1:hover .dropdown1-content {
	display: block;
}

.mySlides1 {
	display: none
}

.mySlides1>img {
	border-radius: 8px;
	box-shadow: 0 10px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19) !important;
}

/* Slideshow container */
.slideshow-container1 {
	max-width: 1000px;
	position: relative;
	margin: auto;
}

/* Next & previous buttons */
.prev1, .next1 {
	cursor: pointer;
	position: absolute;
	top: 50%;
	width: auto;
	padding: 16px;
	margin-top: -22px;
	color: white !important;
	font-weight: bold;
	font-size: 18px;
	transition: 0.6s ease;
	border-radius: 0 3px 3px 0;
	user-select: none;
}

/* Position the "next button" to the right */
.next1 {
	right: 0;
	border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev1:hover, .next1:hover {
	background-color: rgba(0, 0, 0, 0.8);
}

/* Number text (1/3 etc) */
.numbertext1 {
	color: #f2f2f2;
	font-size: 12px;
	padding: 8px 12px;
	position: absolute;
	top: 0;
}

/* The dots/bullets/indicators */
.dot1 {
	cursor: pointer;
	height: 15px;
	width: 15px;
	margin: 0 2px;
	background-color: #bbb;
	border-radius: 50%;
	display: inline-block;
	transition: background-color 0.6s ease;
}

.active1, .dot1:hover {
	background-color: #717171;
}

/* Fading animation */
.fade1 {
	animation-name: fade;
	animation-duration: 1.5s;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<!-- Left Panel -->
	<aside id="left-panel" class="left-panel" style="margin-top: 20px;">
		<nav class="navbar navbar-expand-sm navbar-default">
			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/webcomic/account"><i
							class="menu-icon fa fa-laptop"></i>Thông tin cá nhân </a></li>
					<li class="active"><a href="/webcomic/bill"><i
							class="menu-icon fa fa-laptop"></i>Đơn hàng của tôi</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</aside>
	<!-- /#left-panel -->
	<!-- Right Panel -->

	<div id="right-panel" class="right-panel">

		<!-- Content -->
		<div class="content">
			<div class="animated fadeIn">

				<div class="row">
					<div class="card" style="width: 100%; margin: 14px;">
						<div class="card-header">
							<strong>Thông tin đơn hàng</strong>
						</div>
						<div class="card-body card-block">
							<p id="err" style="color: red;"></p>
							<div class="form-group"
								style="width: 45%; float: left; margin-right: 5%;">
								<label for="billDate" class=" form-control-label">Ngày
									đặt hàng</label> <label style="color: red;">(*)</label> <input
									type="text" name="billDate" id="billDate"
									placeholder="Vui lòng nhập tên tài khoản" class="form-control"
									value="${bill.billDate}" disabled="disabled">
							</div>
							<div class="form-group"
								style="width: 45%; float: left; margin-right: 5%;">
								<label for="billPaymentMethod" class=" form-control-label">Phương
									thức thanh toán</label> <label style="color: red;">(*)</label> <input
									type="text" name="billPaymentMethod" id="billPaymentMethod"
									placeholder="Vui lòng nhập email" class="form-control"
									value="${bill.billPaymentMethod}" disabled="disabled">
							</div>
							<div class="form-group"
								style="width: 45%; float: left; margin-right: 5%;">
								<label for="billPhoneNumber" class=" form-control-label">SĐT</label>
								<label style="color: red;">(*)</label> <input type="text"
									name="billPhoneNumber" id="billPhoneNumber"
									placeholder="Vui lòng nhập sdt" class="form-control"
									value="${bill.billPhoneNumber}" disabled="disabled">
							</div>
							<div class="form-group"
								style="width: 45%; float: left; margin-right: 5%;">
								<label for="billAddress" class=" form-control-label">Địa
									chỉ</label> <label style="color: red;">(*)</label> <input type="text"
									name="billAddress" id="billAddress"
									placeholder="Vui lòng nhập địa chỉ" class="form-control"
									value="${bill.billDeliveryAddress}" disabled="disabled">
							</div>
							<div class="form-group"
								style="width: 45%; float: left; margin-right: 5%;">
								<label for="billTotal" class=" form-control-label">Tổng
									tiền</label> <label style="color: red;">(*)</label> <input type="text"
									name="billTotal" id="billTotal"
									placeholder="Vui lòng nhập địa chỉ" class="form-control"
									value="${bill.billTotal}" disabled="disabled">
							</div>
							<br /> <br />

							<div class="clearfix"></div>

							<table id="bootstrap-data-table"
								class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>STT</th>
										<th>Mã sản phẩm</th>
										<th>Giá</th>
										<th>Số lượng</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${billDetails}" varStatus="status">
										<tr>
											<td>${status.index + 1}</td>
											<td>${item.productID}</td>
											<td>${item.productCost}đ</td>
											<td>${item.productQuantity}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>

			</div>
		</div>



		<div class="clearfix"></div>

		<!-- Footer -->
		<%@ include file="footer.jsp"%>
		<!-- /.site-footer -->
	</div>
	<!-- /#right-panel -->

	<!-- Right Panel -->

	<!-- Scripts -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
	<script src="assets/js/main.js"></script>


	<script src="assets/js/lib/data-table/datatables.min.js"></script>
	<script src="assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
	<script src="assets/js/lib/data-table/dataTables.buttons.min.js"></script>
	<script src="assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
	<script src="assets/js/lib/data-table/jszip.min.js"></script>
	<script src="assets/js/lib/data-table/vfs_fonts.js"></script>
	<script src="assets/js/lib/data-table/buttons.html5.min.js"></script>
	<script src="assets/js/lib/data-table/buttons.print.min.js"></script>
	<script src="assets/js/lib/data-table/buttons.colVis.min.js"></script>
	<script src="assets/js/init/datatables-init.js"></script>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#bootstrap-data-table-export').DataTable();
		});
	</script>
</body>
</html>
