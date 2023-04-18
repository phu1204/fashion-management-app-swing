/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class myconnect {
     String user="root";
    String password="";
    String url="jdbc:mysql://localhost:3307/doan";
   public  Connection conn=null;
    public Statement st=null;
    public ResultSet rs=null;
    public myconnect(){}
    public void connectdb(){
    try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            System.out.println("Khong the ket noi database");
            System.out.println(e);
        }
    }
    
}
