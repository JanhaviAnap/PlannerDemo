package planner;

import java.sql.*;

public class Setup {
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
    public static void createAllTables() {
        try {
            Connection conn = getConnection();
            String[] query = new String[20];
            query[0]="create table userdata (uniqueid int, username varchar(20), userpassword varchar(20), useremail varchar(30));";
            query[1]="create table eventlist (uniqueid int, eventname varchar(120), eventdesc varchar(400), eventstart datetime, eventend datetime);";
            query[2]="create table moodlist( uniqueid int, mooddate date not null, mood varchar(20), color varchar(7), daydesc varchar(400));";
            query[4]="create table quotes(quote varchar(500), qtype varchar(500));";
            for(int i=0; i<4; i++){
                PreparedStatement ps = conn.prepareStatement(query[i]);
                if(ps.executeUpdate()>0){
                    continue;
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

