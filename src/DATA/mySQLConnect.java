/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class mySQLConnect {
    private static Connection conn = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3307/doan";
    static final String USER = "root";
    static final String PASS = "";
    private Statement st = null;
    public void  getConnect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //JOptionPane.showMessageDialog(null, "Kết nối csdl thành công","Thông báo",1);
            //return conn;
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Kết nối csdl thất bại","Thông báo",2);
            //return null;
        }
    }
    public void disConnect(){
        try {
            st.close();
            conn.close();
        } catch (Exception e) {
        }
    }
    public ResultSet executeQuery(String sql)
    {
        ResultSet rs = null;
        try {
            getConnect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    public void executeUpdate(String sql)
    {
        try {
            getConnect();
            st = conn.createStatement();
            //JOptionPane.showMessageDialog(null, "Suscessful");
            
            st.executeUpdate(sql);
            //JOptionPane.showMessageDialog(null, "Suscessful");
            disConnect();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        getConnect();
        return conn;
    }
    public boolean isConnect()
    {
        return conn!=null?true:false;
    }
    public static void main(String[] args) {
        //mySQLConnect mysql = new mySQLConnect();
        //mysql.getConnection();
        //System.out.println(mysql.isConnect());
    }

    void Close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
