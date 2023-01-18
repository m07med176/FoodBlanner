package iti.android.foodplanner.ui.features.register;

import com.google.firebase.auth.FirebaseUser;



public class RegisterPresenter {

    RegisterInterface registerInterface;

    public RegisterPresenter(RegisterInterface RegisterInterface) {
        this.registerInterface = registerInterface;
    }

    public void onSuccess(FirebaseUser user){
        registerInterface.onSuccess(user);
    }
    public void onFail(Exception exception){
        registerInterface.onFail(exception);

    }
}
