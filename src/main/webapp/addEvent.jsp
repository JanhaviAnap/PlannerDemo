<%@page import="planner.UserAction" %>
<%@page import="planner.UserEventAction" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="java.text.*" %>

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
	DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat time = new SimpleDateFormat("HH:mm:ss");
	DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String datenow = date.format(now);
	String timenow = time.format(now);
	String datetimenow = dateTime.format(now);
	Vector<String> viewSchedule = new Vector<String>();
	if(viewSchedule.size()>0){            	
		viewSchedule = UserEventAction.viewEvents(uid,inpUid,inpDate,inpType);
		for(String event: viewSchedule){
    		out.print("<br>");
    		out.println((String)event);
		}
	}else{
		out.println("You're all caught up!");
	}
%>
<form>
	<p>
	   	<label>Date:</label>
	   	<input type="date" name="vdate" value=<%=datenow %>>
	 </p>
</form>
<%
/*Vector<String> elseSchedule = new Vector<String>();
elseSchedule = UserEventAction.viewEventFromYear(552266,2021);
if(elseSchedule.size()>0){
	for(String event: elseSchedule){
		out.print("<br>");
		out.println((String)event);
	}
}else{
	out.println("No events available!!  Alooofja");
}*/
/*try{
LocalDate inpDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("vdate"));
//int day = Integer.parseInt(inpDate.substring(6));
//int month = Integer.parseInt(inpDate.substring(3,5));
//int year = Integer.parseInt(inpDate.substring(0,2));
int day = inpDate.getDayOfMonth();
int month = inpDate.getMonthValue();
int year = 1900 + inpDate.getYear();
out.println("prem@gmail.com");
out.println(552266);
out.println(day);
out.println(month);
out.println(year);
Vector<String> elseSchedule = new Vector<String>();
elseSchedule = UserEventAction.viewEventFromYear(552266,year);
if(elseSchedule.size()>0){
	for(String event: elseSchedule){
		out.print("<br>");
		out.println((String)event);
	}
}else{
	out.println("No events available!!  Alooofja");
}
}catch(Exception e){e.printStackTrace();}*/
%>
<%out.println("----------------------------------------------------------------------------------------"); %>
<%
            		String hello10 = request.getParameter("vtime");
            		out.println(hello10);
            	%>

<%
	/*
	DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat time = new SimpleDateFormat("HH:mm:ss");
	DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Date obj = new Date();
	
	LocalDate localDate = LocalDate.now();
	LocalTime localTime = LocalTime.now();
	LocalDateTime now = LocalDateTime.now();
	
	String datenow = date.format(obj);
	String timenow = time.format(obj);
	String dateTimeNow = dateTime.format(obj);
	
	out.println(datenow);
	out.println(timenow);
	out.println(dateTimeNow);*/
%>
	<%	out.println("Hello");
	String hello = "hellllooooo";
	out.println(hello);
            	//String email = (String)loginAction.getAttribute("email");
            	int uid = UserAction.getUniqueIdFromDB("prem@gmail.com");
            	//int uid = 552266;
            	out.println(uid);
            	Vector<String> schedule = new Vector<String>();
            	schedule = UserEventAction.viewEventFromYearForSelf(uid,2021);
            	for(String event: schedule){
            		out.print("<br>");
            		out.println((String)event);
            	}
            	//String schedule = UserEventAction.viewTasksForTodayForSelfString(uid);
            	//out.println(schedule);
            %>
            <br>
            <%
         /*   DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        	DateFormat time = new SimpleDateFormat("HH:mm:ss");
        	DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date now = new Date();
        	String datenow = date.format(now);
        	String timenow = time.format(now);
        	String dateTimeNow = dateTime.format(now);
        	out.println(datenow);
        	out.println(timenow);
        	out.println(dateTimeNow);
        */    %>
</body>
</html>