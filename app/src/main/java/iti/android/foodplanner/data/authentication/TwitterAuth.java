package iti.android.foodplanner.data.authentication;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public class TwitterAuth extends SocialAuthentication<TwitterAuth> {
    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public TwitterAuth instance() {
        return this;
    }


    @Override
    public void login() {

    }
}
