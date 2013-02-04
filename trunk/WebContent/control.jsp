<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page
	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="java.util.*"
	import="salnikova.util.ModelUtil"
%>

<%
	Integer id = null;
	String idStr = request.getParameter("id");
	if(idStr!=null){
		try{
			id = Integer.parseInt(idStr);
		}
		catch(NumberFormatException ex){
	
		}
	}

	ControlDao dao = ControlDao.get();
	List<Control> controls = dao.getControlList();
%>


<!--<% if(request.isUserInRole("admin")) { %>
<div><a href="admin/home.jsp?page=add_control">добавить</a></div>
<% } %>-->


<table>
	<tr>
		<td>номер</td>	
		<td>название</td>
		<td>макс. балл</td>		
		<td>примеры</td>	
	
	</tr>
	

	<% for(Control ctrl : controls){ 
		Document doc = DocDao.get().findDocument(ctrl.getId());
		%>
		
		<tr>
			<td><%= ctrl.getNumber() %></td>		
			<td><%= ctrl.getName() %></td>
			<td><%= ctrl.getMaxPoint() %></td>		
			<td><% if(doc!=null) {  %><a href="doc.jsp?id=<%= doc.getId() %>"><%=doc.getName() %></a><%  } %></td>					
			
			<% if(request.isUserInRole("admin")) { %>
		
			<td><a href="admin/home.jsp?page=delete&type=control&id=<%=ctrl.getId()%>">удалить</a></td>
			
			<% } %>
			</tr>
	
	<% } %>
</table>

	

