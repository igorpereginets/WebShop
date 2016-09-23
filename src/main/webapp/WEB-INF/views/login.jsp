<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="/resources/css/signin.css" rel="stylesheet">
<div class="container">

	<form class="form-signin" action="/login" method="post">
		<h2 class="form-signin-heading">Please sign in</h2>
		<label for="login" class="sr-only">Email address</label> <input type="text" id="login" name="username" class="form-control" placeholder="Login" required autofocus>
		<label for="inputPassword" class="sr-only">Password</label> <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
			required>
<!-- 		<div class="checkbox"> -->
<!-- 			<label> <input type="checkbox" value="remember-me"> Remember me -->
<!-- 			</label> -->
<!-- 		</div> -->
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	</form>

</div>