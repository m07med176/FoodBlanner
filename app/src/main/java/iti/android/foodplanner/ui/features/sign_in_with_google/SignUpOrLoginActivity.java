package iti.android.foodplanner.ui.features.sign_in_with_google;



import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;


import iti.android.foodplanner.MainActivity;
import iti.android.foodplanner.R;
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
    private ProgressDialog progressDialog;
    SignInWithGooglePresenter presenter;

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
        presenter.googleAuthInitialize(this);

        buttonsEvents();

    }

    private void buttonsEvents() {
        loginWithGoogleButton.setOnClickListener(view -> startActivityForResult(presenter.loginWithGoogle(), RC_SIGN_IN));

        signUpButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        loginTxtViewBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

        guestButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

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
}