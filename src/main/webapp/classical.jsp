<%@page import="planner.UserAction" %>
<%@page import="planner.UserEventAction" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.time.*" %>
<%@ page import="java.text.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat time = new SimpleDateFormat("HH:mm:ss");
	DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String datenow = date.format(now);
	String timenow = time.format(now);
	String datetimenow = dateTime.format(now);
%>
<%
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vision Planner</title>
    <style>
        * {box-sizing: border-box;}
        ul {list-style-type: none;}
        body {font-family: Verdana, sans-serif;}
        
        
        .main {
            width:70%;
            float:right;
        }
        
        .month {
            padding: 70px 25px;
            width: 100%;
            background: #9d9dff;
            text-align: center;
        }
        
        .month ul {
            margin: 0;
            padding: 0;
        }
        
        .month ul li {
            color: white;
            font-size: 20px;
            text-transform: uppercase;
            letter-spacing: 3px;
        }
        
        .month .prev {
            float: left;
            padding-top: 10px;
        }
        
        .month .next {
            float: right;
            padding-top: 10px;
        }
        
        .weekdays {
            margin: 0;
            padding: 10px 0;
            background-color: #ddd;
        }
        
        .weekdays li {
            display: inline-block;
            width: 13.6%;
            color: #666;
            text-align: center;
        }
        
        .days {
            padding: 10px 0;
            background: #eee;
            margin: 0;
        }
        
        .days li {
            list-style-type: none;
            display: inline-block;
            width: 13.6%;
            text-align: center;
            margin-bottom: 5px;
            font-size:12px;
            color: #777;
        }
        
        .days li .active {
            padding: 5px;
            background: #1abc9c;
            color: white !important
        }
        
        /* Add media queries for smaller screens */
        @media screen and (max-width:720px) {
            .weekdays li, .days li {width: 13.1%;}
        }
        
        @media screen and (max-width: 420px) {
            .weekdays li, .days li {width: 12.5%;}
            .days li .active {padding: 2px;}
        }
        
        @media screen and (max-width: 290px) {
            .weekdays li, .days li {width: 12.2%;}
        }
        
        .navbar {
            overflow: hidden;
            background-color: #9d9dff;
        }
        
        /* Style the navigation bar links */
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 25px 20px;
            text-decoration: none;
        }
        
        /* Right-aligned link */
        .navbar a.right {
            float: right;
        }
        
        /* Change color on hover */
        .navbar a:hover {
            background-color: black;
            color: white;
        }
        .button5 {
            background-color: white;
            border: none;
            color: #9d9dff;
            padding: 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin: 10px 10px;
            border-radius: 50%;
            /*float: right;*/
        }
        
        .button:hover {
          background-color: black;
          color:white;
          /*pointer:cursor;*/
        }
        .row{display: flex;}
        .col-1{
            flex: 45%;
        }
        .col-2{
            flex: 55%;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="homepage.html" style="padding: 12px; margin: 0; border: 0;"><img src="assets/img/VisionPlannerLogo.jpeg" alt="logo" style="height: 45px; width: 45px;"></a>
        <a href="classical.jsp">Classical</a>
        <a href="financial.html">Financial</a>
        <a href="moodtracker.jsp">MoodTracker</a>
        <button class="button button5" style="float: right;">P</button>
    </div>
    <h2>12 MARCH</h2>
    <h5>Tuesday, 2021</h5>
    <br>
    <%
    String email = (String)session.getAttribute("uemail");
    int uid = UserAction.getUniqueIdFromDB(email);
    //int uid=552266;
    %>
    <div class="row">
        <div class="col-1">
            <h3>Today's Tasks</h3> 
            <%	
            //out.println("Hello");
			//String hello = "hellllooooo";
			//out.println(hello);
            	//String email = (String)loginAction.getAttribute("uemail");
            	//int uid = UserAction.getUniqueIdFromDB("prem@gmail.com");
            	//int uid = 552266;
            	//out.println(uid);
            	//out.println("Event start           Event End  Event     Desc");
            	Vector<String> schedule = new Vector<String>();
            	schedule = UserEventAction.viewTasksForTodayForSelf(uid);
            	if(schedule.size()>0){            	
            		for(String event: schedule){
                		out.print("<br>");
                		out.println((String)event);
            		}
            	}else{
            		out.println("You're all caught up!");
            	}
            	//String schedule = UserEventAction.viewTasksForTodayForSelfString(uid);
            	//out.println(schedule);
            %>
            <br>
            <br>
            <br>
            <br>
            <h2>Add Events</h2>
            <form>
            	<p>
	                <label>Add For:</label>
	                <input type=text name="aemail" value=<%=email %> required>
	            </p>
	            <p>
	                <label>Event Name:</label>
	                <input type=text name="event" placeholder="Event Name" required>
	            </p>
	            <p>
	                <textarea name="desc" placeholder="Enter Event Description here..."></textarea>
	            </p>
	            <p>
	            	<label>Start Date:</label>
	            	<input type="date" name="asdate" value=<%=datenow %>>
	            	<br>
	            	<label>Start Time:</label>
	            	<input type="time" name="astime" value=<%=timenow %>>
	            </p>
	            <p>
	            	<label>End Date:</label>
	            	<input type="date" name="aedate" value=<%=datenow %>>
	            	<br>
	            	<label>End Time:</label>
	            	<input type="time" name="aetime" value=<%=timenow %>>
	            </p>
	            
	            <input type="submit" value="SUBMIT">
            </form>
            <%
            try{	            
            	String inpEmail = request.getParameter("aemail");
            	int inpUid = UserAction.getUniqueIdFromDB(inpEmail);
            	
            	String eventname = request.getParameter("event");
            	String eventdesc = request.getParameter("desc");
            	
            	String inpStartDate = request.getParameter("asdate");
            	String inpStartTime = request.getParameter("astime");
            	String inpStart = inpStartDate + " " +inpStartTime;
            	
            	String inpEndDate = request.getParameter("aedate");
            	String inpEndTime = request.getParameter("aetime");
            	String inpEnd = inpEndDate + " " +inpEndTime;
            	
            	boolean status = UserEventAction.addEvent(inpUid, eventname, eventdesc, inpStart, inpEnd, email);
            	if(status==true){
            		out.println("Event Added Successfully!");
            	}else{
            		out.println("Try Agin!");
            	}
            	
            }catch(Exception e){
            	e.printStackTrace();
            }
            	
            %>
            <br>
            <br>
            <h2>View Events</h2>
            <form>
            	<p>
	                <label>View For:</label>
	                <input type=text name="vemail" value=<%=email %>>
	            </p>
	            <p>
	            	<label>Date:</label>
	            	<input type="date" name="vdate" value=<%=datenow %>>
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
            try{	
            	email = (String)session.getAttribute("uemail");
            	uid = UserAction.getUniqueIdFromDB(email);
            
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
            }catch(Exception  e){
            	e.printStackTrace();
            }
            	
            %>
            
        </div>
        <div class="col-2">
            <div class="col-2">
                <table cellspacing=0 style="background-color: #eeeeee; width:100%;">
			        <tbody>
			            <tr> 
			                <td style="text-align: center; background-color: #9c9cfc; color:white;padding:30px" colspan="7"><b><%= months[month]+' '+year %></b></td>
			            </tr>
			            <tr style="background-color: #dcdcdc; color: #6b646e; padding:10px;">
			                <td style="text-align: center; padding:15px"><b>Sun</b></td>
			                <td style="text-align: center; padding:15px"><b>Mon</b></td>
			                <td style="text-align: center; padding:15px"><b>Tue</b></td>
			                <td style="text-align: center; padding:15px"><b>Wed</b></td>
			                <td style="text-align: center; padding:15px"><b>Thu</b></td>
			                <td style="text-align: center; padding:15px"><b>Fri</b></td>
			                <td style="text-align: center; padding:15px"><b>Sat</b></td>
			            </tr>
			<%!
			public String startRow() {
				return "<tr bgcolor=\"#eeeeee\">";
			}
			public String createElement(int x, String color) {
				if(x==0) {
					return "<td style='padding:10px;'></td>";
				} else {
					return "<td style='text-align: center; color:#777777;padding:10px;'><b>"+x+"</b></td>";
			}
			}
			public String createElement(int x,String color, String bg) {
				return "<td align=\"center\" bgcolor="+bg+" ><font color=\""+color+"\">"+x+"</font></td>";
			}
			public String endRow() {
				return "</tr>";
			}
			%>
			<%
			
			int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
			int start = day_of_month;
			if(day_of_month> 1)
				cal.set(Calendar.DAY_OF_MONTH,1);
			out.println(startRow());
			int dow = cal.get(Calendar.DAY_OF_WEEK);
			int i=1;
			//creating blank elements
			for(i=1;i<dow;i++) {
				out.println(createElement(0,"#777777"));
			}
			while(cal.get(Calendar.MONTH) == month) {
				day_of_month = cal.get(Calendar.DAY_OF_MONTH);
				dow = cal.get(Calendar.DAY_OF_WEEK);
				//creating row on sunday
				if((dow == 1) && (day_of_month !=1))
					out.println(startRow());
				//highlight today
				if(day_of_month==start)
					out.println(createElement(day_of_month,"white","#1cbc9c"));
				else
					out.println(createElement(day_of_month,"#777777"));
				//end row on saturday
				if(dow == 7)
					out.println(endRow());
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			dow = cal.get(Calendar.DAY_OF_WEEK);
			if(dow==1) {
				out.println(endRow());
			} else {
				for(i=dow;i<=7;i++) {
					out.println(createElement(0,"#777777"));
				}
				out.println(endRow());
			}
			%>
				</tbody>
			</table>
            </div>
        </div>
    </div> 
    
</body>
</html>