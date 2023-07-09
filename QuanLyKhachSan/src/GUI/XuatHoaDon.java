package GUI;

import CLASS.ChiTietSDDichVu;
import CLASS.ChiTietSDThietBi;
import DAO.CTSuDungDichVuDAO;
import DAO.CTSuDungThietBiDAO;
import DAO.DatPhongViewDAO;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTML;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.text.html.HTMLEditorKit;

public class XuatHoaDon extends javax.swing.JFrame {

    public XuatHoaDon() {
        initComponents();

        ArrayList<ChiTietSDDichVu> dsDv = CTSuDungDichVuDAO.getCTSDDichVuTheoMaHD();
        ArrayList<ChiTietSDThietBi> dsTb = CTSuDungThietBiDAO.getCTSDThietBiTheoMaHD();
        
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        df.applyPattern("#,###");

        jTextPane1.setContentType("text/html");

        HTMLDocument htmlDocument = (HTMLDocument) jTextPane1.getDocument();

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
                + "		<h1 style=\"text-align: center;\">***************** HÓA ĐƠN *****************</h1>\n"
                + "    </div>\n"
                + "	<table>\n"
                + "		<tbody>\n"
                + "			<tr>\n"
                + "				<td>Mã HĐ: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp "+DatPhong.idhoadon+"</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Tên khách hàng: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp "+DatPhong.tenKh+"</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Tên nhân viên: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp "+DatPhong.tenNv+"</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Tên phòng: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp "+Main.selectedRoomName+"</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Check-in: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp "+DatPhong.checkin+"</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Check-out: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp "+DatPhong.checkout+"</td>\n"
                + "			</tr>\n"
                + "			<tr>\n"
                + "				<td>Tổng thời gian: </td>\n"
                + "				<td>&nbsp &nbsp &nbsp "+DatPhong.tonggio+"</td>\n"
                + "			</tr>\n"
                + "		</tbody>\n"
                + "	</table>\n"
                + "	<br>\n"
                + "    <table class=\"invoice-items\">\n"
                + "        <thead>\n"
                + "            <tr>\n"
                + "                <th>Chi tiết</th>\n"
                + "                <th>Đơn giá</th>\n"
                + "                <th>Số lượng</th>\n"
                + "                <th>Thành tiền</th>\n"
                + "				<th>Ghi chú</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        htmlContent += "            <tr>\n"
                + "                <td>"+Main.selectedRoomName+"</td>\n"
                + "                <td style=\"text-align: right\">"+String.valueOf(df.format(DatPhongViewDAO.getGiaPhong(Main.selectedRoomId)))+"</td>\n"
                + "                <td style=\"text-align: right\">"+DatPhong.tonggio+"</td>\n"
                + "                <td style=\"text-align: right\">"+df.format(Double.parseDouble(DatPhong.tienphong))+"</td>\n"
                + "                <td>(có phụ thu)</td>\n"
                + "            </tr>\n";

        
        for (ChiTietSDDichVu dsct : dsDv) {
            htmlContent += "            <tr>\n"
                    + "                <td>" + dsct.getTendv() + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getDongia()) + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getSoluong()) + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getTongtien()) + "</td>\n"
                    + "                <td></td>\n"
                    + "            </tr>\n";
        }
        for (ChiTietSDThietBi dsct : dsTb) {
            htmlContent += "            <tr>\n"
                    + "                <td>" + dsct.getTentb()+ "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getDongia())+ "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getDongia()) + "</td>\n"
                    + "                <td style=\"text-align: right\">" + df.format(dsct.getDongia()) + "</td>\n"
                    + "                <td></td>\n"
                    + "            </tr>\n";
        }
        double tongthanhtoan = Double.parseDouble(DatPhong.tienphong) + Double.parseDouble(DatPhong.tienDv) + Double.parseDouble(DatPhong.tienTb);
        htmlContent += "            <tr>\n"
                + "                <td colspan=\"3\" style=\"text-align: right; font-weight: bolder;\">Tổng thanh toán:</td>\n"
                + "                <td style=\"text-align: right\">"+df.format(tongthanhtoan)+"</td>\n"
                + "                <td></td>\n"
                + "            </tr>\n"
                + "            </tr>\n"
                + "                <td colspan=\"3\" style=\"text-align: right; font-weight: bolder;\">Đặt cọc:</td>\n"
                + "                <td style=\"text-align: right\">"+df.format(Double.parseDouble(DatPhong.datcoc))+"</td>\n"
                + "                <td></td>\n"
                + "            </tr>\n"
                + "            </tr>\n"
                + "                <td colspan=\"3\" style=\"text-align: right; font-weight: bolder;\">Số tiền cần thanh toán:</td>\n"
                + "                <td style=\"text-align: right\">"+df.format(tongthanhtoan - Double.parseDouble(DatPhong.datcoc))+"</td>\n"
                + "                <td></td>\n"
                + "            </tr>\n"
                + "        </tbody>\n"
                + "    </table>\n"
                + "</body>\n"
                + "</html>";

        try {
            jTextPane1.setDocument(htmlDocument);
            htmlDocument.setInnerHTML(htmlDocument.getRootElements()[0], htmlContent);
        } catch (Exception e) {}

        //jTextPane1.setText(DatPhong.thongtinHoaDon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTextPane1);

        jPanel2.setBackground(new java.awt.Color(0, 64, 93));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN HÓA ĐƠN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Check Hóa Đơn Chính Xác Chưa? ");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Quay Lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("In Hóa Đơn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 107, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            jTextPane1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(XuatHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XuatHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
