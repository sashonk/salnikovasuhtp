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
	import="salnikova.Script"
%>

<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

Script s = ctx.getBean(Script.class);

s.doActions(response.getWriter());



%>




