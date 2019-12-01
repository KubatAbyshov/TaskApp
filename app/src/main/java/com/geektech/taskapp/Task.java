package com.geektech.taskapp;

import java.io.Serializable;

public class Task implements Serializable {

    private String title;
    private String desc;

    public Task() {

    }

    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
