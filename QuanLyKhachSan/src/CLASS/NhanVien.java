package CLASS;

public class NhanVien {
    String idnv,tennv,ngaysinh,sdt,gioitinh,email,hinhanh,tendn,matkhau;

    public String getIdnv() {
        return idnv;
    }

    public String getTennv() {
        return tennv;
    }

    public NhanVien() {
    }

    public NhanVien(String idnv, String tennv, String ngaysinh, String sdt, String gioitinh, String email, String hinhanh, String tendn, String matkhau, int quyen) {
        this.idnv = idnv;
        this.tennv = tennv;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
        this.email = email;
        this.hinhanh = hinhanh;
        this.tendn = tendn;
        this.matkhau = matkhau;
        this.quyen = quyen;
    }

    public void setIdnv(String idnv) {
        this.idnv = idnv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getEmail() {
        return email;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public String getTendn() {
        return tendn;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public int getQuyen() {
        return quyen;
    }
    int quyen;
}
