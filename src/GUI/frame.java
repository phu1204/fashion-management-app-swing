/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import DTO.*;
import BUS.*;
import DATA.*;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lenovo
 */
public class frame extends javax.swing.JFrame {
    Boolean displayUserLabelFlag = false;
    public static String userlogin=null;
    private DefaultTableModel model1,model2,modelloai,modelnhaphang,modelctnh,modelnhacungcap,modelnhanvien,modelhd,modelcthd,modelgg,modelctgg,modelkhach,modelcthdsell;

    
    //public static String USER;
    //private DefaultTableModel model1,model2,modelloai,modelnhaphang,modelctnh,modelnhacungcap;
    sanphamBUS spBUS = new sanphamBUS();
    QLNVBUS bus = new QLNVBUS();
    sanphamDAO spDAO = new sanphamDAO();
    phieunhaphangDAO nhapDAO = new phieunhaphangDAO();
    chitietsanphamBUS ctspBUS = new chitietsanphamBUS();
    loaiBUS loaiBUS = new loaiBUS();
    nhacungcapBUS nccBUS = new nhacungcapBUS();
    phieunhaphangBUS nhapBUS = new phieunhaphangBUS();
    chitietphieunhapBUS chitietnhapBUS = new chitietphieunhapBUS();
    chitietsanphamDAO ctspDAO = new chitietsanphamDAO();
    ArrayList<chitietphieunhapDTO> stackchitiet = new ArrayList<>();
    
    /**
     * Creates new form frame
     */
    
    public frame(String a) throws Exception {
        userlogin=a;
        if(userlogin.equals("")){
        new Login1().setVisible(true);
        this.dispose();
        }
        setUndecorated(true);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        logoLabel.setIcon(icon("logo", 50, 50));
        nhanvienbackground.setIcon(icon("user",100,100));
        khachhangbackground.setIcon(icon("user",100,100));
       
     
        if(Login1.typehienhanh==1){
            staffLabel.setEnabled(false);
       }
        //jTextField1.setUI(new CustomTextField("This is a hint...", Color.gray));
        //productLeftPanel.setVisible(false);
        //**************San pham***************
        creatTableSanPham();
        creatTableChiTietSanPham();
        creatTableLoaiSanPham();
        creatTableNhaCungCap();
        creatTableChiTietPhieuNhapHang();
        creatTablePhieuNhapHang();
        sanphamTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if(sanphamTable.getSelectedRow() != -1) {
               // remove selected row from the model
                   int row = sanphamTable.getSelectedRow();
                   String value1 = sanphamTable.getModel().getValueAt(row, 0).toString();
                   String value2 = sanphamTable.getModel().getValueAt(row, 1).toString();
                   String value3 = sanphamTable.getModel().getValueAt(row, 2).toString();
                   String value4 = sanphamTable.getModel().getValueAt(row, 3).toString();
                   String value5 = sanphamTable.getModel().getValueAt(row, 4).toString();
                  String value6 = sanphamTable.getModel().getValueAt(row, 5).toString();
                   String value7 = sanphamTable.getModel().getValueAt(row, 6).toString();
                   
                   //maloaiTf.setText(value6);
                   maloaiBox.setSelectedItem("");
                   masanphamTf.setText(value1);
                   masanpham2Tf.setText(value1);
                   tensanphamTf.setText(value2);
                   nsxTf.setText(value3);
                   soluongTf.setText(value5);
                   dongiaTf.setText(value7);
                   
                   model2.setRowCount(0);
                   outModel2(model2, ctspBUS.getList(),value1);
                   
   
            }
        }
        });
        nhaphangtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if(nhaphangtable.getSelectedRow() != -1) {
                try {
                    // remove selected row from the model
                    int row = nhaphangtable.getSelectedRow();
                    String value1 = nhaphangtable.getModel().getValueAt(row, 0).toString();
                    String value2 = nhaphangtable.getModel().getValueAt(row, 1).toString();
                    String value3 = nhaphangtable.getModel().getValueAt(row, 2).toString();
                    String value4 = nhaphangtable.getModel().getValueAt(row, 3).toString();
                    String value5 = nhaphangtable.getModel().getValueAt(row, 4).toString();
                    
                    
                    //maloaiTf.setText(value6);
                    
                    maphieunhapTf.setText(value1);
                    maphieunhapTf2.setText(value1);
                    manhanvienTf.setText(value2);
                    manhacungcapBox.setSelectedItem("");
                    //SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value4);
                    tongtienTf.setText(value5);
                    
                    modelctnh.setRowCount(0);
                    outModelChiTietPhieuNhapHang(modelctnh, chitietnhapBUS.getList(),value1);
                } catch (ParseException ex) {
                    Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
   
            }
        }
        });
        loaitable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if(loaitable.getSelectedRow() != -1) {
               // remove selected row from the model
                   int row = loaitable.getSelectedRow();
                   String value1 = loaitable.getModel().getValueAt(row, 0).toString();
                   String value2 = loaitable.getModel().getValueAt(row, 1).toString();
                   
                   //maloaiTf.setText(value6);
                   
                   maloaiTf_loaisanphamPanel.setText(value1);
                   tenloaiTf_loaisanphamPanel.setText(value2);
                   
                   
                   //modelloai.setRowCount(0);
                   //outModelLoai(modelloai, loaiBUS.getList());
                   
   
            }
        }
        });
        nhacungcaptable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if(nhacungcaptable.getSelectedRow() != -1) {
               // remove selected row from the model
                   int row = nhacungcaptable.getSelectedRow();
                   String value1 = nhacungcaptable.getModel().getValueAt(row, 0).toString();
                   String value2 = nhacungcaptable.getModel().getValueAt(row, 1).toString();
                   String value3 = nhacungcaptable.getModel().getValueAt(row, 2).toString();
                   String value4 = nhacungcaptable.getModel().getValueAt(row, 3).toString();
                   
                   //maloaiTf.setText(value6);
                   
                   manhacungcapTf_ncc.setText(value1);
                   tennhacungcapTf_ncc.setText(value2);
                   diachiTf_ncc.setText(value3);
                   sdtTf_ncc.setText(value4);
                   
                   
                   //modelloai.setRowCount(0);
                   //outModelLoai(modelloai, loaiBUS.getList());
                   
   
            }
        }
        });
        detailproducttable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if(detailproducttable.getSelectedRow() != -1) {
               // remove selected row from the model
                   int row = detailproducttable.getSelectedRow();
                   String value1 = detailproducttable.getModel().getValueAt(row, 0).toString();
                   String value2 = detailproducttable.getModel().getValueAt(row, 1).toString();
                   String value3 = detailproducttable.getModel().getValueAt(row, 2).toString();
                   String value4 = detailproducttable.getModel().getValueAt(row, 3).toString();
                   String value5 = detailproducttable.getModel().getValueAt(row, 4).toString();
                   
                   machitietsanphamTf.setText(value1);
                   masanpham2Tf.setText(value2);
                   sizeBox.setSelectedItem(value3);
                   colorBox.setSelectedItem(value4);
                   soluong2Tf.setText(value5);

   
            }
        }
        });
        chitietnhaphangtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if(chitietnhaphangtable.getSelectedRow() != -1) {
               // remove selected row from the model
                   int row = chitietnhaphangtable.getSelectedRow();
                   String value1 = chitietnhaphangtable.getModel().getValueAt(row, 0).toString();
                   String value2 = chitietnhaphangtable.getModel().getValueAt(row, 1).toString();
                   String value3 = chitietnhaphangtable.getModel().getValueAt(row, 2).toString();
                   String value4 = chitietnhaphangtable.getModel().getValueAt(row, 3).toString();
                   String value5 = chitietnhaphangtable.getModel().getValueAt(row, 4).toString();
                   
                   //maphieunhapTf2.setText(value1);
                   masanphamTf_ctnh.setText(value2);
                   soluongTf_ctnh.setText(value4);
                   dongiaTf_ctnh.setText(value3);

   
            }
        }
        });
        
        
        
        
        
        
        
        setVisibleProduct();
        setVisibleMenu();
        setVisibleSanPham();
       decorateButton();
       
    }
    
    public Boolean notExit(String o){
        if(!"".equals(o)){
            return false;
        } else {
        }
        return true;
         
    }
    public void cleanViewNCC(){
        manhacungcapTf_ncc.setText("");
        tennhacungcapTf_ncc.setText("");
        diachiTf_ncc.setText("");
        sdtTf_ncc.setText("");
        
        nhacungcaptable.clearSelection();
    }
    public void cleanViewLoai(){
        maloaiTf_loaisanphamPanel.setText("");
        tenloaiTf_loaisanphamPanel.setText("");
        
        nhacungcaptable.clearSelection();
    }
    public void cleanViewNhaphang(){
        maphieunhapTf.setText("");
        maphieunhapTf2.setText("");
        manhanvienTf.setText("");
        tongtienTf.setText("");
        masanphamTf_ctnh.setText("");
        soluongTf_ctnh.setText("");
        dongiaTf_ctnh.setText("");
    }
    public void cleanViewChiTietNhapHang(){
        maphieunhapTf2.setText("");
        masanphamTf_ctnh.setText("");
        soluongTf_ctnh.setText("");
        dongiaTf_ctnh.setText("");
    }
    public void cleanViewSanPham(){
        masanphamTf.setText("");
        tensanphamTf.setText("");
        soluongTf.setText("");
        dongiaTf.setText("");
        nsxTf.setText("");
        machitietsanphamTf.setText("");
        masanpham2Tf.setText("");
        soluong2Tf.setText("");
    }
    public void cleanViewCTSP(){
        machitietsanphamTf.setText("");
        masanpham2Tf.setText("");
        soluong2Tf.setText("");
    }
    public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
    }
    public sanphamDTO getTxtSanPham(){
        
        String masanpham = masanphamTf.getText();
        String tensp = tensanphamTf.getText();
        String nsx = nsxTf.getText();
        String maloaiString = maloaiBox.getSelectedItem().toString();
        String maloai[] = maloaiString.split("/");
        
        String img = "blank.png";
        
        String soluong1= soluongTf.getText();
        String dongia1 = dongiaTf.getText();
        int soluong = 0,dongia = 0;
        if(isNumeric(dongia1)){
            
            dongia =Integer.parseInt(dongia1);
        }else{
            dongia = -1;
            soluong = 0;
        }
        if(isNumeric(soluong1)){
            soluong = Integer.parseInt(soluong1);
        }
        
        
        
        
        sanphamDTO sp = new sanphamDTO(masanpham,tensp,nsx,img,soluong,maloai[0],dongia);
        return sp;
    }
    public void importSoluong(String sp,int soluong){
        sanphamDTO spDTO = spBUS.getSP(sp);
        
        if(spDTO==null){
            return;
        }
        spDTO.setSoluong(spDTO.getSoluong()+soluong);
        spDAO.setSoLuong(spDTO);
        
        return;
    }
    public void exportSoluong(String sp,int soluong){
        sanphamDTO spDTO = spBUS.getSP(sp);
        
        if(spDTO==null){
            return;
        }
        spDTO.setSoluong(spDTO.getSoluong()-soluong);
        System.out.println(spDTO.getSoluong()-soluong);
        System.out.println("GUI.frame.exportSoluong()");
        if(spDTO.getSoluong()<0){
            return;
        }
        spDAO.setSoLuong(spDTO);
        
        return;
    }
    public chitietsanphamDTO getTxtChiTietSanPham(){
        String masanpham = masanpham2Tf.getText();
        String machitietsanpham = machitietsanphamTf.getText();
        String soluong = soluong2Tf.getText();
        String size = sizeBox.getSelectedItem().toString();
        String color = colorBox.getSelectedItem().toString();
        int soluong1 = 0;
        if(isNumeric(soluong)){
            soluong1 =Integer.parseInt(soluong);
        }
        else soluong1 = -1;
        
        chitietsanphamDTO ctsp = new chitietsanphamDTO(machitietsanpham, masanpham, size, color, soluong1);
        return ctsp;
        
    }
    public phieunhaphangDTO getTxtPhieuNhapHang(){
        String maphieunhap = maphieunhapTf.getText();
        String manhanvien = manhanvienTf.getText();
        String manhacungcapString = manhacungcapBox.getSelectedItem().toString();
        String manhacungcap[] = manhacungcapString.split("/");
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String ngaynhap = formatter.format(date);  
        //System.out.println(ngaynhap);
        
        phieunhaphangDTO pnh = new phieunhaphangDTO(maphieunhap, manhanvien, manhacungcap[0], ngaynhap, 0);
        return pnh;
    }
    public chitietphieunhapDTO getTxtChiTietPhieuNhap(){
        String maphieunhap = maphieunhapTf2.getText();
        String masanpham = masanphamTf_ctnh.getText();
        int soluong = Integer.parseInt(soluongTf_ctnh.getText());
        int dongia = Integer.parseInt(dongiaTf_ctnh.getText());
        int thanhtien = soluong*dongia;
        chitietphieunhapDTO ctnh = new chitietphieunhapDTO(maphieunhap, masanpham, soluong, dongia, thanhtien);
        return ctnh;
    }
    public loaiDTO getTxtLoai(){
        String maloai = maloaiTf_loaisanphamPanel.getText();
        String tenloai = tenloaiTf_loaisanphamPanel.getText();
        loaiDTO loai = new loaiDTO(maloai, tenloai);
        return loai;
    }
    public nhacungcapDTO getTxtNhaCungCap(){
        String manhacungcap = manhacungcapTf_ncc.getText();
        String tennhacungcap = tennhacungcapTf_ncc.getText();
        String diachi = diachiTf_ncc.getText();
        String sdt = sdtTf_ncc.getText();
        nhacungcapDTO ncc = new nhacungcapDTO(manhacungcap, tennhacungcap, diachi, sdt);
        return ncc;
    }
    public void addSanPhamToTable() throws Exception{
        sanphamDTO sp = new sanphamDTO();
        sp = getTxtSanPham();
        
        
        if(notExit(sp.getMasanpham())){
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập mã sản phẩm");
            return;
        }
        else if(spBUS.checkMasp(sp.getMasanpham())){
            JOptionPane.showMessageDialog(rootPane, "Mã sản phẩm tồn tại");
            return;
        }
        else if(notExit(sp.getMaloai())||notExit(sp.getTensp())){
            JOptionPane.showMessageDialog(rootPane, "Nhập đầy đủ thông tin");
            return;
        }
        else if(sp.getDongia()<0){
            JOptionPane.showMessageDialog(rootPane, "Đơn giá nhập số lượng");
            return;
        }
        
        
        
            spBUS.addSP(sp);
            outModel1(model1,spBUS.getList());
            cleanViewSanPham();
            
    }
    public boolean checkSoLuongNhapChiTietSP(chitietsanphamDTO ctsp){
        if(ctspBUS.getTongSoLuongByIdSP(ctsp.getMasanpham())+ctsp.getSoluong()>spBUS.getSoLuongSP(ctsp.getMasanpham())){
            JOptionPane.showMessageDialog(rootPane, "Nhập quá số lượng sản phẩm hiện có");
            
            return false;
        }
        return true;
    }
    public void addChiTietSanPhamToTable() throws Exception{
        chitietsanphamDTO ctsp = getTxtChiTietSanPham();
        if(ctsp.getSoluong()<0){
            JOptionPane.showMessageDialog(rootPane, "Số lượng nhập chữ số!!!");
            return;
        }
        if(!checkSoLuongNhapChiTietSP(ctsp)){
            return;
        }
        
        int row = sanphamTable.getSelectedRow();
        String value1 = sanphamTable.getModel().getValueAt(row, 0).toString();
        ctspBUS.addCTSP(ctsp);
        //outModel2(model2, ctspBUS.getList(), value1);
        ctspBUS.listCTSP();
        outModel2(model2, ctspBUS.getList(), value1);
        //int n=getSoLuongByMaSanPham(ctspBUS.getList(),ctsp.getMasanpham());
        //setSoLuong(spBUS.getList(),n,ctsp.getMasanpham());
        //sanphamTable.setValueAt(n,row,4);
            //outModel2(model2, ctspBUS.getList(), value1);
            
            
            
    }
    
    public void addChiTietPhieuNhapToTable() throws Exception{
        chitietphieunhapDTO ctpn = getTxtChiTietPhieuNhap();
        if(notExit(ctpn.getMasanpham())){
            return;
        }
        else if(!spBUS.checkMasp(ctpn.getMasanpham())){
            JOptionPane.showMessageDialog(rootPane,"Mã sản phẩm không tồn tại!!!");
            return;
        }
       
        int row = nhaphangtable.getSelectedRow();
        String value1 = nhaphangtable.getModel().getValueAt(row, 0).toString();
        chitietnhapBUS.addCTPNH(ctpn);
        outModelChiTietPhieuNhapHang(modelctnh, chitietnhapBUS.getList(), value1);
        chitietnhapBUS.listCTPNH();
        int n= getTongTienByMaPhieuNhap(chitietnhapBUS.getList(), ctpn.getMaphieunhap());
        setTongTien(nhapBUS.getList(), n, ctpn.getMaphieunhap());
        tongtienTf.setEnabled(true);
        tongtienTf.setText(String.valueOf(n));
        tongtienTf.setEnabled(false);
        nhaphangtable.setValueAt(n, row, 4);
        sanphamDTO sp=spBUS.getSP(ctpn.getMasanpham());
        importSoluong(masanphamTf_ctnh.getText(),Integer.parseInt(soluongTf_ctnh.getText()));
        spBUS.setSP(sp);
        creatTableSanPham();
    }
    public void addPhieuNhapHangToTable() throws Exception{
        phieunhaphangDTO ctpn = getTxtPhieuNhapHang();
        
        if(notExit(ctpn.getManhacungcap())||notExit(ctpn.getManhanvien())||notExit(ctpn.getNgaynhap())){
            JOptionPane.showMessageDialog(rootPane, "Nhập đầy đủ thông tin");
            return ;
        }
        else if(nhapBUS.checkMaPhieuNhap(ctpn.getMaphieunhap())){
            JOptionPane.showMessageDialog(rootPane, "Mã phiếu nhập đã tồn tại");
            return ;
        }
        
        nhapBUS.addPNH(ctpn);
        nhapBUS.listPNH();
        outModelPhieuNhapHang(modelnhaphang, nhapBUS.getList());
    }
    public void addLoaiToTable() throws Exception{
        loaiDTO loai = getTxtLoai();
        if(notExit(loai.getMaloai())){
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập mã loại");
            return;
        }
        else if(loaiBUS.checkMasp(loai.getMaloai())){
            JOptionPane.showMessageDialog(rootPane, "Mã loại đã tồn tại");
            return;
        }
        else if(notExit(loai.getTenloai())){
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập tên loại");
            return;
        }
        
        loaiBUS.addLoai(loai);
        outModelLoai(modelloai, loaiBUS.getList());
    }
    public void addNCCToTable() throws Exception{
        nhacungcapDTO ncc = getTxtNhaCungCap();
        
        if(notExit(ncc.getManhacungcap())){
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập mã nhà cung cấp");
            return;
        }
        else if(nccBUS.checkMasp(ncc.getManhacungcap())){
            JOptionPane.showMessageDialog(rootPane, "Mã nhà cung cấp đã tồn tại");
            return;
        }
        else if(notExit(ncc.getTennhacungcap())||notExit(ncc.getDiachi())){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        nccBUS.addNcc(ncc);
        outModelNCC(modelnhacungcap, nccBUS.getList());
        cleanViewNCC();
    }
    public void deleteSanPhamFromTable() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
        
        if(sanphamTable.getSelectedRow() != -1 && i==0) {
            
            int[] selectedRows = sanphamTable.getSelectedRows();
            String index;
            for (int t = selectedRows.length - 1; t >= 0; t--) {
                
                index = (String) model1.getValueAt(selectedRows[t], 0);
                model1.removeRow(selectedRows[t]);
                System.out.println(index);
                spBUS.deleteSP(index);
            }
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
        return;
    }
    public void deleteLoaiFromTable() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
        
        if(loaitable.getSelectedRow() != -1 && i==0) {
            
            int[] selectedRows = loaitable.getSelectedRows();
            String index;
            for (int t = selectedRows.length - 1; t >= 0; t--) {
                
                index = (String) modelloai.getValueAt(selectedRows[t], 0);
                modelloai.removeRow(selectedRows[t]);
                System.out.println(index);
                loaiBUS.deleteLoai(index);
            }
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
        return;
    }
    public void deleteNCCFromTable() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
        
        if(nhacungcaptable.getSelectedRow() != -1 && i==0) {
            
            int[] selectedRows = nhacungcaptable.getSelectedRows();
            String index;
            for (int t = selectedRows.length - 1; t >= 0; t--) {
                exportSoluong(masanphamTf_ctnh.getText(),Integer.parseInt(dongiaTf_ctnh.getText()));
                index = (String) modelnhacungcap.getValueAt(selectedRows[t], 0);
                modelnhacungcap.removeRow(selectedRows[t]);
                System.out.println(index);
                nccBUS.deleteSP(index);
            }
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
        cleanViewNCC();
        return;
    }
    public void deletePhieuNhapHangFromTable() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
        
        if(nhaphangtable.getSelectedRow() != -1 && i==0) {
            
            int[] selectedRows = nhaphangtable.getSelectedRows();
            String index;
            for (int t = selectedRows.length - 1; t >= 0; t--) {
                
                index = (String) modelnhaphang.getValueAt(selectedRows[t], 0);
                modelnhaphang.removeRow(selectedRows[t]);
                //System.out.println(index);
                for(chitietphieunhapDTO ct:chitietnhapBUS.getCTPNBYIdPN(maphieunhapTf.getText())){
                    exportSoluong(ct.getMasanpham(), ct.getSoluong());
                }
                nhapBUS.deletePNH(index);
                chitietnhapBUS.deleteCTPNH(index);
                //outModelPhieuNhapHang(modelnhaphang, nhapBUS.getList());
                outModelChiTietPhieuNhapHang(modelctnh, chitietnhapBUS.getList(), "");
                spBUS.listSP();
                outModel1(model1, spBUS.getList());
            }
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
        return;
    }
    public void deleteChiTietPhieuNhapHangFromTable(){
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
        
        if(chitietnhaphangtable.getSelectedRow() != -1 && i==0) {
            
            int[] selectedRows = chitietnhaphangtable.getSelectedRows();
            String index1,index2;
            for (int t = selectedRows.length - 1; t >= 0; t--) {
                index1 = (String) modelctnh.getValueAt(selectedRows[t], 0);
                System.out.println(index1);
                index2 = (String) modelctnh.getValueAt(selectedRows[t], 1);
                modelctnh.removeRow(selectedRows[t]);
                System.out.println(index2);
                chitietnhapBUS.deleteCTPNHByProduct(index2);
                //outModelChiTietPhieuNhapHang(modelctnh, chitietnhapBUS.getList(), index);
                System.out.println(soluongTf_ctnh.getText());
                exportSoluong(masanphamTf_ctnh.getText(), Integer.parseInt(soluongTf_ctnh.getText()));
                spBUS.listSP();
                outModel1(model1, spBUS.getList());
                int n= getTongTienByMaPhieuNhap(chitietnhapBUS.getList(), index1);
                System.out.println(n);
                if(n==0){
                    nhapDAO.setTongTien(index1, 0);
                }
                else{
                    setTongTien(nhapBUS.getList(), n, index1);
                }
                
                nhapBUS.listPNH();
                outModelPhieuNhapHang(modelnhaphang, nhapBUS.getList());
            }
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
        return;
    }
    public void deleteChiTietSanPhamFromTable() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
        
        if(detailproducttable.getSelectedRow() != -1 && i==0) {
            
            int[] selectedRows = detailproducttable.getSelectedRows();
            String index;
            for (int t = selectedRows.length - 1; t >= 0; t--) {
                
                index = (String) model2.getValueAt(selectedRows[t], 0);
                model2.removeRow(selectedRows[t]);
                System.out.println(index);
                ctspBUS.deleteCTSP(index);
            }
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
        
        
        return;
    }
    public void editSanPham() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin","Alert",JOptionPane.YES_NO_OPTION);
        
        if(sanphamTable.getSelectedRow() != -1 && i==0) {
            sanphamDTO sp = new sanphamDTO();
            sp=getTxtSanPham();
            
            if(notExit(sp.getMaloai())){
                JOptionPane.showMessageDialog(rootPane, "Chọn mã loại!!");
                return;
            }
            else if(sp.getDongia()<0){
                JOptionPane.showMessageDialog(rootPane, "Đơn giá nhập chữ số");
                return;
            }
            spBUS.setSP(sp);
            outModel1(model1, spBUS.getList());
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần sửa");
        }
        return;
     
        
    }
    public void editChiTietSanPham() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin","Alert",JOptionPane.YES_NO_OPTION);
        
        if(detailproducttable.getSelectedRow() != -1 && i==0) {
            chitietsanphamDTO ctsp = new chitietsanphamDTO();
            ctsp=getTxtChiTietSanPham();
            if(ctsp.getSoluong()<0){
            JOptionPane.showMessageDialog(rootPane, "Số lượng nhập chữ số!!!");
            return;
            }
            if(!checkSoLuongNhapChiTietSP(ctsp)){
            return;
            }   
           
            int row = detailproducttable.getSelectedRow();
            model2.setValueAt(ctsp.getMachitietsanpham(), row, 0);
            model2.setValueAt(ctsp.getMasanpham(), row, 1);
            model2.setValueAt(ctsp.getSize(), row, 2);
            
            model2.setValueAt(ctsp.getMausac(), row, 3);
            model2.setValueAt(ctsp.getSoluong(), row, 4);
            
            ctspBUS.setSP(ctsp);
            
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần sửa");
        }
        return;
     
        
    }
    public void editLoai() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin","Alert",JOptionPane.YES_NO_OPTION);
        
        if(loaitable.getSelectedRow() != -1 && i==0) {
            loaiDTO loai = new loaiDTO();
            loai=getTxtLoai();
            int row = loaitable.getSelectedRow();
            modelloai.setValueAt(loai.getMaloai(), row, 0);
            modelloai.setValueAt(loai.getTenloai(), row, 1);
            
            
            loaiBUS.setSP(loai);
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần sửa");
        }
        return;
     
        
    }
    public void editNCC() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin","Alert",JOptionPane.YES_NO_OPTION);
        
        if(nhacungcaptable.getSelectedRow() != -1 && i==0) {
            nhacungcapDTO ncc = new nhacungcapDTO();
            ncc=getTxtNhaCungCap();
          
            nccBUS.setSP(ncc);
            outModelNCC(modelnhacungcap, nccBUS.getList());
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần sửa");
        }
        cleanViewNCC();
        return;
     
        
    }
    public void editPhieuNhapHang() throws Exception{
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin","Alert",JOptionPane.YES_NO_OPTION);
        
        if(nhaphangtable.getSelectedRow() != -1 && i==0) {
            phieunhaphangDTO pnh = new phieunhaphangDTO();
            pnh=getTxtPhieuNhapHang();
            
            //modelnhaphang.setValueAt(pnh.getMaphieunhap(), row, 0);
            //modelnhaphang.setValueAt(pnh.getManhanvien(), row, 1);
            //modelnhaphang.setValueAt(pnh.getManhacungcap(), row, 2);
            //modelnhaphang.setValueAt(pnh.getNgaynhap(), row, 3);
            //modelnhaphang.setValueAt(pnh.getTongtien(), row, 4);
            
            nhapBUS.setPNH(pnh);
            outModelPhieuNhapHang(modelnhaphang, nhapBUS.getList());
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần sửa");
        }
        return;
     
        
    }
    public void editChiTietNhapHang(){
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin","Alert",JOptionPane.YES_NO_OPTION);
        
        if(chitietnhaphangtable.getSelectedRow() != -1 && i==0) {
            chitietphieunhapDTO ctpn = new chitietphieunhapDTO();
            ctpn=getTxtChiTietPhieuNhap();
            int row = nhaphangtable.getSelectedRow();
            
            String value1 = nhaphangtable.getModel().getValueAt(row, 0).toString();
            
            //modelctnh.setValueAt(ctpn.getMaphieunhap(), row, 0);
            //modelctnh.setValueAt(ctpn.getMasanpham(), row, 1);
            //modelctnh.setValueAt(ctpn.getDongia(), row, 2);
            //modelctnh.setValueAt(ctpn.getSoluong(), row, 3);
            //modelctnh.setValueAt(ctpn.getThanhtien(), row, 4);
            chitietnhapBUS.setCTPNH(ctpn);
            exportSoluong(masanphamTf_ctnh.getText(), Integer.parseInt(soluongTf_ctnh.getText()));
                spBUS.listSP();
                outModel1(model1, spBUS.getList());
            outModelChiTietPhieuNhapHang(modelctnh, chitietnhapBUS.getList(), value1);
            int n = getTongTienByMaPhieuNhap(chitietnhapBUS.getList(), value1);
            setTongTien(nhapBUS.getList(), n, value1);
            
            nhaphangtable.setValueAt(n, row, 4);
            
        }
        else if(i==0){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần sửa");
        }
        return;
     
        
    }
    public DefaultTableModel header(String[] array){
        //DefaultTableModel modelS = new DefaultTableModel();
        Vector header = new Vector();
        for(int i=0;i<array.length;i++){
            header.add(array[i]);
        }
        DefaultTableModel modelS = new DefaultTableModel(header,0);
        modelS.setRowCount(0);
        
        return modelS;
    }
    public void creatTableSanPham(){
        sanphamTable.setFocusable(false);
        sanphamTable.setIntercellSpacing(new Dimension(0,0));     
        sanphamTable.setRowHeight(100);
        sanphamTable.setShowVerticalLines(false);              
        sanphamTable.getTableHeader().setOpaque(true);
        sanphamTable.setFillsViewportHeight(true);
        sanphamTable.getTableHeader().setPreferredSize(new Dimension(50, 50));
        sanphamTable.setSelectionBackground(new Color(153,204,255)); 
        spBUS.listSP();
        String arr[]={"Mă Sản Phẩm","Tên Sản Phẩm","Nhà sản xuất","Ảnh","Số lượng","Mã loại","Đơn giá"};
        model1= header(arr);
        sanphamTable.setModel(model1);
        //set row sorter
        //TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model1);
        //sanphamTable.setRowSorter(rowSorter);
        
        //khởi tạo BUS san pham
        //spBUS.listSP();
        //xuat table
        outModel1(model1, spBUS.getList());
    }
    public void creatTableChiTietSanPham(){
        
        ctspBUS.listCTSP();
        String arr[]={"Mă Chi Tiết Sản Phẩm","Mã Sản Phẩm","Size","Color","Số lượng"};
        //DefaultTableModel model1= header(arr);
        model2 = header(arr);
        detailproducttable.setModel(model2);
        //set row sorter
        //TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model1);
        //sanphamTable.setRowSorter(rowSorter);
        
        //khởi tạo BUS san pham
        //spBUS.listSP();
        //xuat table
        //outModel2(model2, ctspBUS.getList(),"1");
    }
    public void creatTableLoaiSanPham(){
        
        loaiBUS.listLoai();
        String arr[]={"Mă Loại","Tên loại"};
        //DefaultTableModel model1= header(arr);
        modelloai = header(arr);
        loaitable.setModel(modelloai);
        //set row sorter
        //TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model1);
        //sanphamTable.setRowSorter(rowSorter);
        
        //khởi tạo BUS san pham
        //spBUS.listSP();
        //xuat table
        outModelLoai(modelloai, loaiBUS.getList());
    }
    public void creatTableNhaCungCap(){
        
        nccBUS.listNcc();
        String arr[]={"Mă Nhà Cung Cấp","Tên Nhà Cung Cấp","Địa Chỉ","Số Điện Thoại"};
     
        modelnhacungcap = header(arr);
        nhacungcaptable.setModel(modelnhacungcap);
        
        outModelNCC(modelnhacungcap, nccBUS.getList());
    }
    public void creatTablePhieuNhapHang(){
        
        nhapBUS.listPNH();
        String arr[]={"Mă Phiếu Nhập","Mã Nhà Cung Cấp","Mã Nhân Viên","Ngày Nhập","Tổng Tiền"};
     
        modelnhaphang = header(arr);
        nhaphangtable.setModel(modelnhaphang);
        
        outModelPhieuNhapHang(modelnhaphang, nhapBUS.getList());
    }
    public void creatTableChiTietPhieuNhapHang(){
        
        chitietnhapBUS.listCTPNH();
        String arr[]={"Mă Phiếu Nhập","Mã Sản Phẩm","ĐG","SL","Thành Tiền"};
     
        modelctnh = header(arr);
        chitietnhaphangtable.setModel(modelctnh);
        
        outModelChiTietPhieuNhapHang(modelctnh, chitietnhapBUS.getList(),"");
    }
    public void outModelPhieuNhapHang(DefaultTableModel model , ArrayList<phieunhaphangDTO> sp){
        Vector data;
        data = new Vector();
        modelnhaphang.setRowCount(0);
        for(phieunhaphangDTO s:sp){
            model.addRow(new Object[]{
                s.getMaphieunhap(),s.getManhacungcap(),s.getManhanvien(),s.getNgaynhap(),s.getTongtien()
            });  
        }
        
    }
    public void outModelChiTietPhieuNhapHang(DefaultTableModel model , ArrayList<chitietphieunhapDTO> sp,String value){
        Vector data;
        data = new Vector();
        modelctnh.setRowCount(0);
        for(chitietphieunhapDTO s:sp){
            if(s.getMaphieunhap().equals(value)){
                model.addRow(new Object[]{
                    s.getMaphieunhap(),s.getMasanpham(),s.getDongia(),s.getSoluong(),s.getThanhtien()
                });  
            }
             
        }
        
    }
    public void outModel1(DefaultTableModel model , ArrayList<sanphamDTO> sp){
        Vector data;
        data = new Vector();
        model1.setRowCount(0);
        for(sanphamDTO s:sp){
            model.addRow(new Object[]{
                s.getMasanpham(),s.getTensp(),s.getNsx(),s.getImg(),s.getSoluong(),s.getMaloai(),s.getDongia()
            });  
        }
        
    }
    public void outModelLoai(DefaultTableModel model , ArrayList<loaiDTO> sp){
        Vector data;
        data = new Vector();
        maloaiBox.removeAllItems();
        maloaiBox.addItem("");
        modelloai.setRowCount(0);
        for(loaiDTO s:sp){
            model.addRow(new Object[]{
                s.getMaloai(),s.getTenloai()
                
            }); 
            maloaiBox.addItem(s.getMaloai()+"/"+s.getTenloai());
            maloaiBox.setSelectedItem("");
        }
        
    }
    public void outModelNCC(DefaultTableModel model , ArrayList<nhacungcapDTO> sp){
        Vector data;
        data = new Vector();
        manhacungcapBox.removeAllItems();
        manhacungcapBox.addItem("");
        modelnhacungcap.setRowCount(0);
        for(nhacungcapDTO s:sp){
            model.addRow(new Object[]{
                s.getManhacungcap(),s.getTennhacungcap(),s.getDiachi(),s.getSdt()
            });
            manhacungcapBox.addItem(s.getManhacungcap()+"/"+s.getTennhacungcap());
            manhacungcapBox.setSelectedItem("");
        }
        
    }
    public void outModel2(DefaultTableModel model , ArrayList<chitietsanphamDTO> sp,String value){
        Vector data;
        data = new Vector();
        model2.setRowCount(0);
        //creatTableChiTietSanPham();
        int n= 0;
        for(chitietsanphamDTO s:sp){
           
            if(s.getMasanpham().equals(value)||value == "run"){
                n+=s.getSoluong();
                //.out.println(s.getMasanpham());
                //spDAO.setSoLuong(s.getMasanpham(), n);
                model2.addRow(new Object[]{
                    s.getMachitietsanpham(),s.getMasanpham(),s.getSize(),s.getMausac(),s.getSoluong()
                });   
                
            }
            //setSoLuong(spBUS.getList(), n, s.getMasanpham());
            
        }
        //spBUS.listSP();
        //outModel1(model1, spBUS.getList());
        
        
    }
    public int getSoLuongByMaSanPham(ArrayList<chitietsanphamDTO> sp,String value){
        int n=0;
        for(chitietsanphamDTO s:sp){
           
            if(s.getMasanpham().equals(value)){
                n+=s.getSoluong();
            }
            //setSoLuong(spBUS.getList(), n, s.getMasanpham());
            
        }
        return n;
    }
    public int getTongTienByMaPhieuNhap(ArrayList<chitietphieunhapDTO> sp,String value){
        int n=0;
        for(chitietphieunhapDTO s:sp){
           
            if(s.getMaphieunhap().equals(value)){
                n+=s.getThanhtien();
            }
            //setSoLuong(spBUS.getList(), n, s.getMasanpham());
            
        }
        return n;
    }
    public void setSoLuong(ArrayList<sanphamDTO> sp,int soluong,String id){
        for(sanphamDTO s:sp){
           
            if(s.getMasanpham().equals(id)){
               
               // spDAO.setSoLuong(s.getMasanpham(), soluong);               
                
            }
            
        }
    }
    public void setTongTien(ArrayList<phieunhaphangDTO> sp,int soluong,String id){
        
        for(phieunhaphangDTO s:sp){
           
            if(s.getMaphieunhap().equals(id)){
               
                nhapDAO.setTongTien(s.getMaphieunhap(), soluong);               
                
            }
            
            
        }
    }
    public void setVisibleSanPham(){
        capnhatsanphamtable.setVisible(false);
        nhaphangtablepanel.setVisible(false);
        nhacungcaptablepanel.setVisible(false);
        loaitablepanel.setVisible(false);
        chuongtrinhgiamgiatablepanel.setVisible(false);
        capnhathoadontablepanel.setVisible(false);
        
    }
    public void setVisibleMenu(){
        khachhangpanel.setVisible(false);
        leftPanel.setVisible(false);
        mainPanel.setVisible(false);
        productLeftPanel.setVisible(false);
        orderLeftPanel.setVisible(false);
        //productLeftPanel2.setVisible(false);
        nhanvienpanel.setVisible(false);
        settingPanel.setVisible(false);
        dashboardPanel.setVisible(false);
        sellPanel.setVisible(false);
        
        
        
    }
    public void setVisibleProduct(){
        capnhatsanphamPanel.setVisible(false);
        nhaphangPanel.setVisible(false);
        nhacungcapPanel.setVisible(false);
        loaisanphamPanel.setVisible(false);
        chuongtrinhgiamgiaPanel.setVisible(false);
        capnhathoadonPanel.setVisible(false);
                productRightPanel.setVisible(true);

            
    }
    public void setBoderMenu(){
        sellLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        orderLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        productLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        staffLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        customerLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        dashboardLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        //logoutLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        settingLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        
    }
    public void decorateButton(){
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    int interval = 1000; // 1000 ms
    
    new Timer(interval, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            timer.setText(dateFormat.format(now.getTime()));
            datelabel_sell.setText(df.format(now.getTime()));
            
        }

            
    }).start();
 
        menuPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,2,Color.DARK_GRAY));
        phat.setIcon((icon("phat",200,200)));
        phu.setIcon((icon("phu",200,200)));
        nghia.setIcon((icon("nghia",200,200)));
        nam.setIcon((icon("nam",200,200)));
        settingLabel.setIcon(icon("caidat",50,50));
        jPanel2.setLayout(new GridLayout(2, 1));
        jPanel4.setLayout(new GridLayout(1, 4));
   
        jLabel72.setText("Welcome to "+"");
        RETURN.setIcon(icon("return",50,50));
        EXIT.setIcon(icon("exit",50,50));
        //jLabel73.setIcon(icon("user",200,200));
        addSPBtn.setIcon(icon("add",30,30));
        deleteSPBtn.setIcon(icon("delete",30,30));
        editSPBtn.setIcon(icon("edit",30,30));
        //resetSPBtn.setIcon(icon("reset",50,50));
        //sortSPBtn.setIcon(icon("sort",50,50));
        searchSPBtn.setIcon(icon("search",30,30));
        them_nv.setIcon(icon("add",30,30));
        xoa_nv.setIcon(icon("delete",30,30));
        sua_nv.setIcon(icon("edit",30,30));
        reset_nv.setIcon(icon("reset",30,30));
        //addNCCBtn.setIcon(icon("add",50,50));
        //deleteNCCBtn.setIcon(icon("delete",50,50));
        //editNCCBtn.setIcon(icon("edit",50,50));
        //resetNCCBtn.setIcon(icon("reset",50,50));
        //sortNCCBtn.setIcon(icon("sort",50,50));
        //searchNCCBtn.setIcon(icon("search",50,50));
        themBtn_khach.setIcon(icon("add",30,30));
        suaBtn_khach.setIcon(icon("edit",30,30));
        xoaBtn_khach.setIcon(icon("delete",30,30));
        resetBtn_khach.setIcon(icon("reset",30,30));
        //importhdexlbt.setIcon(icon("import",30,30));
        //exporthdexcelbt.setIcon(icon("export",30,30));
        //importcthdexlbt.setIcon(icon("import",30,30));
        //exportcthdexcelbt.setIcon(icon("export",30,30));
        deletehd.setIcon(icon("delete",30,30));
        deleteggbt.setIcon(icon("delete",30,30));
        deletectggbt.setIcon(icon("delete",30,30));
        addggbt.setIcon(icon("add",30,30));
        addctggbt.setIcon(icon("add",30,30));
        editggbt.setIcon(icon("edit",30,30));
        editctggbt.setIcon(icon("edit",30,30));
    }
    public ImageIcon icon(String nameFile,int width,int height){
        String path = "src\\img\\"+nameFile+".png";
        ImageIcon icon = new ImageIcon();
        icon=new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return icon;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        btnGroupNV = new javax.swing.ButtonGroup();
        btnGroupKH = new javax.swing.ButtonGroup();
        bigPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        sellLabel = new javax.swing.JLabel();
        orderLabel = new javax.swing.JLabel();
        productLabel = new javax.swing.JLabel();
        staffLabel = new javax.swing.JLabel();
        customerLabel = new javax.swing.JLabel();
        dashboardLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        timer = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        EXIT = new javax.swing.JLabel();
        RETURN = new javax.swing.JLabel();
        settingLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        productRightPanel = new javax.swing.JPanel();
        capnhatsanphamtable = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sanphamTable = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detailproducttable = new javax.swing.JTable();
        displaySPConhang = new javax.swing.JButton();
        displaySPhethang = new javax.swing.JButton();
        displayallsp = new javax.swing.JButton();
        importspexl = new javax.swing.JButton();
        exportspexl = new javax.swing.JButton();
        displaySPConhang1 = new javax.swing.JButton();
        displaySPConhang2 = new javax.swing.JButton();
        exportctspexl = new javax.swing.JButton();
        nhacungcaptablepanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        nhacungcaptable = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        importnccexl = new javax.swing.JButton();
        exportnccexl = new javax.swing.JButton();
        loaitablepanel = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        loaitable = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        importloaiexl = new javax.swing.JButton();
        exportloaiexl = new javax.swing.JButton();
        nhaphangtablepanel = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        nhaphangtable = new javax.swing.JTable();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        chitietnhaphangtable = new javax.swing.JTable();
        exportnhexl = new javax.swing.JButton();
        importctnhexl = new javax.swing.JButton();
        capnhathoadontablepanel = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtbdshd = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtbcthd = new javax.swing.JTable();
        importhdexlbt = new javax.swing.JLabel();
        exporthdexcelbt = new javax.swing.JLabel();
        exportcthdexcelbt = new javax.swing.JLabel();
        importcthdexlbt = new javax.swing.JLabel();
        chuongtrinhgiamgiatablepanel = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        giamgiatable = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        ctgiamgiatable = new javax.swing.JTable();
        leftPanel = new javax.swing.JPanel();
        productLeftPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        capnhatlabel = new javax.swing.JLabel();
        nhaphanglabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        capnhatsanphamPanel = new javax.swing.JPanel();
        searchSanPhamTf = new javax.swing.JTextField();
        searchSPBtn = new javax.swing.JButton();
        addSPBtn = new javax.swing.JLabel();
        deleteSPBtn = new javax.swing.JLabel();
        editSPBtn = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        masanphamTf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tensanphamTf = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        soluongTf = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        dongiaTf = new javax.swing.JTextField();
        addSPBtn1 = new javax.swing.JLabel();
        deleteSPBtn1 = new javax.swing.JLabel();
        editSPBtn1 = new javax.swing.JLabel();
        machitietsanphamLabel = new javax.swing.JLabel();
        machitietsanphamTf = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        masanpham2Tf = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        soluong2Tf = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        maloaiBox = new javax.swing.JComboBox<>();
        sizeBox = new javax.swing.JComboBox<>();
        colorBox = new javax.swing.JComboBox<>();
        thongbaosanpham = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nsxTf = new javax.swing.JTextField();
        nhaphangPanel = new javax.swing.JPanel();
        jTextField13 = new javax.swing.JTextField();
        searchSPBtn1 = new javax.swing.JButton();
        addSPBtn2 = new javax.swing.JLabel();
        deleteSPBtn2 = new javax.swing.JLabel();
        editSPBtn2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        maphieunhapTf = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        manhanvienTf = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tongtienTf = new javax.swing.JTextField();
        addSPBtn3 = new javax.swing.JLabel();
        deleteSPBtn3 = new javax.swing.JLabel();
        editSPBtn3 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        maphieunhapTf2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        masanphamTf_ctnh = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        soluongTf_ctnh = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        dongiaTf_ctnh = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        manhacungcapBox = new javax.swing.JComboBox<>();
        nhacungcapPanel = new javax.swing.JPanel();
        nhacungcapSearch = new javax.swing.JTextField();
        searchSPBtn2 = new javax.swing.JButton();
        addSPBtn4 = new javax.swing.JLabel();
        deleteSPBtn4 = new javax.swing.JLabel();
        editSPBtn4 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        manhacungcapTf_ncc = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        tennhacungcapTf_ncc = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        diachiTf_ncc = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        sdtTf_ncc = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        loaisanphamPanel = new javax.swing.JPanel();
        loaiSearchTf = new javax.swing.JTextField();
        searchSPBtn3 = new javax.swing.JButton();
        addSPBtn6 = new javax.swing.JLabel();
        deleteSPBtn6 = new javax.swing.JLabel();
        editSPBtn6 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        maloaiTf_loaisanphamPanel = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        tenloaiTf_loaisanphamPanel = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        orderLeftPanel = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        capnhathoadonPanel = new javax.swing.JPanel();
        searchid_hd = new javax.swing.JTextField();
        searchSPBtn4 = new javax.swing.JButton();
        deletehd = new javax.swing.JLabel();
        tthdlabel = new javax.swing.JLabel();
        ttcthdlabel = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        searchstring_hd = new javax.swing.JTextField();
        chuongtrinhgiamgiaPanel = new javax.swing.JPanel();
        searchid_gg = new javax.swing.JTextField();
        searchSPBtn7 = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        addggbt = new javax.swing.JLabel();
        deleteggbt = new javax.swing.JLabel();
        editggbt = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        maggtf = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        tenggtf = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        addctggbt = new javax.swing.JLabel();
        deletectggbt = new javax.swing.JLabel();
        editctggbt = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        idsptf = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        discounttf = new javax.swing.JTextField();
        tensplabel = new javax.swing.JLabel();
        searchstring_gg = new javax.swing.JTextField();
        datebd = new com.toedter.calendar.JDateChooser();
        datekt = new com.toedter.calendar.JDateChooser();
        khachhangpanel = new javax.swing.JPanel();
        hoTf_khach = new javax.swing.JTextField();
        tenTf_khach = new javax.swing.JTextField();
        gmailTf_khach = new javax.swing.JTextField();
        searchTf = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sdtTf_khach = new javax.swing.JTextField();
        themBtn_khach = new javax.swing.JLabel();
        suaBtn_khach = new javax.swing.JLabel();
        xoaBtn_khach = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        dsKHTable = new javax.swing.JTable();
        jgioitinhNam = new javax.swing.JCheckBox();
        jgioitinhNU = new javax.swing.JCheckBox();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        displaySPConhang10 = new javax.swing.JButton();
        displaySPConhang11 = new javax.swing.JButton();
        khachhangbackground = new javax.swing.JLabel();
        resetBtn_khach = new javax.swing.JLabel();
        nhanvienpanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        hoTf_nhanvien = new javax.swing.JTextField();
        tenTf_nhanvien = new javax.swing.JTextField();
        diachiTf_nhanvien = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        dsnvTb_nhanvien = new javax.swing.JTable();
        emailTf_nhanvien = new javax.swing.JTextField();
        sdtTf_nhanvien = new javax.swing.JTextField();
        jDCns_nhanvien = new com.toedter.calendar.JDateChooser();
        luongTf_nhanvien = new javax.swing.JTextField();
        jCBNam = new javax.swing.JCheckBox();
        jCBNu = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        timkiemTf_nhanvien = new javax.swing.JTextField();
        them_nv = new javax.swing.JLabel();
        sua_nv = new javax.swing.JLabel();
        xoa_nv = new javax.swing.JLabel();
        reset_nv = new javax.swing.JLabel();
        displaySPConhang12 = new javax.swing.JButton();
        displaySPConhang13 = new javax.swing.JButton();
        nhanvienbackground = new javax.swing.JLabel();
        timid_nv = new javax.swing.JTextField();
        settingPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        dashboardPanel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        phat = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        phu = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        nghia = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        nam = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        sellPanel = new javax.swing.JPanel();
        row1 = new javax.swing.JPanel();
        row1col1 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        idhoadontf_sell = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        iduserloginlabel_sell = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        idggtf_sell = new javax.swing.JTextField();
        datelabel_sell = new javax.swing.JLabel();
        tennvtf_sell = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        idkhtf_sell = new javax.swing.JTextField();
        tenkhtf_sell = new javax.swing.JTextField();
        row1col2 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        idsptf_sell = new javax.swing.JTextField();
        tientf_sell = new javax.swing.JTextField();
        discounttf_sell = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        tgtf_sell = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        thongbaosanphamtf_sell = new javax.swing.JLabel();
        tensptf_sell = new javax.swing.JTextField();
        row1col3 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        size_sell = new javax.swing.JComboBox<>();
        color_sell = new javax.swing.JComboBox<>();
        giamslbt_sell = new javax.swing.JButton();
        sltf_sell = new javax.swing.JTextField();
        tangslbt_sell = new javax.swing.JButton();
        jLabel114 = new javax.swing.JLabel();
        thongbaoctsp_sell = new javax.swing.JLabel();
        themcthdbtn_sell = new javax.swing.JButton();
        row2 = new javax.swing.JPanel();
        row2col1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cthdtable_sell = new javax.swing.JTable();
        jLabel81 = new javax.swing.JLabel();
        row2col2 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        ttlabel_sell = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        ttglabel_sell = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        ttptlabel_sell = new javax.swing.JLabel();
        carhlabel_sell = new javax.swing.JPanel();
        cashlabel_sell = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        row3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        delhdpn_sell = new javax.swing.JPanel();
        delhdlabel_sell = new javax.swing.JLabel();
        editslpn_sell = new javax.swing.JPanel();
        editsllabel_sell = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1500, 1000));

        bigPanel.setBackground(new Color(36, 37, 42));

        menuPanel.setBackground(new Color(68, 108, 179));
        menuPanel.setForeground(new java.awt.Color(255, 255, 255));
        menuPanel.setOpaque(false);

        sellLabel.setBackground(new java.awt.Color(255, 255, 255));
        sellLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        sellLabel.setForeground(new java.awt.Color(255, 255, 255));
        sellLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sellLabel.setText("SELL");
        sellLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sellLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellLabelMouseClicked(evt);
            }
        });

        orderLabel.setBackground(new java.awt.Color(255, 255, 255));
        orderLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        orderLabel.setForeground(new java.awt.Color(255, 255, 255));
        orderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderLabel.setText("ORDER");
        orderLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        orderLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderLabelMouseClicked(evt);
            }
        });

        productLabel.setBackground(new java.awt.Color(255, 255, 255));
        productLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        productLabel.setForeground(new java.awt.Color(255, 255, 255));
        productLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productLabel.setText("PRODUCT");
        productLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productLabelMouseClicked(evt);
            }
        });

        staffLabel.setBackground(new java.awt.Color(255, 255, 255));
        staffLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        staffLabel.setForeground(new java.awt.Color(255, 255, 255));
        staffLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        staffLabel.setText("STAFF");
        staffLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        staffLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffLabelMouseClicked(evt);
            }
        });

        customerLabel.setBackground(new java.awt.Color(255, 255, 255));
        customerLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        customerLabel.setForeground(new java.awt.Color(255, 255, 255));
        customerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customerLabel.setText("CUSTOMER");
        customerLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerLabelMouseClicked(evt);
            }
        });

        dashboardLabel.setBackground(new java.awt.Color(255, 255, 255));
        dashboardLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        dashboardLabel.setForeground(new java.awt.Color(255, 255, 255));
        dashboardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dashboardLabel.setText("DASHBOARD");
        dashboardLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardLabelMouseClicked(evt);
            }
        });

        logoLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setText("FASHION STORE");
        logoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        timer.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        timer.setForeground(new java.awt.Color(255, 255, 255));
        timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(1, 3));

        EXIT.setToolTipText("Thoát");
        EXIT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EXIT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EXITMouseClicked(evt);
            }
        });
        jPanel6.add(EXIT);

        RETURN.setToolTipText("Trở về");
        RETURN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RETURN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RETURNMouseClicked(evt);
            }
        });
        jPanel6.add(RETURN);

        settingLabel.setBackground(new java.awt.Color(255, 255, 255));
        settingLabel.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        settingLabel.setForeground(new java.awt.Color(255, 255, 255));
        settingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settingLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingLabelMouseClicked(evt);
            }
        });
        jPanel6.add(settingLabel);

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(timer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(sellLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(productLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(staffLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(customerLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(dashboardLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 552, Short.MAX_VALUE)
                .addComponent(sellLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(orderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(staffLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(463, 463, 463)
                .addComponent(timer)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 10334, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(1505, 0));

        productRightPanel.setOpaque(false);

        capnhatsanphamtable.setOpaque(false);
        capnhatsanphamtable.setPreferredSize(new java.awt.Dimension(1505, 1505));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel2.setText("DETAIL PRODUCT TABLE");

        sanphamTable.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        sanphamTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(sanphamTable);

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel23.setText("PRODUCT TABLE");

        detailproducttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(detailproducttable);

        displaySPConhang.setBackground(new Color(36, 37, 42));
        displaySPConhang.setForeground(new java.awt.Color(255, 255, 255));
        displaySPConhang.setText("Sản phẩm còn hàng");
        displaySPConhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPConhangMouseClicked(evt);
            }
        });

        displaySPhethang.setBackground(new Color(36, 37, 42));
        displaySPhethang.setForeground(new java.awt.Color(255, 255, 255));
        displaySPhethang.setText("Sản phẩm hết hàng");
        displaySPhethang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPhethangMouseClicked(evt);
            }
        });

        displayallsp.setBackground(new Color(36, 37, 42));
        displayallsp.setForeground(new java.awt.Color(255, 255, 255));
        displayallsp.setText("Hiển thị toàn bộ sản phẩm");
        displayallsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayallspMouseClicked(evt);
            }
        });

        importspexl.setBackground(new Color(36, 37, 42));
        importspexl.setForeground(new java.awt.Color(255, 255, 255));
        importspexl.setText("IMPORT");
        importspexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importspexlMouseClicked(evt);
            }
        });

        exportspexl.setBackground(new Color(36, 37, 42));
        exportspexl.setForeground(new java.awt.Color(255, 255, 255));
        exportspexl.setText("EXPORT");
        exportspexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportspexlMouseClicked(evt);
            }
        });

        displaySPConhang1.setBackground(new Color(36, 37, 42));
        displaySPConhang1.setForeground(new java.awt.Color(255, 255, 255));
        displaySPConhang1.setText("Sản phẩm còn hàng");
        displaySPConhang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPConhang1MouseClicked(evt);
            }
        });

        displaySPConhang2.setBackground(new Color(36, 37, 42));
        displaySPConhang2.setForeground(new java.awt.Color(255, 255, 255));
        displaySPConhang2.setText("Sản phẩm hết hàng");
        displaySPConhang2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPConhang2MouseClicked(evt);
            }
        });

        exportctspexl.setBackground(new Color(36, 37, 42));
        exportctspexl.setForeground(new java.awt.Color(255, 255, 255));
        exportctspexl.setText("EXPORT");
        exportctspexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportctspexlMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout capnhatsanphamtableLayout = new javax.swing.GroupLayout(capnhatsanphamtable);
        capnhatsanphamtable.setLayout(capnhatsanphamtableLayout);
        capnhatsanphamtableLayout.setHorizontalGroup(
            capnhatsanphamtableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhatsanphamtableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(capnhatsanphamtableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(capnhatsanphamtableLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(displaySPConhang1)
                        .addGap(18, 18, 18)
                        .addComponent(displaySPConhang2)
                        .addGap(18, 18, 18)
                        .addComponent(exportctspexl))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(capnhatsanphamtableLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(24, 24, 24)
                        .addComponent(displaySPConhang)
                        .addGap(18, 18, 18)
                        .addComponent(displaySPhethang)
                        .addGap(18, 18, 18)
                        .addComponent(displayallsp)
                        .addGap(18, 18, 18)
                        .addComponent(importspexl)
                        .addGap(18, 18, 18)
                        .addComponent(exportspexl)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        capnhatsanphamtableLayout.setVerticalGroup(
            capnhatsanphamtableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhatsanphamtableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(capnhatsanphamtableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(displaySPConhang)
                    .addComponent(displaySPhethang)
                    .addComponent(displayallsp)
                    .addComponent(exportspexl)
                    .addComponent(importspexl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(capnhatsanphamtableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(displaySPConhang1)
                    .addComponent(displaySPConhang2)
                    .addComponent(exportctspexl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(546, Short.MAX_VALUE))
        );

        nhacungcaptablepanel.setOpaque(false);
        nhacungcaptablepanel.setPreferredSize(new java.awt.Dimension(1505, 1505));

        nhacungcaptable.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        nhacungcaptable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(nhacungcaptable);

        jLabel52.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel52.setText("NHACUNGCAP TABLE");

        importnccexl.setBackground(new Color(36, 37, 42));
        importnccexl.setForeground(new java.awt.Color(255, 255, 255));
        importnccexl.setText("IMPORT");
        importnccexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importnccexlMouseClicked(evt);
            }
        });

        exportnccexl.setBackground(new Color(36, 37, 42));
        exportnccexl.setForeground(new java.awt.Color(255, 255, 255));
        exportnccexl.setText("EXPORT");
        exportnccexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportnccexlMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout nhacungcaptablepanelLayout = new javax.swing.GroupLayout(nhacungcaptablepanel);
        nhacungcaptablepanel.setLayout(nhacungcaptablepanelLayout);
        nhacungcaptablepanelLayout.setHorizontalGroup(
            nhacungcaptablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhacungcaptablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhacungcaptablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addGroup(nhacungcaptablepanelLayout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(18, 18, 18)
                        .addComponent(importnccexl)
                        .addGap(18, 18, 18)
                        .addComponent(exportnccexl)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        nhacungcaptablepanelLayout.setVerticalGroup(
            nhacungcaptablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhacungcaptablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhacungcaptablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(importnccexl)
                    .addComponent(exportnccexl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel49)
                .addContainerGap(1008, Short.MAX_VALUE))
        );

        loaitablepanel.setOpaque(false);
        loaitablepanel.setPreferredSize(new java.awt.Dimension(1505, 1505));

        loaitable.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        loaitable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(loaitable);

        jLabel55.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel55.setText("LOAI TABLE");

        importloaiexl.setBackground(new Color(36, 37, 42));
        importloaiexl.setForeground(new java.awt.Color(255, 255, 255));
        importloaiexl.setText("IMPORT");
        importloaiexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importloaiexlMouseClicked(evt);
            }
        });

        exportloaiexl.setBackground(new Color(36, 37, 42));
        exportloaiexl.setForeground(new java.awt.Color(255, 255, 255));
        exportloaiexl.setText("EXPORT");
        exportloaiexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportloaiexlMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout loaitablepanelLayout = new javax.swing.GroupLayout(loaitablepanel);
        loaitablepanel.setLayout(loaitablepanelLayout);
        loaitablepanelLayout.setHorizontalGroup(
            loaitablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaitablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loaitablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addGroup(loaitablepanelLayout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(importloaiexl)
                        .addGap(18, 18, 18)
                        .addComponent(exportloaiexl)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        loaitablepanelLayout.setVerticalGroup(
            loaitablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaitablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loaitablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(importloaiexl)
                    .addComponent(exportloaiexl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel54)
                .addContainerGap(1019, Short.MAX_VALUE))
        );

        nhaphangtablepanel.setOpaque(false);
        nhaphangtablepanel.setPreferredSize(new java.awt.Dimension(1505, 1505));

        jLabel56.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel56.setText("CHITIETNHAPHANG TABLE");

        nhaphangtable.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        nhaphangtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(nhaphangtable);

        jLabel58.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel58.setText("NHAPHANG TABLE");

        chitietnhaphangtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane15.setViewportView(chitietnhaphangtable);

        exportnhexl.setBackground(new Color(36, 37, 42));
        exportnhexl.setForeground(new java.awt.Color(255, 255, 255));
        exportnhexl.setText("EXPORT");
        exportnhexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportnhexlMouseClicked(evt);
            }
        });

        importctnhexl.setBackground(new Color(36, 37, 42));
        importctnhexl.setForeground(new java.awt.Color(255, 255, 255));
        importctnhexl.setText("IMPORT");
        importctnhexl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importctnhexlMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout nhaphangtablepanelLayout = new javax.swing.GroupLayout(nhaphangtablepanel);
        nhaphangtablepanel.setLayout(nhaphangtablepanelLayout);
        nhaphangtablepanelLayout.setHorizontalGroup(
            nhaphangtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhaphangtablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhaphangtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nhaphangtablepanelLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(18, 18, 18)
                        .addComponent(importctnhexl))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nhaphangtablepanelLayout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addGap(18, 18, 18)
                        .addComponent(exportnhexl)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        nhaphangtablepanelLayout.setVerticalGroup(
            nhaphangtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhaphangtablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhaphangtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(exportnhexl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nhaphangtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(importctnhexl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(546, Short.MAX_VALUE))
        );

        capnhathoadontablepanel.setOpaque(false);
        capnhathoadontablepanel.setPreferredSize(new java.awt.Dimension(1505, 1505));

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel42.setText("DETAIL ORDER TABLE");

        jtbdshd.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 17)); // NOI18N
        jtbdshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbdshd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbdshdMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jtbdshd);

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel44.setText("ORDER TABLE");

        jtbcthd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbcthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbcthdMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jtbcthd);

        importhdexlbt.setBackground(new Color(36, 37, 42));
        importhdexlbt.setForeground(new java.awt.Color(255, 255, 255));
        importhdexlbt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        importhdexlbt.setText("EXPORT");
        importhdexlbt.setMinimumSize(new java.awt.Dimension(0, 30));
        importhdexlbt.setOpaque(true);
        importhdexlbt.setPreferredSize(new java.awt.Dimension(30, 30));
        importhdexlbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importhdexlbtMouseClicked(evt);
            }
        });

        exporthdexcelbt.setBackground(new Color(36, 37, 42));
        exporthdexcelbt.setForeground(new java.awt.Color(255, 255, 255));
        exporthdexcelbt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exporthdexcelbt.setText("IMPORT");
        exporthdexcelbt.setMinimumSize(new java.awt.Dimension(0, 30));
        exporthdexcelbt.setOpaque(true);
        exporthdexcelbt.setPreferredSize(new java.awt.Dimension(30, 30));

        exportcthdexcelbt.setBackground(new Color(36, 37, 42));
        exportcthdexcelbt.setForeground(new java.awt.Color(255, 255, 255));
        exportcthdexcelbt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exportcthdexcelbt.setText("EXPORT");
        exportcthdexcelbt.setOpaque(true);
        exportcthdexcelbt.setPreferredSize(new java.awt.Dimension(30, 30));
        exportcthdexcelbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportcthdexcelbtMouseClicked(evt);
            }
        });

        importcthdexlbt.setBackground(new Color(36, 37, 42));
        importcthdexlbt.setForeground(new java.awt.Color(255, 255, 255));
        importcthdexlbt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        importcthdexlbt.setText("IMPORT");
        importcthdexlbt.setOpaque(true);
        importcthdexlbt.setPreferredSize(new java.awt.Dimension(30, 30));
        importcthdexlbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importcthdexlbtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout capnhathoadontablepanelLayout = new javax.swing.GroupLayout(capnhathoadontablepanel);
        capnhathoadontablepanel.setLayout(capnhathoadontablepanelLayout);
        capnhathoadontablepanelLayout.setHorizontalGroup(
            capnhathoadontablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhathoadontablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(capnhathoadontablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
                    .addGroup(capnhathoadontablepanelLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(51, 51, 51)
                        .addComponent(importcthdexlbt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportcthdexcelbt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel43)
                    .addGroup(capnhathoadontablepanelLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(57, 57, 57)
                        .addComponent(importhdexlbt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exporthdexcelbt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        capnhathoadontablepanelLayout.setVerticalGroup(
            capnhathoadontablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhathoadontablepanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(capnhathoadontablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(importhdexlbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exporthdexcelbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(capnhathoadontablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(exportcthdexcelbt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importcthdexlbt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(566, 566, 566))
        );

        importhdexlbt.getAccessibleContext().setAccessibleName("");
        exporthdexcelbt.getAccessibleContext().setAccessibleName("");
        exportcthdexcelbt.getAccessibleContext().setAccessibleName("");
        importcthdexlbt.getAccessibleContext().setAccessibleName("");

        chuongtrinhgiamgiatablepanel.setOpaque(false);
        chuongtrinhgiamgiatablepanel.setPreferredSize(new java.awt.Dimension(1505, 1505));

        giamgiatable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        giamgiatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        giamgiatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giamgiatableMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(giamgiatable);

        jLabel60.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel60.setText("SALE TABLE");

        jLabel62.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel62.setText("SALE DETAIL TABLE");

        ctgiamgiatable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ctgiamgiatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã giảm giá", "Mã sản phẩm", "Phần trăm giảm"
            }
        ));
        ctgiamgiatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctgiamgiatableMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(ctgiamgiatable);

        javax.swing.GroupLayout chuongtrinhgiamgiatablepanelLayout = new javax.swing.GroupLayout(chuongtrinhgiamgiatablepanel);
        chuongtrinhgiamgiatablepanel.setLayout(chuongtrinhgiamgiatablepanelLayout);
        chuongtrinhgiamgiatablepanelLayout.setHorizontalGroup(
            chuongtrinhgiamgiatablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chuongtrinhgiamgiatablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chuongtrinhgiamgiatablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        chuongtrinhgiamgiatablepanelLayout.setVerticalGroup(
            chuongtrinhgiamgiatablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chuongtrinhgiamgiatablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(565, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout productRightPanelLayout = new javax.swing.GroupLayout(productRightPanel);
        productRightPanel.setLayout(productRightPanelLayout);
        productRightPanelLayout.setHorizontalGroup(
            productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productRightPanelLayout.createSequentialGroup()
                .addComponent(nhacungcaptablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(capnhatsanphamtable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(loaitablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(nhaphangtablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productRightPanelLayout.createSequentialGroup()
                    .addComponent(capnhathoadontablepanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1533, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addComponent(chuongtrinhgiamgiatablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 14, Short.MAX_VALUE)))
        );
        productRightPanelLayout.setVerticalGroup(
            productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productRightPanelLayout.createSequentialGroup()
                .addComponent(nhacungcaptablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(capnhatsanphamtable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(loaitablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(nhaphangtablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addComponent(capnhathoadontablepanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1537, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(productRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productRightPanelLayout.createSequentialGroup()
                    .addComponent(chuongtrinhgiamgiatablepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1518, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 16, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(productRightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(productRightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        leftPanel.setBackground(new Color( 31, 190, 214));
        leftPanel.setPreferredSize(new java.awt.Dimension(500, 11778));

        productLeftPanel.setOpaque(false);
        productLeftPanel.setPreferredSize(new java.awt.Dimension(450, 1529));

        capnhatlabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        capnhatlabel.setForeground(new java.awt.Color(255, 255, 255));
        capnhatlabel.setText("Cập nhật sản phẩm");
        capnhatlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                capnhatlabelMouseClicked(evt);
            }
        });

        nhaphanglabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        nhaphanglabel.setForeground(new java.awt.Color(255, 255, 255));
        nhaphanglabel.setText("Nhập hàng");
        nhaphanglabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhaphanglabelMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nhà cung cấp");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Loại sản phẩm");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        capnhatsanphamPanel.setOpaque(false);

        searchSanPhamTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSanPhamTfActionPerformed(evt);
            }
        });

        searchSPBtn.setText("Search");
        searchSPBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSPBtnActionPerformed(evt);
            }
        });

        addSPBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        addSPBtn.setText("ADD");
        addSPBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSPBtnMouseClicked(evt);
            }
        });

        deleteSPBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteSPBtn.setText("DELETE");
        deleteSPBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSPBtnMouseClicked(evt);
            }
        });

        editSPBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        editSPBtn.setText("EDIT");
        editSPBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSPBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSPBtnMouseClicked(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mã loại");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mã sản phẩm");

        masanphamTf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                masanphamTfKeyReleased(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tên sản phẩm");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Số lượng");

        soluongTf.setEnabled(false);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Đơn giá(Giá nhập x 2)");

        addSPBtn1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addSPBtn1.setForeground(new java.awt.Color(255, 255, 255));
        addSPBtn1.setText("ADD");
        addSPBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSPBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSPBtn1MouseClicked(evt);
            }
        });

        deleteSPBtn1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteSPBtn1.setForeground(new java.awt.Color(255, 255, 255));
        deleteSPBtn1.setText("DELETE");
        deleteSPBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSPBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSPBtn1MouseClicked(evt);
            }
        });

        editSPBtn1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editSPBtn1.setForeground(new java.awt.Color(255, 255, 255));
        editSPBtn1.setText("EDIT");
        editSPBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSPBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSPBtn1MouseClicked(evt);
            }
        });

        machitietsanphamLabel.setForeground(new java.awt.Color(255, 255, 255));
        machitietsanphamLabel.setText("Mã chi tiết sản phẩm");

        machitietsanphamTf.setEnabled(false);

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Mã sản phẩm");

        masanpham2Tf.setEnabled(false);

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Size");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Color");

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Số lượng");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("SẢN PHẨM");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("CHI TIẾT SẢN PHẨM");

        sizeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL", "XXL", " " }));

        colorBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "White", "Blue", "Black", "Yellow", "Green", "Gray", " " }));

        thongbaosanpham.setForeground(new java.awt.Color(255, 51, 0));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nhà sản xuất");

        javax.swing.GroupLayout capnhatsanphamPanelLayout = new javax.swing.GroupLayout(capnhatsanphamPanel);
        capnhatsanphamPanel.setLayout(capnhatsanphamPanelLayout);
        capnhatsanphamPanelLayout.setHorizontalGroup(
            capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(thongbaosanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                        .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(searchSanPhamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(searchSPBtn))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(addSPBtn)
                                    .addGap(18, 18, 18)
                                    .addComponent(deleteSPBtn)
                                    .addGap(18, 18, 18)
                                    .addComponent(editSPBtn))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(maloaiBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(masanphamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tensanphamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(soluongTf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(addSPBtn1)
                                    .addGap(18, 18, 18)
                                    .addComponent(deleteSPBtn1)
                                    .addGap(18, 18, 18)
                                    .addComponent(editSPBtn1))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(machitietsanphamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(machitietsanphamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(masanpham2Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sizeBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(colorBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(soluong2Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dongiaTf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nsxTf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );
        capnhatsanphamPanelLayout.setVerticalGroup(
            capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhatsanphamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchSanPhamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSPBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSPBtn)
                    .addComponent(deleteSPBtn)
                    .addComponent(editSPBtn))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maloaiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(masanphamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tensanphamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soluongTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dongiaTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nsxTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(thongbaosanpham)
                .addGap(26, 26, 26)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSPBtn1)
                    .addComponent(deleteSPBtn1)
                    .addComponent(editSPBtn1))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(machitietsanphamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(machitietsanphamTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(masanpham2Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(capnhatsanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soluong2Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nhaphangPanel.setOpaque(false);

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        searchSPBtn1.setText("SEARCH");

        addSPBtn2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addSPBtn2.setForeground(new java.awt.Color(255, 255, 255));
        addSPBtn2.setText("ADD");
        addSPBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSPBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSPBtn2MouseClicked(evt);
            }
        });

        deleteSPBtn2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteSPBtn2.setForeground(new java.awt.Color(255, 255, 255));
        deleteSPBtn2.setText("DELETE");
        deleteSPBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSPBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSPBtn2MouseClicked(evt);
            }
        });

        editSPBtn2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editSPBtn2.setForeground(new java.awt.Color(255, 255, 255));
        editSPBtn2.setText("EDIT");
        editSPBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSPBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSPBtn2MouseClicked(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Mã phiếu nhập");

        maphieunhapTf.setEnabled(false);
        maphieunhapTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maphieunhapTfActionPerformed(evt);
            }
        });

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Mã nhân viên ");

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Mã nhà cung cấp");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Tổng tiền");

        tongtienTf.setEnabled(false);

        addSPBtn3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addSPBtn3.setForeground(new java.awt.Color(255, 255, 255));
        addSPBtn3.setText("ADD");
        addSPBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSPBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSPBtn3MouseClicked(evt);
            }
        });

        deleteSPBtn3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteSPBtn3.setForeground(new java.awt.Color(255, 255, 255));
        deleteSPBtn3.setText("DELETE");
        deleteSPBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSPBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSPBtn3MouseClicked(evt);
            }
        });

        editSPBtn3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editSPBtn3.setForeground(new java.awt.Color(255, 255, 255));
        editSPBtn3.setText("EDIT");
        editSPBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSPBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSPBtn3MouseClicked(evt);
            }
        });

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Mã phiếu nhập");

        maphieunhapTf2.setEnabled(false);

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Mã sản phẩm");

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Số lượng");

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Đơn giá");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("NHẬP HÀNG");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("CHI TIẾT NHẬP HÀNG");

        manhacungcapBox.setPreferredSize(new java.awt.Dimension(200, 40));

        javax.swing.GroupLayout nhaphangPanelLayout = new javax.swing.GroupLayout(nhaphangPanel);
        nhaphangPanel.setLayout(nhaphangPanelLayout);
        nhaphangPanelLayout.setHorizontalGroup(
            nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhaphangPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                    .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(searchSPBtn1))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(addSPBtn2)
                            .addGap(18, 18, 18)
                            .addComponent(deleteSPBtn2)
                            .addGap(18, 18, 18)
                            .addComponent(editSPBtn2))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(maphieunhapTf))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(manhanvienTf))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tongtienTf))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(addSPBtn3)
                            .addGap(18, 18, 18)
                            .addComponent(deleteSPBtn3)
                            .addGap(18, 18, 18)
                            .addComponent(editSPBtn3))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(maphieunhapTf2))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(masanphamTf_ctnh))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(soluongTf_ctnh))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dongiaTf_ctnh))
                        .addGroup(nhaphangPanelLayout.createSequentialGroup()
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(manhacungcapBox, 0, 246, Short.MAX_VALUE))))
                .addContainerGap())
        );
        nhaphangPanelLayout.setVerticalGroup(
            nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhaphangPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSPBtn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSPBtn2)
                    .addComponent(deleteSPBtn2)
                    .addComponent(editSPBtn2))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maphieunhapTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manhanvienTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manhacungcapBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongtienTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSPBtn3)
                    .addComponent(deleteSPBtn3)
                    .addComponent(editSPBtn3))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maphieunhapTf2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(masanphamTf_ctnh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soluongTf_ctnh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhaphangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dongiaTf_ctnh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        nhacungcapPanel.setOpaque(false);

        nhacungcapSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhacungcapSearchActionPerformed(evt);
            }
        });

        searchSPBtn2.setText("SEARCH");
        searchSPBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSPBtn2ActionPerformed(evt);
            }
        });

        addSPBtn4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addSPBtn4.setForeground(new java.awt.Color(255, 255, 255));
        addSPBtn4.setText("ADD");
        addSPBtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSPBtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSPBtn4MouseClicked(evt);
            }
        });

        deleteSPBtn4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteSPBtn4.setForeground(new java.awt.Color(255, 255, 255));
        deleteSPBtn4.setText("DELETE");
        deleteSPBtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSPBtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSPBtn4MouseClicked(evt);
            }
        });

        editSPBtn4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editSPBtn4.setForeground(new java.awt.Color(255, 255, 255));
        editSPBtn4.setText("EDIT");
        editSPBtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSPBtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSPBtn4MouseClicked(evt);
            }
        });

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Mã nhà cung cấp");

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Tên nhà cung cấp");

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Địa chỉ ");

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Số điện thoại");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("NHÀ CUNG CẤP");

        javax.swing.GroupLayout nhacungcapPanelLayout = new javax.swing.GroupLayout(nhacungcapPanel);
        nhacungcapPanel.setLayout(nhacungcapPanelLayout);
        nhacungcapPanelLayout.setHorizontalGroup(
            nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                        .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                                .addComponent(nhacungcapSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchSPBtn2))
                            .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                                .addComponent(addSPBtn4)
                                .addGap(18, 18, 18)
                                .addComponent(deleteSPBtn4)
                                .addGap(18, 18, 18)
                                .addComponent(editSPBtn4)))
                        .addGap(0, 59, Short.MAX_VALUE))
                    .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(manhacungcapTf_ncc))
                    .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tennhacungcapTf_ncc))
                    .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(diachiTf_ncc))
                    .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sdtTf_ncc)))
                .addContainerGap())
        );
        nhacungcapPanelLayout.setVerticalGroup(
            nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhacungcapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nhacungcapSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSPBtn2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSPBtn4)
                    .addComponent(deleteSPBtn4)
                    .addComponent(editSPBtn4))
                .addGap(18, 18, 18)
                .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manhacungcapTf_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tennhacungcapTf_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diachiTf_ncc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nhacungcapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdtTf_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(490, Short.MAX_VALUE))
        );

        loaisanphamPanel.setOpaque(false);

        loaiSearchTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loaiSearchTfActionPerformed(evt);
            }
        });

        searchSPBtn3.setText("SEARCH");
        searchSPBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSPBtn3ActionPerformed(evt);
            }
        });

        addSPBtn6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addSPBtn6.setForeground(new java.awt.Color(255, 255, 255));
        addSPBtn6.setText("ADD");
        addSPBtn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSPBtn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSPBtn6MouseClicked(evt);
            }
        });

        deleteSPBtn6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteSPBtn6.setForeground(new java.awt.Color(255, 255, 255));
        deleteSPBtn6.setText("DELETE");
        deleteSPBtn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteSPBtn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSPBtn6MouseClicked(evt);
            }
        });

        editSPBtn6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editSPBtn6.setForeground(new java.awt.Color(255, 255, 255));
        editSPBtn6.setText("EDIT");
        editSPBtn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editSPBtn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSPBtn6MouseClicked(evt);
            }
        });

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Mã Loại ");

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Tên loại");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("LOẠI SẢN PHẨM");

        javax.swing.GroupLayout loaisanphamPanelLayout = new javax.swing.GroupLayout(loaisanphamPanel);
        loaisanphamPanel.setLayout(loaisanphamPanelLayout);
        loaisanphamPanelLayout.setHorizontalGroup(
            loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaisanphamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(loaisanphamPanelLayout.createSequentialGroup()
                        .addGroup(loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loaisanphamPanelLayout.createSequentialGroup()
                                .addComponent(loaiSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchSPBtn3))
                            .addGroup(loaisanphamPanelLayout.createSequentialGroup()
                                .addComponent(addSPBtn6)
                                .addGap(18, 18, 18)
                                .addComponent(deleteSPBtn6)
                                .addGap(18, 18, 18)
                                .addComponent(editSPBtn6))
                            .addGroup(loaisanphamPanelLayout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(maloaiTf_loaisanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(loaisanphamPanelLayout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tenloaiTf_loaisanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );
        loaisanphamPanelLayout.setVerticalGroup(
            loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaisanphamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loaiSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSPBtn3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSPBtn6)
                    .addComponent(deleteSPBtn6)
                    .addComponent(editSPBtn6))
                .addGap(18, 18, 18)
                .addGroup(loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maloaiTf_loaisanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loaisanphamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenloaiTf_loaisanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(606, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout productLeftPanelLayout = new javax.swing.GroupLayout(productLeftPanel);
        productLeftPanel.setLayout(productLeftPanelLayout);
        productLeftPanelLayout.setHorizontalGroup(
            productLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productLeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(productLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(productLeftPanelLayout.createSequentialGroup()
                        .addComponent(capnhatlabel)
                        .addGap(18, 18, 18)
                        .addComponent(nhaphanglabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(capnhatsanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhaphangPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhacungcapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loaisanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        productLeftPanelLayout.setVerticalGroup(
            productLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productLeftPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(productLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(capnhatlabel)
                    .addComponent(nhaphanglabel)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(capnhatsanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nhaphangPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nhacungcapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(loaisanphamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        orderLeftPanel.setOpaque(false);
        orderLeftPanel.setPreferredSize(new java.awt.Dimension(477, 1529));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Cập nhật hóa đơn");
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Chương trình giảm giá");
        jLabel65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel65MouseClicked(evt);
            }
        });

        capnhathoadonPanel.setOpaque(false);

        searchid_hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchid_hdActionPerformed(evt);
            }
        });
        searchid_hd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchid_hdKeyPressed(evt);
            }
        });

        searchSPBtn4.setText("SEARCH");
        searchSPBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSPBtn4ActionPerformed(evt);
            }
        });

        deletehd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deletehd.setForeground(new java.awt.Color(204, 0, 0));
        deletehd.setText("XÓA HÓA ĐƠN");
        deletehd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletehd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletehdMouseClicked(evt);
            }
        });

        tthdlabel.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        tthdlabel.setForeground(new java.awt.Color(255, 255, 255));

        ttcthdlabel.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        ttcthdlabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("HÓA ĐƠN");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("CHI TIẾT HÓA ĐƠN");

        javax.swing.GroupLayout capnhathoadonPanelLayout = new javax.swing.GroupLayout(capnhathoadonPanel);
        capnhathoadonPanel.setLayout(capnhathoadonPanelLayout);
        capnhathoadonPanelLayout.setHorizontalGroup(
            capnhathoadonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhathoadonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(capnhathoadonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tthdlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ttcthdlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(capnhathoadonPanelLayout.createSequentialGroup()
                        .addGroup(capnhathoadonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deletehd)
                            .addGroup(capnhathoadonPanelLayout.createSequentialGroup()
                                .addComponent(searchid_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchstring_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchSPBtn4)))
                        .addGap(0, 206, Short.MAX_VALUE)))
                .addContainerGap())
        );
        capnhathoadonPanelLayout.setVerticalGroup(
            capnhathoadonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capnhathoadonPanelLayout.createSequentialGroup()
                .addGroup(capnhathoadonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchid_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, capnhathoadonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchstring_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchSPBtn4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deletehd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tthdlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jLabel80)
                .addGap(18, 18, 18)
                .addComponent(ttcthdlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        chuongtrinhgiamgiaPanel.setOpaque(false);
        chuongtrinhgiamgiaPanel.setPreferredSize(new java.awt.Dimension(432, 342));

        searchid_gg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchid_ggActionPerformed(evt);
            }
        });

        searchSPBtn7.setText("SEARCH");
        searchSPBtn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSPBtn7ActionPerformed(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("Giảm Giá");

        addggbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addggbt.setForeground(new java.awt.Color(255, 255, 255));
        addggbt.setText("ADD");
        addggbt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addggbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addggbtMouseClicked(evt);
            }
        });

        deleteggbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteggbt.setForeground(new java.awt.Color(255, 255, 255));
        deleteggbt.setText("DELETE");
        deleteggbt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteggbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteggbtMouseClicked(evt);
            }
        });

        editggbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editggbt.setForeground(new java.awt.Color(255, 255, 255));
        editggbt.setText("EDIT");
        editggbt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editggbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editggbtMouseClicked(evt);
            }
        });

        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Mã giảm giá");

        maggtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                maggtfKeyPressed(evt);
            }
        });

        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Tên giảm giá");

        tenggtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tenggtfKeyPressed(evt);
            }
        });

        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("Ngày bắt đầu");

        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("Ngày kết thúc");

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setText("Chi tiết giảm giá");

        addctggbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addctggbt.setForeground(new java.awt.Color(255, 255, 255));
        addctggbt.setText("ADD");
        addctggbt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addctggbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addctggbtMouseClicked(evt);
            }
        });

        deletectggbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deletectggbt.setForeground(new java.awt.Color(255, 255, 255));
        deletectggbt.setText("DELETE");
        deletectggbt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletectggbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletectggbtMouseClicked(evt);
            }
        });

        editctggbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editctggbt.setForeground(new java.awt.Color(255, 255, 255));
        editctggbt.setText("EDIT");
        editctggbt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editctggbt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editctggbtMouseClicked(evt);
            }
        });

        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("Mã sản Phẩm");

        idsptf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idsptfKeyPressed(evt);
            }
        });

        jLabel112.setForeground(new java.awt.Color(255, 255, 255));
        jLabel112.setText("Phần trăm giảm");

        discounttf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                discounttfKeyPressed(evt);
            }
        });

        tensplabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        datebd.setDateFormatString("yyyy-MM-dd");
        datebd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        datekt.setDateFormatString("yyyy-MM-dd");
        datekt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout chuongtrinhgiamgiaPanelLayout = new javax.swing.GroupLayout(chuongtrinhgiamgiaPanel);
        chuongtrinhgiamgiaPanel.setLayout(chuongtrinhgiamgiaPanelLayout);
        chuongtrinhgiamgiaPanelLayout.setHorizontalGroup(
            chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                        .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                        .addComponent(addggbt)
                                        .addGap(18, 18, 18)
                                        .addComponent(deleteggbt)
                                        .addGap(18, 18, 18)
                                        .addComponent(editggbt))
                                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tenggtf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(datebd, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(datekt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                        .addComponent(addctggbt)
                                        .addGap(18, 18, 18)
                                        .addComponent(deletectggbt)
                                        .addGap(18, 18, 18)
                                        .addComponent(editctggbt))
                                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                        .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel111, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel112, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(discounttf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                                .addComponent(idsptf, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tensplabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(maggtf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 26, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                        .addComponent(searchid_gg, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchstring_gg, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchSPBtn7)
                        .addGap(19, 19, 19))))
            .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        chuongtrinhgiamgiaPanelLayout.setVerticalGroup(
            chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chuongtrinhgiamgiaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchid_gg, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSPBtn7)
                    .addComponent(searchstring_gg, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel106)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addggbt)
                    .addComponent(deleteggbt)
                    .addComponent(editggbt))
                .addGap(18, 18, 18)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maggtf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenggtf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datebd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel101, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datekt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addctggbt)
                    .addComponent(deletectggbt)
                    .addComponent(editctggbt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tensplabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idsptf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(chuongtrinhgiamgiaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discounttf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        chuongtrinhgiamgiaPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {deletectggbt, discounttf, editctggbt, idsptf, jLabel108, jLabel111, jLabel112});

        javax.swing.GroupLayout orderLeftPanelLayout = new javax.swing.GroupLayout(orderLeftPanel);
        orderLeftPanel.setLayout(orderLeftPanelLayout);
        orderLeftPanelLayout.setHorizontalGroup(
            orderLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderLeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addGap(18, 18, 18)
                .addGroup(orderLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderLeftPanelLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel65))
                    .addGroup(orderLeftPanelLayout.createSequentialGroup()
                        .addComponent(chuongtrinhgiamgiaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capnhathoadonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        orderLeftPanelLayout.setVerticalGroup(
            orderLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderLeftPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(orderLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65))
                .addGap(27, 27, 27)
                .addGroup(orderLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(capnhathoadonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chuongtrinhgiamgiaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(633, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(0, 4076, Short.MAX_VALUE)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4077, Short.MAX_VALUE))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(0, 26, Short.MAX_VALUE)
                .addComponent(productLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE)
                .addComponent(orderLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8746, Short.MAX_VALUE))
        );

        khachhangpanel.setBackground(new java.awt.Color(255, 255, 255));
        khachhangpanel.setPreferredSize(new java.awt.Dimension(2000, 11778));

        hoTf_khach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hoTf_khachFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hoTf_khachFocusLost(evt);
            }
        });
        hoTf_khach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoTf_khachActionPerformed(evt);
            }
        });

        tenTf_khach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tenTf_khachFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tenTf_khachFocusLost(evt);
            }
        });
        tenTf_khach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenTf_khachActionPerformed(evt);
            }
        });

        gmailTf_khach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                gmailTf_khachFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                gmailTf_khachFocusLost(evt);
            }
        });
        gmailTf_khach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gmailTf_khachActionPerformed(evt);
            }
        });

        searchTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTfActionPerformed(evt);
            }
        });
        searchTf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTfKeyReleased(evt);
            }
        });

        jPanel1.setBackground( new Color(31, 190, 214));
        jPanel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        sdtTf_khach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sdtTf_khachFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                sdtTf_khachFocusLost(evt);
            }
        });
        sdtTf_khach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sdtTf_khachActionPerformed(evt);
            }
        });

        themBtn_khach.setText("THÊM");
        themBtn_khach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        themBtn_khach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themBtn_khachMouseClicked(evt);
            }
        });

        suaBtn_khach.setText("SỬA");
        suaBtn_khach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        suaBtn_khach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suaBtn_khachMouseClicked(evt);
            }
        });

        xoaBtn_khach.setText("XÓA");
        xoaBtn_khach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        xoaBtn_khach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xoaBtn_khachMouseClicked(evt);
            }
        });

        dsKHTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dsKHTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsKHTableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(dsKHTable);

        jgioitinhNam.setText("NAM");

        jgioitinhNU.setText("NỮ");

        jLabel68.setText("HỌ ");

        jLabel69.setText("TÊN ");

        jLabel70.setText("SĐT");

        jLabel71.setText("GMAIL");

        displaySPConhang10.setBackground(new Color(36, 37, 42));
        displaySPConhang10.setForeground(new java.awt.Color(255, 255, 255));
        displaySPConhang10.setText("IMPORT");
        displaySPConhang10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPConhang10MouseClicked(evt);
            }
        });

        displaySPConhang11.setBackground(new Color(36, 37, 42));
        displaySPConhang11.setForeground(new java.awt.Color(255, 255, 255));
        displaySPConhang11.setText("EXPORT");
        displaySPConhang11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPConhang11MouseClicked(evt);
            }
        });

        khachhangbackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        khachhangbackground.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        resetBtn_khach.setText("RESET");
        resetBtn_khach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetBtn_khach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetBtn_khachMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout khachhangpanelLayout = new javax.swing.GroupLayout(khachhangpanel);
        khachhangpanel.setLayout(khachhangpanelLayout);
        khachhangpanelLayout.setHorizontalGroup(
            khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(khachhangpanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(khachhangpanelLayout.createSequentialGroup()
                        .addComponent(khachhangbackground, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(khachhangpanelLayout.createSequentialGroup()
                                .addComponent(hoTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tenTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(khachhangpanelLayout.createSequentialGroup()
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(khachhangpanelLayout.createSequentialGroup()
                                .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sdtTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(khachhangpanelLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(khachhangpanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(gmailTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(khachhangpanelLayout.createSequentialGroup()
                                .addComponent(jgioitinhNam, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jgioitinhNU, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(khachhangpanelLayout.createSequentialGroup()
                        .addComponent(searchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(displaySPConhang10)
                        .addGap(18, 18, 18)
                        .addComponent(displaySPConhang11)
                        .addGap(18, 18, 18)
                        .addComponent(themBtn_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(suaBtn_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(xoaBtn_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resetBtn_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1735, Short.MAX_VALUE))
        );
        khachhangpanelLayout.setVerticalGroup(
            khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(khachhangbackground, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(khachhangpanelLayout.createSequentialGroup()
                        .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tenTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sdtTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gmailTf_khach, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jgioitinhNam)
                            .addComponent(jgioitinhNU))))
                .addGap(64, 64, 64)
                .addGroup(khachhangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displaySPConhang10)
                    .addComponent(displaySPConhang11)
                    .addComponent(themBtn_khach)
                    .addComponent(suaBtn_khach)
                    .addComponent(xoaBtn_khach)
                    .addComponent(resetBtn_khach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10945, Short.MAX_VALUE))
        );

        nhanvienpanel.setBackground(new java.awt.Color(255, 255, 255));
        nhanvienpanel.setPreferredSize(new java.awt.Dimension(2000, 11778));

        jLabel3.setBackground( new Color(31, 190, 214));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("           NHÂN VIÊN");
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setAlignmentY(0.0F);
        jLabel3.setOpaque(true);

        hoTf_nhanvien.setForeground(new java.awt.Color(153, 153, 153));
        hoTf_nhanvien.setText("Nhập họ của nhân viên");
        hoTf_nhanvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hoTf_nhanvienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hoTf_nhanvienFocusLost(evt);
            }
        });
        hoTf_nhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoTf_nhanvienActionPerformed(evt);
            }
        });
        hoTf_nhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hoTf_nhanvienKeyTyped(evt);
            }
        });

        tenTf_nhanvien.setForeground(new java.awt.Color(153, 153, 153));
        tenTf_nhanvien.setText("Nhập tên của nhân viên");
        tenTf_nhanvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tenTf_nhanvienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tenTf_nhanvienFocusLost(evt);
            }
        });

        diachiTf_nhanvien.setForeground(new java.awt.Color(153, 153, 153));
        diachiTf_nhanvien.setText("Nhập địa chỉ của nhân viên");
        diachiTf_nhanvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                diachiTf_nhanvienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                diachiTf_nhanvienFocusLost(evt);
            }
        });

        dsnvTb_nhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Nhân Viên", "Họ", "Tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Email", "Số Điện Thoại", "Lương"
            }
        ));
        dsnvTb_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dsnvTb_nhanvienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(dsnvTb_nhanvien);

        emailTf_nhanvien.setForeground(new java.awt.Color(153, 153, 153));
        emailTf_nhanvien.setText("Nhập email của nhân viên");
        emailTf_nhanvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTf_nhanvienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTf_nhanvienFocusLost(evt);
            }
        });
        emailTf_nhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTf_nhanvienActionPerformed(evt);
            }
        });

        sdtTf_nhanvien.setForeground(new java.awt.Color(153, 153, 153));
        sdtTf_nhanvien.setText("Nhập số điện thoại của nhân viên");
        sdtTf_nhanvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sdtTf_nhanvienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                sdtTf_nhanvienFocusLost(evt);
            }
        });
        sdtTf_nhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sdtTf_nhanvienActionPerformed(evt);
            }
        });

        jDCns_nhanvien.setDateFormatString("yyyy-MM-dd");

        luongTf_nhanvien.setForeground(new java.awt.Color(153, 153, 153));
        luongTf_nhanvien.setText("Nhập lương nhân viên");
        luongTf_nhanvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                luongTf_nhanvienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                luongTf_nhanvienFocusLost(evt);
            }
        });

        jCBNam.setText("Nam");

        jCBNu.setText("Nữ");
        jCBNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBNuActionPerformed(evt);
            }
        });

        jLabel5.setText("Họ");

        jLabel6.setText("Tên");

        jLabel45.setText("Địa chỉ ");

        jLabel46.setText("Số điện thoại ");

        jLabel47.setText("Ngày sinh ");

        jLabel53.setText("Email ");

        jLabel66.setText("Giới tính");

        jLabel67.setText("Lương ");

        timkiemTf_nhanvien.setForeground(new java.awt.Color(153, 153, 153));
        timkiemTf_nhanvien.setText("Nhập nội dung cần tìm vào đây ");
        timkiemTf_nhanvien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                timkiemTf_nhanvienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                timkiemTf_nhanvienFocusLost(evt);
            }
        });
        timkiemTf_nhanvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timkiemTf_nhanvienKeyReleased(evt);
            }
        });

        them_nv.setText("ADD");
        them_nv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                them_nvMouseClicked(evt);
            }
        });

        sua_nv.setText("EDIT");
        sua_nv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sua_nvMouseClicked(evt);
            }
        });

        xoa_nv.setText("DELETE");
        xoa_nv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xoa_nvMouseClicked(evt);
            }
        });

        reset_nv.setText("RESET");
        reset_nv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reset_nvMouseClicked(evt);
            }
        });

        displaySPConhang12.setBackground(new Color(36, 37, 42));
        displaySPConhang12.setForeground(new java.awt.Color(255, 255, 255));
        displaySPConhang12.setText("IMPORT");
        displaySPConhang12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPConhang12MouseClicked(evt);
            }
        });

        displaySPConhang13.setBackground(new Color(36, 37, 42));
        displaySPConhang13.setForeground(new java.awt.Color(255, 255, 255));
        displaySPConhang13.setText("EXPORT");
        displaySPConhang13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displaySPConhang13MouseClicked(evt);
            }
        });
        displaySPConhang13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displaySPConhang13ActionPerformed(evt);
            }
        });

        nhanvienbackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nhanvienbackground.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        timid_nv.setForeground(new java.awt.Color(153, 153, 153));
        timid_nv.setText("Tìm IDNV");
        timid_nv.setPreferredSize(new java.awt.Dimension(50, 35));
        timid_nv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                timid_nvFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                timid_nvFocusLost(evt);
            }
        });
        timid_nv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timid_nvKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout nhanvienpanelLayout = new javax.swing.GroupLayout(nhanvienpanel);
        nhanvienpanel.setLayout(nhanvienpanelLayout);
        nhanvienpanelLayout.setHorizontalGroup(
            nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienpanelLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 2012, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(nhanvienpanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sdtTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nhanvienpanelLayout.createSequentialGroup()
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nhanvienpanelLayout.createSequentialGroup()
                                .addComponent(jCBNam, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCBNu, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDCns_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(diachiTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(nhanvienpanelLayout.createSequentialGroup()
                            .addComponent(hoTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tenTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nhanvienpanelLayout.createSequentialGroup()
                            .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(luongTf_nhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailTf_nhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                    .addGroup(nhanvienpanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nhanvienbackground, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nhanvienpanelLayout.createSequentialGroup()
                        .addComponent(timid_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timkiemTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(them_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xoa_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sua_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reset_nv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(displaySPConhang12)
                        .addGap(18, 18, 18)
                        .addComponent(displaySPConhang13))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nhanvienpanelLayout.setVerticalGroup(
            nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienpanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nhanvienpanelLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tenTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(jLabel67))
                        .addGap(3, 3, 3)
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(luongTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(diachiTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sdtTf_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(jCBNam)
                            .addComponent(jCBNu))
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nhanvienpanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel47))
                            .addGroup(nhanvienpanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jDCns_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(nhanvienpanelLayout.createSequentialGroup()
                        .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(nhanvienpanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(nhanvienpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(them_nv)
                                    .addComponent(xoa_nv)
                                    .addComponent(sua_nv)
                                    .addComponent(reset_nv)
                                    .addComponent(displaySPConhang12)
                                    .addComponent(displaySPConhang13)))
                            .addComponent(timid_nv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timkiemTf_nhanvien, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nhanvienbackground, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10794, Short.MAX_VALUE))
        );

        settingPanel.setBackground(new java.awt.Color(255, 255, 255));
        settingPanel.setPreferredSize(new java.awt.Dimension(2000, 11778));

        jLabel15.setBackground(new Color(68, 108, 179));
        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("KHÁCH HÀNG");
        jLabel15.setToolTipText("");
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel15.setAlignmentY(0.0F);
        jLabel15.setOpaque(true);

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable3);

        jButton7.setText("jButton1");

        jButton8.setText("jButton2");

        jButton9.setText("jButton3");

        javax.swing.GroupLayout settingPanelLayout = new javax.swing.GroupLayout(settingPanel);
        settingPanel.setLayout(settingPanelLayout);
        settingPanelLayout.setHorizontalGroup(
            settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingPanelLayout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 2012, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1016, Short.MAX_VALUE))
            .addGroup(settingPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(settingPanelLayout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(settingPanelLayout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingPanelLayout.setVerticalGroup(
            settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(99, 99, 99)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10840, Short.MAX_VALUE))
        );

        dashboardPanel.setBackground(new java.awt.Color(255, 255, 255));
        dashboardPanel.setPreferredSize(new java.awt.Dimension(2000, 11778));

        jLabel29.setBackground(new Color(68, 108, 179));
        jLabel29.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("KHÁCH HÀNG");
        jLabel29.setToolTipText("");
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel29.setAlignmentY(0.0F);
        jLabel29.setOpaque(true);

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable4);

        jButton10.setText("jButton1");

        jButton11.setText("jButton2");

        jButton12.setText("jButton3");

        jTextField17.setText("jTextField4");
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 2012, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1012, Short.MAX_VALUE))
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12))
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addGap(42, 42, 42)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10895, Short.MAX_VALUE))
        );

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 1000));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBackground(new java.awt.Color(255, 102, 51));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 600));

        jLabel72.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 48)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel72)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel72)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new Color(68, 108, 179));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(1000, 300));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setBackground(new java.awt.Color(255, 204, 0));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel5.setLayout(new java.awt.GridLayout(2, 1));

        phat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(phat);

        jLabel74.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Phan Minh Phát");
        jLabel74.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel5.add(jLabel74);

        jPanel4.add(jPanel5);

        jPanel9.setBackground(new java.awt.Color(102, 102, 255));
        jPanel9.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel9.setLayout(new java.awt.GridLayout(2, 1));

        phu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(phu);

        jLabel76.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Nguyễn Trọng Phú");
        jLabel76.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel9.add(jLabel76);

        jPanel4.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(0, 255, 153));
        jPanel10.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel10.setLayout(new java.awt.GridLayout(2, 1));

        nghia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel10.add(nghia);

        jLabel78.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Lê Trọng Nghĩa");
        jLabel78.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel10.add(jLabel78);

        jPanel4.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 0, 51));
        jPanel11.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel11.setLayout(new java.awt.GridLayout(2, 1));

        nam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(nam);

        jLabel82.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Nguyễn Hữu Hoài Nam");
        jLabel82.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel11.add(jLabel82);

        jPanel4.add(jPanel11);

        jPanel2.add(jPanel4);

        sellPanel.setBackground(new Color(36, 37, 42));
        sellPanel.setLayout(new java.awt.GridBagLayout());

        row1.setBackground(new java.awt.Color(153, 51, 255));
        row1.setOpaque(false);
        row1.setPreferredSize(new java.awt.Dimension(100, 100));
        row1.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        row1col1.setBackground(new Color( 31, 190, 214));

        jPanel22.setOpaque(false);
        jPanel22.setLayout(new java.awt.GridLayout(3, 1, 10, 10));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("NGÀY:");

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("Hóa Đơn");

        idhoadontf_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        idhoadontf_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idhoadontf_sellActionPerformed(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("Nhân viên");

        iduserloginlabel_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        iduserloginlabel_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                iduserloginlabel_sellKeyPressed(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel97.setText("Mã sale");

        idggtf_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        idggtf_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idggtf_sellKeyPressed(evt);
            }
        });

        datelabel_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        datelabel_sell.setForeground(new java.awt.Color(255, 255, 255));

        tennvtf_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tennvtf_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tennvtf_sellKeyPressed(evt);
            }
        });

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel115.setText("Khách hàng");

        idkhtf_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        idkhtf_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idkhtf_sellKeyPressed(evt);
            }
        });

        tenkhtf_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tenkhtf_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tenkhtf_sellKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout row1col1Layout = new javax.swing.GroupLayout(row1col1);
        row1col1.setLayout(row1col1Layout);
        row1col1Layout.setHorizontalGroup(
            row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, row1col1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(row1col1Layout.createSequentialGroup()
                        .addComponent(jLabel93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datelabel_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(row1col1Layout.createSequentialGroup()
                        .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(row1col1Layout.createSequentialGroup()
                                .addComponent(iduserloginlabel_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tennvtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(idhoadontf_sell)
                            .addComponent(idggtf_sell)))
                    .addGroup(row1col1Layout.createSequentialGroup()
                        .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idkhtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenkhtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        row1col1Layout.setVerticalGroup(
            row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(row1col1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(datelabel_sell))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idhoadontf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(row1col1Layout.createSequentialGroup()
                        .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iduserloginlabel_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idggtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tennvtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(row1col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idkhtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenkhtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        row1.add(row1col1);

        row1col2.setBackground(new Color( 31, 190, 214));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("IDSANPHAM");

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setText("GIÁ TIỀN");

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setText("DISCOUNT");

        idsptf_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        idsptf_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idsptf_sellKeyPressed(evt);
            }
        });

        tientf_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        discounttf_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("TIỀN GIẢM");

        tgtf_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("THÔNG BÁO :");

        thongbaosanphamtf_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        thongbaosanphamtf_sell.setForeground(new java.awt.Color(255, 255, 255));

        tensptf_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tensptf_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tensptf_sellKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout row1col2Layout = new javax.swing.GroupLayout(row1col2);
        row1col2.setLayout(row1col2Layout);
        row1col2Layout.setHorizontalGroup(
            row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row1col2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(row1col2Layout.createSequentialGroup()
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idsptf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tensptf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(row1col2Layout.createSequentialGroup()
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tientf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(row1col2Layout.createSequentialGroup()
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(discounttf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(row1col2Layout.createSequentialGroup()
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tgtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, row1col2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel107)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(thongbaosanphamtf_sell)
                .addGap(350, 350, 350))
        );
        row1col2Layout.setVerticalGroup(
            row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row1col2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, row1col2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idsptf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tensptf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tientf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discounttf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tgtf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(row1col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel107)
                    .addComponent(thongbaosanphamtf_sell))
                .addContainerGap())
        );

        row1.add(row1col2);

        row1col3.setBackground(new Color( 31, 190, 214));

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText("SIZE");

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("COLOR");

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("SỐ LƯỢNG");

        size_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        size_sell.setForeground(new java.awt.Color(255, 255, 255));
        size_sell.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn size" }));
        size_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                size_sellActionPerformed(evt);
            }
        });

        color_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        color_sell.setForeground(new java.awt.Color(255, 255, 255));
        color_sell.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn màu"}));
        color_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                color_sellActionPerformed(evt);
            }
        });

        giamslbt_sell.setBackground(new Color(36, 37, 42));
        giamslbt_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        giamslbt_sell.setForeground(new java.awt.Color(255, 255, 255));
        giamslbt_sell.setText("-");
        giamslbt_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giamslbt_sellMouseClicked(evt);
            }
        });

        sltf_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sltf_sell.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sltf_sell.setText("1");
        sltf_sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sltf_sellKeyPressed(evt);
            }
        });

        tangslbt_sell.setBackground(new Color(36, 37, 42));
        tangslbt_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tangslbt_sell.setForeground(new java.awt.Color(255, 255, 255));
        tangslbt_sell.setText("+");
        tangslbt_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tangslbt_sellMouseClicked(evt);
            }
        });

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setText("Thông báo:");

        thongbaoctsp_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        thongbaoctsp_sell.setForeground(new java.awt.Color(255, 255, 255));

        themcthdbtn_sell.setBackground(new java.awt.Color(102, 255, 0));
        themcthdbtn_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        themcthdbtn_sell.setText("Thêm vào hóa đơn");
        themcthdbtn_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themcthdbtn_sellActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout row1col3Layout = new javax.swing.GroupLayout(row1col3);
        row1col3.setLayout(row1col3Layout);
        row1col3Layout.setHorizontalGroup(
            row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row1col3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(row1col3Layout.createSequentialGroup()
                        .addComponent(jLabel114)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thongbaoctsp_sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(row1col3Layout.createSequentialGroup()
                        .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(row1col3Layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(giamslbt_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sltf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tangslbt_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(row1col3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(color_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(size_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(themcthdbtn_sell, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 186, Short.MAX_VALUE)))
                .addContainerGap())
        );
        row1col3Layout.setVerticalGroup(
            row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row1col3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(size_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(color_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giamslbt_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sltf_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tangslbt_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(themcthdbtn_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(row1col3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(thongbaoctsp_sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        row1.add(row1col3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 1800;
        gridBagConstraints.ipady = 300;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        sellPanel.add(row1, gridBagConstraints);

        row2.setBackground(new java.awt.Color(255, 204, 0));
        row2.setOpaque(false);
        row2.setPreferredSize(new java.awt.Dimension(100, 100));
        row2.setLayout(new java.awt.GridBagLayout());

        row2col1.setBackground(new Color(36, 37, 42));

        cthdtable_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cthdtable_sell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Sản phẩm", "Mã chi tiết sản phẩm", "Số lượng", "Đơn giá"
            }
        ));
        cthdtable_sell.setMinimumSize(new java.awt.Dimension(1450, 64));
        jScrollPane3.setViewportView(cthdtable_sell);

        jLabel81.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("DETAIL ORDER");

        javax.swing.GroupLayout row2col1Layout = new javax.swing.GroupLayout(row2col1);
        row2col1.setLayout(row2col1Layout);
        row2col1Layout.setHorizontalGroup(
            row2col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
            .addGroup(row2col1Layout.createSequentialGroup()
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        row2col1Layout.setVerticalGroup(
            row2col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, row2col1Layout.createSequentialGroup()
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 1450;
        gridBagConstraints.ipady = 600;
        row2.add(row2col1, gridBagConstraints);

        row2col2.setBackground(new Color( 31, 190, 214));
        row2col2.setOpaque(false);

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("SUMMARY");

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Tổng tiền ");

        ttlabel_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ttlabel_sell.setForeground(new java.awt.Color(255, 255, 255));
        ttlabel_sell.setText("0");

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Tiền giảm");

        ttglabel_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ttglabel_sell.setForeground(new java.awt.Color(255, 255, 255));
        ttglabel_sell.setText("0");

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Tiền phải trả");

        ttptlabel_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ttptlabel_sell.setForeground(new java.awt.Color(255, 255, 255));
        ttptlabel_sell.setText("0");

        carhlabel_sell.setBackground(new java.awt.Color(51, 255, 0));
        carhlabel_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carhlabel_sellMouseClicked(evt);
            }
        });

        cashlabel_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cashlabel_sell.setText("Cash");
        cashlabel_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashlabel_sellMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout carhlabel_sellLayout = new javax.swing.GroupLayout(carhlabel_sell);
        carhlabel_sell.setLayout(carhlabel_sellLayout);
        carhlabel_sellLayout.setHorizontalGroup(
            carhlabel_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carhlabel_sellLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(cashlabel_sell)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        carhlabel_sellLayout.setVerticalGroup(
            carhlabel_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carhlabel_sellLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cashlabel_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(0, 204, 255));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel73.setText("Credits Cash");
        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel73)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel73)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 102));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel75.setText("Wallet");
        jLabel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel75MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel75)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel75)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout row2col2Layout = new javax.swing.GroupLayout(row2col2);
        row2col2.setLayout(row2col2Layout);
        row2col2Layout.setHorizontalGroup(
            row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row2col2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(row2col2Layout.createSequentialGroup()
                        .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(row2col2Layout.createSequentialGroup()
                                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(ttlabel_sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(row2col2Layout.createSequentialGroup()
                                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(ttglabel_sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(row2col2Layout.createSequentialGroup()
                                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(ttptlabel_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(row2col2Layout.createSequentialGroup()
                                .addComponent(carhlabel_sell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 750, Short.MAX_VALUE)))
                .addContainerGap())
        );
        row2col2Layout.setVerticalGroup(
            row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row2col2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(row2col2Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addGap(18, 18, 18)
                        .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(ttlabel_sell))
                        .addGap(18, 18, 18)
                        .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(ttglabel_sell))
                        .addGap(18, 18, 18)
                        .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(ttptlabel_sell))
                        .addGap(103, 103, 103)
                        .addGroup(row2col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carhlabel_sell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(299, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 750;
        gridBagConstraints.ipady = 500;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 50, 50);
        row2.add(row2col2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 1800;
        gridBagConstraints.ipady = 450;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        sellPanel.add(row2, gridBagConstraints);

        row3.setBackground(new java.awt.Color(102, 255, 102));
        row3.setOpaque(false);
        row3.setPreferredSize(new java.awt.Dimension(100, 100));
        row3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel13.setBackground(new java.awt.Color(153, 0, 255));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel41.setText("Trở về trang chủ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 94, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addGap(0, 93, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        row3.add(jPanel13);

        delhdpn_sell.setBackground(new java.awt.Color(204, 255, 0));
        delhdpn_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delhdpn_sellMouseClicked(evt);
            }
        });

        delhdlabel_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        delhdlabel_sell.setText("Xóa hóa đơn");
        delhdlabel_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delhdlabel_sellMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout delhdpn_sellLayout = new javax.swing.GroupLayout(delhdpn_sell);
        delhdpn_sell.setLayout(delhdpn_sellLayout);
        delhdpn_sellLayout.setHorizontalGroup(
            delhdpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
            .addGroup(delhdpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(delhdpn_sellLayout.createSequentialGroup()
                    .addGap(0, 115, Short.MAX_VALUE)
                    .addComponent(delhdlabel_sell)
                    .addGap(0, 115, Short.MAX_VALUE)))
        );
        delhdpn_sellLayout.setVerticalGroup(
            delhdpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
            .addGroup(delhdpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(delhdpn_sellLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(delhdlabel_sell)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        row3.add(delhdpn_sell);

        editslpn_sell.setBackground(new java.awt.Color(102, 255, 102));
        editslpn_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editslpn_sellMouseClicked(evt);
            }
        });

        editsllabel_sell.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        editsllabel_sell.setText("Sửa số lượng");
        editsllabel_sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editsllabel_sellMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout editslpn_sellLayout = new javax.swing.GroupLayout(editslpn_sell);
        editslpn_sell.setLayout(editslpn_sellLayout);
        editslpn_sellLayout.setHorizontalGroup(
            editslpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
            .addGroup(editslpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(editslpn_sellLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(editsllabel_sell)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        editslpn_sellLayout.setVerticalGroup(
            editslpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
            .addGroup(editslpn_sellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(editslpn_sellLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(editsllabel_sell)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        row3.add(editslpn_sell);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 1000;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        sellPanel.add(row3, gridBagConstraints);

        javax.swing.GroupLayout bigPanelLayout = new javax.swing.GroupLayout(bigPanel);
        bigPanel.setLayout(bigPanelLayout);
        bigPanelLayout.setHorizontalGroup(
            bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bigPanelLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 8630, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1258, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(217, 217, 217)
                    .addComponent(khachhangpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 2993, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1260, Short.MAX_VALUE)))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(212, 212, 212)
                    .addComponent(nhanvienpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 3008, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1250, Short.MAX_VALUE)))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(202, 202, 202)
                    .addComponent(settingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 3028, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1228, Short.MAX_VALUE)))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(216, 216, 216)
                    .addComponent(dashboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 3024, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1230, Short.MAX_VALUE)))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(sellPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bigPanelLayout.setVerticalGroup(
            bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(leftPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 11864, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bigPanelLayout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 11851, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(khachhangpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 11864, Short.MAX_VALUE))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addComponent(nhanvienpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 11851, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addComponent(settingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 11851, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addComponent(dashboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 11851, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bigPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(sellPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productLabelMouseClicked
        // TODO add your handling code here:
        setBoderMenu();
        setVisibleMenu();
        productLeftPanel.setVisible(true);
        leftPanel.setVisible(true);
        mainPanel.setVisible(true);
        productRightPanel.setVisible(false);
        
        
        productLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
    }//GEN-LAST:event_productLabelMouseClicked
void checkuserlogin(String a){
        
       QLNVBUS nvb=new QLNVBUS();
       nvb.docDSNV();
       QLNVDTO nv=new QLNVDTO();
       nv=nvb.getNv(a);
       iduserloginlabel_sell.setText(nv.getId());
       iduserloginlabel_sell.setEnabled(false);
       tennvtf_sell.setText(nv.getHo()+" "+nv.getTen());
       tennvtf_sell.setEnabled(false);
}
void checkidhd() throws SQLException{
    hoadonBUS hdb=new hoadonBUS();
    hdb.docbus();
    int i=hdb.getDshd().size();
    int b=Integer.parseInt(hdb.getDshd().get(i-1).getMahd())+1;
    idhoadontf_sell.setText("HD"+b);
    idhoadontf_sell.setEnabled(false);
}
    private void sellLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellLabelMouseClicked
        // TODO add your handling code here:
        setBoderMenu();
        setVisibleMenu();
        menuPanel.setVisible(false);
        jPanel2.setVisible(false);
        sellPanel.setVisible(true);
        sellLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        try {
            checkidhd();
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkuserlogin(userlogin);
    }//GEN-LAST:event_sellLabelMouseClicked

    private void orderLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderLabelMouseClicked
        // TODO add your handling code here:
        setBoderMenu();
        setVisibleMenu();
        leftPanel.setVisible(true);
        mainPanel.setVisible(true);
        orderLeftPanel.setVisible(true);
                productRightPanel.setVisible(false);

        orderLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
    }//GEN-LAST:event_orderLabelMouseClicked
    void docdsnv(){
        QLNVBUS bus = new QLNVBUS();
        if(bus.getDSNV()==null) bus.docDSNV();
        Vector header = new Vector();
            header.add("ID Nhân Viên");//tua de cot cua jtable
            header.add("Họ");
            header.add("Tên");
            header.add("Ngày Sinh");
            header.add("Giới Tính");
            header.add("Địa Chỉ");
            header.add("Email");
            header.add("Số Điện Thoại");
            header.add("Lương");
            modelnhanvien = new DefaultTableModel(header,0);
            for(QLNVDTO nv: bus.getDSNV())
            {
                Vector row=new Vector();
                row.add(nv.getId());
                row.add(nv.getHo());
                row.add(nv.getTen());
                row.add(nv.getNgaysinh());
                row.add(nv.getGioitinh());
                row.add(nv.getDiachi());
                row.add(nv.getEmail());
                row.add(nv.getSdt());
                row.add(nv.getLuong());
                modelnhanvien.addRow(row);
            }
            dsnvTb_nhanvien.setModel(modelnhanvien);
    }
    private void staffLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffLabelMouseClicked
        // TODO add your handling code here:
//        checkdn();
        if(Login1.typehienhanh==1){
            JOptionPane.showMessageDialog(null, "Không đủ thẩm quyền!");
            nhanvienpanel.removeMouseListener((MouseListener) evt);
        }
        else{
        btnGroupNV.add(jCBNu);
        btnGroupNV.add(jCBNam);
        docdsnv();
        setBoderMenu();
        setVisibleMenu();
        nhanvienpanel.setVisible(true);
        staffLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        clickxnhanvien();
        setmautextnhanvien();
        }
    }//GEN-LAST:event_staffLabelMouseClicked
    
    private void customerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerLabelMouseClicked
        // TODO add your handling code here:
        setBoderMenu();
        setVisibleMenu();
        khachhangpanel.setVisible(true);
        btnGroupKH.add(jgioitinhNU);
        btnGroupKH.add(jgioitinhNam);
        customerLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        try {
            docDSKH();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_customerLabelMouseClicked

    private void dashboardLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardLabelMouseClicked
        // TODO add your handling code here:
        setBoderMenu();
        setVisibleMenu();
        dashboardPanel.setVisible(true);
        
        dashboardLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));

    }//GEN-LAST:event_dashboardLabelMouseClicked

    private void settingLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingLabelMouseClicked
        // TODO add your handling code here:
        setBoderMenu();
        setVisibleMenu();
        //settingLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        settingPanel.setVisible(true);
               
    }//GEN-LAST:event_settingLabelMouseClicked

    private void searchSanPhamTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSanPhamTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchSanPhamTfActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void nhacungcapSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhacungcapSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nhacungcapSearchActionPerformed

    private void loaiSearchTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loaiSearchTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loaiSearchTfActionPerformed

    private void capnhatlabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capnhatlabelMouseClicked
        // TODO add your handling code here:
        setVisibleProduct();
        setVisibleSanPham();
        capnhatsanphamPanel.setVisible(true);
        capnhatsanphamtable.setVisible(true);
    }//GEN-LAST:event_capnhatlabelMouseClicked

    private void nhaphanglabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhaphanglabelMouseClicked
        // TODO add your handling code here:
        setVisibleProduct();
                setVisibleSanPham();
                      setVisibleSanPham();
  nhaphangtablepanel.setVisible(true);

        nhaphangPanel.setVisible(true);
    }//GEN-LAST:event_nhaphanglabelMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        setVisibleProduct();
        nhacungcapPanel.setVisible(true);
                setVisibleSanPham();
       nhacungcaptablepanel.setVisible(true);
   
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        setVisibleProduct();
                        setVisibleSanPham();

        loaisanphamPanel.setVisible(true);
               loaitablepanel.setVisible(true);

    }//GEN-LAST:event_jLabel8MouseClicked
public void dochoadon(){
    try{
            hoadonBUS hdb=new hoadonBUS();
            hdb.docbus();
            Vector header=new Vector();
            header.add("Mã hd");
            header.add("Mã kh");
            header.add("Mã nv");
            header.add("Mã giảm");
            header.add("Ngày");
            header.add("Tổng");
            header.add("Giảm");
            header.add("Phải trả");
            if(modelhd!=null)
                modelhd=new DefaultTableModel(header,0);
            for(hoadonDTO hd:hdb.getDshd()){
                Vector row=new Vector();
                row.add(hd.getMahd());
                row.add(hd.getMakh());
                row.add(hd.getManv());
                row.add(hd.getMagg());
                row.add(hd.getNgay());
                row.add(hd.getTong());
                row.add(hd.getTg());
                row.add(hd.getTpt());
                 modelhd.addRow(row);
            }
            jtbdshd.setModel(modelhd);
            header =new Vector();
            header.add("Mã hd");
            header.add("Mã sp");
            header.add("Mã chi tiết sản phẩm");
            header.add("Số lượng");
            header.add("Đơn giá");
            if(modelcthd != null) modelcthd=new DefaultTableModel(header,0);
            jtbcthd.setModel(modelcthd);
        }catch(Exception e){System.out.println(e);}
    } 
    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        // TODO add your handling code here:
        setVisibleProduct();
                        setVisibleSanPham();
                        capnhathoadonPanel.setVisible(true);
               capnhathoadontablepanel.setVisible(true);
               modelhd=new DefaultTableModel();
               modelcthd=new DefaultTableModel();
               dochoadon();
    }//GEN-LAST:event_jLabel64MouseClicked

    public void docgiamgia(){
    try{
            giamgiaBUS ggb=new giamgiaBUS();
            ggb.docBUS();
            Vector header=new Vector();
            header.add("Mã gg");
            header.add("Tên gg");
            header.add("Ngày bắt đầu");
            header.add("Ngày kết thúc");
            if(modelgg!=null)
                modelgg=new DefaultTableModel(header,0);
            for(giamgiaDTO gg:ggb.getds()){
                Vector row=new Vector();
                row.add(gg.getMagg());
                row.add(gg.getTengg());
                row.add(gg.getNgbd());
                row.add(gg.getNgkt());
                 modelgg.addRow(row);
            }
            giamgiatable.setModel(modelgg);
            header =new Vector();
            header.add("Mã gg");
            header.add("Mã sp");
            header.add("Phần trăm giảm");
            if(modelctgg != null) modelctgg=new DefaultTableModel(header,0);
            jtbcthd.setModel(modelctgg);
        }catch(Exception e){System.out.println(e);}
    }
    private void jLabel65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MouseClicked
        // TODO add your handling code here:
        setVisibleProduct();
        setVisibleSanPham();
                        chuongtrinhgiamgiaPanel.setVisible(true);
               chuongtrinhgiamgiatablepanel.setVisible(true);
               modelgg=new DefaultTableModel();
               modelctgg=new DefaultTableModel();
               docgiamgia();
    }//GEN-LAST:event_jLabel65MouseClicked

    private void searchid_hdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchid_hdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchid_hdActionPerformed

    private void addSPBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSPBtnMouseClicked
        try {
            // TODO add your handling code here:
            addSanPhamToTable();
            //cleanViewSanPham();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addSPBtnMouseClicked

    private void addSPBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSPBtn1MouseClicked
        
        try {
            // TODO add your handling code here:
            addChiTietSanPhamToTable();
            //cleanViewCTSP();
        } catch (Exception ex) {
            
        }
        
    }//GEN-LAST:event_addSPBtn1MouseClicked

    private void deleteSPBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteSPBtnMouseClicked
        try {
            // TODO add your handling code here:
            deleteSanPhamFromTable();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewSanPham();
    }//GEN-LAST:event_deleteSPBtnMouseClicked

    private void editSPBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSPBtnMouseClicked
        try {
            // TODO add your handling code here:
            editSanPham();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_editSPBtnMouseClicked

    private void deleteSPBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteSPBtn1MouseClicked
        try {
            // TODO add your handling code here:
            deleteChiTietSanPhamFromTable();
            //cleanViewCTSP();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteSPBtn1MouseClicked

    private void editSPBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSPBtn1MouseClicked
        try {
            // TODO add your handling code here:
            editChiTietSanPham();
            //cleanViewCTSP();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editSPBtn1MouseClicked
   
    private void searchSPBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSPBtnActionPerformed
        // TODO add your handling code here:
        String str = searchSanPhamTf.getText();
        
        //sanphamTable.setValueAt(evt, ERROR, NORMAL);
        ///.out.println("quanlycuahangthoitrang.GUI.frame.searchSPBtnActionPerformed()");
        
        model1.setRowCount(0);
        //System.out.println("quanlycuahangthoitrang.GUI.frame.searchSPBtnActionPerformed()");
        outModel1(model1, spBUS.searchSP(str ,"" , ""));
    }//GEN-LAST:event_searchSPBtnActionPerformed

    private void maphieunhapTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maphieunhapTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maphieunhapTfActionPerformed

    private void addSPBtn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSPBtn4MouseClicked
        try {
            // TODO add your handling code here:
            addNCCToTable();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        outModelNCC(modelnhacungcap, nccBUS.getList());
    }//GEN-LAST:event_addSPBtn4MouseClicked

    private void addSPBtn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSPBtn6MouseClicked
        try {
            // TODO add your handling code here:
            addLoaiToTable();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        outModelLoai(modelloai, loaiBUS.getList());
        
    }//GEN-LAST:event_addSPBtn6MouseClicked

    private void addSPBtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSPBtn2MouseClicked
        try {
            // TODO add your handling code here:
            addPhieuNhapHangToTable();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewNhaphang();
    }//GEN-LAST:event_addSPBtn2MouseClicked

    private void addSPBtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSPBtn3MouseClicked

        try {
            addChiTietPhieuNhapToTable();
            //cleanViewChiTietNhapHang();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addSPBtn3MouseClicked

    private void deleteSPBtn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteSPBtn6MouseClicked
        try {
            // TODO add your handling code here:
            deleteLoaiFromTable();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewLoai();
        outModelLoai(modelloai, loaiBUS.getList());
        
    }//GEN-LAST:event_deleteSPBtn6MouseClicked

    private void editSPBtn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSPBtn6MouseClicked
        try {
            // TODO add your handling code here:
            editLoai();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewLoai();
        outModelLoai(modelloai, loaiBUS.getList());
        
        
    }//GEN-LAST:event_editSPBtn6MouseClicked

    private void deleteSPBtn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteSPBtn4MouseClicked
        try {
            // TODO add your handling code here:
            deleteNCCFromTable();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewNCC();
        outModelNCC(modelnhacungcap, nccBUS.getList());
    }//GEN-LAST:event_deleteSPBtn4MouseClicked

    private void editSPBtn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSPBtn4MouseClicked
        try {
            // TODO add your handling code here:
            editNCC();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewNCC();
        outModelNCC(modelnhacungcap, nccBUS.getList());
        
    }//GEN-LAST:event_editSPBtn4MouseClicked

    private void searchSPBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSPBtn2ActionPerformed
        // TODO add your handling code here:
        String str = nhacungcapSearch.getText();
        
        //sanphamTable.setValueAt(evt, ERROR, NORMAL);
        ///.out.println("quanlycuahangthoitrang.GUI.frame.searchSPBtnActionPerformed()");
        
        modelnhacungcap.setRowCount(0);
        //System.out.println("quanlycuahangthoitrang.GUI.frame.searchSPBtnActionPerformed()");
        outModelNCC(modelnhacungcap, nccBUS.searchNcc(str));
    }//GEN-LAST:event_searchSPBtn2ActionPerformed

    private void searchSPBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSPBtn3ActionPerformed
        // TODO add your handling code here:
        String str = loaiSearchTf.getText();
        
        //sanphamTable.setValueAt(evt, ERROR, NORMAL);
        ///.out.println("quanlycuahangthoitrang.GUI.frame.searchSPBtnActionPerformed()");
        
        modelloai.setRowCount(0);
        //System.out.println("quanlycuahangthoitrang.GUI.frame.searchSPBtnActionPerformed()");
        outModelLoai(modelloai, loaiBUS.searchSP(str));
    }//GEN-LAST:event_searchSPBtn3ActionPerformed

    private void deleteSPBtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteSPBtn2MouseClicked
        try {
            // TODO add your handling code here:
            deletePhieuNhapHangFromTable();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewNhaphang();
    }//GEN-LAST:event_deleteSPBtn2MouseClicked

    private void editSPBtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSPBtn2MouseClicked
        try {
            // TODO add your handling code here:
            editPhieuNhapHang();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanViewNhaphang();
    }//GEN-LAST:event_editSPBtn2MouseClicked

    private void editSPBtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSPBtn3MouseClicked
        // TODO add your handling code here:
        editChiTietNhapHang();
        //cleanViewChiTietNhapHang();
    }//GEN-LAST:event_editSPBtn3MouseClicked

    private void deleteSPBtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteSPBtn3MouseClicked
        // TODO add your handling code here:
        deleteChiTietPhieuNhapHangFromTable();
        //cleanViewChiTietNhapHang();
    }//GEN-LAST:event_deleteSPBtn3MouseClicked

    private void masanphamTfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_masanphamTfKeyReleased
        // TODO add your handling code here:
        //System.out.println("quanlycuahangthoitrang.GUI.frame.masanphamTfKeyReleased()");
        String id = masanphamTf.getText();
        thongbaosanpham.setText("Sản phẩm chưa nhập hàng");
        thongbaosanpham.setForeground(Color.red);
        soluongTf.setText("");
        dongiaTf.setText("");
        for(chitietphieunhapDTO ct : chitietnhapBUS.getList()){
            //System.out.println(ct.getMasanpham());
            if(!ct.getMasanpham().equals(id)){
                
            }
            else{
                soluongTf.setText(String.valueOf(ct.getSoluong()));
                dongiaTf.setText(String.valueOf(Math.round(ct.getDongia()+(ct.getDongia()*0.7))));
                thongbaosanpham.setText("Sản phẩm đã nhập hàng");
                thongbaosanpham.setForeground(Color.green);
                
            }
            
        }
    }//GEN-LAST:event_masanphamTfKeyReleased

    private void hoTf_khachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoTf_khachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoTf_khachActionPerformed

    private void searchTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTfActionPerformed

    private void hoTf_nhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoTf_nhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoTf_nhanvienActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void searchid_ggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchid_ggActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchid_ggActionPerformed

    private void emailTf_nhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTf_nhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTf_nhanvienActionPerformed

    private void sdtTf_nhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdtTf_nhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sdtTf_nhanvienActionPerformed
    void them(){
        String ho="Nhập họ của nhân viên";
        String ten="Nhập tên của nhân viên";
        String sdt="Nhập số điện thoại của nhân viên";
        String diachi="Nhập địa chỉ của nhân viên";
        String email="Nhập email của nhân viên";
        String luong="Nhập lương nhân viên";
        if(jDCns_nhanvien.getDateFormatString().equals("")||hoTf_nhanvien.getText().equals(ho)|| tenTf_nhanvien.equals(ho) || sdtTf_nhanvien.getText().equals(ten) || diachiTf_nhanvien.getText().equals(diachi) || emailTf_nhanvien.getText().equals(email) 
                || luongTf_nhanvien.getText().equals(luong) )
        {
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin!!!");
        }
        else
        {
        QLNVDTO nv=new QLNVDTO();
        nv.setHo(hoTf_nhanvien.getText());
        nv.setTen(tenTf_nhanvien.getText());
        nv.setDiachi(diachiTf_nhanvien.getText());
        nv.setEmail(emailTf_nhanvien.getText());
        nv.setSdt(sdtTf_nhanvien.getText());
        nv.setLuong(luongTf_nhanvien.getText());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = formatter.format(jDCns_nhanvien.getDate());
        nv.setNgaysinh(dateFormat);
        if(jCBNam.isSelected()){
            nv.setGioitinh("Nam");
        }
        else {nv.setGioitinh("Nữ");}
        QLNVBUS bus = new QLNVBUS();
        bus.them(nv);
        docdsnv();
        }
    }
    private void hoTf_nhanvienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTf_nhanvienFocusGained
        // TODO add your handling code here:
        if (hoTf_nhanvien.getText().equals("Nhập họ của nhân viên"))
        {
            hoTf_nhanvien.setText("");
            hoTf_nhanvien.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_hoTf_nhanvienFocusGained

    private void hoTf_nhanvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTf_nhanvienFocusLost
        // TODO add your handling code here:
        if (hoTf_nhanvien.getText().equals(""))
        {
            hoTf_nhanvien.setText("Nhập họ của nhân viên");
            hoTf_nhanvien.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_hoTf_nhanvienFocusLost

    private void tenTf_nhanvienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tenTf_nhanvienFocusGained
        // TODO add your handling code here:
        if (tenTf_nhanvien.getText().equals("Nhập tên của nhân viên"))
        {
            tenTf_nhanvien.setText("");
            tenTf_nhanvien.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tenTf_nhanvienFocusGained

    private void tenTf_nhanvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tenTf_nhanvienFocusLost
        // TODO add your handling code here:
        if (tenTf_nhanvien.getText().equals(""))
        {
            tenTf_nhanvien.setText("Nhập tên của nhân viên");
            tenTf_nhanvien.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tenTf_nhanvienFocusLost

    private void diachiTf_nhanvienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_diachiTf_nhanvienFocusGained
        // TODO add your handling code here:
         if (diachiTf_nhanvien.getText().equals("Nhập địa chỉ của nhân viên"))
        {
            diachiTf_nhanvien.setText("");
            diachiTf_nhanvien.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_diachiTf_nhanvienFocusGained

    private void diachiTf_nhanvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_diachiTf_nhanvienFocusLost
        // TODO add your handling code here:
        if (diachiTf_nhanvien.getText().equals(""))
        {
            diachiTf_nhanvien.setText("Nhập địa chỉ của nhân viên");
            diachiTf_nhanvien.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_diachiTf_nhanvienFocusLost

    private void sdtTf_nhanvienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sdtTf_nhanvienFocusGained
        // TODO add your handling code here:
        if (sdtTf_nhanvien.getText().equals("Nhập số điện thoại của nhân viên"))
        {
            sdtTf_nhanvien.setText("");
            sdtTf_nhanvien.setForeground(Color.BLACK);
            
        }
        
    }//GEN-LAST:event_sdtTf_nhanvienFocusGained

    private void sdtTf_nhanvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sdtTf_nhanvienFocusLost
        // TODO add your handling code here:
        if (sdtTf_nhanvien.getText().equals(""))
        {
            sdtTf_nhanvien.setText("Nhập số điện thoại của nhân viên");
            sdtTf_nhanvien.setForeground(Color.GRAY);
        }
        
    }//GEN-LAST:event_sdtTf_nhanvienFocusLost

    private void emailTf_nhanvienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTf_nhanvienFocusGained
        // TODO add your handling code here:
        if (emailTf_nhanvien.getText().equals("Nhập email của nhân viên"))
        {
            emailTf_nhanvien.setText("");
            emailTf_nhanvien.setForeground(Color.BLACK);
        }
        
    }//GEN-LAST:event_emailTf_nhanvienFocusGained

    private void emailTf_nhanvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTf_nhanvienFocusLost
        // TODO add your handling code here:
        if (emailTf_nhanvien.getText().equals(""))
        {
            emailTf_nhanvien.setText("Nhập email của nhân viên");
            emailTf_nhanvien.setForeground(Color.GRAY);
        }
        
    }//GEN-LAST:event_emailTf_nhanvienFocusLost

    private void dsnvTb_nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsnvTb_nhanvienMouseClicked
        // TODO add your handling code here:
        int  i=dsnvTb_nhanvien.getSelectedRow();
        setmautextnhanvien1();
        //JOptionPane.showMessageDialog(null, "row:"+i);
        if(i>=0)
        {
            
            hoTf_nhanvien.setText((String) dsnvTb_nhanvien.getValueAt(i, 1));
            tenTf_nhanvien.setText((String) dsnvTb_nhanvien.getValueAt(i, 2));
            diachiTf_nhanvien.setText((String) dsnvTb_nhanvien.getValueAt(i, 5));
            emailTf_nhanvien.setText((String) dsnvTb_nhanvien.getValueAt(i, 6));
            sdtTf_nhanvien.setText((String) dsnvTb_nhanvien.getValueAt(i, 7));
            luongTf_nhanvien.setText((String) dsnvTb_nhanvien.getValueAt(i, 8));
            if(((String) dsnvTb_nhanvien.getValueAt(i, 4)).equals("Nam"))
            {
                jCBNam.setSelected(true);
            }
            else 
            {
                jCBNu.setSelected(true);
            }
            try {
            // TODO add your handling code here:
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) dsnvTb_nhanvien.getValueAt(i, 3));
                    jDCns_nhanvien.setDate(date);
            } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_dsnvTb_nhanvienMouseClicked

    private void luongTf_nhanvienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_luongTf_nhanvienFocusGained
        // TODO add your handling code here:
        if (luongTf_nhanvien.getText().equals("Nhập lương nhân viên"))
        {
            luongTf_nhanvien.setText("");
            luongTf_nhanvien.setForeground(Color.BLACK);
        }
        
    }//GEN-LAST:event_luongTf_nhanvienFocusGained

    private void luongTf_nhanvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_luongTf_nhanvienFocusLost
        // TODO add your handling code here:
        if (luongTf_nhanvien.getText().equals(""))
        {
            luongTf_nhanvien.setText("Nhập lương nhân viên");
            luongTf_nhanvien.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_luongTf_nhanvienFocusLost

    private void jtbdshdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbdshdMouseClicked
         int i=jtbdshd.getSelectedRow();
        try{
            if(i>=0){
            hoadonDTO hd=new hoadonDTO();
            hoadonBUS hdb=new hoadonBUS();
            KhachHangBUS khb=new KhachHangBUS();
            QLNVBUS nvb=new QLNVBUS();
            giamgiaBUS ggb=new giamgiaBUS();
            hdb.docbus();
            khb.docKhachHang();
            nvb.docDSNV();
            ggb.docBUS();
            hd=hdb.getDshd().get(i);
            String s="<html>-------------------------HD"+hd.getMahd()+"-------------------------<br /><br/>";
            s+="Khách hàng: [CT"+hd.getMakh()+"] "+khb.getkh(hd.getMakh()).getHoKH()+" "+khb.getkh(hd.getMakh()).getTenKH()+"<br/>";
            s+="Nhân viên:  [ST"+hd.getMakh()+"] "+nvb.getNv(hd.getManv()).getHo()+" "+nvb.getNv(hd.getManv()).getTen()+"<br/>";
            s+="Chương trình giảm giá:   <br/> [SE"+hd.getMagg()+"] "+ggb.getGg(hd.getMagg()).getTengg()+"<br/>";
            s+="Ngày: "+hd.getNgay()+"<br/>";
            s+="Tổng tiền: "+hd.getTong()+"<br/>";
            s+="Tống tiền giảm: "+hd.getTg()+"<br/>";
            s+="Tiền thanh toán:  "+hd.getTpt()+"<br/>";
            s+="-------------------------END-------------------------";
            s+="</html>";
            tthdlabel.setText(s);
            chitiethoadonBUS ctb=new chitiethoadonBUS();
            ctb.docbus(hd.getMahd());
            Vector header =new Vector();
            header.add("Mã hd");
            header.add("Mã sp");
            header.add("Mã ctsp");
            header.add("Số lượng");
            header.add("Đơn giá");
            if(modelcthd != null) modelcthd=new DefaultTableModel(header,0);
            for(chitiethoadonDTO ct:ctb.getDsct()){
                    Vector row=new Vector();
                    row.add(ct.getMahd());
                    row.add(ct.getMasp());
                    row.add(ct.getMactsp());
                    row.add(ct.getSl());
                    row.add(ct.getDg());
                    
                    modelcthd.addRow(row);
                }
            deletehd.setEnabled(true);
            jtbcthd.setModel(modelcthd);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_jtbdshdMouseClicked

    private void jtbcthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbcthdMouseClicked
        int t=jtbcthd.getSelectedRow();
        String i=String.valueOf(jtbdshd.getSelectedRow()+1);
        if(t>=0){
        try{
            chitiethoadonDTO ct= new chitiethoadonDTO();
        chitiethoadonBUS ctb=new chitiethoadonBUS();
        ctb.docbus(i);
        ct=ctb.getDsct().get(t);
        String s="<html>-------------------------PD"+ct.getMasp()+"-------------------------<br/><br/>Tên sản phẩm:"+spBUS.getSP(ct.getMasp()).getTensp()+"<br/>";
        s+="Chi tiết: [CT"+ct.getMactsp()+"]<br/>";
        s+="-Màu: "+ctspBUS.getCTSP(ct.getMactsp()).getMausac()+"<br/>";
        s+="Size: "+ctspBUS.getCTSP(ct.getMactsp()).getSize()+"<br/>";
        s+="Số lượng: "+ct.getSl()+"<br/>";
        s+="Thành tiền: "+ct.getDg()+"<br/>";
        s+="-------------------------END-------------------------";
        s+="</html>";
        ttcthdlabel.setText(s);
        }catch(Exception e){
            System.out.println("Loi");}
        }
    }//GEN-LAST:event_jtbcthdMouseClicked

    private void displaySPConhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPConhangMouseClicked
        // TODO add your handling code here:
        outModel1(model1, spBUS.searchSP("conhang", "", ""));
    }//GEN-LAST:event_displaySPConhangMouseClicked

    private void displaySPhethangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPhethangMouseClicked
        // TODO add your handling code here:
        outModel1(model1, spBUS.searchSP("hethang", "", ""));
    }//GEN-LAST:event_displaySPhethangMouseClicked

    private void displayallspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayallspMouseClicked
        // TODO add your handling code here:
        outModel1(model1, spBUS.getList());
    }//GEN-LAST:event_displayallspMouseClicked

    private void maggtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maggtfKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tenggtf.requestFocus();
        }
        docgiamgia();
        Vector header=new Vector();
        header.add("Mã gg");
        header.add("Mã sp");
        header.add("Phần trăm giảm");
        if(modelctgg!=null) modelctgg=new DefaultTableModel(header,0);
        ctgiamgiatable.setModel(modelctgg);
    }//GEN-LAST:event_maggtfKeyPressed

    private void tenggtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenggtfKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datebd.requestFocus();
        }              // TODO add your handling code here:
    }//GEN-LAST:event_tenggtfKeyPressed

    private void idsptfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idsptfKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            discounttf.requestFocus();
        }
        spBUS.listSP();
       String t=idsptf.getText()+evt.getKeyChar();
       if(spBUS.getSP(t)!=null)
        tensplabel.setText(spBUS.getSP(t).getTensp());
       else tensplabel.setText("");
    }//GEN-LAST:event_idsptfKeyPressed

    private void addggbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addggbtMouseClicked
         if(maggtf.getText().equals("")== false &&tenggtf.getText().equals("")== false &&datebd.getDate().equals("")== false &&datekt.getDate().equals("")== false )
            {
                try{
                    giamgiaBUS ggb=new giamgiaBUS();
                    ggb.docBUS();
                    if(ggb.getGg(maggtf.getText())!=null)
                    {
                        JOptionPane.showMessageDialog(null,"Mã giảm giá đã tồn tại");
                        return;
                    }
                chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
                ctggb.docbus(maggtf.getText());
                if(!ctggb.getDsct().isEmpty()){
                    giamgiaDTO gg=new giamgiaDTO(maggtf.getText(),tenggtf.getText(),datebd.getDate(),datekt.getDate());

                    ggb.sua(gg,ggb.getds().size()-1);
                    Vector row=new Vector();
                 row.add("EV"+gg.getMagg());
                 row.add(gg.getTengg());
                 row.add(gg.getNgbd());
                 row.add(gg.getNgkt());
                 modelgg.addRow(row);
                 giamgiatable.setModel(modelgg);
                }
                else JOptionPane.showMessageDialog(null,"Cần có chi tiết giảm giá trước khi tạo chương trình giảm giá");
            }catch(Exception e){System.out.println(e);}
            }
         else JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
    }//GEN-LAST:event_addggbtMouseClicked

    private void addctggbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addctggbtMouseClicked
        if(idsptf.getText().equals("")==false && discounttf.getText().equals("")==false && maggtf.getText().equals("")== false &&tenggtf.getText().equals("")== false &&datebd.getDate().equals("")== false &&datekt.getDate().equals("")== false){
            try{
                chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
                ctggb.docbus(maggtf.getText());
                if(ctggb.getDsct().isEmpty()){
                giamgiaDTO gg=new giamgiaDTO(maggtf.getText(),"a",new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"),new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
                giamgiaBUS ggb=new giamgiaBUS();
                ggb.docBUS();
                ggb.them(gg);
                }
                chitietgiamgiaDTO ctgg=new chitietgiamgiaDTO(maggtf.getText(),idsptf.getText(),Integer.parseInt(discounttf.getText()));      
                ctggb.them(ctgg);
                Vector row=new Vector();
                row.add(ctgg.getIdgg());
                row.add(ctgg.getIdsp());
                row.add(ctgg.getDiscount());
                modelctgg.addRow(row);
                ctgiamgiatable.setModel(modelctgg);
            }catch(Exception e){System.out.println(e);}
        }
        else {
            JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
        }
    }//GEN-LAST:event_addctggbtMouseClicked

    private void ctgiamgiatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctgiamgiatableMouseClicked
        int i=ctgiamgiatable.getSelectedRow();
        if(i>=0){
            try{
                   chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
                   ctggb.docbus(maggtf.getText());
                   chitietgiamgiaDTO ctgg=new chitietgiamgiaDTO();
                   ctgg=ctggb.getDsct().get(i);
                   idsptf.setText(ctgg.getIdsp());
                   discounttf.setText(String.valueOf(ctgg.getDiscount()));
                   tensplabel.setText(spBUS.getSP(ctgg.getIdsp()).getTensp());
            }catch(Exception e){System.out.println(e);}
        }
    }//GEN-LAST:event_ctgiamgiatableMouseClicked

    private void giamgiatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamgiatableMouseClicked
       int i=giamgiatable.getSelectedRow();
        if(i>=0){
            try{
                   giamgiaBUS ggb=new giamgiaBUS();
                   ggb.docBUS();
                   giamgiaDTO gg=new giamgiaDTO();
                   gg=ggb.getds().get(i);
                   maggtf.setText(gg.getMagg());
                   tenggtf.setText(gg.getTengg());
                   datebd.setDate(gg.getNgbd());
                   datekt.setDate(gg.getNgkt());
                   chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
                   ctggb.docbus(gg.getMagg());
                   Vector header = new Vector();
                   header.add("Mã gg");
                   header.add("Mã sp");
                   header.add("Phần trăm giảm");
                   if(modelctgg != null) modelctgg=new DefaultTableModel(header,0);
                   for(chitietgiamgiaDTO ctgg:ctggb.getDsct()){
                       Vector row=new Vector();
                       row.add(ctgg.getIdgg());
                       row.add(ctgg.getIdsp());
                       row.add(ctgg.getDiscount());
                       modelctgg.addRow(row);
                   }
                   ctgiamgiatable.setModel(modelctgg);
            }catch(Exception e){System.out.println(e);}
        }
    }//GEN-LAST:event_giamgiatableMouseClicked

    private void deleteggbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteggbtMouseClicked
        int i[]=giamgiatable.getSelectedRows();
        int c=0;
        for(int a:i){
            if(a>=0){
               try{ giamgiaDTO gg=new giamgiaDTO();
                giamgiaBUS ggb=new giamgiaBUS();
                chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
                ggb.docBUS();
                gg=ggb.getds().get(a-c);
                ctggb.docbus(gg.getMagg());
                for(int b=0;b<ctggb.getDsct().size();b++)
                ctggb.xoa(b);
                ggb.xoa(a-c);
                } catch(Exception e){System.out.println(e);}
            }
            modelgg.removeRow(a-c);
            c++;
        }
        giamgiatable.setModel(modelgg);
    }//GEN-LAST:event_deleteggbtMouseClicked

    private void deletectggbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletectggbtMouseClicked
       int i=ctgiamgiatable.getSelectedRow();
       if(i>=0){
          try{ chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
                ctggb.docbus(maggtf.getText());
                ctggb.xoa(i);
                modelctgg.removeRow(i);
                ctgiamgiatable.setModel(modelctgg);
          }catch(Exception e){System.out.println(e);}
       }
    }//GEN-LAST:event_deletectggbtMouseClicked

    private void editggbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editggbtMouseClicked
           int i=giamgiatable.getSelectedRow();
        if(i>=0){
            try{
                giamgiaBUS ggb=new giamgiaBUS();
                ggb.docBUS();
                giamgiaDTO gg=new giamgiaDTO(maggtf.getText(),tenggtf.getText(),datebd.getDate(),datekt.getDate());
                ggb.sua(gg, i);
                modelgg.setValueAt(gg.getTengg(), i, 1);
                modelgg.setValueAt(gg.getNgbd(), i, 2);
                modelgg.setValueAt(gg.getNgkt(), i, 3);
                giamgiatable.setModel(modelgg);
            }catch(Exception e){System.out.println(e);}
        }
    }//GEN-LAST:event_editggbtMouseClicked

    private void timkiemTf_nhanvienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timkiemTf_nhanvienFocusGained
        // TODO add your handling code here:
         if (timkiemTf_nhanvien.getText().equals("Nhập nội dung cần tìm vào đây "))
        {
            timkiemTf_nhanvien.setText("");
            timkiemTf_nhanvien.setForeground(Color.BLACK);
        }
        
    }//GEN-LAST:event_timkiemTf_nhanvienFocusGained

    private void timkiemTf_nhanvienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timkiemTf_nhanvienFocusLost
        // TODO add your handling code here:
        if (timkiemTf_nhanvien.getText().equals(""))
        {
            timkiemTf_nhanvien.setText("Nhập nội dung cần tìm vào đây ");
            timkiemTf_nhanvien.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_timkiemTf_nhanvienFocusLost
    
    void clickxnhanvien()
    {
        hoTf_nhanvien.setText("Nhập họ của nhân viên");
        tenTf_nhanvien.setText("Nhập tên của nhân viên");
        emailTf_nhanvien.setText("Nhập email của nhân viên");
        diachiTf_nhanvien.setText("Nhập địa chỉ của nhân viên");
        sdtTf_nhanvien.setText("Nhập số điện thoại của nhân viên");
        luongTf_nhanvien.setText("Nhập lương nhân viên");
        jCBNam.setSelected(true);
        
    }
    void setmautextnhanvien(){
        hoTf_nhanvien.setForeground(Color.GRAY);
        tenTf_nhanvien.setForeground(Color.GRAY);
        emailTf_nhanvien.setForeground(Color.GRAY);
        diachiTf_nhanvien.setForeground(Color.GRAY);
        sdtTf_nhanvien.setForeground(Color.GRAY);
        luongTf_nhanvien.setForeground(Color.GRAY);
        
        
    }
    void setmautextnhanvien1(){
        hoTf_nhanvien.setForeground(Color.BLACK);
        tenTf_nhanvien.setForeground(Color.BLACK);
        emailTf_nhanvien.setForeground(Color.BLACK);
        diachiTf_nhanvien.setForeground(Color.BLACK);
        sdtTf_nhanvien.setForeground(Color.BLACK);
        luongTf_nhanvien.setForeground(Color.BLACK);
        
        
    }

    
    private void editctggbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editctggbtMouseClicked
         int i=ctgiamgiatable.getSelectedRow();
       if(i>=0){
          try{ chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
                ctggb.docbus(maggtf.getText());
                chitietgiamgiaDTO ctgg=new chitietgiamgiaDTO(maggtf.getText(),idsptf.getText(),Integer.parseInt(discounttf.getText()));
                ctggb.sua(ctgg, i);
                modelctgg.setValueAt(ctgg.getDiscount(), i,2);
                ctgiamgiatable.setModel(modelctgg);
          }catch(Exception e){System.out.println(e);}
       }
    }//GEN-LAST:event_editctggbtMouseClicked

    private void hoTf_khachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTf_khachFocusGained
        // TODO add your handling code here:
      
    }//GEN-LAST:event_hoTf_khachFocusGained

    private void tenTf_khachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tenTf_khachFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tenTf_khachFocusGained

    private void sdtTf_khachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sdtTf_khachFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_sdtTf_khachFocusGained

    private void gmailTf_khachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gmailTf_khachFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_gmailTf_khachFocusGained

    private void hoTf_khachFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoTf_khachFocusLost
        // TODO add your handling code here:\
        
    }//GEN-LAST:event_hoTf_khachFocusLost

    private void tenTf_khachFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tenTf_khachFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tenTf_khachFocusLost

    private void sdtTf_khachFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sdtTf_khachFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_sdtTf_khachFocusLost

    private void gmailTf_khachFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gmailTf_khachFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_gmailTf_khachFocusLost
    
    public void docDSKH() throws Exception{
        KhachHangBUS bus = new KhachHangBUS();
        if(bus.getdskh()==null) bus.docKhachHang();
        Vector header = new Vector();
            header.add("ID Khách");
            header.add("Họ");
            header.add("Tên");
            header.add("Giới Tính");
            header.add("Email");
            header.add("Số Điện Thoại");
            
            modelkhach = new DefaultTableModel(header,0);
            for(KhachHangDTO kh : bus.getdskh())
            {
                Vector row=new Vector();
                row.add(kh.getID_KH());
                row.add(kh.getHoKH());
                row.add(kh.getTenKH());
                row.add(kh.getGioitinhKH());            
                row.add(kh.getGmailKH());
                row.add(kh.getSdtKH());
                
                modelkhach.addRow(row);
            }
            dsKHTable.setModel(modelkhach);
    }
    
    public void themKH() throws Exception{
        KhachHangDTO kh=new KhachHangDTO();
        if(hoTf_khach.getText().isEmpty()|| tenTf_khach.getText().isEmpty()|| sdtTf_khach.getText().isEmpty() || gmailTf_khach.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin!!!");
        }
        else
        {
        kh.setHoKH(hoTf_khach.getText());
        kh.setTenKH(tenTf_khach.getText());       
        if(jgioitinhNam.isSelected()){
            kh.setGioitinhKH("Nam");
        }
        else {kh.setGioitinhKH("Nữ");}
        kh.setGmailKH(gmailTf_khach.getText());
        kh.setSdtKH(sdtTf_khach.getText());
    
        KhachHangBUS bus = new KhachHangBUS();
        String sdt = sdtTf_khach.getText();
        String ho = hoTf_khach.getText();
        String ten = tenTf_khach.getText();
        String email = gmailTf_khach.getText();
        String gmail = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        int sizesdt = sdt.length();
        if(checksdt(sdt)==true && checkHo(ho)==true && checkTen(ten)==true && Pattern.matches(gmail,email)==true && checksizesdt(sdt)==true && checkgmail(email)==true && checkmasdt(sdt)==true){
            if(JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thêm Khách Hàng ?",
                        "Thông Báo", JOptionPane.YES_OPTION) == JOptionPane.OK_OPTION) {
        bus.ThemKhachHang(kh);
            }
        }
        else{          
            if(checkHo(ho)==false){
                JOptionPane.showMessageDialog(null, "Họ sai định dạng");
            }
            if(checkTen(ten)==false){
                JOptionPane.showMessageDialog(null, "Tên sai định dạng");
            }
            if(checksdt(sdt)==false){
                JOptionPane.showMessageDialog(null, "Số điện thoại sai định dạng");
            }
            if(checksizesdt(sdt)==false)
            {
                JOptionPane.showMessageDialog(null, "Số điện thoại chưa đủ");
            }
            if(Pattern.matches(gmail,email)==false){
                JOptionPane.showMessageDialog(null, "Email sai định dạng");
            }
            if(checkgmail(email)==false){
                JOptionPane.showMessageDialog(null, "Gmail này đã tồn tại");
            }
            if(checkmasdt(sdt)==false){
                JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại");
            }

        }
        
        docDSKH();
        }
    
    }
    //kiem tra ho
    public boolean checkgmail(String s) throws Exception
    {
        try
        {
            //ki
            KhachHangBUS bus = new KhachHangBUS();
//            KhachHangDTO kh = new KhachHangDTO();
            for(KhachHangDTO kh : bus.getdskh()){             
            if(kh.getGmailKH().equals(s))
                {
                    return false;
                }                  
            }
               return true;
    
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }
    
    //kiem tra ho
    public boolean checkHo(String s) throws Exception
    {
        try
        {
            //kiểm tra họ tên, họ tên chỉ chứa các ký tự từ a -> z và A -> Z
            if(s.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{1,20}"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }
    
    //kiem tra ten
    public boolean checkTen(String s) throws Exception
    {
        try
        {
            //kiểm tra họ tên, họ tên chỉ chứa các ký tự từ a -> z và A -> Z
            if(s.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{1,20}"))
            {
                return true;
            }
            else
            {                                   
                    return false;
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    //kiem so dien thoai
    public boolean checksdt(String s) {
            if (s == null || s.isEmpty()) {
                return false;
            }
            int sz = s.length();
            for (int i = 0; i < sz ; i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
                 
            }
            return true;
 }
    
    public boolean checkmasdt(String s){
        KhachHangBUS bus = new KhachHangBUS();
        for(KhachHangDTO kh : bus.getdskh()){
            if(kh.getSdtKH().equals(s)){
                return false;
            }
        }
        return true;
    }
    
    //kiem tra do dai sdt
    public boolean checksizesdt(String s){
        if(s.length()>=10 && s.length()<=11){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void suaKH() throws Exception {
        if(hoTf_khach.getText().isEmpty()|| tenTf_khach.getText().isEmpty()|| sdtTf_khach.getText().isEmpty() || gmailTf_khach.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Chưa có thông tin !!!");
        }
        else
        {
         KhachHangDTO kh=new KhachHangDTO();
         KhachHangBUS bus=new KhachHangBUS();
         bus.docKhachHang();
            int  i=dsKHTable.getSelectedRow();
            if (i>=0)
            { 
            kh.setID_KH((String) dsKHTable.getValueAt(i, NORMAL));
            kh.setHoKH(hoTf_khach.getText());
            kh.setTenKH(tenTf_khach.getText());   
            kh.setGmailKH(gmailTf_khach.getText());
            kh.setSdtKH(sdtTf_khach.getText());          
            if(jgioitinhNam.isSelected()){
            kh.setGioitinhKH("Nam");
            }
            else {kh.setGioitinhKH("Nữ");}
            String sdt = sdtTf_khach.getText();
            String ho = hoTf_khach.getText();
            String ten = tenTf_khach.getText();
            String email = gmailTf_khach.getText();
            String gmail = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            if(checkHo(ho)==true && checkTen(ten)==true && checksdt(sdt)==true && Pattern.matches(gmail,email)==true && checksizesdt(sdt)==true && checkgmail(email)==true && checkmasdt(sdt)==true){
                if(JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa khách hàng " + kh.getID_KH() + " ?",
                            "Thông Báo", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                bus.SuaKhachHang(kh);
                hoTf_khach.setText(null);
                tenTf_khach.setText(null);
                gmailTf_khach.setText(null);
                sdtTf_khach.setText(null);
            //cap nhận dssv
            KhachHangDTO old= bus.getdskh().set(i, kh);
            modelkhach.setValueAt(kh.getID_KH(), i, 0);
            modelkhach.setValueAt(kh.getHoKH(), i, 1);
            modelkhach.setValueAt(kh.getTenKH(), i, 2);
            modelkhach.setValueAt(kh.getGioitinhKH(), i, 3);
            modelkhach.setValueAt(kh.getGmailKH(), i, 4);
            modelkhach.setValueAt(kh.getSdtKH(), i, 5);
            dsKHTable.setModel(modelkhach);
            }
    }
                else{
                        if(checkHo(ho)==false){
                        JOptionPane.showMessageDialog(null, "Họ sai định dạng");
                        }
                        if(checkTen(ten)==false){
                            JOptionPane.showMessageDialog(null, "Tên sai định dạng");
                        }
                        if(checksdt(sdt)==false){
                            JOptionPane.showMessageDialog(null, "Số điện thoại sai định dạng");
                        }
                        if(sdt.length()<10 && sdt.length()>11)
                        {
                            JOptionPane.showMessageDialog(null, "Số điện thoại chưa đủ");
                        }
                        if(Pattern.matches(gmail,email)==false)
                        {
                            JOptionPane.showMessageDialog(null, "Email sai định dạng");
                        }
                         if(checksizesdt(sdt)==false)
                        {
                            JOptionPane.showMessageDialog(null, "Số điện thoại chưa đủ");
                        }
                        if(Pattern.matches(gmail,email)==false){
                            JOptionPane.showMessageDialog(null, "Email sai định dạng");
                        }
                        if(checkgmail(email)==false){
                            JOptionPane.showMessageDialog(null, "Gmail này đã tồn tại");
                        }
                        if(checkmasdt(sdt)==false){
                            JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại");
                        }
                    }
    
            }   
    }
}           
            
          
   
    public void xoaKH() throws Exception {
        if(hoTf_khach.getText().isEmpty()|| tenTf_khach.getText().isEmpty()|| sdtTf_khach.getText().isEmpty() || gmailTf_khach.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin!!!");
        }
        else
        {
        KhachHangDTO kh = new KhachHangDTO();
        KhachHangBUS bus = new KhachHangBUS();
        int  i=dsKHTable.getSelectedRow();   
        if (i>=0)
            {
                bus.docKhachHang();
                kh=bus.getdskh().get(i);
            if(JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn XÓA HOÀN TOÀN khách hàng " + kh.getID_KH() + " ?",
                        "Chú ý", JOptionPane.YES_OPTION) == JOptionPane.OK_OPTION) {
             bus.XoaKhachHang(kh,i);
            }
            //cap nhat array list dssv
            modelkhach.removeRow(i);
            dsKHTable.setModel(modelkhach);
            hoTf_khach.setText(null);
            tenTf_khach.setText(null);
            gmailTf_khach.setText(null);
            sdtTf_khach.setText(null);           
            }
        else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để xóa");
        }
    }
        
    }
    
    public void timKH(){
        KhachHangBUS bus = new KhachHangBUS();
        KhachHangDTO kh = new KhachHangDTO();
        String temp = searchTf.getText();
        modelkhach.setRowCount(0);
        for (int i =0; i < KhachHangBUS.timkiemALL(temp).size();i++) {
            Add_row(KhachHangBUS.timkiemALL(temp).get(i));
            
        }
      
    }
    private void Add_row(KhachHangDTO kh) 
    {   
        KhachHangBUS bus = new KhachHangBUS();
       
        Vector row = new Vector();
       
            row.add(kh.getID_KH());
            row.add(kh.getHoKH());
            row.add(kh.getTenKH());
            row.add(kh.getGioitinhKH());
            row.add(kh.getGmailKH());
            row.add(kh.getSdtKH());
            modelkhach.addRow(row);
      
        dsKHTable.setModel(modelkhach);
//        int i=dsKHTable.getSelectedRow();
//            if(i>0)
//            {
//            KhachHangDTO old= bus.getdskh().set(i, kh);
//                modelkhach.setValueAt(kh.getID_KH(), i, 0);
//                modelkhach.setValueAt(kh.getHoKH(), i, 1);
//                modelkhach.setValueAt(kh.getTenKH(), i, 2);
//                modelkhach.setValueAt(kh.getGioitinhKH(), i, 3);
//                modelkhach.setValueAt(kh.getGmailKH(), i, 4);
//                modelkhach.setValueAt(kh.getSdtKH(), i, 5);
//                dsKHTable.setModel(modelkhach);       
//            }
        }
    
    private void themBtn_khachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themBtn_khachMouseClicked
        // TODO add your handling code here:
        try{
            themKH();
        }catch(Exception e){}
    }//GEN-LAST:event_themBtn_khachMouseClicked

    private void suaBtn_khachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suaBtn_khachMouseClicked
        try {
            // TODO add your handling code here:
            suaKH();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_suaBtn_khachMouseClicked

    private void xoaBtn_khachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoaBtn_khachMouseClicked
        try {
            // TODO add your handling code here:
            xoaKH();
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_xoaBtn_khachMouseClicked

    private void dsKHTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dsKHTableMouseClicked
        // TODO add your handling code here:
         int  i=dsKHTable.getSelectedRow();
        
        //JOptionPane.showMessageDialog(null, "row:"+i);
        if(i>=0)
        {
            KhachHangDTO kh =new KhachHangDTO();
            KhachHangBUS bus =new KhachHangBUS();
            kh=bus.getdskh().get(i);     
            hoTf_khach.setText(kh.getHoKH());
            tenTf_khach.setText(kh.getTenKH());
            gmailTf_khach.setText(kh.getGmailKH());
            sdtTf_khach.setText(kh.getSdtKH());
            if(kh.getGioitinhKH().equals("Nam"))
            {
                jgioitinhNam.setSelected(true);
            }
            else 
            {
                jgioitinhNU.setSelected(true);
            };
        }
    }//GEN-LAST:event_dsKHTableMouseClicked
    void intieudenv()
    {
        if(bus.getDSNV()==null) bus.docDSNV();
        Vector header = new Vector();
                header.add("ID Nhân Viên");//tua de cot cua jtable
                header.add("Họ");
                header.add("Tên");
                header.add("Ngày Sinh");
                header.add("Giới Tính");
                header.add("Địa Chỉ");
                header.add("Email");
                header.add("Số Điện Thoại");
                header.add("Lương");
            modelnhanvien = new DefaultTableModel(header,0);
    }     
    void timkiemnv()
    {
        intieudenv();
        for(QLNVDTO nv: bus.getDSNV())
            {   
                String s=nv.getHo()+nv.getTen()+nv.getNgaysinh()+nv.getGioitinh()+nv.getDiachi()+nv.getEmail()+nv.getSdt()+nv.getLuong();
                if(s.contains(timkiemTf_nhanvien.getText()))
                {
                Vector row=new Vector();
                row.add(nv.getId());
                row.add(nv.getHo());
                row.add(nv.getTen());
                row.add(nv.getNgaysinh());
                row.add(nv.getGioitinh());
                row.add(nv.getDiachi());
                row.add(nv.getEmail());
                row.add(nv.getSdt());
                row.add(nv.getLuong());
                modelnhanvien.addRow(row);
                }
            }
            dsnvTb_nhanvien.setModel(modelnhanvien);
    }
    void timkiemidnv()
    {
        intieudenv();
        for(QLNVDTO nv: bus.getDSNV())
            {   
                String id=nv.getId();
                if(id.equals(timid_nv.getText()))
                {
                Vector row=new Vector();
                row.add(nv.getId());
                row.add(nv.getHo());
                row.add(nv.getTen());
                row.add(nv.getNgaysinh());
                row.add(nv.getGioitinh());
                row.add(nv.getDiachi());
                row.add(nv.getEmail());
                row.add(nv.getSdt());
                row.add(nv.getLuong());
                modelnhanvien.addRow(row);
                }
                else if("".contains(timid_nv.getText()))
                {
                     Vector row=new Vector();
                row.add(nv.getId());
                row.add(nv.getHo());
                row.add(nv.getTen());
                row.add(nv.getNgaysinh());
                row.add(nv.getGioitinh());
                row.add(nv.getDiachi());
                row.add(nv.getEmail());
                row.add(nv.getSdt());
                row.add(nv.getLuong());
                modelnhanvien.addRow(row);
                }
            }
            dsnvTb_nhanvien.setModel(modelnhanvien);
    }
    
    private void timkiemTf_nhanvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timkiemTf_nhanvienKeyReleased
        // TODO add your handling code here:
        timkiemnv();
       
        
    }//GEN-LAST:event_timkiemTf_nhanvienKeyReleased
    
    private void them_nvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_them_nvMouseClicked
        
        if(rangbuoctennv()==false)
        {
            JOptionPane.showMessageDialog(null,"Tên có ký tự không đúng !!!");
        }
        else if(rangbuochonv()==false)
        {
            JOptionPane.showMessageDialog(null,"Họ có ký tự không đúng !!!");
        }
        else if(rangbuocluong()==false)
        {
            JOptionPane.showMessageDialog(null,"Lương có ký tự không đúng !!!");
        }
        else if(rangbuocsdt()==false)
        {
            JOptionPane.showMessageDialog(null,"Số điện thoại có ký tự không đúng !!!");
        }
        else if(rangbuocemail()==false)
        {
            JOptionPane.showMessageDialog(null,"Email không đúng định dạng !!!");
        }
        else if(kiemtratrungemail()==true||kiemtratrungsdt()==true)
        {
            JOptionPane.showMessageDialog(null,"Email hoặc Số điện thoại đã tồn tại !!!");
        }
        else{
        them();
        }
    }//GEN-LAST:event_them_nvMouseClicked
    void xoa_nv()
    {
        QLNVDTO nv=new QLNVDTO();
        
        int  i=dsnvTb_nhanvien.getSelectedRow();
        nv.setId((String) dsnvTb_nhanvien.getValueAt(i, NORMAL)); 
        
        if (i>=0)
            {
             bus.xoa(nv,i);
            //cap nhat array list dssv
            modelnhanvien.removeRow(i);
            dsnvTb_nhanvien.setModel(modelnhanvien);
    }
    }
    private void sua_nvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sua_nvMouseClicked
        // TODO add your handling code here:
        if(rangbuoctennv()==false)
        {
            JOptionPane.showMessageDialog(null,"Tên có ký tự không đúng !!!");
        }
        else if(rangbuochonv()==false)
        {
            JOptionPane.showMessageDialog(null,"Họ có ký tự không đúng !!!");
        }
        else if(rangbuocluong()==false)
        {
            JOptionPane.showMessageDialog(null,"Lương có ký tự không đúng !!!");
        }
        else if(rangbuocsdt()==false)
        {
            JOptionPane.showMessageDialog(null,"Số điện thoại có ký tự không đúng !!!");
        }
        else if(rangbuocemail()==false)
        {
            JOptionPane.showMessageDialog(null,"Email không đúng định dạng !!!");
        }
        else{
        sua_nv();
        }
    }//GEN-LAST:event_sua_nvMouseClicked
    
    boolean kiemtratrungsdt()
    {
        QLNVDTO nv=new QLNVDTO();
        
       
        if(bus.getsdtNv(sdtTf_nhanvien.getText())==null)
                {
                    return true;
                }
        else
       
            return false;
    }
    boolean kiemtratrungemail()
    {
        QLNVDTO nv=new QLNVDTO();
        
       
        if(bus.getemailNv(emailTf_nhanvien.getText())==null)
                {
                    return true;
                }
        else
       
            return false;
    }
            
    private void xoa_nvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoa_nvMouseClicked
        // TODO add your handling code here:
        
        xoa_nv();
    }//GEN-LAST:event_xoa_nvMouseClicked

    private void reset_nvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reset_nvMouseClicked
        // TODO add your handling code here:
        reset_nv();
    }//GEN-LAST:event_reset_nvMouseClicked

    private void importhdexlbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importhdexlbtMouseClicked
        hoadonBUS b=new hoadonBUS();
        b.nhapexl();
        dochoadon();
    }//GEN-LAST:event_importhdexlbtMouseClicked

    private void deletehdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletehdMouseClicked
       int i[]=jtbdshd.getSelectedRows();
        int c=0;
        for(int a:i){
            if(a>=0){
               try{ hoadonDTO hd=new hoadonDTO();
                hoadonBUS hdb=new hoadonBUS();
                chitiethoadonBUS ctb=new chitiethoadonBUS();
                hdb.docbus();
                hd=hdb.getDshd().get(a-c);
                ctb.xoahd(hd.getMahd());
                hdb.xoa(a-c);
                } catch(Exception e){System.out.println(e);}
            }
            modelhd.removeRow(a-c);
            c++;
        }
        jtbdshd.setModel(modelhd);
    }//GEN-LAST:event_deletehdMouseClicked
 void capnhathd() throws SQLException, SQLException, ParseException{
       hoadonBUS hdb=new hoadonBUS();
       hdb.docbus();
            for(int i=0;i<hdb.getDshd().size();i++){
         try{
                chitiethoadonBUS ctb=new chitiethoadonBUS();
                chitietgiamgiaBUS ctgb=new chitietgiamgiaBUS();
                ctgb.docbus(hdb.getDshd().get(i).getMagg());
                ctb.docbus(hdb.getDshd().get(i).getMahd());
                if(!ctb.getDsct().isEmpty()){
                    int t=0;
                    int g=0;
                    for(chitiethoadonDTO ct:ctb.getDsct()){
                        t+=ct.getDg();
                        ctgb.getgg(hdb.getDshd().get(i).getMagg());
                        if(ctgb.getgg(hdb.getDshd().get(i).getMagg()).checkng(hdb.getDshd().get(i).getNgay())){
                            ctgb.getdis(ct.getMasp(),hdb.getDshd().get(i).getMagg());
                        g+=ctgb.getdis(ct.getMasp(),hdb.getDshd().get(i).getMagg())*ct.getDg()/100;
                        }
                }
                    hdb.getDshd().get(i).setTong(t);
                    hdb.getDshd().get(i).setTg(g);
                    hdb.getDshd().get(i).setTpt(t-g);
                    hoadonDTO hd=new hoadonDTO(hdb.getDshd().get(i));
                    hdb.sua(hd,i);
        }
        }catch(Exception e){}
         dochoadon();
        }
    }
    private void importcthdexlbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importcthdexlbtMouseClicked
        chitiethoadonBUS b=new chitiethoadonBUS();
        b.nhapexl();
        
            try {
                capnhathd();
            } catch (SQLException ex) {
                Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }//GEN-LAST:event_importcthdexlbtMouseClicked

    private void exportcthdexcelbtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportcthdexcelbtMouseClicked
       chitiethoadonBUS b=new chitiethoadonBUS();
        try {
            b.xuatexl();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportcthdexcelbtMouseClicked

    private void importspexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importspexlMouseClicked
        try {
            spBUS.ImportExcelDatabase();
            creatTableSanPham();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_importspexlMouseClicked

    private void exportspexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportspexlMouseClicked
        try {
            spBUS.ExportExcelDatabase();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportspexlMouseClicked

    private void displaySPConhang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPConhang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_displaySPConhang1MouseClicked

    private void displaySPConhang2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPConhang2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_displaySPConhang2MouseClicked

    private void exportctspexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportctspexlMouseClicked
       chitietsanphamBUS ctspBUS=new chitietsanphamBUS();
        try {
            ctspBUS.ExportExcelDatabase();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportctspexlMouseClicked

    private void importnccexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importnccexlMouseClicked
        try {
            nccBUS.ImportExcelDatabase();
            creatTableNhaCungCap();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_importnccexlMouseClicked

    private void exportnccexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportnccexlMouseClicked
        try {
            nccBUS.ExportExcelDatabase();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportnccexlMouseClicked

    private void importloaiexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importloaiexlMouseClicked
        try {
            loaiBUS.ImportExcelDatabase();
            creatTableLoaiSanPham();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_importloaiexlMouseClicked

    private void exportloaiexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportloaiexlMouseClicked
        try {
            loaiBUS.ExportExcelDatabase();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportloaiexlMouseClicked

    private void exportnhexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportnhexlMouseClicked
        try {
            nhapBUS.ExportExcelDatabase();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportnhexlMouseClicked

    private void importctnhexlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importctnhexlMouseClicked
        try {
            chitietnhapBUS.ImportExcelDatabase();
            creatTableChiTietPhieuNhapHang();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_importctnhexlMouseClicked

    private void displaySPConhang10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPConhang10MouseClicked
        // TODO add your handling code here:
         KhachHangBUS bus = new KhachHangBUS();
        try{
        bus.nhapexlKH();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "lỗi nhập");
            System.out.println(e);
        }
    }//GEN-LAST:event_displaySPConhang10MouseClicked

    private void displaySPConhang11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPConhang11MouseClicked
        // TODO add your handling code here:
        KhachHangBUS bus = new KhachHangBUS();
        try {
            bus.xuatexlKH();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_displaySPConhang11MouseClicked

    private void displaySPConhang12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPConhang12MouseClicked
        // TODO add your handling code here:
         QLNVBUS b=new QLNVBUS();

            b.nhapexl();
            docdsnv();
    }//GEN-LAST:event_displaySPConhang12MouseClicked

    private void displaySPConhang13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displaySPConhang13MouseClicked
        // TODO add your handling code here:
        QLNVBUS b=new QLNVBUS();
        try {
            b.xuatexl();
        } catch (IOException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_displaySPConhang13MouseClicked

    private void jCBNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBNuActionPerformed

    private void displaySPConhang13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displaySPConhang13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displaySPConhang13ActionPerformed

    private void tenTf_khachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenTf_khachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenTf_khachActionPerformed

    private void sdtTf_khachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdtTf_khachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sdtTf_khachActionPerformed

    private void gmailTf_khachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gmailTf_khachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gmailTf_khachActionPerformed

    private void EXITMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EXITMouseClicked
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_EXITMouseClicked

    private void RETURNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RETURNMouseClicked
        // TODO add your handling code here:
        
        Login1 login = new Login1();
        this.dispose();
    }//GEN-LAST:event_RETURNMouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        setBoderMenu();
        setVisibleMenu();
        menuPanel.setVisible(true);
        jPanel2.setVisible(true);
        sellPanel.setVisible(false);
       
        
    }//GEN-LAST:event_jPanel13MouseClicked

    private void idhoadontf_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idhoadontf_sellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idhoadontf_sellActionPerformed

    private void iduserloginlabel_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_iduserloginlabel_sellKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        checkuserlogin(iduserloginlabel_sell.getText());
    }//GEN-LAST:event_iduserloginlabel_sellKeyPressed

    private void tennvtf_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tennvtf_sellKeyPressed

    }//GEN-LAST:event_tennvtf_sellKeyPressed
void checkkhach(String a) throws Exception{
        KhachHangBUS khb=new KhachHangBUS();
        khb.docKhachHang();
        khb.getKh(a);
        idkhtf_sell.setText(khb.getKh(a).getID_KH());
        tenkhtf_sell.setText(khb.getKh(a).getHoKH()+" "+khb.getKh(a).getTenKH());
}
    private void idkhtf_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idkhtf_sellKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            try {
                tenkhtf_sell.setEnabled(true);
                checkkhach(idkhtf_sell.getText());
                tenkhtf_sell.setEnabled(false);
        } catch (Exception ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_idkhtf_sellKeyPressed

    private void tenkhtf_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tenkhtf_sellKeyPressed
 
    }//GEN-LAST:event_tenkhtf_sellKeyPressed
void checksp(String a) throws SQLException, ParseException{
    if(a.equals("")==false)
    if(spBUS.getSP(a)==null){thongbaosanphamtf_sell.setText("Không có sản phẩm đó");}
     else {
   sanphamDTO sp=new sanphamDTO(spBUS.getSP(a));
   tensptf_sell.setText(sp.getTensp());
   tientf_sell.setText(String.valueOf(sp.getDongia()));
   tientf_sell.setEnabled(false);
   if(idggtf_sell.getText().equals("")==false){
    chitietgiamgiaBUS ctb=new chitietgiamgiaBUS();
    ctb.docbus(idggtf_sell.getText());
    giamgiaBUS ggb=new giamgiaBUS();
    ggb.docBUS();
    String idgg;
    if(ggb.getGg(idggtf_sell.getText())==null) idgg="NULL"; else idgg=idggtf_sell.getText();
   giamgiaDTO gg=ggb.getGg(idgg);
   for(chitietgiamgiaDTO ct:ctb.getDsct())
    if(gg.checkng(datelabel_sell.getText()) && ct.getIdsp().equals(idsptf_sell.getText())){
        discounttf_sell.setText(String.valueOf(ct.getDiscount()));
        tgtf_sell.setText(String.valueOf(sp.getDongia()*ct.getDiscount()/100));
   }
     if(discounttf_sell.getText().equals("")){
        discounttf_sell.setText("0");
         tgtf_sell.setText("0");
     }
   }
   else {
       discounttf_sell.setText("0");
       tgtf_sell.setText(String.valueOf(sp.getDongia()*Integer.parseInt(discounttf_sell.getText())/100));
   }
    discounttf_sell.setEnabled(false);
    tgtf_sell.setEnabled(false);
    String ssize[] = new String[new chitietsanphamBUS().getArsize(sp.getMasanpham()).size()+1];
    String scolor[] = new String[1];
    ssize[0]="Chọn size";
    scolor[0]="Chọn màu";
    int i=1;
    for(String str: new chitietsanphamBUS().getArsize(sp.getMasanpham())){
        ssize[i]=str;
        i++;
    }
    size_sell.setModel(new DefaultComboBoxModel<>(ssize));
    color_sell.setModel(new DefaultComboBoxModel<>(scolor));
    }
}

    private void idsptf_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idsptf_sellKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                 try {
                     sltf_sell.setText("1");
                     thongbaosanphamtf_sell.setText("");
                     tensptf_sell.setEnabled(true);
                     checksp(idsptf_sell.getText());
                     tensptf_sell.setEnabled(false);
                 } catch (ParseException ex) {
                     Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
    }//GEN-LAST:event_idsptf_sellKeyPressed
public boolean isnumber(String i){
    try{
        Integer.parseInt(i);
        return true;
    }catch(NumberFormatException e){
        return false;
    }
}
    private void sltf_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sltf_sellKeyPressed
      thongbaoctsp_sell.setText("");
        String s=evt.getKeyChar()+"";
           if(isnumber(s)){
               if(Integer.parseInt(sltf_sell.getText()+s)>0){
                  chitietsanphamBUS ctb=new chitietsanphamBUS();
                  ctb.listCTSP();
                  String idsp=idsptf_sell.getText();
                  String size=(String)size_sell.getSelectedItem();
                  String color=(String)color_sell.getSelectedItem();
                  int maxsl= ctb.getCTSPbyIdSPaSizeaColor(idsp,size,color).getSoluong();
                  if(Integer.parseInt(sltf_sell.getText()+s)>maxsl) {
                      thongbaoctsp_sell.setText("Số lượng vượt quá hàng tồn kho");
                      sltf_sell.setText(String.valueOf(maxsl));
                  }
               }
               else {
                    thongbaoctsp_sell.setText("Số lượng không thể nhỏ hơn hoặc bằng 0");
                    sltf_sell.setText(sltf_sell.getText());
               }
           }
           else {thongbaoctsp_sell.setText("Số lượng phải là một con số");sltf_sell.setText(sltf_sell.getText());
       }
    }//GEN-LAST:event_sltf_sellKeyPressed

    private void giamslbt_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamslbt_sellMouseClicked
        thongbaoctsp_sell.setText("");
        int sl= Integer.parseInt(sltf_sell.getText());
        if(sl>1){sl--;sltf_sell.setText(String.valueOf(sl));}
        else thongbaoctsp_sell.setText("Không thể giảm được nữa");
    }//GEN-LAST:event_giamslbt_sellMouseClicked

    private void tangslbt_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tangslbt_sellMouseClicked
         thongbaoctsp_sell.setText("");
         int sl= Integer.parseInt(sltf_sell.getText());
         chitietsanphamBUS ctb=new chitietsanphamBUS();
         ctb.listCTSP();
         String idsp=idsptf_sell.getText();
         String size=(String)size_sell.getSelectedItem();
         String color=(String)color_sell.getSelectedItem();
         int maxsl= ctb.getCTSPbyIdSPaSizeaColor(idsp,size,color).getSoluong();
         if(sl<maxsl){sl++;sltf_sell.setText(String.valueOf(sl));}
         else thongbaoctsp_sell.setText("Số lượng đã đặt số hàng còn tồn kho");
    }//GEN-LAST:event_tangslbt_sellMouseClicked

    private void size_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_size_sellActionPerformed
        thongbaoctsp_sell.setText("");
         sltf_sell.setText("1");
        String scolor[] = new String[new chitietsanphamBUS().getArcolor(idsptf_sell.getText(),(String)size_sell.getSelectedItem()).size()+1];
        scolor[0]="Chọn màu";
        int i=1;
        for(String str: new chitietsanphamBUS().getArcolor(idsptf_sell.getText(),(String)size_sell.getSelectedItem())){
        scolor[i]=str;
        i++;
    }
    color_sell.setModel(new DefaultComboBoxModel<>(scolor));
       
    }//GEN-LAST:event_size_sellActionPerformed

    private void idggtf_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idggtf_sellKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            idkhtf_sell.requestFocus();
        }
        try {
            checksp(idsptf_sell.getText());
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_idggtf_sellKeyPressed

    private void color_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_sellActionPerformed
         sltf_sell.setText("1");
    }//GEN-LAST:event_color_sellActionPerformed
ArrayList<chitiethoadonDTO> dsct=null;
 public boolean checkcthd(chitiethoadonDTO a){
      for(chitiethoadonDTO ct:dsct){
      if(ct.getMahd().equals(a.getMahd())&&ct.getMasp().equals(a.getMasp())&&ct.getMactsp().equals(a.getMactsp()))
          return true;
      }
      return false;
  }
 void xuatsumary() throws ParseException, SQLException{
     String idgg;
     giamgiaBUS ggb=new giamgiaBUS();
     ggb.docBUS();
     if(ggb.getGg(idggtf_sell.getText())==null) idgg="NULL";
     else idgg=idggtf_sell.getText();
     chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS();
     ctggb.docbus(idgg);
     giamgiaDTO gg=ggb.getGg(idgg);
     int t=0;
     int tg=0;
     for(chitiethoadonDTO ct:dsct){
         t+=ct.getDg();
        if(gg.checkng(datelabel_sell.getText())){
            for(chitietgiamgiaDTO ctgg:ctggb.getDsct()){
                if(ct.getMasp().equals(ctgg.getIdsp()))
                    tg+=ct.getDg()*ctgg.getDiscount()/100;
            }
        }
     }
     ttlabel_sell.setText(String.valueOf(t));
     ttglabel_sell.setText(String.valueOf(tg));
     ttptlabel_sell.setText(String.valueOf(t-tg));
 }
void checkbill() throws ParseException, SQLException{
    chitietsanphamBUS ctspBUS=new chitietsanphamBUS();
        ctspBUS.listCTSP();
        chitietsanphamDTO ctsp=ctspBUS.getCTSPbyIdSPaSizeaColor(idsptf_sell.getText(),(String)size_sell.getSelectedItem(),(String)color_sell.getSelectedItem());
   if(isnumber(sltf_sell.getText()) && Integer.parseInt(sltf_sell.getText())<=ctsp.getSoluong()){
    if(idsptf_sell.getText().equals("") || ((String)size_sell.getSelectedItem()).equals("") || ((String)color_sell.getSelectedItem()).equals("") || tensptf_sell.getText().equals(""))
        thongbaoctsp_sell.setText("Vui lòng điền đầy đủ thông tin");
    else {
        if(dsct==null) dsct=new ArrayList<>();
        String idhd=getidhd(idhoadontf_sell.getText());
        String idsp=idsptf_sell.getText();
        int sl=Integer.parseInt(sltf_sell.getText());
        chitiethoadonDTO cthd=new chitiethoadonDTO(idhd,idsp,ctsp.getMachitietsanpham(),sl,spBUS.getSP(idsp).getDongia()*sl);
         if(checkcthd(cthd)) {
             thongbaoctsp_sell.setText("Sản phẩm đã tồn tại trong hóa đơn");
         }
         else{
         Vector header=new Vector();
        header.add("Mã sản phẩm");
        header.add("Mã chi tiết sản phẩm");
        header.add("Số lượng");
        header.add("Giá");
        if(modelcthdsell==null)modelcthdsell=new DefaultTableModel(header,0);
        Vector row=new Vector();
        row.add(cthd.getMasp());
        row.add(cthd.getMactsp());
        row.add(cthd.getSl());
        row.add(cthd.getDg());
        modelcthdsell.addRow(row);
        dsct.add(cthd);
        xuatsumary();
        cthdtable_sell.setModel(modelcthdsell);
        }
    }
   }else {JOptionPane.showMessageDialog(null,"Vui lòng kiểm tra lại số lượng"); sltf_sell.requestFocus();}
}
    private void themcthdbtn_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themcthdbtn_sellActionPerformed
        if(!idkhtf_sell.getText().equals(""))
            if(!idsptf_sell.getText().equals(""))
                    if(!((String)size_sell.getSelectedItem()).equals("Chọn size") )
                        if(!((String)color_sell.getSelectedItem()).equals("Chọn màu"))
                                try {
                                    thongbaoctsp_sell.setText("");
                                    checkbill();
                                } catch (ParseException ex) {
                                    Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                         else JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
                    else JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
            else JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
        else JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
    }//GEN-LAST:event_themcthdbtn_sellActionPerformed

    private void tensptf_sellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tensptf_sellKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tensptf_sellKeyPressed

    private void delhdpn_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delhdpn_sellMouseClicked
       int i=cthdtable_sell.getSelectedRow();
        if(i>=0){
       modelcthdsell.removeRow(i);
       dsct.remove(i);
       ttlabel_sell.setText("0");
       ttglabel_sell.setText("0");
       ttptlabel_sell.setText("0");
        }
    }//GEN-LAST:event_delhdpn_sellMouseClicked

    private void discounttfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discounttfKeyPressed

           if(Integer.parseInt(discounttf.getText())<=0)
               JOptionPane.showMessageDialog(null,"Phần trăm giảm không thể ít hơn 1");
    }//GEN-LAST:event_discounttfKeyPressed

    private void delhdlabel_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delhdlabel_sellMouseClicked
        int i=cthdtable_sell.getSelectedRow();
        if(i>=0){
       modelcthdsell.removeRow(i);
       dsct.remove(i);
       ttlabel_sell.setText("0");
       ttglabel_sell.setText("0");
       ttptlabel_sell.setText("0");
        }
    }//GEN-LAST:event_delhdlabel_sellMouseClicked

    private void editslpn_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editslpn_sellMouseClicked
        int i=cthdtable_sell.getSelectedRow();
        if(i>=0){
            int sl=0;
            chitiethoadonDTO ct=new chitiethoadonDTO();
            ct=dsct.get(i);
            
            while (true){
            sl=Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng"));
            if(sl>0){
            chitietsanphamBUS ctb=new chitietsanphamBUS();
            ctb.listCTSP();
            int slm=ctb.getSoLuongCTSP(ct.getMactsp());
            if(sl>slm) JOptionPane.showMessageDialog(null,"Số lượng đã vượt số lượng hàng tồn kho"); else break;
            } else JOptionPane.showMessageDialog(null,"Số lượng không thể nhỏ hơn 1");
            }
            ct.setSl(sl);
            ct.setDg(sl*spBUS.getSP(ct.getMasp()).getDongia());
            modelcthdsell.setValueAt(sl, i, 2);
            modelcthdsell.setValueAt(ct.getDg(),i,3);
            cthdtable_sell.setModel(modelcthdsell);
            try {
                xuatsumary();
            } catch (ParseException ex) {
                Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editslpn_sellMouseClicked

    private void editsllabel_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editsllabel_sellMouseClicked
        int i=cthdtable_sell.getSelectedRow();
        if(i>=0){
            int sl=0;
            chitiethoadonDTO ct=new chitiethoadonDTO();
            ct=dsct.get(i);
            
            while (true){
            sl=Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng"));
            if(sl>0){
            chitietsanphamBUS ctb=new chitietsanphamBUS();
            ctb.listCTSP();
            int slm=ctb.getSoLuongCTSP(ct.getMactsp());
            if(sl>slm) JOptionPane.showMessageDialog(null,"Số lượng đã vượt số lượng hàng tồn kho"); else break;
            } else JOptionPane.showMessageDialog(null,"Số lượng không thể nhỏ hơn 1");
            }
            ct.setSl(sl);
            ct.setDg(sl*spBUS.getSP(ct.getMasp()).getDongia());
            modelcthdsell.setValueAt(sl, i, 2);
            modelcthdsell.setValueAt(ct.getDg(),i,3);
            cthdtable_sell.setModel(modelcthdsell);
            try {
                xuatsumary();
            } catch (ParseException ex) {
                Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editsllabel_sellMouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        JOptionPane.showMessageDialog(null,"Chức năng hiện đang cập nhật");
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        JOptionPane.showMessageDialog(null,"Chức năng hiện đang cập nhật");
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked
        JOptionPane.showMessageDialog(null,"Chức năng hiện đang cập nhật");
    }//GEN-LAST:event_jLabel73MouseClicked

    private void jLabel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel75MouseClicked
        JOptionPane.showMessageDialog(null,"Chức năng hiện đang cập nhật");
    }//GEN-LAST:event_jLabel75MouseClicked
String getidhd(String a){
String []s=a.split("HD",2);
return s[1];
}
    void thanhtoan() throws SQLException, Exception{
    String idhd=getidhd(idhoadontf_sell.getText());
    String idkh=new String();
    if(idkhtf_sell.getText().equals(""))
    idkh="1";
    else idkh=idkhtf_sell.getText();
    String idnv=iduserloginlabel_sell.getText();
    String idgg=new String();
    giamgiaBUS ggb=new giamgiaBUS();
    ggb.docBUS();
    if(ggb.getGg(idggtf_sell.getText())==null) idgg="NULL"; else
    idgg=idggtf_sell.getText();
    String ngay=datelabel_sell.getText();
    int tong=Integer.parseInt(ttlabel_sell.getText());
    int tiengiam=Integer.parseInt(ttglabel_sell.getText());
    int thanhtien=Integer.parseInt(ttptlabel_sell.getText());
    hoadonDTO hd=new hoadonDTO(idhd,idkh,idnv,idgg,ngay,tong,tiengiam,thanhtien);
    hoadonBUS hdb=new hoadonBUS();
    hdb.them(hd);
    chitiethoadonBUS cthdb=new chitiethoadonBUS();
    for(chitiethoadonDTO cthd:dsct){
        cthdb.them(cthd);
        sanphamDTO sp=spBUS.getSP(cthd.getMasp());
        sp.setSoluong(sp.getSoluong()-cthd.getSl());
        chitietsanphamDTO ctsp=ctspBUS.getCTSP(cthd.getMactsp());
        ctsp.setSoluong(ctsp.getSoluong()-cthd.getSl());
        spBUS.setSP(sp);
        ctspBUS.setSP(ctsp);
    }
    creatTableSanPham();
    dsct=new ArrayList<chitiethoadonDTO>();
}
void InBill(String a) throws SQLException, ParseException{
    InBill ib=new InBill();
    ib.print(a);
}
    private void carhlabel_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carhlabel_sellMouseClicked
        if(!ttlabel_sell.getText().equals("0")){
        int get=0;
       String [] chose={"Thanh toán","Hủy bỏ"};
       get=Integer.parseInt(JOptionPane.showInputDialog("Tiền khách hàng đưa"));
       int pay=Integer.parseInt(ttptlabel_sell.getText());
       if(get<pay)JOptionPane.showMessageDialog(null,"Chưa nhận đủ tiền");
       else{
           if(get==pay){
                int ch=JOptionPane.showOptionDialog(null,"Đã nhận đủ tiền tiến hành thanh toán ? ","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,chose,chose[0]);
                if(ch==0) {
                    try {
                        thanhtoan();
                    } catch (SQLException ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        InBill(getidhd(idhoadontf_sell.getText()));
                        idhoadontf_sell.setEnabled(true);
                        checkidhd();
                        clearsell();
                    } catch (SQLException ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           }else{
               int ch=JOptionPane.showOptionDialog(null,"Đã nhận dư"+(get-pay)+" tiền tiến hành thanh toán ? ","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,chose,chose[0]);
                if(ch==0) {
                   try {
                       thanhtoan();
                   } catch (SQLException ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (Exception ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   try {
                       InBill(getidhd(idhoadontf_sell.getText()));
                       idhoadontf_sell.setEnabled(true);
                        checkidhd();
                        clearsell();
                   } catch (SQLException ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (ParseException ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
           }
       }}
        else JOptionPane.showMessageDialog(null, "Không có gì để thanh toán");
    }//GEN-LAST:event_carhlabel_sellMouseClicked

    private void searchid_hdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchid_hdKeyPressed
              
    }//GEN-LAST:event_searchid_hdKeyPressed

    private void searchSPBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSPBtn4ActionPerformed
        ArrayList<hoadonDTO> dshd=new ArrayList<>();
        hoadonBUS hdb=new hoadonBUS();
        try {
            hdb.docbus();
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!searchid_hd.getText().equals("")){
            hoadonDTO hd=new hoadonDTO();
            hd=hdb.search(searchid_hd.getText());
            dshd.add(hd);
        }else if(!searchstring_hd.getText().equals("")){
            dshd=hdb.searchString(searchstring_hd.getText());
        }
        Vector header=new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã khách hàng");
        header.add("Mã nhân viên");
        header.add("Mã giảm giá");
        header.add("Ngày");
        header.add("Tổng tiền");
        header.add("Tiền giảm");
        header.add("Tiền phải trả");
        if(modelhd!=null)modelhd=new DefaultTableModel(header,0);
        Vector row=new Vector();
        for(hoadonDTO hd1:dshd){
        row.add(hd1.getMahd());
        row.add(hd1.getMakh());
        row.add(hd1.getManv());
        row.add(hd1.getMagg());
        row.add(hd1.getNgay());
        row.add(hd1.getTong());
        row.add(hd1.getTg());
        row.add(hd1.getTpt());
        modelhd.addRow(row);
        }
        jtbdshd.setModel(modelhd);
    }//GEN-LAST:event_searchSPBtn4ActionPerformed

    private void searchSPBtn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSPBtn7ActionPerformed
        ArrayList<giamgiaDTO> dsgg=new ArrayList<>();
        giamgiaBUS ggb=new giamgiaBUS();
        try {
            try {
                ggb.docBUS();
            } catch (ParseException ex) {
                Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!searchid_gg.getText().equals("")){
            giamgiaDTO gg=ggb.search(searchid_gg.getText());
            dsgg.add(gg);
        }else if(!searchstring_gg.getText().equals("")){
            dsgg=ggb.searchString(searchstring_gg.getText());
        }
        Vector header=new Vector();
            header.add("Mã gg");
            header.add("Tên gg");
            header.add("Ngày bắt đầu");
            header.add("Ngày kết thúc");
            if(modelgg!=null)
                modelgg=new DefaultTableModel(header,0);
            for(giamgiaDTO gg:dsgg){
                Vector row=new Vector();
                row.add(gg.getMagg());
                row.add(gg.getTengg());
                row.add(gg.getNgbd());
                row.add(gg.getNgkt());
                 modelgg.addRow(row);
            }
            giamgiatable.setModel(modelgg);
            header =new Vector();
            header.add("Mã gg");
            header.add("Mã sp");
            header.add("Phần trăm giảm");
            if(modelctgg != null) modelctgg=new DefaultTableModel(header,0);
            jtbcthd.setModel(modelctgg);
    }//GEN-LAST:event_searchSPBtn7ActionPerformed

    private void cashlabel_sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashlabel_sellMouseClicked
         if(!ttlabel_sell.getText().equals("0")){
        int get=0;
       String [] chose={"Thanh toán","Hủy bỏ"};
       get=Integer.parseInt(JOptionPane.showInputDialog("Tiền khách hàng đưa"));
       int pay=Integer.parseInt(ttptlabel_sell.getText());
       if(get<pay)JOptionPane.showMessageDialog(null,"Chưa nhận đủ tiền");
       else{
           if(get==pay){
                int ch=JOptionPane.showOptionDialog(null,"Đã nhận đủ tiền tiến hành thanh toán ? ","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,chose,chose[0]);
                if(ch==0) {
                    try {
                        thanhtoan();
                    } catch (SQLException ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        InBill(getidhd(idhoadontf_sell.getText()));
                        idhoadontf_sell.setEnabled(true);
                        checkidhd();
                        clearsell();
                    } catch (SQLException ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           }else{
               int ch=JOptionPane.showOptionDialog(null,"Đã nhận dư"+(get-pay)+" tiền tiến hành thanh toán ? ","Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,chose,chose[0]);
                if(ch==0) {
                   try {
                       thanhtoan();
                   } catch (SQLException ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (Exception ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   try {
                       InBill(getidhd(idhoadontf_sell.getText()));
                       idhoadontf_sell.setEnabled(true);
                        checkidhd();
                        clearsell();
                   } catch (SQLException ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (ParseException ex) {
                       Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
           }
       }}
        else JOptionPane.showMessageDialog(null, "Không có gì để thanh toán");
    }//GEN-LAST:event_cashlabel_sellMouseClicked

    private void resetBtn_khachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtn_khachMouseClicked
        // TODO add your handling code here:
            hoTf_khach.setText(null);
            tenTf_khach.setText(null);
            gmailTf_khach.setText(null);
            sdtTf_khach.setText(null); 
    }//GEN-LAST:event_resetBtn_khachMouseClicked

    private void searchTfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTfKeyReleased
        // TODO add your handling code here:
        timKH();
        
    }//GEN-LAST:event_searchTfKeyReleased

    private void timid_nvFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timid_nvFocusGained
        // TODO add your handling code here:
        if (timid_nv.getText().equals("Tìm IDNV"))
        {
            timid_nv.setText("");
            timid_nv.setForeground(Color.BLACK);
        }

        
    }//GEN-LAST:event_timid_nvFocusGained

    private void timid_nvFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timid_nvFocusLost
        // TODO add your handling code here:
        if (timid_nv.getText().equals(""))
        {
            timid_nv.setText("Tìm IDNV");
            timid_nv.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_timid_nvFocusLost

    private void timid_nvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timid_nvKeyReleased
        // TODO add your handling code here:
        timkiemidnv();
    }//GEN-LAST:event_timid_nvKeyReleased

    private void hoTf_nhanvienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hoTf_nhanvienKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_hoTf_nhanvienKeyTyped
void clearsell() throws SQLException, ParseException{
    idggtf_sell.setText("");
    idkhtf_sell.setText("");
    tenkhtf_sell.setEnabled(true);
    tenkhtf_sell.setText("");
    idsptf_sell.setText("");
    checksp("0");
    tensptf_sell.setText("");
    discounttf_sell.setEnabled(true);
    discounttf_sell.setText("");
    tgtf_sell.setEnabled(true);
    tgtf_sell.setText("");
    sltf_sell.setText("1");
    modelcthd=new DefaultTableModel();
    cthdtable_sell.setModel(modelcthd);
    ttlabel_sell.setText("0");
    ttglabel_sell.setText("0");
    ttptlabel_sell.setText("0");
    dsct=new ArrayList<>();
    tientf_sell.setEnabled(true);
    tientf_sell.setText("");
}
    void reset_nv()
    {
        clickxnhanvien();
        setmautextnhanvien();
    }
    void sua_nv()
    {
        QLNVDTO nv=new QLNVDTO();
         
            int  i=dsnvTb_nhanvien.getSelectedRow();
            if (i>=0)
            { 
            nv.setId((String) dsnvTb_nhanvien.getValueAt(i, NORMAL));
            nv.setHo(hoTf_nhanvien.getText());
            nv.setTen(tenTf_nhanvien.getText());
            nv.setDiachi(diachiTf_nhanvien.getText());
            nv.setEmail(emailTf_nhanvien.getText());
            nv.setSdt(sdtTf_nhanvien.getText());
            nv.setLuong(luongTf_nhanvien.getText());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = formatter.format(jDCns_nhanvien.getDate());
            nv.setNgaysinh(dateFormat);
            if(jCBNam.isSelected()){
            nv.setGioitinh("Nam");
            }
            else {nv.setGioitinh("Nữ");}
            //cap nhận dssv
            QLNVDTO old= bus.getDSNV().set(i, nv);
            modelnhanvien.setValueAt(nv.getId(), i, 0);
            modelnhanvien.setValueAt(nv.getHo(), i, 1);
            modelnhanvien.setValueAt(nv.getTen(), i, 2);
            modelnhanvien.setValueAt(nv.getNgaysinh(), i, 3);
            modelnhanvien.setValueAt(nv.getGioitinh(), i, 4);
            modelnhanvien.setValueAt(nv.getDiachi(), i, 5);
            modelnhanvien.setValueAt(nv.getEmail(), i, 6);
            modelnhanvien.setValueAt(nv.getSdt(), i, 7);
            modelnhanvien.setValueAt(nv.getLuong(), i, 8);   
            dsnvTb_nhanvien.setModel(modelnhanvien);
            bus.sua(nv);
            dsnvTb_nhanvien.setModel(modelnhanvien);
            
            }    
    }
    boolean rangbuoctennv()
    {
        if (tenTf_nhanvien.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ\" +\n" +
"            \"ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ\" +\n" +
"            \"ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\\\s]+$"))
        {
            return true;
            
        }
        else 
        {
            return false;
        }
            
    }
    boolean rangbuochonv()
    {
         if (hoTf_nhanvien.getText().matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ\" +\n" +
"            \"ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ\" +\n" +
"            \"ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\\\s]+$"))
        {
            return true;
            
        }
        else 
        {
            return false;
        }
    }
    boolean rangbuocsdt()
    {
         if (sdtTf_nhanvien.getText().matches("[0-9]{10,10}"))
        {
            return true;
            
        }
        else 
        {
            return false;
        }
    }
    boolean rangbuocluong()
    {
          if (luongTf_nhanvien.getText().matches("[0-9]{5,15}"))
        {
            return true;
            
        }
        else 
        {
            return false;
        }
    }
    boolean rangbuocemail()
    {
        String email="^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
          if (emailTf_nhanvien.getText().matches(email))
        {
            return true;
            
        }
        else 
        {
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame a=new frame("");
                    a.setVisible(true);
                    a.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EXIT;
    private javax.swing.JLabel RETURN;
    private javax.swing.JLabel addSPBtn;
    private javax.swing.JLabel addSPBtn1;
    private javax.swing.JLabel addSPBtn2;
    private javax.swing.JLabel addSPBtn3;
    private javax.swing.JLabel addSPBtn4;
    private javax.swing.JLabel addSPBtn6;
    private javax.swing.JLabel addctggbt;
    private javax.swing.JLabel addggbt;
    private javax.swing.JPanel bigPanel;
    private javax.swing.ButtonGroup btnGroupKH;
    private javax.swing.ButtonGroup btnGroupNV;
    private javax.swing.JPanel capnhathoadonPanel;
    private javax.swing.JPanel capnhathoadontablepanel;
    private javax.swing.JLabel capnhatlabel;
    private javax.swing.JPanel capnhatsanphamPanel;
    private javax.swing.JPanel capnhatsanphamtable;
    private javax.swing.JPanel carhlabel_sell;
    private javax.swing.JLabel cashlabel_sell;
    private javax.swing.JTable chitietnhaphangtable;
    private javax.swing.JPanel chuongtrinhgiamgiaPanel;
    private javax.swing.JPanel chuongtrinhgiamgiatablepanel;
    private javax.swing.JComboBox<String> colorBox;
    private javax.swing.JComboBox<String> color_sell;
    private javax.swing.JTable ctgiamgiatable;
    private javax.swing.JTable cthdtable_sell;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JPanel dashboardPanel;
    private com.toedter.calendar.JDateChooser datebd;
    private com.toedter.calendar.JDateChooser datekt;
    private javax.swing.JLabel datelabel_sell;
    private javax.swing.JLabel deleteSPBtn;
    private javax.swing.JLabel deleteSPBtn1;
    private javax.swing.JLabel deleteSPBtn2;
    private javax.swing.JLabel deleteSPBtn3;
    private javax.swing.JLabel deleteSPBtn4;
    private javax.swing.JLabel deleteSPBtn6;
    private javax.swing.JLabel deletectggbt;
    private javax.swing.JLabel deleteggbt;
    private javax.swing.JLabel deletehd;
    private javax.swing.JLabel delhdlabel_sell;
    private javax.swing.JPanel delhdpn_sell;
    private javax.swing.JTable detailproducttable;
    private javax.swing.JTextField diachiTf_ncc;
    private javax.swing.JTextField diachiTf_nhanvien;
    private javax.swing.JTextField discounttf;
    private javax.swing.JTextField discounttf_sell;
    private javax.swing.JButton displaySPConhang;
    private javax.swing.JButton displaySPConhang1;
    private javax.swing.JButton displaySPConhang10;
    private javax.swing.JButton displaySPConhang11;
    private javax.swing.JButton displaySPConhang12;
    private javax.swing.JButton displaySPConhang13;
    private javax.swing.JButton displaySPConhang2;
    private javax.swing.JButton displaySPhethang;
    private javax.swing.JButton displayallsp;
    private javax.swing.JTextField dongiaTf;
    private javax.swing.JTextField dongiaTf_ctnh;
    private javax.swing.JTable dsKHTable;
    private javax.swing.JTable dsnvTb_nhanvien;
    private javax.swing.JLabel editSPBtn;
    private javax.swing.JLabel editSPBtn1;
    private javax.swing.JLabel editSPBtn2;
    private javax.swing.JLabel editSPBtn3;
    private javax.swing.JLabel editSPBtn4;
    private javax.swing.JLabel editSPBtn6;
    private javax.swing.JLabel editctggbt;
    private javax.swing.JLabel editggbt;
    private javax.swing.JLabel editsllabel_sell;
    private javax.swing.JPanel editslpn_sell;
    private javax.swing.JTextField emailTf_nhanvien;
    private javax.swing.JLabel exportcthdexcelbt;
    private javax.swing.JButton exportctspexl;
    private javax.swing.JLabel exporthdexcelbt;
    private javax.swing.JButton exportloaiexl;
    private javax.swing.JButton exportnccexl;
    private javax.swing.JButton exportnhexl;
    private javax.swing.JButton exportspexl;
    private javax.swing.JTable giamgiatable;
    private javax.swing.JButton giamslbt_sell;
    private javax.swing.JTextField gmailTf_khach;
    private javax.swing.JTextField hoTf_khach;
    private javax.swing.JTextField hoTf_nhanvien;
    private javax.swing.JTextField idggtf_sell;
    private javax.swing.JTextField idhoadontf_sell;
    private javax.swing.JTextField idkhtf_sell;
    private javax.swing.JTextField idsptf;
    private javax.swing.JTextField idsptf_sell;
    private javax.swing.JTextField iduserloginlabel_sell;
    private javax.swing.JLabel importcthdexlbt;
    private javax.swing.JButton importctnhexl;
    private javax.swing.JLabel importhdexlbt;
    private javax.swing.JButton importloaiexl;
    private javax.swing.JButton importnccexl;
    private javax.swing.JButton importspexl;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCBNam;
    private javax.swing.JCheckBox jCBNu;
    private com.toedter.calendar.JDateChooser jDCns_nhanvien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JCheckBox jgioitinhNU;
    private javax.swing.JCheckBox jgioitinhNam;
    private javax.swing.JTable jtbcthd;
    private javax.swing.JTable jtbdshd;
    private javax.swing.JLabel khachhangbackground;
    private javax.swing.JPanel khachhangpanel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JTextField loaiSearchTf;
    private javax.swing.JPanel loaisanphamPanel;
    private javax.swing.JTable loaitable;
    private javax.swing.JPanel loaitablepanel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JTextField luongTf_nhanvien;
    private javax.swing.JLabel machitietsanphamLabel;
    private javax.swing.JTextField machitietsanphamTf;
    private javax.swing.JTextField maggtf;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox<String> maloaiBox;
    private javax.swing.JTextField maloaiTf_loaisanphamPanel;
    private javax.swing.JComboBox<String> manhacungcapBox;
    private javax.swing.JTextField manhacungcapTf_ncc;
    private javax.swing.JTextField manhanvienTf;
    private javax.swing.JTextField maphieunhapTf;
    private javax.swing.JTextField maphieunhapTf2;
    private javax.swing.JTextField masanpham2Tf;
    private javax.swing.JTextField masanphamTf;
    private javax.swing.JTextField masanphamTf_ctnh;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel nam;
    private javax.swing.JLabel nghia;
    private javax.swing.JPanel nhacungcapPanel;
    private javax.swing.JTextField nhacungcapSearch;
    private javax.swing.JTable nhacungcaptable;
    private javax.swing.JPanel nhacungcaptablepanel;
    private javax.swing.JLabel nhanvienbackground;
    private javax.swing.JPanel nhanvienpanel;
    private javax.swing.JPanel nhaphangPanel;
    private javax.swing.JLabel nhaphanglabel;
    private javax.swing.JTable nhaphangtable;
    private javax.swing.JPanel nhaphangtablepanel;
    private javax.swing.JTextField nsxTf;
    private javax.swing.JLabel orderLabel;
    private javax.swing.JPanel orderLeftPanel;
    private javax.swing.JLabel phat;
    private javax.swing.JLabel phu;
    private javax.swing.JLabel productLabel;
    private javax.swing.JPanel productLeftPanel;
    private javax.swing.JPanel productRightPanel;
    private javax.swing.JLabel resetBtn_khach;
    private javax.swing.JLabel reset_nv;
    private javax.swing.JPanel row1;
    private javax.swing.JPanel row1col1;
    private javax.swing.JPanel row1col2;
    private javax.swing.JPanel row1col3;
    private javax.swing.JPanel row2;
    private javax.swing.JPanel row2col1;
    private javax.swing.JPanel row2col2;
    private javax.swing.JPanel row3;
    private javax.swing.JTable sanphamTable;
    private javax.swing.JTextField sdtTf_khach;
    private javax.swing.JTextField sdtTf_ncc;
    private javax.swing.JTextField sdtTf_nhanvien;
    private javax.swing.JButton searchSPBtn;
    private javax.swing.JButton searchSPBtn1;
    private javax.swing.JButton searchSPBtn2;
    private javax.swing.JButton searchSPBtn3;
    private javax.swing.JButton searchSPBtn4;
    private javax.swing.JButton searchSPBtn7;
    private javax.swing.JTextField searchSanPhamTf;
    private javax.swing.JTextField searchTf;
    private javax.swing.JTextField searchid_gg;
    private javax.swing.JTextField searchid_hd;
    private javax.swing.JTextField searchstring_gg;
    private javax.swing.JTextField searchstring_hd;
    private javax.swing.JLabel sellLabel;
    private javax.swing.JPanel sellPanel;
    private javax.swing.JLabel settingLabel;
    private javax.swing.JPanel settingPanel;
    private javax.swing.JComboBox<String> sizeBox;
    private javax.swing.JComboBox<String> size_sell;
    private javax.swing.JTextField sltf_sell;
    private javax.swing.JTextField soluong2Tf;
    private javax.swing.JTextField soluongTf;
    private javax.swing.JTextField soluongTf_ctnh;
    private javax.swing.JLabel staffLabel;
    private javax.swing.JLabel suaBtn_khach;
    private javax.swing.JLabel sua_nv;
    private javax.swing.JButton tangslbt_sell;
    private javax.swing.JTextField tenTf_khach;
    private javax.swing.JTextField tenTf_nhanvien;
    private javax.swing.JTextField tenggtf;
    private javax.swing.JTextField tenkhtf_sell;
    private javax.swing.JTextField tenloaiTf_loaisanphamPanel;
    private javax.swing.JTextField tennhacungcapTf_ncc;
    private javax.swing.JTextField tennvtf_sell;
    private javax.swing.JTextField tensanphamTf;
    private javax.swing.JLabel tensplabel;
    private javax.swing.JTextField tensptf_sell;
    private javax.swing.JTextField tgtf_sell;
    private javax.swing.JLabel themBtn_khach;
    private javax.swing.JLabel them_nv;
    private javax.swing.JButton themcthdbtn_sell;
    private javax.swing.JLabel thongbaoctsp_sell;
    private javax.swing.JLabel thongbaosanpham;
    private javax.swing.JLabel thongbaosanphamtf_sell;
    private javax.swing.JTextField tientf_sell;
    private javax.swing.JLabel timer;
    private javax.swing.JTextField timid_nv;
    private javax.swing.JTextField timkiemTf_nhanvien;
    private javax.swing.JTextField tongtienTf;
    private javax.swing.JLabel ttcthdlabel;
    private javax.swing.JLabel ttglabel_sell;
    private javax.swing.JLabel tthdlabel;
    private javax.swing.JLabel ttlabel_sell;
    private javax.swing.JLabel ttptlabel_sell;
    private javax.swing.JLabel xoaBtn_khach;
    private javax.swing.JLabel xoa_nv;
    // End of variables declaration//GEN-END:variables
}
