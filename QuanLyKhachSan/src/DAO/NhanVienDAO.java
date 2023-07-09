/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CLASS.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class NhanVienDAO {

    public static ArrayList<String> getDSTenNhanVien() {
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "select ten_nhanvien from nhanvien";
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

    public static ArrayList<NhanVien> getDSNhanVien() {
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        try {
            String sql = "select * from nhanvien";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdnv(rs.getString(1).trim());
                nv.setTennv(rs.getString(2).trim());
                nv.setNgaysinh(rs.getString(3).trim());
                nv.setSdt(rs.getString(4).trim());
                nv.setGioitinh(rs.getString(5).trim());
                nv.setEmail(rs.getString(6).trim());
                nv.setHinhanh(rs.getString(7).trim());
                nv.setTendn(rs.getString(8).trim());
                nv.setMatkhau(rs.getString(9).trim());
                nv.setQuyen(Integer.parseInt(rs.getString(10).trim()));
                ds.add(nv);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }

    public static String getIdNhanVien(String tennv) {
        String name = "";
        try {
            String sql = "select id_nhanvien from nhanvien where ten_nhanvien = N'" + tennv + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return name;
    }

    public static boolean ThemNV(NhanVien nv) {
        boolean kq = false;
        String sql = String.format("insert into nhanvien values ('%s',N'%s','%s','%s',N'%s','%s','%s','%s','%s','%d') ",
                nv.getIdnv(),
                nv.getTennv(),
                nv.getNgaysinh(),
                nv.getSdt(),
                nv.getGioitinh(),
                nv.getEmail(),
                nv.getHinhanh(),
                nv.getTendn(),
                nv.getMatkhau(),
                nv.getQuyen());
        String sql1 = String.format("set dateformat dmy EXEC Them_Nhan_Vien N'%s', '%s', '%s', N'%s', '%s', '%s', '%s', '%s', '%d'",
                nv.getTennv(),
                nv.getNgaysinh(),
                nv.getSdt(),
                nv.getGioitinh(),
                nv.getEmail(),
                nv.getHinhanh(),
                nv.getTendn(),
                nv.getMatkhau(),
                nv.getQuyen());
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql1);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }

    public static boolean xoanv(String id) {
        boolean kq = false;
        String sql = "delete from nhanvien where id_nhanvien = '" + id + "'";
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        return kq;
    }

    public static boolean Suanv(NhanVien nv) {
        boolean kq = false;
        String sql = String.format("update nhanvien set ten_nhanvien = N'%s', ngay_sinh = '%s', sdt = '%s', gioi_tinh = N'%s', email = '%s', hinh_anh = '%s' , ten_dang_nhap = '%s' , mat_khau = '%s' , quyen = '%d' where id_nhanvien = '%s'",
                nv.getTennv(),
                nv.getNgaysinh(),
                nv.getSdt(),
                nv.getGioitinh(),
                nv.getEmail(),
                nv.getHinhanh(),
                nv.getTendn(),
                nv.getMatkhau(),
                nv.getQuyen(),
                nv.getIdnv());
        ConnectionDB cn = new ConnectionDB();
        cn.getCn();
        int n = cn.executeUpdate(sql);
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
    
    public static ArrayList<NhanVien> searchDSNhanVien_TheoTen(String ten) {
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        try {
            String sql = "select * from nhanvien where ten_nhanvien = N'"+ten+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdnv(rs.getString(1).trim());
                nv.setTennv(rs.getString(2).trim());
                nv.setNgaysinh(rs.getString(3).trim());
                nv.setSdt(rs.getString(4).trim());
                nv.setGioitinh(rs.getString(5).trim());
                nv.setEmail(rs.getString(6).trim());
                nv.setHinhanh(rs.getString(7).trim());
                nv.setTendn(rs.getString(8).trim());
                nv.setMatkhau(rs.getString(9).trim());
                nv.setQuyen(Integer.parseInt(rs.getString(10).trim()));
                ds.add(nv);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<NhanVien> searchDSNhanVien_TheoMa(String ma) {
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        try {
            String sql = "select * from nhanvien where id_nhanvien = N'"+ma+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdnv(rs.getString(1).trim());
                nv.setTennv(rs.getString(2).trim());
                nv.setNgaysinh(rs.getString(3).trim());
                nv.setSdt(rs.getString(4).trim());
                nv.setGioitinh(rs.getString(5).trim());
                nv.setEmail(rs.getString(6).trim());
                nv.setHinhanh(rs.getString(7).trim());
                nv.setTendn(rs.getString(8).trim());
                nv.setMatkhau(rs.getString(9).trim());
                nv.setQuyen(Integer.parseInt(rs.getString(10).trim()));
                ds.add(nv);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
}
