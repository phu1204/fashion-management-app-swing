package DTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class sanphamDTO {
    private String masanpham,tensp,nsx,img,maloai;
    private int soluong,dongia;
    public sanphamDTO() {
    }
    
    public sanphamDTO(String masanpham, String tensp, String nsx, String img, int soluong, String maloai,int dongia) {
        this.masanpham = masanpham;
        this.tensp = tensp;
        this.nsx = nsx;
        this.img = img;
        this.soluong = soluong;
        this.maloai = maloai;
        this.dongia = dongia;
    }
    public sanphamDTO(sanphamDTO sp){
        this.masanpham = sp.masanpham;
        this.tensp = sp.tensp;
        this.nsx = sp.nsx;
        this.img = sp.img;
        this.soluong = sp.soluong;
        this.maloai = sp.maloai;
        this.dongia = sp.dongia;
    }
    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getDongia() {
        return dongia;
    }
    
    
}
