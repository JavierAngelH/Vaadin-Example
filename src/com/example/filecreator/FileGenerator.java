package com.example.filecreator;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sun.rowset.WebRowSetImpl;

public class FileGenerator {
	
 public FileGenerator(){
	 
 }
	   public String generateFile(String tableName) throws Exception
	    {
		   String path = "";
	   Class.forName("com.mysql.jdbc.Driver"); // The driver used to connect to the database /
	  
	     Connection conn = DriverManager.getConnection
	       ("jdbc:mysql://localhost:3306/attendant_db","root","1234");
	    // You should change this to point to your database suitably. Otherwise, this example will not work /
	    try {
	      Statement stmt = conn.createStatement();
	      try {
	        ResultSet query_set = stmt.executeQuery("select * from " + tableName); 
	        
	        //  Pull out all the data from departments table through a database query /
	        try {
	          WebRowSetImpl my_xml_data = new WebRowSetImpl();
	          my_xml_data.populate(query_set);
	          
	          FileWriter fw = null;

	          File file = new File(tableName+".html");
	          fw = new FileWriter(file);
	          
	          
	          
	          path = file.getAbsolutePath();
	          
	          my_xml_data.writeXml(fw);
	          fw.flush();
	           fw.close();
	         
	          // Prints the output of the query to the screen as XML /
	        } 
	        finally {
	           try { query_set.close(); 
	           } catch (Exception ignore) {}
	        }
	      }       finally {
	        try { stmt.close(); } catch (Exception ignore) {}
	      }
	      
	    } 
	    finally {
	      try { conn.close(); 
	     
	      } catch (Exception ignore) {}
	      
	} 
	      
	    return path;
	      }
	    }