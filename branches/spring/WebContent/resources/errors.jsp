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
	Object o = request.getAttribute("errors");


	if(o instanceof List){
		List<String> errors = (List<String>)o;
		if(errors.size()>0){
			%> <ul style='color:#ff0000;'> <%
		}
		
		for(String error : errors){
			%> <li> <%= error %> </li> <%
		}

		if(errors.size()>0){
			%> </ul> <%
		}
		
	}


%>




