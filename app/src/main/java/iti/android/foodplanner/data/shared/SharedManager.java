package iti.android.foodplanner.data.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.network.ApiCalls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SharedManager {
    public static final String IS_FIRST = "IS_FIRST";
    public static final String USER_INFO = "USER_INFO";
    private volatile static SharedManager instance = null;
    private  SharedPreferences sharedPreferences = null;
    private SharedManager(Context context){
        final String SHARE_KEY = "shareRoom";
        sharedPreferences = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE);
    }
    public static synchronized SharedManager getInstance(Context context) {
        if (instance == null)
            instance = new SharedManager(context);
        return instance;
    }

    public void saveUser(User user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_INFO,new Gson().toJson(user));
        editor.apply();
    }

    public User getUser(){
        String userStr = sharedPreferences.getString(USER_INFO,null);
        User user = new User();
        if (userStr != null)
            user = new Gson().fromJson(userStr,User.class);
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



}

