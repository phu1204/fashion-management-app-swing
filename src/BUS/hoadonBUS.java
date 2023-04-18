/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DTO.hoadonDTO;
import DATA.hoadonDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class hoadonBUS {
    ArrayList<hoadonDTO> dshd=new ArrayList<hoadonDTO>();
    public hoadonBUS(){}
    public void docbus() throws SQLException{
        hoadonDAO data=new hoadonDAO();
        if(dshd!=null) dshd=new ArrayList<hoadonDTO>();
        dshd=data.docDAO();
    }
    public void them(hoadonDTO hd) throws SQLException{
        hoadonDAO data=new hoadonDAO();
        data.them(hd);
        dshd.add(hd);
    }
   public void xoa(int t){
        hoadonDAO data=new hoadonDAO();
        hoadonDTO hd=new hoadonDTO();
        hd=dshd.get(t);
        data.xoa(hd.getMahd());
        dshd.remove(t);
    }
   public void sua(hoadonDTO hd,int i){
        hoadonDAO data=new hoadonDAO();
        data.sua(hd);
        dshd.set(i,hd);
    }
   public ArrayList<hoadonDTO> getDshd(){
          return dshd;
    }
   public void xuatexl() throws IOException{
   hoadonDAO data=new hoadonDAO();
   data.xuatexl();
   }
   public void nhapexl(){
   hoadonDAO data=new hoadonDAO();
   data.nhapexl();
   }
   public hoadonDTO search(String id)
    {
        for(hoadonDTO ct : dshd)
        {
            if( ct.getMahd().equals(id))
            {
                return ct;
            }
        }
        return null;
    }
   public ArrayList<hoadonDTO> searchString(String str){
       ArrayList<hoadonDTO> ds= new ArrayList<hoadonDTO>();
       for(hoadonDTO hd:dshd)
           if(hd.getMagg().equals(str) || hd.getMakh().equals(str) || hd.getManv().equals(str)||hd.getNgay().equals(str) || String.valueOf(hd.getTg()).equals(str) || String.valueOf(hd.getTong()).equals(str) ||String.valueOf(hd.getTpt()).equals(str) )
               ds.add(hd);
       return ds;
    }
   public void Inhoadon(hoadonDTO hd) throws SQLException, ParseException{
       InBill ib=new InBill();
       ib.print(hd.getMahd());
   }
}
