package com.shortthirdman.core.database.oracle;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
 
public class OutParamCallableStmt {
 
    public static void main(String arg[]) {
        Connection con = null;
        CallableStatement callSt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>","user","password");
            callSt = con.prepareCall("{CALL myprocedure(?,?)}");
            callSt.setInt(1, 200);
            callSt.registerOutParameter(2, Types.DOUBLE);
            callSt.execute();
            Double output = callSt.getDouble(2);
            System.out.println("The output returned from stored procedure: " + output);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if(callSt != null) callSt.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}