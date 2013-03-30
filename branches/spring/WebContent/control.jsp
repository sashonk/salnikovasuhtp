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
final TutorsDao tutorsDao = ctx.getBean(TutorsDao.class);
 Tutor tutor = null;
if(request.getUserPrincipal()!=null){
	tutor = tutorsDao.findProfile(request.getUserPrincipal().getName());
}

	Integer id = null;
	String idStr = request.getParameter("id");
	if(idStr!=null){
		try{
			id = Integer.parseInt(idStr);
		}
		catch(NumberFormatException ex){
	
		}
	}
	
	if(id==null){
		throw new IllegalArgumentException("expected 'id' parameter");
	}

	ControlDao dao = ctx.getBean(ControlDao.class);
	Control control = dao.getControl(id);
	List<Control> controls = new ArrayList<Control>(1);
	controls.add(control);
%>


<table>
	<tr>
		<td>номер</td>	
		<td>название</td>
		<td>макс. балл</td>		
		<td>примеры</td>	
	
	</tr>
	

	<% for(Control ctrl : controls){ 
		Document doc = ctx.getBean(DocDao.class).findDocument(ctrl.getId());
		%>
		
		<tr>
			<td><%= ctrl.getNumber() %></td>		
			<td><%= ctrl.getName() %></td>
			<td><%= ctrl.getMaxPoint() %></td>		
			<td><% if(doc!=null) {  %><a href="doc.html?id=<%= doc.getId() %>"><%=doc.getName() %></a><%  } %></td>					
			
			<% if(tutor!=null && tutor.getId().equals(ctrl.getOwnerId())) { %>
		
			<td><a href="admin/delete.html?type=control&id=<%=ctrl.getId()%>">удалить</a></td>
			
			<% } %>
			</tr>
	
	<% } %>
</table>

	

