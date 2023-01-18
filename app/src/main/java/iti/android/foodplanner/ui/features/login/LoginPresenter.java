package iti.android.foodplanner.ui.features.login;

import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    LoginInterface loginInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public void onSuccess(FirebaseUser user){
        loginInterface.onSuccess(user);
    }
    public void onFail(String task){
        loginInterface.onFail(task);

    }

}
