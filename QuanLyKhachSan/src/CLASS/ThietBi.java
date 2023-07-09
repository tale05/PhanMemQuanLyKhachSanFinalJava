package CLASS;

public class ThietBi {
    String mathietbi, tenthietbi;
    int gia;

    public String getMathietbi() {
        return mathietbi;
    }

    public String getTenthietbi() {
        return tenthietbi;
    }

    public int getGia() {
        return gia;
    }

    public void setMathietbi(String mathietbi) {
        this.mathietbi = mathietbi;
    }

    public void setTenthietbi(String tenthietbi) {
        this.tenthietbi = tenthietbi;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public ThietBi(String mathietbi, String tenthietbi, int gia) {
        this.mathietbi = mathietbi;
        this.tenthietbi = tenthietbi;
        this.gia = gia;
    }
}
