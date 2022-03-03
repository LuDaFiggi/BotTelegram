/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegram_api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bonfissuto_luca
 */
public class Updates {

    public void getUpdates() {

        BufferedReader read = null;
        //PrintWriter print;
        try {
            //print = new PrintWriter("validate.xml");
            URL url;
            url = new URL("https://api.telegram.org/bot5104853499:AAHxkpigvsm3p6UGxyL_54xySEyrUfMXJeg/getUpdates");
            read = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = read.readLine()) != null) {
                //print.println(line);
                /*ArrayList up = new ArrayList();
                up.add("ciao");
                up.add("ciao2");*/

                // PROBLEMA --> LA LINE E' SOLO LA PRIMA LINEA MA IL COMANDO JSON COMPLETO STA SU DUE LINEE --> RISOLVI CON UN INDICE O UN CONTROLLO ++
                JSONObject obj = new JSONObject(line);
                if(obj.getString("text").isEmpty()){
                    System.out.println("non esiste!");
                }
                else{
                    String pageName = obj.getString("text");
                    System.out.println(pageName);
                }
                //System.out.println(line);
            }

            read.close();
            //print.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Updates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Updates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Updates.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
