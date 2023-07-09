package CLASS;

public class ChiTietSDThietBi {
    String mahd, matb, ngay, tentb;
    int soluong;
    double tongtien, dongia;

    public String getMahd() {
        return mahd;
    }

    public String getMatb() {
        return matb;
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

    public void setMatb(String matb) {
        this.matb = matb;
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

    public String getTentb() {
        return tentb;
    }

    public double getDongia() {
        return dongia;
    }

    public void setTentb(String tentb) {
        this.tentb = tentb;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }
    

    public ChiTietSDThietBi(String mahd, String matb, String ngay, int soluong, double tongtien) {
        this.mahd = mahd;
        this.matb = matb;
        this.ngay = ngay;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public ChiTietSDThietBi(String tentb, double dongia, int soluong, double tongtien) {
        this.tentb = tentb;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.dongia = dongia;
    }
    
    
}
