package com.dfsp.dashboard.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PageFormSelector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String routerLink;
    private String dbTitle;

    public PageFormSelector(String title, String routerLink, String dbTitle) {
        this.title = title;
        this.routerLink = routerLink;
        this.dbTitle = dbTitle;
    }

    public PageFormSelector() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }

    public String getDbTitle() {
        return dbTitle;
    }

    public void setDbTitle(String dbTitle) {
        this.dbTitle = dbTitle;
    }
}
