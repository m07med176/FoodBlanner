package iti.android.foodplanner.data.authentication;

public class TwitterAuth extends SocialAuthentication {
    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public void login() {

    }
}
