package iti.android.foodplanner.data.authentication;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public class FacebookAuth extends SocialAuthentication<FacebookAuth> {
    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public FacebookAuth instance() {
        return this;
    }


    @Override
    public void login() {

    }
}
