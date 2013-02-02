<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

import="salnikova.dao.*" 
import="salnikova.model.*"
import="java.util.*"
import="java.math.*"	
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

	
	
	Integer control = null;
	String controlStr = request.getParameter("control");
	if(controlStr!=null){
		try{
			control = Integer.parseInt(controlStr);
		}
		catch(NumberFormatException ex){
	
		}
	}
	
	
	ControlDao cDao = ControlDao.get();
	StudentsDao sDao = StudentsDao.get();
	
	Control ctrl = cDao.getControl(control);
	Student student = sDao.findStudent(id);
	
	
	String pointsStr = request.getParameter("points");
	BigDecimal points = BigDecimal.ZERO;
	if(pointsStr!=null && control != null && id !=null){
		points = BigDecimal.valueOf(Double.parseDouble(pointsStr));						
		sDao.createAttestation(id, control, points);
		
		%> 
			<div>аттестация студента <b><%= student.getSecondName() %></b> по контролю <b><%= ctrl.getName() %></b> произведена</div>
			<div><a href="../?page=student&id=<%=id%>">назад к <%= ModelUtil.shortName(student) %></a></div>
		<%
	}
	else{
	
	

			
%>

<div><span style="color:#ff0000">максимальный балл: <%= ctrl.getMaxPoint() %></span></div>
<form>
<table>	
	<tr>
		<td>балл</td>
		<td><input name="points"/></td>
	</tr>
	
		
</table>	
<input type="submit"/>

<input type="hidden" name="page" value="attestation" />
<input type="hidden" name="id" value="<%=id %>" />
<input type="hidden" name="control" value="<%=control %>" />
</form>

<% } %>