/*
 * To change this license header, choose License Headecon.rs in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;
import DTO.hoadonDTO;
import connect.*;
import java.sql.*;
import java.util.*;
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
 * @author ASUS
 */
public class hoadonDAO {
    myconnect con=new myconnect();
    ArrayList<hoadonDTO> dshd=new ArrayList<hoadonDTO>();
    public hoadonDAO(){
        con.connectdb();
    }
    public ArrayList<hoadonDTO> docDAO() throws SQLException{
        String qry="Select * from hoadon";
        dshd=new ArrayList<hoadonDTO>();
        con.st=con.conn.createStatement();
       con.rs=con.st.executeQuery(qry);
        while(con.rs.next()){
            hoadonDTO hd=new hoadonDTO();
            hd.setMahd(con.rs.getString(1));
            hd.setMakh(con.rs.getString(2));
            hd.setManv(con.rs.getString(3));
            hd.setMagg(con.rs.getString(4));
            hd.setNgay(con.rs.getString(5));
            hd.setTong(Integer.parseInt(con.rs.getString(6)));
            hd.setTg(Integer.parseInt(con.rs.getString(7)));
            hd.setTpt(Integer.parseInt(con.rs.getString(8)));
            dshd.add(hd);
        }
        con.rs.close();
        return dshd;
    }
   public void them(hoadonDTO hd) throws SQLException{
        String qry="Insert into hoadon value ( '"+hd.getMahd()+"','"+hd.getMakh()+"','"+hd.getManv()+"','"+hd.getMagg()+"','"+hd.getNgay()
                +"','"+hd.getTong()+"','"+hd.getTg()+"','"+hd.getTpt()+"')";
       con.st=con.conn.createStatement();
        con.st.executeUpdate(qry);
    }
    public void xoa(String ma){
        try{
            String qry="Delete from `hoadon` where Idhd='"+ma+"'";
            con.st=con.conn.createStatement();
            con.st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("xoa du lieu that bai!!!");
            System.out.println(e);
        }
    }
  public  void sua(hoadonDTO hd){
        try{
            String qry="Update hoadon set Idkhach='"+hd.getMakh()+"',Idnv='"+hd.getManv()+"',`IdGgiá`='"+hd.getMagg()+"',Ngày='"+hd.getNgay()
                +"',`Tổng tiền`='"+hd.getTong()+"',`Tiền giảm`='"+hd.getTg()+"',`Tiền trả`='"+hd.getTpt()+"' where Idhd='"+hd.getMahd()+"'";
            con.st=con.conn.createStatement();
            con.st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("Sua du lieu bi loi!!");
            System.out.println(e);
        }
    }
    public void xuatexl() throws FileNotFoundException, IOException {
        try {

            String sql = "select * from hoadon";
            con.st=con.conn.createStatement();
            ResultSet rs = con.st.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("HoaDonExcel");

            XSSFCellStyle style = workbook.createCellStyle();

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("MAHD");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
            cell.setCellValue("MAKH");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("MANV");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("MAGG");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("NGAY");
            cell.setCellStyle(style);
            
            cell = row.createCell(5);
            cell.setCellValue("TONGTIENCT");
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue("TONGTIENGIAM");
            cell.setCellStyle(style);

            cell=row.createCell(7);
            cell.setCellValue("TIENPHAITRA");
            cell.setCellStyle(style);
            int i = 1;

            while (rs.next()) {
                int mahd=rs.getInt(1);
                int makh=rs.getInt(2);
                int manv=rs.getInt(3);
                int magg=rs.getInt(4);
                String ngay=rs.getString(5);
                int tt=rs.getInt(6);
                int tg=rs.getInt(7);
                int tpt=rs.getInt(8);
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(mahd);
                cell = row.createCell(1);
                cell.setCellValue(makh);
                cell = row.createCell(2);
                cell.setCellValue(manv);
                cell = row.createCell(3);
                cell.setCellValue(magg);
                cell = row.createCell(4);
                cell.setCellValue(ngay);
                cell = row.createCell(5);
                cell.setCellValue(tt);
                cell = row.createCell(6);
                cell.setCellValue(tg);
                cell=row.createCell(7);
                cell.setCellValue(tpt);
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
                File ex = new File(chooser.getSelectedFile() + "/hoadonExcel.xlsx");
                while (ex.exists()) {
                    String s = "chitietExcel.xlsx";
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
                int makh = (int)row.getCell(1).getNumericCellValue();
                int manv = (int)row.getCell(2).getNumericCellValue();
                int magg = (int)row.getCell(3).getNumericCellValue();
                String ngay = row.getCell(4).getStringCellValue();
                int tt = (int) row.getCell(5).getNumericCellValue();
                int tg = (int )row.getCell(6).getNumericCellValue();
                int tpt=(int) row.getCell(7).getNumericCellValue();

                String sql_check = "SELECT * FROM hoadon WHERE idhd='" +mahd + "'";
                con.st=con.conn.createStatement();
                ResultSet rs = con.st.executeQuery(sql_check);
                if (!rs.next()) {
                    
                    String sql = "INSERT INTO hoadon VALUES (";
                    sql += "'" + mahd + "',";
                    sql += "'" + makh + "',";
                    sql += "'" + manv + "',";
                    sql += "'" + magg + "',";
                    sql += "'" + ngay + "',";
                    sql += "'" + tt + "',";
                    sql += "'" + tg + "',";
                    sql += "'" + tpt + "')";
                    System.out.println(sql);
                    con.st.executeUpdate(sql);
                } else {
                    
                    String sql = "UPDATE hoadon SET ";
                    sql += "Idkhach='" + makh + "', ";
                    sql += "Idnv='" + manv + "', ";
                    sql += "`IdGgiá`='" + magg + "', ";
                    sql += "`Ngày`='" + ngay + "', ";
                    sql += "`Tổng tiền`='" + tt + "', ";
                    sql += "`Tiền giảm`='" + tg + "', ";
                    sql +="`Tiền trả`='"+tpt+"' ";
                    sql += "WHERE Idhd='" + mahd + "'";
                    con.st.executeUpdate(sql);
                }
            }
            in.close();

        }catch(Exception e){System.out.println(e);} 
    }
}
