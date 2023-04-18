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
public class phieunhaphangDTO {
    private String maphieunhap,manhanvien,manhacungcap,ngaynhap;
    private int tongtien;

    public phieunhaphangDTO() {
    }

    public phieunhaphangDTO(String maphieunhap, String manhanvien, String manhacungcap, String ngaynhap, int tongtien) {
        this.maphieunhap = maphieunhap;
        this.manhanvien = manhanvien;
        this.manhacungcap = manhacungcap;
        this.ngaynhap = ngaynhap;
        this.tongtien = tongtien;
    }

    public String getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(String maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(String manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
    
}
