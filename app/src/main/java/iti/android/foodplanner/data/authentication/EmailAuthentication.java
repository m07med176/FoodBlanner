package iti.android.foodplanner.data.authentication;

import iti.android.foodplanner.ui.features.login.LoginInterface;
import iti.android.foodplanner.ui.features.register.RegisterInterface;

public abstract class EmailAuthentication<T> extends Authentication<T>{
    public abstract void login(LoginInterface loginInterface,String email, String password);
    public abstract void register(RegisterInterface registerInterface, String email, String password);
}
