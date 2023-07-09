package DAO;

import java.sql.ResultSet;

public class DangNhapDAO {
    public static String getPassword(String user) {
        String password = "";
        try {
            String sql = "select mat_khau from nhanvien where ten_dang_nhap = '"+user.trim()+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 password=rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return password;
    }
    
    public static String getUsername(String user) {
        String username = "";
        try {
            String sql = "select ten_dang_nhap from nhanvien where ten_dang_nhap = '"+user.trim()+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 username=rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return username;
    }
    
    public static String getId(String user) {
        String username = "";
        try {
            String sql = "select id_nhanvien from nhanvien where ten_dang_nhap = '"+user.trim()+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 username=rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return username;
    }
    
    public static int getRole(String user) {
        int a = 0;
        try {
            String sql = "select quyen from nhanvien where ten_dang_nhap = '"+user.trim()+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 a=Integer.parseInt(rs.getString(1));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return a;
    }
    
    public static String getFullname(String user) {
        String username = "";
        try {
            String sql = "select ten_nhanvien from nhanvien where ten_dang_nhap = '"+user.trim()+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 username=rs.getString(1).trim();
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return username;
    }
    
    public static String getFileimage(String user) {
        String username = "";
        try {
            String sql = "select hinh_anh from nhanvien where ten_dang_nhap = '"+user.trim()+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 username=rs.getString(1).trim();
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return username;
    }
}
