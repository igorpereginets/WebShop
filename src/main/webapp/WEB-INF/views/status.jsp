<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld"%>
<div id="page-wrapper">
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Status <small>Overview</small>
				</h1>
			</div>
		</div>
		<div class="col-sm-12">
			<form:form action="/admin/status" modelAttribute="filter" method="get" name="searchForm">
				<custom:hiddenInputs excludeParams="statusSearch" />
				<form:input type="search" path="statusSearch" class="form-control" placeholder="Search status..." />
			</form:form>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="fa fa-table fa-fw"></i> Status Panel
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th class="id">ID</th>
									<th>Name</th>
									<th class="update">Update</th>
									<th class="delete">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.content}" var="st">
									<tr>
										<td>${st.id}</td>
										<td>${st.status}</td>
										<td><a href="/admin/status/update/${st.id}?${pageContext.request.queryString}" class="fa fa-fw fa-pencil"></a></td>
										<td><a href="/admin/status/delete/${st.id}?${pageContext.request.queryString}" class="glyphicon glyphicon-remove-sign"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<button type="button" class="btn btn-primary btn-md pull-right" data-toggle="modal" data-target="#add">Add Status</button>
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
							<li <custom:isActive key="sort" value="status"/>><a href="<custom:allParams change="sort" value="status"/>">Name</a></li>
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
				<h4 class="modal-title" id="myModalLabel">Add Status</h4>
			</div>
			<div class="modal-body">
				<div class="form-style-8">
					<form:form modelAttribute="statusSaveForm" action="/admin/status" method="post" id="save">
						<form:hidden path="id" />
						<form:errors path="status" />
						<form:input path="status" placeholder="Status" />
					</form:form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" form="save" formaction="/admin/status?${pageContext.request.queryString}">
					<span class="glyphicon glyphicon-ok"></span> Save
				</button>
				<c:choose>
					<c:when test="${statusSaveForm.id ne 0}">
						<a href="/admin/status?${pageContext.request.queryString}" id="close" class="btn btn-default">Close</a>
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
	<c:when test="${statusSaveForm.id ne 0}">
		<script>
			$('#add').modal('show');
		</script>
	</c:when>
	<c:otherwise>
		<spring:hasBindErrors name="statusSaveForm">
			<c:if test="${errors.errorCount ne 0}">
				<script>
					$('#add').modal('show');
				</script>
			</c:if>
		</spring:hasBindErrors>
	</c:otherwise>
</c:choose>