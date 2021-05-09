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
    String email = (String)session.getAttribute("uemail");
    int uid = UserAction.getUniqueIdFromDB(email);
    //int uid=552266;
    DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat time = new SimpleDateFormat("HH:mm:ss");
	DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String datenow = date.format(now);
	String timenow = time.format(now);
	String datetimenow = dateTime.format(now);
	email = "alex@gmail.com";
	uid = UserAction.getUniqueIdFromDB(email);
    %>
<form>
            	<p>
	                <label>View For:</label>
	                <select name="vfor">
	            		<option value="self">Self</option>
	            		<option value="else">Else</option>
	            	</select>
	                <input type=text name="vemail" value=<%=email %>>
	            </p>
	            <p>
	            	<label>Date:</label>
	            	<input type="date" name="vdate" value=<%=datenow %>>
	            </p>
	            <p>
	            	<label>Time:</label>
	            	<input type="time" name="vtime" value=<%=timenow %>>
	            </p>
	            <p>
	            	<label>View:</label>
	            	<select name="vtype">
	            		<option value="daily">Daily</option>
	            		<option value="monthly">Monthly</option>
	            		<option value="yearly">Yearly</option>
	            	</select>
	            </p>
	            <input type="submit" value="SUBMIT">
            </form>
            
<%	 
				//email = (String)session.getAttribute("uemail");
				
            
            	String inpEmail = request.getParameter("vemail");
            	int inpUid = UserAction.getUniqueIdFromDB(inpEmail);
            	
            	String inpDate = request.getParameter("vdate");
            	
            	String inpType = request.getParameter("vtype");
            	Vector<String> viewSchedule = new Vector<String>();
            	
            	viewSchedule = UserEventAction.viewEvents(uid,inpUid,inpDate,inpType);
            	if(viewSchedule.size()>0){
            		for(String event: viewSchedule){
                		out.print("<br>");
                		out.println((String)event);
                	}
            	}else{
            		out.println("You're all caught up!");
            	}
        		
%>

</body>
</html>