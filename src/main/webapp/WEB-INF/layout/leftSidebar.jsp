<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-2">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">My Account</h3>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<li class="list-group-item"><a href="" class="account-info"> My orders </a></li>
				<li class="list-group-item"><a href="/myInfo" class="account-info"> My personal info </a></li>
				<li class="list-group-item"><a href="" class="account-info"> My wishlist </a></li>
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
					<li class="list-group-item"><a href="?categorySearch=${category.id}" class="category"> ${category.name} <span class="badge">${category.count}</span>
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
					<li class="list-group-item"><a href="?brandSearch=${brand.id}" class="brands"> ${brand.name} </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>