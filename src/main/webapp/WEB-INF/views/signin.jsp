<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="icon" href="img/core-img/favicon.ico">
<title>THTV BookStore - Sign In</title>

<!-- This templates was made by Colorlib (https://colorlib.com) -->

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="main" style="padding: 22px 0;">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content" style="padding-top: 50px;">
					<div class="signin-image">
						<figure>
							<img src="images/signin-image.jpg" alt="sing up image">
						</figure>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Đăng Nhập</h2>
						<form action="signin" method="POST" class="register-form"
							id="login-form">
							<c:if test="${param.error != null }">
								<p id="err" style="color: red; font-size: 12px;">*Sai thông
									tin email hoặc mật khẩu*</p>
							</c:if>
							<p id="err" style="color: red; font-size: 12px;"></p>
							<div class="form-group">
								<label for="your_email"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" name="userEmail" id="your_email"
									placeholder="Nhập vào email của bạn" />
							</div>
							<div class="form-group">
								<label for="your_pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="userPassword" id="your_pass"
									placeholder="Nhập vào password của bạn" />
							</div>
							<div class="form-group">
								<a href="/webcomic/forgotPassword" style="margin-left: 200px;">Quên
									mật khẩu?</a>
							</div>
							<div class="form-group form-button">
								<input type="button" onclick="submitForm()" name="signin"
									id="signin" class="form-submit" value="Đăng Nhập" /> <a
									href="/webcomic/signup" class="form-submit"
									style="text-decoration: none; padding: 11px 45px; background-color: #42b72a;">Đăng
									ký</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script>
		function submitForm() {
			var email = document.getElementById('your_email').value.trim();
			var pass = document.getElementById('your_pass').value.trim();
			if (email != "" && pass != "") {
				document.getElementById('login-form').submit();
			} else {
				document.getElementById('err').innerHTML = "*Vui lòng điền đầy đủ thông tin*";
			}
		}
	</script>
</body>
</html>