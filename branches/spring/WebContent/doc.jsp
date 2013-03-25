<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page
	import="java.util.*"
	import="salnikova.dao.DocDao" 
	import="salnikova.model.*"
	import="java.net.URLEncoder"
	import="java.io.OutputStream"

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

OutputStream os = response.getOutputStream();

DocData doc = DocDao.get().getDocData(id);
Document d = DocDao.get().getDocument(id);

String filename = URLEncoder.encode(d.getName().replace(" ", "_").toString(),"UTF-8");
response.setContentType("application/force-download");
response.setContentLength(doc.getContent().length);
response.setHeader("Content-Disposition", "attachment; filename=" + filename);
os.write(doc.getContent());

%>