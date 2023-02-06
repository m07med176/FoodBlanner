package iti.android.foodplanner.ui.features.login;

import static iti.android.foodplanner.ui.util.Utils.isValidEmail;
import static iti.android.foodplanner.ui.util.Utils.isValidPassword;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import iti.android.foodplanner.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.data.authentication.Authentication;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.ui.features.register.RegisterActivity;


public class LoginActivity extends AppCompatActivity implements LoginInterface{
    private static final String TAG = "GOOGLEAUTHENTCATION";

    private static final String EMAIL = "dev.mohamed.arfa@gmail.com";
    CallbackManager mCallbackManager;
    TextView emailTV;
    TextView passwordTV;
    TextView createNewAccountTV;
    Button loginButton;
    Authentication authentication;
    AuthenticationFactory authenticationFactory;
    private static boolean statesFlagEmail=false;
    private static boolean statesFlagPassword=false;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        authentication= AuthenticationFactory.authenticationManager(AuthenticationFactory.EMAIL);

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

        createNewAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        passwordTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isValidPassword(charSequence))
                {  statesFlagPassword=true;
                    buttonStates();}
                if(isValidPassword(charSequence)==false)
                {
                    statesFlagPassword=false;
                    buttonStates();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        emailTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isValidEmail(charSequence))
                {   statesFlagEmail=true;
                    buttonStates();}
                if(isValidEmail(charSequence)==false) {
                    statesFlagEmail=false;
                    buttonStates();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "",
                        "Loading. Please wait...", true);
               authentication.login(LoginActivity.this,emailTV.getText().toString(), passwordTV.getText().toString());

            }
        });

    }





    public void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    public void initUi(){
        emailTV = findViewById(R.id.emailTxtView);
        passwordTV = findViewById(R.id.passwordTxtView);
        loginButton = findViewById(R.id.loginBtn);
        createNewAccountTV = findViewById(R.id.createNewAccountTxtView);
        loginButton.setEnabled(false);
    }


    public void buttonStates() {
        if (statesFlagEmail&&statesFlagPassword)
            loginButton.setEnabled(true);
        else
            loginButton.setEnabled(false);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
            updateUI(user);
    }

    @Override
    public void onFail(String task) {
        Toast.makeText(LoginActivity.this, task,
                Toast.LENGTH_SHORT).show();
        updateUI((FirebaseUser) null);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        String mToken = token.getToken();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                })
                .addOnFailureListener(e -> {
                    updateUI(null);

                });


    }

}