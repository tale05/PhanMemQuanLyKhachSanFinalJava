package CLASS;

public class KhachHang {

    private String idkh, tenkh, ngaysinh, diachi, sdt, cmnd, gioitinh;

    public void setIdkh(String idkh) {
        this.idkh = idkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getIdkh() {
        return idkh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public KhachHang() {
    }

    public KhachHang(String idkh, String tenkh, String ngaysinh, String diachi, String sdt, String cmnd, String gioitinh) {
        this.idkh = idkh;
        this.tenkh = tenkh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.cmnd = cmnd;
        this.gioitinh = gioitinh;
    }
}
