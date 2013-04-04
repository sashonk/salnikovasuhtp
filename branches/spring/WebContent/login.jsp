<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page

	import="salnikova.dao.*" 
	import="salnikova.model.*"
	import="java.util.*"

%>



<form name="f" action="/j_spring_security_check" method="POST">
<table cellspacing="5">
    <tr>
	<td>Логин</td>
	<td><input name="j_username" /></td>
    <tr>

    <tr>
	<td>Пароль</td>
	<td><input name="j_password" type="password" /></td>
    </tr>



</table>

    <div style='margin:5px;'><input type="checkbox" name="j_spring_remember_me" /> запомнить меня</div>
    <div style='margin:5px;'><input type="submit" /></div>
</form>

<% 
    String fail = request.getParameter("fail");

    if(fail!=null){
	%><div style='color: #ff0000;'>Неверное имя пользователя/пароль! </div>  <%
    }

%>