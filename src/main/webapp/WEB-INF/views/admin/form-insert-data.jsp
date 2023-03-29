<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>THTV BookStore - Form</title>
<meta name="description" content="THTV BookStore - Form">
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
<link rel="stylesheet" href="../assets/css/cs-skin-elastic.css">
<link rel="stylesheet" href="../assets/css/style.css">

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>

</head>
<body>

	<%@ include file="leftpanel.jsp"%>

	<!-- Right Panel -->
	<div id="right-panel" class="right-panel">

		<%@ include file="rightpanel-header.jsp"%>

		<div class="breadcrumbs">
			<div class="breadcrumbs-inner">
				<div class="row m-0">
					<div class="col-sm-4">
						<div class="page-header float-left">
							<div class="page-title">
								<h1>
									<a
										<c:if test="${formName == 'danh mục'}">href="/webcomic/admin/category?list"</c:if>
										<c:if test="${formName == 'tác giả'}">href="/webcomic/admin/author?list"</c:if>
										<c:if test="${formName == 'thể loại'}">href="/webcomic/admin/genre?list"</c:if>
										<c:if test="${formName == 'nhà xuất bản'}">href="/webcomic/admin/publisher?list"</c:if>
										<c:if test="${formName == 'nhà cung cấp'}">href="/webcomic/admin/supplier?list"</c:if>
										<c:if test="${formName == 'bộ sách'}">href="/webcomic/admin/series?list"</c:if>
										<c:if test="${formName == 'sách'}">href="/webcomic/admin/product?list"</c:if>
										<c:if test="${formName == 'người dùng'}">href="/webcomic/admin/user?list"</c:if>
										<c:if test="${formName == 'đơn hàng'}">href="/webcomic/admin/bill?list"</c:if>>
										Trở về </a>
								</h1>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="page-header float-right">
							<div class="page-title">
								<ol class="breadcrumb text-right">
									<li><a href="#">${menuItem}</a></li>
									<li class="active">${formName}</li>
								</ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="content">
			<div class="animated fadeIn">

				<div class="row">
					<div class="card" style="width: 100%; margin: 14px;">
						<div class="card-header">
							<strong>Thêm ${formName}</strong>
						</div>
						<form method="post" id="insert-form" accept-charset="UTF-8"
							<c:if test="${formName == 'danh mục' && allID == null}">action="/webcomic/admin/category?create"</c:if>
							<c:if test="${formName == 'tác giả' && allID == null}">action="/webcomic/admin/author?create"</c:if>
							<c:if test="${formName == 'thể loại' && allID == null}">action="/webcomic/admin/genre?create"</c:if>
							<c:if test="${formName == 'nhà xuất bản' && allID == null}">action="/webcomic/admin/publisher?create"</c:if>
							<c:if test="${formName == 'nhà cung cấp' && allID == null}">action="/webcomic/admin/supplier?create"</c:if>
							<c:if test="${formName == 'bộ sách' && allID == null}">action="/webcomic/admin/series?create"</c:if>
							<c:if test="${formName == 'sách' && allID == null}">action="/webcomic/admin/product?create"</c:if>
							<c:if test="${formName == 'danh mục' && allID != null}">action="/webcomic/admin/category?update"</c:if>
							<c:if test="${formName == 'tác giả' && allID != null}">action="/webcomic/admin/author?update"</c:if>
							<c:if test="${formName == 'thể loại' && allID != null}">action="/webcomic/admin/genre?update"</c:if>
							<c:if test="${formName == 'nhà xuất bản' && allID != null}">action="/webcomic/admin/publisher?update"</c:if>
							<c:if test="${formName == 'nhà cung cấp' && allID != null}">action="/webcomic/admin/supplier?update"</c:if>
							<c:if test="${formName == 'bộ sách' && allID != null}">action="/webcomic/admin/series?update"</c:if>
							<c:if test="${formName == 'sách' && allID != null}">action="/webcomic/admin/product?update"</c:if>
							<c:if test="${formName == 'người dùng'}">action="/webcomic/admin/user?update"</c:if>
							<c:if test="${formName == 'đơn hàng'}">action="/webcomic/admin/bill?update"</c:if>>
							<div class="card-body card-block">
								<p id="err" style="color: red;"></p>
								<input type="hidden" name="allID" id="allID"
									value="${allID}${user.userID}${bill.billID}">
								<c:if
									test="${formName != 'sách' && formName != 'người dùng' && formName != 'đơn hàng'}">
									<div class="form-group" style="width: 40%;">
										<label for="allName" class=" form-control-label">Tên
											${formName}</label> <label style="color: red;">(*)</label> <input
											type="text" name="allName" id="allName"
											placeholder="Vui lòng nhập tên ${formName}"
											class="form-control" value="${allName}">
									</div>
								</c:if>
								<c:if test="${formName == 'nhà cung cấp'}">
									<div class="form-group" style="width: 40%;">
										<label for="supplierPhoneNumber" class=" form-control-label">Số
											điện thoại NCC</label> <label style="color: red;">(*)</label> <input
											type="number" maxlength="10" name="supplierPhoneNumber"
											id="supplierPhoneNumber"
											placeholder="Vui lòng nhập tên số điện thoại"
											class="form-control" value="${supplierPhoneNumber}">
									</div>
									<div class="form-group" style="width: 40%;">
										<label for="supplierAddress" class=" form-control-label">Địa
											chỉ NCC</label> <label style="color: red;">(*)</label> <input
											type="email" name="supplierAddress" id="supplierAddress"
											placeholder="Vui lòng nhập địa chỉ" class="form-control"
											value="${supplierAddress}">
									</div>
								</c:if>
								<c:if test="${formName == 'sách'}">
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="allName" class=" form-control-label">Tên
											${formName}</label> <label style="color: red;">(*)</label> <input
											type="text" name="allName" id="allName"
											placeholder="Vui lòng nhập tên ${formName}"
											class="form-control" value="${allName}">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="productYear" class=" form-control-label">Năm
											Xuất Bản</label> <label style="color: red;">(*)</label> <input
											type="number" name="productYear" id="productYear"
											placeholder="Vui lòng nhập năm xuất bản" class="form-control"
											value="${product.productYear}">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="productLevel" class=" form-control-label">Lứa
											tuổi</label> <label style="color: red;">(*)</label> <input
											type="text" name="productLevel" id="productLevel"
											placeholder="Vui lòng nhập lứa tuổi" class="form-control"
											value="${product.productLevel}">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="productCost" class=" form-control-label">Giá</label>
										<label style="color: red;">(*)</label> <input type="number"
											name="productCost" id="productCost"
											placeholder="Vui lòng nhập giá" class="form-control"
											value="${product.productCost}">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="productStock" class=" form-control-label">Số
											lượng tồn</label> <label style="color: red;">(*)</label> <input
											type="text" name="productStock" id="productStock"
											placeholder="Vui lòng nhập số lượng tồn" class="form-control"
											value="${product.productStock}">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="categoryID" class=" form-control-label">Danh
											mục</label> <label style="color: red;">(*)</label> <select
											name="categoryID" id="categoryID" class="form-control">
											<c:if test="${product.categoryID == null}">
												<option value="0">Vui lòng chọn danh mục</option>
											</c:if>
											<c:if test="${product.categoryID != null}">
												<option value="${product.categoryID}">${categoryID}</option>
											</c:if>

											<c:forEach var="item" items="${listCategory}">
												<option value="${item.categoryID}">${item.categoryName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="seriesID" class=" form-control-label">Bộ
											sách</label> <label style="color: red;">(*)</label> <select
											name="seriesID" id="seriesID" class="form-control">
											<c:if test="${product.seriesID == null}">
												<option value="0">Vui lòng chọn bộ sách</option>
											</c:if>
											<c:if test="${product.seriesID != null}">
												<option value="${product.seriesID}">${seriesID}</option>
											</c:if>

											<c:forEach var="item" items="${listSeries}">
												<option value="${item.seriesID}">${item.seriesName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="supplierID" class=" form-control-label">Nhà
											cung cấp</label> <label style="color: red;">(*)</label> <select
											name="supplierID" id="supplierID" class="form-control">
											<c:if test="${product.supplierID == null}">
												<option value="0">Vui lòng chọn nhà cung cấp</option>
											</c:if>
											<c:if test="${product.supplierID != null}">
												<option value="${product.supplierID}">${supplierID}</option>
											</c:if>

											<c:forEach var="item" items="${listSupplier}">
												<option value="${item.supplierID}">${item.supplierName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="authorID" class=" form-control-label">Tác
											giả</label> <label style="color: red;">(*)</label> <select
											name="authorID" id="authorID" multiple class="form-control">
											<c:forEach var="item" items="${listAuthor}">
												<option value="${item.authorID}">${item.authorName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="genreID" class=" form-control-label">Thể
											loại</label> <label style="color: red;">(*)</label> <select
											name="genreID" id="genreID" multiple class="form-control">
											<c:forEach var="item" items="${listGenre}">
												<option value="${item.genreID}">${item.genreName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="productDescription" class=" form-control-label">Mô
											tả</label> <label style="color: red;">(*)</label>
										<textarea name="productDescription" id="productDescription"
											rows="9" placeholder="Content..." class="form-control">${product.productDescription}</textarea>
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="publisherID" class=" form-control-label">Nhà
											cung cấp</label> <label style="color: red;">(*)</label> <select
											name="publisherID" id="publisherID" class="form-control">
											<c:if test="${product.publisherID == null}">
												<option value="0">Vui lòng chọn nhà xuất bản</option>
											</c:if>
											<c:if test="${product.publisherID != null}">
												<option value="${product.publisherID}">${publisherID}</option>
											</c:if>

											<c:forEach var="item" items="${listPublisher}">
												<option value="${item.publisherID}">${item.publisherName}</option>
											</c:forEach>
										</select>
									</div>
								</c:if>
								<c:if test="${formName == 'người dùng'}">
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="allName" class=" form-control-label">Tên
											tài khoản</label> <label style="color: red;">(*)</label> <input
											type="text" name="allName" id="allName"
											placeholder="Vui lòng nhập tên tài khoản"
											class="form-control" value="${user.userName}"
											disabled="disabled">
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
										<label for="userName" class=" form-control-label">SĐT</label>
										<label style="color: red;">(*)</label> <input type="number"
											name="userPhoneNumber" id="userPhoneNumber"
											placeholder="Vui lòng nhập sdt" class="form-control"
											value="${user.userPhoneNumber}" disabled="disabled">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="userName" class=" form-control-label">Địa
											chỉ</label> <label style="color: red;">(*)</label> <input type="text"
											name="userAddress" id="userAddress"
											placeholder="Vui lòng nhập địa chỉ" class="form-control"
											value="${user.userAddress}" disabled="disabled">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="userState" class=" form-control-label">Trạng
											thái </label> <label style="color: red;">(*)</label> <select
											name="
											userState" id=" userState"
											class="form-control">
											<c:if test="${user.userState == true}">
												<option value="true" selected="selected">Hoạt động</option>
												<option value="false">Ngưng hoạt động</option>
											</c:if>
											<c:if test="${user.userState == false}">
												<option value="true">Hoạt động</option>
												<option value="false" selected="selected">Ngưng
													hoạt động</option>
											</c:if>
										</select>
									</div>
								</c:if>
								<c:if test="${formName == 'đơn hàng'}">
								<input type="hidden" value="abc" name="allName" id="allName"> 
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="billDate" class=" form-control-label">Ngày
											đặt hàng</label> <label style="color: red;">(*)</label> <input
											type="text" name="billDate" id="billDate"
											placeholder="Vui lòng nhập tên tài khoản"
											class="form-control" value="${bill.billDate}"
											disabled="disabled">
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
											tiền</label> <label style="color: red;">(*)</label> <input
											type="text" name="billTotal" id="billTotal"
											placeholder="Vui lòng nhập địa chỉ" class="form-control"
											value="${bill.billTotal}" disabled="disabled">
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="billState" class=" form-control-label">Trạng
											thái </label> <label style="color: red;">(*)</label> <select
											name="billState" id="billState" class="form-control">
											<option value="Chưa duyệt"
												<c:if test="${bill.billState == 'Chưa duyệt'}">selected="selected"</c:if>>
												Chưa duyệt</option>
											<option value="Đã duyệt"
												<c:if test="${bill.billState == 'Đã duyệt'}">selected="selected"</c:if>>
												Đã duyệt</option>
											<option value="Đã giao hàng"
												<c:if test="${bill.billState == 'Đã giao hàng'}">selected="selected"</c:if>>
												Đã giao hàng</option>
										</select>
									</div>
									<div class="form-group"
										style="width: 45%; float: left; margin-right: 5%;">
										<label for="billNote" class=" form-control-label">Mô
											tả</label> <label style="color: red;">(*)</label>
										<textarea name="billNote" id="billNote" rows="9"
											placeholder="Content..." class="form-control">${bill.billNote}</textarea>
									</div>

									<br />
									<br />

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
											<c:forEach var="item1" items="${billDetails}"
												varStatus="status1">
												<tr>
													<td>${status1.index + 1}</td>
													<td>${item1.productID}</td>
													<td>${item1.productCost}đ</td>
													<td>${item1.productQuantity}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>


								<c:if
									test="${allID == null && formName != 'người dùng' && formName != 'đơn hàng'}">
									<button type="button" onclick="submitForm()"
										class="btn btn-primary">Thêm</button>
								</c:if>
								<c:if
									test="${allID != null || formName == 'người dùng' || formName == 'đơn hàng'}">
									<button type="button" onclick="submitForm()"
										class="btn btn-warning" style="color: white;">Cập
										nhật</button>
								</c:if>
								<br /> <br />

							</div>
						</form>
						<c:if test="${formName == 'sách'}">
							<div class="form-group" style="margin-left: 3%;">
								<form method="post" action="/webcomic/admin/product?excel"
									enctype="multipart/form-data">
									<label for="file" class=" form-control-label">Chọn file
										excel &nbsp;</label> <input type="file" name="file" id="file" value=""
										accept=".xlsx"> <br /> <br />
									<button type="submit" class="btn btn-success">Thêm
										Excel</button>
								</form>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<!-- .animated -->
		</div>
		<!-- .content -->

		<div class="clearfix"></div>

		<%@ include file="rightpanel-footer.jsp"%>

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
	<script src="../assets/js/main.js"></script>
	<script>
		function submitForm() {
			var name = document.getElementById('allName').value.trim();
			var test = ${formName == 'nhà cung cấp'};
			var test1 = ${formName == 'sách'};
			var test2 = ${formName == 'người dùng'};
			var test3 = ${formName == 'đơn hàng'};
			if (test2 == true || test3 == true) {
				document.getElementById('insert-form').submit();
			}
			if (name != "") {
				if (test == true) {
					var phone = document.getElementById('supplierPhoneNumber').value
							.trim();
					var address = document.getElementById('supplierAddress').value
							.trim();
					if (phone != "" && address != "") {
						document.getElementById('insert-form').submit();
					} else {
						document.getElementById('err').innerHTML = "Vui lòng điền đủ các ô (*)";
					}
				} else if (test1 == true) {
					var category = document.getElementById('categoryID').value
							.trim();
					var genre = document.getElementById('genreID').value.trim();
					var series = document.getElementById('seriesID').value
							.trim();
					var author = document.getElementById('authorID').value
							.trim();
					var supplier = document.getElementById('supplierID').value
							.trim();
					var publisher = document.getElementById('publisherID').value
							.trim();
					var description = document
							.getElementById('productDescription').value.trim();
					var cost = document.getElementById('productCost').value
							.trim();
					var year = document.getElementById('productYear').value
							.trim();
					var stock = document.getElementById('productStock').value
							.trim();
					var level = document.getElementById('productLevel').value
							.trim();
					if (category != "" && genre != "" && series != ""
							&& author != "" && supplier != ""
							&& publisher != "" && description != ""
							&& cost != "" && year != "" && stock != ""
							&& level != "") {
						document.getElementById('insert-form').submit();
					} else {
						document.getElementById('err').innerHTML = "Vui lòng điền đủ các ô (*)";
					}
				} else {
					document.getElementById('insert-form').submit();
				}
			} else {
				document.getElementById('err').innerHTML = "Vui lòng điền đủ các ô (*)";
			}
		}
	</script>

</body>
</html>
