package com.ecommerce.pos.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
 
   private static final String USERNAME = "postgres";
 
   private static final String PASSWORD = "docker";
 
   private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/pos_ecommerce";
   
   public static Connection createConnection() throws Exception{
      Class.forName("org.postgresql.Driver");
      Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
      return connection;
   }
   
}