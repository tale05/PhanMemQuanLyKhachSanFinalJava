package DAO;

import CLASS.DichVu;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DichVuDAO {
    public static ArrayList<DichVu> getDichVu(){
        ArrayList<DichVu> ds = new ArrayList<DichVu>();
        try{
            String sql = "Select * from dichvu";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                DichVu dv = new DichVu(rs.getString(1).trim(),
                rs.getString(2).trim(),
                Integer.parseInt(rs.getString(3).trim()));
                ds.add(dv);
            }
        }
        catch(Exception e){
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    public static ArrayList<String> getIdDichvu() {
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "select id_dichvu from dichvu";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                ds.add(rs.getString("id_ichvu"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu bàn");
        }
        return ds;
    }
    public static int ThemXoaSuaDichVu(String sql) {
        int i=0;
        try {
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            i = cn.executeUpdate(sql);
            cn.close();
            System.out.println("Thêm/xóa/sửa thành công");
            
        } catch (Exception e) {
            System.out.println("Thêm/xóa/sửa không thành công");
           
        }
        return i;
    }
}
