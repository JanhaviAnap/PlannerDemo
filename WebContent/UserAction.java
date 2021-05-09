//package backendJava;

import java.sql.*;
import java.util.Random;

public class UserAction {

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

    public static int getUniqueIdFromDB(String email){
        int uniqueId = 0;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("select uniqueid from userdata where useremail=?;");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                uniqueId = rs.getInt("uniqueid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueId;
    }

    public static User getUserDataFromDB(int uniqueId){
        //select * from userdata where uniqueid=552266;
        User u = new User();
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from userdata where uniqueid=?;");
            ps.setInt(1, uniqueId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                u.uniqueId = rs.getInt("uniqueid");
                u.userName = rs.getString("username");
                u.password= rs.getString("userpassword");
                u.email = rs.getString("useremail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public static boolean checkIfEmailPresent(String email) {
        boolean status = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from userdata where useremail=?;");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean loginWithEmail(String inp_useremail, String password){
        boolean status = false;
        try{
            Connection conn = getConnection();
            //Statement st = null;
            //st = conn.createStatement();
            //String query = "select username,userpassword from userdata where username = '"+inp_username+"' and password = '"+password+"';";
            //ResultSet rs = st.executeQuery(query);
            
            PreparedStatement ps = conn.prepareStatement("select useremail,userpassword from userdata where useremail = ? and userpassword = ?");
            ps.setString(1, inp_useremail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                status = true;
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    
    public static boolean signUp(String userName, String password, String email){
        boolean status=false;
        try{
            int uniqueid = generateUniqueUserId();
            Connection conn = getConnection();
            if(checkIfEmailPresent(email)){
                return false;
            }
            PreparedStatement ps = conn.prepareStatement("insert into userdata values(?,?,?,?);");
            ps.setInt(1, uniqueid);
            ps.setString(2, userName);
            ps.setString(3, password);
            ps.setString(4, email);
            //ResultSet rs = ps.executeQuery();
            if(ps.executeUpdate()>0){
                status = true;
            }            
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public static int generateUniqueUserId(){
        /*while the random no generated is present in db
        The nextInt(n) method returns a random integer
         in the range 0 to n-1 inclusive. So the lowest possible 
         value of num is 0 + 1000000 = 1000000, and the highest 
         possible value is 9000000 - 1 + 1000000 = 9999999,
          as desired. */
        boolean status = false;
        int id = 0;
        try{
            do{
                Random rand = new Random();
                id = rand.nextInt(900000)+100000;
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement("select uniqueid from userdata where uniqueid=?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    status = true;
                }else{
                    break;
                }
            }while(status);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return id;
    }

}
