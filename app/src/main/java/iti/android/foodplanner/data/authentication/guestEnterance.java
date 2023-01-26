package iti.android.foodplanner.data.authentication;

import android.content.Context;

import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.shared.SharedManager;

public class guestEnterance {
    public static void guestLogin(Context context){
        SharedManager.getInstance(context).clearAllData();
        Repository.getInstance(context).deleteAllTable(Repository.DELETE_PLAN_AND_FAV);
    }
}
