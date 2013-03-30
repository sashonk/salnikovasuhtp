<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<LINK href="resources/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="resources/jquery.js"></script>
<script type="text/javascript" src="resources/index.js"></script>
<script type="text/javascript" src="resources/script.js"></script>

</head>


<%@ page
import="salnikova.web.Pages"
import="org.springframework.web.context.support.WebApplicationContextUtils"
import="org.springframework.web.context.WebApplicationContext"
import="org.springframework.web.servlet.support.RequestContextUtils"
import="java.io.File"
%>

<%

final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
	
   Object pg = request.getAttribute("page");
%>

<body>


		<img class="bg-image" style='position: absolute; z-index: -1; width:100%; height: 100%;' src='resources/blank.gif'>
		
		<div class='html'>
		<div class="header">
		<% 
			String name = null;
			if(request.getUserPrincipal()!=null){
				name = request.getUserPrincipal().getName();
			}
		%>
		
		<div style='float:right; <%= name==null? "visibility: hidden;" : "" %>'><span>Здравствуйте, <b><%=name %></b></span><br><span><a  href="j_spring_security_logout">выход</a></span></div>
		<div style='clear:both; <%= name==null? "visibility: hidden;" : "" %>'></div>
		

		<div style="text-align:center; font-size: 36px; font-family: times new roman;">Система "СТУДЕНТ"</div>
		<div style='text-align:center; margin-bottom: 5px;'><i>Не бойся не знать. Бойся не учиться</i></div>
		
		<div class='navi'>
		<a class="navi_link" href="index.html""><img <%= (pg==null || "summary".equals(pg)) ? "style='display:none;'" : "" %>  src='resources/main.png'><img <%= (pg==null || "summary".equals(pg)) ? "" : "style='display:none;'" %> src='resources/main-hover.png'></a>
		<a class="navi_link" href="<%=Pages.CONTACTS%>.html"><img <%= Pages.CONTACTS.equals(pg) ? "style='display:none;'" : "" %> src='resources/contacts.png'><img <%= Pages.CONTACTS.equals(pg) ? "" : "style='display:none;'" %> src='resources/contacts-hover.png' ></a>
		<a class="navi_link" href="resources/schedule.pdf"><img src='resources/schedule.png' ></a>
		<a class="navi_link" href="<%=Pages.BIBLIOGRAPHY %>.html" ><img <%= Pages.BIBLIOGRAPHY.equals(pg) ? "style='display:none;'" : "" %> src='resources/bibliography.png'  ><img <%= Pages.BIBLIOGRAPHY.equals(pg) ? "" : "style='display:none;'" %> src='resources/bibliography-hover.png'  ></a>
		<a class="navi_link" href="admin/<%= Pages.MANAGEMENT %>.html "><img src='resources/management.png'></a>
	
		
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
		
		
		<% if(new File(request.getServletContext().getRealPath("/"+pg+".jsp")).exists()){  %>
			
			<jsp:include page="${page}.jsp" />
		
		<% } else{ 
			%>Страница не существует<%
		}%>
		
	
		
</div>

		<!--<div class="boot"><a href="contacts.html">КОНТАКТЫ </a>&copy sashonk 2013</div>-->
		</div>
</body>
</html>