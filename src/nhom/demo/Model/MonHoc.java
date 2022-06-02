package nhom.demo.Model;

public class MonHoc {
    private String maMonHoc;
    private String TenMonHoc;
    private Long soTin;
    private String giaoVien;
    private String hinhThucThi;

    public MonHoc() {
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "maMonHoc='" + maMonHoc + '\'' +
                ", TenMonHoc='" + TenMonHoc + '\'' +
                ", soTin=" + soTin +
                ", giaoVien='" + giaoVien + '\'' +
                ", hinhThucThi='" + hinhThucThi + '\'' +
                '}';
    }

    public MonHoc(String maMonHoc, String tenMonHoc, long soTin, String giaoVien, String hinhThucThi) {
        this.maMonHoc = maMonHoc;
        TenMonHoc = tenMonHoc;
        this.soTin = soTin;
        this.giaoVien = giaoVien;
        this.hinhThucThi = hinhThucThi;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }

    public Long getSoTin() {
        return soTin;
    }

    public void setSoTin(Long soTin) {
        this.soTin = soTin;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public String getHinhThucThi() {
        return hinhThucThi;
    }

    public void setHinhThucThi(String hinhThucThi) {
        this.hinhThucThi = hinhThucThi;
    }
}
