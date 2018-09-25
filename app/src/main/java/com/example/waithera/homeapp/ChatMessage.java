package com.example.waithera.homeapp;

import java.util.Date;

/**
 * Created by Waithera on 10/09/2018.
 */

public class ChatMessage {
    private String messageText;
    private String messgeUser;
    private long messageTime;

    public ChatMessage(String messageText, String messgeUser) {
        this.messageText = messageText;
        this.messgeUser = messgeUser;

        this.messageTime = new Date().getTime();
    }

    public ChatMessage() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessgeUser() {
        return messgeUser;
    }

    public void setMessgeUser(String messgeUser) {
        this.messgeUser = messgeUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}

