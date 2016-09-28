<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
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
									<a type="button" class="btn btn-success" href="/toBucket/${good.id}">Add to bucket</a>
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
