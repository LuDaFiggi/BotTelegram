/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telegram_api;

/**
 * @author lucab
 */
public class Info {
    private int update_id;
    private int message_id;
    private int date;
    private String text;
    private Info i;
    private Chat c;
    
    
    public void popola(int update_id,int message_id,int date,String text,Info i,Chat c){
        this.update_id = update_id;
        this.message_id = message_id;
        this.date = date;
        this.text = text;
        this.i = i;
        this.c = c;
    }

    public int getChatId()
    {
        return c.getId();
    }
    
    public String getText()
    {
        return this.text;
    }
    
    @Override
    public String toString() {
        return "Info{" + "update_id=" + update_id + ", message_id=" + message_id + ", date=" + date + ", text=" + text + ", chat id=" + c.getId() + '}';
    }

    
    public Info() {
    }
}
