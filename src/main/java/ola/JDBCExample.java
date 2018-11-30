package ola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver" ;
	   static final String DB_URL = "jdbc:sqlite:/var/lib/proxysql/proxysql.db";
			
	   

	   
	   //  Database credentials
	   static final String USER = "admin";
	   static final String PASS = "admin";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "select * from mysql_users";
	      ResultSet rs = stmt.executeQuery(sql);
	      /*****************************************/
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	   
	         String user = rs.getString("username");
	         String pass = rs.getString("password");

	         //Display values
	         System.out.print(", User: " + user);
	         System.out.println(", PAss: " + pass);
	      }
	      //STEP 6: Clean-up environment
	      /******************************/
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	}//end FirstExample
