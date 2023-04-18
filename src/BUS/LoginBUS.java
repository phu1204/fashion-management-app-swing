/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.LoginDAO;
import DTO.LoginDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class LoginBUS {
    public  static  ArrayList<LoginDTO> Arr_login = new ArrayList();
    
    public void docdangnhap() throws Exception
    {
        LoginDAO data = new LoginDAO();
        if(Arr_login == null)
            Arr_login = new ArrayList<>();
        Arr_login = data.docdangnhap();
        //ddd
    }
    
    public void ThemTaiKhoan(LoginDTO lg){
        try{
            LoginDAO data = new LoginDAO();
            data.addTaikhoan(lg);
            Arr_login.add(lg);
        } catch (Exception e){
           JOptionPane.showMessageDialog(null,"Khong thanh cong");
        }
    }
    
    public boolean checkid(int ma){
        for(LoginDTO lg : Arr_login){
            if(lg.getId()==ma){
                return true;
            }
            
        }
        return false;
    }
    
    public boolean checkusername(String user){
        for(LoginDTO lg : Arr_login){
            if(lg.getUsername().equals(user)){
                return true;
            }
            
        }
        return false;
    }
}
