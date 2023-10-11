package com.amdocs.nursery.dao;

import java.sql.*;
import java.sql.DriverManager;

public class plantConnection {
 Connection con;
	
	public Connection getConnection() {
		con=null;
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver"); //registration
			 con=DriverManager.getConnection("Jdbc:Oracle:thin:@LAPTOP-UE18S6SM:1521:XE","system","admin"); //connection
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return con;
	}
}
