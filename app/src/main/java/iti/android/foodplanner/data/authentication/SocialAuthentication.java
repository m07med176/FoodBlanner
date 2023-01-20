package iti.android.foodplanner.data.authentication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import iti.android.foodplanner.ui.features.sign_in_with_google.SignInWithGoogleInterface;

public abstract class SocialAuthentication<T> extends Authentication<T>{
    public abstract void login();
}
