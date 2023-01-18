package iti.android.foodplanner.ui.features.sign_in_with_google;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SignInWithGooglePresenter {
    private SignInWithGoogleInterface signInWithGoogleInterface;


    public SignInWithGooglePresenter(SignInWithGoogleInterface signInWithGoogleInterface) {
        this.signInWithGoogleInterface = signInWithGoogleInterface;
    }

    public void onSuccessFullFireBaseAuth(){
        signInWithGoogleInterface.onSuccessFullFireBaseAuth();
    }

    public void onFailedFireBaseAuth(){
        signInWithGoogleInterface.onFailedFireBaseAuth();
    }

    public void onSuccessFullSignIn( GoogleSignInAccount account){
        signInWithGoogleInterface.onSuccessFullSignIn(account);
    }

    public void onFailedSignIn( GoogleSignInAccount account){
        signInWithGoogleInterface.onFailedSignIn(account);
    }

}
