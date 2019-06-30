package com.agarwal.arpit.robochat.entities;

import com.agarwal.arpit.robochat.Utils.MessageType;

public class ChatMessageItem {

    private String name = "--";
    private String time="--";
    private String msg="--";
    private MessageType type;
    private boolean isSuccess = true;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
