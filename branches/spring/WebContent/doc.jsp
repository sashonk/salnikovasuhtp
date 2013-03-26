<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page
	import="java.util.*"
	import="salnikova.dao.DocDao" 
	import="salnikova.model.*"
	import="java.net.URLEncoder"
	import="java.io.OutputStream"
			import="org.springframework.web.context.support.WebApplicationContextUtils"
			import="org.springframework.web.context.WebApplicationContext"
			import="org.springframework.web.servlet.support.RequestContextUtils"
%>
	
<%
final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);


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

DocData doc = ctx.getBean(DocDao.class).getDocData(id);
Document d = ctx.getBean(DocDao.class).getDocument(id);

String filename = URLEncoder.encode(d.getName().replace(" ", "_").toString(),"UTF-8");
response.setContentType("application/force-download");
response.setContentLength(doc.getContent().length);
response.setHeader("Content-Disposition", "attachment; filename=" + filename);
os.write(doc.getContent());
os.close();

%>