/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot_telegram;

/**
 *
 * @author bonfissuto_luca
 */
import java.io.IOException;
import telegram_api.*;
import org.json.*;

public class BOT_TELEGRAM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        GetUpdates p = new GetUpdates();
        p.start();
    }
}
