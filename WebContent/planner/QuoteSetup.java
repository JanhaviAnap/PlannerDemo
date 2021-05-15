package planner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class QuoteSetup {
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
    
    public static void readQuotes() {
    	try{  
    		File file=new File("src/quotelist");     
    		FileReader fr=new FileReader(file);   
    		BufferedReader br=new BufferedReader(fr);      
    		String line; 
    		Connection conn = getConnection();
            //if(rs.next()){
            	//color = rs.getString("color");
                //status = true;
            //}
	    	while((line=br.readLine())!=null){ 
	    		String[] quotemood = line.split("%");
		    	//insert into quotes values("It Feels Good To Be Lost In The Right Direction","calm");
	            PreparedStatement ps = conn.prepareStatement("Select * from quotes where quote=? and qtype=?;");
	            ps.setString(1, quotemood[0].trim());
	            ps.setString(2, quotemood[1].trim());
	            ResultSet rs = ps.executeQuery();
	            if(!rs.next()){
	            	System.out.println(quotemood[0].trim()+"\n"+quotemood[1].trim());
	            	PreparedStatement ps1 = conn.prepareStatement("insert into quotes values(?,?);");
		            ps1.setString(1, quotemood[0].trim());
		            ps1.setString(2, quotemood[1].trim());
		            ps1.executeUpdate();
	            }
	    	}  
	    	fr.close();    
    	}catch(Exception e){  
    		e.printStackTrace();  
    	} 
    }
    
    //public static void main(String[] args) {
    	//readQuotes();
    	//System.out.println(UserMoodAction.getQuote(552266,"2021-05-13"));
    //}
}
