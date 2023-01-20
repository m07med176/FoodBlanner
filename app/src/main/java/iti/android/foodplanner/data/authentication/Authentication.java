package iti.android.foodplanner.data.authentication;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.auth.FirebaseUser;

import iti.android.foodplanner.ui.features.login.LoginInterface;
import iti.android.foodplanner.ui.features.register.RegisterInterface;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignInWithGoogleInterface;

public abstract class Authentication<T> {
    public void login(LoginInterface loginInterface, String email, String password){}
    public void register(RegisterInterface registerInterface, String email, String password){}
    public abstract boolean logout();
    public abstract T instance();

}
