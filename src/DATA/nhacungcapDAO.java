/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.nhacungcapDTO;
import DTO.sanphamDTO;
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

/**
 *
 * @author Lenovo
 */
public class nhacungcapDAO {
    private  mySQLConnect mySQL = new mySQLConnect();
    
    public nhacungcapDAO() {
    }
    public ArrayList<nhacungcapDTO> list(){
        ArrayList<nhacungcapDTO> ncc = new ArrayList<>();
        try {
            String sql = "SELECT * FROM nhacungcap";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
                String manhacungcap = rs.getString("manhacungcap");
                String tennhacungcap = rs.getString("tennhacungcap");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                
                nhacungcapDTO s = new nhacungcapDTO(manhacungcap, tennhacungcap, diachi, sdt);
                ncc.add(s);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ncc;
    }
    public void add(nhacungcapDTO ncc) throws Exception{
        String sql = "INSERT INTO nhacungcap(manhacungcap,tennhacungcap,diachi,sdt) VALUES (";
        sql += "'"+ncc.getManhacungcap()+"',";
        sql += "'"+ncc.getTennhacungcap()+"',";
        sql += "'"+ncc.getDiachi()+"',";
        sql += "'"+ncc.getSdt()+"'";
        sql += ")";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void delete(String id) throws Exception{
        String sql = "DELETE from nhacungcap where manhacungcap='"+id+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void set(nhacungcapDTO ncc) throws Exception{
        String sql = "UPDATE nhacungcap SET ";
        sql += "manhacungcap='"+ncc.getManhacungcap()+"', ";
        sql += "tennhacungcap='"+ncc.getTennhacungcap()+"', ";
        sql += "diachi='"+ncc.getDiachi()+"', ";
        sql += "sdt='"+ncc.getSdt()+"' ";
        sql += "WHERE manhacungcap='"+ncc.getManhacungcap()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
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
                String manhacungcap = row.getCell(0).getStringCellValue();
                String tennhacungcap = row.getCell(1).getStringCellValue();
                String diachi = row.getCell(2).getStringCellValue();
                String sdt = row.getCell(3).getStringCellValue();
                
                String sql_check = "SELECT * FROM nhacungcap WHERE manhacungcap='"+manhacungcap+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO nhacungcap VALUES (";
                    sql += "'"+manhacungcap+"',";
                    sql += "'"+tennhacungcap+"',";
                    sql += "'"+diachi+"',";
                    sql += "'"+sdt+"')";
                    
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE nhacungcap SET ";
                    sql += "tennhacungcap='"+tennhacungcap+"', ";
                    sql += "diachi='"+diachi+"', ";
                    sql += "sdt='"+sdt+"' ";
                   
                    sql += "WHERE manhacungcap='"+manhacungcap+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
                System.out.println(ex);}
    }
    public void ExportExcelDatabase() throws FileNotFoundException, IOException{
        try{
            String sql = "SELECT * FROM nhacungcap";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("nhacungcap");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("manhacungcap");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("tennhacungcap");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("diachi");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("sdt");
            cell.setCellStyle(style);
            
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("manhacungcap"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("tennhacungcap"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("diachi"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("sdt"));
            
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./src/Export/nhacungcapExcel.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
   
}
