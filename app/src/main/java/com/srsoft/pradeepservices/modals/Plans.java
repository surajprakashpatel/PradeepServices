package com.srsoft.pradeepservices.modals;

public class Plans {

    private String name;
    private String id;
    private String link;

    private String category;

    public Plans(String name, String id, String link, String category) {
        this.name = name;
        this.id = id;
        this.link = link;
        this.category = category;
    }

    public Plans() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
