package nhom.demo.Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nhom.demo.Repo.MonHocRepository;
import nhom.demo.Repo.SinhVienRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SinhVien {
    private String maSv;
    private String hoTen;
    private String lop;
    private String nganh;
    private String khoaHoc;
    private String gioiTinh;
    private String DiaChi;
    private String sdt;
    private String email;
    private Long soTienTrongTK;
    private Long soTinChiDk;

    public SinhVien() {
    }

    public SinhVien(String maSv, String hoTen, String lop,
                    String nganh, String khoaHoc, String gioiTinh,
                    String diaChi, String sdt, String email) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.lop = lop;
        this.nganh = nganh;
        this.khoaHoc = khoaHoc;
        this.gioiTinh = gioiTinh;
        DiaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSoTienTrongTK() {
        return soTienTrongTK;
    }

    public void setSoTienTrongTK(long soTienTrongTK) {
        this.soTienTrongTK = soTienTrongTK;
    }

    public long getSoTinChiDk() {
        return soTinChiDk;
    }

    public void setSoTinChiDk(long soTinChiDk) {
        this.soTinChiDk = soTinChiDk;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "maSv=" + maSv + ", hoTen=" + hoTen + ", lop=" + lop + ", nganh=" + nganh + ", khoaHoc=" + khoaHoc + ", gioiTinh=" + gioiTinh + ", DiaChi=" + DiaChi + ", sdt=" + sdt + ", email=" + email + ", soTienTrongTK=" + soTienTrongTK + ", soTinChiDk=" + soTinChiDk + '}';
    }
    
    
}
