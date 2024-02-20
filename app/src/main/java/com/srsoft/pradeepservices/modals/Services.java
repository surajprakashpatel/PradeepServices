package com.srsoft.pradeepservices.modals;

public class Services {

    private String name;
    private int image;

    public Services(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Services() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
