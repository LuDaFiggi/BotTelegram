/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegram_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lucab
 */
public class ClearUpdates {

    String jsonString;

    public ClearUpdates() throws MalformedURLException, IOException {
        try {
            this.jsonString = getStringJson("https://api.telegram.org/bot5104853499:AAHxkpigvsm3p6UGxyL_54xySEyrUfMXJeg/getUpdates");
        } catch (IOException ex) {
            Logger.getLogger(ClearUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear() throws MalformedURLException, IOException{
        JSONObject obj = new JSONObject(jsonString);
        boolean result = obj.getBoolean("ok");
        int update_id = 0;
        if (result) {
            JSONArray arr = obj.getJSONArray("result");
            if (arr.length() > 0) {
                for (int i = 0; i < arr.length(); i++) {
                    update_id = arr.getJSONObject(i).getInt("update_id");
                }
            }
        }
        URL url = new URL("https://api.telegram.org/bot5104853499:AAHxkpigvsm3p6UGxyL_54xySEyrUfMXJeg/getUpdates?offset=" + (update_id + 1));
        url.openStream();
    }

    public String getStringJson(String url) throws MalformedURLException, IOException {
        URL website = new URL(url);
        InputStream stream = website.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String s = "";
        String line = "";
        while ((line = reader.readLine()) != null) {
            s += line + "\n";
        }
        return s;
    }

}
