<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="hero_area" style="min-height: auto;">
	<!-- header section strats -->
	<header class="header_section">
		<div class="header_top">
			<div class="container-fluid">
				<div class="top_nav_container">
					<div class="contact_nav">
						<a href="/webcomic/" style="margin-right: 35px;"> <span>
								Trang chủ </span>
						</a>
						<div class="dropdown1">
							<button class="dropbtn1">
								Danh mục <i class="fa fa-caret-down"></i>
							</button>
							<div class="dropdown1-content">
								<c:forEach items="${categories}" var="item">
									<a href="/webcomic/?categoryID=${item.categoryID}">${item.categoryName}</a>
								</c:forEach>
							</div>
						</div>
					</div>
					<form class="search_form" method="post" action="/webcomic/">
						<input type="text" style="width: 500px;" class="form-control"
							name="search" placeholder="Nhập tên cuốn sách bạn muốn tìm...">
						<button class="" type="submit">
							<i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</form>
					<div class="user_option_box">
						<c:if test="${pageContext.request.userPrincipal.name == null}">
							<a href="/webcomic/signin" class="account-link"><i
								class="fa fa-user" aria-hidden="true"></i> <span> Tài
									khoản </span> </a>
						</c:if>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<div class="dropdown1">
								<button class="dropbtn1">
									<i class="fa fa-user" aria-hidden="true"></i>
									${pageContext.request.userPrincipal.name} <i
										class="fa fa-caret-down"></i>
								</button>
								<div class="dropdown1-content">
									<a href="/webcomic/account">Thông tin</a> <a href="/webcomic/logout">Đăng
										xuất</a>
								</div>
							</div>
						</c:if>
						<a href="/webcomic/cart?list" class="cart-link"> <i
							class="fa fa-shopping-cart" aria-hidden="true"></i> <span>
								Giỏ hàng </span>
						</a>
					</div>
				</div>

			</div>
		</div>

	</header>
	<!-- end header section -->
</div>
