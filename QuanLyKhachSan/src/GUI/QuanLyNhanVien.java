package GUI;

import DAO.NhanVienDAO;
import CLASS.NhanVien;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhanVien extends javax.swing.JFrame {

    Vector tblData = new Vector();
    Vector tblTitle = new Vector();
    DefaultTableModel tblModel;
    static ArrayList<NhanVien> dsnv = NhanVienDAO.getDSNhanVien();

    public QuanLyNhanVien() {
        initComponents();
        ten_nhanvien.setText(DangNhap.fullname);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(DangNhap.fileimage));
        ImageIcon imageIcon = new ImageIcon("user.png");
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);
        lblHinhAnh.setIcon(imageIcon);
        addTitle();
        loadDataNV(dsnv);
    }

    private void addTitle() {
        tblTitle.add("Mã NV");
        tblTitle.add("Tên NV");
        tblTitle.add("Ngày Sinh");
        tblTitle.add("Số Điện Thoại");
        tblTitle.add("Giới Tính");
        tblTitle.add("Email");
        tblTitle.add("Hình ảnh");
        tblTitle.add("Tên đăng nhập");
        tblTitle.add("Mật Khẩu");
        tblTitle.add("Quyền");
    }

    private void loadDataNV(ArrayList<NhanVien> ds) {
        tblData.removeAllElements();
        for (NhanVien n : ds) {
            Vector v = new Vector();
            v.add(n.getIdnv());
            v.add(n.getTennv());
            v.add(n.getNgaysinh());
            v.add(n.getSdt());
            v.add(n.getGioitinh());
            v.add(n.getEmail());
            v.add(n.getHinhanh());
            v.add(n.getTendn());
            v.add(n.getMatkhau());
            v.add(n.getQuyen());
            tblData.add(v);
        }
        jTable1.setModel(new DefaultTableModel(tblData, tblTitle));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        imageAvatar1 = new CLASS.ImageAvatar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_idnv = new javax.swing.JTextField();
        txt_tennv = new javax.swing.JTextField();
        txt_ngaysinh = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_dangnhap = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdb_nam = new javax.swing.JRadioButton();
        rdb_nu = new javax.swing.JRadioButton();
        ten_nhanvien = new javax.swing.JLabel();
        rdb_nhanvien = new javax.swing.JRadioButton();
        rdb_admin = new javax.swing.JRadioButton();
        but_them = new javax.swing.JButton();
        but_xoa = new javax.swing.JButton();
        but_sua = new javax.swing.JButton();
        but_luu = new javax.swing.JButton();
        but_nhapanh = new javax.swing.JButton();
        txtAnh = new javax.swing.JTextField();
        txt_nhaplaipass = new javax.swing.JPasswordField();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 64, 93));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày Sinh");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Số Điện Thoại");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Giới Tính");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Email");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hình Ảnh");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tên Đăng Nhập");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mật Khẩu");

        txt_idnv.setEnabled(false);

        txt_sdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_sdtKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Xác Nhân MK");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Quyền");

        rdb_nam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdb_nam);
        rdb_nam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdb_nam.setForeground(new java.awt.Color(153, 255, 0));
        rdb_nam.setSelected(true);
        rdb_nam.setText("Nam");
        rdb_nam.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        rdb_nu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdb_nu);
        rdb_nu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdb_nu.setForeground(new java.awt.Color(153, 255, 0));
        rdb_nu.setText("Nữ");

        ten_nhanvien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ten_nhanvien.setForeground(new java.awt.Color(255, 255, 255));
        ten_nhanvien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ten_nhanvien.setText("ABC");

        rdb_nhanvien.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdb_nhanvien);
        rdb_nhanvien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdb_nhanvien.setForeground(new java.awt.Color(153, 255, 0));
        rdb_nhanvien.setSelected(true);
        rdb_nhanvien.setText("Nhân Viên");

        rdb_admin.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdb_admin);
        rdb_admin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdb_admin.setForeground(new java.awt.Color(153, 255, 0));
        rdb_admin.setText("Admin");

        but_them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_them.setForeground(new java.awt.Color(255, 0, 0));
        but_them.setText("LƯU");
        but_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_themActionPerformed(evt);
            }
        });

        but_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_xoa.setForeground(new java.awt.Color(255, 0, 51));
        but_xoa.setText("XÓA");
        but_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_xoaActionPerformed(evt);
            }
        });

        but_sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_sua.setForeground(new java.awt.Color(255, 0, 0));
        but_sua.setText("SỬA");
        but_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_suaActionPerformed(evt);
            }
        });

        but_luu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_luu.setForeground(new java.awt.Color(255, 0, 51));
        but_luu.setText("THÊM");
        but_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_luuActionPerformed(evt);
            }
        });

        but_nhapanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_nhapanh.setForeground(new java.awt.Color(255, 0, 0));
        but_nhapanh.setText("NHẬP ẢNH");
        but_nhapanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_nhapanhActionPerformed(evt);
            }
        });

        txt_nhaplaipass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_nhaplaipassMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_nhaplaipassMouseReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Avatar nhân viên");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 51));
        jButton1.setText("QUAY LẠI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo tên", "Theo mã" }));

        jButton2.setText("Tìm kiếm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_idnv, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(91, 91, 91)
                                                .addComponent(txtAnh))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel5)
                                                .addGap(14, 14, 14)
                                                .addComponent(rdb_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(rdb_nu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(41, 41, 41)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(but_nhapanh, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txt_nhaplaipass, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_dangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11)
                                        .addGap(35, 35, 35)
                                        .addComponent(rdb_nhanvien)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rdb_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(61, 61, 61))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ten_nhanvien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(but_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(but_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(but_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(but_them, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(350, 350, 350)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ten_nhanvien))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdb_nam)
                                    .addComponent(rdb_nu)
                                    .addComponent(txt_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_nhaplaipass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(but_nhapanh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_dangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(rdb_admin)
                                    .addComponent(rdb_nhanvien)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_idnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                .addComponent(but_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(but_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(but_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(but_them, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        NhanVien nv = NhanVienDAO.getDSNhanVien().get(index);
        txt_idnv.setText(nv.getIdnv());
        txt_tennv.setText(nv.getTennv());
        txt_ngaysinh.setText(nv.getNgaysinh());
        txt_sdt.setText(nv.getSdt());
        txt_email.setText(nv.getEmail());
        txtAnh.setText(nv.getHinhanh());
        txt_pass.setText(nv.getMatkhau());
        txt_dangnhap.setText(nv.getTendn());
        String gioitinh = nv.getGioitinh();
        int quyen = nv.getQuyen();
        if ("Nam".equals(gioitinh)) {
            rdb_nam.setSelected(true);
        } else {
            rdb_nu.setSelected(true);
        }
        if (quyen == 1) {
            rdb_admin.setSelected(true);
        } else {
            rdb_nhanvien.setSelected(true);
        }
        ImageIcon imageIcon = new ImageIcon(txtAnh.getText().trim());
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);
        lblHinhAnh.setIcon(imageIcon);
    }//GEN-LAST:event_jTable1MouseClicked

    public boolean ktNhapNgay1(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void but_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_themActionPerformed
        char[] strPassword = txt_pass.getPassword();
        String pass = String.valueOf(strPassword);
        char[] strRePassword = txt_nhaplaipass.getPassword();
        String repass = String.valueOf(strRePassword);

        if (txt_idnv.getText().isEmpty() && txt_sdt.getText().isEmpty() && txtAnh.getText().isEmpty() && txt_pass.getText().isEmpty()
                && txt_tennv.getText().isEmpty() && txt_nhaplaipass.getText().isEmpty() && txt_ngaysinh.getText().isEmpty() && txt_dangnhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập đầy đủ thông tin !", "Thông báo", 2);
        } else {
            if (!ktNhapNgay1(txt_ngaysinh.getText())) {
                JOptionPane.showMessageDialog(this, "Phải nhập đúng định dạng: dd/MM/yyyy !", "Thông báo", 2);
            } else {
                if (!pass.equals(repass)) {
                    JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu và mật khẩu không trùng khớp", "Thông báo", 2);
                } else {
                    NhanVien nv = new NhanVien();
                    nv.setIdnv(txt_idnv.getText());
                    nv.setTennv(txt_tennv.getText());
                    nv.setNgaysinh(txt_ngaysinh.getText());
                    nv.setSdt(txt_sdt.getText());
                    nv.setEmail(txt_email.getText());
                    nv.setHinhanh(txtAnh.getText());
                    nv.setMatkhau(txt_pass.getText());
                    nv.setTendn(txt_dangnhap.getText());
                    String gioitinh = nv.getGioitinh();
                    int quyen = nv.getQuyen();
                    if (rdb_nam.isSelected()) {
                        nv.setGioitinh("Nam");
                    } else {
                        nv.setGioitinh("Nữ");
                    }
                    if (rdb_admin.isSelected()) {
                        nv.setQuyen(1);
                    } else {
                        nv.setQuyen(0);
                    }
                    NhanVienDAO.ThemNV(nv);
                    JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                    loadtablenv();
                }
            }
        }
    }//GEN-LAST:event_but_themActionPerformed

    private void but_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_luuActionPerformed
        txt_dangnhap.setText("");
        txt_email.setText("");
        txt_idnv.setText("");
        txt_ngaysinh.setText("");
        txtAnh.setText("");
        txt_nhaplaipass.setText("");
        txt_pass.setText("");
        txt_sdt.setText("");
        txt_tennv.setText("");
        rdb_nam.setSelected(true);
        rdb_nhanvien.setSelected(true);
        txt_idnv.requestFocus();
    }//GEN-LAST:event_but_luuActionPerformed

    private void but_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_xoaActionPerformed
        String id = txt_idnv.getText();
        NhanVienDAO.xoanv(id);
        JOptionPane.showMessageDialog(this, "Xóa Thành Công");
        loadtablenv();
    }//GEN-LAST:event_but_xoaActionPerformed

    private void but_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_suaActionPerformed
        NhanVien nv = new NhanVien();
        nv.setIdnv(txt_idnv.getText());
        nv.setTennv(txt_tennv.getText());
        nv.setNgaysinh(txt_ngaysinh.getText());
        nv.setSdt(txt_sdt.getText());
        nv.setEmail(txt_email.getText());
        nv.setHinhanh(txtAnh.getText());
        nv.setMatkhau(txt_pass.getText());
        nv.setTendn(txt_dangnhap.getText());
        String gioitinh = nv.getGioitinh();
        int quyen = nv.getQuyen();
        if (rdb_nam.isSelected()) {
            nv.setGioitinh("Nam");
        } else {
            nv.setGioitinh("Nữ");
        }
        if (rdb_admin.isSelected()) {
            nv.setQuyen(1);
        } else {
            nv.setQuyen(0);
        }
        NhanVienDAO.Suanv(nv);
        JOptionPane.showMessageDialog(this, "Sửa Thành Công");
        loadtablenv();
    }//GEN-LAST:event_but_suaActionPerformed

    private void txt_nhaplaipassMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nhaplaipassMouseReleased
        // TODO add your handling code here
//        String txtmatkhau = txt_pass.getText();
//        String txtmatkhauconform = txt_nhaplaipass.getText();
//        if(txtmatkhauconform != txtmatkhau){
//            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không đúng");
//        }
    }//GEN-LAST:event_txt_nhaplaipassMouseReleased

    private void txt_nhaplaipassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nhaplaipassMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_nhaplaipassMouseExited

    private void txt_sdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sdtKeyReleased
        String sdt = txt_sdt.getText();
        if (sdt.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không chứa ký tự");
            txt_sdt.setText("");
        } else if (sdt.length() > 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được 10 số");
            txt_sdt.setText("");
        }
    }//GEN-LAST:event_txt_sdtKeyReleased

    private void but_nhapanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_nhapanhActionPerformed
        final JFileChooser fileDialog = new JFileChooser();
        int returnVal = fileDialog.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileDialog.getSelectedFile();
            txtAnh.setText(file.getName());
            String url = file.getName();
            ImageIcon imageIcon = new ImageIcon(url);
            Image image = imageIcon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(320, 320, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimg);

            lblHinhAnh.setIcon(imageIcon);
        } else {
            txtAnh.setText("");
        }
    }//GEN-LAST:event_but_nhapanhActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Main frm = new Main();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String chon = (String) jComboBox1.getSelectedItem();
        if (chon.equals("Theo tên")) {
            if (jTextField1.getText().isEmpty()) {
                dsnv = NhanVienDAO.getDSNhanVien();
                loadDataNV(dsnv);
            } else {
                dsnv = NhanVienDAO.searchDSNhanVien_TheoTen(jTextField1.getText());
                loadDataNV(dsnv);
            }
        }
        else if (chon.equals("Theo mã")) {
            if (jTextField1.getText().isEmpty()) {
                dsnv = NhanVienDAO.getDSNhanVien();
                loadDataNV(dsnv);
            } else {
                dsnv = NhanVienDAO.searchDSNhanVien_TheoMa(jTextField1.getText());
                loadDataNV(dsnv);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_luu;
    private javax.swing.JButton but_nhapanh;
    private javax.swing.JButton but_sua;
    private javax.swing.JButton but_them;
    private javax.swing.JButton but_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private CLASS.ImageAvatar imageAvatar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JRadioButton rdb_admin;
    private javax.swing.JRadioButton rdb_nam;
    private javax.swing.JRadioButton rdb_nhanvien;
    private javax.swing.JRadioButton rdb_nu;
    private javax.swing.JLabel ten_nhanvien;
    private javax.swing.JTextField txtAnh;
    private javax.swing.JTextField txt_dangnhap;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_idnv;
    private javax.swing.JTextField txt_ngaysinh;
    private javax.swing.JPasswordField txt_nhaplaipass;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_tennv;
    // End of variables declaration//GEN-END:variables

    private void loadtablenv() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (NhanVien nv : NhanVienDAO.getDSNhanVien()) {
            Object[] row = new Object[]{nv.getIdnv(), nv.getTennv(), nv.getNgaysinh(), nv.getSdt(), nv.getGioitinh(), nv.getEmail(), nv.getHinhanh(), nv.getTendn(), nv.getMatkhau(), nv.getQuyen()};
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.updateUI();
    }
}
