package com.shortthirdman.core.microsoft.access;

import java.sql.Connection;

import java.util.*;

import com.ms.jdbc.odbc.JdbcOdbcDriver;

public class ConnectAccessDB {

    private Connection  con = null;
    private ResultSet rs;
     
    public ConnectAccessDB(){}

    private void findEmployee() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.ms.jdbc.odbc.JdbcOdbcDriver");
            String url = "jdbc:odbc:JDBCdsn";
            con = DriverManager.getConnection(url, "", "");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT EmployeeID, LastName,  Title FROM Employees");
            while (rs.next()) {
                System.out.println(rs.getInt("EmployeeID") + " " + rs.getString("LastName") + " " + rs.getString("Title"));
            }
            if(con != null)
                con.close();
            con = null;
        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException clex) {
            throw clex;
        }
    }

     private void closeConnection(){
          try {
               if(con != null)
                    con.close();
               con = null;
          } catch(Exception ex) {
               ex.printStackTrace();
          }
     }
    
     public static void main(String[] args) throws Exception {
          Connect myDbTest = new Connect();
          try {
              myDbTest.FindEmployee();
          } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + ex.getMessage());
		  }      
}
}