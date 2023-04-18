/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.Hello
 */
package BUS;

import DATA.chitietphieunhapDAO;
import DATA.nhacungcapDAO;
import DATA.phieunhaphangDAO;
import DTO.chitietphieunhapDTO;
import DTO.phieunhaphangDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class chitietphieunhapBUS {
    private ArrayList<chitietphieunhapDTO> dsctpnh;

    public chitietphieunhapBUS() {
    }
    public void listCTPNH(){
        chitietphieunhapDAO ctpnhDAO = new chitietphieunhapDAO();
        dsctpnh = new ArrayList<>();
        dsctpnh = ctpnhDAO.list();
        //return dssp;
    }
    public void addCTPNH(chitietphieunhapDTO sp){
        
        dsctpnh.add(sp);
        
        chitietphieunhapDAO ctpnhDAO = new chitietphieunhapDAO();
        ctpnhDAO.add(sp);
    }
    public void deleteCTPNH(String id) throws Exception{
        for(chitietphieunhapDTO pnh:dsctpnh){
            if(pnh.getMaphieunhap().equals(id)){
                dsctpnh.remove(pnh);
                chitietphieunhapDAO pnhDAO = new chitietphieunhapDAO();
                pnhDAO.delete(id);
                return;
            }
        }
    }
    public void deleteCTPNHByProduct(String id){
        for(chitietphieunhapDTO pnh:dsctpnh){
            if(pnh.getMasanpham().equals(id)){
                dsctpnh.remove(pnh);
                chitietphieunhapDAO pnhDAO = new chitietphieunhapDAO();
                pnhDAO.deleteByProduct(id);
                return;
            }
        }
    }
    public void setCTPNH(chitietphieunhapDTO pnh){
        for(int i=0;i<dsctpnh.size();i++){
            if(dsctpnh.get(i).getMaphieunhap().equals(pnh.getMaphieunhap())){
                dsctpnh.set(i, pnh);
                chitietphieunhapDAO pnhDAO = new chitietphieunhapDAO();
                pnhDAO.set(pnh);
                return;
            }
        }
    }
    public boolean checkMaPhieuNhap(String id){
        for(chitietphieunhapDTO sp : dsctpnh){
            if(sp.getMaphieunhap().equals(id)){
                return true;
            }
                
        }
        return false;
        
    }
    public chitietphieunhapDTO getPNH(String id){
        for(chitietphieunhapDTO pnh : dsctpnh){
            if(pnh.getMaphieunhap().equals(id)){
                return pnh;
            }
            
        }
        return null;
    }
    public int getSoLuongByPhieuNhap(String id){
        for(chitietphieunhapDTO pnh : dsctpnh){
            if(pnh.getMaphieunhap().equals(id)){
                return pnh.getSoluong();
            }
            
        }
        return 0;
    }
    public int getDonGiaByPhieuNhap(String id){
        for(chitietphieunhapDTO pnh : dsctpnh){
            if(pnh.getMaphieunhap().equals(id)){
                return pnh.getDongia();
            }
            
        }
        return 0;
    }
    public ArrayList<chitietphieunhapDTO> getCTPNBYIdPN(String id){
        ArrayList<chitietphieunhapDTO> ctpn = new ArrayList<>();
        for(chitietphieunhapDTO pnh : dsctpnh){
            if(pnh.getMaphieunhap().equals(id)){
                ctpn.add(pnh);
            }
            
        }
        return ctpn;
    }
    public ArrayList<chitietphieunhapDTO> searchPNH(String maphieunhap,String masanpham){
        ArrayList<chitietphieunhapDTO> search = new ArrayList<>();
        maphieunhap = maphieunhap.isEmpty()?maphieunhap = "": maphieunhap;
        masanpham = masanpham.isEmpty()?masanpham = "":masanpham;
        
        
        for(chitietphieunhapDTO pnh:dsctpnh){
            if( pnh.getMaphieunhap().contains(maphieunhap)&&
                pnh.getMasanpham().contains(masanpham) 
                
                  
            ){
                search.add(pnh);
            }
               
        }
        return search;
    }
    public ArrayList<chitietphieunhapDTO> getList(){
        return dsctpnh;
    }
    public void ImportExcelDatabase() throws IOException, SQLException{
        chitietphieunhapDAO ctpnDAO = new chitietphieunhapDAO();
        ctpnDAO.ImportExcelDatabase();
    }
}
