<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="page-wrapper">
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Bucket <small>Overview</small>
				</h1>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-table fa-fw"></i> Bucket Panel
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th class="id">ID</th>
									<th>User</th>
									<th>Goods</th>
									<th>Amount</th>
									<th class="update">Update</th>
									<th class="delete">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.content}" var="bucket">
									<tr>
										<td>${bucket.id}</td>
										<td>${bucket.user.login},${bucket.user.firstName}${bucket.user.lastName}</td>
										<td>${bucket.good.name}</td>
										<td>${bucket.goodsAmount}</td>
										<td><a href="/admin/${user.id}/buckets/update/${bucket.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}"
											class="fa fa-fw fa-pencil"></a></td>
										<td><a href="/admin/${user.id}/buckets/delete/${bucket.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}"
											class="glyphicon glyphicon-remove-sign"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<button type="button" class="btn btn-primary btn-md pull-right" data-toggle="modal" data-target="#add">Add Bucket</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="text-center">
				<nav aria-label="Search results pages">
					<ul class="pagination">
						<c:choose>
							<c:when test="${!page.isFirst()}">
								<li><a href="?page=${page.number}&size=${page.size}&sort=${param['sort']}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" var="pageNumber" end="${countPages}" varStatus="loop">
							<c:choose>
								<c:when test="${page.number + 1 eq pageNumber}">
									<li class="active"><a href="?page=${pageNumber}&size=${page.size}&sort=${param['sort']}">${pageNumber} <span class="sr-only" /></span></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="?page=${pageNumber}&size=${page.size}&sort=${param['sort']}">${pageNumber} <span class="sr-only" /></span></a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${!page.isLast()}">
								<li><a href="?page=${page.number + 2}&size=${page.size}&sort=${param['sort']}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
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
							<li><a href="?page=${page.number}&size=${page.size}&sort=id">ID</a></li>
							<li><a href="?page=${page.number}&size=${page.size}&sort=goodsAmount">Amount</a></li>
							<li><a href="?page=${page.number}&size=${page.size}&sort=good">Goods</a></li>
						</ul>
					</div>
					<div class="dropup pull-right size">
						<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							Size <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="size">
							<c:forEach begin="5" end="20" varStatus="loop" var="sizeNumber" step="5">
								<c:choose>
									<c:when test="${sizeNumber eq page.size}">
										<li class="active"><a href="?page=${page.number}&size=${sizeNumber}&sort=${param['sort']}">${sizeNumber}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="?page=${page.number}&size=${sizeNumber}&sort=${param['sort']}">${sizeNumber}</a></li>
									</c:otherwise>
								</c:choose>
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
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add Bucket</h4>
			</div>
			<div class="modal-body">
				<div class="form-style-8">
					<form:form action="/admin/${user.id}/buckets" method="post" id="save" modelAttribute="bucket">
						<form:hidden path="id" />
						<form:errors path="user" />
						<form:select path="user" required="true">
							<option value="${user.id}">${user.login},${user.firstName}${user.lastName}</option>
						</form:select>
						<form:errors path="good" />
						<form:select path="good" required="true">
							<form:option value="0">Choose Goods</form:option>
							<form:options items="${goods}" itemLabel="name" itemValue="id" />
						</form:select>
						<form:errors path="goodsAmount" />
						<form:input type="number" path="goodsAmount" placeholder="Amount" required="true" min="1" max="2000000" />
					</form:form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" form="save">
					<span class="glyphicon glyphicon-ok"></span> Save
				</button>
				<c:choose>
					<c:when test="${bucket.id ne 0}">
						<a href="/admin/${user.id}/buckets?page=${page.number}&size=${page.size}&sort=${param['sort']}" id="close" class="btn btn-default">Close</a>
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
	<c:when test="${bucket.id ne 0}">
		<script>
			$('#add').modal('show');
		</script>
	</c:when>
	<c:otherwise>
		<spring:hasBindErrors name="bucket">
			<c:if test="${errors.errorCount ne 0}">
				<script>
					$('#add').modal('show');
				</script>
			</c:if>
		</spring:hasBindErrors>
	</c:otherwise>
</c:choose>