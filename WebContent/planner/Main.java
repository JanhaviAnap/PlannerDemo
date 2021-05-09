package planner;

import java.util.Scanner;
//import front.UserAction;
import java.util.Vector;

public class Main {
  public static void testLoginWithEmail() {
      System.out.println("\nTesting login with email");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter email: ");
          String email = sc.next();
          System.out.println("Enter password");
          String password = sc.next();
          boolean status = UserAction.loginWithEmail(email, password);
          if(status){
              System.out.println("Login Success !");
          }else{
              System.out.println("Login Failed !");
          }
          
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testSignUp() {
      System.out.println("\nTesting SignUp");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter username: ");
          String username = sc.next();
          System.out.println("Enter password");
          String password = sc.next();
          System.out.println("Enter email");
          String email = sc.next();
          boolean status = UserAction.signUp(username, password,email);
          if(status){
              System.out.println("SignUp Success !");
          }else{
              System.out.println("SignUp Failed !");
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testGetUniqueIdFromDB(){
      System.out.println("Testing Get Unique id From DB");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter email");
          String email = sc.next();
          int id = UserAction.getUniqueIdFromDB(email);
          System.out.println("The unique id is: "+id);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testGetUserDataFromDB(){
      System.out.println("Testing get user data from db");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter uniqueId: ");
          int uniqueId = sc.nextInt();
          User u = UserAction.getUserDataFromDB(uniqueId);
          System.out.println("User username: "+u.userName);
          System.out.println("User password: "+u.password);
          System.out.println("User email: "+u.email);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

//--------------------------------------------------------------------------------------------------------------------------------------------------------
/*
  public static void testAddEvent() {
      System.out.println("\nTesting Add event");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter email: ");
          String email = sc.nextLine();
          System.out.println("Enter event Name");
          String eventname = sc.nextLine();
          System.out.println("Enter event Descrpition");
          String eventdesc = sc.nextLine();
          System.out.println("Enter event start time in format\"YYYY-MM-DD HH:MI:SS only \":");
          String eventstart = sc.nextLine();
          System.out.println("Enter event end time in format\"YYYY-MM-DD HH:MI:SS only \":");
          String eventend = sc.nextLine();
          boolean status = UserEventAction.addEvent(UserAction.getUniqueIdFromDB(email),eventname,eventdesc,eventstart,eventend);
          if(status){
              System.out.println("Event Added Successfully !");
          }else{
              System.out.println("Addition of Event Failed !");
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewEventFromYear() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter email whose schedule is to be seen: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewEventFromYear(UserAction.getUniqueIdFromDB(email), year);
          System.out.println("The following times are scheduled for "+email+" :");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewEventFromYearMonth() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter email whose schedule is to be seen: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          System.out.println("View for month: ");
          int month = sc.nextInt();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewEventFromYearMonth(UserAction.getUniqueIdFromDB(email), year,month);
          System.out.println("The following times are scheduled for "+email+" :");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewEventFromYearMonthDate() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter email whose schedule is to be seen: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          System.out.println("View for month: ");
          int month = sc.nextInt();
          System.out.println("View for date: ");
          int date = sc.nextInt();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewEventFromYearMonthDate(UserAction.getUniqueIdFromDB(email), year,month,date);
          System.out.println("The following times are scheduled for "+email+" :");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewTasksForToday() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter email whose schedule is to be seen: ");
          String email = sc.nextLine();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewTasksForToday(UserAction.getUniqueIdFromDB(email));
          System.out.println("The tasks for today are:");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewEventFromYearForSelf() {
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewEventFromYearForSelf(UserAction.getUniqueIdFromDB(email), year);
          System.out.println("The following events are scheduled for you:");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewEventFromYearMonthForSelf() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          System.out.println("View for month: ");
          int month = sc.nextInt();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewEventFromYearMonthForSelf(UserAction.getUniqueIdFromDB(email), year,month);
          System.out.println("The following events are scheduled for you:");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewEventFromYearMonthDateForSelf(){
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          System.out.println("View for month: ");
          int month = sc.nextInt();
          System.out.println("View for date: ");
          int date = sc.nextInt();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewEventFromYearMonthDateForSelf(UserAction.getUniqueIdFromDB(email), year,month,date);
          System.out.println("The following events are scheduled for you :");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testviewTasksForTodayForSelf() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          Vector<String> schedule = new Vector<String>();
          schedule = UserEventAction.viewTasksForToday(UserAction.getUniqueIdFromDB(email));
          System.out.println("The tasks for today are:");
          for (String line : schedule) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      } 
  }

  public static void testAddMood() {
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email:");
          String email = sc.nextLine();
          System.out.println("Enter the date(YYYY-MM-DD):");
          String date = sc.nextLine();
          System.out.println("Enter your mood: ");
          String moodname = sc.nextLine();
          System.out.println("Describe your day: ");
          String daydesc = sc.nextLine();
          boolean status = UserMoodAction.addMood(UserAction.getUniqueIdFromDB(email), date, moodname, UserMoodAction.getColor(moodname), daydesc);
          if(status){
              System.out.println("Mood added successfully!");
          }else{
              System.out.println("Mood not added!");
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testviewMoodFromYear() {
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          Vector<String> trend = new Vector<String>();
          trend = UserMoodAction.viewMoodFromYear(UserAction.getUniqueIdFromDB(email), year);
          System.out.println("The trend of your mood:");
          for (String line : trend) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewMoodFromYearMonth() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          System.out.println("View for month: ");
          int month = sc.nextInt();
          Vector<String> trend = new Vector<String>();
          trend = UserMoodAction.viewMoodFromYearMonth(UserAction.getUniqueIdFromDB(email), year,month);
          System.out.println("The trend of your mood:");
          for (String line : trend) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testViewMoodFromYearMonthDate(){
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          System.out.println("View for year: ");
          int year = sc.nextInt();
          System.out.println("View for month: ");
          int month = sc.nextInt();
          System.out.println("View for date: ");
          int date = sc.nextInt();
          Vector<String> trend = new Vector<String>();
          trend = UserMoodAction.viewMoodFromYearMonthDate(UserAction.getUniqueIdFromDB(email), year,month,date);
          System.out.println("The trend of your mood :");
          for (String line : trend) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void testviewMoodForToday() {
      System.out.println("\nTesting viewing ");
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter your email: ");
          String email = sc.nextLine();
          Vector<String> trend = new Vector<String>();
          trend = UserMoodAction.viewMoodForToday(UserAction.getUniqueIdFromDB(email));
          System.out.println("The mood for today is:");
          for (String line : trend) {
              System.out.println(line);
          }
      } catch (Exception e) {
          e.printStackTrace();
      } 
  }
  */

  public static void main(String[] args) throws Exception {
      try{
          // //testing methods from UserAction.java
          // testLoginWithEmail();
          // testSignUp();
          // testGetUniqueIdFromDB();
          // testGetUserDataFromDB();

          // //testing methods from UserEventAction.java
          // testAddEvent();
          // testViewEventFromYear();
          // testViewEventFromYearMonth();
          // testViewEventFromYearMonthDate();
          // testViewTasksForToday();
          // testViewEventFromYearForSelf();
          // testViewEventFromYearMonthForSelf();
          // testViewEventFromYearMonthDateForSelf();
          // testviewTasksForTodayForSelf();

          // //testing methods from UserMoodAction.java
          // testAddMood();
          // testviewMoodFromYear();
          // testViewMoodFromYearMonth();
          // testViewMoodFromYearMonthDate();
          // testviewMoodForToday();

      }catch(Exception e){
          e.printStackTrace();
      }
  }
}
