<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="java.util.*"
	import="java.math.*"	

%>

<%
	Integer depId = null;
	String dep = request.getParameter("department");
	if(dep!=null){
		try{
			depId = Integer.parseInt(dep);
		}
		catch(NumberFormatException ex){
	
		}
	}


	Integer groupId = null;
	String group = request.getParameter("group");
	if(group!=null){
		try{
			groupId = Integer.parseInt(group);
		}
		catch(NumberFormatException ex){
	
		}
	}	
	String surname = request.getParameter("secondname");
	
	SummaryDao dao = SummaryDao.get();
	Map<Student, Map<Integer, Attestation>> data = dao.getAttestationData(groupId);
	 
	ControlDao ctrlDao = ControlDao.get();
	List<Control> controls = ctrlDao.getControlList();
	
	Collections.sort(controls, new Comparator<Control>() {

		@Override
		public int compare(Control o1, Control o2) {

			return o1.getNumber().compareTo(o2.getNumber());
		}
	});
	
	
	GroupsDao gdao = GroupsDao.get();
	List<Group> groups = gdao.getGroups(null);
	
	
	StudentsDao sDao = StudentsDao.get();
%>

<div align="center">



<form>
	<select name="group">
		<option value="-" <%= groupId==null ? "selected" : ""  %> >-</option>

	<% for(Group g : groups){ %>
	
		<option value="<%= g.getId() %>" <%= g.getId().equals(groupId) ? "selected" : ""  %> ><%= g.getName() %></option>
	
	<% } %>
	</select>
	
	<button>выбрать</button>
</form>



</div>
<table>
	<tr>
		<td>студент</td>
		<% for(Control control : controls) { %>
			<td><div><%= control.getName() %></div><div style="font-size: 16px;">(<%= control.getMaxPoint() %>)</div></td>
		<% } %>
		<td>сумма</td>
	</tr>
	<% for(Student student : data.keySet()) { %>
	
	<tr>
		<td><a href="?page=student&id=<%=student.getId() %>"><%=student.getSecondName()%></a></td>
				 
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
			<td <%= threshold.compareTo(total) < 0 ? "style='background: #CAFF7A'" : "style='background:#E49595'" %>><%=total  %></td>
			
			
	</tr>
	<% } %>
</table>
