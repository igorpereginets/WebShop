<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/lightbox.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/resources/main.css">
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/lightbox.min.js"></script>
	<div class="container-fluid">
		<tiles:insertAttribute name="header" />
		<div class="row content">
			<tiles:insertAttribute name="left-sidebar" />
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="right-sidebar" />
		</div>
	</div>
</body>
</html>