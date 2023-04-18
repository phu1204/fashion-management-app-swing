package DATA;

import DTO.chitietsanphamDTO;
import DTO.sanphamDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class chitietsanphamDAO {
    private  mySQLConnect mySQL = new mySQLConnect();
    public void creatTableSanPham(){
        
    }
    public chitietsanphamDAO() {
    }
    public ArrayList<chitietsanphamDTO> list(){
        ArrayList<chitietsanphamDTO> ctsp = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chitietsanpham";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
                String machitietsanpham = rs.getString("machitietsanpham");
                String masanpham = rs.getString("masanpham");
                String size = rs.getString("size");
                String mausac = rs.getString("mausac");
                //String maloai = rs.getString("maloai");
                int soluong = rs.getInt("soluong");
                
                chitietsanphamDTO s = new chitietsanphamDTO(machitietsanpham, masanpham, size, mausac, soluong);
                ctsp.add(s);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ctsp;
    }
    public ArrayList<String> getDistinctSize(String masanpham){
        ArrayList<String> sizeCTSP = new ArrayList<>() ;
        String sql = "SELECT DISTINCT size FROM chitietsanpham where masanpham='"+masanpham+"'";
        //System.out.println(sql);
        ResultSet rs = mySQL.executeQuery(sql);
        
        int i=0;
        try {
            while(rs.next()){
                String size = rs.getNString("size");
                sizeCTSP.add(size);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(chitietsanphamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sql);
        return sizeCTSP;
    }
    public ArrayList<String> getDistinctColor(String masanpham,String size){
        ArrayList<String> colorCTSP = new ArrayList<>() ;
        String sql = "SELECT DISTINCT mausac FROM chitietsanpham where masanpham='"+masanpham+"' and size='"+size+"'";
        //System.out.println(sql);
        ResultSet rs = mySQL.executeQuery(sql);
        
        int i=0;
        try {
            while(rs.next()){
                String mau = rs.getNString("mausac");
                colorCTSP.add(mau);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(chitietsanphamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sql);
        return colorCTSP;
    }
    public void add(chitietsanphamDTO sp){
        String sql = "INSERT INTO chitietsanpham(masanpham,size,mausac,soluong) VALUES (";
        
        sql += "'"+sp.getMasanpham()+"',";
        sql += "'"+sp.getSize()+"',";
        sql += "'"+sp.getMausac()+"',";
        //sql += "'"+sp.getLoai()+"',";
        sql += "'"+sp.getSoluong()+"'";
        sql += ")";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String idSP) throws Exception{
        String sql = "DELETE from chitietsanpham where machitietsanpham='"+idSP+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void set(chitietsanphamDTO ctsp) throws Exception{
        String sql = "UPDATE chitietsanpham SET ";
        sql += "machitietsanpham='"+ctsp.getMachitietsanpham()+"', ";
        sql += "masanpham='"+ctsp.getMasanpham()+"', ";
        sql += "size='"+ctsp.getSize()+"', ";
        sql += "mausac='"+ctsp.getMausac()+"', ";
        //sql += "loai='"+ctsp.getLoai()+"', ";
        sql += "soluong='"+ctsp.getSoluong()+"' ";
        sql += "WHERE machitietsanpham='"+ctsp.getMachitietsanpham()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void setSoLuong(chitietsanphamDTO ctsp){
        String sql = "UPDATE chitietsanpham SET ";
        sql += "soluong='"+ctsp.getSoluong()+"' ";
        sql += "WHERE machitietsanpham='"+ctsp.getMachitietsanpham()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void ExportExcelDatabase() throws FileNotFoundException, IOException{
        try{
            String sql = "SELECT * FROM chitietsanpham";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("chitietsanphamExcel");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("machitietsanpham");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("masanpham");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("size");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("mausac");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("soluong");
            cell.setCellStyle(style);
            
            
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("machitietsanpham"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("masanpham"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("size"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("mausac"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getInt("soluong"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./src/Export/chitietsanphamExcel.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
   
}
