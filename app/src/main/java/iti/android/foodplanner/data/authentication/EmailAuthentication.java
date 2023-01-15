package iti.android.foodplanner.data.authentication;

public abstract class EmailAuthentication extends Authentication{
    public abstract void login(String email,String password);
    public abstract void register();
}
