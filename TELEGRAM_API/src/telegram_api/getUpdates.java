/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class GetUpdates extends Thread {

    public Chat c;
    public From f;
    public Info inf;
    public Messages m;
    int arrl;
    ClearUpdates clear;
    SendMessage send;
    Location location;

    public GetUpdates() throws IOException {
        c = new Chat();
        f = new From();
        inf = new Info();
        arrl = 0;
        clear = new ClearUpdates();
        send = new SendMessage();
        location = new Location();
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

    @Override
    public void run() {

        while (true) {
            String jsonString = "";

            try {
                clear.clear();
                jsonString = getStringJson("https://api.telegram.org/bot5104853499:AAHxkpigvsm3p6UGxyL_54xySEyrUfMXJeg/getUpdates");

            } catch (IOException ex) {
                Logger.getLogger(GetUpdates.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject obj = new JSONObject(jsonString);
            boolean result = obj.getBoolean("ok");
            if (result) {
                JSONArray arr = obj.getJSONArray("result");

                if (arr.length() > arrl) {
                    for (int i = arrl; i < arr.length(); i++) {
                        int update_id = arr.getJSONObject(i).getInt("update_id");
                        JSONObject arrmess = arr.getJSONObject(i).getJSONObject("message");
                        int message_id = arrmess.getInt("message_id");
                        String text = arrmess.getString("text");
                        int date = arrmess.getInt("date");
                        JSONObject arrFrom = arr.getJSONObject(i).getJSONObject("message").getJSONObject("from");
                        int idFrom = arrFrom.getInt("id");
                        boolean is_botFrom = arrFrom.getBoolean("is_bot");
                        String first_nameFrom = arrFrom.getString("first_name");
                        String language_codeFrom = arrFrom.getString("language_code");
                        f.popola(idFrom, is_botFrom, first_nameFrom, language_codeFrom);

                        JSONObject arrChat = arr.getJSONObject(i).getJSONObject("message").getJSONObject("chat");
                        int idChat = arrChat.getInt("id");

                        //--------------------------------------------
                        //                   CITTA'
                        //--------------------------------------------
                        if (text.contains("/citta")) {
                            
                            location.search(text, i, idChat, first_nameFrom);

                        } //--------------------------------------------
                        else {
                            String first_nameChat = arrChat.getString("first_name");
                            String type = arrChat.getString("type");
                            c.popola(idChat, first_nameChat, type);

                            inf.popola(update_id, message_id, date, text, inf, c);
                            System.out.println(inf.toString());
                            try {
                                send.send(idChat, text);
                            } catch (IOException ex) {
                                Logger.getLogger(GetUpdates.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Ho risposto: " + text);
                        }
                    }
                }
                arrl = arr.length();
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(GetUpdates.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
