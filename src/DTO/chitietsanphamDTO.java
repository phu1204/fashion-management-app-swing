/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class chitietsanphamDTO {
    private String machitietsanpham,masanpham,size,mausac;
    private int soluong;

    public chitietsanphamDTO() {
    }
    
    public chitietsanphamDTO(String machitietsanpham, String masanpham, String size, String mausac,int soluong) {
        this.machitietsanpham = machitietsanpham;
        this.masanpham = masanpham;
        this.size = size;
        this.mausac = mausac;
        
        this.soluong=soluong;
    }

    public String getMachitietsanpham() {
        return machitietsanpham;
    }

    public void setMachitietsanpham(String machitietsanpham) {
        this.machitietsanpham = machitietsanpham;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }
    
    
}
