/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.giamgiaDAO;
import DTO.giamgiaDTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class giamgiaBUS {
    ArrayList<giamgiaDTO> dsgg=new ArrayList<giamgiaDTO>();
    public giamgiaBUS(){}
    public void docBUS() throws SQLException, ParseException{
        giamgiaDAO data=new giamgiaDAO();
        if(dsgg!=null) dsgg=new ArrayList<giamgiaDTO>();
        dsgg=data.docDao();
    }
    public ArrayList<giamgiaDTO> getds(){
        return dsgg;
    }
    public void them(giamgiaDTO hd) throws SQLException{
        giamgiaDAO data=new giamgiaDAO();
        data.them(hd);
        dsgg.add(hd);
    }
   public void xoa(int t){
        giamgiaDAO data=new giamgiaDAO();
        giamgiaDTO gg=new giamgiaDTO();
        gg=dsgg.get(t);
        data.xoa(gg.getMagg());
        dsgg.remove(t);
    }
   public void sua(giamgiaDTO hd,int i){
        giamgiaDAO data=new giamgiaDAO();
        data.sua(hd);
        dsgg.set(i,hd);
    }
   public giamgiaDTO getGg(String id){
       for(giamgiaDTO gg:dsgg)
           if(gg.getMagg().equals(id)) return gg;
       return null;
   }
   public giamgiaDTO search(String id)
    {
        for(giamgiaDTO ct : dsgg)
        {
            if( ct.getMagg().equals(id))
            {
                return ct;
            }
        }
        return null;
    }
   public ArrayList<giamgiaDTO> searchString(String str){
       ArrayList<giamgiaDTO> ds= new ArrayList<giamgiaDTO>();
       for(giamgiaDTO gg:dsgg)
           if(gg.getNgbd().equals(str) || gg.getTengg().contains(str) || gg.getNgkt().equals(str)  )
               ds.add(gg);
       return ds;
    }
}
