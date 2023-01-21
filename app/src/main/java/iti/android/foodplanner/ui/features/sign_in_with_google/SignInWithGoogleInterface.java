package iti.android.foodplanner.ui.features.sign_in_with_google;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;

public interface SignInWithGoogleInterface {
    public void onSuccessFullFireBaseAuth();
    public void onFailedFireBaseAuth();
}
