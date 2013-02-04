<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="salnikova.web.*"
	import="salnikova.util.*"

	import="java.util.*"
	import="java.math.*"

%>

<%

	request.setCharacterEncoding("utf-8");

	List<String> msgs = new LinkedList<String>();
	Map<String, Object> map = WebHelper.processRequest(request, null, 2048*2048, msgs);

	Object number = map.get("number");
	Object name = map.get("name");
	Object maxPointsStr = map.get("maxpoint");
	Object examples = map.get("examples");
	
	if(name!=null && maxPointsStr!= null && number!=null){
		BigDecimal maxPoints = BigDecimal.valueOf(Integer.parseInt((String)maxPointsStr));
		Upload doc = (Upload)map.get("examples");
		
		StudentsDao dao = StudentsDao.get();
		
		Control c = new Control(); 
		c.setNumber(Integer.parseInt((String)number));
		c.setName((String)name);
		c.setMaxPoint(maxPoints);
				
		Control newCtrl = ControlDao.get().createControl(c);		
		
		if(doc!=null){
				DocDao.get().createDoc(doc.getName(), doc.getContent(), newCtrl.getId());
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