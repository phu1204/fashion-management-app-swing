/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author Shadow
 */
public class InBill {

    private ArrayList<chitietgiamgiaDTO> dsctgg=new ArrayList<chitietgiamgiaDTO>(); // lấy ct gg
    private ArrayList<chitiethoadonDTO> cthd = new ArrayList<chitiethoadonDTO>(); // lấy cthd
    private chitiethoadonBUS cthdBus = new chitiethoadonBUS(); //lấy dscthd
    private sanphamBUS spBus = new sanphamBUS(); // lấy sp
    private hoadonBUS hdbus = new hoadonBUS(); // lấy hd
    private giamgiaDTO gg=new giamgiaDTO(); // lấy ngày giảm giá
    private chitietgiamgiaBUS ctggb=new chitietgiamgiaBUS(); // lấy thông tin chi tiết hóa đơn
    private QLNVBUS nvb=new QLNVBUS(); // lấy thông tin nv
    private BaseFont bf;
    private giamgiaBUS ggb=new giamgiaBUS();
    public InBill() throws SQLException, ParseException {
        spBus.listSP();
        hdbus.docbus();
        ggb.docBUS();
    }

    public void print(String mahd) throws ParseException, SQLException {
        cthdBus.docbus(mahd);
        cthd=cthdBus.getDsct();
        hoadonDTO hd=new hoadonDTO();
        hd=hdbus.search(mahd);
        ctggb.docbus(hd.getMagg());
        String ngay=hd.getNgay();
        gg=ggb.getGg(hd.getMagg());
        String uderline = "-";
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            File file;
            int count = 0;
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                file = new File(chooser.getSelectedFile() + "/bill"+"-HD"+mahd + ".pdf");
                while(file.exists()) {
                    String s = "/bill" +"-HD"+ mahd + ".pdf";
                    String []parts = s.split(".",2);
              
                    file = new File(chooser.getSelectedFile() +"/"+"("+(count++)+")"+parts[1] );
                }
                bf = BaseFont.createFont("src\\font\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Document bill = new Document(PageSize.A4, 15, 15, 10, 10);

                String line = "";
                for (int i = 0; i < bill.getPageSize().getWidth() / 5; i++) {
                    line += uderline;
                }
                PdfWriter.getInstance(bill, new FileOutputStream(file));

                bill.open();

                Paragraph header = new Paragraph("FASHION STORE", new Font(bf, 35));
                header.setAlignment(1);
                bill.add(header);

                Paragraph info = new Paragraph("Hóa đơn : HD"+mahd 
                        + "                     Ngày : " + ngay
                        + "                                                                        Nhân viên : " + nvb.getNv(hd.getManv()).getHo()+" "+nvb.getNv(hd.getManv()).getTen(), new Font(bf, 15));
                bill.add(info);
              
                Paragraph l = new Paragraph(line);
                l.setAlignment(1);
                bill.add(l);

                String[] cellHeader = {"Tên SP", "SL","Đơn Giá (VNĐ)", "Giảm giá","Thành tiền"};

                PdfPTable t = new PdfPTable(cellHeader.length);
                t.setSpacingAfter(5);
                t.setSpacingBefore(5);
                int[] relativeWidths = {20, 20, 20, 20,20};
                t.setWidths(relativeWidths);

                for (String s : cellHeader) {
                    t.addCell(createCell(s, new Font(bf, 13)));
                }
         
                    for (chitiethoadonDTO ct : cthd) {
                        for(sanphamDTO sp:spBus.getList())
                                if(ct.getMasp().equals(sp.getMasanpham())){                                    
                                    t.addCell(createCell(sp.getTensp()));
                                    t.addCell(createCell(String.valueOf(ct.getSl())));
                                    t.addCell(createCell(String.valueOf(sp.getDongia())));
                                    int tg=0;
                                    if(gg.checkng(ngay))
                                        for(chitietgiamgiaDTO ctgg:dsctgg)
                                            if(ctgg.getIdsp().equals(sp.getMasanpham()))
                                                tg=sp.getDongia()*ctgg.getDiscount()/100;
                                    t.addCell(createCell(String.valueOf(tg)));
                                    t.addCell(createCell(String.valueOf((sp.getDongia()-tg)*ct.getSl())));                          
                                
                        }

                    }
             
                
                bill.add(t);

                bill.add(l);

                Paragraph sum = new Paragraph("Thanh toán : " + hd.getTpt() + "đ", new Font(bf, 20));
                sum.setAlignment(Element.ALIGN_RIGHT);
                bill.add(sum);

                bill.close();
                JOptionPane.showMessageDialog(null, "In hoàn tất");
                
            }
            
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(InBill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InBill.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PdfPCell createCell(String s) {
        PdfPCell cell = new PdfPCell(new Phrase(s, new Font(bf, 13)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);

        return cell;
    }

    public PdfPCell createCell(String s, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(s, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);
        return cell;
    }

    

}
