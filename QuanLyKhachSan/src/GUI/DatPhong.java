package GUI;

import CLASS.ChiTietSDDichVu;
import CLASS.ChiTietSDThietBi;
import CLASS.DatPhongView;
import CLASS.DichVu;
import CLASS.ThietBi;
import DAO.ConnectionDB;
import DAO.DatPhongViewDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class DatPhong extends javax.swing.JFrame {

    Vector dataHoaDon = new Vector();
    Vector titleHoaDon = new Vector();
    ArrayList<DatPhongView> listView = DatPhongViewDAO.getViewDatPhong1();

    Vector dataDichVu = new Vector();
    Vector titleDichVu = new Vector();
    ArrayList<DichVu> listDichVu = DatPhongViewDAO.getDataDichVu();

    Vector dataChiTietDichVu = new Vector();
    Vector titleChiTietDichVu = new Vector();
    ArrayList<ChiTietSDDichVu> listChiTietDichVu = DatPhongViewDAO.getDataChiTietSDDichVu();

    Vector dataThietBi = new Vector();
    Vector titleThietBi = new Vector();
    ArrayList<ThietBi> listThietBi = DatPhongViewDAO.getDataThietBi();

    Vector dataChiTietThietBi = new Vector();
    Vector titleChiTietThietBi = new Vector();
    ArrayList<ChiTietSDThietBi> listChiTietThietBi = DatPhongViewDAO.getDataChiTietSDThietBi();

    public static String idhoadon = "";
    private TableRowSorter<TableModel> rowSorterDv = new TableRowSorter<>();
    private TableRowSorter<TableModel> rowSorterTb = new TableRowSorter<>();

    public DatPhong() {
        initComponents();
        System.out.println(Main.selectedRoomId);
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        df.applyPattern("#,###");
        jLabel20.setText(String.valueOf(df.format(DatPhongViewDAO.getGiaPhong(Main.selectedRoomId))));
        jLabel48.setVisible(false);
        jLabel61.setVisible(false);
        if (Main.selectedStatus.equals("Còn trống")) {
            btnCheckIn.setVisible(true);
            btnCheckOut.setVisible(false);
            btnDoiPhong.setEnabled(false);
            btnTinhTienPhong.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnThanhToan.setEnabled(false);
            btnTienDuaKhach.setEnabled(false);
            //btnInHoaDon.setEnabled(false);

        } else if (Main.selectedStatus.equals("Đang sử dụng")) {
            btnCheckIn.setVisible(false);
            btnCheckOut.setVisible(true);
            cboKhachHang.setEnabled(false);
            cboNhanVien.setEnabled(false);
            cboLoaiThue.setEnabled(false);
            txtSoNguoiO.setEnabled(false);
            btnXemTienDatCoc.setEnabled(true);
            btnDoiPhong.setEnabled(true);
            btnTinhTienPhong.setEnabled(true);
            btnThanhToan.setEnabled(true);
            btnTienDuaKhach.setEnabled(true);
            btnInHoaDon.setEnabled(true);
        }
        jLabel2.setText(DatPhongViewDAO.getRoomName(Main.selectedRoomName));

        Date now = new Date();
        dateCheckIn.setDate(now);
        dateNgayThueDv.setDate(now);
        dateNgayThueTb.setDate(now);

        addTitleHoaDon();
        loadDataHoaDon(listView);
        //-------------------------
        addTitleDichVu();
        loadDataDichVu(listDichVu);
        addTitleChiTietDichVu();
        loadDataChiTietDichVu(listChiTietDichVu);
        //-------------------------
        addTitleThietBi();
        loadDataThietBi(listThietBi);
        addTitleChiTietThietBi();
        loadDataChiTietThietBi(listChiTietThietBi);

        loadCboKhachHang();
        loadCboNhanVien();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateCheckIn.setDateFormatString(dateFormat.toPattern());
        dateCheckOut.setDateFormatString(dateFormat.toPattern());
        SimpleDateFormat dateFormat_dv = new SimpleDateFormat("dd/MM/yyyy");
        dateNgayThueDv.setDateFormatString(dateFormat_dv.toPattern());
        dateNgayThueTb.setDateFormatString(dateFormat_dv.toPattern());

        SpinnerNumberModel modelspinner_dv = new SpinnerNumberModel();
        modelspinner_dv.setMinimum(1);
        modelspinner_dv.setValue(1);
        txtSoLuongDv.setModel(modelspinner_dv);
        SpinnerNumberModel modelspinner_tb = new SpinnerNumberModel();
        modelspinner_tb.setMinimum(1);
        modelspinner_tb.setValue(1);
        txtSoLuongTb.setModel(modelspinner_tb);

        searchTableSevice();
        searchTableProducts();

    }

    private void addTitleHoaDon() {
        titleHoaDon.add("Mã hóa đơn");
        titleHoaDon.add("Tên phòng");
        titleHoaDon.add("Tên khách hàng");
        titleHoaDon.add("Tên nhân viên");
        titleHoaDon.add("Loại hình đặt");
        titleHoaDon.add("Check-in");
        titleHoaDon.add("Check-out");
        titleHoaDon.add("Tổng thời gian");
        titleHoaDon.add("Trạn thái hđ");
    }

    private void loadDataHoaDon(ArrayList<DatPhongView> listView) {
        dataHoaDon.removeAllElements();
        for (DatPhongView view : listView) {
            Vector vector = new Vector();
            vector.add(view.getMadatphong());
            vector.add(view.getTenphong());
            vector.add(view.getTenkh());
            vector.add(view.getTennv());
            vector.add(view.getLoai());
            vector.add(view.getCheckin());
            vector.add(view.getCheckout());
            vector.add(view.getTongthoigian());
            vector.add(view.getTrangthai());
            dataHoaDon.add(vector);
        }
        tblDatPhong.setModel(new DefaultTableModel(dataHoaDon, titleHoaDon));
    }

    private void addTitleDichVu() {
        titleDichVu.add("Mã DV");
        titleDichVu.add("Tên DV");
        titleDichVu.add("Giá");
    }

    private void loadDataDichVu(ArrayList<DichVu> listView) {
        dataDichVu.removeAllElements();
        for (DichVu view : listView) {
            Vector vector = new Vector();
            vector.add(view.getMadichvu());
            vector.add(view.getTendichvu());
            vector.add(view.getGia());
            dataDichVu.add(vector);
        }
        tblDichVu.setModel(new DefaultTableModel(dataDichVu, titleDichVu));
    }

    private void addTitleChiTietDichVu() {
        titleChiTietDichVu.add("Mã HD");
        titleChiTietDichVu.add("Mã DV");
        titleChiTietDichVu.add("Ngày thuê");
        titleChiTietDichVu.add("Số lượng");
        titleChiTietDichVu.add("Tổng tiền");
    }

    private void loadDataChiTietDichVu(ArrayList<ChiTietSDDichVu> listView) {
        dataChiTietDichVu.removeAllElements();
        for (ChiTietSDDichVu view : listView) {
            Vector vector = new Vector();
            vector.add(view.getMahd());
            vector.add(view.getMadv());
            vector.add(view.getNgay());
            vector.add(view.getSoluong());
            vector.add(view.getTongtien());
            dataChiTietDichVu.add(vector);
        }
        tblChiTietDichVu.setModel(new DefaultTableModel(dataChiTietDichVu, titleChiTietDichVu));
    }

    private void addTitleThietBi() {
        titleThietBi.add("Mã TB");
        titleThietBi.add("Tên TB");
        titleThietBi.add("Giá");
    }

    private void loadDataThietBi(ArrayList<ThietBi> listView) {
        dataThietBi.removeAllElements();
        for (ThietBi view : listView) {
            Vector vector = new Vector();
            vector.add(view.getMathietbi());
            vector.add(view.getTenthietbi());
            vector.add(view.getGia());
            dataThietBi.add(vector);
        }
        tblThietBi.setModel(new DefaultTableModel(dataThietBi, titleThietBi));
    }

    private void addTitleChiTietThietBi() {
        titleChiTietThietBi.add("Mã HD");
        titleChiTietThietBi.add("Mã TB");
        titleChiTietThietBi.add("Ngày thuê");
        titleChiTietThietBi.add("Số lượng");
        titleChiTietThietBi.add("Tổng tiền");
    }

    private void loadDataChiTietThietBi(ArrayList<ChiTietSDThietBi> listView) {
        dataChiTietThietBi.removeAllElements();
        for (ChiTietSDThietBi view : listView) {
            Vector vector = new Vector();
            vector.add(view.getMahd());
            vector.add(view.getMatb());
            vector.add(view.getNgay());
            vector.add(view.getSoluong());
            vector.add(view.getTongtien());
            dataChiTietThietBi.add(vector);
        }
        tblChiTietThietBi.setModel(new DefaultTableModel(dataChiTietThietBi, titleChiTietThietBi));
    }

    private void loadCboKhachHang() {
        DefaultComboBoxModel cbomodel = new DefaultComboBoxModel();
        ArrayList<String> lst = KhachHangDAO.getDSTKhachHang();
        for (String str : lst) {
            cbomodel.addElement(str);
        }
        cboKhachHang.setModel(cbomodel);
    }

    private void loadCboNhanVien() {
        DefaultComboBoxModel cbomodel = new DefaultComboBoxModel();
        ArrayList<String> lst = NhanVienDAO.getDSTenNhanVien();
        for (String str : lst) {
            cbomodel.addElement(str);
        }
        cboNhanVien.setModel(cbomodel);
    }

    private void searchTableSevice() {
        rowSorterDv = new TableRowSorter<>(tblDichVu.getModel());
        tblDichVu.setRowSorter(rowSorterDv);
        txtTimKiemDv.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemDv.getText();

                if (text.trim().length() == 0) {
                    rowSorterDv.setRowFilter(null);
                } else {
                    rowSorterDv.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemDv.getText();

                if (text.trim().length() == 0) {
                    rowSorterDv.setRowFilter(null);
                } else {
                    rowSorterDv.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    private void searchTableProducts() {
        rowSorterTb = new TableRowSorter<>(tblThietBi.getModel());
        tblThietBi.setRowSorter(rowSorterTb);
        txtTimKiemTb.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemTb.getText();

                if (text.trim().length() == 0) {
                    rowSorterTb.setRowFilter(null);
                } else {
                    rowSorterTb.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemTb.getText();

                if (text.trim().length() == 0) {
                    rowSorterTb.setRowFilter(null);
                } else {
                    rowSorterTb.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboKhachHang = new javax.swing.JComboBox<>();
        cboNhanVien = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboLoaiThue = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dateCheckIn = new com.toedter.calendar.JDateChooser();
        dateCheckOut = new com.toedter.calendar.JDateChooser();
        txtSoNguoiO = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatPhong = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTongThoiGian = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTrangThaiHD = new javax.swing.JTextField();
        btnCheckIn = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnTinhTienPhong = new javax.swing.JButton();
        btnDoiPhong = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtDatCoc = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        btnXemTienDatCoc = new javax.swing.JButton();
        txtXemTruocTienDatCoc = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTienDatCoc = new javax.swing.JTextField();
        txtPhuThuCheckOut = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtTongTienThanhToan = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTongTienPhong_PhuThu = new javax.swing.JTextField();
        txtTienDichVu = new javax.swing.JTextField();
        txtTienThietBi = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPhuThuCheckIn = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTongTienPhong_TruTienDatCoc = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        btnTienDuaKhach = new javax.swing.JButton();
        txtTienThoi = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnInHoaDon = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChiTietDichVu = new javax.swing.JTable();
        txtTimKiemDv = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtMaDv = new javax.swing.JTextField();
        txtTenDv = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtSoLuongDv = new javax.swing.JSpinner();
        jLabel32 = new javax.swing.JLabel();
        dateNgayThueDv = new com.toedter.calendar.JDateChooser();
        jLabel33 = new javax.swing.JLabel();
        btnThemCTDichVu = new javax.swing.JButton();
        btnTinhTongTienDv = new javax.swing.JButton();
        btnXoaCTDichVu = new javax.swing.JButton();
        btnCapNhatCTDichVu = new javax.swing.JButton();
        txtTongTienDv = new javax.swing.JLabel();
        btnTimKiemCTDV = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtgiadv = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblChiTietThietBi = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblThietBi = new javax.swing.JTable();
        txtTimKiemTb = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtMaTb = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtTenTb = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtSoLuongTb = new javax.swing.JSpinner();
        btnTimKiemCTTB = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        dateNgayThueTb = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        txtTongTienTb = new javax.swing.JLabel();
        btnTinhTongTienTb = new javax.swing.JButton();
        btnThemCTThietBi = new javax.swing.JButton();
        btnXoaCTThietBi = new javax.swing.JButton();
        btnCapNhatCTThietBi = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(0, 64, 93));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("jLabel2  ");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 51, 51));
        jLabel43.setText("|  Giá phòng:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 64, 93));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chi Tiết Đặt Phòng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Khách Hàng:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nhân viên:");

        cboKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cboNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Loại thuê");

        cboLoaiThue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLoaiThue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ngày", "Theo giờ" }));
        cboLoaiThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiThueActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số người ở");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Check-in");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Check-out");

        dateCheckIn.setDateFormatString("dd/MM/yyyy HH:mm:ss");

        dateCheckOut.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        dateCheckOut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateCheckOutFocusLost(evt);
            }
        });

        txtSoNguoiO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoNguoiO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSoNguoiO.setText("1");
        txtSoNguoiO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoNguoiOFocusLost(evt);
            }
        });
        txtSoNguoiO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoNguoiOKeyTyped(evt);
            }
        });

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
        tblDatPhong.setEditingRow(0);
        tblDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatPhongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatPhong);

        jPanel5.setBackground(new java.awt.Color(0, 64, 93));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Hóa Đơn Chi Tiết");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel9)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Ngày");

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHD.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Tên phòng:");

        txtTenPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenPhong.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Tổng thời gian");

        txtTongThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongThoiGian.setEnabled(false);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Trạng thái HĐ");

        txtTrangThaiHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrangThaiHD.setEnabled(false);

        btnCheckIn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/check-in.png"))); // NOI18N
        btnCheckIn.setText(" Check-In");
        btnCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInActionPerformed(evt);
            }
        });

        btnCheckOut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/check-out.png"))); // NOI18N
        btnCheckOut.setText(" CheckOut");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });

        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/back.png"))); // NOI18N
        btnQuayLai.setText(" Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnCapNhat.setText(" Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnTinhTienPhong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTinhTienPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/payforroom.png"))); // NOI18N
        btnTinhTienPhong.setText(" Tính tiền");
        btnTinhTienPhong.setMaximumSize(new java.awt.Dimension(143, 32));
        btnTinhTienPhong.setMinimumSize(new java.awt.Dimension(143, 32));
        btnTinhTienPhong.setPreferredSize(new java.awt.Dimension(143, 32));
        btnTinhTienPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhTienPhongActionPerformed(evt);
            }
        });

        btnDoiPhong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDoiPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/changing-room.png"))); // NOI18N
        btnDoiPhong.setText("Đổi phòng");
        btnDoiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiPhongActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Mã HĐ:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Đặt cọc");

        txtDatCoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDatCoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDatCoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDatCocKeyTyped(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Số tiền phải đặt cọc :");

        btnXemTienDatCoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXemTienDatCoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/dollar.png"))); // NOI18N
        btnXemTienDatCoc.setText("Đặt cọc");
        btnXemTienDatCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemTienDatCocActionPerformed(evt);
            }
        });

        txtXemTruocTienDatCoc.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        txtXemTruocTienDatCoc.setForeground(new java.awt.Color(255, 0, 0));
        txtXemTruocTienDatCoc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtXemTruocTienDatCoc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 0, 51));
        jLabel48.setText("(75%) - so với tổng tiền phòng:");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 0, 51));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cboLoaiThue, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtSoNguoiO, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnXemTienDatCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDoiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTinhTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtXemTruocTienDatCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtDatCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtTongThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoNguoiO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel21))
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel23))
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel24))
                    .addComponent(txtTongThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtDatCoc)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtXemTruocTienDatCoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCheckIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCheckOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDoiPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXemTienDatCoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTinhTienPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 64, 93));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Thanh Toán ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Phụ thu check-out");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Phụ thu check-in");

        jPanel8.setBackground(new java.awt.Color(0, 64, 93));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 25));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Phụ thu check-in & check-out:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        txtTienDatCoc.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTienDatCoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienDatCoc.setEnabled(false);

        txtPhuThuCheckOut.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPhuThuCheckOut.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPhuThuCheckOut.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setText("Tổng tiền dịch vụ");

        jPanel11.setBackground(new java.awt.Color(0, 64, 93));
        jPanel11.setPreferredSize(new java.awt.Dimension(100, 25));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tổng tiền dịch vụ + Tổng tiền thiết bị:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        txtTongTienThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        txtTongTienThanhToan.setForeground(new java.awt.Color(255, 0, 0));
        txtTongTienThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTongTienThanhToan.setText("Tổng tiền:");

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setText("Tổng tiền + phụ thu");

        txtTongTienPhong_PhuThu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongTienPhong_PhuThu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTongTienPhong_PhuThu.setEnabled(false);

        txtTienDichVu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTienDichVu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienDichVu.setEnabled(false);

        txtTienThietBi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTienThietBi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienThietBi.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel26.setText("Tổng tiền thiết bị");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Tiền đặt cọc");

        txtPhuThuCheckIn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPhuThuCheckIn.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPhuThuCheckIn.setEnabled(false);

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel27.setText("Tổng tiền (sau khi đặt cọc)");

        txtTongTienPhong_TruTienDatCoc.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTongTienPhong_TruTienDatCoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTongTienPhong_TruTienDatCoc.setEnabled(false);

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTienKhachDua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnTienDuaKhach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTienDuaKhach.setForeground(new java.awt.Color(0, 102, 255));
        btnTienDuaKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save-money.png"))); // NOI18N
        btnTienDuaKhach.setText("Tiền Đưa Khách");
        btnTienDuaKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienDuaKhachActionPerformed(evt);
            }
        });

        txtTienThoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTienThoi.setForeground(new java.awt.Color(255, 0, 0));
        txtTienThoi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 51, 51));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/pay-per-click.png"))); // NOI18N
        btnThanhToan.setText(" Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Quy Định Về Check-In và Check-Out");

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/certificate.png"))); // NOI18N
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 51, 204));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel44.setText("Tiền khách đưa");

        jPanel12.setBackground(new java.awt.Color(0, 64, 93));
        jPanel12.setPreferredSize(new java.awt.Dimension(100, 25));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Tổng tiền thanh toán:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel47.setText("Tiền thối lại khách");

        btnInHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/print.png"))); // NOI18N
        btnInHoaDon.setText(" In Hóa Đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        jLabel52.setText("VNĐ");

        jLabel53.setText("VNĐ");

        jLabel54.setText("VNĐ");

        jLabel55.setText("VNĐ");

        jLabel56.setText("VNĐ");

        jLabel57.setText("VNĐ");

        jLabel58.setText("VNĐ");

        jLabel59.setText("VNĐ");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 0, 0));
        jLabel60.setText("VNĐ");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 0, 51));
        jLabel61.setText("VNĐ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42)))
                        .addContainerGap(50, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(16, 16, 16)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTongTienPhong_TruTienDatCoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtTongTienPhong_PhuThu, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhuThuCheckOut, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhuThuCheckIn, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienDatCoc))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel56)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTienThietBi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(txtTienDichVu, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnInHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTienDuaKhach)))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienKhachDua)
                                    .addComponent(txtTienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTienDatCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPhuThuCheckIn)
                        .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPhuThuCheckOut)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTienPhong_PhuThu)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTienPhong_TruTienDatCoc)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTienThanhToan)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTienThoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTienDuaKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThanhToan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDichVu);

        tblChiTietDichVu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblChiTietDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietDichVuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblChiTietDichVu);

        jLabel15.setText("Mã dịch vụ:");

        jLabel28.setText("Tên dịch vụ:");

        txtMaDv.setEditable(false);
        txtMaDv.setEnabled(false);
        txtMaDv.setFocusable(false);

        txtTenDv.setEditable(false);
        txtTenDv.setEnabled(false);
        txtTenDv.setFocusable(false);

        jLabel30.setText("Chọn số lượng:");

        txtSoLuongDv.setValue(10);

        jLabel32.setText("Ngày thuê:");

        dateNgayThueDv.setEnabled(false);
        dateNgayThueDv.setFocusable(false);

        jLabel33.setText("Tổng tiền dịch vụ:");

        btnThemCTDichVu.setText("Thêm CT dịch vụ");
        btnThemCTDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTDichVuActionPerformed(evt);
            }
        });

        btnTinhTongTienDv.setText("Tính tiền dịch vụ");
        btnTinhTongTienDv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhTongTienDvActionPerformed(evt);
            }
        });

        btnXoaCTDichVu.setText("Xóa CT dịch vụ");
        btnXoaCTDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTDichVuActionPerformed(evt);
            }
        });

        btnCapNhatCTDichVu.setText("Cập nhật CT dịch vụ");
        btnCapNhatCTDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCTDichVuActionPerformed(evt);
            }
        });

        txtTongTienDv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTongTienDv.setForeground(new java.awt.Color(255, 0, 51));

        btnTimKiemCTDV.setText("Tìm kiếm CTDV theo HD");
        btnTimKiemCTDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemCTDVActionPerformed(evt);
            }
        });

        jButton2.setText("Load lại CTDV");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel19.setText("Tìm kiếm DV:");

        txtgiadv.setEditable(false);
        txtgiadv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtgiadv.setEnabled(false);

        jLabel45.setText("Giá:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnThemCTDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaCTDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhatCTDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTimKiemDv)
                                    .addComponent(txtMaDv)
                                    .addComponent(txtTenDv, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtgiadv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongTienDv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(dateNgayThueDv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(txtSoLuongDv, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTimKiemCTDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(btnTinhTongTienDv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuongDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiemCTDV)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jButton2))))
                    .addComponent(dateNgayThueDv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTienDv, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTinhTongTienDv)
                        .addComponent(txtgiadv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTDichVu)
                    .addComponent(btnXoaCTDichVu)
                    .addComponent(btnCapNhatCTDichVu))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tblChiTietThietBi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblChiTietThietBi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietThietBiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblChiTietThietBi);

        tblThietBi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblThietBi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThietBiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblThietBi);

        jLabel35.setText("Mã thiết bị:");

        txtMaTb.setEditable(false);
        txtMaTb.setEnabled(false);
        txtMaTb.setFocusable(false);

        jLabel36.setText("Tên thiết bị:");

        txtTenTb.setEditable(false);
        txtTenTb.setEnabled(false);
        txtTenTb.setFocusable(false);

        jLabel37.setText("Chọn số lượng:");

        txtSoLuongTb.setValue(10);

        btnTimKiemCTTB.setText("Tìm kiếm CTTB theo HD");
        btnTimKiemCTTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemCTTBActionPerformed(evt);
            }
        });

        jLabel38.setText("Ngày thuê:");

        dateNgayThueTb.setEnabled(false);
        dateNgayThueTb.setFocusable(false);

        jButton5.setText("Load lại CTTB");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel39.setText("Tổng tiền thiết bị:");

        txtTongTienTb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTongTienTb.setForeground(new java.awt.Color(255, 0, 51));

        btnTinhTongTienTb.setText("Tính tiền thiết bị");
        btnTinhTongTienTb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhTongTienTbActionPerformed(evt);
            }
        });

        btnThemCTThietBi.setText("Thêm CT thiết bị");
        btnThemCTThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTThietBiActionPerformed(evt);
            }
        });

        btnXoaCTThietBi.setText("Xóa CT thiết bị");
        btnXoaCTThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTThietBiActionPerformed(evt);
            }
        });

        btnCapNhatCTThietBi.setText("Cập nhật CT thiết bị");
        btnCapNhatCTThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCTThietBiActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setEnabled(false);

        jLabel50.setText("Giá:");

        jLabel51.setText("Tìm kiếm TB:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnThemCTThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaCTThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhatCTThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTimKiemTb, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaTb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(txtTenTb, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongTienTb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(dateNgayThueTb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(txtSoLuongTb, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTimKiemCTTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(btnTinhTongTienTb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTimKiemTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSoLuongTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTimKiemCTTB)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jButton5))))
                    .addComponent(dateNgayThueTb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTienTb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTinhTongTienTb)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTThietBi)
                    .addComponent(btnXoaCTThietBi)
                    .addComponent(btnCapNhatCTThietBi))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(0, 64, 93));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Chi Tiết Dịch Vụ");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(0, 64, 93));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Chi Tiết Thiết Bị");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        if (txtSoNguoiO.getText().toString().isEmpty() || dateCheckOut.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Phải nhập đầy đủ thông tin !", "Thông báo", 2);
        } else {
            if (txtXemTruocTienDatCoc.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cần phải xem số tiền đặt cọc trước khi check-in !", "Thông báo", 2);
            } else {
                if (!txtDatCoc.getText().trim().equals(txtXemTruocTienDatCoc.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "Phải nhập đúng số tiền cần đặt cọc khi check-in theo quy định !", "Thông báo", 2);
                } else {
                    if (cboLoaiThue.getSelectedItem().toString().equals("Theo ngày")) {
                        if (dateCheckOut.getDate().getTime() < dateCheckIn.getDate().getTime()) {
                            JOptionPane.showMessageDialog(this, "Check-out phải lớn hơn check-in", "Thông báo", 2);
                        } else {
                            // Kiểm tra số ngày
                            long diff = dateCheckOut.getDate().getTime() - dateCheckIn.getDate().getTime();
                            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                            if (days < 1) {
                                JOptionPane.showMessageDialog(this, "Phải chọn loại theo giờ", "Thông báo", 2);
                            } else {
                                String idNv = NhanVienDAO.getIdNhanVien((String) cboNhanVien.getSelectedItem());
                                String idKh = KhachHangDAO.getIdKhachHang((String) cboKhachHang.getSelectedItem());

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String daycheckin = formatter.format(dateCheckIn.getDate());

                                SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String daycheckout = formatter2.format(dateCheckOut.getDate());

                                String sql1 = "set dateformat dmy EXEC Dat_Phong '" + idNv + "', '" + idKh + "', '" + Main.selectedRoomId + "', '" + daycheckin + "', '" + daycheckout + "', " + txtSoNguoiO.getText() + ", N'" + (String) cboLoaiThue.getSelectedItem() + "', " + Double.valueOf(txtDatCoc.getText()) + "";
                                int kq = DatPhongViewDAO.excuteSQL(sql1);
                                if (kq != -1) {
                                    String sql2 = "EXEC Update_TrangThaiPhong_1 @idphong = '" + Main.selectedRoomId + "'";
                                    DatPhongViewDAO.excuteSQL(sql2);
                                    listView = DatPhongViewDAO.getViewDatPhong1();
                                    JOptionPane.showMessageDialog(this, "Check-in thành công !", "Thông báo", 1);
                                    loadDataHoaDon(listView);
                                    Main frm = new Main();
                                    frm.setVisible(true);
                                    setVisible(false);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Check-in thất bại !", "Thông báo", 2);
                                }
                            }
                        }
                    } else if (cboLoaiThue.getSelectedItem().toString().equals("Theo giờ")) {
                        if (dateCheckOut.getDate().getTime() < dateCheckIn.getDate().getTime()) {
                            JOptionPane.showMessageDialog(this, "Check-out phải lớn hơn check-in", "Thông báo", 2);
                        } else {
                            // Kiểm tra số giờ
                            long diff = dateCheckOut.getDate().getTime() - dateCheckIn.getDate().getTime();
                            long hours = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);

                            if (hours > 24) {
                                JOptionPane.showMessageDialog(this, "Phải chọn loại theo ngày", "Thông báo", 2);
                            } else if (dateCheckOut.getDate().getDate() != dateCheckIn.getDate().getDate()) {
                                JOptionPane.showMessageDialog(this, "Phải chọn loại theo ngày", "Thông báo", 2);
                            } else {
                                String idNv = NhanVienDAO.getIdNhanVien((String) cboNhanVien.getSelectedItem());
                                String idKh = KhachHangDAO.getIdKhachHang((String) cboKhachHang.getSelectedItem());

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String daycheckin = formatter.format(dateCheckIn.getDate());

                                SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                String daycheckout = formatter2.format(dateCheckOut.getDate());

                                String sql1 = "set dateformat dmy EXEC Dat_Phong '" + idNv + "', '" + idKh + "', '" + Main.selectedRoomId + "', '" + daycheckin + "', '" + daycheckout + "', " + txtSoNguoiO.getText() + ", N'" + (String) cboLoaiThue.getSelectedItem() + "', " + Double.valueOf(txtDatCoc.getText()) + "";
                                int kq = DatPhongViewDAO.excuteSQL(sql1);
                                if (kq != -1) {
                                    String sql2 = "EXEC Update_TrangThaiPhong_1 @idphong = '" + Main.selectedRoomId + "'";
                                    DatPhongViewDAO.excuteSQL(sql2);
                                    listView = DatPhongViewDAO.getViewDatPhong1();
                                    JOptionPane.showMessageDialog(this, "Check-in thành công !", "Thông báo", 1);
                                    loadDataHoaDon(listView);
                                    Main frm = new Main();
                                    frm.setVisible(true);
                                    setVisible(false);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Check-in thất bại !", "Thông báo", 2);
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCheckInActionPerformed

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
        txtMaHD.setText(DatPhongViewDAO.getMaHD(Main.selectedRoomId));
        txtTongTienDv.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
        txtTienDichVu.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
        txtTongTienTb.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
        txtTienThietBi.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
        txtTienDatCoc.setText(String.valueOf(DatPhongViewDAO.getTienDatCoc(txtMaHD.getText().trim())));
    }//GEN-LAST:event_formWindowOpened

    private void tblDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatPhongMouseClicked
        int i = tblDatPhong.getSelectedRow();

        txtMaHD.setText(tblDatPhong.getValueAt(i, 0).toString().trim());
        txtTenPhong.setText(tblDatPhong.getValueAt(i, 1).toString().trim());
        cboKhachHang.setSelectedItem(tblDatPhong.getValueAt(i, 2).toString().trim());
        cboNhanVien.setSelectedItem(tblDatPhong.getValueAt(i, 3).toString().trim());
        cboLoaiThue.setSelectedItem(tblDatPhong.getValueAt(i, 4).toString().trim());
        txtTongThoiGian.setText(tblDatPhong.getValueAt(i, 7).toString().trim());
        txtTrangThaiHD.setText(tblDatPhong.getValueAt(i, 8).toString().trim());

        String dateString1 = tblDatPhong.getValueAt(i, 5).toString();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = dateFormat1.parse(dateString1);
        } catch (ParseException ex) {
            Logger.getLogger(DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
        dateCheckIn.setDate(date1);
        String dateString2 = tblDatPhong.getValueAt(i, 6).toString();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = null;
        try {
            date2 = dateFormat2.parse(dateString2);
        } catch (ParseException ex) {
            Logger.getLogger(DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
        dateCheckOut.setDate(date2);

        txtDatCoc.setText(String.valueOf(DatPhongViewDAO.getTienDatCoc(tblDatPhong.getValueAt(i, 0).toString().trim())));
        txtTienDatCoc.setText(String.valueOf(DatPhongViewDAO.getTienDatCoc(tblDatPhong.getValueAt(i, 0).toString().trim())));
    }//GEN-LAST:event_tblDatPhongMouseClicked

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn trả phòng?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (DatPhongViewDAO.getTongTien(txtMaHD.getText()) == 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa thanh toán tiền phòng cho hóa đơn " + txtMaHD.getText() + "và check-out " + Main.selectedRoomName + " này!", "Thông báo", 2);
            } else {
                String sql = "update phong set trang_thai = N'Đang dọn dẹp' where id_phong = '" + Main.selectedRoomId + "';"
                        + "update datphong set trang_thai = N'Đã thanh toán' where id_datphong = '" + txtMaHD.getText().trim() + "'";
                DatPhongViewDAO.excuteSQL(sql);
                JOptionPane.showMessageDialog(this, "Trả phòng thành công và chuyển trạng thái của hóa đơn " + txtMaHD.getText().trim() + " là: 'Đã thanh toán' !", "Thông báo", 1);
                Main frm = new Main();
                frm.setVisible(true);
                setVisible(false);
            }
        }
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        Main frm = new Main();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnDoiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiPhongActionPerformed
        idhoadon = txtMaHD.getText().trim();
        DoiPhong frm = new DoiPhong();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnDoiPhongActionPerformed

    private void cboLoaiThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiThueActionPerformed
        if (cboLoaiThue.getSelectedItem().equals("Theo ngày"))
            jLabel18.setText("Ngày");
        else if (cboLoaiThue.getSelectedItem().equals("Theo giờ"))
            jLabel18.setText("Giờ");
    }//GEN-LAST:event_cboLoaiThueActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String daycheckin = formatter.format(dateCheckIn.getDate());
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String daycheckout = formatter2.format(dateCheckOut.getDate());

        String sql = "set dateformat dmy UPDATE datphong SET check_in = '" + daycheckin + "', check_out = '" + daycheckout + "', dat_coc = " + txtDatCoc.getText().trim() + " WHERE id_datphong = '" + txtMaHD.getText().trim() + "'";
        int rs = DatPhongViewDAO.excuteSQL(sql);
        if (rs != -1) {
            listView = DatPhongViewDAO.getViewDatPhong1();
            loadDataHoaDon(listView);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công !", "Thông báo", 1);
            dateCheckOut.setDate(null);
            txtTongThoiGian.setText("");
            txtTrangThaiHD.setText("");
            txtDatCoc.setText("");
            txtTienDatCoc.setText("");
            txtPhuThuCheckIn.setText("");
            txtPhuThuCheckOut.setText("");
            txtTongTienPhong_PhuThu.setText("");
            txtTongTienPhong_TruTienDatCoc.setText("");
        } else
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại !", "Thông báo", 2);
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtDatCocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatCocKeyTyped
        if (evt.getKeyChar() < '0' || evt.getKeyChar() > '9')
            evt.consume();
    }//GEN-LAST:event_txtDatCocKeyTyped

    private void txtSoNguoiOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoNguoiOKeyTyped
        if (evt.getKeyChar() < '0' || evt.getKeyChar() > '9')
            evt.consume();
    }//GEN-LAST:event_txtSoNguoiOKeyTyped

    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        int i = tblDichVu.getSelectedRow();
        txtMaDv.setText(tblDichVu.getValueAt(i, 0).toString().trim());
        txtTenDv.setText(tblDichVu.getValueAt(i, 1).toString().trim());
        txtgiadv.setText(tblDichVu.getValueAt(i, 2).toString().trim());
    }//GEN-LAST:event_tblDichVuMouseClicked

    private void btnThemCTDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTDichVuActionPerformed
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String day = formatter.format(dateNgayThueDv.getDate());
        if (txtMaDv.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Chưa chọn dịch vụ nào! Hãy chọn 1 dịch vụ", "Thông báo", 2);
        else {
            String sql = "EXEC Them_chi_tiet_su_dung_dv '" + txtMaHD.getText().trim() + "', '" + txtMaDv.getText().trim() + "', '" + day + "', " + (int) txtSoLuongDv.getValue() + "";
            DatPhongViewDAO.excuteSQL(sql);
            listChiTietDichVu = DatPhongViewDAO.getDataChiTietSDDichVu();
            loadDataChiTietDichVu(listChiTietDichVu);
            txtMaDv.setText("");
            txtTenDv.setText("");
            txtSoLuongDv.setValue(1);
            txtTongTienDv.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
            txtTienDichVu.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
            JOptionPane.showMessageDialog(this, "Thêm dịch vụ cho " + Main.selectedRoomName + " thành công !", "Thông báo", 1);
        }
    }//GEN-LAST:event_btnThemCTDichVuActionPerformed

    private void btnXoaCTDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTDichVuActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (txtMaDv.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa chọn dịch vụ nào của khách hàng để xóa", "Thông báo", 2);
            } else {
                String sql = "delete from chitietsudungdv where id_datphong = '" + txtMaHD.getText() + "' and id_dichvu = '" + txtMaDv.getText() + "'";
                int rs = DatPhongViewDAO.excuteSQL(sql);
                if (rs != -1) {
                    listChiTietDichVu = DatPhongViewDAO.getDataChiTietSDDichVu();
                    loadDataChiTietDichVu(listChiTietDichVu);
                    txtMaDv.setText("");
                    txtTenDv.setText("");
                    txtTongTienDv.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
                    txtTienDichVu.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
                    JOptionPane.showMessageDialog(this, "Xóa thành công !", "Thông báo", 1);
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại !", "Thông báo", 2);
                }
            }
        }
    }//GEN-LAST:event_btnXoaCTDichVuActionPerformed

    private void btnCapNhatCTDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCTDichVuActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thay đổi?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (txtMaDv.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa chọn dịch vụ nào của khách hàng để cập nhật", "Thông báo", 2);
            } else {
                String sql = "update chitietsudungdv set so_luong = " + (int) txtSoLuongDv.getValue() + ", tong_tien_dv = " + (int) txtSoLuongDv.getValue() + " * (select gia from dichvu where id_dichvu = '" + txtMaDv.getText() + "') where id_datphong = '" + txtMaHD.getText() + "' and id_dichvu = '" + txtMaDv.getText() + "'";
                int rs = DatPhongViewDAO.excuteSQL(sql);
                if (rs != -1) {
                    listChiTietDichVu = DatPhongViewDAO.getDataChiTietSDDichVu();
                    loadDataChiTietDichVu(listChiTietDichVu);
                    txtMaDv.setText("");
                    txtTenDv.setText("");
                    txtTongTienDv.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
                    txtTienDichVu.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công !", "Thông báo", 1);
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại !", "Thông báo", 2);
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatCTDichVuActionPerformed

    private void tblChiTietDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietDichVuMouseClicked
        int i = tblChiTietDichVu.getSelectedRow();
        txtMaHD.setText(tblChiTietDichVu.getValueAt(i, 0).toString().trim());
        txtMaDv.setText(tblChiTietDichVu.getValueAt(i, 1).toString().trim());
        txtTenDv.setText(DatPhongViewDAO.getTenDichVu(tblChiTietDichVu.getValueAt(i, 1).toString().trim()));
        txtSoLuongDv.setValue(Integer.valueOf(tblChiTietDichVu.getValueAt(i, 3).toString().trim()));
    }//GEN-LAST:event_tblChiTietDichVuMouseClicked

    private void btnTinhTongTienDvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhTongTienDvActionPerformed
        txtTongTienDv.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
        txtTienDichVu.setText(String.valueOf(DatPhongViewDAO.sumTienDvTheoMaHd(txtMaHD.getText().trim())));
    }//GEN-LAST:event_btnTinhTongTienDvActionPerformed

    private void btnTimKiemCTDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemCTDVActionPerformed
        listChiTietDichVu = DatPhongViewDAO.getDataChiTietSDDichVuTheoMaHd(txtMaHD.getText());
        loadDataChiTietDichVu(listChiTietDichVu);
    }//GEN-LAST:event_btnTimKiemCTDVActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        listChiTietDichVu = DatPhongViewDAO.getDataChiTietSDDichVu();
        loadDataChiTietDichVu(listChiTietDichVu);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblThietBiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThietBiMouseClicked
        int i = tblThietBi.getSelectedRow();
        txtMaTb.setText(tblThietBi.getValueAt(i, 0).toString().trim());
        txtTenTb.setText(tblThietBi.getValueAt(i, 1).toString().trim());
        jTextField1.setText(tblThietBi.getValueAt(i, 2).toString().trim());
    }//GEN-LAST:event_tblThietBiMouseClicked

    private void tblChiTietThietBiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietThietBiMouseClicked
        int i = tblChiTietThietBi.getSelectedRow();
        txtMaHD.setText(tblChiTietThietBi.getValueAt(i, 0).toString().trim());
        txtMaTb.setText(tblChiTietThietBi.getValueAt(i, 1).toString().trim());
        txtTenTb.setText(DatPhongViewDAO.getTenThietBi(tblChiTietThietBi.getValueAt(i, 1).toString().trim()));
        txtSoLuongTb.setValue(Integer.valueOf(tblChiTietThietBi.getValueAt(i, 3).toString().trim()));
    }//GEN-LAST:event_tblChiTietThietBiMouseClicked

    private void btnTimKiemCTTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemCTTBActionPerformed
        listChiTietThietBi = DatPhongViewDAO.getDataChiTietSDThietBiTheoMaHd(txtMaHD.getText());
        loadDataChiTietThietBi(listChiTietThietBi);
    }//GEN-LAST:event_btnTimKiemCTTBActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        listChiTietThietBi = DatPhongViewDAO.getDataChiTietSDThietBi();
        loadDataChiTietThietBi(listChiTietThietBi);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnTinhTongTienTbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhTongTienTbActionPerformed
        txtTongTienTb.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
        txtTienThietBi.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
    }//GEN-LAST:event_btnTinhTongTienTbActionPerformed

    private void btnThemCTThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTThietBiActionPerformed
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String day = formatter.format(dateNgayThueTb.getDate());
        if (txtMaTb.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Chưa chọn thiết bị nào! Hãy chọn 1 thiết bị", "Thông báo", 2);
        else {
            String sql = "EXEC Them_chi_tiet_su_dung_tb '" + txtMaHD.getText().trim() + "', '" + txtMaTb.getText().trim() + "', '" + day + "', " + (int) txtSoLuongTb.getValue() + "";
            DatPhongViewDAO.excuteSQL(sql);
            listChiTietThietBi = DatPhongViewDAO.getDataChiTietSDThietBi();
            loadDataChiTietThietBi(listChiTietThietBi);
            txtMaTb.setText("");
            txtTenTb.setText("");
            txtSoLuongTb.setValue(1);
            txtTongTienTb.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
            txtTienThietBi.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
            JOptionPane.showMessageDialog(this, "Thêm dịch vụ cho " + Main.selectedRoomName + " thành công !", "Thông báo", 1);
        }
    }//GEN-LAST:event_btnThemCTThietBiActionPerformed

    private void btnXoaCTThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTThietBiActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (txtMaTb.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa chọn thiết bị nào của khách hàng để xóa", "Thông báo", 2);
            } else {
                String sql = "delete from chitietsudungtb where id_datphong = '" + txtMaHD.getText() + "' and id_thietbi = '" + txtMaTb.getText() + "'";
                DatPhongViewDAO.excuteSQL(sql);
                listChiTietThietBi = DatPhongViewDAO.getDataChiTietSDThietBi();
                loadDataChiTietThietBi(listChiTietThietBi);
                txtMaTb.setText("");
                txtTenTb.setText("");
                txtSoLuongTb.setValue(1);
                txtTongTienTb.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
                txtTienThietBi.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
                JOptionPane.showMessageDialog(this, "Xóa thành công !", "Thông báo", 1);
            }
        }
    }//GEN-LAST:event_btnXoaCTThietBiActionPerformed

    private void btnCapNhatCTThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCTThietBiActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thay đổi?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (txtMaTb.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa chọn thiết bị nào của khách hàng để cập nhật", "Thông báo", 2);
            } else {
                String sql = "update chitietsudungtb set so_luong = " + (int) txtSoLuongTb.getValue() + ", tong_tien_tb = " + (int) txtSoLuongTb.getValue() + " * (select gia from thietbi where id_thietbi = '" + txtMaTb.getText() + "') where id_datphong = '" + txtMaHD.getText() + "' and id_thietbi = '" + txtMaTb.getText() + "'";
                DatPhongViewDAO.excuteSQL(sql);
                listChiTietThietBi = DatPhongViewDAO.getDataChiTietSDThietBi();
                loadDataChiTietThietBi(listChiTietThietBi);
                txtMaTb.setText("");
                txtTongTienTb.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
                txtTienThietBi.setText(String.valueOf(DatPhongViewDAO.sumTienTbTheoMaHd(txtMaHD.getText().trim())));
                JOptionPane.showMessageDialog(this, "Cập nhật thành công !", "Thông báo", 1);
            }
        }
    }//GEN-LAST:event_btnCapNhatCTThietBiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        df.applyPattern("#,###");
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn tổng tiền thanh toán không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (cboLoaiThue.getSelectedIndex() == 0) {
                txtTongTienThanhToan.setText(String.valueOf(
                        df.format(Double.parseDouble(txtTongTienPhong_TruTienDatCoc.getText().trim())
                                + Double.parseDouble(txtTienDichVu.getText().trim())
                                + Double.parseDouble(txtTienThietBi.getText().trim()))));
                String sql1 = "update datphong set phu_thu_checkin = " + txtPhuThuCheckIn.getText().trim() + ", phu_thu_checkout = " + txtPhuThuCheckOut.getText().trim() + ", tong_tien = " + txtTongTienPhong_PhuThu.getText().trim() + " where id_datphong = '" + txtMaHD.getText().trim() + "'";
                DatPhongViewDAO.excuteSQL(sql1);
                btnCapNhat.setEnabled(false);
                btnXemTienDatCoc.setEnabled(false);
                btnDoiPhong.setEnabled(false);
                btnTinhTienPhong.setEnabled(false);
            } else {
                txtTongTienThanhToan.setText(String.valueOf(
                        df.format(Double.parseDouble(txtTongTienPhong_TruTienDatCoc.getText().trim())
                                + Double.parseDouble(txtTienDichVu.getText().trim())
                                + Double.parseDouble(txtTienThietBi.getText().trim()))));
                String sql1 = "update datphong set tong_tien = " + txtTongTienPhong_PhuThu.getText().trim() + " where id_datphong = '" + txtMaHD.getText().trim() + "'";
                DatPhongViewDAO.excuteSQL(sql1);
                btnCapNhat.setEnabled(false);
                btnXemTienDatCoc.setEnabled(false);
                btnDoiPhong.setEnabled(false);
                btnTinhTienPhong.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTienDuaKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienDuaKhachActionPerformed
        jLabel61.setVisible(true);
        double a = Double.valueOf(txtTongTienThanhToan.getText().replace(",", "")) - Double.valueOf(txtTienKhachDua.getText());
        if (a < 0) {
            txtTienThoi.setText(String.valueOf(Double.parseDouble(txtTienKhachDua.getText().trim())
                    - Double.parseDouble(txtTongTienThanhToan.getText().trim().replace(",", ""))));
        } else {
            JOptionPane.showMessageDialog(this, "Khách hàng '" + (String) cboKhachHang.getSelectedItem() + "' chưa đưa đủ tiền ! Vui lòng báo lại khách.", "Thông báo", 2);
        }
    }//GEN-LAST:event_btnTienDuaKhachActionPerformed

    private void txtSoNguoiOFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoNguoiOFocusLost
        if (Integer.parseInt(txtSoNguoiO.getText()) > DatPhongViewDAO.getSoLuongNguoi(Main.selectedRoomId)) {
            JOptionPane.showMessageDialog(this, "Số người ở phải ít hơn " + DatPhongViewDAO.getSoLuongNguoi(Main.selectedRoomId) + " người", "Thông báo", 1);
            txtSoNguoiO.requestFocus();
        }
    }//GEN-LAST:event_txtSoNguoiOFocusLost

    private void dateCheckOutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateCheckOutFocusLost
        long day = dateCheckOut.getDate().getDay() - dateCheckIn.getDate().getDay();
        jLabel40.setText("dsfdsfdsfdsfds");
    }//GEN-LAST:event_dateCheckOutFocusLost

    private void btnXemTienDatCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemTienDatCocActionPerformed
        if (dateCheckOut.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Chưa nhập check-out !", "Thông báo", 2);
        } else {
            if (dateCheckOut.getDate().getTime() < dateCheckIn.getDate().getTime()) {
                JOptionPane.showMessageDialog(this, "Check-out phải lớn hơn check-in !", "Thông báo", 2);
            } else {
                LocalDate fromDate = dateCheckIn.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = dateCheckOut.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                jLabel48.setVisible(true);
                long daysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
                long price = DatPhongViewDAO.getGiaPhong(Main.selectedRoomId);
                long rs = (daysBetween * price * 3) / 4;
                txtXemTruocTienDatCoc.setText(String.valueOf(rs));
                jLabel49.setText(String.valueOf(daysBetween * price));
            }
        }
    }//GEN-LAST:event_btnXemTienDatCocActionPerformed

    private void btnTinhTienPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhTienPhongActionPerformed
        ConnectionDB con = new ConnectionDB();
        con.getCn();
        String sql = "DECLARE @tong_tien FLOAT;\n"
                + "DECLARE @tien_phuthu FLOAT;\n"
                + "DECLARE @tien_phuthu1 FLOAT;\n"
                + "EXEC Tinh_Tong_Tien_Phuthu_1 N'" + (String) cboLoaiThue.getSelectedItem() + "', '" + txtMaHD.getText().trim() + "', @tong_tien OUTPUT, @tien_phuthu OUTPUT, @tien_phuthu1 OUTPUT;\n"
                + "SELECT @tong_tien AS tong_tien, @tien_phuthu AS tien_phuthu_checkin, @tien_phuthu1 AS tien_phuthu_checkout;";
        ResultSet rs = con.executeQuery(sql);
        try {
            while (rs.next()) {
                txtTongTienPhong_PhuThu.setText(rs.getString(1));
                txtPhuThuCheckIn.setText(rs.getString(2));
                txtPhuThuCheckOut.setText(rs.getString(3));

                txtTongTienPhong_TruTienDatCoc.setText(String.valueOf(Double.parseDouble(rs.getString(1)) - DatPhongViewDAO.getTienDatCoc(txtMaHD.getText())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }//GEN-LAST:event_btnTinhTienPhongActionPerformed

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        QuyDinh frm = new QuyDinh();
        frm.setVisible(true);
    }//GEN-LAST:event_jLabel42MouseClicked

    public static String tenKh = "", tenNv = "", checkin = "", checkout = "", tonggio = "", tienphong = "", tienDv = "", tienTb = "", datcoc = "";

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        if (dateCheckOut.getDate() == null || txtTongTienPhong_PhuThu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cần phải nhập đầy đủ thông tin mới in hóa đơn!", "Thông báo", 2);
        } else {
            idhoadon = txtMaHD.getText();
            tenKh = (String) cboKhachHang.getSelectedItem();
            tenNv = (String) cboNhanVien.getSelectedItem();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            checkin = formatter.format(dateCheckIn.getDate());
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            checkout = formatter2.format(dateCheckOut.getDate());
            tonggio = txtTongThoiGian.getText().trim();
            tienphong = txtTongTienPhong_PhuThu.getText().trim();
            tienDv = txtTienDichVu.getText().trim();
            tienTb = txtTienThietBi.getText().trim();
            datcoc = txtTienDatCoc.getText().trim();
            XuatHoaDon frm = new XuatHoaDon();
            frm.setVisible(true);
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

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
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhatCTDichVu;
    private javax.swing.JButton btnCapNhatCTThietBi;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnDoiPhong;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemCTDichVu;
    private javax.swing.JButton btnThemCTThietBi;
    private javax.swing.JButton btnTienDuaKhach;
    private javax.swing.JButton btnTimKiemCTDV;
    private javax.swing.JButton btnTimKiemCTTB;
    private javax.swing.JButton btnTinhTienPhong;
    private javax.swing.JButton btnTinhTongTienDv;
    private javax.swing.JButton btnTinhTongTienTb;
    private javax.swing.JButton btnXemTienDatCoc;
    private javax.swing.JButton btnXoaCTDichVu;
    private javax.swing.JButton btnXoaCTThietBi;
    private javax.swing.JComboBox<String> cboKhachHang;
    private javax.swing.JComboBox<String> cboLoaiThue;
    private javax.swing.JComboBox<String> cboNhanVien;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private com.toedter.calendar.JDateChooser dateCheckOut;
    private com.toedter.calendar.JDateChooser dateNgayThueDv;
    private com.toedter.calendar.JDateChooser dateNgayThueTb;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblChiTietDichVu;
    private javax.swing.JTable tblChiTietThietBi;
    private javax.swing.JTable tblDatPhong;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTable tblThietBi;
    private javax.swing.JTextField txtDatCoc;
    private javax.swing.JTextField txtMaDv;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaTb;
    private javax.swing.JTextField txtPhuThuCheckIn;
    private javax.swing.JTextField txtPhuThuCheckOut;
    private javax.swing.JSpinner txtSoLuongDv;
    private javax.swing.JSpinner txtSoLuongTb;
    private javax.swing.JTextField txtSoNguoiO;
    private javax.swing.JTextField txtTenDv;
    private javax.swing.JTextField txtTenPhong;
    private javax.swing.JTextField txtTenTb;
    private javax.swing.JTextField txtTienDatCoc;
    private javax.swing.JTextField txtTienDichVu;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThietBi;
    private javax.swing.JLabel txtTienThoi;
    private javax.swing.JTextField txtTimKiemDv;
    private javax.swing.JTextField txtTimKiemTb;
    private javax.swing.JTextField txtTongThoiGian;
    private javax.swing.JLabel txtTongTienDv;
    private javax.swing.JTextField txtTongTienPhong_PhuThu;
    private javax.swing.JTextField txtTongTienPhong_TruTienDatCoc;
    private javax.swing.JLabel txtTongTienTb;
    private javax.swing.JLabel txtTongTienThanhToan;
    private javax.swing.JTextField txtTrangThaiHD;
    private javax.swing.JLabel txtXemTruocTienDatCoc;
    private javax.swing.JTextField txtgiadv;
    // End of variables declaration//GEN-END:variables
}
