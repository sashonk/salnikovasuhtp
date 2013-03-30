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
final TutorsDao dao = ctx.getBean(TutorsDao.class);

String idStr = request.getParameter("id");
Integer id = null;
if(idStr!=null){
	try{
		id = Integer.parseInt(idStr);
	}
	catch(Exception ex){
		
	}
}

if(id!=null){
	
	Tutor t = dao.getTutor(id);
	
	List<Group> groups = dao.getGroups(t.getId());
	for(Group g : groups){
		%> <div><%= g.getName() %> </div> <%
	}
	
	
}
else{

	List<Tutor> tutors = dao.getAll();
	for(final Tutor tutor : tutors){
		%> <div><a href='tutors.html?id=<%= tutor.getId() %>'><%= tutor.getLastName() %></a></div> <%
	}		

}


%>