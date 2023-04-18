/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class chitietgiamgiaDTO {
    String Idgg=null;
    String Idsp=null;
    int discount=0;
    public chitietgiamgiaDTO(){}
    public chitietgiamgiaDTO(String Idgg,String Idsp,int discount){
    this.Idgg=Idgg;
    this.Idsp=Idsp;
    this.discount=discount;
    }
    public chitietgiamgiaDTO(chitietgiamgiaDTO gg){
    Idgg=gg.getIdgg();
    Idsp=gg.getIdsp();
    discount=gg.getDiscount();
    }
    public String getIdgg() {
        return Idgg;
    }

    public void setIdgg(String Idgg) {
        this.Idgg = Idgg;
    }

    public String getIdsp() {
        return Idsp;
    }

    public void setIdsp(String Idsp) {
        this.Idsp = Idsp;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
