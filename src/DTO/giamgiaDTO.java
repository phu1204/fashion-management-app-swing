/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class giamgiaDTO {
    String magg;
    String tengg;
    Date ngbd;
    Date ngkt;
    public giamgiaDTO(){}
    public giamgiaDTO(String magg,String tengg,Date ngbd,Date ngkt){
    this.magg=magg;
    this.tengg=tengg;
    this.ngbd=ngbd;
    this.ngkt=ngkt;
    }
    public giamgiaDTO(giamgiaDTO gg){
    magg=gg.getMagg();
    tengg=gg.getTengg();
    ngbd=gg.getNgbd();
    ngkt=gg.getNgkt();
    }

    public String getMagg() {
        return magg;
    }

    public void setMagg(String magg) {
        this.magg = magg;
    }

    public String getTengg() {
        return tengg;
    }

    public void setTengg(String tengg) {
        this.tengg = tengg;
    }

    public Date getNgbd() {
        return ngbd;
    }

    public void setNgbd(Date ngbd) {
        this.ngbd = ngbd;
    }

    public Date getNgkt() {
        return ngkt;
    }

    public void setNgkt(Date ngkt) {
        this.ngkt = ngkt;
    }
    public boolean checkng(String gg) throws ParseException{
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    Date ht=sdf.parse(gg);
    if(ht.before(ngbd) || ht.after(ngkt)) return false;
        return true;
    }
}
