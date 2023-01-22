package iti.android.foodplanner.data.models;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.data.models.meal.MealsItem;

public class BackupHolder {
    private User userInfo;
    private List<MealsItem> FAV = new ArrayList<>();

    public BackupHolder() {
    }

    public BackupHolder(User userInfo,List<MealsItem> FAV) {
        this.userInfo = userInfo;
        this.FAV = FAV;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public List<MealsItem> getFAV() {
        return FAV;
    }

    public void setFAV(List<MealsItem> FAV) {
        this.FAV = FAV;
    }
}
