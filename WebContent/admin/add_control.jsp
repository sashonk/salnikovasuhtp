<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="java.util.*"
	import="java.math.*"

%>

<%
	String number = request.getParameter("number");
	String name = request.getParameter("name");
	String maxPointsStr = request.getParameter("maxpoint");
	
	if(name!=null && maxPointsStr!= null && number!=null){
		BigDecimal maxPoints = BigDecimal.valueOf(Integer.parseInt(maxPointsStr));		
		StudentsDao dao = StudentsDao.get();
		
		Control c = new Control(); 
		c.setNumber(Integer.parseInt(number));
		c.setName(name);
		c.setMaxPoint(maxPoints);
		
		ControlDao.get().createControl(c);
		
		%> 
			<div>создан контроль <b><%= name %></b> </div>
		<%
		
		
	}
	else{
	

%>

	
	
<form>
<table>
	<tr>
		<td>порядковый номер</td>
		<td><input name="number"/></td>
	</tr>
	<tr>
		<td>название</td>
		<td><input name="name"/></td>
	</tr>
	
	<tr>
		<td>максимальный балл</td>
		<td><input name="maxpoint"/></td>
	</tr>	
	
</table>

	<input type="submit">
	
	
	<input type="hidden" name="page" value="add_control" />
</form>

<% } %>