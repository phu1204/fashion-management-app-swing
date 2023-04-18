/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.queryDTO;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Query {
    private  mySQLConnect mySQL = new mySQLConnect();
    //so luong khach den cua hang trong 1 khoang thoi gian
    
    //get doanh thu 
    public ArrayList<queryDTO> getTongDoanhThuInTime(String beginDate ,String endDate,String date1,String date2,int start,int end,String sumorcount,String name,String from){
        ArrayList<queryDTO> result= new ArrayList<>();
        try {
            String sql = "SELECT CONCAT("+date1+"(`Ngày`),"+"'/',"+date2+"(`Ngày`))"+ " AS month,"+sumorcount+"(`"+name+"`) AS tongtien FROM `"+from+"`"
                    +" Where `Ngày` <="+"'"+endDate+"'"
                    +" And `Ngày`>="+"'"+beginDate+"'"
                    +"GROUP BY "+date1+"(`Ngày`),"+" "
                    + date2+"(`Ngày`) "
                    + "ORDER BY "+date2+"(`Ngày`)"+","+date1+"(`Ngày`) ASC "
                    +"LIMIT "+start+","+end;
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
               
                String month = rs.getString("month");
                int tongtien = rs.getInt("tongtien");
                queryDTO query = new queryDTO(month, tongtien);
                result.add(query);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
        
    }
    public ArrayList<queryDTO> getTongDoanhThuInTime2(String beginDate ,String endDate,String date1,String date2,String date3,String sumorcount,String name,String from){
        ArrayList<queryDTO> result= new ArrayList<>();
        try {
            String sql = "SELECT CONCAT("
                    + ""+date1+"(`Ngày`),"
                    + "'/',"+date2+"(`Ngày`),"
                    + "'/',"+date3+"(`Ngày`)"+")"+ " AS month,"+sumorcount+"(`"+name+"`) AS tongtien FROM `"+from+"`"
                    +" Where `Ngày` <="+"'"+endDate+"'"
                    +" And `Ngày`>="+"'"+beginDate+"'"
                    +"GROUP BY "+date1+"(`Ngày`),"+date2+"(`Ngày`),"+date3+"(`Ngày`) "
                    + "ORDER BY "+date1+"(`Ngày`)"+","+date2+"(`Ngày`)"+","+date3+"(`Ngày`) ";
                    
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
               
                String month = rs.getString("month");
                int tongtien = rs.getInt("tongtien");
                queryDTO query = new queryDTO(month, tongtien);
                result.add(query);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
        
    }
    public ArrayList<queryDTO> getTongDoanhThuInTime1(String beginDate ,String endDate,String date1,int start,int end,String sumorcount,String name,String from){
        ArrayList<queryDTO> result= new ArrayList<>();
        try {
            String sql = "SELECT "+date1+"(`Ngày`) "+" AS month,"+sumorcount+"(`"+name+"`) AS tongtien FROM `"+from+"`"
                    +" Where `Ngày` <="+"'"+endDate+"'"
                    +" And `Ngày`>="+"'"+beginDate+"'"
                    +"GROUP BY "+date1+"(`Ngày`) "
                    +"Order by "+date1+" (`Ngày`) "
                    +" LIMIT "+start+","+end;
                    
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
               
                String month = rs.getString("month");
                int tongtien = rs.getInt("tongtien");
                queryDTO query = new queryDTO(month, tongtien);
                result.add(query);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
        
    }
    public ArrayList<queryDTO> getTopSomeThing(String id,String sumorcount,String name,String from){
        ArrayList<queryDTO> result= new ArrayList<>();
        try {
            String sql = "SELECT "+id+" AS id,"+sumorcount+"(`"+name+"`) AS count FROM `"+from+"`";
                    
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
               
                String month = rs.getString("id");
                int tongtien = rs.getInt("count");
                queryDTO query = new queryDTO(month, tongtien);
                result.add(query);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
        
    }
    
    public ArrayList<queryDTO> getTopSomeThingByGroupBy(String id,String sumorcount,String name,String from,String groupby,String orderby,String sort,int start,int end,String beginDate,String endDate){
        ArrayList<queryDTO> result= new ArrayList<>();
        try {
            String sql = "SELECT "+id+" AS id,"+sumorcount+"(`"+name+"`) AS count FROM `"+from+"`"
                    +"Where `Ngày` <="+"'"+endDate+"'"+" And "+"`Ngày`>="+"'"+beginDate+"'"
                    +" Group by "+groupby+" ORDER BY "+orderby+" "+sort+" Limit "+start+","+end;
                    
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
               
                String month = rs.getString("id");
                int tongtien = rs.getInt("count");
                queryDTO query = new queryDTO(month, tongtien);
                result.add(query);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
        
    }
    public ArrayList<queryDTO> getTopSomgthing2table(String id,String SumOrCount,String SoCname,String from1,String from2,String beginDate,String endDate,String joinName,String groupbyName,String sort,int start,int end){
        ArrayList<queryDTO> result= new ArrayList<>();
        try {
            String sql = "SELECT "+id+" as id ,"+SumOrCount+"(`"+SoCname+"`) as sl "
                    + "FROM "+from1+" as f1 "+","+from2+" as f2 "
                    +" Where "+"f2."+"`Ngày`<="+"'"+endDate+"'"+" And "+"f2."+"`Ngày`>="+"'"+beginDate+"'"+" "
                    + " and"
                    +" f1."+joinName+"="+" f2."+joinName
                    +" Group by "+groupbyName+" "
                    + "ORDER BY sl "+sort+" "
                    + "Limit "+start+","+end;
                    
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
               
                String month = rs.getString("id");
                int tongtien = rs.getInt("sl");
                queryDTO query = new queryDTO(month, tongtien);
                result.add(query);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
        
    }
    public ArrayList<queryDTO> getTopLoai(String sort,int start,int end,String beginDate,String endDate){
        ArrayList<queryDTO> result= new ArrayList<>();
        try {
            String sql = "SELECT sp.maloai as maloai,SUM(`Số lượng`) as sl"+" "
                    + "FROM sanpham as sp,chitiethoadon as cthd,hoadon as hd"+" "
                    + "WHERE sp.masanpham = cthd.Idsp"+" "
                    + "and hd.Idhd=cthd.Idhd"+" "
                    + "and hd.`Ngày`>="+"'"+beginDate+"'"+" "
                    + "and hd.`Ngày`<="+"'"+endDate+"'"+" "
                    + "GROUP BY sp.maloai"+" "
                    + "ORDER BY `Số lượng` "+sort+" "
                    + "LIMIT "+start+","+end;
                    
            System.out.println(sql);
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
               
                String month = rs.getString("maloai");
                int tongtien = rs.getInt("sl");
                queryDTO query = new queryDTO(month, tongtien);
                result.add(query);
                
            }
            rs.close();
            mySQL.disConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
        
    }
    
    
   
    public ArrayList<queryDTO> getDoanhThu(String Sdate,String Edate,String time1,String time2){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTongDoanhThuInTime(Sdate, Edate, time1,time2, 0, 200, "SUM", "Tổng tiền", "hoadon");
        return query;
    }
    public ArrayList<queryDTO> getDoanhThu1(String Sdate,String Edate,String time1){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTongDoanhThuInTime1(Sdate, Edate, time1, 0, 12, "SUM", "Tổng tiền", "hoadon");
        return query;
    }
    public ArrayList<queryDTO> getDoanhThuNgay(String Sdate,String Edate){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTongDoanhThuInTime2(Sdate, Edate, "year", "month", "day", "sum", "Tổng tiền", "hoadon");
        return query;
    }
    public ArrayList<queryDTO> getSoLuongHoaDon(String Sdate,String Edate,String time){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTongDoanhThuInTime(Sdate, Edate, time,time, 0, 12, "count", "Idhd", "hoadon");
        return query;
    }
    public ArrayList<queryDTO> getSoLuongNhanVien(){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTopSomeThing("Idnv","count","Idnv","nhanvien");
        return query;
    }
    public ArrayList<queryDTO> getSoLuongKhachHang(){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTopSomeThing("Idkhach","count","Idkhach","khach");
        return query;
    }
    public ArrayList<queryDTO> getTopKhachHang(String sort,int start,int end,String beginDate,String endDate){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTopSomeThingByGroupBy("Idkhach", "SUM", "Tổng tiền", "hoadon", "Idkhach", "count",sort, start, end,beginDate,endDate);
        return query;
    }
    public ArrayList<queryDTO> getTopNhanVien(String sort,int start,int end,String beginDate,String endDate){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTopSomeThingByGroupBy("Idnv", "COUNT", "Idnv", "hoadon", "Idnv", "count",sort, start, end,beginDate,endDate);
        return query;
    }
    public ArrayList<queryDTO> getTopSanpham(String sort , int start , int end,String beginDate,String endDate){
        Query q = new Query();
        ArrayList<queryDTO> query = q.getTopSomgthing2table("Idsp", "Sum", "Số lượng", "chitiethoadon", "hoadon", beginDate, endDate,"Idhd","Idsp", sort, start,end);
        return query;
    }
    
}
