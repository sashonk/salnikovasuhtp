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

	Integer id = null;
	String idStr = request.getParameter("id");
	if(idStr!=null){
		try{
			id = Integer.parseInt(idStr);
		}
		catch(NumberFormatException ex){
	
		}
	}

	StudentsDao dao = ctx.getBean(StudentsDao.class);
	Student student = dao.findStudent(id);
	
	Map<Control, Attestation> data = dao.getAttestations(id);
	
  
	Group g = ctx.getBean(GroupsDao.class).getGroup(student.getGroupId());
%>

<div style="font-size: 20px; margin: 5px;"><%= student.getSecondName() %> <%= student.getFirstName() %>, группа <a href='summary.html?group=<%=g.getId() %>'><%=g.getName()  %></a> </div>
	
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
			
			<td ><a href="admin/attestation.html?id=<%=id%>&control=<%=ctrl.getId()%>">аттестовать</a></td>
			
			<%  } %>
		</tr>
	
	
	<%} %>
</table>

<div style="margin: 20px;">Сумма баллов: <b><%= dao.getTotalPoints(id) %></b></div>
