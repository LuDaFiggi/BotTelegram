/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegram_api;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lucab
 */
public class Location {

    SendMessage send;
    File WriteFile;

    public Location() {
        send = new SendMessage();
        WriteFile = new File("Utenti.txt");
    }

    public void search(String text, int pos, int idChat, String name) throws IOException {
        String[] split = text.split(" ");
        String luogo = "";
        for (int j = 1; j < split.length; j++) {
            if (j != split.length - 1) {
                luogo += split[j] + "+";
            } else {
                luogo += split[j];
            }
        }
        //System.out.println(luogo);
        String jsonStringPlace = "";
        try {
            jsonStringPlace = GetStringJSON.GetStringJSON("https://nominatim.openstreetmap.org/search?q=" + luogo + "&format=json&addressdetails=1");
        } catch (IOException ex) {
            Logger.getLogger(GetUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(jsonStringPlace);
        String stringa = "{result:" + jsonStringPlace + "}";
        JSONObject place = new JSONObject(stringa);
        JSONArray arrPlaces = place.getJSONArray("result");

        //--------------------------------------------
        //                 SendLocation
        //--------------------------------------------
        String citta = arrPlaces.getJSONObject(pos).getString("display_name");
        Float lat = arrPlaces.getJSONObject(pos).getFloat("lat");
        Float lon = arrPlaces.getJSONObject(pos).getFloat("lon");
        System.out.println("[" + idChat + "] SEARCHED --> " + citta);
        
        //FUNZIONA MA LO FA GIA L'XML se serve usare
        /*try {
        // distance = Math.sqrt((x1-x2)(x1-x2) + (y1-y2)(y1-y2));
            send.sendLocation(idChat, lat, lon);
            send.send(idChat, citta);
        } catch (IOException ex) {
            Logger.getLogger(GetUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        //--------------------------------------------
        //                 WriteToFile
        //--------------------------------------------
        WriteFile.WriteToFile(idChat, name, lat, lon);
    }
}
