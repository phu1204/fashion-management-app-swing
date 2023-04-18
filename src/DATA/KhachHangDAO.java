
package DATA;


import DTO.KhachHangDTO;
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


import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Lenovo
 */
public class KhachHangDAO {
    private  mySQLConnect mySQL = new mySQLConnect();

//    ArrayList<KhachHangDTO> Arr_khachhang = new ArrayList<KhachHangDTO>();

//    ArrayList<KhachHangDTO> Arr_khachhang = new ArrayList<KhachHangDTO>();
    
    public void QLKH(){
        
    }
    public KhachHangDAO() {
        
    }

    public ArrayList<KhachHangDTO> docKhachHang() throws Exception{
       ArrayList<KhachHangDTO> Arr_khachhang = new ArrayList<KhachHangDTO>();
       String qry="Select * from khach";
       ResultSet rs = mySQL.executeQuery(qry);
        try{
            while(rs.next()){
                String ID_KH = rs.getString("Idkhach");
                String HoKH = rs.getString("Họ");
                String TenKH = rs.getString("Tên");
                String GioitinhKH = rs.getString("gioitinh");
                String GmailKH = rs.getString("Gmail");
                String SdtKH = rs.getString("SDT"); 
                KhachHangDTO kh = new KhachHangDTO(ID_KH, HoKH, TenKH, GioitinhKH,  GmailKH,  SdtKH);
                Arr_khachhang.add(kh);
//                KhachHangDTO kh = new KhachHangDTO();
//                kh.setID_KH(rs.getString(1));
//                kh.setHoKH(rs.getString(2));
//                kh.setTenKH(rs.getString(3));
//                kh.setGioitinhKH(rs.getString(4));
//                kh.setGmailKH(rs.getString(5));
//                kh.setSdtKH(rs.getString(6));
//                Arr_khachhang.add(kh);
            }  
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
    }
            rs.close();
        return Arr_khachhang;
        
}
    public void them(KhachHangDTO kh){
         try{
            String qry ="INSERT INTO khach Values (";
//            qry = qry+"'"+khachhang.getID_KH()+"'";
            qry=qry+"'"+"0"+"'";
            qry=qry+","+"'"+kh.getHoKH()+"'";
            qry=qry+","+"'"+kh.getTenKH()+"'";           
            qry=qry+","+"'"+kh.getGioitinhKH()+"'";           
            qry=qry+","+"'"+kh.getGmailKH()+"'";
            qry=qry+","+"'"+kh.getSdtKH()+"'";            
            qry=qry+")";
//            connect.getStatement();
            mySQL.executeUpdate(qry);
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, "lỗi");
       }       
    }
    public void xoa(String ID_KH){
      
        try{
            String qry="delete from khach where Idkhach='"+ID_KH+"'";
           mySQL.executeUpdate(qry);
            System.out.println(qry);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Lỗi");
        }
    }
    public void sua(KhachHangDTO kh){
        try{
            String qry="Update khach Set ";
            
            qry = qry+"Họ='"+kh.getHoKH()+"',";
            qry = qry+"Tên='"+kh.getTenKH()+"',";
            qry = qry+"gioitinh='"+kh.getGioitinhKH()+"',";          
            qry = qry+"Gmail='"+kh.getGmailKH()+"',";
            qry = qry+"SDT='"+kh.getSdtKH()+"'";
            qry = qry+" where Idkhach='"+kh.getID_KH()+"'";
            mySQL.executeUpdate(qry);
            System.out.println(qry);          
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
     public void xuatexlKH() throws FileNotFoundException, IOException {
        try {

            String sql = "select * from khach";
            
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("KhachExcel");

            XSSFCellStyle style = workbook.createCellStyle();

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("MAKH");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
            cell.setCellValue("TENKH");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("HỌ");
            cell.setCellStyle(style);
            
            cell = row.createCell(3);
            cell.setCellValue("GIOITINH");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("GMMAIL");
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue("SDT");
            cell.setCellStyle(style);
            
  
            int i = 1;
            while (rs.next()) {
                String ID_KH=rs.getString(1);
                String HoKH=rs.getString(2);
		String TenKH=rs.getString(3);
                String GioitinhKH=rs.getString(4);
                String GmailKH=rs.getString(5);
		String SdtKH=rs.getString(6);
                
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(ID_KH);
                cell = row.createCell(1);
                cell.setCellValue(HoKH);
                cell = row.createCell(2);
                cell.setCellValue(TenKH);
                cell = row.createCell(3);
                cell.setCellValue(GioitinhKH);
                cell = row.createCell(4);
                cell.setCellValue(GmailKH);
		cell = row.createCell(5);
                cell.setCellValue(SdtKH);
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
                File ex = new File(chooser.getSelectedFile() + "/KhachExcel.xlsx");
                while (ex.exists()) {
                    String s = "KhachExcel.xlsx";
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
      public void nhapexlKH() {
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
                int ID_KH = (int)row.getCell(0).getNumericCellValue();
                String HoKH= (String)row.getCell(1).getStringCellValue();
                String TenKH = (String)row.getCell(2).getStringCellValue();
                String GioitinhKH = (String)row.getCell(3).getStringCellValue();
                String GmailKH = (String)row.getCell(4).getStringCellValue();
                String SdtKH= (String)row.getCell(5).getStringCellValue();

                String sql_check = "SELECT * FROM khach WHERE Idkhach='" +ID_KH+ "'";
                
                ResultSet rs = mySQL.executeQuery(sql_check);
                if (!rs.next()) {
                    
                    String sql = "INSERT INTO khach VALUES (";
                    sql += "'" + ID_KH + "',";
                    sql += "'" + HoKH + "',";
                    sql += "'" + TenKH + "',";
                    sql += "'" + GioitinhKH + "',";
                    sql += "'" + GmailKH + "',";
                    sql += "'" + SdtKH + "')";
                    mySQL.executeUpdate(sql);
                } else {
                    
                    String sql = "UPDATE khach SET ";
                    sql += "`Họ`='" + HoKH + "' ";
                    sql += "`Tên`='" + TenKH + "' ";
                    sql += "`gioitinh`='" + GioitinhKH + "' ";
                    sql += "`Gmail`='" + GmailKH + "' ";
                    sql += "`SDT`='" + SdtKH + "' ";
                    sql += "WHERE Idkhach='" + ID_KH + "'";
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();

        }catch(Exception e){System.out.println(e);} 
    }
}



   
