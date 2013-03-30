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


	String name = request.getParameter("name");
	String depStr = request.getParameter("dep");
	String gradeStr = request.getParameter("grade");
	
	if(name!=null && depStr!= null && gradeStr!=null){
		Integer dep = Integer.parseInt(depStr);		
		Integer grade = Integer.parseInt(gradeStr);
		GroupsDao dao = ctx.getBean(GroupsDao.class);
		
		dao.createGroup(name, dep, grade);
		
		%> 
			<div>группа <b><%= name %></b> добавлена</div>
		<%
		
		
	}
	else{
	
	DepartmentsDao dao =ctx.getBean(DepartmentsDao.class);
	List<Department> departments = dao.getDepartments();
%>

	
	
<form>
<table>

	<tr>
		<td>курс</td>
		<td><input name="grade"/></td>
	</tr>

	<tr>
		<td>название</td>
		<td><input name="name"/></td>
	</tr>
	
	
	<tr>
		<td>факультет</td>
		<td><select name="dep">
				<% for(Department dep : departments){ %>
				
					<option value="<%= dep.getId() %>"><%= dep.getName() %></option>
				
				<% } %>
			</select>
		</td>
	</tr>	
	
</table>

	<input type="submit">
	
	<input type="hidden" name="page" value="add_group" />
</form>

<% } %>