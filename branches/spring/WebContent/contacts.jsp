<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ page

import="salnikova.dao.*" 
import="salnikova.model.*"
import="java.util.*"
import="java.math.*"	
		import="org.springframework.web.context.support.WebApplicationContextUtils"
		import="org.springframework.web.context.WebApplicationContext"
		import="org.springframework.web.servlet.support.RequestContextUtils"
		import="salnikova.web.*"
	import="salnikova.util.*"

%>

<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
final TutorsDao dao = ctx.getBean(TutorsDao.class);

List<Tutor> tutors = dao.getAll();

for(Tutor tutor : tutors){
	%> 
		<div> <%= ModelUtil.fullName(tutor) %> </div>
		<div>
		
		<% if(!Utils.isBlank(tutor.getDegree())){ %>
		 <%= tutor.getDegree() %>, 			
		<% } %>
		
		<% if(!Utils.isBlank(tutor.getPost())){ %>
		 <%= tutor.getPost() %> 			
		<% } %>	
		
		</div>
		
		<div>
		<% if(!Utils.isBlank(tutor.getEmail())){ %>
		 <%= tutor.getEmail() %> 			
		<% } %>		
		
		</div>
		
		<br>
	<%
}



%>




