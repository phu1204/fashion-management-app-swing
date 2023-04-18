/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.chitietgiamgiaDAO;
import DTO.chitietgiamgiaDTO;
import DTO.giamgiaDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class chitietgiamgiaBUS {

    ArrayList<chitietgiamgiaDTO> dsgg=new ArrayList<chitietgiamgiaDTO>();
    public chitietgiamgiaBUS(){}
    public void docbus(String a) throws SQLException{
        chitietgiamgiaDAO data=new chitietgiamgiaDAO();
        if(dsgg!=null) dsgg=new ArrayList<chitietgiamgiaDTO>();
        dsgg=data.docct(a);
    }
    public void them(chitietgiamgiaDTO gg) throws SQLException{
        chitietgiamgiaDAO data=new chitietgiamgiaDAO();
        data.them(gg);
        dsgg.add(gg);
    }
   public void xoa(int t){
        chitietgiamgiaDAO data=new chitietgiamgiaDAO();
        chitietgiamgiaDTO gg=new chitietgiamgiaDTO();
        gg=dsgg.get(t);
        data.xoa(gg.getIdgg(),gg.getIdsp());
        dsgg.remove(t);
    }
   public void sua(chitietgiamgiaDTO gg,int i){
        chitietgiamgiaDAO data=new chitietgiamgiaDAO();
        data.sua(gg);
        dsgg.set(i,gg);
    }
   public ArrayList<chitietgiamgiaDTO> getDsct(){
        return dsgg;
    }
      public giamgiaDTO getgg(String a){
       chitietgiamgiaDAO data=new chitietgiamgiaDAO();
       return data.getgg(a);
   }
   public int getdis(String a,String b){
      for(chitietgiamgiaDTO ct:dsgg){
        if(ct.getIdsp().equals(a) && ct.getIdgg().equals(b)) return ct.getDiscount();
      }
      return 0;
   }
}
