package CLASS;

import java.util.Date;

public class DatPhongView {
    String madatphong, tenphong, tennv, tenkh, loai, tongthoigian, trangthai;
    String checkin, checkout;

    public String getMadatphong() {
        return madatphong;
    }

    public String getTenphong() {
        return tenphong;
    }

    public String getTennv() {
        return tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public String getLoai() {
        return loai;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getTongthoigian() {
        return tongthoigian;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setMadatphong(String madatphong) {
        this.madatphong = madatphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setTongthoigian(String tongthoigian) {
        this.tongthoigian = tongthoigian;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public DatPhongView(String madatphong, String tenphong, String tennv, String tenkh, String loai, String checkin, String checkout, String tongthoigian, String trangthai) {
        this.madatphong = madatphong;
        this.tenphong = tenphong;
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.loai = loai;
        this.checkin = checkin;
        this.checkout = checkout;
        this.tongthoigian = tongthoigian;
        this.trangthai = trangthai;
    }

}
