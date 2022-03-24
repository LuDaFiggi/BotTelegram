/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegram_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucab
 */
public class File {

    String filename;
    int count;

    public File(String path) {
        filename = path;
        count = 0;
    }

    public List<String> check(int idChat, float lat, float lon) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> users = new ArrayList<String>();
        count = 0;
        for (String line = ""; (line = bufferedReader.readLine()) != null;) {
            if (line.contains(String.valueOf(idChat))) {
                String[] linea = line.split(";");
                linea[2] = String.valueOf(lat);
                linea[3] = String.valueOf(lon);
                line = linea[0] + ";" + linea[1] + ";" + linea[2] + ";" + linea[3] + "\n";
                count++;
            }
            users.add(line);
        }
        return users;
    }

    public void WriteToFile(int idChat, String name, float lat, float lon) throws FileNotFoundException, IOException {
        List<String> toWrite = check(idChat, lat, lon);
        if (count == 0) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
                String fileContent = idChat + ";" + name + ";" + lat + ";" + lon + "\n";
                bufferedWriter.append(fileContent);
                System.out.println("Utente[" + idChat + "] salvato su file");
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {                
                for (int i = 0; i < toWrite.size(); i++) {
                    bufferedWriter.write(toWrite.get(i));
                }
                System.out.println("Dati di utente[" + idChat + "] aggiornati su file");
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        
    }
}
