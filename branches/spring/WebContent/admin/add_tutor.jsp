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
TutorsDao dao = ctx.getBean(TutorsDao.class);


List<String> validationMessages = new LinkedList<String>();


List<FormField> fields = new LinkedList<FormField>();
fields.add(new FormField("lastName","фамилия", 3, FormField.Type.TEXT,  true));
fields.add(new FormField( "firstName","имя",  1, FormField.Type.TEXT,  true));
fields.add(new FormField("middleName", "отчество",  2, FormField.Type.TEXT, false));
fields.add(new FormField( "login", "логин", 4, FormField.Type.TEXT, true));
fields.add(new FormField("password", "пароль", 5, FormField.Type.PASSWORD, true));
fields.add(new FormField("post", "должность", 6,FormField.Type.TEXT, false));
fields.add(new FormField("degree", "ученая степень", 7,FormField.Type.TEXT, false));
fields.add(new FormField("email", "email", 8,FormField.Type.TEXT, false));


Map<String, FormField> fieldMap = new HashMap<String ,FormField>();
for(FormField field : fields){
	fieldMap.put(field.getName(), field);
}

	String idStr = request.getParameter("id");
	Integer id = null;
	if(idStr==null){
		try{
			id = Integer.parseInt(idStr);
		}
		catch(NumberFormatException ex){
			//ignore
		}
	}
	
	String action = request.getParameter("action");
	if(action==null){
		action = "add";
	}
	
	if("submit".equals(action)){
		
		for(FormField field : fields){
			String param = request.getParameter(field.getName());
			if("".equals(param.trim()) &&  field.isRequired()){
				validationMessages.add("не задано обязательное поле \""+field.getDisplayName() +"\"");
			}
			field.setValue(param==null ? "" : param);
		}
		
		
		if(validationMessages.size()>0){
			action = "add";
		}
		else{
			String firstName = fieldMap.get("firstName").getValue();
			String lastName = fieldMap.get("lastName").getValue();
			String middleName = fieldMap.get("middleName").getValue();
			String login = fieldMap.get("login").getValue();
			String password = fieldMap.get("password").getValue();
			String post = fieldMap.get("post").getValue();
			String degree = fieldMap.get("degree").getValue();
			String email = fieldMap.get("email").getValue();
			
			Tutor tutor = dao.create(firstName, middleName,
					lastName, post,  degree,
					login, password, email);
			
			%> создан профиль <b><%= login %></b> <%
		}
		

		
		
	}
	if("add".equals(action)){%>
		
<form>
	<table>

		<% for(FormField field : fields){ %>
		
		<tr>
			<td><%= field.getDisplayName() %> <%= field.isRequired() ? wild() : "" %></td>
			<td><input type="<%= field.getType() %>" name="<%= field.getName() %>" value="<%= field.getValue() %>" /></td>
		</tr>			
		
		<% } %>
		

		
		
	</table>
	
	<div><%= wild() %> - обязательно для заполнения</div>

	<input type="submit">
	
	<input type="hidden" name='action' value='submit'/>
		
</form>

	<% 
	if(validationMessages.size()>0){
		%><ul> <%
		
	for(String msg : validationMessages) { %>
		<li style='color: #ff0000;'><%= msg %></li>	
	<%  }
	
		%></ul> <%
	} %>
	
	<%}

	


	
	
%>

<%! String wild(){
	return "<span style='color: #ff0000;'>*</span>";
	
}

%>
