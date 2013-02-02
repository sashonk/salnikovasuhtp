<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page
	import="java.util.*"
	import="salnikova.dao.StudentsHelper" 
	import="salnikova.dao.DocHelper" 
	import="salnikova.model.Document"


%>
	
Integer id = null;
String idStr = request.getParameter("id");
if(idStr!=null){
	try{
		id = Integer.parseInt(idStr);
	}
	catch(NumberFormatException ex){
		
	}
}

Document doc = DocHelper.getDocument(id);
response.setContentType("application/force-download");
response.setContentLength(document.getSize());
response.getOutputStream().write(content);

