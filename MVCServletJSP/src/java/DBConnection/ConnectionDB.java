/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL E7450
 */
public class ConnectionDB {
     private String driver="com.mysql.jdbc.Driver";
     private String jdbcURL= "jdbc:mysql://localhost/BookStore";
    private String jdbcUsername="root";
    private String jdbcPassword="root"; 
    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;
    public ConnectionDB() {
    connect();
    }

    private void connect()    {
         try {
            Class.forName(driver);// buoc1
              conn = DriverManager.getConnection(jdbcURL,"root","root");// buoc 2
        } 
        catch(java.lang.ClassNotFoundException e) {          
            System.out.println(e.getMessage());
        }
         catch (SQLException e1) {
             System.out.println(e1.getMessage());
         }
    }
    protected void closeConn() {
        if (conn!=null) {
             
            try {
                conn.close();
            } catch (SQLException ex) {
                 System.out.println(ex.getMessage());
            }
             
        }
        if (ps!=null) {
             
            try {
                ps.close();
            } catch (SQLException ex) {
                 System.out.println(ex.getMessage());
            }
             
        } 
    }
    
    }
