package iti.android.foodplanner.data.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.List;

import iti.android.foodplanner.data.models.User;

public class SharedManager {
    public static MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    public static final String DELEMETER = ",";
    public static final String AREAS = "AREAS";
    public static final String INGREDIENTS = "INGREDIENTS";
    public static final String CATEGORIES = "CATEGORIES";
    public static final String IS_FIRST = "IS_FIRST";
    public static final String USER_INFO = "USER_INFO";
    private volatile static SharedManager instance = null;

    private  SharedPreferences sharedPreferences = null;
    final String SHARE_KEY = "shareRoom";
    private SharedManager(Context context){

        sharedPreferences = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE);
    }
    public static  SharedManager getInstance(Context context) {
        if (instance == null)
            instance = new SharedManager(context);
        return instance;
    }
    public void clearAllData(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveUser(User user){
        userMutableLiveData.postValue(user);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_INFO,new Gson().toJson(user));
        editor.apply();
    }

    public boolean isUser(){

        return sharedPreferences.contains(USER_INFO);
    }

    public User getUser(){
        String userStr = sharedPreferences.getString(USER_INFO,null);
        User user = new User();
        if (userStr != null) {
            user = new Gson().fromJson(userStr, User.class);
            saveEntrance();
        }
        return user;
    }
    public boolean isFirstEntrance(){
        return sharedPreferences.getBoolean(IS_FIRST,false);
    }


    public void saveEntrance(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST,true);
        editor.apply();
    }


    public void refreshSharedPrefrence(Context context){

        sharedPreferences = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE);

    }

    public void saveList(String type, List<String> cashList){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(type,String.join(DELEMETER,cashList));
        editor.apply();
    }

        public String[] getList(String type){
        String[] results = null;
        switch (type){
            case AREAS:
                results = sharedPreferences.getString(AREAS,"Egyptian").split(DELEMETER);
                break;
            case INGREDIENTS:
                results = sharedPreferences.getString(INGREDIENTS,"Chicken").split(DELEMETER);
                break;
            case CATEGORIES:
                results = sharedPreferences.getString(CATEGORIES,"Beef").split(DELEMETER);
                break;

        }
        return results;
    }


}


