<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row contain">
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
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">${user.firstName} ${user.lastName} Profile</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-3 " align="center">
								<img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive">
							</div>
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-responsive">
									<tbody>
										<tr>
											<td>Login:</td>
											<td>${user.login}</td>
										</tr>
										<tr>
											<td>First Name:</td>
											<td>${user.firstName}</td>
										</tr>
										<tr>
											<td>Last Name:</td>
											<td>${user.lastName}</td>
										</tr>
										<tr>
											<td>Birthday:</td>
											<td>${user.birthday}</td>
										</tr>
										<tr>
											<td>Gender:</td>
											<td>
												<c:choose>
													<c:when test="${user.gender}">
														Female
													</c:when>
													<c:otherwise>
														Male
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<tr>
											<td>Home Address:</td>
											<td>${user.address.street}, ${user.address.city.name}</td>
										</tr>
										<tr>
											<td>Email:</td>
											<td><a href="mailto:${user.email}">${user.email}</a></td>
										</tr>
										<tr>
											<td>Phone Number:</td>
											<td>${user.telephone}</td>
										</tr>
										<tr>
											<td>Your Money:</td>
											<td>${user.money}$</td>
										</tr>
										<tr>
											<td>Rate:</td>
											<td>${user.rate}</td>
										</tr>
									</tbody>
								</table>
								<a href="" class="btn btn-primary">Edit information</a> <a href="" class="btn btn-primary">Change password</a>
							</div>
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