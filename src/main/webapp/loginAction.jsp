<%@page import="planner.UserAction" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String email=request.getParameter("email");
	String password=request.getParameter("pswd");
	session.setAttribute("uemail",email);
	
	boolean status=UserAction.loginWithEmail(email,password);
	if(status==true){
		String site = new String("classical.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
	}else{
		String site = new String("login.html");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
	}
%>
</body>
</html>