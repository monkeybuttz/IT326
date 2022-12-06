package com.jdbc.model;

public class Message {
    private int receiverId;
    private String text;
    private int senderID;
    private int messageID;

    public Message() {
    }

    public Message(String text, int senderID, int receiverId) {
        this.text = text;
        this.senderID = senderID;
        this.receiverId = receiverId;
    }

    public Message(int id, String text, int senderID, int receiverId) {
        this.messageID = id;
        this.text = text;
        this.senderID = senderID;
        this.receiverId = receiverId;
    }

    public void setMessageId(int id) {
        messageID = id;
    }

    public void setReceiverId(int id) {
        receiverId = id;
    }

    public void setText(String str) {
        text = str;
    }

    public void setSenderId(int senderID) {
        this.senderID = senderID;
    }

    public int getMessageId() {
        return messageID;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }

    public int getSenderId() {
        return senderID;
    }
}
