<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<div class="col-md-8">
		<div class="row">
			<div class="container-fluid">
				<div class="content-wrapper">
					<form:form class="createForm" method="post" modelAttribute="goodsSaveForm" action="/createGoods" enctype="multipart/form-data">
						<form:hidden path="id" />
						<div class="row">
							<div class="col-sm-4">
								<label>Name: </label>
							</div>
							<div class="col-sm-8">
								<form:input type="text" placeholder="Name" class="createInput" path="name" />
							</div>
							<div class="col-sm-4">
								<label>Price: </label>
							</div>
							<div class="col-sm-8">
								<form:input type="number" size="0.01" placeholder="Price" class="createInput" path="price" />
							</div>
							<div class="col-sm-4">
								<label>Amount: </label>
							</div>
							<div class="col-sm-8">
								<form:input type="number" placeholder="Amount" class="createInput" path="amount" />
							</div>
							<div class="row">
								<div class="col-sm-4">
									<label>Category: </label>
								</div>
								<div class="col-sm-8">
									<form:select path="category" class="createInput">
										<form:option value="">Choose category</form:option>
										<c:forEach items="${categories}" var="category">
											<c:choose>
												<c:when test="${category.id eq goodsSaveForm.category.id}">
													<form:option selected="selected" value="${category.id}">${category.name}</form:option>
												</c:when>
												<c:otherwise>
													<form:option value="${category.id}">${category.name}</form:option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<label>Brand: </label>
								</div>
								<div class="col-sm-8">
									<form:input type="text" placeholder="Brand" class="createInput" path="brand" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<label>State: </label>
								</div>
								<div class="col-sm-8">
									<form:select path="state" class="createInput">
										<form:option value="">Choose state</form:option>
										<c:forEach items="${states}" var="state">
											<c:if test="${state.name ne 'Sold'}">
												<c:choose>
													<c:when test="${state.id eq goodsSaveForm.state.id}">
														<form:option selected="selected" value="${state.id}">${state.name}</form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${state.id}">${state.name}</form:option>
													</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<label>Tags: </label>
								</div>
								<div class="col-sm-8">
									<form:input type="text" placeholder="Tags (through space)" class="createInput" path="tags" />
								</div>
							</div>
							<div class="col-sm-4">
								<label>Short description: </label>
							</div>
							<div class="col-sm-8">
								<form:input type="text" placeholder="Short description" class="createInput" path="shortDescription" />
							</div>
							<div class="col-sm-4">
								<label>Description: </label>
							</div>
							<div class="col-sm-8">
								<form:textarea type="text" style="resize: vertical;" placeholder="Description" class="createInput" path="description" />
							</div>
							<div class="col-sm-4">
								<label>Image: </label>
							</div>
							<div class="col-sm-8">
								<form:input type="file" placeholder="Image" class="fileInput" path="file" />
							</div>
							<a type="button" href="/" class="btn btn-danger pull-right">Cancel</a>
							<button type="submit" class="btn btn-success pull-right">Save</button>
						</div>
					</form:form>
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
					<li class="list-group-item"><a href="?tagSearch=${tag.id}"> ${tag.name} </a></li>
				</c:forEach>
			</div>
		</div>
	</div>
</div>