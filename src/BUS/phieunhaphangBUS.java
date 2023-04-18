/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.loaiDAO;
import DATA.phieunhaphangDAO;
import DTO.loaiDTO;
import DTO.phieunhaphangDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class phieunhaphangBUS {
    private ArrayList<phieunhaphangDTO> dspnh;

    public phieunhaphangBUS() {
    }
    public void listPNH(){
        phieunhaphangDAO pnhDAO = new phieunhaphangDAO();
        dspnh = new ArrayList<>();
        dspnh = pnhDAO.list();
        //return dssp;
    }
    public void addPNH(phieunhaphangDTO sp) throws Exception{
        
        dspnh.add(sp);
        
        phieunhaphangDAO pnhDAO = new phieunhaphangDAO();
        pnhDAO.add(sp);
    }
    public void deletePNH(String id) throws Exception{
        for(phieunhaphangDTO pnh:dspnh){
            if(pnh.getMaphieunhap().equals(id)){
                dspnh.remove(pnh);
                phieunhaphangDAO pnhDAO = new phieunhaphangDAO();
                pnhDAO.delete(id);
                return;
            }
        }
    }
    public void setPNH(phieunhaphangDTO pnh) throws Exception{
        for(int i=0;i<dspnh.size();i++){
            if(dspnh.get(i).getMaphieunhap().equals(pnh.getMaphieunhap())){
                dspnh.set(i, pnh);
                phieunhaphangDAO pnhDAO = new phieunhaphangDAO();
                pnhDAO.set(pnh);
                return;
            }
        }
    }
    public boolean checkMaPhieuNhap(String id){
        for(phieunhaphangDTO sp : dspnh){
            if(sp.getMaphieunhap().equals(id)){
                return true;
            }
                
        }
        return false;
        
    }
    public phieunhaphangDTO getPNH(String id){
        for(phieunhaphangDTO pnh : dspnh){
            if(pnh.getMaphieunhap().equals(id)){
                return pnh;
            }
            
        }
        return null;
    }
    
    public int getTongTien(String id){
        for(phieunhaphangDTO pnh : dspnh){
            if(pnh.getMaphieunhap().equals(id)){
                return pnh.getTongtien();
            }
            
        }
        return 0;
    }
    public ArrayList<phieunhaphangDTO> searchPNH(String str,String ngaynhap){
        ArrayList<phieunhaphangDTO> search = new ArrayList<>();
        
        
        for(phieunhaphangDTO pnh:dspnh){
            if( pnh.getMaphieunhap().contains(str)||
                pnh.getManhanvien().contains(str) ||
                pnh.getManhacungcap().contains(str)||
                pnh.getNgaynhap().contains(ngaynhap)
                  
            ){
                search.add(pnh);
            }
               
        }
        return search;
    }
    public ArrayList<phieunhaphangDTO> getList(){
        return dspnh;
    }
    public void ExportExcelDatabase() throws IOException{
        phieunhaphangDAO nhapDAO = new phieunhaphangDAO();
        nhapDAO.ExportExcelDatabase();
    }
    
}
