package GUI;

import CLASS.ChiTietSDDichVu;
import CLASS.ChiTietSDThietBi;
import CLASS.HDDatPhong;
import CLASS.PhongDatNhieuTheoQuy;
import CLASS.PhongDatNhieuTheoThang;
import DAO.CTSuDungDichVuDAO;
import DAO.CTSuDungThietBiDAO;
import DAO.DatPhongViewDAO;
import java.util.ArrayList;
import java.util.Vector;
import DAO.HoaDonDatPhongDAO;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLDocument;

public class HoaDonDatPhong extends javax.swing.JFrame {

    //Vector cho bảng hóa đơn đặt phòng
    Vector title = new Vector();
    Vector data = new Vector();
    ArrayList<HDDatPhong> ds = HoaDonDatPhongDAO.getDataDatPhong();

    //Vector cho bảng danh sách phòng được đặt nhiều hơn 1 lần theo tháng
    Vector title1 = new Vector();
    Vector data1 = new Vector();
    ArrayList<PhongDatNhieuTheoThang> ds1 = HoaDonDatPhongDAO.setDSDatPhongNhieuHonTheoThang();

    //Vector cho bảng danh sách phòng được đặt nhiều hơn 1 lần theo quý
    Vector title2 = new Vector();
    Vector data2 = new Vector();
    ArrayList<PhongDatNhieuTheoQuy> ds2 = HoaDonDatPhongDAO.setDSDatPhongNhieuHonTheoQuy();

    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>();

    public HoaDonDatPhong() {
        initComponents();
        lblTenNhanVien.setText(DangNhap.fullname);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(DangNhap.fileimage));
        lblChucVuNV.setText("(Quản lý)");
        addTitle_BangHoaDon();
        loadData_BangHoaDon(ds);
        designTable();
        searchInfoInTable();
        addTitle_BangDSTheoThang();
        loadData_BangDSTheoThang(ds1);
        addTitle_BangDSTheoQuy();
        loadData_BangDSTheoQuy(ds2);

        ActionListener updateTimerAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String datenow = currentDate.format(formatter);
                String dayOfWeek = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                switch (dayOfWeek) {
                    case "Monday" ->
                        lblDateNow.setText("Thứ hai" + ", " + datenow + " " + hour + ":" + minute + ":" + second);
                    case "Tuesday" ->
                        lblDateNow.setText("Thứ ba" + ", " + datenow + " " + hour + ":" + minute + ":" + second);
                    case "Wednesday" ->
                        lblDateNow.setText("Thứ tư" + ", " + datenow + " " + hour + ":" + minute + ":" + second);
                    case "Thursday" ->
                        lblDateNow.setText("Thứ năm" + ", " + datenow + " " + hour + ":" + minute + ":" + second);
                    case "Friday" ->
                        lblDateNow.setText("Thứ sáu" + ", " + datenow + " " + hour + ":" + minute + ":" + second);
                    case "Saturday" ->
                        lblDateNow.setText("Thứ bảy" + ", " + datenow + " " + hour + ":" + minute + ":" + second);
                    case "Sunday" ->
                        lblDateNow.setText("Chủ nhật" + ", " + datenow + " " + hour + ":" + minute + ":" + second);
                }
            }
        };
        Timer timer = new Timer(1000, updateTimerAction);
        timer.start();

        cboThongKe.setSelectedIndex(0);
    }

    private void searchInfoInTable() {
        rowSorter = new TableRowSorter<>(tblDatPhong.getModel());
        tblDatPhong.setRowSorter(rowSorter);
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiem.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                    jLabel4.setText("Số kết quả tìm kiếm: 0");
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    int rowCount = rowSorter.getViewRowCount();
                    jLabel4.setText("Số kết quả tìm kiếm: " + String.valueOf(rowCount));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiem.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                    jLabel4.setText("Số kết quả tìm kiếm: 0");
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    int rowCount = rowSorter.getViewRowCount();
                    jLabel4.setText("Số kết quả tìm kiếm: " + String.valueOf(rowCount));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    private void designTable() {
        tblDatPhong.getColumnModel().getColumn(0).setPreferredWidth(45);
        tblDatPhong.getColumnModel().getColumn(1).setPreferredWidth(45);
        tblDatPhong.getColumnModel().getColumn(2).setPreferredWidth(55);
        tblDatPhong.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblDatPhong.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblDatPhong.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblDatPhong.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblDatPhong.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblDatPhong.getColumnModel().getColumn(8).setPreferredWidth(80);
        tblDatPhong.getColumnModel().getColumn(9).setPreferredWidth(50);
        tblDatPhong.getColumnModel().getColumn(10).setPreferredWidth(35);
        tblDatPhong.getColumnModel().getColumn(11).setPreferredWidth(50);
        tblDatPhong.getColumnModel().getColumn(12).setPreferredWidth(50);

        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.RIGHT);
        tblDatPhong.getColumnModel().getColumn(6).setCellRenderer(right);
        tblDatPhong.getColumnModel().getColumn(7).setCellRenderer(right);
        tblDatPhong.getColumnModel().getColumn(8).setCellRenderer(right);
        tblDatPhong.getColumnModel().getColumn(9).setCellRenderer(right);
        tblDatPhong.getColumnModel().getColumn(10).setCellRenderer(right);

    }

    private void addTitle_BangHoaDon() {
        title.add("ID Hoá Đơn");
        title.add("ID Nhân Viên");
        title.add("ID Khách Hàng");
        title.add("ID Phòng");
        title.add("Check-In");
        title.add("Check-Out");
        title.add("Tiền Đặt Cọc");
        title.add("Phụ Thu Check-in");
        title.add("Phụ Thu Check-out");
        title.add("Tổng Tiền");
        title.add("Số Người Ở");
        title.add("Mô Hình Thuê");
        title.add("Trạng Thái HĐ");
    }

    private void loadData_BangHoaDon(ArrayList<HDDatPhong> ds) {
        data.removeAllElements();
        for (HDDatPhong dp : ds) {
            Vector v = new Vector();
            v.add(dp.getMahd());
            v.add(dp.getManv());
            v.add(dp.getMakh());
            v.add(dp.getMaphong());
            v.add(dp.getCheckin());
            v.add(dp.getCheckout());
            v.add(dp.getDatcoc());
            v.add(dp.getPhuthu1());
            v.add(dp.getPhuthu2());
            v.add(dp.getTongtien());
            v.add(dp.getSonguoi());
            v.add(dp.getLoai());
            v.add(dp.getTrangthai());
            data.add(v);
        }
        tblDatPhong.setModel(new DefaultTableModel(data, title));
    }

    private void addTitle_BangDSTheoThang() {
        title1.add("ID Phòng");
        title1.add("Số lần đặt");
    }

    private void loadData_BangDSTheoThang(ArrayList<PhongDatNhieuTheoThang> ds) {
        data1.removeAllElements();
        for (PhongDatNhieuTheoThang dp : ds) {
            Vector v = new Vector();
            v.add(dp.getId());
            v.add(dp.getSolan());
            data1.add(v);
        }
        jTable1.setModel(new DefaultTableModel(data1, title1));
    }

    private void addTitle_BangDSTheoQuy() {
        title2.add("ID Phòng");
        title2.add("Số lần đặt");
    }

    private void loadData_BangDSTheoQuy(ArrayList<PhongDatNhieuTheoQuy> ds) {
        data2.removeAllElements();
        for (PhongDatNhieuTheoQuy dp : ds) {
            Vector v = new Vector();
            v.add(dp.getId());
            v.add(dp.getSolan());
            data2.add(v);
        }
        jTable2.setModel(new DefaultTableModel(data2, title2));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatPhong = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        imageAvatar1 = new CLASS.ImageAvatar();
        lblTenNhanVien = new javax.swing.JLabel();
        lblChucVuNV = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        cboThongKe = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        lblDateNow = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDatPhong.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDatPhong.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblDatPhong);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 151, 1455, 230));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("THỐNG KÊ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 36, 162, 37));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("HÓA ĐƠN ĐẶT PHÒNG");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 73, -1, 52));
        jPanel1.add(imageAvatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 24, 100, 100));

        lblTenNhanVien.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jPanel1.add(lblTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 45, 165, 28));

        lblChucVuNV.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jPanel1.add(lblChucVuNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 73, 165, 30));

        jPanel2.setBackground(new java.awt.Color(0, 64, 93));
        jPanel2.setPreferredSize(new java.awt.Dimension(5, 100));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 24, 8, 109));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 90, 208, 29));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setText("Xem Hóa Đơn In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 90, 150, 30));

        cboThongKe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn:", "Theo tháng", "Theo quý" }));
        cboThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThongKeActionPerformed(evt);
            }
        });
        jPanel1.add(cboThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 120, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tìm kiếm chính xác thông tin hóa đơn ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 400, 310, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Load Lại Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, 160, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Loc danh sách theo:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 51, 146, 31));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn tháng thống kê", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 51, 333, 31));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel7.setText("Thống kê hóa đơn theo ngày check-in");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 11, 485, -1));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 35, 485, 10));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("*****Tổng số hóa đơn có trong ");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 94, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Tháng ?");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 94, 72, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("*****Tổng doanh thu trong ");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 128, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Tháng ?");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 128, 72, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("0 đơn");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 94, 186, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("0.0 VNĐ");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 128, 213, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText(":");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 94, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText(":");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 128, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 202, 485, 85));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("*****Danh sách số phòng đặt nhiều hơn 1 lần trong ");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 162, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setText("Tháng ?");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 162, 72, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 520, 310));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Loc danh sách theo:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 51, 146, 31));

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn quý thống kê", "Quý 1", "Quý 2", "Quý 3", "Quý 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 51, 333, 31));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel19.setText("Thống kê hóa đơn theo ngày check-in");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 11, 485, -1));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 35, 485, 10));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("*****Tổng số hóa đơn có trong ");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 94, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setText("Quý ?");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 94, 72, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("*****Tổng doanh thu trong ");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 128, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel23.setText("Quý ?");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 128, 72, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("0 đơn");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 94, 186, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
        jLabel25.setText("0.0 VNĐ");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 128, 213, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setText(":");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 94, -1, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel27.setText(":");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 128, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 202, 485, 85));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setText("*****Danh sách số phòng đặt nhiều hơn 1 lần trong ");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 162, -1, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel29.setText("Quý ?");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 162, 72, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 520, 310));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Thống kê hóa đơn theo:");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 400, 200, 30));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 35, 485, 10));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel31.setText("Tìm kiếm theo mã HĐ, tên KH, sđt KH");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 370, -1));

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn cách tìm:", "Tìm kiếm mã hóa đơn", "Tìm kiếm tên khách hàng", "Tìm kiếm sđt khách hàng" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 362, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel32.setText("Tìm kiếm mã HĐ");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 160, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setEnabled(false);
        jPanel5.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 261, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Tìm Kiếm");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 92, 26));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel33.setText("Tìm kiếm tên KH");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 230, -1));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setEnabled(false);
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 261, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Tìm Kiếm");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 92, 26));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel34.setText("Tìm kiếm sđt KH");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 210, -1));

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField3.setEnabled(false);
        jPanel5.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 261, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("Tìm Kiếm");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 92, 26));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 390, 310));

        jPanel6.setBackground(new java.awt.Color(0, 64, 93));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Xem trước thông tin khi in Hóa Đơn");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 400, 480, 30));

        jPanel7.setBackground(new java.awt.Color(0, 64, 93));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 220, -1));

        jPanel8.setBackground(new java.awt.Color(0, 64, 93));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 390, 30));

        jScrollPane4.setViewportView(jTextPane1);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 440, 480, 310));

        jPanel9.setBackground(new java.awt.Color(0, 64, 93));
        jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Thời gian hiện tại:");

        lblDateNow.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblDateNow.setForeground(new java.awt.Color(255, 255, 255));
        lblDateNow.setText("DD/MM/YYYY");

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/calendar.png"))); // NOI18N

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cancel.png"))); // NOI18N
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4))
                .addGap(18, 18, 18)
                .addComponent(lblDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel38)
                                .addComponent(lblDateNow))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 850, 70));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton6.setText("In Hóa Đơn");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 90, 140, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Mục tìm kiếm nhanh:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, 150, 30));

        jLabel4.setText("Số kết quả tìm kiếm: 0");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 126, 210, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1524, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for (double i = 0.0; i <= 1.0; i += 0.1) {
            String val = i + "";
            float f = Float.parseFloat(val);
            this.setOpacity(f);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void cboThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThongKeActionPerformed
        switch (cboThongKe.getSelectedIndex()) {
            case 1 -> {
                jPanel3.setVisible(true);
                jPanel4.setVisible(false);
            }
            case 2 -> {
                jPanel3.setVisible(false);
                jPanel4.setVisible(true);
            }
            case 0 -> {
                jPanel3.setVisible(false);
                jPanel4.setVisible(false);
            }
        }
    }//GEN-LAST:event_cboThongKeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ds = HoaDonDatPhongDAO.getDataDatPhong();
        loadData_BangHoaDon(ds);
        designTable();  // ds: bảng hóa đơn đặt phòng
        //--------------------------------------------------
        jLabel12.setText("0 đơn");
        jLabel13.setText("0.0 VNĐ");
        jComboBox2.setSelectedIndex(0);
        ds1 = HoaDonDatPhongDAO.setDSDatPhongNhieuHonTheoThang(); // ds1: bảng DS theo tháng
        loadData_BangDSTheoThang(ds1);
        //--------------------------------------------------
        jLabel24.setText("0 đơn");
        jLabel25.setText("0.0 VNĐ");
        jComboBox3.setSelectedIndex(0);
        ds2 = HoaDonDatPhongDAO.setDSDatPhongNhieuHonTheoQuy();  //  ds2: bảng DS theo quý
        loadData_BangDSTheoQuy(ds2);
        jTextPane1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String str = (String) jComboBox2.getSelectedItem();
        String month = str.substring(str.indexOf(" ") + 1);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        switch (jComboBox2.getSelectedIndex()) {
            case 0 -> {
                jLabel9.setText("Tháng ?");
                jLabel11.setText("Tháng ?");
                jLabel17.setText("Tháng ?");
                jLabel12.setText("0 đơn");
                jLabel13.setText("0.0 VNĐ");
                ds = HoaDonDatPhongDAO.getDataDatPhong();
                loadData_BangHoaDon(ds);
                designTable();
                jComboBox2.setSelectedIndex(0);
                ds1 = HoaDonDatPhongDAO.setDSDatPhongNhieuHonTheoThang();
                loadData_BangDSTheoThang(ds1);
            }
            case 1 -> {
                jLabel9.setText("Tháng 1");
                jLabel11.setText("Tháng 1");
                jLabel17.setText("Tháng 1");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 2 -> {
                jLabel9.setText("Tháng 2");
                jLabel11.setText("Tháng 2");
                jLabel17.setText("Tháng 2");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 3 -> {
                jLabel9.setText("Tháng 3");
                jLabel11.setText("Tháng 3");
                jLabel17.setText("Tháng 3");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 4 -> {
                jLabel9.setText("Tháng 4");
                jLabel11.setText("Tháng 4");
                jLabel17.setText("Tháng 4");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 5 -> {
                jLabel9.setText("Tháng 5");
                jLabel11.setText("Tháng 5");
                jLabel17.setText("Tháng 5");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 6 -> {
                jLabel9.setText("Tháng 6");
                jLabel11.setText("Tháng 6");
                jLabel17.setText("Tháng 6");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 7 -> {
                jLabel9.setText("Tháng 7");
                jLabel11.setText("Tháng 7");
                jLabel17.setText("Tháng 7");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 8 -> {
                jLabel9.setText("Tháng 8");
                jLabel11.setText("Tháng 8");
                jLabel17.setText("Tháng 8");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 9 -> {
                jLabel9.setText("Tháng 9");
                jLabel11.setText("Tháng 9");
                jLabel17.setText("Tháng 9");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 10 -> {
                jLabel9.setText("Tháng 10");
                jLabel11.setText("Tháng 10");
                jLabel17.setText("Tháng 10");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 11 -> {
                jLabel9.setText("Tháng 11");
                jLabel11.setText("Tháng 11");
                jLabel17.setText("Tháng 11");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
            case 12 -> {
                jLabel9.setText("Tháng 12");
                jLabel11.setText("Tháng 12");
                jLabel17.setText("Tháng 12");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoThang(month);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel12.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoThang(month)) + " đơn");
                String tongtien = decimalFormat.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoThang(month));
                jLabel13.setText(tongtien + " VNĐ");
                ds1 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoThang(month);
                loadData_BangDSTheoThang(ds1);
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String str = (String) jComboBox3.getSelectedItem();
        String quy = str.substring(str.length() - 1);
        DecimalFormat df = new DecimalFormat("###,###");
        switch (jComboBox3.getSelectedIndex()) {
            case 0 -> {
                jLabel21.setText("Quý ?");
                jLabel23.setText("Quý ?");
                jLabel29.setText("Quý ?");
                ds = HoaDonDatPhongDAO.getDataDatPhong();
                loadData_BangHoaDon(ds);
                designTable();
                jComboBox3.setSelectedIndex(0);
                jLabel24.setText("0 đơn");
                ds2 = HoaDonDatPhongDAO.setDSDatPhongNhieuHonTheoQuy();
                loadData_BangDSTheoQuy(ds2);
            }
            case 1 -> {
                jLabel21.setText("Quý 1");
                jLabel23.setText("Quý 1");
                jLabel29.setText("Quý 1");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoQuy(quy);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel24.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoQuy(quy)) + " đơn");
                String tongtien = df.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoQuy(quy));
                jLabel25.setText(tongtien + " VNĐ");
                ds2 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoQuy(quy);
                loadData_BangDSTheoQuy(ds2);
            }
            case 2 -> {
                jLabel21.setText("Quý 2");
                jLabel23.setText("Quý 2");
                jLabel29.setText("Quý 2");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoQuy(quy);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel24.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoQuy(quy)) + " đơn");
                String tongtien = df.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoQuy(quy));
                jLabel25.setText(tongtien + " VNĐ");
                ds2 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoQuy(quy);
                loadData_BangDSTheoQuy(ds2);
            }
            case 3 -> {
                jLabel21.setText("Quý 3");
                jLabel23.setText("Quý 3");
                jLabel29.setText("Quý 3");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoQuy(quy);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel24.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoQuy(quy)) + " đơn");
                String tongtien = df.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoQuy(quy));
                jLabel25.setText(tongtien + " VNĐ");
                ds2 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoQuy(quy);
                loadData_BangDSTheoQuy(ds2);
            }
            case 4 -> {
                jLabel21.setText("Quý 4");
                jLabel23.setText("Quý 4");
                jLabel29.setText("Quý 4");
                ds = HoaDonDatPhongDAO.getDSDatPhong_TheoQuy(quy);
                loadData_BangHoaDon(ds);
                designTable();
                jLabel24.setText(String.valueOf(HoaDonDatPhongDAO.countHoaDonTheoQuy(quy)) + " đơn");
                String tongtien = df.format(HoaDonDatPhongDAO.sumTongTienHoaDonTheoQuy(quy));
                jLabel25.setText(tongtien + " VNĐ");
                ds2 = HoaDonDatPhongDAO.getDSDatPhongNhieuHon1_TheoQuy(quy);
                loadData_BangDSTheoQuy(ds2);
            }

        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        switch (jComboBox4.getSelectedIndex()) {
            case 0 -> {
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jButton3.setEnabled(false);
                jButton4.setEnabled(false);
                jButton5.setEnabled(false);
            }
            case 1 -> {
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jButton3.setEnabled(true);
                jButton4.setEnabled(false);
                jButton5.setEnabled(false);
            }
            case 2 -> {
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(false);
                jButton3.setEnabled(false);
                jButton4.setEnabled(true);
                jButton5.setEnabled(false);
            }
            case 3 -> {
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(true);
                jButton3.setEnabled(false);
                jButton4.setEnabled(false);
                jButton5.setEnabled(true);
            }
            default ->
                throw new AssertionError();
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTextField1.getText().isEmpty()) {
            ds = HoaDonDatPhongDAO.getDataDatPhong();
            loadData_BangHoaDon(ds);
            designTable();
        } else {
            ds = HoaDonDatPhongDAO.search1(jTextField1.getText().trim());
            loadData_BangHoaDon(ds);
            designTable();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jTextField2.getText().isEmpty()) {
            ds = HoaDonDatPhongDAO.getDataDatPhong();
            loadData_BangHoaDon(ds);
            designTable();
        } else {
            ds = HoaDonDatPhongDAO.search2(jTextField2.getText().trim());
            loadData_BangHoaDon(ds);
            designTable();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTextField3.getText().isEmpty()) {
            ds = HoaDonDatPhongDAO.getDataDatPhong();
            loadData_BangHoaDon(ds);
            designTable();
        } else {
            ds = HoaDonDatPhongDAO.search3(jTextField3.getText().trim());
            loadData_BangHoaDon(ds);
            designTable();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        Main frm = new Main();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel35MouseClicked

    ArrayList<ChiTietSDDichVu> dsDv = CTSuDungDichVuDAO.getCTSDDichVuTheoMaHD();
    ArrayList<ChiTietSDThietBi> dsTb = CTSuDungThietBiDAO.getCTSDThietBiTheoMaHD();

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        df.applyPattern("#,###");
        jTextPane1.setContentType("text/html");
        HTMLDocument htmlDocument = (HTMLDocument) jTextPane1.getDocument();
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();

        if (jComboBox2.getSelectedIndex() == 0) {
            jTextPane1.setText("");
        }

        String htmlContent = "<html>\n"
                + "<head>\n"
                + "    <title>Hotel Invoice Template</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-header {\n"
                + "            text-align: center;\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-details {\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-details p {\n"
                + "            margin: 5px;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-items {\n"
                + "            width: 100%;\n"
                + "            border-collapse: collapse;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-items th,\n"
                + "        .invoice-items td {\n"
                + "            text-align: left;\n"
                + "            border-bottom: 1px solid #000;\n"
                + "            vertical-align: middle;\n"
                + "            font-size: xx-small;\n"
                + "        }\n"
                + "\n"
                + "        .total-amount {\n"
                + "            text-align: right;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"invoice-header\">\n"
                + "        <h4 style=\"text-align: right;\">(Tuấn Anh - Hoàng - Hào - Quản lý khách sạn)</h4>\n"
                + "		<h4>Khách sạn The Vintage (The Vintage Inn)</h4>\n"
                + "		<h4 style=\"margin-top: -20px;\">Địa chỉ: 482 Đường Phú Thọ Hòa, Phú Thọ Hoà, Tân Phú, Thành phố Hồ Chí Minh</h4>\n"
                + "		<h4 style=\"margin-top: -20px;\">SĐT: 0123456789</h4>\n"
                + "		<hr style=\"margin-top: -10px;\">\n"
                + "		<h1 style=\"text-align: center;\">************* THỐNG KÊ HÓA ĐƠN *************</h1>\n"
                + "    </div>\n"
                + "	<table>\n"
                + "		<tbody>\n"
                + "			<tr>\n"
                + "				<td>Thời gian hiện tại:</td>\n"
                + "				<td>&nbsp &nbsp &nbsp " + lblDateNow.getText() + "</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Tên nhân viên lập thống kê: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp " + lblTenNhanVien.getText() + "</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Chức vụ: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp " + lblChucVuNV.getText() + "</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Chi tiết thống kê: </td>\n"
                + "				<td></td>\n"
                + "			</tr>\n"
                + "		</tbody>\n"
                + "	</table>\n"
                + "	<br>\n"
                + "    <table class=\"invoice-items\">\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Mã HĐ</th>\n"
                + "                <th>Mã NV</th>\n"
                + "                <th>Mã KH</th>\n"
                + "                <th>Mã Phòng</th>\n"
                + "                <th>Thành tiền</th>\n"
                + "                <th>Check-in</th>\n"
                + "                <th>Check-out</th>\n"
                + "                <th>Tiền cọc</th>\n"
                + "                <th>Phụ Thu 1:</th>\n"
                + "                <th>Phụ Thu 2:</th>\n"
                + "                <th>Số Người Ở</th>\n"
                + "                <th>Mô Hình Thuê</th>\n"
                + "                <th>Trạng Thái HĐ</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";

        for (HDDatPhong dsct : ds) {
            htmlContent += "            <tr>\n"
                    + "                <td>" + dsct.getMahd().trim() + "</td>\n"
                    + "                <td>" + dsct.getManv().trim() + "</td>\n"
                    + "                <td>" + dsct.getMakh().trim() + "</td>\n"
                    + "                <td>" + dsct.getMaphong().trim() + "</td>\n"
                    + "                <td>" + dsct.getCheckin().trim() + "</td>\n"
                    + "                <td>" + dsct.getCheckout().trim() + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getDatcoc()) + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getPhuthu1()) + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getPhuthu1()) + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getTongtien()) + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getSonguoi()) + "</td>\n"
                    + "                <td>" + dsct.getLoai() + "</td>\n"
                    + "                <td>" + dsct.getTrangthai() + "</td>\n"
                    + "            </tr>\n";
        }

        if (cboThongKe.getSelectedIndex() == 1) {
            htmlContent += " </table>\n"
                    + "<br><br><strong>Tổng hóa đơn có trong <b>" + (String) jComboBox2.getSelectedItem() + "</b> là: <b>" + jLabel12.getText() + "</b></strong>"
                    + "<br><br><strong>Tổng doanh thu trong <b>" + (String) jComboBox2.getSelectedItem() + "</b> là: <b>" + jLabel13.getText() + "</b></strong>"
                    + "<br><br><strong>Danh sách các phòng được đặt nhiều lần: </strong>"
                    + "    <table class=\"invoice-items\">\n"
                    + "        <thead>\n"
                    + "            <tr>\n"
                    + "                <th>Mã Phòng</th>\n"
                    + "                <th>Số lần đặt</th>\n"
                    + "            </tr>\n"
                    + "        </thead>\n"
                    + "        <tbody>\n";

            for (PhongDatNhieuTheoThang p : ds1) {
                htmlContent += "            <tr>\n"
                        + "                <td>" + p.getId() + "</td>\n"
                        + "                <td>" + p.getSolan() + "</td>\n"
                        + "            </tr>\n";

            }
            htmlContent += "</tbody>\n"
                    + " </table>\n"
                    + "<br><br><br><br>"
                    + "<div style=\"text-align: right;\">\n"
                    + "        <i style=\"text-align: right;\">TP.HCM, ngày " + day + " tháng " + month + " năm " + year + "</i>\n"
                    + "        <p>Ký tên</p><br><br><br><br><br><br>\n"
                    + "    </div>"
                    + "</body>\n"
                    + "</html>";
        } else if (cboThongKe.getSelectedIndex() == 0) {
            htmlContent += " </table>\n"
                    + "<br><br><strong>Tổng hóa đơn hiện đang có là: " + HoaDonDatPhongDAO.countHoaDon() + " đơn</strong>"
                    + "<br><br><strong>Tổng doanh thu hóa đơn đã thanh toán: " + df.format(HoaDonDatPhongDAO.sumTongTienHoaDonDaThanhToan()) + " VNĐ</strong>"
                    + "<br><br><strong>Tổng doanh thu hóa đơn chưa thanh toán: " + df.format(HoaDonDatPhongDAO.sumTongTienHoaDonChuaThanhToan()) + " VNĐ</strong>"
                    + "<br><br><br><br>"
                    + "<div style=\"text-align: right;\">\n"
                    + "        <i style=\"text-align: right;\">TP.HCM, ngày " + day + " tháng " + month + " năm " + year + "</i>\n"
                    + "        <p>Ký tên</p><br><br><br><br><br><br>\n"
                    + "    </div>"
                    + "</body>\n"
                    + "</html>";
        } else if (cboThongKe.getSelectedIndex() == 2) {
            htmlContent += " </table>\n"
                    + "<br><br><strong>Tổng hóa đơn có trong <b>" + (String) jComboBox3.getSelectedItem() + "</b> là: <b>" + jLabel24.getText() + "</b></strong>"
                    + "<br><br><strong>Tổng doanh thu trong <b>" + (String) jComboBox3.getSelectedItem() + "</b> là: <b>" + jLabel25.getText() + "</b></strong>"
                    + "<br><br><strong>Danh sách các phòng được đặt nhiều lần: </strong>"
                    + "    <table class=\"invoice-items\">\n"
                    + "        <thead>\n"
                    + "            <tr>\n"
                    + "                <th>Mã Phòng</th>\n"
                    + "                <th>Số lần đặt</th>\n"
                    + "            </tr>\n"
                    + "        </thead>\n"
                    + "        <tbody>\n";

            for (PhongDatNhieuTheoQuy p : ds2) {
                htmlContent += "            <tr>\n"
                        + "                <td>" + p.getId() + "</td>\n"
                        + "                <td>" + p.getSolan() + "</td>\n"
                        + "            </tr>\n";

            }
            htmlContent += "</tbody>\n"
                    + " </table>\n"
                    + "<br><br><br><br><br><br><br>"
                    + "<div style=\"text-align: right;\">\n"
                    + "        <i style=\"text-align: right;\">TP.HCM, ngày " + day + " tháng " + month + " năm " + year + "</i>\n"
                    + "        <p>Ký tên</p><br><br><br><br><br><br>\n"
                    + "    </div>"
                    + "</body>\n"
                    + "</html>";
        }

        try {
            jTextPane1.setDocument(htmlDocument);
            htmlDocument.setInnerHTML(htmlDocument.getRootElements()[0], htmlContent);
            int width = (int) (210 * 0.39 * Toolkit.getDefaultToolkit().getScreenResolution());
            int height = (int) (297 * 0.39 * Toolkit.getDefaultToolkit().getScreenResolution());
            jTextPane1.setPreferredSize(new Dimension(width, height));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            jTextPane1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDonDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonDatPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboThongKe;
    private CLASS.ImageAvatar imageAvatar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblChucVuNV;
    private javax.swing.JLabel lblDateNow;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JTable tblDatPhong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
