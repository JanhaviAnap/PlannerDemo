<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar"%>
<%
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
%>
<table cellspacing=0 style="background-color: #eeeeee;">
        <tbody>
            <tr> 
                <td style="text-align: center; background-color: #9c9cfc; color:white;padding:10px" colspan="7"><b><%= months[month]+' '+year %></b></td>
            </tr>
            <tr style="background-color: #dcdcdc; color: #6b646e;">
                <td style="padding:5px"><b>Sun</b></td>
                <td style="padding:5px"><b>Mon</b></td>
                <td style="padding:5px"><b>Tue</b></td>
                <td style="padding:5px"><b>Wed</b></td>
                <td style="padding:5px"><b>Thu</b></td>
                <td style="padding:5px"><b>Fri</b></td>
                <td style="padding:5px"><b>Sat</b></td>
            </tr>
<%!
public String startRow() {
	return "<tr bgcolor=\"#eeeeee\">";
}
public String createElement(int x, String color) {
	if(x==0) {
		return "<td></td>";
	} else {
		return "<td style='text-align: center; color:#777777;'><b>"+x+"</b></td>";
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