package CLASS;

public class HDDatPhong {
    String mahd, manv, makh, maphong, checkin, checkout, loai, trangthai;
    double datcoc, phuthu1, phuthu2, tongtien;
    int songuoi;

    public String getMahd() {
        return mahd;
    }

    public String getManv() {
        return manv;
    }

    public String getMakh() {
        return makh;
    }

    public String getMaphong() {
        return maphong;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getLoai() {
        return loai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public double getDatcoc() {
        return datcoc;
    }

    public double getPhuthu1() {
        return phuthu1;
    }

    public double getPhuthu2() {
        return phuthu2;
    }

    public double getTongtien() {
        return tongtien;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public void setDatcoc(double datcoc) {
        this.datcoc = datcoc;
    }

    public void setPhuthu1(double phuthu1) {
        this.phuthu1 = phuthu1;
    }

    public void setPhuthu2(double phuthu2) {
        this.phuthu2 = phuthu2;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }

    public HDDatPhong(String mahd, String manv, String makh, String maphong, String checkin, String checkout, double datcoc, double phuthu1, double phuthu2, double tongtien, int songuoi, String loai, String trangthai) {
        this.mahd = mahd;
        this.manv = manv;
        this.makh = makh;
        this.maphong = maphong;
        this.checkin = checkin;
        this.checkout = checkout;
        this.datcoc = datcoc;
        this.phuthu1 = phuthu1;
        this.phuthu2 = phuthu2;
        this.tongtien = tongtien;
        this.songuoi = songuoi;
        this.loai = loai;
        this.trangthai = trangthai;
    }
    
}
