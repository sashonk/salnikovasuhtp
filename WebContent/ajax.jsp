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
		import="org.json.*"
		import="salnikova.util.*"
%>

<%
	final WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
final String cmd = request.getParameter("cmd");
final JSONObject object = new JSONObject();

try{


if("get_groups".equals(cmd)){
	Integer depId = Integer.parseInt(request.getParameter("depid"));
	
	List<Group> groups = ctx.getBean(GroupsDao.class).getGroups(depId);
	JSONArray data = new JSONArray();
	for(Group g : groups){
		JSONObject entry = new JSONObject();
		entry.put("id", g.getId());
		entry.put("name", g.getName());
		data.put(entry);
	}
	
	object.put("data", data);
}

else if("get_summary".equals(cmd)){
	Integer group = Integer.parseInt(request.getParameter("group"));
	Map<Student, Map<Integer, Attestation>> data = ctx.getBean(SummaryDao.class).getAtts(group);
	List<Control> controlList = ctx.getBean(ControlDao.class).getControlList();
	
	Set<Student> set = new TreeSet<Student>(data.keySet());
	JSONArray students = new JSONArray();
	for(Student student : set){
		Map<Integer, Attestation> mp = data.get(student);
		JSONObject st = new JSONObject();
		st.put("name", ModelUtil.shortName(student));
		st.put("id", student.getId());
		
		JSONArray atts = new JSONArray();
		for(Control c : controlList){
	Attestation att = mp.get(c.getId());
	JSONObject at = new JSONObject();
	at.put("id", c.getId());
	at.put("points", att.getPoints());
	atts.put(at);
		}
		st.put("attestations", atts);
		
		students.put(st);
		
	}
	object.put("students", students);
}

else{
	throw new IllegalArgumentException("unknown command");
}




}
catch(Exception ex){
	object.put("error", ex);
}

response.getWriter().println(object.toString());
%>