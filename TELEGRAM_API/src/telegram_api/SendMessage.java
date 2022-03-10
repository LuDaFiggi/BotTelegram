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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lucab
 */
public class SendMessage {
    public SendMessage(){
        //https://api.telegram.org/bot5104853499:AAHxkpigvsm3p6UGxyL_54xySEyrUfMXJeg/sendMessage?chat_id=712829989&text=ciao
    }
    
    public void send(int chat_id, String mex) throws MalformedURLException, IOException{
        URL url = new URL("https://api.telegram.org/bot5104853499:AAHxkpigvsm3p6UGxyL_54xySEyrUfMXJeg/sendMessage?chat_id=" + chat_id + "&text=" + (mex));
        url.openStream();
    }
    
}
