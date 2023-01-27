package iti.android.foodplanner.data.authentication;

import android.content.Context;

import iti.android.foodplanner.ui.features.login.LoginInterface;
import iti.android.foodplanner.ui.features.register.RegisterInterface;

public abstract class EmailAuthentication<T> extends Authentication<T>{
    public abstract void login(LoginInterface loginInterface, String email, String password, Context context);
    public abstract void register(RegisterInterface registerInterface, String email, String password, Context context,String userName);
}
