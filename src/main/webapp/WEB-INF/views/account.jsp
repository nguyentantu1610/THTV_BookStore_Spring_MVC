<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>THTV BookStore - Checkout</title>
<meta name="description" content="THTV BookStore - Checkout">
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
<link rel="stylesheet" href="assets/css/style.css">
<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<link
	href="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/jqvmap@1.5.1/dist/jqvmap.min.css"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/weathericons@2.1.0/css/weather-icons.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.css"
	rel="stylesheet" />

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
#weatherWidget .currentDesc {
	color: #ffffff !important;
}

.traffic-chart {
	min-height: 335px;
}

#flotPie1 {
	height: 150px;
}

#flotPie1 td {
	padding: 3px;
}

#flotPie1 table {
	top: 20px !important;
	right: -10px !important;
}

.chart-container {
	display: table;
	min-width: 270px;
	text-align: left;
	padding-top: 10px;
	padding-bottom: 10px;
}

#flotLine5 {
	height: 105px;
}

#flotBarChart {
	height: 150px;
}

#cellPaiChart {
	height: 160px;
}

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
					<!-- <div class="col-lg-6"> -->
					<div class="card" style="width: 100%; margin: 14px;">
						<div class="card-header">
							<strong>Thông tin tài khoản</strong>
						</div>
						<form method="post" id="insert-form" accept-charset="UTF-8"
							action="/webcomic/account">
							<div class="card-body card-block">
								<p id="err" style="color: red;"></p>
								<input type="hidden" name="userID" id="userID"
									value="${user.userID}">
								<div class="form-group"
									style="width: 45%; float: left; margin-right: 5%;">
									<label for="userName" class=" form-control-label">Tên
										tài khoản</label> <label style="color: red;">(*)</label> <input
										type="text" name="userName" id="userName"
										placeholder="Vui lòng nhập tên tài khoản" class="form-control"
										value="${user.userName}">
								</div>
								<div class="form-group"
									style="width: 45%; float: left; margin-right: 5%;">
									<label for="userName" class=" form-control-label">Email</label>
									<label style="color: red;">(*)</label> <input type="email"
										name="userEmail" id="userEmail"
										placeholder="Vui lòng nhập email" class="form-control"
										value="${user.userEmail}" disabled="disabled">
								</div>
								<div class="form-group"
									style="width: 45%; float: left; margin-right: 5%;">
									<label for="userName" class=" form-control-label">SĐT</label> <label
										style="color: red;">(*)</label> <input type="number"
										name="userPhoneNumber" id="userPhoneNumber"
										placeholder="Vui lòng nhập sdt" class="form-control"
										value="${user.userPhoneNumber}">
								</div>
								<div class="form-group"
									style="width: 45%; float: left; margin-right: 5%;">
									<label for="userName" class=" form-control-label">Địa
										chỉ</label> <label style="color: red;">(*)</label> <input type="text"
										name="userAddress" id="userAddress"
										placeholder="Vui lòng nhập địa chỉ" class="form-control"
										value="${user.userAddress}">
								</div>
								<button type="button" onclick="submitForm()"
									class="btn btn-warning" style="color: white;">Cập nhật</button>
								<br /> <br />

							</div>
						</form>

					</div>

				</div>
			</div>
			<!-- .animated -->
		</div>
		<!-- .content -->
		<div class="clearfix"></div>
		<!-- Footer -->
		<%@ include file="footer.jsp"%><!-- /.site-footer -->
	</div>
	<!-- /#right-panel -->

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

	<!--  Chart js -->
	<script
		src="https://cdn.jsdelivr.net/npm/chart.js@2.7.3/dist/Chart.bundle.min.js"></script>

	<!--Chartist Chart-->
	<script
		src="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/chartist-plugin-legend@0.6.2/chartist-plugin-legend.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery.flot@0.8.3/jquery.flot.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/flot-pie@1.0.0/src/jquery.flot.pie.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/flot-spline@0.0.1/js/jquery.flot.spline.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/simpleweather@3.1.0/jquery.simpleWeather.min.js"></script>
	<script src="assets/js/init/weather-init.js"></script>

	<script src="https://cdn.jsdelivr.net/npm/moment@2.22.2/moment.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.js"></script>
	<script src="assets/js/init/fullcalendar-init.js"></script>

	<!--Local Stuff-->
	<script>
		function submitForm() {
			var userName = document.getElementById('userName').value.trim();
			var userEmail = document.getElementById('userEmail').value.trim();
			var userPhoneNumber = document.getElementById('userPhoneNumber').value
					.trim();
			var userAddress = document.getElementById('userAddress').value
					.trim();

			if (userName != "" && userEmail != "" && userPhoneNumber != ""
					&& userAddress != "") {
				document.getElementById('insert-form').submit();
			} else {
				document.getElementById('err').innerHTML = "Vui lòng điền đủ các ô (*)";
			}
		}
	</script>
</body>
</html>
