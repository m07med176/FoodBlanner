package iti.android.foodplanner.data.authentication;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public class FacebookAuth extends SocialAuthentication {
    @Override
    public boolean logout() {
        return false;
    }


    @Override
    public void login() {

    }
}
