/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DTO.chitiethoadonDTO;
import DATA.chitiethoadonDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class chitiethoadonBUS {
    ArrayList<chitiethoadonDTO> dshd=new ArrayList<chitiethoadonDTO>();
    public chitiethoadonBUS(){}
    public void docbus(String a) throws SQLException{
        chitiethoadonDAO data=new chitiethoadonDAO();
        if(dshd!=null) dshd=new ArrayList<chitiethoadonDTO>();
        dshd=data.docct(a);
    }
    public void them(chitiethoadonDTO hd) throws SQLException{
        chitiethoadonDAO data=new chitiethoadonDAO();
        data.them(hd);
        dshd.add(hd);
    }
   public void xoa(int t){
        chitiethoadonDAO data=new chitiethoadonDAO();
        chitiethoadonDTO hd=new chitiethoadonDTO();
        hd=dshd.get(t);
        data.xoa(hd.getMahd(),hd.getMasp(),hd.getMactsp());
        dshd.remove(t);
    }
   public void sua(chitiethoadonDTO hd,int i){
        chitiethoadonDAO data=new chitiethoadonDAO();
        data.sua(hd);
        dshd.set(i,hd);
    }
   public ArrayList<chitiethoadonDTO> getDsct(){
        return dshd;
    }
   public int getdg(int a){
        chitiethoadonDAO data=new chitiethoadonDAO();
        return data.getdg(a);
    }
   public void xoahd(String a) throws SQLException{
       chitiethoadonDAO data=new chitiethoadonDAO();
       data.xoahd(a);
       dshd.clear();
   }
   public void nhapexl(){
       chitiethoadonDAO data=new chitiethoadonDAO();
       data.nhapexl();
   }
   public void xuatexl() throws IOException{
       chitiethoadonDAO data=new chitiethoadonDAO();
       data.xuatexl();
   }
}
