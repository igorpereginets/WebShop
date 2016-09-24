<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css" href="/resources/css/registration.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<div class="container registration">
	<div class="row main">
		<div class="panel-heading">
			<div class="panel-title text-center">
				<h1 class="title">elimSShop</h1>
				<hr />
			</div>
		</div>
		<div class="main-login main-center">
			<form:form class="form-horizontal" method="post" action="/registration" modelAttribute="user">
				<div class="form-group">
					<form:errors class="error" path="login" />
					<label for="login" class="cols-sm-2 control-label">Your Login</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<form:input type="text" class="form-control" path="login" id="login" placeholder="Enter your Login" required="true" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:errors class="error" path="email" />
					<label for="email" class="cols-sm-2 control-label">Your Email</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
							<form:input type="text" class="form-control" path="email" id="email" placeholder="Enter your Email" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:errors class="error" path="firstName" />
					<label for="firstName" class="cols-sm-2 control-label">Your First Name</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
							<form:input type="text" class="form-control" path="firstName" id="firstName" placeholder="Enter your First Name" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:errors class="error" path="lastName" />
					<label for="lastName" class="cols-sm-2 control-label">Your Last Name</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
							<form:input type="text" class="form-control" path="lastName" id="lastName" placeholder="Enter your Last Name" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:errors class="error" path="telephone" />
					<label for="telephone" class="cols-sm-2 control-label">Your Telephone</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-phone fa" aria-hidden="true"></i></span>
							<form:input type="text" class="form-control" path="telephone" id="telephone" placeholder="Enter your Telephone" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:errors class="error" path="password" />
					<label for="password" class="cols-sm-2 control-label">Password</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<form:input type="password" class="form-control" path="password" id="password" placeholder="Enter your Password" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
							<form:input type="password" class="form-control" path="confirm" id="confirm" placeholder="Confirm your Password" />
						</div>
					</div>
				</div>
				<div class="form-group ">
					<button type="submit" class="btn btn-primary btn-lg btn-block login-button">Register</button>
				</div>
			</form:form>
		</div>
	</div>
</div>