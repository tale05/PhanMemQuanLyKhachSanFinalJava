/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import DAO.PhongDAO;
import CLASS.Phong;
import DAO.KhachHangDAO;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class QuanLyPhong extends javax.swing.JFrame {

    Vector tblData = new Vector();
    Vector tblTitle = new Vector();
    DefaultTableModel tblModel;
    static ArrayList<Phong> ph = PhongDAO.getDSPhong();

    public QuanLyPhong() {
        initComponents();
        ten_nhanvien.setText(DangNhap.fullname);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(DangNhap.fileimage));
        addTitle();
        loadDataPH(ph);
        loadCboTenLoaiPhong();
        String name = (String) jComboBox1.getSelectedItem();
        txt_idloaiphong.setText(String.valueOf(PhongDAO.getIdLoaiPhong(name)));
    }

    private void addTitle() {
        tblTitle.add("ID Phòng");
        tblTitle.add("ID Loại Phòng");
        tblTitle.add("ID Tầng");
        tblTitle.add("Tên Phòng");
        tblTitle.add("Số Lượng Người");
        tblTitle.add("Trạng Thái");
    }

    private void loadDataPH(ArrayList<Phong> ph) {
        tblData.removeAllElements();
        for (Phong n : ph) {
            Vector v = new Vector();
            v.add(n.getPhong());
            v.add(n.getIdloaiphong());
            v.add(n.getTang());
            v.add(n.getTen());
            v.add(n.getSoluongnguoi());
            v.add(n.getTrangthai());
            tblData.add(v);
        }
        jTable1.setModel(new DefaultTableModel(tblData, tblTitle));
    }

    private void loadCboTenLoaiPhong() {
        DefaultComboBoxModel cbomodel = new DefaultComboBoxModel();
        ArrayList<String> lst = PhongDAO.getDsTenLoaiPhong();
        for (String str : lst) {
            cbomodel.addElement(str);
        }
        jComboBox1.setModel(cbomodel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        imageAvatar1 = new CLASS.ImageAvatar();
        ten_nhanvien = new javax.swing.JLabel();
        txt_idphong = new javax.swing.JTextField();
        txt_idloaiphong = new javax.swing.JTextField();
        txt_idtang = new javax.swing.JTextField();
        txt_tenphong = new javax.swing.JTextField();
        txt_soluongnguoi = new javax.swing.JTextField();
        but_them = new javax.swing.JButton();
        but_xoa = new javax.swing.JButton();
        but_sua = new javax.swing.JButton();
        but_luu = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txt_trangthai = new javax.swing.JComboBox<>();
        but_luu1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 64, 93));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Phòng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID loại phòng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID tầng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên Phòng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số Lượng Người");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Trạng Thái");

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

        ten_nhanvien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ten_nhanvien.setForeground(new java.awt.Color(255, 255, 255));
        ten_nhanvien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ten_nhanvien.setText("ABC");

        txt_idphong.setEditable(false);
        txt_idphong.setEnabled(false);

        txt_idloaiphong.setEditable(false);
        txt_idloaiphong.setEnabled(false);

        but_them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save.png"))); // NOI18N
        but_them.setText("LƯU");
        but_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_themActionPerformed(evt);
            }
        });

        but_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trash.png"))); // NOI18N
        but_xoa.setText("XÓA");
        but_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_xoaActionPerformed(evt);
            }
        });

        but_sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        but_sua.setText("SỬA");
        but_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_suaActionPerformed(evt);
            }
        });

        but_luu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        but_luu.setText("THÊM");
        but_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_luuActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên loại phòng");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        txt_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn trống", "Đang sử dụng" }));

        but_luu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        but_luu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logout.png"))); // NOI18N
        but_luu1.setText("QUAY LẠI");
        but_luu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_luu1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo mã phòng", "Theo tên phòng" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        jButton1.setText("TÌM KIẾM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(but_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(but_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(but_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(but_them, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(but_luu1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_idphong, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_idloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2))
                        .addGap(109, 109, 109)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_tenphong)
                            .addComponent(txt_soluongnguoi)
                            .addComponent(txt_trangthai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_idtang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ten_nhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(txt_idphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txt_idtang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(txt_idloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_soluongnguoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ten_nhanvien)
                    .addComponent(txt_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(but_luu)
                        .addComponent(but_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(but_sua, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(but_them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(but_luu1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        Phong ph = PhongDAO.getDSPhong().get(index);
        txt_idphong.setText(ph.getPhong());
        txt_idloaiphong.setText(ph.getIdloaiphong());
        txt_idtang.setText(ph.getTang());
        txt_tenphong.setText(ph.getTen());
        txt_soluongnguoi.setText(ph.getSoluongnguoi());
        txt_trangthai.setSelectedItem(ph.getTrangthai());
        jComboBox1.setSelectedItem(PhongDAO.getTenLoaiPhong(ph.getIdloaiphong()));

    }//GEN-LAST:event_jTable1MouseClicked
    private void loadtablenv() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Phong nv : PhongDAO.getDSPhong()) {
            Object[] row = new Object[]{nv.getPhong(), nv.getIdloaiphong(), nv.getTang(), nv.getTen(), nv.getSoluongnguoi(), nv.getTrangthai()};
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.updateUI();
    }
    private void but_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_themActionPerformed
        if (txt_tenphong.getText().isEmpty() && txt_idtang.getText().isEmpty() && txt_soluongnguoi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập đủ thông tin!", "Thông báo", 2);
        } else {
            Phong ph = new Phong();
            ph.setPhong(txt_idphong.getText());
            ph.setIdloaiphong(txt_idloaiphong.getText());
            ph.setTang(txt_idtang.getText());
            ph.setTen(txt_tenphong.getText());
            ph.setSoluongnguoi(txt_soluongnguoi.getText());
            ph.setTrangthai((String) txt_trangthai.getSelectedItem());
            PhongDAO.ThemNV(ph);
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            loadtablenv();
        }
    }//GEN-LAST:event_but_themActionPerformed

    private void but_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_xoaActionPerformed
        String id = txt_idphong.getText();
        PhongDAO.Xoaph(id);
        JOptionPane.showMessageDialog(this, "Xóa Thành Công");
        loadtablenv();
    }//GEN-LAST:event_but_xoaActionPerformed

    private void but_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_suaActionPerformed
        Phong ph = new Phong();
        ph.setPhong(txt_idphong.getText());
        ph.setIdloaiphong(txt_idloaiphong.getText());
        ph.setTang(txt_idtang.getText());
        ph.setTen(txt_tenphong.getText());
        ph.setSoluongnguoi(txt_soluongnguoi.getText());
        ph.setTrangthai((String) txt_trangthai.getSelectedItem());
        PhongDAO.Suanv(ph);
        JOptionPane.showMessageDialog(this, "Sửa Thành Công");
        loadtablenv();
    }//GEN-LAST:event_but_suaActionPerformed

    private void but_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_luuActionPerformed
        txt_idphong.setText("");
        txt_idloaiphong.setText("");
        txt_idtang.setText("");
        txt_tenphong.setText("");
        txt_soluongnguoi.setText("");
        txt_trangthai.setSelectedIndex(1);
        loadCboTenLoaiPhong();
    }//GEN-LAST:event_but_luuActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String name = (String) jComboBox1.getSelectedItem();
        txt_idloaiphong.setText(String.valueOf(PhongDAO.getIdLoaiPhong(name)));
    }//GEN-LAST:event_jComboBox1ActionPerformed

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

    private void but_luu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_luu1ActionPerformed
        Main frm = new Main();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_but_luu1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String str = (String) jComboBox2.getSelectedItem();
        if (str.equals("Theo mã phòng")) {
            if (jTextField1.getText().trim().isEmpty()) {
                ph = PhongDAO.getDSPhong();
                loadDataPH(ph);
            } else {
                ph = PhongDAO.searchTheoMaPhong(jTextField1.getText().trim());
                loadDataPH(ph);
            }
        } else if (str.equals("Theo tên phòng")) {
            if (jTextField1.getText().trim().isEmpty()) {
                ph = PhongDAO.getDSPhong();
                loadDataPH(ph);
            } else {
                ph = PhongDAO.searchTheoTenPhong(jTextField1.getText().trim());
                loadDataPH(ph);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_luu;
    private javax.swing.JButton but_luu1;
    private javax.swing.JButton but_sua;
    private javax.swing.JButton but_them;
    private javax.swing.JButton but_xoa;
    private CLASS.ImageAvatar imageAvatar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel ten_nhanvien;
    private javax.swing.JTextField txt_idloaiphong;
    private javax.swing.JTextField txt_idphong;
    private javax.swing.JTextField txt_idtang;
    private javax.swing.JTextField txt_soluongnguoi;
    private javax.swing.JTextField txt_tenphong;
    private javax.swing.JComboBox<String> txt_trangthai;
    // End of variables declaration//GEN-END:variables
}
