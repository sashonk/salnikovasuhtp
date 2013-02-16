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

	StudentsDao dao = StudentsDao.get();
	Student student = dao.findStudent(id);
	
	Map<Control, Attestation> data = dao.getAttestations(id);
	
  
	Group g = GroupsDao.get().getGroup(student.getGroupId());
%>

<div style="font-size: 20px; margin: 5px;"><%= student.getSecondName() %> <%= student.getFirstName() %>, группа <%=g.getName()  %> <!--(<a href="admin/home.jsp?page=delete&type=student&id=<%= student.getId() %>">удалить студента</a>)--></div>
	
<table>
	<tr> 
		<td>контроль</td>
		<td>баллы</td>
	<tr>
	
	<% for(Control ctrl : data.keySet()) { 
		Attestation a = data.get(ctrl);
		
		%>
		<tr> 
			<td><%= ctrl.getName() %></td>
			<td <%= a==null ? "style='background: #FFFF99;'" : "" %>><%=  a==null? "" : a.getPoints() %> </td>
			
			<% if(request.isUserInRole("admin")) { %>
			
			<td ><a href="admin/home.jsp?page=attestation&id=<%=id%>&control=<%=ctrl.getId()%>">аттестовать</a></td>
			
			<%  } %>
		</tr>
	
	
	<%} %>
</table>

<div style="margin: 20px;">Сумма баллов: <b><%= dao.getTotalPoints(id) %></b></div>
