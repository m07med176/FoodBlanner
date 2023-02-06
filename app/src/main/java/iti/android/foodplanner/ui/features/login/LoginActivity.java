package iti.android.foodplanner.ui.features.login;

import static iti.android.foodplanner.ui.util.Utils.isValidEmail;
import static iti.android.foodplanner.ui.util.Utils.isValidPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseUser;


import iti.android.foodplanner.data.internetConnection.InternetConnection;
import iti.android.foodplanner.ui.features.main.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.authentication.Authentication;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.backup.BackupManager;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.shared.SharedManager;
import iti.android.foodplanner.ui.features.register.RegisterActivity;
import iti.android.foodplanner.ui.util.Utils;


public class LoginActivity extends AppCompatActivity implements LoginInterface{
    private static final String TAG = "GOOGLEAUTHENTCATION";

    private TextView emailTV;
    private TextView passwordTV;
    private TextView createNewAccountTV;
    private TextView guestBtn;
    private Button loginButton;
    private Authentication authentication;
    private AuthenticationFactory authenticationFactory;
    private static boolean statesFlagEmail=false;
    private static boolean statesFlagPassword=false;
    private ProgressDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LinearLayout linearLayout=findViewById(R.id.login_container);
        authentication= AuthenticationFactory.authenticationManager(AuthenticationFactory.EMAIL);

        initUi();

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
               if(!statesFlagPassword)
                showPasswordError();
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
                if(!statesFlagEmail) {
                    showEmailError();
                }

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = Utils.loadingDialog(LoginActivity.this);
                authentication.login(LoginActivity.this,emailTV.getText().toString(), passwordTV.getText().toString(),LoginActivity.this);

            }
        });
        guestBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

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
        guestBtn=findViewById(R.id.continueAsGuestLogin);
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
          dialog.dismiss();

        updateUI(user);
       finish();


    }

    @Override
    public void onFail(String task) {

        Toast.makeText(LoginActivity.this, task,
                Toast.LENGTH_SHORT).show();
        dialog.dismiss();
        updateUI((FirebaseUser) null);
        showEmailError();
        showPasswordError();

    }


    public void showEmailError(){
        emailTV.setError("Please enter the correct email");
    }
    public void showPasswordError(){
        passwordTV.setError("Please enter the correct password");
    }
    private User getUserData(FirebaseUser user) {


        return new User(user.getUid(), user.getDisplayName(), user.getPhotoUrl().toString(), user.getEmail(),AuthenticationFactory.EMAIL);
    }
}