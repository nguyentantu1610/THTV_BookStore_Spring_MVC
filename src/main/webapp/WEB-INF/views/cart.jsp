<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="description" content="THTV BookStore - Cart">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>THTV BookStore - Cart</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">

<!-- Responsive CSS -->
<link href="css/responsive1.css" rel="stylesheet">

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

	<div id="wrapper">

		<%@ include file="header.jsp"%>

		<!-- ****** Cart Area Start ****** -->
		<div class="cart_area section_padding_100 clearfix">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="cart-table clearfix">
							<table class="table table-responsive">
								<thead>
									<tr>
										<th>Sản phẩm</th>
										<th>Giá</th>
										<th>Số lượng</th>
										<th>Tổng tiền</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${carts}" var="item" varStatus="status">

										<tr>
											<td class="cart_product_img d-flex align-items-center"><a
												href="#"><img src="${item.productImage}" alt="Product"></a>
												<h6>${item.productName}</h6></td>
											<td class="price"><span>${item.cost} đ</span></td>
											<td class="qty">
												<div class="quantity">
													<form method="post" action="/webcomic/cart?change"
														id="form${status.index}">
														<input type="hidden" value="${item.productID}"
															name="productID" id="productID"> <span
															style="width: 20px;" class="qty-minus"
															onclick="submitForm(${status.index},-1)"><a><i
																class="fa fa-minus" aria-hidden="true"></i></a></span> <input
															type="number" class="qty-text" id="qty${status.index}"
															step="1" min="1" max="99" name="quantity"
															style="width: 40px;" value="${item.quantity}"> <span
															class="qty-plus" style="width: 20px;"
															onclick="submitForm(${status.index},1)"><a><i
																class="fa fa-plus" aria-hidden="true"></i></a></span>

													</form>
												</div> <script>
													function submitForm(num,mima) {
														var effect = document.getElementById('qty'+num);
														let qty = effect.value;
														let x = mima + parseInt(qty, 10);
														if (!isNaN(x) && x > 0) {
															effect.value = x;
															let total = document.getElementById('total'+num);
															total.innerHTML = x * ${item.cost};
															document.getElementById('form'+num).submit();
														}
													}
												</script>
											</td>
											<td class="total_price"><span id="total${status.index}">${item.cost * item.quantity}
													đ</span></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

						<div class="cart-footer d-flex mt-30">
							<div class="back-to-shop w-50">
								<a href="/webcomic/">Tiếp tục mua sắm</a>
							</div>
							<div class="update-checkout w-50 text-right">
								<a href="/webcomic/cart?delete">Xoá giỏ hàng</a>
							</div>
						</div>

					</div>
				</div>

				<div class="row">
					<div class="col-12 col-lg-4">
						<div class="cart-total-area mt-70">
							<c:if test="${carts.size() > 0}">
								<a href="/webcomic/cart?checkout" class="btn karl-checkout-btn"
									style="background-color: #ff084e; color: white; padding-top: 15px; font-weight: bold;">Thanh
									Toán</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ****** Cart Area End ****** -->

		<%@ include file="footer.jsp"%>
	</div>
	<!-- /.wrapper end -->

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script src="js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="js/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Plugins js -->
	<script src="js/plugins.js"></script>
	<!-- Active js -->
	<script src="js/active.js"></script>

</body>

</html>