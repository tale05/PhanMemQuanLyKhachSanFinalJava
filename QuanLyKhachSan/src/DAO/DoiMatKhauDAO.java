package DAO;

import java.sql.ResultSet;

public class DoiMatKhauDAO {
    public static String getPassword(String id) {
        String password = "";
        try {
            String sql = "select mat_khau from nhanvien where id_nhanvien = '"+id.trim()+"'";
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
    
    public static int excuteSQL(String sql) {
        int i=0;
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
}
