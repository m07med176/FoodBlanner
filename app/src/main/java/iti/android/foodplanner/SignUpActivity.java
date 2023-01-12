package iti.android.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class SignUpActivity extends AppCompatActivity {
    TextView signUpEmailTv;
    TextView signUpPasswordTv;
    Button signUpButton;
    private FirebaseAuth mAuth;
    private static final String TAG="GOOGLEAUTHENTCATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpEmailTv=findViewById(R.id.signupEmailTxtView);
        signUpPasswordTv=findViewById(R.id.passwordSignupTxtView);
        signUpButton=findViewById(R.id.signUpBtn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewUser(signUpEmailTv.getText().toString(),signUpPasswordTv.getText().toString());
            }
        });



    }
    public void addNewUser(String email,String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "This email already exists !",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });



    }

    public void updateUI(FirebaseUser user) {
        if(user!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));}
    }
}