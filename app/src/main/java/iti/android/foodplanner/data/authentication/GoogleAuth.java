package iti.android.foodplanner.data.authentication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import iti.android.foodplanner.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignInWithGoogleInterface;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignInWithGooglePresenter;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignUpOrLoginActivity;

public class GoogleAuth extends SocialAuthentication implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "GOOGLEAUTHENTCATION";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private GoogleApiClient googleApiClient;
    private SignInWithGoogleInterface signInWithGoogleInterface;

    public GoogleAuth() {
        mAuth = FirebaseAuth.getInstance();

    }

    public void googleIntializer(Context context, @NonNull FragmentActivity fragmentActivity, SignInWithGoogleInterface signInWithGoogleInterface) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(context).enableAutoManage(fragmentActivity, this).
                addApi(Auth.GOOGLE_SIGN_IN_API, gso).
                build();
        this.signInWithGoogleInterface = signInWithGoogleInterface;
    }

    @Override
    public boolean logout() {
        return false;
    }


    public Intent loginWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        return signInIntent;
    }

    @Override
    public FirebaseUser getCurrentUser() {
         mAuth=FirebaseAuth.getInstance();
        return mAuth.getCurrentUser();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    signInWithGoogleInterface.onSuccessFullFireBaseAuth();
                })
                .addOnFailureListener(e -> signInWithGoogleInterface.onFailedFireBaseAuth());
    }

    public void chekRequestCode(int requestCode, Intent data) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void login() {

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
            // Signed in successfully, show authenticated UI.
            signInWithGoogleInterface.onSuccessFullSignIn(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            signInWithGoogleInterface.onFailedSignIn(null);
        }
    }
}
