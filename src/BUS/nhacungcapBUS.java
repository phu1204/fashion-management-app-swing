/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.loaiDAO;
import DATA.nhacungcapDAO;
import DATA.sanphamDAO;
import DTO.nhacungcapDTO;
import DTO.sanphamDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class nhacungcapBUS {
    private ArrayList<nhacungcapDTO> dsncc;

    public nhacungcapBUS() {
    }
    public void listNcc(){
        nhacungcapDAO spDAO = new nhacungcapDAO();
        dsncc = new ArrayList<>();
        dsncc = spDAO.list();
        //return dssp;
    }
    public void addNcc(nhacungcapDTO ncc) throws Exception{
        
        dsncc.add(ncc);
        
        nhacungcapDAO nccDAO = new nhacungcapDAO();
        nccDAO.add(ncc);
    }
    public void deleteSP(String id) throws Exception{
        for(nhacungcapDTO ncc:dsncc){
            if(ncc.getManhacungcap().equals(id)){
                dsncc.remove(ncc);
                nhacungcapDAO nccDAO = new nhacungcapDAO();
                nccDAO.delete(id);
                return;
            }
        }
    }
    public void setSP(nhacungcapDTO s) throws Exception{
        for(int i=0;i<dsncc.size();i++){
            if(dsncc.get(i).getManhacungcap().equals(s.getManhacungcap())){
                dsncc.set(i, s);
                nhacungcapDAO nccDAO = new nhacungcapDAO();
                nccDAO.set(s);
                return;
            }
        }
    }
    public boolean checkMasp(String id){
        for(nhacungcapDTO ncc : dsncc){
            if(ncc.getManhacungcap().equals(id)){
                return true;
            }
                
        }
        return false;
        
    }
    public nhacungcapDTO getNcc(String id){
        for(nhacungcapDTO ncc : dsncc){
            if(ncc.getManhacungcap().equals(id)){
                return ncc;
            }
            
        }
        return null;
    }
    public ArrayList<nhacungcapDTO> searchNcc(String str){
        ArrayList<nhacungcapDTO> search = new ArrayList<>();
        str = str.isEmpty()?str = "":str;
       
        for(nhacungcapDTO ncc:dsncc){
            if( ncc.getManhacungcap().contains(str)||
                ncc.getTennhacungcap().toLowerCase().contains(str.toLowerCase())||
                ncc.getDiachi().toLowerCase().contains(str.toLowerCase())||
                ncc.getSdt().contains(str)
               
                    
            ){
                search.add(ncc);
            }
               
        }
        return search;
    }
    public ArrayList<nhacungcapDTO> getList(){
        return dsncc;
    }
    public void ExportExcelDatabase() throws IOException{
        nhacungcapDAO nccDAO = new nhacungcapDAO();
        nccDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase() throws IOException, SQLException{
        nhacungcapDAO nccDAO = new nhacungcapDAO();
        nccDAO.ImportExcelDatabase();
    }  
}
