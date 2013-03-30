<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 


<%@ page

 import="salnikova.web.Pages"

 	
%>


<html>
<head>
<script type="text/javascript" src="../resources/jquery.js"></script>
<script type="text/javascript" src="../resources/admin.js"></script>
<script type="text/javascript" src="../resources/script.js"></script>
</head>

<body>
<div><a href="..">на главную</a></div>
<div><a href="management.html">управление</a></div>
<div style='margin-top: 5px'></div>

<%
	Object pg = request.getAttribute("page");




	if(pg == null){
		response.sendRedirect("..");
		return;
	}

	
	
%>
	<jsp:include page="${page}.jsp" />

</body>
</html>