<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="description" content="THTV BookStore - Product Detail">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>THTV BookStore - Product Detail</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">

<!-- Responsive CSS -->
<link href="css/responsive1.css" rel="stylesheet">

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<!-- fonts style -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap"
	rel="stylesheet">
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

		<!-- <<<<<<<<<<<<<<<<<<<< Breadcumb Area Start <<<<<<<<<<<<<<<<<<<< -->
		<div class="breadcumb_area">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<ol class="breadcrumb d-flex align-items-center">
							<li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
							<li class="breadcrumb-item active">${product.productName}</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
		<!-- <<<<<<<<<<<<<<<<<<<< Breadcumb Area End <<<<<<<<<<<<<<<<<<<< -->

		<!-- <<<<<<<<<<<<<<<<<<<< Single Product Details Area Start >>>>>>>>>>>>>>>>>>>>>>>>> -->
		<section class="single_product_details_area section_padding_0_100">
			<div class="container">
				<div class="row">

					<div class="col-12 col-md-6">
						<div class="single_product_thumb">
							<div id="product_details_slider" class="carousel slide"
								data-ride="carousel">

								<ol class="carousel-indicators">
									<c:forEach items="${product.productImage}" var="item"
										varStatus="status">
										<c:if test="${status.index == 0}">
											<li class="active" data-target="#product_details_slider"
												data-slide-to="${status.index}"
												style="background-image: url(${item});"></li>
										</c:if>
										<c:if test="${status.index != 0}">
											<li data-target="#product_details_slider"
												data-slide-to="${status.index}"
												style="background-image: url(${item});"></li>
										</c:if>
									</c:forEach>
								</ol>

								<div class="carousel-inner">
									<c:forEach items="${product.productImage}" var="item"
										varStatus="status">
										<c:if test="${status.index == 0}">
											<div class="carousel-item active">
												<a class="gallery_img" href="${item}"> <img
													class="d-block w-100" src="${item}" alt="First slide">
												</a>
											</div>
										</c:if>
										<c:if test="${status.index != 0}">
											<div class="carousel-item">
												<a class="gallery_img" href="${item}"> <img
													class="d-block w-100" src="${item}" alt="First slide">
												</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12 col-md-6">
						<div class="single_product_desc">
							<h2 style="font-weight: bold; margin-bottom: 30px;">${product.productName}</h2>

							<div>
								<p style="float: left; width: 33%;">
									<strong>Nhà cung cấp:</strong> ${supplierName}
								</p>
								<p style="float: left; width: 33%;">
									<strong>Nhà xuất bản:</strong> ${publisherName}
								</p>
								<p style="float: left; width: 33%;">
									<strong>Năm XB:</strong> ${product.productYear}
								</p>
							</div>

							<div>
								<p style="float: left; width: 70%;">
									<strong>Tác giả:</strong> ${authors}
								</p>
								<p style="float: left; width: 30%;">
									<strong>Lứa tuổi:</strong> ${product.productLevel}
								</p>
							</div>

							<p>
								<strong>Thể loại: </strong> ${genres}
							</p>

							<h4 class="price" style="margin-top: 30px;">Giá:
								${productCost}</h4>

							<div class="single_product_ratings mb-15">
								<i class="fa fa-star" aria-hidden="true"></i> <i
									class="fa fa-star" aria-hidden="true"></i> <i
									class="fa fa-star" aria-hidden="true"></i> <i
									class="fa fa-star" aria-hidden="true"></i> <i
									class="fa fa-star" aria-hidden="true"></i>
							</div>

							<!-- Add to Cart Form -->
							<form class="cart clearfix mb-50 d-flex" method="post"
								style="margin-bottom: 20px;" action="/webcomic/cart/?add">
								<input type="hidden" value="${product.productID}"
									name="productID">
								<div class="quantity">
									<span class="qty-minus"
										onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i
										class="fa fa-minus" aria-hidden="true"></i></span> <input
										type="number" class="qty-text" id="qty" step="1" min="1"
										max="12" name="quantity" value="1"> <span
										class="qty-plus"
										onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i
										class="fa fa-plus" aria-hidden="true"></i></span>
								</div>
								<button type="submit" name="addtocart" value="5"
									style="width: 200px;" class="btn cart-submit d-block">Thêm
									vào giỏ hàng</button>
							</form>

							<p class="available">
								Có sẵn: <span class="text-muted">${product.productStock}</span>
							</p>

							<div id="accordion" role="tablist">
								<div class="card">
									<div class="card-header" role="tab" id="headingOne">
										<h6 class="mb-0">
											<a data-toggle="collapse" href="#collapseOne"
												aria-expanded="true" aria-controls="collapseOne">Nội
												dung</a>
										</h6>
									</div>

									<div id="collapseOne" class="collapse show" role="tabpanel"
										aria-labelledby="headingOne" data-parent="#accordion">
										<div class="card-body">${product.productDescription}</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- <<<<<<<<<<<<<<<<<<<< Single Product Details Area End >>>>>>>>>>>>>>>>>>>>>>>>> -->

		<section class="you_may_like_area clearfix">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="section_heading text-center">
							<h2>Các sản phẩm liên quan</h2>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<div class="you_make_like_slider owl-carousel">

							<c:forEach items="${products}" var="item" varStatus="status">

								<!-- Single gallery Item -->
								<div class="single_gallery_item" style="width: 20vw;">
									<!-- Product Image -->
									<div class="product-img">
										<a href="/webcomic/productDetail?productID=${item.productID}"><img
											src="${item.productImage[0]}" alt="image"></a>
									</div>
									<!-- Product Description -->
									<div class="product-description">
										<h4 class="product-price">${listCost[status.index]}</h4>
										<a href="/webcomic/productDetail?productID=${item.productID}">${item.productName}</a>
										<!-- Add to Cart -->
										<a href="/webcomic/cart?add&&productID=${item.productID}"
											class="add-to-cart-btn" style="margin-top: 15px;">Thêm
											vào giỏ hàng</a>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>
		</section>

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