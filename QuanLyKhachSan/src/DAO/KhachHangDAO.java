package DAO;

import CLASS.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;

public class KhachHangDAO {
    public static ArrayList<String> getDSTKhachHang() {
        ArrayList<String> ds = new ArrayList<String>();
        try {
            String sql = "select ten_khachhang from khachhang";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                ds.add(rs.getString(1).trim());
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static String getIdKhachHang(String tenkh) {
        String str = "";
        try {
            String sql = "select id_khachhang from khachhang where ten_khachhang = N'"+tenkh.trim()+"'";
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
    
    public static ArrayList<KhachHang> getDSKhachHang() {
        ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
        try {
            String sql = "select * from khachhang";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdkh(rs.getString(1).trim());
                kh.setTenkh(rs.getString(2).trim());
                kh.setNgaysinh(rs.getString(3).trim());
                kh.setDiachi(rs.getString(4).trim());
                kh.setSdt(rs.getString(5).trim());
                kh.setCmnd(rs.getString(6).trim());
                kh.setGioitinh(rs.getString(7).trim());
                ds.add(kh);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static boolean ThemKH(KhachHang kh) {
        boolean kq = false;
        String sql = String.format("insert into khachhang values ('%s',N'%s','%s',N'%s','%s','%s','%s') ",
                kh.getIdkh(),
                kh.getTenkh(),
                kh.getNgaysinh(),
                kh.getDiachi(),
                kh.getSdt(),
                kh.getCmnd(),
                kh.getGioitinh());
        String sql1 = String.format("set dateformat dmy EXEC Them_Khach_Hang N'%s', '%s', N'%s', N'%s', '%s', N'%s'",
                kh.getTenkh(),
                kh.getNgaysinh(),
                kh.getDiachi(),
                kh.getSdt(),
                kh.getCmnd(),
                kh.getGioitinh());
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql1);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }
    
    public static boolean XoaKH(String id) {
        boolean kq = false;
        String sql = "delete from khachhang where id_khachhang = '" + id + "'";
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }
    
    public static boolean Suakh(KhachHang kh) {
        boolean kq = false;
        String sql = String.format("update khachhang set ten_khachhang = N'%s', ngay_sinh = '%s', dia_chi = N'%s', sdt = N'%s', cmnd = '%s', gioi_tinh = '%s' where id_khachhang = '%s'",
                kh.getTenkh(),
                kh.getNgaysinh(),
                kh.getDiachi(),
                kh.getSdt(),
                kh.getCmnd(),
                kh.getGioitinh(),
                kh.getIdkh());
        String sql1 = String.format("set dateformat dmy update khachhang set ten_khachhang = N'%s', ngay_sinh = '%s', dia_chi = N'%s', sdt = '%s', cmnd = '%s', gioi_tinh = N'%s' where id_khachhang = '%s'", 
                kh.getTenkh(),
                kh.getNgaysinh(),
                kh.getDiachi(),
                kh.getSdt(),
                kh.getCmnd(),
                kh.getGioitinh(),
                kh.getIdkh());
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql1);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }
    
    public static int excuteSQL(String sql) {
        int i = 0;
        try {
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            i = cn.executeUpdate(sql);
            cn.close();
            System.out.println("Thành công");

        } catch (Exception e) {
            System.out.println("Không thành công");

        }
        return i;
    }
    
    public static ArrayList<KhachHang> searchDSKhachHang_TheoTen(String ten) {
        ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
        try {
            String sql = "select * from khachhang where ten_khachhang = N'"+ten+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdkh(rs.getString(1).trim());
                kh.setTenkh(rs.getString(2).trim());
                kh.setNgaysinh(rs.getString(3).trim());
                kh.setDiachi(rs.getString(4).trim());
                kh.setSdt(rs.getString(5).trim());
                kh.setCmnd(rs.getString(6).trim());
                kh.setGioitinh(rs.getString(7).trim());
                ds.add(kh);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<KhachHang> searchDSNhanVien_TheoMa(String ma) {
        ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
        try {
            String sql = "select * from khachhang where id_khachhang = N'"+ma+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdkh(rs.getString(1).trim());
                kh.setTenkh(rs.getString(2).trim());
                kh.setNgaysinh(rs.getString(3).trim());
                kh.setDiachi(rs.getString(4).trim());
                kh.setSdt(rs.getString(5).trim());
                kh.setCmnd(rs.getString(6).trim());
                kh.setGioitinh(rs.getString(7).trim());
                ds.add(kh);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
}
