<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="java.util.*"

%>

<%

	String error = request.getParameter("error");

%>


 

<form action="j_security_check">
<table>
	<tr>
		<td>логин</td>
		<td><input name="j_username"></td>
	</tr>
	
	<tr>
		<td>пароль</td>
		<td><input type="password" name="j_password"></td>
	</tr>

</table>

	<input type="submit"/>
</form>   

<%= error != null ? "<div style='color: red;'>ошибка авторизации</div>" : "" %>