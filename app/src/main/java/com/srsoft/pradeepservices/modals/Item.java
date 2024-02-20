package com.srsoft.pradeepservices.modals;

public class Item {
    private String id;
    private String name;

    private String link;

    public Item(String id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
