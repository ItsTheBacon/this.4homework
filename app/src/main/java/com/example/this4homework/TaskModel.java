package com.example.this4homework;

public class TaskModel {
    private String title, descriptoin;

    public String getTitle() {
        return title;
    }

    public String getDescriptoin() {
        return descriptoin;
    }



    public TaskModel (String title, String description){
        this.title= title;
        this.descriptoin= description;

    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescriptoin(String descriptoin) {
        this.descriptoin = descriptoin;
    }




}
