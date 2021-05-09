<%@ page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String bgcolor = "#9d9dff";
	String tablecolor = "#dddddd";
	String fontcolor = "#666666";
	//String tableborder = "#ffff00";
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
%>
<script type="text/javascript">
	function ctck()
	{
		var sds = document.getElementById("dum");
		if(sds == null){
			alert("You are using a free package.\n You are not allowed to remove the tag.\n");
		}
		var sdss = document.getElementById("dumdiv");
		if(sdss == null)
		{
			alert("You are using a free package.\n You are not allowed to remove the tag.\n");
		}
	}
	document.onload="ctck()";
</script>
<table style='border: 2px solid rgb(0, 0, 0);color:446655;' bgcolor='<%= bgcolor%>' cellspacing=1 height=150 width=250>
<tr width="250" height="30">
	<td style='border-bottom: 0px solid rgb(255, 255, 255);' align=center width=245><font color='white'><b><%= months[month]+' '+year %></b></font></td>
	<td style='border-bottom: 0px solid rgb(255, 255, 255);' align=center width=5></td>
</tr>

<tr width=250 height=100>
<td colspan=2 height=100 width=250>
	<table color=446655 bgcolor=dddddd border=0 cellpadding=0 cellspacing=1 height=100 width=250>
		<tr align=center bgcolor="#505050" bordercolor="yellow">
			<td><font size=-1><b>Sun</b></font></td>
			<td><font size=-1><b>Mon</b></font></td>
			<td><font size=-1><b>Tue</b></font></td>
			<td><font size=-1><b>Wed</b></font></td>
			<td><font size=-1><b>Thu</b></font></td>
			<td><font size=-1><b>Fri</b></font></td>
			<td><font size=-1><b>Sat</b></font></td>
		</tr>
<%!
public String startRow(String color) {
	return "<tr bgcolor=\""+color+"\">";
}
public String createElement(int x, String color) {
	if(x==0) {
		return "<td></td>";
	} else {
		return "<td align=\"center\"><font color=\""+color+"\" size=\"-1\"><b>"+x+"</b></font></td>";
	}
}
public String createElement(int x,String color, int bg) {
	return "<td align=\"center\" bgcolor=\"#ff0000\" ><font color=\""+color+"\">"+x+"</font></td>";
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
out.println(startRow(tablecolor));
int dow = cal.get(Calendar.DAY_OF_WEEK);
int i=1;
for(;i<dow;i++) {
	out.println(createElement(0,fontcolor));
}
while(cal.get(Calendar.MONTH) == month) {
	day_of_month = cal.get(Calendar.DAY_OF_MONTH);
	dow = cal.get(Calendar.DAY_OF_WEEK);
	if((dow == 1) && (day_of_month !=1))
		out.println(startRow(tablecolor));
	if(day_of_month==start)
		out.println(createElement(day_of_month,fontcolor,1));
	else
		out.println(createElement(day_of_month,fontcolor));
	if(dow == 7)
		out.println(endRow());
	cal.add(Calendar.DAY_OF_MONTH, 1);
}
dow = cal.get(Calendar.DAY_OF_WEEK);
if(dow==1) {
	out.println(endRow());
} else {
	for(i=dow;i<=7;i++) {
		out.println(createElement(0,fontcolor));
	}
	out.println(endRow());
}
%>
	</table>
</td>
</tr>
</table>
