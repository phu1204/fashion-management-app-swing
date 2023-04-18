package DATA;


import connect.myconnect;
import DTO.giamgiaDTO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class giamgiaDAO {
    myconnect conn=new myconnect();
    
    public giamgiaDAO(){
        conn.connectdb();
    }
    public ArrayList<giamgiaDTO> docDao() throws SQLException, ParseException{
        String qry="select * from giamgia";
        conn.st=conn.conn.createStatement();
        conn.rs=conn.st.executeQuery(qry);
        ArrayList<giamgiaDTO> ds=new ArrayList<giamgiaDTO>();
        while(conn.rs.next()){
            giamgiaDTO gg=new giamgiaDTO();
            gg.setMagg(conn.rs.getString(1));
            gg.setTengg(conn.rs.getString(2));
            gg.setNgbd(new SimpleDateFormat("yyyy-MM-dd").parse(conn.rs.getString(3)));
            gg.setNgkt(new SimpleDateFormat("yyyy-MM-dd").parse(conn.rs.getString(4)));
            ds.add(gg);
        }
        return ds;
    }
    public void them(giamgiaDTO gg) throws SQLException{
        String qry="Insert into giamgia value ('"+gg.getMagg()+"','"+gg.getTengg()+"','"+gg.getNgbd()+"','"+gg.getNgkt()+"')";
       conn.st=conn.conn.createStatement();
        conn.st.executeUpdate(qry);
    }
    public void xoa(String ma){
        try{
            String qry="Delete from giamgia where `IdGgiá`='"+ma+"'";
            conn.st=conn.conn.createStatement();
            conn.st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("xoa du lieu that bai!!!");
            System.out.println(e);
        }
    }
  public  void sua(giamgiaDTO gg){
        try{
            String qry="Update giamgia set `Tên sự kiện`='"+gg.getTengg()+"',`Ngày bắt đầu`='"+gg.getNgbd()+"',`Ngày kết thúc`='"+gg.getNgkt()+"' where `IdGgiá` ='"+gg.getMagg()+"'";
            conn.st=conn.conn.createStatement();
            conn.st.executeUpdate(qry);
        }catch(Exception e){
            System.out.println("Sua du lieu bi loi!!");
            System.out.println(e);
        }
    }
}  
