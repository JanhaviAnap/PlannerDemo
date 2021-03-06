package planner;

import java.io.*;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class UserMoodAction {
	static final String dburl = "jdbc:mysql://localhost:3306/visionplanner";
    static final String jdbcdriver = "com.mysql.cj.jdbc.Driver";
    static final String username = "root";
    static final String password = "Abcd@1234";

    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl,username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean checkIfDatePresent(int uniqueid,String date) {
        boolean status = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from moodlist where mooddate=? and uniqueid=?;");
            ps.setString(1, date);
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getColor(String moodname) {
        HashMap<String, String> moodMap = new HashMap<String, String>();
        moodMap = Mood.getMoodMap();
        return moodMap.get(moodname);
    }

    public static boolean addMood (int uniqueid,String date, String mood, String color, String daydesc){
        boolean status = false;
        try {
            Connection conn = UserAction.getConnection();
            if(checkIfDatePresent(uniqueid,date)){
	            PreparedStatement ps = conn.prepareStatement("update moodlist set mood=?,color=?,daydesc=? where uniqueid=? and mooddate=?;");
	            ps.setString(1, mood);
	            ps.setString(2, color);
	            ps.setString(3, daydesc);
	            ps.setInt(4, uniqueid);
	            ps.setString(5, date);
	            if(ps.executeUpdate()>0){
	                status = true;
	            }
            }else {
            
                PreparedStatement ps = conn.prepareStatement("insert into moodlist values(?,?,?,?,?);");
                ps.setInt(1, uniqueid);
                ps.setString(2, date);
                ps.setString(3, mood);
                ps.setString(4, color);
                ps.setString(5, daydesc);
                if(ps.executeUpdate()>0){
                    status = true;
                }
            }
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return status;
    }
    

    public static Vector<String> viewMoodFromYear(int uniqueid,int year) {
        Vector<String> trend = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select mooddate,mood,daydesc from moodlist where year(mooddate)=? and uniqueid=? order by mooddate;");
            ps.setInt(1, year);
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                trend.add(rs.getString("mooddate")+"  "+rs.getString("mood")+" - "+rs.getString("daydesc"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return trend;
    }

    public static Vector<String> viewMoodFromYearMonth(int uniqueid,int year,int month) {
        Vector<String> trend = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select mooddate,mood,daydesc from moodlist where year(mooddate)=? and month(mooddate)=? and uniqueid=? order by mooddate;");
            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                trend.add(rs.getString("mooddate")+"  "+rs.getString("mood")+" - "+rs.getString("daydesc"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return trend;
    }

    public static Vector<String> viewMoodFromYearMonthDate(int uniqueid,int year,int month, int date) {
        Vector<String> trend = new Vector<String>();
        try{
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select mooddate,mood,daydesc from moodlist where date(mooddate)=? and uniqueid=? order by mooddate;");
            ps.setString(1, year+"-"+month+"-"+date);
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                trend.add(rs.getString("mooddate")+"  "+rs.getString("mood")+" - "+rs.getString("daydesc"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return trend;
    }

    public static Vector<String> viewMoodForToday(int uniqueid){
        Vector<String> trend = new Vector<String>();
        try{
            LocalDate currentdate = LocalDate.now();
            Connection conn = UserAction.getConnection();
            PreparedStatement ps = conn.prepareStatement("select mooddate,mood,daydesc from moodlist where date(mooddate)=? and uniqueid=? order by mooddate;");
            ps.setString(1,currentdate.toString());
            ps.setInt(2, uniqueid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                trend.add(rs.getString("mooddate")+"  "+rs.getString("mood")+" - "+rs.getString("daydesc"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return trend;
    }
    
    public static String getDateColor(int uniqueid,String date) {
        boolean status = false;
        String color = "#eeeeee";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from moodlist where uniqueid=? and mooddate=?;");
            ps.setInt(1, uniqueid);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            	color = rs.getString("color");
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
    		//System.out.println(color[i]+" "+d);  
    	}
        //String d = String.format("%04d",currentYear)+"-"+String.format("%02d",currentMonth)+"-"+String.format("%02d",currentDay);
        //System.out.println(d+" "+first+" "+last);
        //color[currentDay]="#1cbc9c";
        return color;
    }
    
    public static String getQuote(int uniqueid,String date) {
    	String quote = "";
    	try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("select mood from moodlist where mooddate=?;");
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            	String mood = rs.getString("mood");
            	PreparedStatement ps1 = conn.prepareStatement("select quote from quotes where qtype=? order by rand() limit 1;");
            	ps1.setString(1, mood);
            	ResultSet rs1 = ps1.executeQuery();
            	if(rs1.next()) {
            		quote = rs1.getString("quote");
            	}
            }else {
            	PreparedStatement ps1 = conn.prepareStatement("select quote from quotes order by rand() limit 1;");
            	ResultSet rs1 = ps1.executeQuery();
            	if(rs1.next()) {
            		quote = rs1.getString("quote");
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return quote;
    }
    
//    public static void main(String[] args) {
//    	//String[] colors = getDateColorArray(552266);
//    	int uniqueid = 545298006;
//    	String date = "2021-05-16";
//    	String mood = "happy";
//    	String color = "#F9A7B0";
//    	String daydesc = "nice";
//    	boolean status = addMood(uniqueid, date, mood, color, daydesc);
//    	if(status) {
//    		System.out.println("done");
//    	}else{
//    		System.out.println("not done");
//    	}
//    }
}
