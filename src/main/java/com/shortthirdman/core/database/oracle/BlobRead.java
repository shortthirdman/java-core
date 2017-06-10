package com.shortthirdman.core.database.oracle;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class BlobRead {
    public static void main(String arg[]){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>","user","password");
            st = con.createStatement();
            rs = st.executeQuery("select student_img from student_profile where id=101");
            if(rs.next()) {
                is = rs.getBinaryStream(1);
            }
            is = new FileInputStream(new File("Student_img.jpg"));
            os = new FileOutputStream("std_img.jpg");
            byte[] content = new byte[1024];
            int size = 0;
            while((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null) is.close();
                if(os != null) os.close();
                if(st != null) st.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}