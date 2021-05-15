package planner;


import java.sql.*;
import java.util.Calendar;
//import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class UserEventAction {
    public static boolean addEvent(int uniqueid,String eventname,String eventdesc,String eventstart, String eventend, String email) {
        boolean status = false;
        try{
            Connection conn = UserAction.getConnection();
            //String selfemail = UserAction.getEmailFromId(uniqueid);
            int elseid = UserAction.getUniqueIdFromDB(email);
            //add event to self schedule
            PreparedStatement ps = conn.prepareStatement("insert into eventlist values (?,?,?,?,?,?);");
            ps.setInt(1, uniqueid);
            ps.setString(2, eventname);
            ps.setString(3, eventdesc);
            ps.setString(4, eventstart);
            ps.setString(5, eventend);
            ps.setString(6, email);
            if(ps.executeUpdate()>0){
                status = true;
            }
            //add event invite to another user
            if(elseid!=uniqueid) {
            	ps = conn.prepareStatement("insert into eventlist values (?,?,?,?,?,?);");
                ps.setInt(1, elseid);
                ps.setString(2, eventname);
                ps.setString(3, eventdesc);
                ps.setString(4, eventstart);
                ps.setString(5, eventend);
                ps.setString(6, email);
                if(ps.executeUpdate()>0){
                    status = true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    
    public static Vector<String> viewEventFromYearForSelf(int uniqueid,int year) {
        Vector<String> schedule = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventstart,eventend,eventname,eventdesc,inviteby from eventlist where year(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setInt(1, year);
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	int invuid = UserAction.getUniqueIdFromDB(rs.getString("inviteby"));
            	if(invuid!=uniqueid) {
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Invited by: "+rs.getString("inviteby")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}else{
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }

    public static Vector<String> viewEventFromYearMonthForSelf(int uniqueid,int year,int month) {
        Vector<String> schedule = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventstart,eventend,eventname,eventdesc,inviteby from eventlist where year(eventstart)=? and month(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	int invuid = UserAction.getUniqueIdFromDB(rs.getString("inviteby"));
            	if(invuid!=uniqueid) {
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Invited by: "+rs.getString("inviteby")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}else{
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }

    public static Vector<String> viewEventFromYearMonthDateForSelf(int uniqueid,int year,int month, int date) {
        Vector<String> schedule = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventstart,eventend,eventname,eventdesc,inviteby from eventlist where date(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setString(1, year+"-"+month+"-"+date);
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	int invuid = UserAction.getUniqueIdFromDB(rs.getString("inviteby"));
            	if(invuid!=uniqueid) {
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Invited by: "+rs.getString("inviteby")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}else{
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }

    public static Vector<String> viewTasksForTodayForSelf(int uniqueid){
        Vector<String> schedule = new Vector<String>();
        try{
            LocalDate currentdate = LocalDate.now();
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventname,eventdesc,eventstart,eventend,inviteby from eventlist where date(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setString(1,currentdate.toString());
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	int invuid = UserAction.getUniqueIdFromDB(rs.getString("inviteby"));
            	if(invuid!=uniqueid) {
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Invited by: "+rs.getString("inviteby")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}else{
            		schedule.add("\n<b>"+rs.getString("eventname")+"</b> "+rs.getString("eventdesc")+"<br>Start: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            	}
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }

    public static Vector<String> viewEventFromYear(int uniqueid,int year) {
        Vector<String> schedule = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventstart,eventend from eventlist where year(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setInt(1, year);
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	schedule.add("\nStart: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }

    public static Vector<String> viewEventFromYearMonth(int uniqueid,int year,int month) {
        Vector<String> schedule = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventstart,eventend from eventlist where year(eventstart)=? and month(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	schedule.add("\nStart: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }

    public static Vector<String> viewEventFromYearMonthDate(int uniqueid,int year,int month, int date) {
        Vector<String> schedule = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventstart,eventend from eventlist where date(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setString(1, year+"-"+month+"-"+date);
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	schedule.add("\nStart: "+rs.getString("eventstart")+"<br>End: "+rs.getString("eventend")+"<br>");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }

    public static Vector<String> viewTasksForToday(int uniqueid){
        Vector<String> schedule = new Vector<String>();
        try{
            LocalDate currentdate = LocalDate.now();
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct eventname,eventdesc,eventstart,eventend from eventlist where date(eventstart)=? and uniqueid=? order by eventstart;");
            ps.setString(1,currentdate.toString());
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                schedule.add("\nevent name: "+rs.getString("eventname")+"\nEvent description: "+rs.getString("eventdesc")+"\nEvent timings"+rs.getString("eventstart")+"  "+rs.getString("eventend"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return schedule;
    }
    
    public static Vector<String> viewEvents(int selfuid,int uid,String date,String vtype){
    	Vector<String> schedule = new Vector<String>();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    	LocalDate d = LocalDate.now();
    	if(date!=null)
    		d = LocalDate.parse(date, formatter);
    	int day = d.getDayOfMonth();
    	int month = d.getMonthValue();
    	int year = d.getYear();
    	try {
    		if(vtype.equals("yearly")) {
    			if(selfuid==uid) {
    				schedule = viewEventFromYearForSelf(uid,year);
    			}else {
    				schedule = viewEventFromYear(uid,year);
    			}
    		}else if(vtype.equals("monthly")) {
    			if(selfuid==uid) {
    				schedule = viewEventFromYearMonthForSelf(uid,year,month);
    			}else {
    				schedule = viewEventFromYearMonth(uid,year,month);
    			}
    		}else{
    			if(selfuid==uid) {
    				schedule = viewEventFromYearMonthDateForSelf(uid,year,month,day);
    			}else {
    				schedule = viewEventFromYearMonthDate(uid,year,month,day);
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return schedule;
    }
    
    public static String getDateColor(int uniqueid,String date) {
        boolean status = false;
        String color = "#eeeeee";//#9c9cfc
        try {
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select distinct * from eventlist where uniqueid=? and date(eventstart)=?;");
            ps.setInt(1, uniqueid);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            	color = "#9c9cfc";
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
    }
    
    public static String[] getDateColorArray(int uniqueid) {
    	String[] color = new String[32];
    	for(int i=0; i<32; i++) {
    		color[i]="#eeeeee";
    	}
    	LocalDate currentdate = LocalDate.now();
        int currentDay = currentdate.getDayOfMonth();
        int currentMonth = currentdate.getMonthValue();
        int currentYear = currentdate.getYear();
        Calendar cal = Calendar.getInstance();
    	int first = cal.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH);
    	int last = cal.getActualMaximum(Calendar.DATE);
    	for(int i=first;i<=last;i++) {
    		String d = String.format("%04d",currentYear)+"-"+String.format("%02d",currentMonth)+"-"+String.format("%02d",i);
    		color[i]=getDateColor(uniqueid,d);  
    		//System.out.println(color[i]);  
    	}
    	return color;
    }
    
    //public static void main(String[] args) {
    	//String[] colors = getDateColorArray(552266);
   // }
}
