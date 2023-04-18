/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.nhacungcapDTO;
import DTO.phieunhaphangDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class phieunhaphangDAO {
    private  mySQLConnect mySQL = new mySQLConnect();
    
    public phieunhaphangDAO() {
    }
    public ArrayList<phieunhaphangDTO> list(){
        ArrayList<phieunhaphangDTO> pnh = new ArrayList<>();
        try {
            String sql = "SELECT * FROM phieunhaphang";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
                String maphieunhap = rs.getString("maphieunhap");
                String manhanvien = rs.getString("manhanvien");
                String manhacungcap = rs.getString("manhacungcap");
                String ngaynhap = rs.getString("ngaynhap");
                int tongtien = rs.getInt("tongtien");
                
                phieunhaphangDTO s = new phieunhaphangDTO(maphieunhap, manhanvien, manhacungcap, ngaynhap,tongtien);
                pnh.add(s);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pnh;
    }
    public void add(phieunhaphangDTO pnh) throws Exception{
        String sql = "INSERT INTO phieunhaphang(manhanvien,manhacungcap,ngaynhap,tongtien) VALUES (";

        sql += "'"+pnh.getManhanvien()+"',";
        sql += "'"+pnh.getManhacungcap()+"',";
        sql += "'"+pnh.getNgaynhap()+"',";
        sql += "'"+pnh.getTongtien()+"'";
        sql += ")";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void delete(String id) throws Exception{
        String sql = "DELETE from phieunhaphang where maphieunhap='"+id+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void set(phieunhaphangDTO ncc) throws Exception{
        String sql = "UPDATE phieunhaphang SET ";
        sql += "maphieunhap='"+ncc.getMaphieunhap()+"', ";
        sql += "manhanvien='"+ncc.getManhanvien()+"', ";
        sql += "manhacungcap='"+ncc.getManhacungcap()+"', ";
        sql += "ngaynhap='"+ncc.getNgaynhap()+"',";
        sql += "tongtien='"+ncc.getTongtien()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void setTongTien(String id,int soluong){
        String sql = "UPDATE phieunhaphang SET ";
        sql += "tongtien='"+soluong+"' ";
        sql += "WHERE maphieunhap='"+id+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void ExportExcelDatabase() throws FileNotFoundException, IOException{
        try{
            String sql = "SELECT * FROM phieunhaphang";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("phieunhaphangExcel");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("maphieunhap");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("manhanvien");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("manhacungcap");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("ngaynhap");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("tongtien");
            cell.setCellStyle(style);
            
            
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("maphieunhap"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("manhanvien"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("manhacungcap"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("ngaynhap"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getInt("tongtien"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./src/Export/phieunhaphangExcel.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    public static void main(String[] args) {
        
    }
}
