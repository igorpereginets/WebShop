<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="/resources/css/sb-admin.css" rel="stylesheet" />
<link href="/resources/css/plugins/morris.css" rel="stylesheet" />
<link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<link href="/resources/css/admin-forms.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/chosen.css">
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/chosen.jquery.min.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<div id="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
	</div>
	<script type="text/javascript">
		$(function() {
			$(".chzn-select").chosen({
				width : "100%"
			});
		});
	</script>
	<script type="text/javascript">
		$('#male').change(function() {

			$('#female').attr('checked', false);

		});
	</script>
	<script type="text/javascript">
		$('#female').change(function() {

			$('#male').attr('checked', false);

		});
	</script>

</body>
</html>