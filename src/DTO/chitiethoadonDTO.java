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
public class chitiethoadonDTO {
    String mahd;
    String masp;
    String mactsp;
    int sl;
    int dg;
    public chitiethoadonDTO(){}
    public chitiethoadonDTO(String a,String b,String m,int c,int d){
        mahd=a;
        masp=b;
        mactsp=m;
        sl=c;
        dg=d;
    }
    public chitiethoadonDTO(chitiethoadonDTO cthd){
        mahd=cthd.mahd;
        masp=cthd.masp;
        mactsp=cthd.mactsp;
        sl=cthd.sl;
        dg=cthd.dg;
    }
    public String getMahd() {
        return mahd;
    }

    public String getMactsp() {
        return mactsp;
    }

    public void setMactsp(String mactsp) {
        this.mactsp = mactsp;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getDg() {
        return dg;
    }

    public void setDg(int dg) {
        this.dg = dg;
    }
}
