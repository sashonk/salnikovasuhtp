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

%>

<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

	request.setCharacterEncoding("utf-8");

	List<String> msgs = new LinkedList<String>();
	Map<String, Object> map = WebHelper.processRequest(request, null, 2048*2048, msgs);

	String number = (String)map.get("number");
	String name = (String)map.get("name");
	String maxPointsStr = (String)map.get("maxpoint");
	
	if(name!=null && maxPointsStr!= null && number!=null){
		BigDecimal maxPoints = BigDecimal.valueOf(Double.parseDouble(maxPointsStr));
		Upload doc = (Upload)map.get("examples");
		
		StudentsDao dao = ctx.getBean(StudentsDao.class);
						
		Control newCtrl = ctx.getBean(ControlDao.class).createControl((String)name, maxPoints, Integer.parseInt((String)number));		
		
		if(doc!=null){
			ctx.getBean(DocDao.class).createDoc(doc.getName(), doc.getContent(), newCtrl.getId());
		}
%> 
			<div>создан контроль <b><%= name %></b> </div>
		<%
		
		
	}
	else{
	

%>

	
	
<form enctype="multipart/form-data" method="POST">
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
	
	
	<tr>
		<td>примеры</td>
		<td><input type="file" name="examples"/></td>
	</tr>	
	
</table>

	<input type="submit">
	
	
	<input type="hidden" name="page" value="add_control" />
</form>

<% } %>