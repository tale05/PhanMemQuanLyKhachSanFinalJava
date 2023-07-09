package DAO;

import CLASS.HDDatPhong;
import CLASS.PhongDatNhieuTheoQuy;
import CLASS.PhongDatNhieuTheoThang;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HoaDonDatPhongDAO {

    public static ArrayList<HDDatPhong> getDataDatPhong() {
        ArrayList<HDDatPhong> ds = new ArrayList<>();
        try {
            String sql = "select * from datphong where trang_thai = N'Đã thanh toán'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                HDDatPhong v = new HDDatPhong(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDataDatPhong()");
        }
        return ds;
    }

    public static ArrayList<HDDatPhong> getDSDatPhong_TheoThang(String month) {
        ArrayList<HDDatPhong> ds = new ArrayList<>();
        try {
            String sql = "SELECT * FROM datphong WHERE MONTH(check_in) = " + month.trim() + "and trang_thai = N'Đã thanh toán'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                HDDatPhong v = new HDDatPhong(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDSDatPhong_TheoThang()");
        }
        return ds;
    }

    public static ArrayList<HDDatPhong> getDSDatPhong_TheoQuy(String quy) {
        ArrayList<HDDatPhong> ds = new ArrayList<>();
        try {
            String sql = "DECLARE @Quy INT;SET @Quy = " + Integer.parseInt(quy.trim()) + ";SELECT * FROM datphong WHERE MONTH(check_in) BETWEEN (@Quy - 1) * 3 + 1 AND @Quy * 3;";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                HDDatPhong v = new HDDatPhong(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDSDatPhong_TheoQuy()");
        }
        return ds;
    }

    public static ArrayList<HDDatPhong> getDSDatPhong_TheoTuan() {
        ArrayList<HDDatPhong> ds = new ArrayList<>();
        try {
            String sql = "select * from datphong where MONTH(check_out) = MONTH(GETDATE())";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                HDDatPhong v = new HDDatPhong(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDSDatPhong_TheoTuan()");
        }
        return ds;
    }

    public static int countHoaDon() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM datphong";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu countHoaDonTheoThang()");
        }
        return count;
    }
    
    public static int countHoaDonTheoThang(String month) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM datphong WHERE MONTH(check_in) = " + month.trim() + "";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu countHoaDonTheoThang()");
        }
        return count;
    }

    public static int countHoaDonTheoQuy(String quy) {
        int count = 0;
        try {
            String sql = "DECLARE @Quy INT;\n"
                    + "SET @Quy = " + quy + ";\n"
                    + "SELECT COUNT(*) FROM datphong WHERE MONTH(check_in) BETWEEN (@Quy - 1) * 3 + 1 AND @Quy * 3;";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu countHoaDonTheoQuy()");
        }
        return count;
    }

    public static long sumTongTienHoaDonDaThanhToan() {
        long sum = 0;
        try {
            String sql = "SELECT SUM(tong_tien) FROM datphong WHERE trang_thai = N'Đã thanh toán'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTongTienHoaDonTheoThang()");
        }
        return sum;
    }
    
    public static long sumTongTienHoaDonChuaThanhToan() {
        long sum = 0;
        try {
            String sql = "SELECT SUM(tong_tien) FROM datphong WHERE trang_thai = N'Chưa thanh toán'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTongTienHoaDonTheoThang()");
        }
        return sum;
    }
    
    public static long sumTongTienHoaDonTheoThang(String month) {
        long sum = 0;
        try {
            String sql = "SELECT SUM(tong_tien) FROM datphong WHERE MONTH(check_in) = " + month.trim() + " AND trang_thai = N'Đã thanh toán'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTongTienHoaDonTheoThang()");
        }
        return sum;
    }

    public static long sumTongTienHoaDonTheoQuy(String quy) {
        long sum = 0;
        try {
            String sql = "DECLARE @Quy INT;SET @Quy = " + quy + ";SELECT SUM(tong_tien) FROM datphong WHERE MONTH(check_in) BETWEEN (@Quy - 1) * 3 + 1 AND @Quy * 3 AND trang_thai = N'Đã thanh toán';";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTongTienHoaDonTheoQuy()");
        }
        return sum;
    }

    public static ArrayList<PhongDatNhieuTheoThang> getDSDatPhongNhieuHon1_TheoThang(String month) {
        ArrayList<PhongDatNhieuTheoThang> ds = new ArrayList<>();
        try {
            String sql = "SELECT id_phong, COUNT(*) AS total_bookings FROM datphong WHERE MONTH(check_in) = " + month.trim() + " GROUP BY id_phong HAVING COUNT(*) > 1;";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                PhongDatNhieuTheoThang v = new PhongDatNhieuTheoThang(rs.getString(1), rs.getInt(2));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDSDatPhongNhieuHon1_TheoThang()");
        }
        return ds;
    }

    public static ArrayList<PhongDatNhieuTheoThang> setDSDatPhongNhieuHonTheoThang() {
        ArrayList<PhongDatNhieuTheoThang> ds = new ArrayList<>();
        PhongDatNhieuTheoThang v = new PhongDatNhieuTheoThang("Chưa có dữ liệu", 0);
        ds.add(v);
        return ds;
    }

    public static ArrayList<PhongDatNhieuTheoQuy> getDSDatPhongNhieuHon1_TheoQuy(String quy) {
        ArrayList<PhongDatNhieuTheoQuy> ds = new ArrayList<>();
        try {
            String sql = "DECLARE @quy INT;\n"
                    + "SET @quy = "+quy+";\n"
                    + "SELECT dp.id_phong, COUNT(*) AS tonglandat\n"
                    + "FROM datphong dp\n"
                    + "WHERE DATEPART(QUARTER, dp.check_in) = @quy\n"
                    + "GROUP BY dp.id_phong\n"
                    + "HAVING COUNT(*) > 1;";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                PhongDatNhieuTheoQuy v = new PhongDatNhieuTheoQuy(rs.getString(1), rs.getInt(2));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDSDatPhongNhieuHon1_TheoQuy()");
        }
        return ds;
    }

    public static ArrayList<PhongDatNhieuTheoQuy> setDSDatPhongNhieuHonTheoQuy() {
        ArrayList<PhongDatNhieuTheoQuy> ds = new ArrayList<>();
        PhongDatNhieuTheoQuy v = new PhongDatNhieuTheoQuy("Chưa có dữ liệu", 0);
        ds.add(v);
        return ds;
    }
    
    public static ArrayList<HDDatPhong> search1(String str) {
        ArrayList<HDDatPhong> ds = new ArrayList<>();
        try {
            String sql = "select * from datphong where id_datphong = '"+str.trim()+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                HDDatPhong v = new HDDatPhong(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu search1()");
        }
        return ds;
    }
    
    public static ArrayList<HDDatPhong> search2(String str) {
        ArrayList<HDDatPhong> ds = new ArrayList<>();
        try {
            String sql = "SELECT*FROM datphong WHERE id_khachhang IN (SELECT id_khachhang FROM khachhang WHERE ten_khachhang = N'"+str.trim()+"')";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                HDDatPhong v = new HDDatPhong(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu search2()");
        }
        return ds;
    }
    
    public static ArrayList<HDDatPhong> search3(String str) {
        ArrayList<HDDatPhong> ds = new ArrayList<>();
        try {
            String sql = "SELECT*FROM datphong WHERE id_khachhang IN (SELECT id_khachhang FROM khachhang WHERE sdt = '"+str+"')";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                HDDatPhong v = new HDDatPhong(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getString(13));
                ds.add(v);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu search3()");
        }
        return ds;
    }
}
