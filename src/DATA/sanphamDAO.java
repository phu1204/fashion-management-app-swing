/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.sanphamDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Lenovo
 */
public class sanphamDAO {
    private  mySQLConnect mySQL = new mySQLConnect();
    public void creatTableSanPham(){
        
    }
    public sanphamDAO() {
    }
    public ArrayList<sanphamDTO> list(){
        ArrayList<sanphamDTO> sp = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sanpham";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
                String masanpham = rs.getString("masanpham");
                String tensanpham = rs.getString("tensanpham");
                String maloai = rs.getString("maloai");
                String nsx = rs.getString("nsx");
                String img = rs.getString("img");
                int soluong = rs.getInt("soluong");
                int dongia = rs.getInt("dongia");
                sanphamDTO s = new sanphamDTO(masanpham, tensanpham, nsx, img, soluong, maloai, dongia);
                sp.add(s);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sp;
    }
    public void add(sanphamDTO sp) throws Exception{
        String sql = "INSERT INTO sanpham(masanpham,tensanpham,nsx,img,soluong,maloai,dongia) VALUES (";
        sql += "'"+sp.getMasanpham()+"',";
        sql += "'"+sp.getTensp()+"',";
        sql += "'"+sp.getNsx()+"',";
        sql += "'"+sp.getImg()+"',";
        sql += "'"+sp.getSoluong()+"',";
        sql += "'"+sp.getMaloai()+"',";
        sql += "'"+sp.getDongia()+"'";
        sql += ")";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void delete(String idSP) throws Exception{
        String sql = "DELETE from sanpham where masanpham='"+idSP+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void set(sanphamDTO sp) throws Exception{
        String sql = "UPDATE sanpham SET ";
        sql += "masanpham='"+sp.getMasanpham()+"', ";
        sql += "tensanpham='"+sp.getTensp()+"', ";
        sql += "nsx='"+sp.getNsx()+"', ";
        sql += "img='"+sp.getImg()+"', ";
        sql += "soluong='"+sp.getSoluong()+"', ";
        sql += "maloai='"+sp.getMaloai()+"', ";
        sql += "dongia='"+sp.getDongia()+"' ";
        sql += "WHERE masanpham='"+sp.getMasanpham()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void setSoLuong(sanphamDTO sp){
        String sql = "UPDATE sanpham SET ";
        sql += "soluong='"+sp.getSoluong()+"' ";
        sql += "WHERE masanpham='"+sp.getMasanpham()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void ExportExcelDatabase() throws FileNotFoundException, IOException{
        try{
            String sql = "SELECT * FROM sanpham";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sanphamExcel");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("masanpham");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("tensanpham");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("nsx");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("img");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("soluong");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("maloai");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("dongia");
            cell.setCellStyle(style);
            
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("masanpham"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("tensanpham"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("nsx"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("img"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getInt("soluong"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getInt("maloai"));
            cell = row.createCell(6);
            cell.setCellValue(rs.getInt("dongia"));
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./src/Export/sanphamExcel.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void ImportExcelDatabase() throws IOException, SQLException{
        try{
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Excel", "xlsx");
            fc.setFileFilter(filter);
            File file = fc.getSelectedFile();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }
            FileInputStream in = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                row = (Row) sheet.getRow(i);
                String masanpham = row.getCell(0).getStringCellValue();
                String tensanpham = row.getCell(1).getStringCellValue();
                String nsx = row.getCell(2).getStringCellValue();
                String img = row.getCell(3).getStringCellValue();
                int soluong = (int) row.getCell(4).getNumericCellValue();
                int maloai = (int)row.getCell(5).getNumericCellValue();
                int dongia = (int) row.getCell(6).getNumericCellValue();
                
                String sql_check = "SELECT * FROM sanpham WHERE masanpham='"+masanpham+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO sanpham VALUES (";
                    sql += "'"+masanpham+"',";
                    sql += "'"+tensanpham+"',";
                    sql += "'"+nsx+"',";
                    sql += "'"+img+"',";
                    sql += "'"+soluong+"',";
                    sql += "'"+maloai+"',";
                    sql += "'"+dongia+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE sanpham SET ";
                    sql += "tensanpham='"+tensanpham+"', ";
                    sql += "nsx='"+nsx+"', ";
                    sql += "img='"+img+"', ";
                    sql += "soluong='"+soluong+"', ";
                    sql += "maloai='"+maloai+"', ";
                    
                    sql += "dongia='"+dongia+"' ";
                    sql += "WHERE masanpham='"+masanpham+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
                System.out.println(ex);}
    }
    
   
}
