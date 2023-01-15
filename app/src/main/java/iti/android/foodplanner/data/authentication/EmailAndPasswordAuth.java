package iti.android.foodplanner.data.authentication;

public class EmailAndPasswordAuth extends EmailAuthentication {
    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public void login(String email, String password) {

    }

    @Override
    public void register() {

    }
}
