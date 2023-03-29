<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Left Panel -->
<aside id="left-panel" class="left-panel">
	<nav class="navbar navbar-expand-sm navbar-default">
		<div id="main-menu" class="main-menu collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/webcomic/admin/admin"><i
						class="menu-icon fa fa-laptop"></i>Trang chủ</a></li>
				<li class="menu-item-has-children dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="menu-icon fa fa-tasks"></i>Quản
						lý danh mục
				</a>
					<ul class="sub-menu children dropdown-menu">
						<li><i class="fa fa-bars"></i><a
							href="/webcomic/admin/category?list">Danh mục sách</a></li>
						<li><i class="fa fa-puzzle-piece"></i><a
							href="/webcomic/admin/genre?list">Thể loại sách</a></li>
					</ul></li>
				<li class="menu-item-has-children dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="menu-icon fa fa-building-o"></i>Quản
						lý đối tác
				</a>
					<ul class="sub-menu children dropdown-menu">
						<li><i class="fa fa-truck"></i> <a
							href="/webcomic/admin/supplier?list">Nhà cung cấp</a></li>
						<li><i class="fa fa-print"></i> <a
							href="/webcomic/admin/publisher?list">Nhà xuất bản</a></li>
						<li><i class="fa fa-smile-o"></i> <a
							href="/webcomic/admin/author?list">Tác giả</a></li>
					</ul></li>
				<li class="menu-item-has-children dropdown"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="menu-icon fa fa-pagelines"></i>Quản
						lý sản phẩm
				</a>
					<ul class="sub-menu children dropdown-menu">
						<li><i class="fa fa-archive"></i><a
							href="/webcomic/admin/series?list">Bộ sách</a></li>
						<li><i class="fa fa-book"></i><a
							href="/webcomic/admin/product?list">Sách</a></li>
					</ul></li>
				<li><a href="/webcomic/admin/user?list"><i
						class="menu-icon fa fa-user"></i>Quản lý người dùng</a></li>
				<li><a href="/webcomic/admin/bill?list"><i
						class="menu-icon fa fa-money"></i>Quản lý đơn hàng</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
</aside>
<!-- /#left-panel -->