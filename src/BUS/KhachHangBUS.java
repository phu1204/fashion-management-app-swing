
package BUS;

import DATA.KhachHangDAO;
import DTO.KhachHangDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 * @author nguye
 */
public class KhachHangBUS {
    


//    public static ArrayList<KhachHangDTO> Arr_khachhang = new ArrayList<>();
//    public  void docKhachHang() throws Exception
     static  ArrayList<KhachHangDTO> Arr_khachhang;
    public  void docKhachHang() throws Exception
    {
        //trung chuyển data qua cái GUI
        
        KhachHangDAO data = new KhachHangDAO();
        if(Arr_khachhang == null)

//    public static ArrayList<KhachHangDTO> Arr_khachhang = new ArrayList<>();
//    public  void docKhachHang() throws Exception
        //trung chuyển data qua cái GUI
        if(Arr_khachhang != null)
            Arr_khachhang = new ArrayList<KhachHangDTO>();
        Arr_khachhang = data.docKhachHang();
           
    }
    
    public  void ThemKhachHang(KhachHangDTO kh) throws Exception
    {
        //trung chuyển data qua cái GUI
        KhachHangDAO data = new KhachHangDAO();
        data.them(kh);
        Arr_khachhang.add(kh);
        docKhachHang();
        //ddd   
    }
    
    public  void SuaKhachHang(KhachHangDTO kh) throws Exception
    {
        //trung chuyển data qua cái GUI
        KhachHangDAO data = new KhachHangDAO();
        data.sua(kh);
        Arr_khachhang.add(kh);
        //ddd   
    }
    
    public  void XoaKhachHang(KhachHangDTO kh,int i) throws Exception
    {
        //trung chuyển data qua cái GUI
        KhachHangDAO data = new KhachHangDAO();
        data.xoa(kh.getID_KH());
        Arr_khachhang.remove(i);
        //ddd   
    }
    
    public ArrayList<KhachHangDTO> getdskh(){
        return Arr_khachhang;
    }
     public static ArrayList<KhachHangDTO> timkiemALL(String tuKhoa){
         ArrayList<KhachHangDTO> Arr_temp = new ArrayList<>();
         for (int i =0; i < KhachHangBUS.Arr_khachhang.size();i++) {
            if(
                    Arr_khachhang.get(i).getHoKH().toLowerCase().contains(tuKhoa.toLowerCase())||
            Arr_khachhang.get(i).getTenKH().toLowerCase().contains(tuKhoa.toLowerCase())||
            Arr_khachhang.get(i).getGmailKH().toLowerCase().contains(tuKhoa.toLowerCase())||           
            Arr_khachhang.get(i).getSdtKH().toLowerCase().contains(tuKhoa.toLowerCase())
                    ){  
                Arr_temp.add(KhachHangBUS.Arr_khachhang.get(i));
            }
        }
       
         return Arr_temp;
    }


    public KhachHangDTO getkh(String makh) {
        for(KhachHangDTO kh:Arr_khachhang){
            if(makh.equals(kh.getID_KH()))
                return kh;
        }
        return null;
    }

     public KhachHangDTO getKh(String id){
            for(KhachHangDTO kh:Arr_khachhang){
                if(String.valueOf(kh.getID_KH()).equals(id) ){
                    return kh;
                }
            }
     return null;
     }
     public void nhapexlKH(){
       KhachHangDAO data=new KhachHangDAO();
       data.nhapexlKH();
   }
   public void xuatexlKH() throws IOException{
       KhachHangDAO data=new KhachHangDAO();
       data.xuatexlKH();
   }
}