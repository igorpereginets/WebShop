<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/resources/css/myGoods.css" rel='stylesheet' type='text/css'>
<div class="col-md-8">
	<div class="row">
		<table class="table table-hover table-striped col-md-12">
			<tbody>
				<c:forEach items="${goods}" var="good">
					<tr class="${good.active ? 'success' : 'danger'}">
						<td class="col-md-3"><img class="pull-left photo" src="${good.pathToFile}" alt="${good.name}" /></td>
						<td class="col-md-7">
							<h5>${good.name}</h5>
							<p class="definition">${good.shortDescription}</p>
							<p class="price">${good.price}$</p>
						</td>
						<td class="col-md-2">
							<h2 class="text-center">${good.state.name}</h2> <a href="/deleteGoods/${good.id}" class="btn btn-danger pull-right delete">Delete</a> <a
							href="/showGoods/${good.id}" class="btn btn-primary pull-right view">Take a View</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
