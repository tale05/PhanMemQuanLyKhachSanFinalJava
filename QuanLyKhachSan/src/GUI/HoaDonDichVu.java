package GUI;

import CLASS.ChiTietSDDichVu;
import DAO.CTSuDungDichVuDAO;
import DAO.HoaDonDatPhongDAO;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument;

public class HoaDonDichVu extends javax.swing.JFrame {

    Vector title = new Vector();
    Vector data = new Vector();
    ArrayList<ChiTietSDDichVu> ds = CTSuDungDichVuDAO.getData();

    public HoaDonDichVu() {
        initComponents();
        addTitle();
        addData(ds);
        jComboBox2.setSelectedIndex(0);
    }

    private void addTitle() {
        title.add("ID Hoá Đơn");
        title.add("ID Dịch Vụ");
        title.add("Ngày Thuê");
        title.add("Số Lượng");
        title.add("Tổng Tiền DV");
    }

    private void addData(ArrayList<ChiTietSDDichVu> ds) {
        data.removeAllElements();
        for (ChiTietSDDichVu c : ds) {
            Vector v = new Vector();
            v.add(c.getMahd());
            v.add(c.getMadv());
            v.add(c.getNgay());
            v.add(c.getSoluong());
            v.add(c.getTongtien());
            data.add(v);
        }
        jTable1.setModel(new DefaultTableModel(data, title));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTongDv = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDoanhThuDv = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/back.png"))); // NOI18N
        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn tháng thống kê", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/show.png"))); // NOI18N
        jButton2.setText("Xem Trước");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Tổng số hóa đơn thống kê được là: ");

        lblTongDv.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Tổng tiền thống kê được là:");

        lblDoanhThuDv.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jScrollPane2.setViewportView(jTextPane1);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/print.png"))); // NOI18N
        jButton3.setText("In Hóa Đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 64, 93));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Thống kê hóa đơn dịch vụ:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTongDv, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(lblDoanhThuDv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTongDv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDoanhThuDv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jButton1))))
                .addContainerGap(30, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Main frm = new Main();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String str = (String) jComboBox2.getSelectedItem();
        String month = str.substring(str.indexOf(" ") + 1);
        DecimalFormat df = new DecimalFormat("###,###");
        switch (jComboBox2.getSelectedIndex()) {
            case 0 -> {
                lblTongDv.setText("0 dịch vụ");
                lblDoanhThuDv.setText("0.0 VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getData();
                addData(ds);
            }
            case 1 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 2 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 3 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 4 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 5 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 6 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 7 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 8 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 9 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 10 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 11 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
            case 12 -> {
                lblTongDv.setText(String.valueOf(CTSuDungDichVuDAO.countHoaDonTheoThang(month)) + " dịch vụ");
                lblDoanhThuDv.setText(String.valueOf(df.format(CTSuDungDichVuDAO.sumTongTienDichVuTheoThang(month))) + " VNĐ");
                jTextPane1.setText("");
                ds = CTSuDungDichVuDAO.getDataTheoThang(month);
                addData(ds);
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        df.applyPattern("#,###");
        jTextPane1.setContentType("text/html");
        HTMLDocument htmlDocument = (HTMLDocument) jTextPane1.getDocument();
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();

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
                + "            font-size: large;\n"
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
                + "		<h1 style=\"text-align: center;\">********** THỐNG KÊ HÓA ĐƠN DỊCH VỤ **********</h1>\n"
                + "    </div>\n"
                + "	<table>\n"
                + "		<tbody>\n"
                + "			<tr>\n"
                + "				<td>Thời gian hiện tại:</td>\n"
                + "				<td>&nbsp &nbsp &nbsp " + day + "/" + month + "/" + year + "</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Tên nhân viên lập thống kê: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp " + DangNhap.fullname + "</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Chức vụ: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp Quản lý </td>\n"
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
                + "                <th>Mã DV</th>\n"
                + "                <th>Ngày Thuê</th>\n"
                + "                <th>Số Lượng</th>\n"
                + "                <th>Tổng Tiền DV</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";

        for (ChiTietSDDichVu dsct : ds) {
            htmlContent += "            <tr>\n"
                    + "                <td>" + dsct.getMahd().trim() + "</td>\n"
                    + "                <td>" + dsct.getMadv().trim() + "</td>\n"
                    + "                <td>" + dsct.getNgay().trim() + "</td>\n"
                    + "                <td style=\"text-align: right\">" + dsct.getSoluong() + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getTongtien()) + "</td>\n"
                    + "            </tr>\n";
        }

        if (!(jComboBox2.getSelectedIndex() == 0)) {
            htmlContent += " </table>\n"
                    + "<br><br><strong>Tổng hóa đơn sử dụng dịch vụ là: <b>" + lblTongDv.getText() + "</b></strong>"
                    + "<br><br><strong>Tổng doanh thu sử dụng dịch vụ là: <b>" + lblDoanhThuDv.getText() + "</b></strong>"
                    + "<br><br><br><br>"
                    + "<div style=\"text-align: right;\">\n"
                    + "        <i style=\"text-align: right;\">TP.HCM, ngày " + day + " tháng " + month + " năm " + year + "</i>\n"
                    + "        <p>Ký tên</p><br><br><br><br><br><br>\n"
                    + "    </div>";
        } else {
            htmlContent += " </table>\n"
                    + "<br><br><strong>Tổng hóa đơn sử dụng dịch vụ là: <b>" + CTSuDungDichVuDAO.countHoaDon() + "</b></strong>"
                    + "<br><br><strong>Tổng doanh thu sử dụng dịch vụ là: <b>" + df.format(CTSuDungDichVuDAO.sumTongTienDichVu()) + "</b></strong>"
                    + "<br><br><br><br>"
                    + "<div style=\"text-align: right;\">\n"
                    + "        <i style=\"text-align: right;\">TP.HCM, ngày " + day + " tháng " + month + " năm " + year + "</i>\n"
                    + "        <p>Ký tên</p><br><br><br><br><br><br>\n"
                    + "    </div>";
        }

        try {
            jTextPane1.setDocument(htmlDocument);
            htmlDocument.setInnerHTML(htmlDocument.getRootElements()[0], htmlContent);
            int width = (int) (210 * 0.39 * Toolkit.getDefaultToolkit().getScreenResolution());
            int height = (int) (297 * 0.39 * Toolkit.getDefaultToolkit().getScreenResolution());
            jTextPane1.setPreferredSize(new Dimension(width, height));

        } catch (Exception e) {}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            jTextPane1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonDichVu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblDoanhThuDv;
    private javax.swing.JLabel lblTongDv;
    // End of variables declaration//GEN-END:variables
}
