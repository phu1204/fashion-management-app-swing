/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.QLNVDAO;
import DTO.QLNVDTO;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author admin
 */
public class QLNVBUS {
    static ArrayList<QLNVDTO> dsnv;
    public QLNVBUS(){
        
    }
    public void docDSNV()
    {
        QLNVDAO data=new QLNVDAO();
        if (dsnv==null) {dsnv=new ArrayList<QLNVDTO>();}
        dsnv=data.docDSNV();
    }
    public void them(QLNVDTO nv)
    {
        QLNVDAO data=new QLNVDAO();
        if (dsnv==null) dsnv=new ArrayList<QLNVDTO>();
        data.them(nv);
        dsnv.add(nv);
        docDSNV();
    }
    public void xoa(QLNVDTO nv,int i)
    {
        
        QLNVDAO data=new QLNVDAO();
        data.xoa(nv.getId());
        dsnv.remove(i);
        
    }
    public void sua(QLNVDTO nv)
    {
        QLNVDAO data=new QLNVDAO();
        data.sua(nv);
    }
    public ArrayList<QLNVDTO> getDSNV(){
        return dsnv;
    }
    public QLNVDTO getNv(String id){
        
           for(QLNVDTO nv:dsnv){
                     if(nv.getId().equals(id)){
                                return nv;
                     }
                     
           }
           return null;
    }
    public void nhapexl(){
       QLNVDAO data=new QLNVDAO();
        if (dsnv==null) dsnv=new ArrayList<QLNVDTO>();
        data.nhapexl();
        docDSNV();
       
   }
   public void xuatexl() throws IOException{
       QLNVDAO data=new QLNVDAO();
       data.xuatexl();
   }
   public QLNVDTO getsdtNv(String sdt){
        
           for(QLNVDTO nv:dsnv){
                     if(nv.getId().equals(sdt)){
                                return nv;
                     }
                     
           }
           return null;
    }
    public QLNVDTO getemailNv(String email){
        
           for(QLNVDTO nv:dsnv){
                     if(nv.getId().equals(email)){
                                return nv;
                     }
                     
           }
           return null;
    }
}