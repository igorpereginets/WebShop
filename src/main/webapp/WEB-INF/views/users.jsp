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
					Users <small>Overview</small>
				</h1>
			</div>
		</div>
		<form:form action="/admin/users" modelAttribute="filter" method="get" name="searchForm">
			<custom:hiddenInputs
				excludeParams="loginSearch, firstNameSearch, lastNameSearch, emailSearch, telephoneSearch, minMoney, maxMoney, minRate, maxRate, genderSearch, _genderFilter" />
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="loginSearch" class="form-control" placeholder="Search login..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="firstNameSearch" class="form-control" placeholder="Search First Name..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="lastNameSearch" class="form-control" placeholder="Search Last Name..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="emailSearch" class="form-control" placeholder="Search E-mail..." />
			</div>
			<div class="col-md-4 col-sm-12">
				<form:input type="search" path="telephoneSearch" class="form-control" placeholder="Search Telephone..." />
			</div>
			<div class="col-md-2 col-sm-12">
				<form:input type="number" path="minMoney" class="form-control" placeholder="Min money" max="100000000" />
			</div>
			<div class="col-md-2 col-sm-12">
				<form:input type="number" path="maxMoney" class="form-control" placeholder="Max money" max="100000000" />
			</div>
			<div class="col-md-2 col-sm-12">
				<form:input type="number" path="minRate" class="form-control" placeholder="Min rate" max="100000000" />
			</div>
			<div class="col-md-2 col-sm-12">
				<form:input type="number" path="maxRate" class="form-control" placeholder="Max rate" max="100000000" />
			</div>
			<form:checkbox path="genderSearch" value="M" id="male" />	Male
						<form:checkbox path="genderSearch" value="F" id="female" />	Female
					<button type="submit" class="btn btn-info pull-right">Search</button>
		</form:form>
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-table fa-fw"></i> User Panel
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th class="id">ID</th>
									<th>First name</th>
									<th>Last name</th>
									<th>Gender</th>
									<th>Login</th>
									<th>E-mail</th>
									<th>Password</th>
									<th>Telephone</th>
									<th>Birthday</th>
									<th>Money</th>
									<th>Rate</th>
									<th>Bucket</th>
									<th class="update">Update</th>
									<th class="delete">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.content}" var="user">
									<tr>
										<td>${user.id}</td>
										<td>${user.firstName}</td>
										<td>${user.lastName}</td>
										<td><c:choose>
												<c:when test="${user.gender}">Male</c:when>
												<c:otherwise>Female</c:otherwise>
											</c:choose></td>
										<td>${user.login}</td>
										<td>${user.email}</td>
										<td>${user.password}</td>
										<td>${user.telephone}</td>
										<td>${user.birthday}</td>
										<td>${user.money}</td>
										<td>${user.rate}</td>
										<td><a href="/admin/${user.id}/buckets" class="glyphicon glyphicon-remove-sign"></a></td>
										<td><a href="/admin/users/update/${user.id}?${pageContext.request.queryString}" class="fa fa-fw fa-pencil"></a></td>
										<td><a href="/admin/users/delete/${user.id}?${pageContext.request.queryString}" class="glyphicon glyphicon-remove-sign"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<button type="button" class="btn btn-primary btn-md pull-right" data-toggle="modal" data-target="#add">Add User</button>
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
							<li <custom:isActive key="sort" value="login"/>><a href="<custom:allParams change="sort" value="login"/>">Login</a></li>
							<li <custom:isActive key="sort" value="firstName"/>><a href="<custom:allParams change="sort" value="firstName"/>">First name</a></li>
							<li <custom:isActive key="sort" value="lastName"/>><a href="<custom:allParams change="sort" value="lastName"/>">Last name</a></li>
							<li <custom:isActive key="sort" value="email"/>><a href="<custom:allParams change="sort" value="email"/>">E-mail</a></li>
							<li <custom:isActive key="sort" value="telephone"/>><a href="<custom:allParams change="sort" value="telephone"/>">Telephone</a></li>
							<li <custom:isActive key="sort" value="birthday"/>><a href="<custom:allParams change="sort" value="birthday"/>">Birthday</a></li>
							<li <custom:isActive key="sort" value="money"/>><a href="<custom:allParams change="sort" value="money"/>">Money</a></li>
							<li <custom:isActive key="sort" value="rate"/>><a href="<custom:allParams change="sort" value="rate"/>">Rate</a></li>
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
				<h4 class="modal-title" id="myModalLabel">Add User</h4>
			</div>
			<div class="modal-body">
				<div class="form-style-8">
					<form:form action="/admin/users" method="post" id="save" modelAttribute="userSaveForm">
						<form:hidden path="id" />
						<form:errors path="email" />
						<form:input type="email" path="email" placeholder="E-mail" required="true" maxlength="40" />
						<form:errors path="password" />
						<form:input type="text" path="password" placeholder="Password" required="true" maxlength="40" />
						<form:errors path="login" />
						<form:input type="text" path="login" placeholder="Login" required="true" maxlength="40" />
						<form:errors path="firstName" />
						<form:input type="text" path="firstName" placeholder="First Name" required="true" maxlength="40" />
						<form:errors path="lastName" />
						<form:input type="text" path="lastName" placeholder="Last Name" required="true" maxlength="40" />
						<form:select path="gender">
							<c:choose>
								<c:when test="${user.gender}">
									<option value="true" selected="selected">Male</option>
									<option value="false">Female</option>
								</c:when>
								<c:otherwise>
									<option value="true">Male</option>
									<option value="false" selected="selected">Female</option>
								</c:otherwise>
							</c:choose>
						</form:select>
						<form:errors path="telephone" />
						<form:input type="text" path="telephone" placeholder="Telephone" required="true" maxlength="40" />
						<form:errors path="birthday" />
						<form:input type="text" path="birthday" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" placeholder="Birthday YYYY-MM-DD"
							required="true" maxlength="40" />
						<form:errors path="money" />
						<form:input type="number" step="0.01" path="money" placeholder="Money" required="true" maxlength="40" />
						<form:errors path="rate" />
						<form:input type="number" step="0.01" path="rate" placeholder="Rate" required="true" maxlength="40" />
						<form:errors path="address" />
						<form:select path="address" required="true">
							<option value="0">Choose Address</option>
							<c:forEach items="${addresses}" var="address">
								<c:choose>
									<c:when test="${address.id eq userSaveForm.address.id}">
										<option value="${address.id}" selected="selected">${address.street},${address.city.name},${address.postcode}</option>
									</c:when>
									<c:otherwise>
										<option value="${address.id}">${address.street},${address.city.name},${address.postcode}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</form:form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" form="save" formaction="/admin/users?${pageContext.request.queryString}">
					<span class="glyphicon glyphicon-ok"></span> Save
				</button>
				<c:choose>
					<c:when test="${userSaveForm.id ne 0}">
						<a href="/admin/users?${pageContext.request.queryString}" id="close" class="btn btn-default">Close</a>
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
	<c:when test="${userSaveForm.id ne 0}">
		<script>
			$('#add').modal('show');
		</script>
	</c:when>
	<c:otherwise>
		<spring:hasBindErrors name="userSaveForm">
			<c:if test="${errors.errorCount ne 0}">
				<script>
					$('#add').modal('show');
				</script>
			</c:if>
		</spring:hasBindErrors>
	</c:otherwise>
</c:choose>
