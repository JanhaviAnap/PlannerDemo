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
	String username=request.getParameter("username");
	String password=request.getParameter("pswd");
	
	boolean status=UserAction.signUp(username,password,email);
	if(status==true){
		//response.redirect("login.html");
		//out.println("success!");
		String site = new String("ty.html");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
	}else{
		//response.redirect("ty.html");
		//out.println("try again!");
		String site = new String("signup.html");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
	}
	
%>
</body>
</html>