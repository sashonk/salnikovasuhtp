<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page
import="salnikova.web.Pages"
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

	Integer depId = null;	
	Integer groupId = null;
	String group = request.getParameter("group");
	if(group!=null){
		try{
			groupId = Integer.parseInt(group);
		}
		catch(NumberFormatException ex){
	
		}
	}	
	
	DepartmentsDao depDao = ctx.getBean(DepartmentsDao.class);
			
	List<Department> deps = depDao.getDepartments();
	if(depId==null && deps.size()>0){
		
		if(groupId!=null){
			depId = depDao.findDepartment(groupId).getId();
		}
		else{	
			depId = deps.get(0).getId();
		}
	}
	
	GroupsDao gdao = ctx.getBean(GroupsDao.class);
	List<Group> groups = depId!=null? gdao.getGroups(depId) : new LinkedList<Group>();
	if(groupId==null && groups.size() > 0){
		groupId = groups.get(0).getId();
	}
	
	
	String surname = request.getParameter("secondname");
	
	SummaryDao dao =	ctx.getBean(SummaryDao.class);
	Map<Student, Map<Integer, Attestation>> data = dao.getAtts(groupId);
	 
	ControlDao ctrlDao = ctx.getBean(ControlDao.class);
	List<Control> controls = dao.getControls(groupId);
	StudentsDao sDao = ctx.getBean(StudentsDao.class);
%>

<div align="center">



<form>
	фак-т
	<select  class='departments-combo' >

	<% for(Department d : deps){ %>
	
		<option value="<%= d.getId() %>" <%= d.getId().equals(depId) ? "selected" : ""  %> ><%= d.getName() %></option>
	
	<% } %>
	</select>
	
	гр.
	<select name="group" class='groups-combo'>

	<% for(Group g : groups){ %>
	
		<option value="<%= g.getId() %>" <%= g.getId().equals(groupId) ? "selected" : ""  %> ><%= g.getName() %></option>
	
	<% } %>
	</select>
	
	<button>выбрать</button>
</form>



</div>
<table class='summary'>
	<tr>
		<td>студент</td>
		<% 
		BigDecimal ruffled = BigDecimal.ZERO;
		for(Control control : controls) { 
			ruffled = ruffled.add(control.getMaxPoint()); %>
			<td><div><a href='control.html?id=<%=control.getId() %>'><%= control.getName() %><a></div><div style="font-size: 16px;">(<%= control.getMaxPoint() %>)</div></td>
		<% } %>
		<td><div>сумма</div><div> (<%= ruffled %>) </div></td>
	</tr>
	<% 
		
	//Set<Student> set = new TreeSet<Student>(data.keySet());
	List<Student> list = new ArrayList<Student>(data.keySet());
	Collections.sort(list, new Comparator<Student>(){
		public int compare(Student o1, Student o2){
			return o1.getLastName().compareTo(o2.getLastName());
		}
	});
	
	for(Student student :list) { %>
	
	<tr>
		<td><a href="<%=Pages.STUDENT%>.html?id=<%=student.getId() %>"><%=salnikova.util.ModelUtil.shortName(student)%></a></td>
				 
				<% Map<Integer, Attestation> attestations = data.get(student); 
					for(Control control : controls){
						
						Attestation a = attestations.get(control.getId());
						
						%> 
							<td <%= a==null ? "style='background: #FFFF99';" : "" %>><%= a==null? "": a.getPoints().toString() %></td>
						
						<%
					}
				
				%>
			
				
				<% BigDecimal total = sDao.getTotalPoints(student.getId());  
					BigDecimal threshold = BigDecimal.valueOf(35.0);
				%>
			<td <%= threshold.compareTo(total) <= 0 ? "style='background: #CAFF7A'" : "style='background:#E49595'" %>><%=total  %></td>
			
			
	</tr>
	<% } %>
</table>
