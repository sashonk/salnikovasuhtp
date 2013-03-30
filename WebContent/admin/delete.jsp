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

%>

<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);


	Map<String, String> notifyMap = new HashMap<String, String>();
	notifyMap.put("group", "при удалении группы будут удалены все студенты этой группы");
	notifyMap.put("control", "при удалении контроля будут удалены все аттестации по этому контролю");



	Integer id = null;
	String idStr = request.getParameter("id");
	if(idStr!=null){
		try{
			id = Integer.parseInt(idStr);
		}
		catch(NumberFormatException ex){
	
		}
	}

	String type = request.getParameter("type");
	
	
	String force = request.getParameter("force");	
	if(force==null){
		%>
		
		<div>Уверены? <%= notifyMap.get(type)!=null ? notifyMap.get(type) : "" %></div>
		
		
		<form id='yes_form' style="display:none;">
			<input type="hidden" name="id" value="<%=id%>">
			<input type="hidden" name="type" value="<%= type %>">
			<input type="hidden" name="page" value="delete">
			<input type="hidden" name="force" value="1">
		</form>
		
		<button onclick="document.getElementById('yes_form').submit();">ДА</button>
		<button onclick="history.go(-1);">НЕТ</button>
		
		<%
	}
	else{
	
		if(type.equals("control")){
			ctx.getBean(ControlDao.class).deleteControl(id);
			%>контроль удален<%
		}
		else if(type.equals("student")){
			ctx.getBean(StudentsDao.class).deleteStudent(id);
			%>студент удален<%
		}   
		else if(type.equals("group")){
			ctx.getBean(GroupsDao.class).delete(id);
			%>группа удалена<%
		} 
		else if(type.equals("tutor")){
			ctx.getBean(TutorsDao.class).delete(id);
			%>пользователь удален<%		
			
		}
				

	}
%>
