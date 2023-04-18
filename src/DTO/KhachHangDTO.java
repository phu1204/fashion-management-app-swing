 package DTO;
// cho nghĩa ngu vcl
 // ahuhuhu
 // ahihihihi
/**
 *
 * @author asus
 * @author phudeptrai
 */
public class KhachHangDTO {
    
    public String ID_KH;
    public String HoKH;
    public String TenKH;
    public String GioitinhKH;
    public String SdtKH;
    public String GmailKH;
    
    public KhachHangDTO(){
        
    }
    
    public KhachHangDTO(String ID_KH , String HoKH,String TenKH,String GioitinhKH, String GmailKH, String SdtKH){
        this.ID_KH=ID_KH;
        this.HoKH=HoKH;
        this.TenKH=TenKH;
        this.GioitinhKH=GioitinhKH;
        this.GmailKH=GmailKH;
        this.SdtKH=SdtKH;
        
        
    }
    public void setID_KH(String ID_KH) {
        this.ID_KH = ID_KH;
    }

    public void setHoKH(String Ho) {
        this.HoKH = Ho;
    }

    public void setTenKH(String Ten) {
        this.TenKH = Ten;
    }

    public void setSdtKH(String SĐT) {
        this.SdtKH = SĐT;
    }

    public void setGmailKH(String Gmail) {
        this.GmailKH = Gmail;
    }

    public String getID_KH() {
        return ID_KH;
    }

    public String getHoKH() {
        return HoKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getSdtKH() {
        return SdtKH;
    }

    public String getGmailKH() {
        return GmailKH;
    }
    public void setGioitinhKH(String GioitinhKH) {
        this.GioitinhKH = GioitinhKH;
    }

    public String getGioitinhKH() {
        return GioitinhKH;
    }
      
}