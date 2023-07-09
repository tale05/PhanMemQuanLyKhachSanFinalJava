package DAO;

import CLASS.ChiTietSDDichVu;
import CLASS.ChiTietSDThietBi;
import CLASS.DatPhongView;
import CLASS.DichVu;
import CLASS.ThietBi;
import GUI.Main;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatPhongViewDAO {

    public static ArrayList<DatPhongView> getViewDatPhong1() {
        ArrayList<DatPhongView> ds = new ArrayList<DatPhongView>();
        try {
            String sql = "SELECT id_datphong, ten, ten_nhanvien, ten_khachhang, loai, check_in, check_out, tong_thoi_gian, trang_thai FROM view_datphong2 where ten = N'" + Main.selectedRoomName + "' and trang_thai = N'Chưa thanh toán'";
            //String sql = "SELECT id_datphong, ten, ten_nhanvien, ten_khachhang, loai, check_in, check_out, tong_thoi_gian, trang_thai FROM view_datphong2 where ten = N'" + Main.selectedRoomName + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                DatPhongView v = new DatPhongView(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        rs.getString(4).trim(),
                        rs.getString(5).trim(),
                        rs.getString(6).substring(0, 19),
                        rs.getString(7).substring(0, 19),
                        rs.getString(8).trim(),
                        rs.getString(9).trim());
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getViewDatPhong()");
        }
        return ds;
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

    public static String getRoomName(String name) {
        String str = "";
        try {
            String sql = "select ten from phong where ten = N'" + name + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString("ten");
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getRoomName()");
        }
        return str;
    }

    public static double getTienDatCoc(String iddatphong) {
        double tiendatcoc = 0;
        try {
            String sql = "select dat_coc from datphong where id_datphong = '" + iddatphong.trim() + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                tiendatcoc = Double.parseDouble(rs.getString(1));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getTienDatCoc()");
        }
        return tiendatcoc;
    }

    public static ArrayList<DichVu> getDataDichVu() {
        ArrayList<DichVu> ds = new ArrayList<DichVu>();
        try {
            String sql = "select * from dichvu";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                DichVu v = new DichVu(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        Integer.parseInt(rs.getString(3).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDataDichVu()");
        }
        return ds;
    }

    public static ArrayList<ChiTietSDDichVu> getDataChiTietSDDichVu() {
        ArrayList<ChiTietSDDichVu> ds = new ArrayList<ChiTietSDDichVu>();
        try {
            String sql = "select * from chitietsudungdv";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDDichVu v = new ChiTietSDDichVu(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        Integer.parseInt(rs.getString(4).trim()),
                        Double.parseDouble(rs.getString(5).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDataChiTietSDDichVu()");
        }
        return ds;
    }

    public static ArrayList<ChiTietSDDichVu> getDataChiTietSDDichVuTheoMaHd(String mahd) {
        ArrayList<ChiTietSDDichVu> ds = new ArrayList<ChiTietSDDichVu>();
        try {
            String sql = "select * from chitietsudungdv where id_datphong = '" + mahd + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDDichVu v = new ChiTietSDDichVu(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        Integer.parseInt(rs.getString(4).trim()),
                        Double.parseDouble(rs.getString(5).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDataChiTietSDDichVuTheoMaHd()");
        }
        return ds;
    }

    public static double sumTienDvTheoMaHd(String mahd) {
        double sum = 0;
        try {
            String sql = "SELECT SUM(tong_tien_dv) FROM chitietsudungdv WHERE id_datphong = '" + mahd + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getDouble(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTienDvTheoMaHd()");
        }
        return sum;
    }

    public static String getTenDichVu(String madv) {
        String str = "";
        try {
            String sql = "select ten_dichvu from dichvu where id_dichvu = '" + madv + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getTenDichVu()");
        }
        return str;
    }

    public static ArrayList<ThietBi> getDataThietBi() {
        ArrayList<ThietBi> ds = new ArrayList<ThietBi>();
        try {
            String sql = "select * from thietbi";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ThietBi v = new ThietBi(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        Integer.parseInt(rs.getString(3).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDataThietBi()");
        }
        return ds;
    }

    public static ArrayList<ChiTietSDThietBi> getDataChiTietSDThietBi() {
        ArrayList<ChiTietSDThietBi> ds = new ArrayList<ChiTietSDThietBi>();
        try {
            String sql = "select * from chitietsudungtb";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDThietBi v = new ChiTietSDThietBi(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        Integer.parseInt(rs.getString(4).trim()),
                        Double.parseDouble(rs.getString(5).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du getDataChiTietSDThietBi()");
        }
        return ds;
    }

    public static ArrayList<ChiTietSDThietBi> getDataChiTietSDThietBiTheoMaHd(String mahd) {
        ArrayList<ChiTietSDThietBi> ds = new ArrayList<ChiTietSDThietBi>();
        try {
            String sql = "select * from chitietsudungtb where id_datphong = '" + mahd + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ChiTietSDThietBi v = new ChiTietSDThietBi(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        Integer.parseInt(rs.getString(4).trim()),
                        Double.parseDouble(rs.getString(5).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getDataChiTietSDThietBiTheoMaHd()");
        }
        return ds;
    }

    public static ArrayList<ThietBi> searchThietBiTheoMaTb(String matb) {
        ArrayList<ThietBi> ds = new ArrayList<ThietBi>();
        try {
            String sql = "select * from thietbi where id_thietbi = '" + matb + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ThietBi v = new ThietBi(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        Integer.parseInt(rs.getString(3).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu searchThietBiTheoMaTb()");
        }
        return ds;
    }

    public static ArrayList<ThietBi> searchThietBiTheoTenTb(String tentb) {
        ArrayList<ThietBi> ds = new ArrayList<ThietBi>();
        try {
            String sql = "select * from thietbi where ten_thietbi = N'" + tentb + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                ThietBi v = new ThietBi(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        Integer.parseInt(rs.getString(3).trim()));
                ds.add(v);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu searchThietBiTheoTenTb()");
        }
        return ds;
    }

    public static double sumTienTbTheoMaHd(String mahd) {
        double sum = 0;
        try {
            String sql = "SELECT SUM(tong_tien_tb) FROM chitietsudungtb WHERE id_datphong = '" + mahd + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getDouble(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu sumTienTbTheoMaHd()");
        }
        return sum;
    }

    public static String getTenThietBi(String matb) {
        String str = "";
        try {
            String sql = "select ten_thietbi from thietbi where id_thietbi = '" + matb + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getTenThietBi()");
        }
        return str;
    }

    public static String getMaHD(String maphong) {
        String str = "";
        try {
            String sql = "select id_datphong from datphong where id_phong = '" + maphong + "' and trang_thai = N'Chưa thanh toán'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getMaHD()");
        }
        return str;
    }

    public static int getGiaPhong(String maphong) {
        int n = 0;
        try {
            String sql = "select gia from loaiphong lp inner join phong p on p.id_loaiphong = lp.id_loaiphong where id_phong = '" + maphong + "'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getGiaPhong()");
        }
        return n;
    }
    
    public static int getSoLuongNguoi(String maphong) {
        int n = 0;
        try {
            String sql = "select so_luong_nguoi from phong where id_phong = '"+maphong+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                n = rs.getInt(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getSoLuongNguoi()");
        }
        return n;
    }
    
        public static double getTongTien(String mahd) {
        double n = 0;
        try {
            String sql = "select tong_tien from datphong where id_datphong = '"+mahd+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                n = rs.getDouble(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Loi khong the lay du lieu getGiaPhong()");
        }
        return n;
    }
}
