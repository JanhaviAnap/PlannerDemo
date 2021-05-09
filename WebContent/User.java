
public class User {
    int uniqueId;
    String userName;
    String password;
    String email;
    //Map<Integer, User> ul = new HashMap<Integer, User>(); 

    public User(){
        uniqueId = 0;
        userName = "";
        password = "";
        email = "";
    }

    public int getUserId(){
        return uniqueId;
    }
    public void setUserId(int uniqueId){
        this.uniqueId = uniqueId;
    }

    public String getUserName(){
        return userName;
    }
    public void setUsername(String userName){
        this.userName = userName;
    }

    public String getPasssword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //login
}
