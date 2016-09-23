<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld"%>
<div id="page-wrapper">
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<div class="col-sm-12">
				<h1 class="page-header">
					Goods <small>Overview</small>
				</h1>
			</div>
		</div>
		<form:form action="/admin/goods" modelAttribute="filter" method="get" name="searchForm">
			<custom:hiddenInputs
				excludeParams="nameSearch, minPrice, maxPrice, userSearch, minAmount, maxAmount, beginSearch, endSearch, stateSearch, brandSearch, categorySearch, activeSearch, _activeSearch" />
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="nameSearch" class="form-control" placeholder="Search name..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="number" path="minPrice" class="form-control" placeholder="Min price" />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="number" path="maxPrice" class="form-control" placeholder="Max price" />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="userSearch" class="form-control" placeholder="Search user..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="number" path="minAmount" class="form-control" placeholder="Min amount" />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="number" path="maxAmount" class="form-control" placeholder="Max amount" />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="beginSearch" class="form-control" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
					placeholder="Begin YYYY-MM-DD" />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="endSearch" class="form-control" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
					placeholder="End YYYY-MM-DD" />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="stateSearch" class="form-control" placeholder="Search state..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="brandSearch" class="form-control" placeholder="Search brand..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="categorySearch" class="form-control" placeholder="Search category..." />
			</div>
			<form:radiobutton path="activeSearch" value="true" />	Active
		 			<form:radiobutton path="activeSearch" value="false" /> Not active 
		 			<button type="submit" class="hidden"></button>
		</form:form>
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-table fa-fw"></i> Goods Panel
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th class="id">ID</th>
									<th>Name</th>
									<th>Amount</th>
									<th>Price</th>
									<th>Category</th>
									<th>Brand</th>
									<th>User</th>
									<th>State</th>
									<th>Begin</th>
									<th>End</th>
									<th>isActive</th>
									<th class="update">Update</th>
									<th class="delete">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.content}" var="good">
									<tr>
										<td>${good.id}</td>
										<td>${good.name}</td>
										<td>${good.amount}</td>
										<td>${good.price}</td>
										<td>${good.category.name}</td>
										<td>${good.brand.name}</td>
										<td>${good.user.login}</td>
										<td>${good.state.name}</td>
										<td>${good.begin}</td>
										<td>${good.end}</td>
										<td>${good.active}</td>
										<td><a href="/admin/goods/update/${good.id}?${pageContext.request.queryString}" class="fa fa-fw fa-pencil"></a></td>
										<td><a href="/admin/goods/delete/${good.id}?${pageContext.request.queryString}" class="glyphicon glyphicon-remove-sign"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<button type="button" class="btn btn-primary btn-md pull-right" data-toggle="modal" data-target="#add">Add Goods</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="text-center">
				<nav aria-label="Search results pages">
					<ul class="pagination">
						<c:choose>
							<c:when test="${!page.isFirst()}">
								<li><a href="<custom:allParams change="page" value="${page.number}"/>" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" var="pageNumber" end="${countPages}" varStatus="loop">
							<c:choose>
								<c:when test="${page.number + 1 eq pageNumber}">
									<li class="active"><a href="<custom:allParams change="page" value="${pageNumber}"/>">${pageNumber} <span class="sr-only" /></span></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="<custom:allParams change="page" value="${pageNumber}"/>">${pageNumber} <span class="sr-only" /></span></a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${!page.isLast()}">
								<li><a href="<custom:allParams change="page" value="${page.number + 2}"/>" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><a aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
							</c:otherwise>
						</c:choose>
					</ul>
					<div class="dropup pull-right sort">
						<button class="btn btn-default dropdown-toggle" type="button" id="sorting" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							Sort <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="sort">
							<li <custom:isActive key="sort" value="id"/>><a href="<custom:allParams change="sort" value="id"/>">ID</a></li>
							<li <custom:isActive key="sort" value="name"/>><a href="<custom:allParams change="sort" value="name"/>">Name</a></li>
							<li <custom:isActive key="sort" value="price"/>><a href="<custom:allParams change="sort" value="price"/>">Price</a></li>
							<li <custom:isActive key="sort" value="amount"/>><a href="<custom:allParams change="sort" value="amount"/>">Amount</a></li>
							<li <custom:isActive key="sort" value="begin"/>><a href="<custom:allParams change="sort" value="begin"/>">Begin</a></li>
							<li <custom:isActive key="sort" value="end"/>><a href="<custom:allParams change="sort" value="end"/>">End</a></li>
							<li <custom:isActive key="sort" value="active"/>><a href="<custom:allParams change="sort" value="active"/>">Active</a></li>
							<li <custom:isActive key="sort" value="user.login"/>><a href="<custom:allParams change="sort" value="user.login"/>">User</a></li>
							<li <custom:isActive key="sort" value="state.name"/>><a href="<custom:allParams change="sort" value="state.name"/>">State</a></li>
							<li <custom:isActive key="sort" value="brand.name"/>><a href="<custom:allParams change="sort" value="brand.name"/>">Brand</a></li>
							<li <custom:isActive key="sort" value="category.name"/>><a href="<custom:allParams change="sort" value="category.name"/>">Category</a></li>
						</ul>
					</div>
					<div class="dropup pull-right size">
						<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							Size <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="size">
							<c:forEach begin="5" end="20" varStatus="loop" var="sizeNumber" step="5">
								<li <custom:isActive key="size" value="${sizeNumber}"/>><a href="<custom:allParams change="size" value="${sizeNumber}"/>">${sizeNumber}</a></li>
							</c:forEach>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Add Goods</h4>
			</div>
			<div class="modal-body">
				<div class="form-style-8">
					<form:form action="/admin/goods" method="post" id="save" modelAttribute="goodsSaveForm" enctype="multipart/form-data">
						<form:hidden path="id" />
						<form:hidden path="pathToFile" />
						<form:errors path="name" />
						<form:input type="text" path="name" placeholder="Goods name" />
						<form:errors path="price" />
						<form:input type="number" step="1.00" path="price" placeholder="Price" />
						<form:errors path="amount" />
						<form:input type="number" path="amount" placeholder="Amount" />
						<form:errors path="description" />
						<form:textarea type="text" path="description" placeholder="Description" />
						<form:errors path="shortDescription" />
						<form:textarea path="shortDescription" placeholder="Short Description" />
						<form:errors path="user" />
						<form:select path="user">
							<option value="0">Choose user</option>
							<c:forEach items="${users}" var="user">
								<c:choose>
									<c:when test="${user.id eq goodsSaveForm.user.id}">
										<option value="${user.id}" selected="selected">${user.login},${user.firstName}${user.lastName}</option>
									</c:when>
									<c:otherwise>
										<option value="${user.id}">${user.login},${user.firstName}${user.lastName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<form:errors path="state" />
						<form:select path="state">
							<form:option value="0">Choose state</form:option>
							<form:options items="${states}" itemLabel="name" itemValue="id" />
						</form:select>
						<form:errors path="brand" />
						<form:select path="brand">
							<form:option value="0">Choose brand</form:option>
							<form:options items="${brands}" itemLabel="name" itemValue="id" />
						</form:select>
						<form:errors path="category" />
						<form:select path="category">
							<form:option value="0">Choose category</form:option>
							<form:options items="${categories}" itemLabel="name" itemValue="id" />
						</form:select>
						<form:input type="file" path="file" />
					</form:form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" form="save" formaction="/admin/goods?${pageContext.request.queryString}">
					<span class="glyphicon glyphicon-ok"></span> Save
				</button>
				<c:choose>
					<c:when test="${goodsSaveForm.id ne 0}">
						<a href="/admin/goods?${pageContext.request.queryString}" id="close" class="btn btn-default">Close</a>
					</c:when>
					<c:otherwise>
						<button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>
<c:choose>
	<c:when test="${goodsSaveForm.id ne 0}">
		<script>
			$('#add').modal('show');
		</script>
	</c:when>
	<c:otherwise>
		<spring:hasBindErrors name="goodsSaveForm">
			<c:if test="${errors.errorCount ne 0}">
				<script>
					$('#add').modal('show');
				</script>
			</c:if>
		</spring:hasBindErrors>
	</c:otherwise>
</c:choose>