package iti.android.foodplanner.data.authentication;

public class FacebookAuth extends SocialAuthentication {
    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public void login() {

    }
}
