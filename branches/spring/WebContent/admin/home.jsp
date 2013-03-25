<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

 import="salnikova.web.Pages"


%>




<div><a href="?">на главную</a></div>


<%
	String pg = request.getParameter("page");




	if(pg == null){
		response.sendRedirect("..");
		return;
	}

	
	
	
	
	if(pg.equals("add_student")){%>
		<%@ include file="add_student.jsp" %>
	<%}
	
	
	
	
	
	else if(pg.equals("add_control")){%>
		<%@ include file="add_control.jsp" %>
	<%}

	
	
	
	
	else if(pg.equals("add_group")){%>
		<%@ include file="add_group.jsp" %>
	<%}	
	
	
	
	
	
	else if(pg.equals("attestation")){%>
		<%@ include file="attestation.jsp" %>
	<%}
	
	
	
	
	else if(pg.equals("management")){%>
	<%@ include file="management.jsp" %>
	<%}
	
	
	
	
	else if(pg.equals("delete")){%>
	<%@ include file="delete.jsp" %>
<%}
				
%>



