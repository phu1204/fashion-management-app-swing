/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.chitietphieunhapDTO;
import DTO.chitietsanphamDTO;
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
public class chitietphieunhapDAO {
    private  mySQLConnect mySQL = new mySQLConnect();
    public void creatTableSanPham(){
        
    }
    public chitietphieunhapDAO() {
    }
    public ArrayList<chitietphieunhapDTO> list(){
        ArrayList<chitietphieunhapDTO> ctpn = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chitietphieunhap";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
                String maphieunhap = rs.getString("maphieunhap");
                String masanpham = rs.getString("masanpham");
                int soluong = rs.getInt("soluong");
                int dongia = rs.getInt("dongia");
                int thanhtien = rs.getInt("thanhtien");
                
                
                chitietphieunhapDTO s = new chitietphieunhapDTO(maphieunhap, masanpham, soluong, dongia, thanhtien);
                ctpn.add(s);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ctpn;
    }
    public void add(chitietphieunhapDTO ctpn){
        String sql = "INSERT INTO chitietphieunhap VALUES (";
        sql += "'"+ctpn.getMaphieunhap()+"',";
        sql += "'"+ctpn.getMasanpham()+"',";
        sql += "'"+ctpn.getSoluong()+"',";
        sql += "'"+ctpn.getDongia()+"',";
        sql += "'"+ctpn.getThanhtien()+"'";
        sql += ")";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String id) throws Exception{
        String sql = "DELETE from chitietphieunhap where maphieunhap='"+id+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void deleteByProduct(String id){
        String sql = "DELETE from chitietphieunhap where masanpham='"+id+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void set(chitietphieunhapDTO ctsp){
        String sql = "UPDATE chitietphieunhap SET ";
        sql += "maphieunhap='"+ctsp.getMaphieunhap()+"', ";
        sql += "masanpham='"+ctsp.getMasanpham()+"', ";
        sql += "soluong='"+ctsp.getSoluong()+"', ";
        sql += "dongia='"+ctsp.getDongia()+"', ";
        sql += "thanhtien='"+ctsp.getThanhtien()+"' ";
        sql += "WHERE maphieunhap='"+ctsp.getMaphieunhap()+"'";
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
                String maphieunhap = row.getCell(0).getStringCellValue();
                String manhanvien = row.getCell(1).getStringCellValue();
                String manhacungcap = row.getCell(2).getStringCellValue();
                String ngaynhap = row.getCell(3).getStringCellValue();
                int tongtien = (int) row.getCell(4).getNumericCellValue();
                
                String sql_check = "SELECT * FROM chitietphieunhap WHERE maphieunhap='"+maphieunhap+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO chitietphieunhap VALUES (";
                    sql += "'"+maphieunhap+"',";
                    sql += "'"+manhanvien+"',";
                    sql += "'"+manhacungcap+"',";
                    sql += "'"+ngaynhap+"',";
                    sql += "'"+tongtien+"')";
                    
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE chitietphieunhap SET ";
                    sql += "manhanvien='"+manhanvien+"', ";
                    sql += "manhacungcap='"+manhacungcap+"', ";
                    sql += "ngaynhap='"+ngaynhap+"' ";
                    
                    sql += "WHERE maphieunhap='"+maphieunhap+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
                System.out.println(ex);}
    }
    
}
