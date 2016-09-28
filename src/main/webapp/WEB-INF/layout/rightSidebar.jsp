<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="col-md-2">
	<security:authorize access="isAuthenticated()">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">My Bucket</h3>
			</div>
			<div class="panel-body">
				<h5>Goods in bucket: ${bucketCount}</h5>
				<a href="/myBucket" class="btn btn-primary navbar-btn">Check Out</a>
			</div>
		</div>
	</security:authorize>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Tags</h3>
		</div>
		<div class="panel-body">
			<c:forEach items="${tags}" var="tag">
				<li class="list-group-item"><a href="/?tagSearch=${tag.id}"> ${tag.name} </a></li>
			</c:forEach>
		</div>
	</div>
</div>