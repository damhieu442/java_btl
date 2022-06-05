package nhom.demo.Repo;

import nhom.demo.Model.MonHoc;
import nhom.demo.Model.SinhVien;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;

public class SinhVienRepository {
    public static List<SinhVien> readData()
    {
        List<SinhVien> sinhViens = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader("SinhVien.json")) {
            Object obj = jsonParser.parse(fileReader);
            JSONArray sinhVienList = (JSONArray) obj;

            sinhVienList.forEach(mph -> {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setMaSv((String) ((JSONObject) mph).get("maSv"));
                sinhVien.setHoTen((String) ((JSONObject) mph).get("hoTen"));
                sinhVien.setLop((String) ((JSONObject) mph).get("lop"));
                sinhVien.setNganh((String) ((JSONObject) mph).get("nganh"));
                sinhVien.setKhoaHoc((String) ((JSONObject) mph).get("khoaHoc"));
                sinhVien.setGioiTinh((String) ((JSONObject) mph).get("gioiTinh"));
                sinhVien.setDiaChi((String) ((JSONObject) mph).get("diaChi"));
                sinhVien.setSdt((String) ((JSONObject) mph).get("sdt"));
                sinhVien.setEmail((String) ((JSONObject) mph).get("email"));
                sinhVien.setSoTinChiDk((long) ((JSONObject) mph).get("soTinChiDk"));
                sinhVien.setSoTienTrongTK((long) ((JSONObject) mph).get("soTienTrongTaiKhoan"));

                sinhViens.add(sinhVien);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sinhViens;
    }
    
//    public static void main(String[] args) {
//        List<SinhVien> list = readData();
//      System.out.println(list.toString());
//      }

    public static void writeData(List<SinhVien> list)
    {
        JSONArray mhList = new JSONArray();
        for ( SinhVien sinhVien: list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("maSv",sinhVien.getMaSv());
            jsonObject.put("hoTen",sinhVien.getHoTen());
            jsonObject.put("lop",sinhVien.getLop());
            jsonObject.put("nganh",sinhVien.getNganh());
            jsonObject.put("khoaHoc",sinhVien.getKhoaHoc());
            jsonObject.put("gioiTinh",sinhVien.getGioiTinh());
            jsonObject.put("diaChi",sinhVien.getDiaChi());
            jsonObject.put("sdt",sinhVien.getSdt());
            jsonObject.put("email",sinhVien.getEmail());
            jsonObject.put("soTinChiDk",sinhVien.getSoTinChiDk());
            jsonObject.put("soTienTrongTaiKhoan",sinhVien.getSoTienTrongTK());

            mhList.add(jsonObject);
        }


        try(FileWriter fileWriter = new FileWriter("SinhVien.json"))
        {
            fileWriter.write(mhList.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
