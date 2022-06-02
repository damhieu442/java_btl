/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author hieuv
 */
public class LK_SinhVien_MonHoc {
    private SinhVien sv;
    private List<MonHoc> dsachMon;
    public final int tienMoiTin = 350;

    public LK_SinhVien_MonHoc(SinhVien sv, List<MonHoc> dsachMon) {
        this.sv = sv;
        this.dsachMon = dsachMon;
    }

    public LK_SinhVien_MonHoc() {
        this.setDsachMon();
    }

    public SinhVien getSv() {
        return sv;
    }

    public void setSv(SinhVien sv) {
        this.sv = sv;
    }

    public List<MonHoc> getDsachMon() {
        return dsachMon;
    }

    public void setDsachMon(List<MonHoc> dsachMon) {
        this.dsachMon = dsachMon;
    }
    
    public void setDsachMon()
    {
        List<MonHoc> resultMonHocs = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader("DanhSachDangKyMonHoc.json")) {
            Object obj = jsonParser.parse(fileReader);
            JSONArray monhoclis = (JSONArray) obj;

            monhoclis.forEach(mhp -> {
                SinhVien sinhVien1 = new SinhVien();
                MonHoc monHoc = new MonHoc();
                List<SinhVien> listSv = (new SinhVienRepository()).readData();
                String maSV = ( (String) ((JSONObject) mhp).get("MaSV"));
                for (SinhVien sinhVien : listSv) {
                    if(maSV.equals(sinhVien.getMaSv())){
                        sinhVien1 = sinhVien;
                        break;
                    }
                }
                this.sv = sinhVien1;
                List<MonHoc> dsMon = (new MonHocRepository()).readData();
                String[] dsMaMonHoc = ( (String) ((JSONObject) mhp).get("maMonHoc")).split(",");
                for (int i = 0; i < dsMaMonHoc.length; i++) {
                    for (MonHoc monHoc1 : dsMon) {
                        if(dsMaMonHoc[i].equals(monHoc1.getMaMonHoc())){
                            resultMonHocs.add(monHoc1);
                            break;
                        }
                    }
                }
                this.dsachMon = resultMonHocs;
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
    }
    public void thanhToan()
    {
        int totalMoney = 0;
        for (MonHoc monHoc : dsachMon) {
            totalMoney += monHoc.getSoTin()*tienMoiTin;
        }
        
        if(this.sv.getSoTienTrongTK() > totalMoney){
            List<SinhVien> list = (new SinhVienRepository()).readData();
            for (SinhVien sinhVien : list) {
                if(this.sv.getMaSv().equals(sinhVien.getMaSv()))
                {
                    sinhVien.setSoTienTrongTK(this.sv.getSoTienTrongTK()-totalMoney);
                }
            }
            
            new SinhVienRepository().writeData(list);
        
        }
    }
    
    public static void main(String[] args) {
        LK_SinhVien_MonHoc lksvmh = new LK_SinhVien_MonHoc();
        System.out.println(lksvmh);
        
        lksvmh.thanhToan();
    }

    @Override
    public String toString() {
        return "LK_SinhVien_MonHoc{" + "sv=" + sv + ", dsachMon=" + dsachMon + '}';
    }
    
    
}
