/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.sanphamDAO;
import DTO.sanphamDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lenovo
 */
public class sanphamBUS {
    private ArrayList<sanphamDTO> dssp;

    public sanphamBUS() {
    }
    public void listSP(){
        sanphamDAO spDAO = new sanphamDAO();
        dssp = new ArrayList<>();
        dssp = spDAO.list();
        //return dssp;
    }
    public void addSP(sanphamDTO sp) throws Exception{
        
        dssp.add(sp);
        
        sanphamDAO spDAO = new sanphamDAO();
        spDAO.add(sp);
    }
    public void deleteSP(String idSP) throws Exception{
        for(sanphamDTO sp:dssp){
            if(sp.getMasanpham().equals(idSP)){
                dssp.remove(sp);
                sanphamDAO spDAO = new sanphamDAO();
                spDAO.delete(idSP);
                return;
            }
        }
    }
    public void setSP(sanphamDTO s) throws Exception{
        for(int i=0;i<dssp.size();i++){
            if(dssp.get(i).getMasanpham().equals(s.getMasanpham())){
                dssp.set(i, s);
                sanphamDAO spDAO = new sanphamDAO();
                spDAO.set(s);
                return;
            }
        }
    }
    public boolean checkMasp(String masp){
        for(sanphamDTO sp : dssp){
            if(sp.getMasanpham().equals(masp)){
                return true;
            }
                
        }
        return false;
        
    }
    public sanphamDTO getSP(String masp){
        for(sanphamDTO sp : dssp){
            if(sp.getMasanpham().equals(masp)){
                return sp;
            }
            
        }
        return null;
    }
    public int getSoLuongSP(String masp){
        for(sanphamDTO sp : dssp){
            if(sp.getMasanpham().equals(masp)){
                return sp.getSoluong();
            }
            
        }
        return 0;
    }
    public ArrayList<sanphamDTO> searchSP2(String index, String max,String min){
        ArrayList<sanphamDTO> search = new ArrayList<>();
        for(sanphamDTO sp:dssp){
            if( sp.getMasanpham().contains(index)||
                sp.getTensp().toLowerCase().contains(index.toLowerCase())||
               
                sp.getMaloai().contains(index)||
                sp.getNsx().toLowerCase().contains(index.toLowerCase())
                
                    
            ){
               
                search.add(sp);
            }
             
        }
        return search;
    }
    public ArrayList<sanphamDTO> searchSP(String index, String max,String min){
        ArrayList<sanphamDTO> search = new ArrayList<>();
        if(index == "conhang"){
             for(sanphamDTO sp:dssp){
            if( sp.getSoluong()>0
                
                    
            ){
                search.add(sp);
            }
               
        }
        return search;
        }else if(index == "hethang"){
             for(sanphamDTO sp:dssp){
            if( sp.getSoluong()==0
                
                    
            ){
                search.add(sp);
            }
               
        }
        return search;
        }
        else if(index == "gia"){
            min = min.isEmpty()?min = "0":min;
            max = max.isEmpty()?max = "0":max;
            int minN = Integer.parseInt(min);
            int maxN = Integer.parseInt(max);
             for(sanphamDTO sp:dssp){
            if( sp.getDongia()>=minN && sp.getDongia()<=maxN){
                search.add(sp);
            }
               
        }
        return search;
        }
        else{
            index = index.isEmpty()?index = "":index;
        for(sanphamDTO sp:dssp){
            if( sp.getMasanpham().contains(index)||
                sp.getMaloai().contains(index)||
                sp.getNsx().contains(index)
                
                    
            ){
                search.add(sp);
            }
               
        }
        return search;
        }
        
    }
    public ArrayList<sanphamDTO> getList(){
        return dssp;
    }
    public void ExportExcelDatabase() throws IOException{
        sanphamDAO spDAO = new sanphamDAO();
        spDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase() throws IOException, SQLException{
        sanphamDAO spDAO = new sanphamDAO();
        spDAO.ImportExcelDatabase();
    }
    
    
    
}
