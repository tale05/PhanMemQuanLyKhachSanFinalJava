package DAO;

import CLASS.Phong;
import java.sql.*;
import java.util.ArrayList;

public class PhongDAO {

    public static int excuteSQL(String sql) {
        int i = 0;
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        i = cn.executeUpdate(sql);
        cn.close();
        return i;
    }

    public static String getTenPhong(String tenphong) {
        String str = "";
        try {
            String sql = "select ten from phong where id_phong = '" + tenphong.trim() + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return str;
    }

    public static ArrayList<String> getTenPhong() {
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "select ten from phong where trang_thai = N'Còn trống'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ds.add(rs.getString(1));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }

    public static String getLoaiPhong(String tenphong) {
        String str = "";
        try {
            String sql = "select ten_loai from loaiphong lp inner join phong p on lp.id_loaiphong = p.id_loaiphong where ten = N'" + tenphong + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return str;
    }

    public static ArrayList<String> getDsTenLoaiPhong() {
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "select ten_loai from loaiphong";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ds.add(rs.getString(1));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }

    public static int getGiaPhong(String tenphong) {
        int gia = 0;
        try {
            String sql = "select gia from loaiphong lp inner join phong p on lp.id_loaiphong = p.id_loaiphong where ten = N'" + tenphong + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                gia = rs.getInt(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return gia;
    }

    public static int getSoLuong(String tenphong) {
        int num = 0;
        try {
            String sql = "select so_luong_nguoi from phong where ten = N'" + tenphong + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                num = rs.getInt(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return num;
    }

    public static String getIdPhong1(String tenphong) {
        String str = "";
        try {
            String sql = "select id_phong from phong where ten = '" + tenphong.trim() + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return str;
    }

    public static ArrayList<Phong> getDSPhong() {
        ArrayList<Phong> ds = new ArrayList<Phong>();
        try {
            String sql = "select * from phong";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                Phong ph = new Phong();
                ph.setPhong(rs.getString(1).trim());
                ph.setIdloaiphong(rs.getString(2).trim());
                ph.setTang(rs.getString(3).trim());
                ph.setTen(rs.getString(4).trim());
                ph.setSoluongnguoi(rs.getString(5).trim());
                ph.setTrangthai(rs.getString(6).trim());
                ds.add(ph);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }

    public static String getIdPhong(String tenphong) {
        String str = "";
        try {
            String sql = "select ten from phong where id_phong = '" + tenphong.trim() + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return str;
    }

    public static boolean ThemNV(Phong ph) {
        boolean kq = false;
        String sql = String.format("EXEC Them_Phong %s, %s, N'%s', %s",
                ph.getIdloaiphong(),
                ph.getTang(),
                ph.getTen(),
                ph.getSoluongnguoi());
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }

    public static boolean Xoaph(String id) {
        boolean kq = false;
        String sql = "delete from phong where id_phong = '" + id + "'";
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }

    public static boolean Suanv(Phong ph) {
        boolean kq = false;
        String sql = String.format("update phong set  id_loaiphong = '%s', id_tang = '%s', ten = '%s', so_luong_nguoi = '%s', trang_thai = N'%s' where id_phong = '%s'",
                ph.getIdloaiphong(),
                ph.getTang(),
                ph.getTen(),
                ph.getSoluongnguoi(),
                ph.getTrangthai(),
                ph.getPhong());
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }

    public static int getIdLoaiPhong(String tenloaiphong) {
        int a = 0;
        try {
            String sql = "select id_loaiphong from loaiphong where ten_loai = N'"+tenloaiphong+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                a = rs.getInt(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return a;
    }
    
    public static String getTenLoaiPhong(String id) {
        String str = "";
        try {
            String sql = "select ten_loai from loaiphong where id_loaiphong = '"+id+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return str;
    }
    
    public static ArrayList<Phong> searchTheoMaPhong(String maphong) {
        ArrayList<Phong> ds = new ArrayList<Phong>();
        try {
            String sql = "select * from phong where id_phong = '"+maphong+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                Phong ph = new Phong();
                ph.setPhong(rs.getString(1).trim());
                ph.setIdloaiphong(rs.getString(2).trim());
                ph.setTang(rs.getString(3).trim());
                ph.setTen(rs.getString(4).trim());
                ph.setSoluongnguoi(rs.getString(5).trim());
                ph.setTrangthai(rs.getString(6).trim());
                ds.add(ph);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<Phong> searchTheoTenPhong(String tenphong) {
        ArrayList<Phong> ds = new ArrayList<Phong>();
        try {
            String sql = "select * from phong where ten = N'"+tenphong+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                Phong ph = new Phong();
                ph.setPhong(rs.getString(1).trim());
                ph.setIdloaiphong(rs.getString(2).trim());
                ph.setTang(rs.getString(3).trim());
                ph.setTen(rs.getString(4).trim());
                ph.setSoluongnguoi(rs.getString(5).trim());
                ph.setTrangthai(rs.getString(6).trim());
                ds.add(ph);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
}
