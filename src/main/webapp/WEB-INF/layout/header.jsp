<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div class="row">
	<div class="col-md-12">
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/"><img src="/resources/img/logo2.png" height="40" alt="Brand"></a>

			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
					<li><a href="#" role="button" data-toggle="modal" data-target=".about">About</a></li>
					<li><a href="#" role="button" data-toggle="modal" data-target=".contact">Contact us</a></li>
					<li><a href="#" role="button" data-toggle="modal" data-target=".help">Help</a></li>
				</ul>
				<div class="pull-right">
					<security:authorize access="hasRole('ADMIN')">
						<a type="button" class="btn btn-info navbar-btn" href="/admin/countries">Admin</a>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<a href="/logout" type="button" class="btn btn-success navbar-btn">Log Out</a>
					</security:authorize>
					<security:authorize access="!isAuthenticated()">
						<a href="/login" type="button" class="btn btn-primary navbar-btn">Log In</a>
						<a type="button" class="btn btn-success navbar-btn" name="signup">Sign Up</a>
					</security:authorize>
				</div>
			</div>
		</nav>
	</div>
</div>
<div class="row slider">
	<div class="container-fluid">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			<!-- 			Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>
			<!-- 			Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="/resources/img/head-onlineMarketing.jpg" height="600" width="2000" alt="...">
					<div class="carousel-caption">...</div>
				</div>
				<div class="item">
					<img src="/resources/img/head-SEO.jpg" height="600" width="2000" alt="...">
					<div class="carousel-caption">...</div>
				</div>
				<div class="item">
					<img src="/resources/img/help-hero-banner.jpg" height="600" width="2000" alt="...">
					<div class="carousel-caption">...</div>
				</div>
			</div>
			<!-- 			Controls -->
			<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</div>