<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld"%>

<%@ include file="/WEB-INF/layout/slider.jsp"%>
<%@ include file="/WEB-INF/layout/leftSidebar.jsp"%>
<div class="col-md-8">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Filter</h3>
		</div>
		<div class="panel-body">
			<form:form action="/" modelAttribute="filter" method="get" name="searchForm">
				<custom:hiddenInputs excludeParams="nameSearch, minPrice, maxPrice, minAmount, maxAmount, brandSearch, categorySearch, tagSearch" />
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
					<form:input type="number" path="minAmount" class="form-control" placeholder="Min amount" />
				</div>
				<div class="col-md-4 col-sm-12">
					<form:input type="number" path="maxAmount" class="form-control" placeholder="Max amount" />
				</div>
				<div class="col-md-4 col-sm-12">
					<form:select path="brandSearch" class="form-control">
						<form:option value="">Choose Brand</form:option>
						<form:options items="${brands}" itemLabel="name" itemValue="id" />
					</form:select>
				</div>
				<div class="col-md-4 col-sm-12">
					<form:select path="categorySearch" class="form-control">
						<form:option value="">Choose category</form:option>
						<c:forEach items="${categories}" var="category">
							<c:choose>
								<c:when test="${category.id eq filter.categorySearch.id}">
									<form:option selected="selected" value="${category.id}">${category.name}</form:option>
								</c:when>
								<c:otherwise>
									<form:option value="${category.id}">${category.name}</form:option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-md-4 col-sm-12">
					<form:select path="tagSearch" class="form-control">
						<form:option value="">Choose tag</form:option>
						<form:options items="${tags}" itemLabel="name" itemValue="id" />
					</form:select>
				</div>
				<button type="submit" class="btn btn-primary pull-right">Search</button>
			</form:form>
		</div>
	</div>
	<div class="row">
		<c:forEach items="${page.content}" var="good">
			<div class="col-md-6 col-lg-4">
				<div class="thumbnail">
					<a href="/showGoods/${good.id}" class="commodity"> <img class="goodsPhoto" src="${good.pathToFile}" alt="${good.name}"></a>
					<div class="caption">
						<h5>${good.name}</h5>
						<p class="description">${good.shortDescription}</p>
						<p class="pull-left price">${good.price}$</p>
						<p class="text-right toBucket">
							<a class="btn btn-primary" href="/showGoods/${good.id}">View</a>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
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
								<li class="active"><a href="<custom:allParams change='page' value='${pageNumber}'/>">${pageNumber} <span class="sr-only" /></span></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="<custom:allParams change='page' value='${pageNumber}'/>">${pageNumber} <span class="sr-only" /></span></a></li>
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
						<li <custom:isActive key="sort" value="name"/>><a href="<custom:allParams change="sort" value="name"/>"> Name</a></li>
						<li <custom:isActive key="sort" value="price"/>><a href="<custom:allParams change="sort" value="price"/>"> Price</a></li>
						<li <custom:isActive key="sort" value="begin"/>><a href="<custom:allParams change="sort" value="begin"/>"> Date</a></li>
					</ul>
				</div>
				<div class="dropup pull-right size">
					<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						Size <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="size">
						<c:forEach begin="6" end="24" varStatus="loop" var="sizeNumber" step="6">
							<li <custom:isActive key="size" value="${sizeNumber}"/>><a href="<custom:allParams change="size" value="${sizeNumber}"/>">${sizeNumber}</a></li>
						</c:forEach>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</div>