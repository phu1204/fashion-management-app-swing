/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import DATA.Query;
import DTO.queryDTO;
import DTO.sanphamDTO;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.QuadCurve;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Renderer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import static org.apache.pdfbox.cos.COSName.CP;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



/**
 *
 * @author Lenovo
 */
public class dashboard extends javax.swing.JFrame {
    private DefaultTableModel model1;
    /**
     * Creates new form dashboard
     */
    public dashboard() {
        initComponents();
        
        monthlyChart();
        
        
       decoration();
       information();
       Query q = new Query();
       
        
    }
    public void creatTableSanPham(){
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new Dimension(0,0));     
        jTable1.setRowHeight(100);
        jTable1.setShowVerticalLines(false);              
        jTable1.getTableHeader().setOpaque(true);
        jTable1.setFillsViewportHeight(true);
        jTable1.getTableHeader().setPreferredSize(new Dimension(50, 50));
        jTable1.setSelectionBackground(new Color(153,204,255)); 
        
        
        //set row sorter
        //TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model1);
        //sanphamTable.setRowSorter(rowSorter);
        ArrayList<queryDTO> q = new ArrayList<>();
        //khởi tạo BUS san pham
        //spBUS.listSP();
        //xuat table
        outModel1(model1, q,"","","");
    }
    public void outModel1(DefaultTableModel model , ArrayList<queryDTO> query,String name,String quantity,String tableName){
        tablename.setText(tableName);
        String arr[]={"STT",name,quantity};
        model1= header(arr);
        jTable1.setModel(model1);
        Vector data;
        data = new Vector();
        model1.setRowCount(0);
        int i=1;
        for(queryDTO s:query){
            model1.addRow(new Object[]{
                i,s.getName(),s.getQuantity()
            });  
            i++;
        }
        
    }
    public void decoration(){
        jDateChooser1.setIcon(icon("carlendar", 30, 30));
        jDateChooser2.setIcon(icon("carlendar", 30, 30));
    }
    public ChartPanel decorateMonthlyChart(DefaultCategoryDataset dcd){
        JFreeChart jchart = ChartFactory.createAreaChart("Monthly Revenue Record", "Month", "Money", dcd, PlotOrientation.VERTICAL, true, true, rootPaneCheckingEnabled);
        jchart.getPlot().setBackgroundPaint(Color.WHITE);
        
        jchart.getPlot().setForegroundAlpha(0.8f);
        jchart.getPlot().setOutlinePaint((Color.WHITE));
        ChartPanel cp = new ChartPanel(jchart);
        cp.setSize(800, 300);
        return cp;
    }
    public void monthlyChart(){
        
        LocalDate today = LocalDate.now();
        
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        Query q = new Query();
        for(queryDTO query : q.getDoanhThuNgay(today.withDayOfMonth(1).toString(), today.withDayOfMonth(today.lengthOfMonth()).toString())){
            dcd.setValue(query.getQuantity(),"Month",query.getName());
        }
        //jPanel10.add(decorateMonthlyChart(dcd));
        //jPanel10.updateUI();
    }
    public void information(){
        Query q = new Query();
        LocalDate today = LocalDate.now();
        for(queryDTO query : q.getDoanhThu1(today.toString(), today.toString(), "day")){
            revenue1.setText(String.valueOf((query.getQuantity())/1000000)+" triệu VND");
        }
        
        int currentRevueneMonth = 0;
        for(queryDTO query : q.getDoanhThu1(today.withDayOfMonth(1).toString(),today.withDayOfMonth(today.lengthOfMonth()).toString(),"month")){
            currentRevueneMonth = query.getQuantity();
            revenue2.setText(String.valueOf((query.getQuantity())/1000000)+" triệu VND");
        }
        
        int lastRevueneMonth =0;
        LocalDate lastmonth= today.minusMonths(1);
        for(queryDTO query : q.getDoanhThu1(lastmonth.withDayOfMonth(1).toString(),lastmonth.withDayOfMonth(lastmonth.lengthOfMonth()).toString(),"month")){
            lastRevueneMonth = query.getQuantity();
        }
        System.out.println(today.minusMonths(1).toString());
        System.out.println(lastRevueneMonth);
        float t = (float)(currentRevueneMonth-lastRevueneMonth)/lastRevueneMonth;
 
        if(t>=0){
            revenue22.setForeground(Color.GREEN);
            revenue22.setText(String.valueOf(t*100)+"%");
        }
        if(t<0){
            revenue22.setForeground(Color.red);
            revenue22.setText(String.valueOf(-t*100)+"%");
        }
        int currentRevuene =0;
        for(queryDTO query : q.getDoanhThu1(today.withDayOfYear(1).toString(),today.withDayOfYear(today.lengthOfYear()).toString(),"year")){
            currentRevuene = query.getQuantity();
            revenue3.setText(String.valueOf((query.getQuantity())/1000000)+" triệu VND");
        }
        
        int currentOrder=0;
        for(queryDTO query : q.getSoLuongHoaDon(today.withDayOfMonth(1).toString(),today.withDayOfMonth(today.lengthOfMonth()).toString(),"month")){
            currentOrder = query.getQuantity();
        }
        int oldOrder=0;
        for(queryDTO query : q.getSoLuongHoaDon(lastmonth.withDayOfMonth(1).toString(),lastmonth.withDayOfMonth(lastmonth.lengthOfMonth()).toString(),"month")){
            oldOrder = query.getQuantity();
        }
        ordermonth.setText(String.valueOf(currentOrder));
        float t1 = (float)(currentOrder-oldOrder)/oldOrder;
 
        if(t>=0){
            orderlast.setForeground(Color.GREEN);
            orderlast.setText(String.valueOf(t1*100)+"%");
        }
        if(t<0){
            orderlast.setForeground(Color.red);
            orderlast.setText(String.valueOf(-t1*100)+"%");
        }
        for(queryDTO query : q.getSoLuongKhachHang()){
            customerinfo.setText(String.valueOf(query.getQuantity()));
        }
        for(queryDTO query : q.getSoLuongNhanVien()){
            staffinfo.setText(String.valueOf(query.getQuantity()));
        }
    
    }
    public String getStartDate(){
        String strDate="";
        Date date = jDateChooser1.getDate();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try{
            strDate = simpleDateFormat.format(date);
        }catch(Exception ex){
            strDate="";
        }
        
        return strDate;
    }
    public String getEndDate(){
        String strDate="";
        Date date = jDateChooser2.getDate();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try{
            strDate = simpleDateFormat.format(date);
        }catch(Exception ex){
            strDate="";
        }
        
        return strDate;
    }
    public void Chart(ArrayList<queryDTO> arr,String name,String vName,String hName){
        
        Query q= new Query();
        //ArrayList<queryDTO> query = q.getTopSanpham("2019-1-1", "2022-1-1", "DESC", 0, 5);
         ArrayList<queryDTO> query1 = arr;
         outModel1(model1, query1, vName, hName,name);
        JFreeChart barChart = ChartFactory.createBarChart(
                name,
                vName, hName,
                createDataset(query1), PlotOrientation.VERTICAL, false, false, false);
        ChartPanel cp2 = new ChartPanel(barChart);
        cp2.setSize(1200, 600);
        jPanel6.removeAll();
        jPanel6.add(cp2);
        jPanel6.updateUI();
    }
    private CategoryDataset createDataset(ArrayList<queryDTO> sql) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for(queryDTO query:sql){
        dataset.addValue(query.getQuantity(), "", query.getName());
    }
    return dataset;
    }
    
    public void RevenueChart(ArrayList<queryDTO> arr,String name,String vName,String hName){
        outModel1(model1, arr, vName, hName, name);
        //custom line 
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(4.0f));
        renderer.setSeriesStroke(2, new BasicStroke(4.0f));
        renderer.setSeriesStroke(3, new BasicStroke(4.0f));
        renderer.setBaseShapesVisible(false);
        
        ArrayList<queryDTO> query1 = arr;
        //create line 
        JFreeChart lineChartObject = ChartFactory.createLineChart(
         name,vName,
         "Tổng tiền(1E8=100.000.000 VND)",
                createDataset(arr),PlotOrientation.VERTICAL,true,true,false);
        
        //set up line and chart
        CategoryPlot plot = lineChartObject.getCategoryPlot();
        plot.setRenderer(renderer);
        plot.setOutlinePaint(null);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);
        plot.setRangeGridlinePaint(Color.white);
        
        plot.setForegroundAlpha(0.8f);
        
        //create chart panel
        ChartPanel cp2 = new ChartPanel(lineChartObject);
        cp2.setSize(1200, 600);
        
        //add chart panel into panel 
        jPanel6.removeAll();
        jPanel6.add(cp2);
        jPanel6.updateUI();
    }
    
    public ImageIcon icon(String nameFile,int width,int height){
        String path = "src\\img\\"+nameFile+".png";
        ImageIcon icon = new ImageIcon();
        icon=new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return icon;
    }
    public void topSanPham(){
        String t =(String) topComboBox.getSelectedItem();
        int top;
        if(t=="ALL"){
            top=200;
        }
        
        String a[] = t.split(" ");
        try{
             top = Integer.parseInt(a[1]);
        }catch(Exception ex){
            top=200;
        }
        Query q = new Query();
        Chart(q.getTopSanpham("DESC",0,top,getStartDate(),getEndDate()),"Sản phẩm bán chạy nhất "+getStartDate()+"-"+getEndDate(), "Mã sản phẩm", "Số lượng bán");
        
    }
    public void topLoai(){
        String t =(String) topComboBox.getSelectedItem();
        int top;
        if(t=="ALL"){
            top=200;
        }
        
        String a[] = t.split(" ");
        try{
             top = Integer.parseInt(a[1]);
        }catch(Exception ex){
            top=200;
        }
        Query q = new Query();
        Chart(q.getTopLoai("DESC",0,top,getStartDate(),getEndDate()),"Loại bán chạy nhất từ ngày "+getStartDate()+"-"+getEndDate(), "Mã loại", "Số lượng bán");
    }
    public void topKhachhang(){
        String t =(String) topComboBox.getSelectedItem();
        int top;
        if(t=="ALL"){
            top=200;
        }
        
        String a[] = t.split(" ");
        try{
             top = Integer.parseInt(a[1]);
        }catch(Exception ex){
            top=200;
        }
        Query q = new Query();
        Chart(q.getTopKhachHang("DESC",1,top,getStartDate(),getEndDate()),"Top khách hàng "+getStartDate()+"-"+getEndDate(), "Mã khách hàng", "Tổng tiền");
    }
    
    public void doanhthungay(){
        
        Query q = new Query();
        RevenueChart(q.getDoanhThuNgay(getStartDate(), getEndDate()), "Doanh thu theo ngày từ ngày "+getStartDate()+"-"+getEndDate(), "Month", "Tổng tiền");
    }
    public void doanhthutheothang(){
        
        Query q = new Query();
        RevenueChart(q.getDoanhThu(getStartDate(), getEndDate(), "month", "year"), "Doanh thu theo tháng "+getStartDate()+"-"+getEndDate(), "Month", "Tổng tiền");
    }
    public void doanhthutheonam(){
        Query q = new Query();
        RevenueChart(q.getDoanhThu(getStartDate(), getEndDate(), "year", "year"), "Tổng doanh thu quý 1 "+getStartDate()+"-"+getEndDate(), "Year", "Tổng tiền");
    }
    public void topNhanvien(){
        String t =(String) topComboBox.getSelectedItem();
        int top;
        if(t=="ALL"){
            top=200;
        }
        
        String a[] = t.split(" ");
        try{
             top = Integer.parseInt(a[1]);
        }catch(Exception ex){
            top=200;
        }
       
        Query q = new Query();
        Chart(q.getTopNhanVien("DESC",0,top,getStartDate(),getEndDate()),"Top nhân viên bán hàng tốt nhất "+getStartDate()+"-"+getEndDate(), "Mã nhân viên", "Số lượng hóa đơn");
    }
    public void quy1(){
        String t1 =(String) topComboBox1.getSelectedItem();
        int year = Integer.parseInt(t1);
        Query q = new Query();
        RevenueChart(q.getDoanhThu(year+"-1-1", year+"-3-31", "month", "year"), "Tổng doanh thu quý 1 "+year, "Month/Year", "Tổng tiền");
    }
    public void quy2(){
        String t1 =(String) topComboBox1.getSelectedItem();
        int year = Integer.parseInt(t1);
        Query q = new Query();
        RevenueChart(q.getDoanhThu(year+"-3-1", year+"-6-30", "month", "year"), "Tổng doanh thu quý 2 "+year, "Month/Year", "Tổng tiền");
    }
    public void quy3(){
        String t1 =(String) topComboBox1.getSelectedItem();
        int year = Integer.parseInt(t1);
        Query q = new Query();
        RevenueChart(q.getDoanhThu(year+"-6-1", year+"-9-30", "month", "year"), "Tổng doanh thu 3 "+year, "Month/Year", "Tổng tiền");
    }
    public void quy4(){
        String t1 =(String) topComboBox1.getSelectedItem();
        int year = Integer.parseInt(t1);
        Query q = new Query();
        RevenueChart(q.getDoanhThu(year+"-9-1", year+"-12-31", "month", "year"), "Tổng doanh thu 4 "+year, "Month/Year", "Tổng tiền");
    }
    public void year(){
        String t1 =(String) topComboBox1.getSelectedItem();
        int year = Integer.parseInt(t1);
        Query q = new Query();
        RevenueChart(q.getDoanhThu(year+"-1-1", year+"-12-31", "month", "year"), "Tổng doanh thu trong năm "+year,"Month/Year", "Tổng tiền");
    }
    public void alloftime(){
        LocalDate today = LocalDate.now();
        Query q = new Query();
        RevenueChart(q.getDoanhThu("1900-1-1", today.withDayOfYear(today.lengthOfYear()).toString(), "month", "year"), "Tổng doanh thu tất cả thời gian từ năm 2019-"+today.getYear(), "Month/Year", "Tổng tiền");
    }
    public void doanhthutheongay(){
        String t1 =(String) topComboBox1.getSelectedItem();
        int year = Integer.parseInt(t1);
        String t2 =(String) topComboBox2.getSelectedItem();
        String a[] = t2.split(" ");
        int month = Integer.parseInt(a[1]);
         Query q = new Query();
        RevenueChart(q.getDoanhThu(year+"-"+month+"-1", "+"+year+"-"+month+"-31", "day", "month"), "Tổng doanh thu tất cả thời gian", "Day/Month", "Tổng tiền");
    }
    public boolean check(){
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date();
        Date d2 = new Date();
        try {
            d1 = sdformat.parse(getStartDate());
        } catch (ParseException ex) {
            Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            d2 = sdformat.parse(getEndDate());
        } catch (ParseException ex) {
            Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(d1.compareTo(d2) > 0){
            JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu phải thấp hơn ngày kết thúc!!!");
            buttonGroup2.clearSelection();
            return false;
            
        }
        else{
            return true;
        }
        
        
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
    public void callCheck(){
        if(!check()){
            buttonGroup2.clearSelection();
            return;
        }else if(getStartDate().equals("")|| getEndDate().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn ngày");
            buttonGroup2.clearSelection();
            return;
        }
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

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        revenue1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        revenue2 = new javax.swing.JLabel();
        revenue22 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        revenue3 = new javax.swing.JLabel();
        revenue32 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        ordermonth = new javax.swing.JLabel();
        orderlast = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        customerinfo = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        staffinfo = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tablename = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        topComboBox = new javax.swing.JComboBox<>();
        topComboBox1 = new javax.swing.JComboBox<>();
        topComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new Color(237, 238, 240));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new Color(208, 210, 217));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(1720, 300));
        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 20, 20));

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridLayout(2, 3, 20, 20));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.GridLayout(2, 2));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Revenue");
        jLabel1.setBorder(new EmptyBorder(10,10,10,10));
        jPanel11.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Today");
        jLabel2.setBorder(new EmptyBorder(10,10,10,10));
        jPanel11.add(jLabel2);

        revenue1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        revenue1.setText("0");
        revenue1.setBorder(new EmptyBorder(10,10,10,10));
        revenue1.setMaximumSize(new java.awt.Dimension(11, 50));
        revenue1.setMinimumSize(new java.awt.Dimension(11, 50));
        revenue1.setPreferredSize(new java.awt.Dimension(11, 50));
        jPanel11.add(revenue1);

        jPanel9.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.GridLayout(2, 2));

        jLabel25.setBackground(new java.awt.Color(153, 153, 153));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 153, 153));
        jLabel25.setText("Revenue");
        jLabel25.setBorder(new EmptyBorder(10,10,10,10));
        jPanel12.add(jLabel25);

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 204, 204));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Since last month");
        jLabel26.setBorder(new EmptyBorder(10,10,10,10));
        jPanel12.add(jLabel26);

        revenue2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        revenue2.setText("0");
        revenue2.setBorder(new EmptyBorder(10,10,10,10));
        jPanel12.add(revenue2);

        revenue22.setForeground(new Color(18, 208, 154));
        revenue22.setText("Up 30%");
        revenue22.setBorder(new EmptyBorder(10,10,10,10));
        jPanel12.add(revenue22);

        jPanel9.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.GridLayout(2, 2));

        jLabel29.setBackground(new java.awt.Color(153, 153, 153));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 153, 153));
        jLabel29.setText("Revenue");
        jLabel29.setBorder(new EmptyBorder(10,10,10,10));
        jPanel13.add(jLabel29);

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(204, 204, 204));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("This year");
        jLabel30.setBorder(new EmptyBorder(10,10,10,10));
        jPanel13.add(jLabel30);

        revenue3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        revenue3.setText("0");
        revenue3.setBorder(new EmptyBorder(10,10,10,10));
        jPanel13.add(revenue3);

        revenue32.setForeground(new Color(18, 208, 154));
        revenue32.setBorder(new EmptyBorder(10,10,10,10));
        jPanel13.add(revenue32);

        jPanel9.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.GridLayout(2, 2));

        jLabel33.setBackground(new java.awt.Color(153, 153, 153));
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 153, 153));
        jLabel33.setText("Order");
        jLabel33.setBorder(new EmptyBorder(10,10,10,10));
        jPanel14.add(jLabel33);

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(204, 204, 204));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Since last month");
        jLabel34.setBorder(new EmptyBorder(10,10,10,10));
        jPanel14.add(jLabel34);

        ordermonth.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ordermonth.setText("0");
        ordermonth.setBorder(new EmptyBorder(10,10,10,10));
        jPanel14.add(ordermonth);

        orderlast.setForeground(new Color(18, 208, 154));
        orderlast.setBorder(new EmptyBorder(10,10,10,10));
        jPanel14.add(orderlast);

        jPanel9.add(jPanel14);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new java.awt.GridLayout(2, 2));

        jLabel37.setBackground(new java.awt.Color(153, 153, 153));
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("Customer");
        jLabel37.setBorder(new EmptyBorder(10,10,10,10));
        jPanel20.add(jLabel37);

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 204, 204));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setBorder(new EmptyBorder(10,10,10,10));
        jPanel20.add(jLabel38);

        customerinfo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        customerinfo.setText("0");
        customerinfo.setBorder(new EmptyBorder(10,10,10,10));
        jPanel20.add(customerinfo);

        jPanel9.add(jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new java.awt.GridLayout(2, 2));

        jLabel41.setBackground(new java.awt.Color(153, 153, 153));
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(153, 153, 153));
        jLabel41.setText("Staff");
        jLabel41.setBorder(new EmptyBorder(10,10,10,10));
        jPanel21.add(jLabel41);

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 13)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 204, 204));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setBorder(new EmptyBorder(10,10,10,10));
        jPanel21.add(jLabel42);

        staffinfo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        staffinfo.setText("0");
        staffinfo.setBorder(new EmptyBorder(10,10,10,10));
        jPanel21.add(staffinfo);

        jLabel44.setForeground(new Color(18, 208, 154));
        jLabel44.setBorder(new EmptyBorder(10,10,10,10));
        jPanel21.add(jLabel44);

        jPanel9.add(jPanel21);

        jPanel3.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setOpaque(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        tablename.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tablename.setForeground(new java.awt.Color(51, 51, 51));
        tablename.setText("Bảng thống kê");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
            .addComponent(tablename, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(tablename, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        jPanel2.add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(1720, 600));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 600));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jPanel6, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(500, 600));

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jDateChooser1.setForeground(new java.awt.Color(153, 153, 153));
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(4, 2, 20, 20));

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton1.setText("Loại");
        jRadioButton1.setOpaque(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1);

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton2.setText("Sản phẩm bán chạy");
        jRadioButton2.setOpaque(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2);

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton3.setText("Khách hàng");
        jRadioButton3.setOpaque(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton3);

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton4.setText("Nhân viên");
        jRadioButton4.setOpaque(false);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton4);

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton5.setText("Doanh thu theo ngày");
        jRadioButton5.setOpaque(false);
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton5);

        buttonGroup2.add(jRadioButton12);
        jRadioButton12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton12.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton12.setText("Doanh thu theo tháng");
        jRadioButton12.setOpaque(false);
        jRadioButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton12);

        buttonGroup2.add(jRadioButton13);
        jRadioButton13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton13.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton13.setText("Doanh thu theo năm");
        jRadioButton13.setOpaque(false);
        jRadioButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton13);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(3, 2, 20, 20));

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton6.setText("Quý 1");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton6);

        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton7.setText("Quý 2");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton7);

        buttonGroup2.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton8.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton8.setText("Quý 3");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton8);

        buttonGroup2.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton9.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton9.setText("Quý 4");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton9);

        buttonGroup2.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton10.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton10.setText("Trong năm");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton10);

        buttonGroup2.add(jRadioButton11);
        jRadioButton11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRadioButton11.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton11.setText("All of time");
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioButton11);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Thống kê phân loại ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Tìm kiếm doanh thu theo tháng");

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jDateChooser2.setForeground(new java.awt.Color(153, 153, 153));
        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        topComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        topComboBox.setForeground(new java.awt.Color(102, 102, 102));
        topComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TOP 1", "TOP 5", "TOP 10", "ALL", " " }));
        topComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topComboBoxActionPerformed(evt);
            }
        });

        topComboBox1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        topComboBox1.setForeground(new java.awt.Color(102, 102, 102));
        topComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2020", "2021", "2022", " " }));

        topComboBox2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        topComboBox2.setForeground(new java.awt.Color(102, 102, 102));
        topComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", " " }));
        topComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topComboBox2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Tìm kiếm doanh thu theo ngày");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(topComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 233, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(topComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(topComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 101, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(topComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel4.add(jPanel7, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jPanel2.add(jPanel4, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2710, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        callCheck();
        topLoai();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        callCheck();
        topSanPham();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        callCheck();
        topKhachhang();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        callCheck();
        topNhanvien();
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
        quy1();
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
        quy2();
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
        quy3();
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
        quy4();
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
        year();
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
        alloftime();
    }//GEN-LAST:event_jRadioButton11ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
        doanhthungay();
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void topComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_topComboBoxActionPerformed

    private void topComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topComboBox2ActionPerformed
        // TODO add your handling code here:
        callCheck();
        doanhthutheongay();
    }//GEN-LAST:event_topComboBox2ActionPerformed

    private void jRadioButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton12ActionPerformed
        // TODO add your handling code here:
        callCheck();
        doanhthutheothang();
    }//GEN-LAST:event_jRadioButton12ActionPerformed

    private void jRadioButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton13ActionPerformed
        // TODO add your handling code here:
        callCheck();
        doanhthutheonam();
    }//GEN-LAST:event_jRadioButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel customerinfo;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel orderlast;
    private javax.swing.JLabel ordermonth;
    private javax.swing.JLabel revenue1;
    private javax.swing.JLabel revenue2;
    private javax.swing.JLabel revenue22;
    private javax.swing.JLabel revenue3;
    private javax.swing.JLabel revenue32;
    private javax.swing.JLabel staffinfo;
    private javax.swing.JLabel tablename;
    private javax.swing.JComboBox<String> topComboBox;
    private javax.swing.JComboBox<String> topComboBox1;
    private javax.swing.JComboBox<String> topComboBox2;
    // End of variables declaration//GEN-END:variables
}
