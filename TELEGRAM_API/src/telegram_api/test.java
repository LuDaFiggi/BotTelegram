/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegram_api;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bonfissuto_luca
 */
public class test {

    public void hello() {
        String jsonString = "{nome:'mario',messaggi:['a','b','c']}";
        JSONObject obj = new JSONObject(jsonString);
        String pageName = obj.getString("nome");
        JSONArray arr = obj.getJSONArray("messaggi");

        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getString(i);
            System.out.println(post_id);
        }
    }
}
