package iti.android.foodplanner.ui.features.sign_in_with_google;



import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import iti.android.foodplanner.data.shared.SharedManager;
import iti.android.foodplanner.ui.features.main.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.ui.features.login.LoginActivity;
import iti.android.foodplanner.ui.features.register.RegisterActivity;

public class SignUpOrLoginActivity extends AppCompatActivity implements SignInWithGoogleInterface {
    private static final String TAG="SignUpOrLoginActivity";
    private static final int RC_SIGN_IN = 9001;
    private static final String EMAIL = "dev.mohamed.arfa@gmail.com";
    CallbackManager mCallbackManager;
    Button signUpButton;
    SignInButton loginWithGoogleButton;
    TextView loginTxtViewBtn;
    Button guestButton;
    private ProgressDialog progressDialog;
    SignInWithGooglePresenter presenter;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        if(presenter.isUser())
            gotoApp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_login);
        presenter =new SignInWithGooglePresenter(this,this);


        initUi();

        //        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(getApplication());

        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
        presenter.googleAuthInitialize(this);

        buttonsEvents();

    }

    private void buttonsEvents() {
        loginWithGoogleButton.setOnClickListener(view -> startActivityForResult(presenter.loginWithGoogle(), RC_SIGN_IN));

        signUpButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        loginTxtViewBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

        guestButton.setOnClickListener(v -> {
            SharedManager.getInstance(getApplicationContext()).clearAllData();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                progressDialog=new ProgressDialog(SignUpOrLoginActivity.this);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("Please Wait .....");
                progressDialog.setTitle("Get Ready");
                progressDialog.show();
       presenter.checkRequestCode(requestCode,data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }




    public void initUi(){
        loginWithGoogleButton=findViewById(R.id.signInWithGoogle);
        signUpButton=findViewById(R.id.signUpWithMailBtn);
        loginTxtViewBtn=findViewById(R.id.loginTxtView);
        guestButton=findViewById(R.id.guestButton);

    }

    @Override
    public void onSuccessFullFireBaseAuth() {
        gotoApp();
    }

    @Override
    public void onFailedFireBaseAuth() {
        Toast.makeText(SignUpOrLoginActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();

    }

    public void gotoApp() {
        startActivity(new Intent(SignUpOrLoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(progressDialog!=null)
        {
            progressDialog.dismiss();
        }
    }



    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        String mToken = token.getToken();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    gotoApp();
                })
                .addOnFailureListener(e -> {
                    gotoApp();

                });


    }

}