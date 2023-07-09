package DAO;

import CLASS.ChiTietSDDichVu;
import CLASS.ChiTietSDThietBi;
import CLASS.DichVu;
import GUI.DatPhong;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CTSuDungThietBiDAO {

    public static ArrayList<ChiTietSDThietBi> getCTSDThietBiTheoMaHD() {
        ArrayList<ChiTietSDThietBi> ds = new ArrayList<>();
        try {
            String sql = "SELECT tb.ten_thietbi, tb.gia, cttb.so_luong, cttb.tong_tien_tb FROM chitietsudungtb cttb JOIN thietbi tb ON cttb.id_thietbi = tb.id_thietbi WHERE cttb.id_datphong = '" + DatPhong.idhoadon + "';";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDThietBi ctdv = new ChiTietSDThietBi(rs.getString(1), rs.getDouble(2), rs.getInt(3), rs.getDouble(4));
                ds.add(ctdv);
            }
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<ChiTietSDThietBi> getData() {
        ArrayList<ChiTietSDThietBi> ds = new ArrayList<>();
        try {
            String sql = "select * from chitietsudungtb";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDThietBi ctdv = new ChiTietSDThietBi(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                ds.add(ctdv);
            }
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<ChiTietSDThietBi> getDataTheoThang(String month) {
        ArrayList<ChiTietSDThietBi> ds = new ArrayList<>();
        try {
            String sql = "select * from chitietsudungtb WHERE MONTH(ngay_thue) = " + month.trim() + " ";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDThietBi ctdv = new ChiTietSDThietBi(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                ds.add(ctdv);
            }
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static int countHoaDonTheoThang(String month) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM chitietsudungtb WHERE MONTH(ngay_thue) = " + month.trim() + "";
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
    
    public static int countHoaDon() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM chitietsudungtb";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu countHoaDon()");
        }
        return count;
    }
    
    public static long sumTongTienDichVuTheoThang(String month) {
        long sum = 0;
        try {
            String sql = "select SUM(tong_tien_tb) from chitietsudungtb WHERE MONTH(ngay_thue) = "+month.trim()+" ";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTongTienDichVuTheoThang()");
        }
        return sum;
    }
    
    public static long sumTongTienDichVu() {
        long sum = 0;
        try {
            String sql = "select SUM(tong_tien_tb) from chitietsudungtb";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTongTienDichVu()");
        }
        return sum;
    }
}
