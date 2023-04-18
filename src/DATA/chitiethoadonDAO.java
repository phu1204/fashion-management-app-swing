/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;
import DTO.chitiethoadonDTO;
import java.sql.*;
import java.util.ArrayList;
import connect.myconnect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class chitiethoadonDAO {
    myconnect con=new myconnect();
    Connection conn=null;
    Statement st=null;
    ResultSet rs=null;
    ArrayList<chitiethoadonDTO> cthd=new ArrayList<chitiethoadonDTO>();
    public chitiethoadonDAO(){
        con.connectdb();
        conn=con.conn;
    }
    public ArrayList<chitiethoadonDTO> docct(String a) throws SQLException{
        try{
            String qry="Select * from chitiethoadon where Idhd='"+a+"'";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next()){
                chitiethoadonDTO ct=new chitiethoadonDTO();
                ct.setMahd(rs.getString(1));
                ct.setMasp(rs.getString(2));
                ct.setMactsp(rs.getString(5));
                ct.setSl(Integer.parseInt(rs.getString(3)));
                ct.setDg(Integer.parseInt(rs.getString(4)));
                
                cthd.add(ct);
            }
        }catch(Exception e){
            System.out.println("Xem chi tiet that bai");
            System.out.println(e);
        }
        rs.close();
        return cthd;
    }
    public void them(chitiethoadonDTO ct){
        try{
            String qry="Insert into chitiethoadon value ('"+ct.getMahd()+"','"+ct.getMasp()+"','"+ct.getSl()+"','"+ct.getDg()+"','"+ct.getMactsp()+"')";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("Them ct that bai");
            System.out.println(e);
        }
    }
   public void xoa(String a,String b,String c){
        try{
            String qry="Delete from chitiethoadon where Idhd='"+a+"' AND Idsp='"+b+"' AND mactsp='"+c+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(Exception e){
            System.out.println("xóa ct that bai");
            System.out.println(e);
        }
    }
   public void sua(chitiethoadonDTO ct){
        try{
            String qry="Update chitiethoadon set `Số Lượng`='"+ct.getSl()+"',`Đơn giá`='"+ct.getDg()+"' where Idhd='"+ct.getMahd()+"' AND Idsp='"+ct.getMasp()+"' AND mactsp='"+ct.getMactsp()+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("Sua du lieu bi loi!!");
            System.out.println(e);
        }
    }
    public int getdg(int a){
        int i=0;
        try{
            String qry="Select `dongia` from sanpham where masanpham='"+a+"'";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next())
            i=Integer.parseInt(rs.getString(1));
        }catch(Exception e){}
        return i;
    }
    public void xoahd(String a) throws SQLException{
        String qry="Delete from chitiethoadon where Idhd='"+a+"'";
        st=conn.createStatement();
        st.executeUpdate(qry);
    }
    public void xuatexl() throws FileNotFoundException, IOException {
        try {

            String sql = "select * from chitiethoadon";
            con.st=con.conn.createStatement();
            ResultSet rs = con.st.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("ChiTietHoaDonExcel");

            XSSFCellStyle style = workbook.createCellStyle();

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("MAHD");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
            cell.setCellValue("MASP");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("MACTSP");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("SOLUONG");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("DONGIA");
            cell.setCellStyle(style);
  
            int i = 1;
            while (rs.next()) {
                int mahd=rs.getInt(1);
                String masp=rs.getString(2);
                int mactsp=rs.getInt(5);
                int sl=rs.getInt(3);
                int dg=rs.getInt(4);
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(mahd);
                cell = row.createCell(1);
                cell.setCellValue(masp);
                cell = row.createCell(2);
                cell.setCellValue(mactsp);
                cell = row.createCell(3);
                cell.setCellValue(sl);
                cell = row.createCell(4);
                cell.setCellValue(dg);
                i++;
            }

            for (int col = 0; col < row.getLastCellNum(); col++) {
                sheet.autoSizeColumn((short) (col));
            }
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                int count = 0;
                File ex = new File(chooser.getSelectedFile() + "/chitiethoadonExcel.xlsx");
                while (ex.exists()) {
                    String s = "chitiethoadonExcel.xlsx";
                    String[] parts = s.split(".", 2);
                    String path = parts[1];
                    ex = new File(chooser.getSelectedFile() + "/" + "(" + (count++) + ")" + path);
                }
                FileOutputStream out = new FileOutputStream(ex);
                out = new FileOutputStream(ex);
                workbook.write(out);
                out.close();
            }

        } catch (SQLException ex) {}
          

    }

    public void nhapexl() {
        try {

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

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                int mahd = (int)row.getCell(0).getNumericCellValue();
                String masp=row.getCell(1).getStringCellValue();
                int mactsp = (int)row.getCell(2).getNumericCellValue();
                int sl = (int)row.getCell(3).getNumericCellValue();
                int dg=(int) row.getCell(4).getNumericCellValue();

                String sql_check = "SELECT * FROM chitiethoadon WHERE Idhd='" +mahd + "' AND Idsp='"+masp+"' AND mactsp='"+mactsp+"'";
                con.st=con.conn.createStatement();
                ResultSet rs = con.st.executeQuery(sql_check);
                if (!rs.next()) {
                    
                    String sql = "INSERT INTO chitiethoadon VALUES (";
                    sql += "'" + mahd + "',";
                    sql += "'" + masp + "',";
                    sql += "'" + sl + "',";
                    sql += "'" + dg + "',";
                    sql += "'" + mactsp + "')";
                    con.st.executeUpdate(sql);
                } else {
                    
                    String sql = "UPDATE chitiethoadon SET ";
                    sql += "`Số lượng`='" + sl + "', ";
                    sql += "`Đơn giá`='" + dg + "' ";
                    sql += "WHERE Idhd='" + mahd + "' AND `mactsp`='"+mactsp+"' AND Idsp='"+masp+"'";
                    con.st.executeUpdate(sql);
                }
            }
            in.close();

        }catch(Exception e){System.out.println(e);} 
    }
}
