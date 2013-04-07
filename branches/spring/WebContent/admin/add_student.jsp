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
import="salnikova.util.*"
%>

<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);


	String firstName = request.getParameter("firstName");
	String secondName = request.getParameter("secondName");
	String groupIdStr = request.getParameter("group");
	
	Integer depId = null;
	String dep = request.getParameter("department");
	if(dep!=null){
		try{
			depId = Integer.parseInt(dep);
		}
		catch(NumberFormatException ex){
	
		}
	}
	List<Department> deps = ctx.getBean(DepartmentsDao.class).getDepartments();
	if(depId==null && deps.size()>0){
		depId = deps.get(0).getId();
	}
	
	
	List<String> errors = new LinkedList<String>();
	request.setAttribute("errors", errors);
	boolean show = true;
	if(firstName!=null && secondName!= null && groupIdStr!=null){
		
		try{
			Integer group = Integer.parseInt(groupIdStr);
			
			if(Utils.isBlank(firstName)){
				errors.add("не введено имя");
			}
			
			if(Utils.isBlank(secondName)){
				errors.add("не введена фамилия");				
			}
			
			if(errors.size()==0){
					StudentsDao dao = ctx.getBean(StudentsDao.class);
					Student s = dao.createStudent(firstName, secondName, group);
				
				
				%> 
					<div>студент <b><%= ModelUtil.shortName(s) %></b> добавлен</div>
				<%
			
			
				show = false;
			}
		}
		catch(Exception ex){
			errors.add("не удалось добавить студента: " + ex.toString());
		}
	}


	if(show){
	
	GroupsDao dao = ctx.getBean(GroupsDao.class);
	List<Group> groups = dao.getGroups(depId);
%>

	
	
<form>
<table>
	<tr>
		<td>имя</td>
		<td><input name="firstName" value='<%= firstName == null ? "" : firstName %>'/></td>
	</tr>
	
	<tr>
		<td>фамилия</td>
		<td><input name="secondName" value='<%= secondName == null ? "" : secondName %>'/></td>
	</tr>	
	
	<tr>
		<td>факультет</td>
		<td><select class='departments-combo autowire'>
				<% for(Department d : deps){ %>
				
					<option value="<%= d.getId() %>" ><%= d.getName() %></option>
				
				<% } %>
			</select>
		</td>
	</tr>	

	<tr>
		<td>группа</td>
		<td><select name="group" class='groups-combo'>

			</select>
		</td>
	</tr>	
	
</table>

	<input type="submit">
	
	<input type="hidden" name="page" value="add_student" />	
</form>

<jsp:include page="../resources/errors.jsp" />

<% } %>