/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import connect.myconnect;
import DTO.chitietgiamgiaDTO;
import DTO.giamgiaDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

/**
 *
 * @author ASUS
 */
public class chitietgiamgiaDAO {
    myconnect con=new myconnect();
    public chitietgiamgiaDAO(){
            con.connectdb();
    }
    public ArrayList<chitietgiamgiaDTO> docct(String a) throws SQLException{
        ArrayList<chitietgiamgiaDTO>ds=new ArrayList<chitietgiamgiaDTO>();
        String qry="select * from chitietgiamgia where `IdGgiá`='"+a+"'";
        con.st=con.conn.createStatement();
        con.rs=con.st.executeQuery(qry);
        while(con.rs.next()){
                chitietgiamgiaDTO ctgg=new chitietgiamgiaDTO();
                ctgg.setIdgg(con.rs.getString(1));
                ctgg.setIdsp(con.rs.getString(2));
                ctgg.setDiscount(Integer.parseInt(con.rs.getString(3)));
                ds.add(ctgg);
        }
        return ds;
    }
    public void them(chitietgiamgiaDTO ct){
        try{
            String qry="Insert into chitietgiamgia value ('"+ct.getIdgg()+"','"+ct.getIdsp()+"','"+ct.getDiscount()+"')";
            con.st=con.conn.createStatement();
            con.st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("Them ct that bai");
            System.out.println(e);
        }
    }
   public void xoa(String a,String b){
        try{
            String qry="Delete from chitietgiamgia where `IdGgiá`='"+a+"' AND Idsp='"+b+"'";
            con.st=con.conn.createStatement();
            con.st.executeUpdate(qry);
        }
        catch(Exception e){
            System.out.println("xoa ct that bai");
            System.out.println(e);
        }
    }
   public void sua(chitietgiamgiaDTO ct){
        try{
            String qry="Update chitietgiamgia set `%giảm`="+ct.getDiscount()+" where `IdGgiá`='"+ct.getIdgg()+"' AND Idsp='"+ct.getIdsp()+"'";
            con.st=con.conn.createStatement();
            con.st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("Sua du lieu bi loi!!");
            System.out.println(e);
        }
    }
   public giamgiaDTO getgg(String a){
       giamgiaDTO gg=new giamgiaDTO();
      try{
            String qry="select * from giamgia where `IdGgiá`='"+a+"'";
            con.st=con.conn.createStatement();
            con.rs=con.st.executeQuery(qry);
            while(con.rs.next()){
            gg.setMagg(a);
            gg.setTengg(con.rs.getString(1));
            gg.setNgbd(new SimpleDateFormat("yyyy-MM-dd").parse(con.rs.getString(2)));
            gg.setNgkt(new SimpleDateFormat("yyyy-MM-dd").parse(con.rs.getString(3)));}
      }catch(Exception ex){}
      return gg;
  }
}
