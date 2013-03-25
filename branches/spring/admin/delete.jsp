<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="java.util.*"

%>

<%
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
			ControlDao.get().deleteControl(id);
			%>контроль удален<%
		}
		else if(type.equals("student")){
			StudentsDao.get().deleteStudent(id);
			%>студент удален<%
		}
		else if(type.equals("group")){
			GroupsDao.get().delete(id);
			%>группа удалена<%
		}
		else if(type.equals("attestation")){
			Integer controlId =Integer.parseInt(request.getParameter("control"));			
			StudentsDao.get().deleteAttestation(id, controlId);
			%>контроль удален<%
		}		

	}
%>
