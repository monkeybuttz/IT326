package com.jdbc.model;

public class Message {
    private int receiverId;
    private String text;
    private int senderId;
    private int messageId;

    public Message() {}

    public Message(int messageId, String str, int senderId, int receiverId)
    {
        this.messageId = messageId;
        text = str;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public void  setMessageId(int id)
    {
        messageId = id;
    }

    public void setReceiverId(int id)
    {
        receiverId = id;
    }

    public void setText(String str)
    {
        text = str;
    }

    public void setSenderId(int senderId)
    {
        this.senderId = senderId;
    }

    public int getMessageId()
    {
        return messageId;
    }

    public int getReceiverId()
    {
        return receiverId;
    }

    public String getText()
    {
        return text;
    }

    public int getSenderId()
    {
        return senderId;
    }    
}
