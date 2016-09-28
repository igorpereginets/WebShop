<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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