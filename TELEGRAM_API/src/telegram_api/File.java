/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegram_api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author lucab
 */
public class File {

    String filename;

    public File(String path) {
        filename = path;
    }

    public void WriteToFile(int idChat, String name, float lat, float lon) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            String fileContent = idChat + ";" + name + ";" + lat + ";" + lon + "\n";
            bufferedWriter.write(fileContent);
            System.out.println("Utente[" + idChat + "] salvato su file");
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
