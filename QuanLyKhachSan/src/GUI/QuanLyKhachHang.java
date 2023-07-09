package GUI;

import DAO.KhachHangDAO;
import CLASS.KhachHang;
import DAO.NhanVienDAO;
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

public class QuanLyKhachHang extends javax.swing.JFrame {

    Vector tblData = new Vector();
    Vector tblTitle = new Vector();
    DefaultTableModel tblModel;
    static ArrayList<KhachHang> dskh = KhachHangDAO.getDSKhachHang();

    public QuanLyKhachHang() {
        initComponents();
        ten_nhanvien.setText(DangNhap.fullname);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(DangNhap.fileimage));
        addTitle();
        loadDataKH(dskh);
    }

    private void addTitle() {
        tblTitle.add("ID Khách Hàng");
        tblTitle.add("Tên Khách Hàng");
        tblTitle.add("Ngày Sinh");
        tblTitle.add("Địa Chỉ");
        tblTitle.add("Số Điện Thoại");
        tblTitle.add("CMND");
        tblTitle.add("Giới Tính");
    }

    private void loadDataKH(ArrayList<KhachHang> ds) {
        tblData.removeAllElements();
        for (KhachHang n : ds) {
            Vector v = new Vector();
            v.add(n.getIdkh());
            v.add(n.getTenkh());
            v.add(n.getNgaysinh());
            v.add(n.getDiachi());
            v.add(n.getSdt());
            v.add(n.getCmnd());
            v.add(n.getGioitinh());
            tblData.add(v);
        }
        jTable1.setModel(new DefaultTableModel(tblData, tblTitle));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        a = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_idkh = new javax.swing.JTextField();
        txt_tenkh = new javax.swing.JTextField();
        txt_ngaysinh = new javax.swing.JTextField();
        txt_diachi = new javax.swing.JTextField();
        txt_sodienthoai = new javax.swing.JTextField();
        txt_cmnd = new javax.swing.JTextField();
        rdb_nam = new javax.swing.JRadioButton();
        rdb_nu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        but_them = new javax.swing.JButton();
        but_xoa = new javax.swing.JButton();
        but_sua = new javax.swing.JButton();
        but_luu = new javax.swing.JButton();
        but_back = new javax.swing.JButton();
        imageAvatar1 = new CLASS.ImageAvatar();
        ten_nhanvien = new javax.swing.JLabel();
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

        a.setBackground(new java.awt.Color(0, 64, 93));
        a.setForeground(new java.awt.Color(0, 64, 93));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Khách Hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên Khách Hàng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày Sinh");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Địa Chỉ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số Điện Thoại");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CMND");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Giới Tính");

        txt_idkh.setEnabled(false);

        txt_sodienthoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_sodienthoaiKeyTyped(evt);
            }
        });

        txt_cmnd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cmndKeyTyped(evt);
            }
        });

        rdb_nam.setBackground(new java.awt.Color(255, 255, 0));
        buttonGroup1.add(rdb_nam);
        rdb_nam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdb_nam.setForeground(new java.awt.Color(255, 255, 255));
        rdb_nam.setText("NAM");

        rdb_nu.setBackground(new java.awt.Color(255, 255, 0));
        buttonGroup1.add(rdb_nu);
        rdb_nu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdb_nu.setForeground(new java.awt.Color(255, 255, 255));
        rdb_nu.setText("NỮ");

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

        but_them.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        but_them.setForeground(new java.awt.Color(255, 0, 0));
        but_them.setText("LƯU");
        but_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_themActionPerformed(evt);
            }
        });

        but_xoa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        but_xoa.setForeground(new java.awt.Color(255, 0, 0));
        but_xoa.setText("XÓA");
        but_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_xoaActionPerformed(evt);
            }
        });

        but_sua.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        but_sua.setForeground(new java.awt.Color(255, 51, 0));
        but_sua.setText("SỬA");
        but_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_suaActionPerformed(evt);
            }
        });

        but_luu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        but_luu.setForeground(new java.awt.Color(255, 0, 0));
        but_luu.setText("THÊM");
        but_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_luuActionPerformed(evt);
            }
        });

        but_back.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        but_back.setForeground(new java.awt.Color(255, 0, 0));
        but_back.setText("QUAY LẠI");
        but_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_backActionPerformed(evt);
            }
        });

        ten_nhanvien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ten_nhanvien.setForeground(new java.awt.Color(255, 255, 255));
        ten_nhanvien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ten_nhanvien.setText("abc");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo tên", "Theo mã" }));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 255, 0));
        jButton2.setText("TÌM KIẾM");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aLayout = new javax.swing.GroupLayout(a);
        a.setLayout(aLayout);
        aLayout.setHorizontalGroup(
            aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aLayout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(aLayout.createSequentialGroup()
                        .addGap(0, 47, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, aLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(29, 29, 29)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_idkh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38))
                            .addGroup(aLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(but_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(but_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)))
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(57, 57, 57)
                                .addComponent(txt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(aLayout.createSequentialGroup()
                                    .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(61, 61, 61)))
                                    .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_cmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(29, 29, 29))
                                .addGroup(aLayout.createSequentialGroup()
                                    .addComponent(but_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(66, 66, 66)
                                    .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(but_them, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jLabel7))
                                .addGap(19, 19, 19)
                                .addComponent(rdb_nam)
                                .addGap(26, 26, 26)
                                .addComponent(rdb_nu))
                            .addGroup(aLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(but_back)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ten_nhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(44, 44, 44))
        );
        aLayout.setVerticalGroup(
            aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aLayout.createSequentialGroup()
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aLayout.createSequentialGroup()
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_idkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(rdb_nam)
                                    .addComponent(rdb_nu))
                                .addGap(49, 49, 49)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(txt_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_cmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(but_xoa)
                            .addComponent(but_them)
                            .addComponent(but_sua)
                            .addComponent(but_back)
                            .addComponent(but_luu))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ten_nhanvien)
                        .addGap(55, 55, 55)))
                .addGroup(aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(a, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(a, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        KhachHang nv = KhachHangDAO.getDSKhachHang().get(index);
        txt_idkh.setText(nv.getIdkh());
        txt_tenkh.setText(nv.getTenkh());
        txt_ngaysinh.setText(nv.getNgaysinh());
        txt_diachi.setText(nv.getDiachi());
        txt_sodienthoai.setText(nv.getSdt());
        txt_cmnd.setText(nv.getCmnd());
        String gioitinh = nv.getGioitinh();
        if ("Nam".equals(gioitinh)) {
            rdb_nam.setSelected(true);
        } else {
            rdb_nu.setSelected(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void but_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_themActionPerformed
        if (txt_idkh.getText().isEmpty() && txt_tenkh.getText().isEmpty() && txt_ngaysinh.getText().isEmpty() && txt_diachi.getText().isEmpty()
                && txt_sodienthoai.getText().isEmpty() && txt_cmnd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập đầy đủ thông tin !", "Thông báo", 2);
        } else {
            if (!ktNhapNgay1(txt_ngaysinh.getText())) {
                JOptionPane.showMessageDialog(this, "Phải nhập đúng định dạng: dd/MM/yyyy !", "Thông báo", 2);
            } else {
                KhachHang kh = new KhachHang();
                kh.setIdkh(txt_idkh.getText());
                kh.setTenkh(txt_tenkh.getText());
                kh.setNgaysinh(txt_ngaysinh.getText());
                kh.setDiachi(txt_diachi.getText());
                kh.setSdt(txt_sodienthoai.getText());
                kh.setCmnd(txt_cmnd.getText());
                String gioitinh = kh.getGioitinh();
                if (rdb_nam.isSelected()) {
                    kh.setGioitinh("Nam");
                } else {
                    kh.setGioitinh("Nữ");
                }
                KhachHangDAO.ThemKH(kh);
                JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                loadtablekh();
                txt_idkh.setText("");
                txt_tenkh.setText("");
                txt_ngaysinh.setText("");
                txt_diachi.setText("");
                txt_sodienthoai.setText("");
                txt_cmnd.setText("");
                rdb_nam.setSelected(true);
            }
        }
    }//GEN-LAST:event_but_themActionPerformed

    private void but_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_xoaActionPerformed
        String id = txt_idkh.getText();
        String sql = "delete from khachhang where id_khachhang = '"+txt_idkh.getText()+"'";
        KhachHangDAO.excuteSQL(sql);
        JOptionPane.showMessageDialog(this, "Xóa Thành Công");
        dskh = KhachHangDAO.getDSKhachHang();
        loadDataKH(dskh);
        
        
        txt_idkh.setText("");
        txt_tenkh.setText("");
        txt_ngaysinh.setText("");
        txt_diachi.setText("");
        txt_sodienthoai.setText("");
        txt_cmnd.setText("");
        rdb_nam.setSelected(true);
    }//GEN-LAST:event_but_xoaActionPerformed

    private void but_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_suaActionPerformed
        KhachHang kh = new KhachHang();
        kh.setIdkh(txt_idkh.getText());
        kh.setTenkh(txt_tenkh.getText());
        kh.setNgaysinh(txt_ngaysinh.getText());
        kh.setDiachi(txt_diachi.getText());
        kh.setSdt(txt_sodienthoai.getText());
        kh.setCmnd(txt_cmnd.getText());
        String gioitinh = kh.getGioitinh();
        if (rdb_nam.isSelected()) {
            kh.setGioitinh("Nam");
        } else {
            kh.setGioitinh("Nữ");
        }
        KhachHangDAO.Suakh(kh);
        JOptionPane.showMessageDialog(this, "Sửa Thành Công");
        loadtablekh();
        
        
        txt_idkh.setText("");
        txt_tenkh.setText("");
        txt_ngaysinh.setText("");
        txt_diachi.setText("");
        txt_sodienthoai.setText("");
        txt_cmnd.setText("");
        rdb_nam.setSelected(true);
    }//GEN-LAST:event_but_suaActionPerformed

    private void but_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_luuActionPerformed
        txt_idkh.setText("");
        txt_tenkh.setText("");
        txt_ngaysinh.setText("");
        txt_diachi.setText("");
        txt_sodienthoai.setText("");
        txt_cmnd.setText("");
        rdb_nam.setSelected(true);
        txt_tenkh.requestFocus();
    }//GEN-LAST:event_but_luuActionPerformed

    private void but_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_backActionPerformed
        Main frm = new Main();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_but_backActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String chon = (String) jComboBox1.getSelectedItem();
        if (chon.equals("Theo tên")) {
            if (jTextField1.getText().isEmpty()) {
                dskh = KhachHangDAO.getDSKhachHang();
                loadDataKH(dskh);
            } else {
                dskh = KhachHangDAO.searchDSKhachHang_TheoTen(jTextField1.getText());
                loadDataKH(dskh);
            }
        } else if (chon.equals("Theo mã")) {
            if (jTextField1.getText().isEmpty()) {
                dskh = KhachHangDAO.getDSKhachHang();
                loadDataKH(dskh);
            } else {
                dskh = KhachHangDAO.searchDSNhanVien_TheoMa(jTextField1.getText());
                loadDataKH(dskh);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_sodienthoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sodienthoaiKeyTyped
        if (evt.getKeyChar() < '0' || evt.getKeyChar() > '9')
            evt.consume();
    }//GEN-LAST:event_txt_sodienthoaiKeyTyped

    private void txt_cmndKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cmndKeyTyped
        if (evt.getKeyChar() < '0' || evt.getKeyChar() > '9')
            evt.consume();
    }//GEN-LAST:event_txt_cmndKeyTyped

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

    private void loadtablekh() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (KhachHang nv : KhachHangDAO.getDSKhachHang()) {
            Object[] row = new Object[]{nv.getIdkh(), nv.getTenkh(), nv.getNgaysinh(), nv.getDiachi(), nv.getSdt(), nv.getCmnd()};
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.updateUI();
    }

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
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel a;
    private javax.swing.JButton but_back;
    private javax.swing.JButton but_luu;
    private javax.swing.JButton but_sua;
    private javax.swing.JButton but_them;
    private javax.swing.JButton but_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private CLASS.ImageAvatar imageAvatar1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rdb_nam;
    private javax.swing.JRadioButton rdb_nu;
    private javax.swing.JLabel ten_nhanvien;
    private javax.swing.JTextField txt_cmnd;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_idkh;
    private javax.swing.JTextField txt_ngaysinh;
    private javax.swing.JTextField txt_sodienthoai;
    private javax.swing.JTextField txt_tenkh;
    // End of variables declaration//GEN-END:variables
}
