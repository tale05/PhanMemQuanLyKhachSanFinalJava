/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import CLASS.ThietBi;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ThietBiDAO {
    public static ArrayList<ThietBi> getThietBi(){
        ArrayList<ThietBi> ds = new ArrayList<ThietBi>();
        try{
            String sql = "Select * from thietbi";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                ThietBi tb = new ThietBi(rs.getString(1).trim(),
                rs.getString(2).trim(),
                Integer.parseInt(rs.getString(3).trim()));
                ds.add(tb);
            }
        }
        catch(Exception e){
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    public static ArrayList<String> getIdThietBi() {
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "select id_thietbi from thietbi";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                ds.add(rs.getString("id_thietbi"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu bàn");
        }
        return ds;
    }
    public static int ThemXoaSuaThietBi(String sql) {
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
