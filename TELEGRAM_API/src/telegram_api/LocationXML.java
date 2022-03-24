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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author lucab
 */
public class LocationXML {

    SendMessage send;
    public LocationXML() {
        send = new SendMessage();
    }

    //
    public void ParseFile(String text, int pos, int idChat, String name) throws ParserConfigurationException, SAXException {

        String[] split = text.split(" ");
        String luogo = "";
        for (int j = 1; j < split.length; j++) {
            if (j != split.length - 1) {
                luogo += split[j] + "+";
            } else {
                luogo += split[j];
            }
        }
        BufferedReader read = null;
        PrintWriter print;
        try {
            print = new PrintWriter("validate.xml");
            URL url;
            url = new URL("https://nominatim.openstreetmap.org/search?q=" + luogo + "&format=xml&addressdetails=1");
            read = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = read.readLine()) != null) {
                print.println(line);
                //System.out.println(line);
            }
            read.close();
            print.close();
            Parse(idChat, "validate.xml", luogo, pos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocationXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LocationXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocationXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Parse(int idChat, String fileName, String nomeTag, int pos) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileName);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("searchresults");
        /*if(nList.getLength() == 0){
            nList = doc.getElementsByTagName("Success");
        }*/

        //System.out.println("NODE LIST ITEM 0: " + nList.item(0));
        //System.out.println("");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                NodeList n = eElement.getChildNodes();

                for (int i = 0; i < n.getLength(); i++) {
                    Node node = n.item(i);
                    float lat, lon;
                    lat = lon = 0;
                    String citta = "";
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        for (int j = 0; j < node.getChildNodes().getLength(); j++) {
                            //System.out.println(node.getChildNodes().item(j).getNodeName() + ": " + node.getChildNodes().item(j).getTextContent());
                            if (node.getChildNodes().item(j).getNodeName().equals("country_code")) {
                                if (node.getChildNodes().item(j).getTextContent().equals("it")) {
                                    for (int k = 0; k < node.getChildNodes().getLength(); k++) {
                                        System.out.println("SEARCHED --> " + node.getChildNodes().item(k).getTextContent());
                                        lat = Float.valueOf(node.getAttributes().getNamedItem("lat").getTextContent());
                                        lon = Float.valueOf(node.getAttributes().getNamedItem("lon").getTextContent());
                                        citta = node.getAttributes().getNamedItem("display_name").getTextContent();
                                    }
                                    break;
                                }
                            }
                        }
                        send.sendLocation(idChat, lat, lon);
                        send.send(idChat, citta);
                        break;
                    }
                }
            }
        }
    }
}
