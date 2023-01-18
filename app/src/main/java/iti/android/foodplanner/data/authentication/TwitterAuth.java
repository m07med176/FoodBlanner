package iti.android.foodplanner.data.authentication;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public class TwitterAuth extends SocialAuthentication {
    @Override
    public boolean logout() {
        return false;
    }



    @Override
    public Intent loginWithGoogle() {
        return null;
    }

    @Override
    public void login() {

    }
}
