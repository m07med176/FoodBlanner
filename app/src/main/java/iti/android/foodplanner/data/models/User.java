package iti.android.foodplanner.data.models;

import java.io.Serializable;

public class User implements Serializable {

    private String UID;
    private String name;
    private String imageUrl;
    private String email;

    public User() {
    }

    public User(String UID, String name, String imageUrl, String email) {
        this.UID = UID;
        this.name = name;
        this.imageUrl = imageUrl;
        this.email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
