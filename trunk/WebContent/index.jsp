<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<LINK href="style.css" rel="stylesheet" type="text/css">

</head>
<body>

		<div class="header">
		<div style="text-align:center; font-size: 36px; font-family: times new roman;">Система "СТУДЕНТ"</div>
		
		<div><a class="navi_link" href="?">на главную</a></div>
		<div><a class="navi_link" href="?page=control">контроль</a></div>
		<div><a class="navi_link" href="?page=contacts">контакты</a></div>
		
		<% if(request.isUserInRole("admin")) { 
			
			if(request.getParameter("logout")!=null){
				request.logout();
				response.sendRedirect("?");
			}
			
			%>

			
			<div><a class="admin_btn" href="admin/home.jsp?page=add_student">добавить студента</a></div>
			<div><a class="admin_btn" href="admin/home.jsp?page=add_group">добавить группу</a></div>
			<div><a class="admin_btn" href="admin/home.jsp?page=add_control">добавить КТ</a></div>
			
			<div style="margin: 10px;"><a class="admin_btn" href="?logout">выход</a></div>

		<% } %>
		
		</div>
		<div class="content" align="center">
		
		
		<%
			String pg = request.getParameter("page");
				if(pg==null){
					pg = "summary";
		
		}
				
				
				if(pg.equals("student")){%>
				
		<%@ include file="student.jsp" %>						
		
		
		<%}else if(pg.equals("control")){%>				
							
		<%@ include file="control.jsp" %>	
		
			
		 
		
		<%}else if(pg.equals("summary")){%>
				
		<%@ include file="summary.jsp" %>	
		
		
		

		
		
		<%}else if(pg.equals("contacts")){%>
		
		<%@ include file="contacts.jsp"%>	
		
		
		
		
		
		<%}else if(pg.equals("login")){%>
		
		<% response.sendRedirect("login.jsp"); %>	
		
		
		
		
		<%}else if(pg.equals("login_error")){%>
		
		<% response.sendRedirect("login.jsp?error"); %>	
		
		
		<%}%>
		
</div>
	
		<div class="boot"><a href="?page=contacts">КОНТАКТЫ </a>&copy sashonk 2012</div>
</body>
</html>