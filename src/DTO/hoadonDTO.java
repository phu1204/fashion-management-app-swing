package DTO;
public class hoadonDTO {
    String mahd;
    String makh;
    String manv;
    String magg;
    String ngay;
    int tong;
    int tg;
    int tpt;
    public hoadonDTO(){}
    public hoadonDTO(String mahd,String makh,String manv,String magg,String ngay,int tong,int tg,int tpt){
        this.mahd=mahd;
        this.makh=makh;
        this.manv=manv;
        this.magg=magg;
        this.ngay=ngay;
        this.tong=tong;
        this.tg=tg;
        this.tpt=tpt;
    }
    public hoadonDTO(hoadonDTO hd){
        mahd=hd.mahd;
        makh=hd.makh;
        manv=hd.manv;
        magg=hd.magg;
        ngay=hd.ngay;
        tong=hd.tong;
        tg=hd.tg;
        tpt=hd.tpt;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMagg() {
        return magg;
    }

    public void setMagg(String magg) {
        this.magg = magg;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }

    public int getTg() {
        return tg;
    }

    public void setTg(int tg) {
        this.tg = tg;
    }

    public int getTpt() {
        return tpt;
    }

    public void setTpt(int tpt) {
        this.tpt = tpt;
    }
}
