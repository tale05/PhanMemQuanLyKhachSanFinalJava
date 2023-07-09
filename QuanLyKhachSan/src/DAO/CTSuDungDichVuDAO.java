package DAO;

import CLASS.ChiTietSDDichVu;
import CLASS.DichVu;
import GUI.DatPhong;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CTSuDungDichVuDAO {

    public static ArrayList<ChiTietSDDichVu> getCTSDDichVuTheoMaHD() {
        ArrayList<ChiTietSDDichVu> ds = new ArrayList<>();
        try {
            String sql = "SELECT dv.ten_dichvu, dv.gia, ctsd.so_luong, ctsd.tong_tien_dv FROM chitietsudungdv ctsd JOIN dichvu dv ON ctsd.id_dichvu = dv.id_dichvu WHERE ctsd.id_datphong = '" + DatPhong.idhoadon + "';";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDDichVu ctdv = new ChiTietSDDichVu(rs.getString(1), rs.getDouble(2), rs.getInt(3), rs.getDouble(4));
                ds.add(ctdv);
            }
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<ChiTietSDDichVu> getData() {
        ArrayList<ChiTietSDDichVu> ds = new ArrayList<>();
        try {
            String sql = "select * from chitietsudungdv";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDDichVu ctdv = new ChiTietSDDichVu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                ds.add(ctdv);
            }
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<ChiTietSDDichVu> getDataTheoThang(String month) {
        ArrayList<ChiTietSDDichVu> ds = new ArrayList<>();
        try {
            String sql = "select * from chitietsudungdv WHERE MONTH(ngay_thue) = " + month.trim() + " ";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDDichVu ctdv = new ChiTietSDDichVu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
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
            String sql = "SELECT COUNT(*) FROM chitietsudungdv WHERE MONTH(ngay_thue) = " + month.trim() + "";
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
            String sql = "SELECT COUNT(*) FROM chitietsudungdv";
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
    
    public static long sumTongTienDichVuTheoThang(String month) {
        long sum = 0;
        try {
            String sql = "select SUM(tong_tien_dv) from chitietsudungdv WHERE MONTH(ngay_thue) = "+month.trim()+" ";
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
    
    public static long sumTongTienDichVu() {
        long sum = 0;
        try {
            String sql = "select SUM(tong_tien_dv) from chitietsudungdv";
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
