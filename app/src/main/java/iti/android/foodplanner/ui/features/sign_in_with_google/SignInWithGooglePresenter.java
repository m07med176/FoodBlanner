package iti.android.foodplanner.ui.features.sign_in_with_google;

import android.content.Context;
import android.content.Intent;

import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.authentication.GoogleAuth;

public class SignInWithGooglePresenter {
    private GoogleAuth.Google google;
    private Repository repository;
    private SignInWithGoogleInterface signInWithGoogleInterface;


    public SignInWithGooglePresenter(Context context,SignInWithGoogleInterface signInWithGoogleInterface) {
        repository = Repository.getInstance(context);
        this.signInWithGoogleInterface = signInWithGoogleInterface;
        GoogleAuth authentication=(GoogleAuth) AuthenticationFactory.authenticationManager(AuthenticationFactory.GOOGLE);
        google = authentication.instance();
    }

    public void googleAuthInitialize(SignUpOrLoginActivity activity){
        google.googleIntializer(activity,activity,signInWithGoogleInterface);
    }
    public void onSuccessFullFireBaseAuth(){
        signInWithGoogleInterface.onSuccessFullFireBaseAuth();
    }

    public void onFailedFireBaseAuth(){
        signInWithGoogleInterface.onFailedFireBaseAuth();
    }


    public Intent loginWithGoogle() {
        return google.loginWithGoogle();
    }

    public boolean isUser() {
        return repository.isUser();
    }

    public void checkRequestCode(int requestCode, Intent data) {
        google.checkRequestCode(requestCode,data);
    }
}
