package iti.android.foodplanner.data.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.network.ApiCalls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SharedManager {
    public static final String DELEMETER = ",";
    public static final String AREAS = "AREAS";
    public static final String INGREDIENTS = "INGREDIENTS";
    public static final String CATEGORIES = "CATEGORIES";
    public static final String IS_FIRST = "IS_FIRST";
    public static final String USER_INFO = "USER_INFO";
    private volatile static SharedManager instance = null;
    private  SharedPreferences sharedPreferences = null;
    private SharedManager(Context context){
        final String SHARE_KEY = "shareRoom";
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

