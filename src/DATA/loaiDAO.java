package DATA;

import DTO.chitietsanphamDTO;
import DTO.loaiDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class loaiDAO {
    private  mySQLConnect mysql = new mySQLConnect();
    public void creatTableSanPham(){
        
    }
    public loaiDAO() {
    }
    public ArrayList<loaiDTO> list(){
        ArrayList<loaiDTO> loai = new ArrayList<>();
        try {
            String sql = "SELECT * FROM loai";
            ResultSet rs = mysql.executeQuery(sql); 
            while(rs.next()){
                String maloai = rs.getString("maloai");
                String tenloai = rs.getString("tenloai");
               
                loaiDTO s = new loaiDTO(maloai, tenloai);
                loai.add(s);
                
            }
            rs.close();
            mysql.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loai;
    }
    public void add(loaiDTO loai) throws Exception{
        String sql = "INSERT INTO loai VALUES (";
        sql += "'"+loai.getMaloai()+"',";
        
        sql += "'"+loai.getTenloai()+"'";
        sql += ")";
        System.out.println(sql);
        mysql.executeUpdate(sql);
    }
    
    public void delete(String idLoai) throws Exception{
        String sql = "DELETE from loai where maloai='"+idLoai+"'";
        System.out.println(sql);
        mysql.executeUpdate(sql);
    }
    
    public void set(loaiDTO loai) throws Exception{
        String sql = "UPDATE loai SET ";
        sql += "maloai='"+loai.getMaloai()+"', ";
        sql += "tenloai='"+loai.getTenloai()+"' ";
        sql += "WHERE maloai='"+loai.getMaloai()+"'";
        System.out.println(sql);
        mysql.executeUpdate(sql);
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
                String maloai = row.getCell(0).getStringCellValue();
                String tenloai = row.getCell(1).getStringCellValue();
                
                
                String sql_check = "SELECT * FROM loai WHERE maloai='"+maloai+"'";
                ResultSet rs = mysql.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO loai VALUES (";
                    sql += "'"+maloai+"',";
                    sql += "'"+tenloai+"')";
                    
                    System.out.println(sql);
                    mysql.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE loai SET ";
                    sql += "tenloai='"+tenloai+"' ";
                    
                    sql += "WHERE maloai='"+maloai+"'";
                    System.out.println(sql);    
                    mysql.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
                System.out.println(ex);}
    }
    public void ExportExcelDatabase() throws FileNotFoundException, IOException{
        try{
            String sql = "SELECT * FROM loai";
            ResultSet rs = mysql.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("loaiEXCEL");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("maloai");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("tenloai");
            cell.setCellStyle(style);
            
            
            
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("maloai"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("tenloai"));
    
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./src/Export/loaiEXCEL.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
   
}
