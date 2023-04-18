/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DATA.mySQLConnect;
import DTO.LoginDTO;
import static GUI.Login1.st;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PHU
 */
public class LoginDAO {
    private  mySQLConnect mySQL = new mySQLConnect();
    public ArrayList<LoginDTO> docdangnhap() throws Exception{
        //connect
        ArrayList<LoginDTO> Arr_login = new ArrayList();
        String sql = "SELECT * From `dangnhap`";
        ResultSet rs = mySQL.executeQuery(sql);
        try {
                    while (rs.next()) {
                LoginDTO Login = new LoginDTO();
                Login.setUsername(rs.getString("username"));
                Login.setPassword(rs.getString("password"));
                Login.setId(Integer.parseInt(rs.getString("ID_Nhanvien")));
                Login.setType(rs.getInt("Type"));
                Arr_login.add(Login);     
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_login;
    }

    public void updateTaikhoan(String taikhoan, String matkhau, String id) {
        try{           

                LoginDTO acc = new LoginDTO();           
                String sql = "UPDATE `dangnhap` "
                        + "SET `username`= \"" + taikhoan + "\", "
                        + "`password` = \"" + matkhau + "\" "
                        + "WHERE `ID_Nhanvien`= \"" + id + "\" ";
                st.executeUpdate(sql);
                mySQL.executeUpdate(sql);
            }
            catch (Exception e){ 
            }
    }
    
    public void addTaikhoan(LoginDTO lg) {
        try {
                LoginDTO acc = new LoginDTO();
                String sql = "INSERT INTO `dangnhap` (`username`, `password`, `ID_Nhanvien`, `Type`) VALUES (";
//                        + "\"" + taikhoan + "\""
//                        + ",\"" + matkhau + "\""
//                        + ",\"" + id + "\""
//                        + ",'" + type + "')";
                       sql = sql+"'"+lg.username+"'";
                       sql = sql+","+"'"+lg.password+"'";
                       sql = sql+","+"'"+lg.id+"'";
                       sql = sql+","+"'"+lg.type+"'";
                       sql = sql+")";
//                st.executeUpdate(sql);
                mySQL.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTaikhoan(String taikhoan, String matkhau, String id, String type) {
        try {
                LoginDTO acc = new LoginDTO();                
                String sql = "DELETE FROM `dangnhap` WHERE "
                        + "`username` = \"" + taikhoan + "\"";
                st.executeUpdate(sql);
                mySQL.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}


