package CLASS;

public class DichVu {
    String madichvu, tendichvu;
    int gia;

    public void setMadichvu(String madichvu) {
        this.madichvu = madichvu;
    }

    public void setTendichvu(String tendichvu) {
        this.tendichvu = tendichvu;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMadichvu() {
        return madichvu;
    }

    public String getTendichvu() {
        return tendichvu;
    }

    public int getGia() {
        return gia;
    }

    public DichVu(String madichvu, String tendichvu, int gia) {
        this.madichvu = madichvu;
        this.tendichvu = tendichvu;
        this.gia = gia;
    }
    
}
