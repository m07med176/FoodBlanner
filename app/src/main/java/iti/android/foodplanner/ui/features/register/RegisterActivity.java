package iti.android.foodplanner.ui.features.register;




import static iti.android.foodplanner.ui.util.Utils.isValidEmail;
import static iti.android.foodplanner.ui.util.Utils.isValidPassword;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iti.android.foodplanner.MainActivity;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.authentication.Authentication;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.backup.BackupManager;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.shared.SharedManager;
import iti.android.foodplanner.ui.features.login.LoginActivity;


public class RegisterActivity extends AppCompatActivity implements RegisterInterface{
    private TextView signUpEmailTv;
    private TextView signUpPasswordTv;
    private Button signUpButton;
    private TextView loginTxtViewBtn;
    private TextView guestBtn;
    private TextView userNameTv;
    private ProgressDialog dialog;

    private static boolean statesFlagEmail=false;
    private static boolean statesFlagPassword=false;

    private FirebaseAuth mAuth;
    private static final String TAG="RegisterActivity";

    private  Authentication authentication;
    private AuthenticationFactory authenticationFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        authentication= AuthenticationFactory.authenticationManager(AuthenticationFactory.EMAIL);

        initUi();

        loginTxtViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        signUpEmailTv.addTextChangedListener(new TextWatcher() {
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
            public void afterTextChanged(Editable s) {
                if(!statesFlagEmail) {
                    showEmailError();
                }
            }


        });
        signUpPasswordTv.addTextChangedListener(new TextWatcher() {
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
                if(!statesFlagPassword)
                    showPasswordError();
            }

        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(RegisterActivity.this, "",
                        "Loading. Please wait...", true);
                authentication.register(RegisterActivity.this,signUpEmailTv.getText().toString(),signUpPasswordTv.getText().toString(),RegisterActivity.this,userNameTv.getText().toString());
            }
        });
        guestBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }


    public void initUi(){
        signUpEmailTv=findViewById(R.id.signupEmailTxtView);
        signUpPasswordTv=findViewById(R.id.passwordSignupTxtView);
        signUpButton=findViewById(R.id.signUpBtn);
        loginTxtViewBtn=findViewById(R.id.loginTxtView);
        guestBtn=findViewById(R.id.continueAsGuest);
        userNameTv=findViewById(R.id.userNameTextView);
        signUpButton.setEnabled(false);
    }

    public void updateUI(FirebaseUser user) {
        if(user!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));}
    }
    public void buttonStates() {
        if (statesFlagEmail&&statesFlagPassword)
            signUpButton.setEnabled(true);
        else
            signUpButton.setEnabled(false);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        dialog.dismiss();
        updateUI(user);
    }

    @Override
    public void onFail(Exception exception) {
        dialog.dismiss();
        if (exception == null) {
            Toast.makeText(getApplicationContext(), "UnExpected error occurred", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
        showEmailError();
        showPasswordError();
    }
    public void showEmailError(){
        signUpEmailTv.setError("Please enter email in the right form");
    }
    public void showPasswordError(){
        signUpPasswordTv.setError("Please enter password contains first character capital with at least 3 characters and 3 digits");
    }



}