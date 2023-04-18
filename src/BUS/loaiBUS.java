/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.loaiDAO;
import DATA.sanphamDAO;
import DTO.loaiDTO;
import DTO.sanphamDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class loaiBUS {
    private ArrayList<loaiDTO> dsloai;

    public loaiBUS() {
    }
    public void listLoai(){
        loaiDAO spDAO = new loaiDAO();
        dsloai = new ArrayList<>();
        dsloai = spDAO.list();
        //return dssp;
    }
    public void addLoai(loaiDTO sp) throws Exception{
        
        dsloai.add(sp);
        
        loaiDAO spDAO = new loaiDAO();
        spDAO.add(sp);
    }
    public void deleteLoai(String idLoai) throws Exception{
        for(loaiDTO sp:dsloai){
            if(sp.getMaloai().equals(idLoai)){
                dsloai.remove(sp);
                loaiDAO loaiDAO = new loaiDAO();
                loaiDAO.delete(idLoai);
                return;
            }
        }
    }
    public void setSP(loaiDTO s) throws Exception{
        for(int i=0;i<dsloai.size();i++){
            if(dsloai.get(i).getMaloai().equals(s.getMaloai())){
                dsloai.set(i, s);
                loaiDAO loaiDAO = new loaiDAO();
                loaiDAO.set(s);
                return;
            }
        }
    }
    public boolean checkMasp(String maloai){
        for(loaiDTO sp : dsloai){
            if(sp.getMaloai().equals(maloai)){
                return true;
            }
                
        }
        return false;
        
    }
    public loaiDTO getSP(String maloai){
        for(loaiDTO sp : dsloai){
            if(sp.getMaloai().equals(maloai)){
                return sp;
            }
            
        }
        return null;
    }
    public ArrayList<loaiDTO> searchSP(String str){
        ArrayList<loaiDTO> search = new ArrayList<>();
        str = str.isEmpty()?str = "": str;
        
        
        for(loaiDTO loai:dsloai){
            if( loai.getTenloai().toLowerCase().contains(str.toLowerCase())||
                loai.getMaloai().contains(str)
                  
            ){
                search.add(loai);
            }
               
        }
        return search;
    }
    public ArrayList<loaiDTO> getList(){
        return dsloai;
    }
    public void ExportExcelDatabase() throws IOException{
        loaiDAO loaiDAO = new loaiDAO();
        loaiDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase() throws IOException, SQLException{
        loaiDAO loaiDAO = new loaiDAO();
        loaiDAO.ImportExcelDatabase();
    }  
}

