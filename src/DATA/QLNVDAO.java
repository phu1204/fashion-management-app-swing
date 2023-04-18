/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.QLNVDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author admin
 */
public class QLNVDAO {
    String user="root";
    String password="";
    String url="jdbc:mysql://localhost:3307/doan";
    Connection conn = null;
    Statement st=null;
    ResultSet rs=null;
    public QLNVDAO()
    {
        if(conn==null)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url,user,password);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QLNVDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (SQLException ex) {
                Logger.getLogger(QLNVDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public ArrayList docDSNV()
    {
        ArrayList dsnv=new ArrayList<QLNVDTO>();
        
        try {
            String qry="select * from nhanvien";
            st=conn.createStatement();
            rs=st.executeQuery(qry);         
            while(rs.next()){
                QLNVDTO nv=new QLNVDTO();
                nv.setId(rs.getString(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setNgaysinh(rs.getString(4));
                nv.setGioitinh(rs.getString(5));
                nv.setDiachi(rs.getString(6));
                nv.setEmail(rs.getString(7));
                nv.setSdt(rs.getString(8));
                nv.setLuong(rs.getString(9));
                dsnv.add(nv);
            }
            } catch (SQLException ex) {
            Logger.getLogger(QLNVDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnv;
        
    }
    public void them(QLNVDTO nv)
    {   
       
        
            try {
            String qry="Insert into nhanvien values (";
            qry=qry+"'"+"0"+"'";
            qry=qry+","+"'"+nv.getHo()+"'";
            qry=qry+","+"'"+nv.getTen()+"'";
            qry=qry+","+"'"+nv.getNgaysinh()+"'";
            qry=qry+","+"'"+nv.getGioitinh()+"'";
            qry=qry+","+"'"+nv.getDiachi()+"'";
            qry=qry+","+"'"+nv.getEmail()+"'";
            qry=qry+","+"'"+nv.getSdt()+"'";
            qry=qry+","+"'"+nv.getLuong()+"'";
            qry=qry+")";
            st=conn.createStatement();
            st.executeUpdate(qry);
            } catch (SQLException ex) {
            Logger.getLogger(QLNVDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
            
       
    }
    public void xoa(String id){
        
        try {
            String qry="Delete from nhanvien where idnv='"+id+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(QLNVDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void sua(QLNVDTO nv){
        
        try {
           String qry="UPDATE nhanvien SET ";
           qry=qry+" "+"Họ="+"'"+nv.getHo()+"'";
           qry=qry+",Tên="+"'"+nv.getTen()+"'";
           qry=qry+",`Ngày sinh`="+"'"+nv.getNgaysinh()+"'";
           qry=qry+",`Giới tính`="+"'"+nv.getGioitinh()+"'";
           qry=qry+",`Địa chỉ`="+"'"+nv.getDiachi()+"'";
           qry=qry+",Email="+"'"+nv.getEmail()+"'";
           qry=qry+",SDT="+"'"+nv.getSdt()+"'";
           qry=qry+",`Lương tháng`="+"'"+nv.getLuong()+"'";
           qry=qry+" "+"WHERE Idnv='"+nv.getId()+"'";
           st=conn.createStatement();
           st.executeUpdate(qry);
       
        } catch (SQLException ex) {
            Logger.getLogger(QLNVDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void xuatexl() throws FileNotFoundException, IOException {
        try {

            String sql = "select * from nhanvien";
            st=conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("NhanvienExcel");

            XSSFCellStyle style = workbook.createCellStyle();

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("IDNV");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
            cell.setCellValue("Họ");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("Tên");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("Ngày Sinh");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("Giới Tính");
            cell.setCellStyle(style);
            
            cell = row.createCell(5);
            cell.setCellValue("Địa Chỉ");
            cell.setCellStyle(style);
            
            cell = row.createCell(6);
            cell.setCellValue("Email");
            cell.setCellStyle(style);
            
            cell = row.createCell(7);
            cell.setCellValue("Số Điện Thoại");
            cell.setCellStyle(style);
            
            cell = row.createCell(8);
            cell.setCellValue("Lương");
            cell.setCellStyle(style);
  
            int i = 1;
            while (rs.next()) {
                int id=rs.getInt(1);
                String ho=rs.getString(2);
                String ten=rs.getString(3);
                String ngaysinh=rs.getString(4);
                String gioitinh=rs.getString(5);
                String diachi=rs.getString(6);
                String email=rs.getString(7);
                String sdt=rs.getString(8);
                int luong=rs.getInt(9);
               
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(id);
                cell = row.createCell(1);
                cell.setCellValue(ho);
                cell = row.createCell(2);
                cell.setCellValue(ten);
                cell = row.createCell(3);
                cell.setCellValue(ngaysinh);
                cell = row.createCell(4);
                cell.setCellValue(gioitinh);
                cell = row.createCell(5);
                cell.setCellValue(diachi);
                cell = row.createCell(6);
                cell.setCellValue(email);
                cell = row.createCell(7);
                cell.setCellValue(sdt);
                cell = row.createCell(8);
                cell.setCellValue(luong);
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
                File ex = new File(chooser.getSelectedFile() + "/nhanvienExcel.xlsx");
                while (ex.exists()) {
                    String s = "nhanvienExcel.xlsx";
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
    
                int Id=(int)row.getCell(0).getNumericCellValue();
                String Ho=row.getCell(1).getStringCellValue();
                String Ten=row.getCell(2).getStringCellValue();
                String Ngaysinh=row.getCell(3).getStringCellValue();
                String Gioitinh=row.getCell(4).getStringCellValue();
                String Diachi=row.getCell(5).getStringCellValue();
                String Email=row.getCell(6).getStringCellValue();
                String SDT=row.getCell(7).getStringCellValue();
                int Luong=(int)row.getCell(8).getNumericCellValue();

                String sql_check = "SELECT * FROM nhanvien WHERE Idnv='" +Id+ "'";
                st=conn.createStatement();
                ResultSet rs = st.executeQuery(sql_check);
                if (!rs.next()) {
                    
                    String sql = "INSERT INTO nhanvien VALUES (";
                    sql += "'" + Id + "',";
                    sql += "'" + Ho + "',";
                    sql += "'" + Ten + "',";
                    sql += "'" + Ngaysinh + "',";
                    sql += "'" + Gioitinh + "',";
                    sql += "'" + Diachi + "',";
                    sql += "'" + Email + "',";
                    sql += "'" + SDT + "',";
                    sql += "'" + Luong + "')";
                    st.executeUpdate(sql);
                } else {
                    
                    String sql = "UPDATE nhanvien SET ";
                    sql += "`Họ`='" +Ho + "', ";
                    sql += "`Tên`='" +Ten + "' ";
                    sql += "`Ngày Sinh`='" +Ngaysinh + "' ";
                    sql += "`Giới tính`='" +Gioitinh+ "' ";
                    sql += "`Địa chỉ`='" + Diachi+ "' ";
                    sql += "`Email`='" +Email + "' ";
                    sql += "`SDT`='" + SDT + "' ";
                    sql += "`Lương tháng`='" + Luong + "' ";
                    sql += "WHERE Idnv='" +Id+ "'";
                    st.executeUpdate(sql);
                }
            }
            in.close();

        }catch(Exception e){System.out.println(e);} 
    }
            
    
    
}