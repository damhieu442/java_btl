/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom.demo.Repo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nhom.demo.Model.Login;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author phitrunghieu
 */
public class LoginRepository {
    public static List<Login> readData()
    {
        List<Login> list = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader("login.json")) {
            Object obj = jsonParser.parse(fileReader);
            JSONArray users = (JSONArray) obj;

            users.forEach(mhp -> {
                Login user = new Login();
                user.setUsername( (String) ((JSONObject) mhp).get("username"));
                user.setPassword( (String) ((JSONObject) mhp).get("password"));
               
                list.add(user);
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


    public static void writeData(List<Login> list)
    {
        JSONArray users = new JSONArray();
        for ( Login user: list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            

            users.add(jsonObject);
        }

        try(FileWriter fileWriter = new FileWriter("login.json"))
        {
            fileWriter.write(users.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//        List<Login> users = readData();
//        System.out.println(users.get(0).getUsername());
//        System.out.println(users.get(0).getPassword());
//    }
}
