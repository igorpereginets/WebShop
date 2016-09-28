<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-8">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">${user.firstName} ${user.lastName} Profile</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-3 " align="center">
							<a href="${user.pathToFile}" data-lightbox="image-1" data-title="${user.firstName} ${user.lastName}"> <img id="item-display"
								alt="${user.firstName}" src="${user.pathToFile}" class="img-circle img-responsive"></a>
						</div>
						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-responsive">
								<tbody>
									<tr>
										<td>Login:</td>
										<td>${user.login}</td>
									</tr>
									<tr>
										<td>First Name:</td>
										<td>${user.firstName}</td>
									</tr>
									<tr>
										<td>Last Name:</td>
										<td>${user.lastName}</td>
									</tr>
									<tr>
										<td>Birthday:</td>
										<td>${user.birthday}</td>
									</tr>
									<tr>
										<td>Gender:</td>
										<td><c:choose>
												<c:when test="${user.gender}">
														Male
													</c:when>
												<c:otherwise>
														Female
													</c:otherwise>
											</c:choose></td>
									</tr>
									<tr>
										<td>Home Address:</td>
										<td>${user.address.street}, ${user.address.city.name} ${user.address.city.country.name}</td>
									</tr>
									<tr>
										<td>Email:</td>
										<td><a href="mailto:${user.email}">${user.email}</a></td>
									</tr>
									<tr>
										<td>Phone Number:</td>
										<td>${user.telephone}</td>
									</tr>
									<tr>
										<td>Your Money:</td>
										<td>${user.money}$</td>
									</tr>
									<tr>
										<td>Rate:</td>
										<td>${user.rate}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
