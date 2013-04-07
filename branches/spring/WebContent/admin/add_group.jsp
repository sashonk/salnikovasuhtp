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
		import="salnikova.util.Utils"

%>

<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);


	String name = request.getParameter("name");
	String depStr = request.getParameter("dep");
	String gradeStr = request.getParameter("grade");
	
	List<String> errors = new LinkedList<String>();
	request.setAttribute("errors", errors);
	boolean show = true;
	if(name!=null && depStr!= null && gradeStr!=null){
		
		try{
			Integer dep = Integer.parseInt(depStr);		
			Integer grade = Integer.parseInt(gradeStr);
			
			if(Utils.isBlank(name)){
				errors.add("не введено название");
			}
			
			if(errors.size()==0){
				GroupsDao dao = ctx.getBean(GroupsDao.class);			
				dao.createGroup(name, dep, grade);
				
				%> 
					<div>группа <b><%= name %></b> добавлена</div>
				<%
				
				show = false;
			}
		}
		catch(Exception ex){
			errors.add("не удалось добавить группу: "+ ex.toString());
		}
		
	}
	
	if(show){
	
	DepartmentsDao dao =ctx.getBean(DepartmentsDao.class);
	List<Department> departments = dao.getDepartments();
%>

	
	
<form>
<table>

	<tr>
		<td>курс</td>
		<td><input name="grade" value='<%=gradeStr==null? "" : gradeStr %>'/></td>
	</tr>

	<tr>
		<td>название</td>
		<td><input name="name"  value='<%=name==null? "" : name %>'/></td>
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

<jsp:include page="../resources/errors.jsp" />

<% } %>