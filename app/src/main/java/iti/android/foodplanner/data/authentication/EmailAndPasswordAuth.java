package iti.android.foodplanner.data.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import iti.android.foodplanner.ui.features.login.LoginActivity;
import iti.android.foodplanner.ui.features.login.LoginInterface;
import iti.android.foodplanner.ui.features.login.LoginPresenter;
import iti.android.foodplanner.ui.features.register.RegisterInterface;

public class EmailAndPasswordAuth extends EmailAuthentication {
    private FirebaseAuth mAuth;
    private static final String TAG="LOGINWITHEMAILANDPASSWORD";
    private LoginInterface loginInterface;
    private RegisterInterface registerInterface;

    @Override
    public boolean logout() {
        return false;
    }



    @Override
    public Intent loginWithGoogle() {
        return null;
    }

    @Override
    public void login(LoginInterface loginInterface, String email, String Password) {
        this.loginInterface=loginInterface;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, Password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginInterface.onSuccess(user);
                        } else {
                            loginInterface.onFail(task.getException().getMessage());

                        }
                    }
                });
    }

    @Override
    public void register(RegisterInterface registerInterface, String email, String password) {
        this.registerInterface=registerInterface;
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            registerInterface.onSuccess(user);
                        } else {Exception exception = task.getException();
                           registerInterface.onFail(exception);
                        }
                    }
                });

    }
}
