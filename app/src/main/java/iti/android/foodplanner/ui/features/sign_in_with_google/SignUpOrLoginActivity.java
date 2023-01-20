package iti.android.foodplanner.ui.features.sign_in_with_google;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;


import iti.android.foodplanner.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.data.authentication.Authentication;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.authentication.GoogleAuth;
import iti.android.foodplanner.ui.features.login.LoginActivity;
import iti.android.foodplanner.ui.features.register.RegisterActivity;

public class SignUpOrLoginActivity extends AppCompatActivity implements SignInWithGoogleInterface {
    private static final String TAG="SignUpOrLoginActivity";
    private static final int RC_SIGN_IN = 9001;

    Button signUpButton;
    SignInButton loginWithGoogleButton;
    TextView loginTxtViewBtn;
    Button guestButton;

    GoogleAuth.Google google;

    SignInWithGooglePresenter signInWithGooglePresenter;



    @Override
    protected void onStart() {
        super.onStart();

        if(google.getCurrentUser()!=null){
            reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_login);
        getSupportActionBar().hide();
        initUi();

        signInWithGooglePresenter=new SignInWithGooglePresenter(this);

        GoogleAuth authentication=(GoogleAuth)AuthenticationFactory.authenticationManager(AuthenticationFactory.GOOGLE);
        google = authentication.instance();
        google.googleIntializer(SignUpOrLoginActivity.this,SignUpOrLoginActivity.this,this);

        loginWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(google.loginWithGoogle(), RC_SIGN_IN);
            }
        });

        signUpButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        loginTxtViewBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

        guestButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       google.checkRequestCode(requestCode,data);
    }

    public void updateUI(GoogleSignInAccount user) {
        if(user!=null){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));}
    }

    public void reload() {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onSuccessFullFireBaseAuth() {
        startActivity(new Intent(SignUpOrLoginActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public void onFailedFireBaseAuth() {
        Toast.makeText(SignUpOrLoginActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccessFullSignIn( GoogleSignInAccount account) {
        updateUI(account);

    }

    @Override
    public void onFailedSignIn( GoogleSignInAccount account) {
        updateUI((GoogleSignInAccount) null);
    }
    public void initUi(){
        loginWithGoogleButton=findViewById(R.id.signInWithGoogle);
        signUpButton=findViewById(R.id.signUpWithMailBtn);
        loginTxtViewBtn=findViewById(R.id.loginTxtView);
        guestButton=findViewById(R.id.guestButton);

    }
}