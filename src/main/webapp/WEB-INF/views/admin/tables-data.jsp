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
<link rel="stylesheet" href="../assets/css/cs-skin-elastic.css">
<link rel="stylesheet"
	href="../assets/css/lib/datatable/dataTables.bootstrap.min.css">
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
								<c:if test="${content != 'user' && content != 'bill' }">
									<a
										<c:if test="${content == 'category'}">href="/webcomic/admin/category?create" class="btn btn-primary"</c:if>
										<c:if test="${content == 'author'}">href="/webcomic/admin/author?create" class="btn btn-primary"</c:if>
										<c:if test="${content == 'supplier'}">href="/webcomic/admin/supplier?create" class="btn btn-primary"</c:if>
										<c:if test="${content == 'publisher'}">href="/webcomic/admin/publisher?create" class="btn btn-primary"</c:if>
										<c:if test="${content == 'genre'}">href="/webcomic/admin/genre?create" class="btn btn-primary"</c:if>
										<c:if test="${content == 'series'}">href="/webcomic/admin/series?create" class="btn btn-primary"</c:if>
										<c:if test="${content == 'product'}">href="/webcomic/admin/product?create" class="btn btn-primary"</c:if>
										style="margin-top: 7px; margin-right: 10px;"> <i
										class="fa fa-plus-square"></i>&nbsp; Thêm mới
									</a>
								</c:if>
								<c:if test="${content == 'product'}">
									<a href="/webcomic/admin/product?excel" class="btn btn-success"
										style="margin-top: 7px; float: right;"> <i
										class="fa fa-print"></i>&nbsp; In
									</a>
								</c:if>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="page-header float-right">
							<div class="page-title">
								<ol class="breadcrumb text-right">
									<li><a href="#">${parentItem}</a></li>
									<li class="active">${childItem}</li>
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
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">Bảng các ${childItem}</strong>
							</div>
							<div class="card-body">
								<table id="bootstrap-data-table"
									class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>STT</th>
											<c:if test="${content == 'bill'}">
												<th>Mã người dùng</th>
												<th>Ngày</th>
												<th>SĐT</th>
												<th>Trạng thái</th>
											</c:if>
											<c:if test="${content == 'product'}">
												<th>Hình ảnh</th>
											</c:if>
											<c:if test="${content != 'bill'}">
												<th>Tên ${childItem}</th>
											</c:if>
											<c:if test="${content == 'supplier' || content == 'user'}">
												<th>Địa chỉ</th>
												<th>SĐT</th>
											</c:if>
											<c:if test="${content == 'product'}">
												<th>Số lượng tồn</th>
												<th>Giá</th>
											</c:if>
											<c:if test="${content == 'user'}">
												<th>Email</th>
											</c:if>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${list}" varStatus="status">
											<tr>
												<td>${status.index + 1}</td>
												<c:if test="${content == 'category'}">
													<td>${item.categoryName}</td>
													<td><a
														href="/webcomic/admin/category?update&&categoryID=${item.categoryID}"
														class="btn btn-warning"> <i class="fa fa-pencil"></i>
													</a> <span>&nbsp;</span> <a
														href="/webcomic/admin/category?delete&&categoryID=${item.categoryID}"
														class="btn btn-danger"> <i class="fa fa-times"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'genre'}">
													<td>${item.genreName}</td>
													<td><a
														href="/webcomic/admin/genre?update&&genreID=${item.genreID}"
														class="btn btn-warning"> <i class="fa fa-pencil"></i>
													</a> <span>&nbsp;</span> <a
														href="/webcomic/admin/genre?delete&&genreID=${item.genreID}"
														class="btn btn-danger"> <i class="fa fa-times"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'author'}">
													<td>${item.authorName}</td>
													<td><a
														href="/webcomic/admin/author?update&&authorID=${item.authorID}"
														class="btn btn-warning"> <i class="fa fa-pencil"></i>
													</a><span>&nbsp;</span> <a
														href="/webcomic/admin/author?delete&&authorID=${item.authorID}"
														class="btn btn-danger"> <i class="fa fa-times"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'supplier'}">
													<td>${item.supplierName}</td>
													<td>${item.supplierAddress}</td>
													<td>${item.supplierPhoneNumber}</td>
													<td><a
														href="/webcomic/admin/supplier?update&&supplierID=${item.supplierID}"
														class="btn btn-warning"> <i class="fa fa-pencil"></i>
													</a> <span>&nbsp;</span> <a
														href="/webcomic/admin/supplier?delete&&supplierID=${item.supplierID}"
														class="btn btn-danger"> <i class="fa fa-times"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'publisher'}">
													<td>${item.publisherName}</td>
													<td><a
														href="/webcomic/admin/publisher?update&&publisherID=${item.publisherID}"
														class="btn btn-warning"> <i class="fa fa-pencil"></i>
													</a> <span>&nbsp;</span> <a
														href="/webcomic/admin/publisher?delete&&publisherID=${item.publisherID}"
														class="btn btn-danger"> <i class="fa fa-times"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'series'}">
													<td>${item.seriesName}</td>
													<td><a
														href="/webcomic/admin/series?update&&seriesID=${item.seriesID}"
														class="btn btn-warning"> <i class="fa fa-pencil"></i>
													</a> <span>&nbsp;</span> <a
														href="/webcomic/admin/series?delete&&seriesID=${item.seriesID}"
														class="btn btn-danger"> <i class="fa fa-times"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'product'}">
													<td><img src="../${item.productImage[0]}" alt="image"
														style="width: 7vw;"></td>
													<td>${item.productName}</td>
													<td>${item.productStock}</td>
													<td>${listCost[status.index]}</td>
													<td><a type="button" class="btn btn-primary"
														style="color: white;"
														href="/webcomic/admin/product?image&&productID=${item.productID}">
															<i class="fa fa-picture-o"></i>
													</a> <a
														href="/webcomic/admin/product?update&&productID=${item.productID}"
														class="btn btn-warning"> <i class="fa fa-pencil"></i>
													</a> <span>&nbsp;</span> <a
														href="/webcomic/admin/product?delete&&productID=${item.productID}"
														class="btn btn-danger"> <i class="fa fa-times"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'user'}">
													<td>${item.userName}</td>
													<td>${item.userAddress}</td>
													<td>${item.userPhoneNumber}</td>
													<td>${item.userEmail}</td>
													<td><a type="button" class="btn btn-primary"
														style="color: white;"
														href="/webcomic/admin/user?update&&userID=${item.userID}">
															<i class="fa fa-pencil"></i>
													</a></td>
												</c:if>
												<c:if test="${content == 'bill'}">
													<td>${item.userID}</td>
													<td>${item.billDate}</td>
													<td>${item.billPhoneNumber}</td>
													<td>${item.billState}</td>
													<td><a type="button" class="btn btn-primary"
														style="color: white;"
														href="/webcomic/admin/bill?update&&billID=${item.billID}">
															<i class="fa fa-pencil"></i>
													</a></td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
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


	<script src="../assets/js/lib/data-table/datatables.min.js"></script>
	<script src="../assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
	<script src="../assets/js/lib/data-table/dataTables.buttons.min.js"></script>
	<script src="../assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
	<script src="../assets/js/lib/data-table/jszip.min.js"></script>
	<script src="../assets/js/lib/data-table/vfs_fonts.js"></script>
	<script src="../assets/js/lib/data-table/buttons.html5.min.js"></script>
	<script src="../assets/js/lib/data-table/buttons.print.min.js"></script>
	<script src="../assets/js/lib/data-table/buttons.colVis.min.js"></script>
	<script src="../assets/js/init/datatables-init.js"></script>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#bootstrap-data-table-export').DataTable();
		});
	</script>


</body>
</html>
