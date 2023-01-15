package iti.android.foodplanner.data.shared;

import android.content.Context;
import android.content.SharedPreferences;
public class SharedManager {
    public static final String SHARE_KEY = "shareRoom";
    public static final String MESSAGE_KEY = "MESSAGE_KEY";


    public static void saveShared(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // TODO put saved data
        editor.commit();
    }


    public static Object getShared(Context context){
        // TODO get saved data
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_KEY,Context.MODE_PRIVATE);
        Object messageModel = new Object(
        );
        return messageModel;
    }



}

