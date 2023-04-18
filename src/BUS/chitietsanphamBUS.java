/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.chitietsanphamDAO;
import DATA.nhacungcapDAO;
import DATA.sanphamDAO;
import DTO.chitietsanphamDTO;
import DTO.sanphamDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class chitietsanphamBUS {
    private ArrayList<chitietsanphamDTO> dsctsp;

    public chitietsanphamBUS() {
    }
    public void listCTSP(){
        chitietsanphamDAO ctspDAO = new chitietsanphamDAO();
        dsctsp = new ArrayList<>();
        dsctsp = ctspDAO.list();
        //return dssp;
    }
    public void addCTSP(chitietsanphamDTO ctsp) throws Exception{
        
        dsctsp.add(ctsp);
        
        chitietsanphamDAO ctspDAO = new chitietsanphamDAO();
        ctspDAO.add(ctsp);
    }
    public void deleteCTSP(String id) throws Exception{
        for(chitietsanphamDTO ctsp:dsctsp){
            if(ctsp.getMachitietsanpham().equals(id)){
                dsctsp.remove(ctsp);
                chitietsanphamDAO ctspDAO = new chitietsanphamDAO();
                ctspDAO.delete(id);
                return;
            }
        }
    }
    public void setSP(chitietsanphamDTO s) throws Exception{
        for(int i=0;i<dsctsp.size();i++){
            if(dsctsp.get(i).getMachitietsanpham().equals(s.getMachitietsanpham())){
                System.out.println("BUS.chitietsanphamBUS.setSP()");
                dsctsp.set(i, s);
                
                chitietsanphamDAO ctspDAO = new chitietsanphamDAO();
                ctspDAO.set(s);
                return;
            }
        }
    }
    public boolean checkMasp(String id){
        for(chitietsanphamDTO ctsp : dsctsp){
            if(ctsp.getMachitietsanpham().equals(id)){
                return true;
            }
                
        }
        return false;
        
    }
    public ArrayList<chitietsanphamDTO> getCTSPByIdSP(String id){
        ArrayList<chitietsanphamDTO> search = new ArrayList<>();
        for(chitietsanphamDTO ctsp : dsctsp){
            if(ctsp.getMasanpham().equals(id)){
                search.add(ctsp);
            }
        }
        return search;
    }
    public chitietsanphamDTO getCTSP(String id){
        for(chitietsanphamDTO ctsp : dsctsp){
            if(ctsp.getMachitietsanpham().equals(id)){
                return ctsp;
            }
        }
        return null;
    }
    public chitietsanphamDTO getCTSPbyIdSPaSizeaColor(String masanpham , String size , String color){
        
        for(chitietsanphamDTO ctsp : dsctsp){
            if(ctsp.getMasanpham().equals(masanpham) && ctsp.getMausac().equals(color) && ctsp.getSize().equals(size)){
                return ctsp;
            }
        }
        return null;
    }
    public int getSoLuongCTSP(String id){
        for(chitietsanphamDTO ctsp : dsctsp){
            if(ctsp.getMachitietsanpham().equals(id)){
                return ctsp.getSoluong();
            }
        }
        return 0;
    }
    public int getTongSoLuongByIdSP(String id){
        int n=0;
        for(chitietsanphamDTO ctsp :dsctsp){
            if(ctsp.getMasanpham().equals(id)){
                n+=ctsp.getSoluong();
            }
        }
        return n;
    }
    public ArrayList<String> getArsize(String a){
    chitietsanphamDAO data=new chitietsanphamDAO();
        return data.getDistinctSize(a);
    }
    public ArrayList<String> getArcolor(String a,String b){
    chitietsanphamDAO ctspDAO=new chitietsanphamDAO();
       return ctspDAO.getDistinctColor(a,b);
    }
    public ArrayList<chitietsanphamDTO> searchCTSP(String machitietsanpham,String masanpham,String size , String mausac,int soluong){
        ArrayList<chitietsanphamDTO> search = new ArrayList<>();
        masanpham = masanpham.isEmpty()?masanpham = "":masanpham;
        size = size.isEmpty()?size = "":size;
        mausac = mausac.isEmpty()?mausac = "":mausac;
        

        for(chitietsanphamDTO ctsp:dsctsp){
            if( ctsp.getMasanpham().contains(masanpham)&&
                ctsp.getSize().contains(size)&&
                ctsp.getMausac().contains(mausac)
               
            ){
                search.add(ctsp);
            }
               
        }
        return search;
    }
    public ArrayList<chitietsanphamDTO> getList(){
        return dsctsp;
    }
    public void ExportExcelDatabase() throws IOException{
        chitietsanphamDAO ctspDAO = new chitietsanphamDAO();
        ctspDAO.ExportExcelDatabase();
    }
    public static void main(String[] args) throws SQLException {
        
        chitietsanphamBUS ctspBUS = new chitietsanphamBUS();
        ctspBUS.listCTSP();
        chitietsanphamDAO ctspDAO = new chitietsanphamDAO();
        ctspDAO.list();
        for(String a: ctspDAO.getDistinctSize("1")){
            System.out.println(a);
        }
        for(String a: ctspDAO.getDistinctColor("1","Đen")){
            System.out.println(a);
        }
        //chitietsanphamDTO ctspDTO = ctspBUS.getCTSPbyIdSPaSizeaColor(ctspBUS.getCTSPByIdSP("1"));
        //System.out.println(ctspDTO.getSoluong());
        
    }
    
}
