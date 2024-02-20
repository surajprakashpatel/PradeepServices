package com.srsoft.pradeepservices.modals;

public class UserModal {

    private String name;
    private String phone;
    private String email;
    private String userId;

    public UserModal(String name, String phone, String email, String userId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.userId = userId;
    }

    public UserModal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
