<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
						<button type="submit" class="btn btn-lg btn-success pull-right">Save commodity</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
