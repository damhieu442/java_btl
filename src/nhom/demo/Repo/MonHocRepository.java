package nhom.demo.Repo;

import nhom.demo.Model.MonHoc;
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

public class MonHocRepository {
    public static List<MonHoc> readData()
    {
        List<MonHoc> list = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader("MonHoc.json")) {
            Object obj = jsonParser.parse(fileReader);
            JSONArray monhoclis = (JSONArray) obj;

            monhoclis.forEach(mhp -> {
                MonHoc monHoc = new MonHoc();
                monHoc.setMaMonHoc( (String) ((JSONObject) mhp).get("maMonHoc"));
                monHoc.setTenMonHoc( (String) ((JSONObject) mhp).get("tenMonHoc"));
                monHoc.setSoTin((long) ((JSONObject) mhp).get("soTin"));
                monHoc.setGiaoVien( (String) ((JSONObject) mhp).get("giaoVien"));
                monHoc.setHinhThucThi( (String) ((JSONObject) mhp).get("hinhThucThi"));

                list.add(monHoc);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void writeData(List<MonHoc> list)
    {
        JSONArray mhList = new JSONArray();
        for ( MonHoc monHoc: list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("maMonHoc",monHoc.getMaMonHoc());
            jsonObject.put("tenMonHoc",monHoc.getTenMonHoc());
            jsonObject.put("soTin",monHoc.getSoTin());
            jsonObject.put("giaoVien",monHoc.getGiaoVien());
            jsonObject.put("hinhThucThi",monHoc.getHinhThucThi());

            mhList.add(jsonObject);
        }


        try(FileWriter fileWriter = new FileWriter("MonHoc.json"))
        {
            fileWriter.write(mhList.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        writeData((List<MonHoc>) new MonHoc("MH001","Thiet ke web",12,"phu dep trai","tu luan"));
//        System.out.println(readData().toString());
//    }

}
