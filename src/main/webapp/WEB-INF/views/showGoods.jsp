<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="row content">
	<div class="col-md-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">My Account</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<li class="list-group-item"><a href="index.html" class="account-info"> My orders </a></li>
					<li class="list-group-item"><a href="index.html" class="account-info"> My personal info </a></li>
					<li class="list-group-item"><a href="index.html" class="account-info"> My wishlist </a></li>
				</ul>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Categories</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<c:forEach items="${categories}" var="category">
						<li class="list-group-item"><a href="/" class="category"> ${category.name} <span class="badge">${category.count}</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Brands</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<c:forEach items="${brands}" var="brand">
						<li class="list-group-item"><a href="/" class="brands"> ${brand.name} </a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<div class="row">
			<div class="container-fluid">
				<div class="content-wrapper">
					<div class="item-container">
						<div class="container">
							<div class="col-md-7">
								<div class="thumbnail">
									<a href="${good.pathToFile}" data-lightbox="image-1" data-title="${good.name}"> <img id="item-display" src="${good.pathToFile}"
										alt="${good.name}"></img></a>
								</div>
							</div>
							<div class="col-md-5">
								<div class="product-title">${good.name}</div>
								<div class="product-desc">${good.shortDescription}</div>
								<div class="product-rating">
									<i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i
										class="fa fa-star-o"></i>
								</div>
								<hr>
								<div class="product-price">${good.price}$</div>
								<div class="product-stock">${good.state.name}</div>
								<hr>
								<security:authorize access="isAuthenticated()">
									<div class="btn-group cart">
										<button type="button" class="btn btn-success">Add to cart</button>
									</div>
									<div class="btn-group wishlist">
										<button type="button" class="btn btn-danger">Add to wishlist</button>
									</div>
								</security:authorize>
							</div>
						</div>
					</div>
					<div class="container-fluid">
						<div class="col-md-12 product-info">
							<ul id="myTab" class="nav nav-tabs nav_tabs">
								<li class="active"><a href="#service-one" data-toggle="tab">DESCRIPTION</a></li>
								<li><a href="#service-two" data-toggle="tab">PRODUCT INFO</a></li>
								<li><a href="#service-three" data-toggle="tab">REVIEWS</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade in active" id="service-one">
									<section class="container product-info">${good.description}</section>
								</div>
								<div class="tab-pane fade" id="service-two">
									<section class="container"></section>
								</div>
								<div class="tab-pane fade" id="service-three"></div>
							</div>
							<hr>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">My Bucket</h3>
			</div>
			<div class="panel-body">
				<h5>No Products...</h5>
				<button type="button" class="btn btn-primary navbar-btn">Check Out</button>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Tags</h3>
			</div>
			<div class="panel-body">
				<c:forEach items="${tags}" var="tag">
					<li class="list-group-item"><a href="?tagSearch=${tag.name}"> ${tag.name} </a></li>
				</c:forEach>
			</div>
		</div>
	</div>
</div>