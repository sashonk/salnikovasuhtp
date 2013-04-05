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
		import="salnikova.orm.Storage"

%>

<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
final TutorsDao dao = ctx.getBean(TutorsDao.class);
final DepartmentsDao depDao = ctx.getBean(DepartmentsDao.class);
final GroupsDao gDao = ctx.getBean(GroupsDao.class);
final Storage storage = ctx.getBean(Storage.class);

%>
	<div><a class="admin_btn" href="add_tutor.html">новый пользователь</a></div>

	<div style='margin: 5px;'></div>
	<div style='padding: 20px;'>
<%

 Integer id = null; 
 if(request.getParameter("id")!=null){
	 id = Integer.parseInt(request.getParameter("id"));
 }
 
 if(request.getParameter("supervise")!=null){
	 Integer group = Integer.parseInt(request.getParameter("group"));
	 Group g = gDao.getGroup(group);
	 g.setTutorId(id);
	 g = storage.save(g);
	 response.sendRedirect("tutors.html?id="+id);
 }
 else if(request.getParameter("deprive")!=null){
	 Integer group = Integer.parseInt(request.getParameter("group"));
	 Group g = gDao.getGroup(group);
	 g.setTutorId(null);
	 g = storage.save(g);
	 response.sendRedirect("tutors.html?id="+id);
 }
 

if(id!=null){
	final List<Department> deps = depDao.getDepartments();
 	final Tutor tutor = dao.getTutor(id);
 	
	final List<Group> groups = dao.getGroups(id);

	%> <div style='margin: 5px;'> <%=tutor.getLastName() %> </div> <div style='padding:20px;'><%
			
	for(Group g : groups){
		%><div> <%= g.getName() %> <a href='tutors.html?deprive=1&group=<%=g.getId()%>&id=<%=id%>'>удалить</a></div><%
	}

	%> 
	
	</div>
	
	<div>
	<form>
	фак-т 
	
	<select class='departments-combo autowire'>	
		<% for(Department dep : deps){ %>
			<option value='<%= dep.getId() %>'><%= dep.getName() %></option>
		
		<%} %>
	</select> 
	гр. 
	<select name='group' class='groups-combo'>
	</select> 
	
	<input type='submit' />
	<input type='hidden' name='supervise' />
	<input type='hidden' name='id' value='<%= id %>'>
	</form>
	</div>
	<%
}
else{
	List<Tutor> all = dao.getAll();
	for(Tutor tutor : all){
		%><div style='margin: 5px; '><a style='margin-right: 50px;' href='tutors.html?id=<%= tutor.getId() %>'><%= tutor.getLastName() %></a> (<a  href='delete.html?type=tutor&id=<%= tutor.getId() %>'>удалить</a>)</div><%
	}
}



%></div>