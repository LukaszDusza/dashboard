package com.dfsp.dashboard.controllers;

import java.util.ArrayList;
import java.util.List;

public class ControllersList {

    private String title;
    private String link;

    public ControllersList(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public ControllersList() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
