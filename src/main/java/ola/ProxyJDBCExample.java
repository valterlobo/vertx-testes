package ola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProxyJDBCExample {
	
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
			   //"com.mysql.cj.jdbc.Driver"; 
	   //jdbc:mariadb://{host}[:{port}]/[{database}] 
	   static final String DB_URL = "jdbc:mariadb://67.205.189.154:6033/bd_teste?usePipelineAuth=false";
			   //"jdbc:mysql://127.0.0.1:6032";
	   

	   
	   //  Database credentials
	   
	   static final String USER = "root";
	   static final String PASS = "root";
	   
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
	      sql = "select * from Email";
	      ResultSet rs = stmt.executeQuery(sql);
	      /*****************************************/
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("ID");
	         String nome = rs.getString("Nome");
	        
	         System.out.print("ID: " + id);
	         System.out.print(", Nome: " + nome);
	         System.out.print("\n");
	    
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

}
