package GUI;

import DAO.ConnectionDB;
import DAO.MainDAO;
import DAO.PhongDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        showListRoom();
        setHoverButtonAndDesign();
        btnHoaDonDatPhong.setVisible(false);
        btnHoaDonDichVu.setVisible(false);
        btnHoaDonThietBi.setVisible(false);
        btnQuayLai.setVisible(false);

        lblUsername.setText(DangNhap.fullname);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(DangNhap.fileimage));
        if (DangNhap.role == 1) {
            btnQLNhanVien.setVisible(true);
            btnThongKe.setVisible(true);
            lblUsername1.setText("Admin");
        } else if (DangNhap.role == 0) {
            btnQLNhanVien.setVisible(false);
            btnThongKe.setVisible(false);
            lblUsername1.setText("Receptionists");
        }
    }

    public static String selectedRoomId = "";
    public static String selectedRoomName = "";
    public static String selectedStatus = "";

    public void showListRoom() {
        jPanel2.setLayout(new GridLayout(3, 6, 10, 10));
        try {
            JPopupMenu popupMenu = new JPopupMenu();

            JMenuItem datphong = new JMenuItem("Đặt phòng");
            JMenuItem traphong = new JMenuItem("Chi tiết đặt phòng");
            JMenuItem dondep_khongcheckin = new JMenuItem("Dọn dẹp phòng");
            JMenuItem dondep_dangcheckin = new JMenuItem("Dọn dẹp phòng");
            JMenuItem hoantat_khongcheckin = new JMenuItem("Hủy dọn phòng");
            JMenuItem hoantat_dangcheckin = new JMenuItem("Hủy dọn phòng");
            popupMenu.add(datphong);
            popupMenu.add(traphong);
            popupMenu.add(dondep_khongcheckin);
            popupMenu.add(dondep_dangcheckin);
            popupMenu.add(hoantat_khongcheckin);
            popupMenu.add(hoantat_dangcheckin);

            datphong.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DatPhong frm = new DatPhong();
                    frm.setVisible(true);
                    setVisible(false);
                }
            });

            traphong.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DatPhong frm = new DatPhong();
                    frm.setVisible(true);
                    setVisible(false);
                }
            });

            dondep_khongcheckin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String sql = "update phong set trang_thai = N'Đang dọn dẹp' where id_phong = '" + selectedRoomId + "'";
                    MainDAO.excuteSQL(sql);
                    Main frm = new Main();
                    frm.setVisible(true);
                    setVisible(false);
                }
            });

            dondep_dangcheckin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String sql = "update phong set trang_thai = N'Đang dọn dẹp-used' where id_phong = '" + selectedRoomId + "'";
                    MainDAO.excuteSQL(sql);
                    Main frm = new Main();
                    frm.setVisible(true);
                    setVisible(false);
                }
            });

            hoantat_khongcheckin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String sql = "update phong set trang_thai = N'Còn trống' where id_phong = '" + selectedRoomId + "'";
                    MainDAO.excuteSQL(sql);
                    Main frm = new Main();
                    frm.setVisible(true);
                    setVisible(false);
                }
            });

            hoantat_dangcheckin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String sql = "update phong set trang_thai = N'Đang sử dụng' where id_phong = '" + selectedRoomId + "'";
                    MainDAO.excuteSQL(sql);
                    Main frm = new Main();
                    frm.setVisible(true);
                    setVisible(false);
                }
            });

            ConnectionDB conn = new ConnectionDB();
            conn.getCn();
            ResultSet rs = conn.executeQuery("SELECT ten, trang_thai, id_phong FROM phong ");

            while (rs.next()) {
                String roomId = rs.getString(3);
                String trangthai = rs.getString(2);
                String ten = rs.getString(1);
                JButton room = new JButton("<html>" + roomId + "<br>" + ten + "<br><br>" + trangthai + "</html>");
                //JButton room = new JButton(roomId);
                Font font = new Font("Arial", Font.CENTER_BASELINE, 18);
                room.setFont(font);
                room.setHorizontalAlignment(JButton.CENTER);
                room.setVerticalAlignment(JButton.CENTER);

                if (rs.getString(2).equals("Còn trống")) {
                    room.setBackground(Color.decode("#33FF66"));
                    room.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }

                        public void mouseReleased(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                datphong.setVisible(true);
                                traphong.setVisible(false);
                                dondep_khongcheckin.setVisible(true);
                                dondep_dangcheckin.setVisible(false);
                                hoantat_khongcheckin.setVisible(false);
                                hoantat_dangcheckin.setVisible(false);
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                                selectedRoomId = room.getText().trim().substring(6, 10);
                                selectedRoomName = room.getText().trim().substring(14, 23);
                                selectedStatus = room.getText().trim().substring(31, 40);
                            }
                        }
                    });
                } else if (rs.getString(2).equals("Đang sử dụng")) {
                    room.setBackground(Color.decode("#FF3300"));
                    room.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }

                        public void mouseReleased(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                datphong.setVisible(false);
                                traphong.setVisible(true);
                                dondep_khongcheckin.setVisible(false);
                                dondep_dangcheckin.setVisible(true);
                                hoantat_khongcheckin.setVisible(false);
                                hoantat_dangcheckin.setVisible(false);
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                                selectedRoomId = room.getText().trim().substring(6, 10);
                                selectedRoomName = room.getText().trim().substring(14, 23);
                                selectedStatus = room.getText().trim().substring(31, 43);
                            }
                        }
                    });
                } else if (rs.getString(2).equals("Đang dọn dẹp")) {
                    room.setBackground(Color.decode("#FFFF00"));
                    //room.setIcon(new javax.swing.ImageIcon("used.png"));
                    room.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }

                        public void mouseReleased(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                datphong.setVisible(false);
                                traphong.setVisible(false);
                                dondep_khongcheckin.setVisible(false);
                                dondep_dangcheckin.setVisible(false);
                                hoantat_khongcheckin.setVisible(true);
                                hoantat_dangcheckin.setVisible(false);
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                                selectedRoomId = room.getText().trim().substring(6, 10);
                                selectedRoomName = room.getText().trim().substring(14, 23);
                                selectedStatus = room.getText().trim().substring(31, 43);
                            }
                        }
                    });
                } else if (rs.getString(2).equals("Đang dọn dẹp-used")) {
                    room.setBackground(Color.decode("#FFFF00"));
                    //room.setIcon(new javax.swing.ImageIcon("used.png"));
                    room.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }

                        public void mouseReleased(MouseEvent e) {
                            if (e.isPopupTrigger()) {
                                datphong.setVisible(false);
                                traphong.setVisible(false);
                                dondep_khongcheckin.setVisible(false);
                                dondep_dangcheckin.setVisible(false);
                                hoantat_khongcheckin.setVisible(false);
                                hoantat_dangcheckin.setVisible(true);
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                                selectedRoomId = room.getText().trim().substring(6, 10);
                                selectedRoomName = room.getText().trim().substring(14, 23);
                                selectedStatus = room.getText().trim().substring(31, 43);
                            }
                        }
                    });
                }
                jPanel2.add(room);
            }

            rs.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnQLKhachHang = new javax.swing.JLabel();
        btnQLPhong = new javax.swing.JLabel();
        btnQLDichVu = new javax.swing.JLabel();
        btnQLThietBi = new javax.swing.JLabel();
        btnQLNhanVien = new javax.swing.JLabel();
        btnThongKe = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnThoat = new javax.swing.JLabel();
        imageAvatar1 = new CLASS.ImageAvatar();
        btnHoaDonDatPhong = new javax.swing.JLabel();
        btnHoaDonDichVu = new javax.swing.JLabel();
        btnHoaDonThietBi = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JLabel();
        btnDoiMatKhau = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblUsername1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 64, 93));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnQLKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQLKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnQLKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/person.png"))); // NOI18N
        btnQLKhachHang.setText("  Quản lý khách hàng");
        btnQLKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLKhachHangMouseClicked(evt);
            }
        });
        jPanel1.add(btnQLKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 320, 60));

        btnQLPhong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQLPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnQLPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/bed.png"))); // NOI18N
        btnQLPhong.setText("  Quản lý phòng");
        btnQLPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLPhongMouseClicked(evt);
            }
        });
        jPanel1.add(btnQLPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 320, 60));

        btnQLDichVu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQLDichVu.setForeground(new java.awt.Color(255, 255, 255));
        btnQLDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/customer-service.png"))); // NOI18N
        btnQLDichVu.setText("  Quản lý dịch vụ");
        btnQLDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLDichVuMouseClicked(evt);
            }
        });
        jPanel1.add(btnQLDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 320, 60));

        btnQLThietBi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQLThietBi.setForeground(new java.awt.Color(255, 255, 255));
        btnQLThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home-appliance.png"))); // NOI18N
        btnQLThietBi.setText("  Quản lý thiết bị");
        btnQLThietBi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLThietBiMouseClicked(evt);
            }
        });
        jPanel1.add(btnQLThietBi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 320, 60));

        btnQLNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQLNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnQLNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/team.png"))); // NOI18N
        btnQLNhanVien.setText("  Quản lý nhân viên");
        btnQLNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQLNhanVienMouseClicked(evt);
            }
        });
        jPanel1.add(btnQLNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 320, 60));

        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/data-analysis.png"))); // NOI18N
        btnThongKe.setText("  Thống kê doanh thu");
        btnThongKe.setRequestFocusEnabled(false);
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
        });
        jPanel1.add(btnThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 320, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome back,");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 25, -1, -1));

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("abc");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 260, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, 262, 20));

        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/emergency-exit.png"))); // NOI18N
        btnThoat.setText("  Thoát");
        btnThoat.setRequestFocusEnabled(false);
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });
        jPanel1.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 800, 320, 60));
        jPanel1.add(imageAvatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 320, 140));

        btnHoaDonDatPhong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHoaDonDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/data-analysis.png"))); // NOI18N
        btnHoaDonDatPhong.setText("  Quản lý hóa đơn đặt phòng");
        btnHoaDonDatPhong.setRequestFocusEnabled(false);
        btnHoaDonDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonDatPhongMouseClicked(evt);
            }
        });
        jPanel1.add(btnHoaDonDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 320, 60));

        btnHoaDonDichVu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHoaDonDichVu.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/data-analysis.png"))); // NOI18N
        btnHoaDonDichVu.setText("  Quản lý hóa đơn dịch vụ");
        btnHoaDonDichVu.setRequestFocusEnabled(false);
        btnHoaDonDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonDichVuMouseClicked(evt);
            }
        });
        jPanel1.add(btnHoaDonDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 320, 60));

        btnHoaDonThietBi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHoaDonThietBi.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/data-analysis.png"))); // NOI18N
        btnHoaDonThietBi.setText("  Quản lý hóa đơn thiết bị");
        btnHoaDonThietBi.setRequestFocusEnabled(false);
        btnHoaDonThietBi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonThietBiMouseClicked(evt);
            }
        });
        jPanel1.add(btnHoaDonThietBi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 320, 60));

        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/back.png"))); // NOI18N
        btnQuayLai.setText("  Quay lại");
        btnQuayLai.setRequestFocusEnabled(false);
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLaiMouseClicked(evt);
            }
        });
        jPanel1.add(btnQuayLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 800, 320, 60));

        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lock.png"))); // NOI18N
        btnDoiMatKhau.setText("  Đổi mật khẩu");
        btnDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseClicked(evt);
            }
        });
        jPanel1.add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 320, 60));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 262, 20));

        lblUsername1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername1.setText("abc");
        jPanel1.add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 260, -1));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 860));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 27, 980, 800));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            DangNhap frm = new DangNhap();
            frm.setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_btnThoatMouseClicked

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        btnQLDichVu.setVisible(false);
        btnQLKhachHang.setVisible(false);
        btnQLNhanVien.setVisible(false);
        btnQLPhong.setVisible(false);
        btnQLThietBi.setVisible(false);
        btnThongKe.setVisible(false);
        btnThoat.setVisible(false);
        btnHoaDonDatPhong.setVisible(true);
        btnHoaDonDichVu.setVisible(true);
        btnHoaDonThietBi.setVisible(true);
        btnQuayLai.setVisible(true);
    }//GEN-LAST:event_btnThongKeMouseClicked

    private void btnQLNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLNhanVienMouseClicked
        QuanLyNhanVien frm = new QuanLyNhanVien();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnQLNhanVienMouseClicked

    private void btnQLThietBiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLThietBiMouseClicked
        QuanLyThietBi frm = new QuanLyThietBi();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnQLThietBiMouseClicked

    private void btnQLDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLDichVuMouseClicked
        QuanLyDichVu frm = new QuanLyDichVu();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnQLDichVuMouseClicked

    private void btnQLPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLPhongMouseClicked
        QuanLyPhong frm = new QuanLyPhong();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnQLPhongMouseClicked

    private void btnQLKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLKhachHangMouseClicked
        QuanLyKhachHang frm = new QuanLyKhachHang();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnQLKhachHangMouseClicked

    private void btnHoaDonDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonDatPhongMouseClicked
        HoaDonDatPhong frm = new HoaDonDatPhong();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnHoaDonDatPhongMouseClicked

    private void btnHoaDonDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonDichVuMouseClicked
        HoaDonDichVu frm = new HoaDonDichVu();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnHoaDonDichVuMouseClicked

    private void btnHoaDonThietBiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonThietBiMouseClicked
        HoaDonThietBi frm = new HoaDonThietBi();
        frm.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnHoaDonThietBiMouseClicked

    private void btnQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMouseClicked
        btnQLDichVu.setVisible(true);
        btnQLKhachHang.setVisible(true);
        btnQLNhanVien.setVisible(true);
        btnQLPhong.setVisible(true);
        btnQLThietBi.setVisible(true);
        btnThongKe.setVisible(true);
        btnThoat.setVisible(true);

        btnHoaDonDatPhong.setVisible(false);
        btnHoaDonDichVu.setVisible(false);
        btnHoaDonThietBi.setVisible(false);
        btnQuayLai.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiMouseClicked

    private void btnDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseClicked
        DoiMatKhau frm = new DoiMatKhau(this, rootPaneCheckingEnabled);
        frm.setVisible(true);
    }//GEN-LAST:event_btnDoiMatKhauMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private Color chooseColor = Color.decode("#006699");

    private void setHoverButtonAndDesign() {
        btnQLKhachHang.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnQLDichVu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnQLNhanVien.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnQLPhong.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnQLThietBi.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnQuayLai.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnThoat.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnHoaDonDatPhong.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnHoaDonDichVu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnHoaDonThietBi.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnThongKe.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnDoiMatKhau.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        btnQLKhachHang.setOpaque(true);
        btnQLKhachHang.setBackground(Color.decode("#00405d"));
        btnQLKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnQLKhachHang.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLKhachHang.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnQLKhachHang.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLKhachHang.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnQLKhachHang.setBackground(Color.decode("#99FFCC"));
                btnQLKhachHang.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnQLPhong.setOpaque(true);
        btnQLPhong.setBackground(Color.decode("#00405d"));
        btnQLPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnQLPhong.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLPhong.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnQLPhong.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLPhong.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnQLPhong.setBackground(Color.decode("#99FFCC"));
                btnQLPhong.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnQLDichVu.setOpaque(true);
        btnQLDichVu.setBackground(Color.decode("#00405d"));
        btnQLDichVu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnQLDichVu.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLDichVu.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnQLDichVu.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLDichVu.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnQLDichVu.setBackground(Color.decode("#99FFCC"));
                btnQLDichVu.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnQLThietBi.setOpaque(true);
        btnQLThietBi.setBackground(Color.decode("#00405d"));
        btnQLThietBi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnQLThietBi.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLThietBi.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnQLThietBi.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLThietBi.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnQLThietBi.setBackground(Color.decode("#99FFCC"));
                btnQLThietBi.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnQLNhanVien.setOpaque(true);
        btnQLNhanVien.setBackground(Color.decode("#00405d"));
        btnQLNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnQLNhanVien.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLNhanVien.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnQLNhanVien.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQLNhanVien.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnQLNhanVien.setBackground(Color.decode("#99FFCC"));
                btnQLNhanVien.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnThongKe.setOpaque(true);
        btnThongKe.setBackground(Color.decode("#00405d"));
        btnThongKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnThongKe.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnThongKe.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnThongKe.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnThongKe.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnThongKe.setBackground(Color.decode("#99FFCC"));
                btnThongKe.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnHoaDonDatPhong.setOpaque(true);
        btnHoaDonDatPhong.setBackground(Color.decode("#00405d"));
        btnHoaDonDatPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnHoaDonDatPhong.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnHoaDonDatPhong.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnHoaDonDatPhong.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnHoaDonDatPhong.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnHoaDonDatPhong.setBackground(Color.decode("#99FFCC"));
                btnHoaDonDatPhong.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnHoaDonDichVu.setOpaque(true);
        btnHoaDonDichVu.setBackground(Color.decode("#00405d"));
        btnHoaDonDichVu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnHoaDonDichVu.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnHoaDonDichVu.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnHoaDonDichVu.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnHoaDonDichVu.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnHoaDonDichVu.setBackground(Color.decode("#99FFCC"));
                btnHoaDonDichVu.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnHoaDonThietBi.setOpaque(true);
        btnHoaDonThietBi.setBackground(Color.decode("#00405d"));
        btnHoaDonThietBi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnHoaDonThietBi.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnHoaDonThietBi.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnHoaDonThietBi.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnHoaDonThietBi.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnHoaDonThietBi.setBackground(Color.decode("#99FFCC"));
                btnHoaDonThietBi.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnDoiMatKhau.setOpaque(true);
        btnDoiMatKhau.setBackground(Color.decode("#00405d"));
        btnDoiMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnDoiMatKhau.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnDoiMatKhau.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnDoiMatKhau.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnDoiMatKhau.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnDoiMatKhau.setBackground(Color.decode("#99FFCC"));
                btnDoiMatKhau.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnQuayLai.setOpaque(true);
        btnQuayLai.setBackground(Color.decode("#00405d"));
        btnQuayLai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnQuayLai.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQuayLai.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnQuayLai.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnQuayLai.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnQuayLai.setBackground(Color.decode("#99FFCC"));
                btnQuayLai.setBackground(Color.decode("#00405d"));
            }
        });
        //---------------------------------------------------------------------------------
        btnThoat.setOpaque(true);
        btnThoat.setBackground(Color.decode("#00405d"));
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnThoat.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnThoat.setBackground(chooseColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnThoat.getBackground().equals(Color.decode("#99FFCC"))) {
                    btnThoat.setBackground(Color.decode("#00405d"));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnThoat.setBackground(Color.decode("#99FFCC"));
                btnThoat.setBackground(Color.decode("#00405d"));
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDoiMatKhau;
    private javax.swing.JLabel btnHoaDonDatPhong;
    private javax.swing.JLabel btnHoaDonDichVu;
    private javax.swing.JLabel btnHoaDonThietBi;
    private javax.swing.JLabel btnQLDichVu;
    private javax.swing.JLabel btnQLKhachHang;
    private javax.swing.JLabel btnQLNhanVien;
    private javax.swing.JLabel btnQLPhong;
    private javax.swing.JLabel btnQLThietBi;
    private javax.swing.JLabel btnQuayLai;
    private javax.swing.JLabel btnThoat;
    private javax.swing.JLabel btnThongKe;
    private CLASS.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    // End of variables declaration//GEN-END:variables
}
