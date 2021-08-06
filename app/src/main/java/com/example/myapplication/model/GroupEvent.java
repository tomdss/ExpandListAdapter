package com.example.myapplication.model;

public class GroupEvent {
    String title;
    Boolean isExpand = false;

    public GroupEvent(String title, Boolean isExpand) {
        this.title = title;
        this.isExpand = isExpand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getExpand() {
        return isExpand;
    }

    public void setExpand(Boolean expand) {
        isExpand = expand;
    }
}
