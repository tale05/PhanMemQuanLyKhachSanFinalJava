package CLASS;

public class ChiTietSDDichVu {
    String mahd, madv, ngay, tendv;
    int soluong;
    double tongtien, dongia;

    public String getMahd() {
        return mahd;
    }

    public String getMadv() {
        return madv;
    }

    public String getNgay() {
        return ngay;
    }

    public int getSoluong() {
        return soluong;
    }

    public double getTongtien() {
        return tongtien;
    }
    

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public void setMadv(String madv) {
        this.madv = madv;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }
    

    public ChiTietSDDichVu(String mahd, String madv, String ngay, int soluong, double tongtien) {
        this.mahd = mahd;
        this.madv = madv;
        this.ngay = ngay;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public ChiTietSDDichVu(String tendv, double dongia, int soluong, double tongtien) {
        this.tendv = tendv;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.dongia = dongia;
    }
    
    
}
