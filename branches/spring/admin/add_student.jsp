<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="java.util.*"

%>

<%
	String firstName = request.getParameter("firstName");
	String secondName = request.getParameter("secondName");
	String groupIdStr = request.getParameter("group");
	
	if(firstName!=null && secondName!= null && groupIdStr!=null){
		Integer group = Integer.parseInt(groupIdStr);
		
		StudentsDao dao = StudentsDao.get();
		
		Student s = new Student();
		s.setFirstName(firstName);
		s.setSecondName(secondName);
		s.setGroupId(group);
		dao.createStudent(s);
		
		%> 
			<div>студент <b><%= secondName %></b> добавлен</div>
		<%
		
		
	}
	else{
	
	GroupsDao dao = GroupsDao.get();
	List<Group> groups = dao.getGroups(null);
%>

	
	
<form>
<table>
	<tr>
		<td>имя</td>
		<td><input name="firstName"/></td>
	</tr>
	
	<tr>
		<td>фамилия</td>
		<td><input name="secondName"/></td>
	</tr>	

	<tr>
		<td>группа</td>
		<td><select name="group">
				<% for(Group group : groups){ %>
				
					<option value="<%= group.getId() %>" ><%= group.getName() %></option>
				
				<% } %>
			</select>
		</td>
	</tr>	
	
</table>

	<input type="submit">
	
	<input type="hidden" name="page" value="add_student" />	
</form>

<% } %>