<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<LINK href="style.css" rel="stylesheet" type="text/css">

</head>


<%@ page
import="salnikova.web.Pages"


%>

<%
    String pg = request.getParameter("page");
%>

<body>
		<img style='position: absolute; z-index: -1; width:100%; height: 100%;' src='univer.jpg'>
		
		<div class='html'>
		<div class="header">
		<% 
			String name = null;
			if(request.getUserPrincipal()!=null){
				name = request.getUserPrincipal().getName();
			}
		%>
		
		<div style='float:right; <%= name==null? "visibility: hidden;" : "" %>'><span>Здравствуйте, <b><%=name %></b></span><br><span><a  href="?logout">выход</a></span></div>
		<div style='clear:both; <%= name==null? "visibility: hidden;" : "" %>'></div>
		

		<div style="text-align:center; font-size: 36px; font-family: times new roman;">Система "СТУДЕНТ"</div>
		<div style='text-align:center; margin-bottom: 5px;'><i>Не бойся не знать. Бойся не учиться</i></div>
		
		<div class='navi'><a class="navi_link" href="?"><img <%= (pg==null || "".equals(pg)) ? "style='display:none;'" : "" %>  src='main.png'><img <%= (pg==null || "".equals(pg)) ? "" : "style='display:none;'" %> src='main-hover.png'></a>
		<a class="navi_link" href="?page=control"><img <%= Pages.CONTROL.equals(pg) ? "style='display:none;'" : "" %> src='control.png'><img <%= Pages.CONTROL.equals(pg) ? "" : "style='display:none;'" %> src='control-hover.png' ></a>
		<a class="navi_link" href="?page=contacts"><img <%= Pages.CONTACTS.equals(pg) ? "style='display:none;'" : "" %> src='contacts.png'><img <%= Pages.CONTACTS.equals(pg) ? "" : "style='display:none;'" %> src='contacts-hover.png' ></a>
		<a class="navi_link" href="schedule.pdf"><img src='schedule.png' ></a>
		<a class="navi_link" href="admin/home.jsp?page=management"><img src='management.png'></a>

		
		<% if(request.isUserInRole("admin")) { 
			
			if(request.getParameter("logout")!=null){
				session.invalidate();
				response.sendRedirect("?");
			}
			
			%>

			

			
			


		<% } %>
		</div>
		
		</div>
		<div class="content" align="center">
		
		
		<%
			
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
		
		
		
		
		
		<%}else if(pg.equals("schedule")){%>
		
		<%@ include file="schedule.jsp" %>	
		
		
		
		
		<%}else if(pg.equals("login_error")){%>
		
		<% response.sendRedirect("login.jsp?error"); %>	
		
		
		<%}%>
		
</div>

		<div class="boot"><a href="?page=contacts">КОНТАКТЫ </a>&copy sashonk 2013</div>
		</div>
</body>
</html>