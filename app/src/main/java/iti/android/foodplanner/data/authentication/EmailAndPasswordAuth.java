package iti.android.foodplanner.data.authentication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.backup.BackupManager;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.shared.SharedManager;
import iti.android.foodplanner.ui.features.login.LoginInterface;
import iti.android.foodplanner.ui.features.register.RegisterInterface;

public class EmailAndPasswordAuth extends EmailAuthentication<EmailAndPasswordAuth> {
    private FirebaseAuth mAuth;
    private static final String TAG="EmailAndPasswordAuth";
    private LoginInterface loginInterface;
    private RegisterInterface registerInterface;
    private Context context;
    @Override
    public void logout(Context context){
        this.context=context;
    }

    @Override
    public EmailAndPasswordAuth instance() {
        return this;
    }


    @Override
    public void login(LoginInterface loginInterface, String email, String Password,Context context) {
        this.context=context;
        this.loginInterface=loginInterface;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, Password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser= mAuth.getCurrentUser();
                            loginInterface.onSuccess(firebaseUser);
                            SharedManager sharedManager = SharedManager.getInstance(context);
                            User userInfo = getUserData(firebaseUser);

                            BackupManager.getInstance(sharedManager).saveUser(userInfo, task1 -> {
                                if(task.isSuccessful())
                                {
                                    sharedManager.saveUser(userInfo);
                                    Repository.getInstance(context).restoreAllData();
                                }

                            });

                        }
                        else {
                            loginInterface.onFail(task.getException().getMessage());

                        }
                    }
                });




    }

    @Override
    public void register(RegisterInterface registerInterface, String email, String password,Context context,String userName) {
        this.context=context;
        this.registerInterface=registerInterface;


        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser= mAuth.getCurrentUser();
                            registerInterface.onSuccess(firebaseUser);
                            User userInfo= getUserData(firebaseUser,userName);
                            Log.i(TAG, "userName: "+userName);
                            SharedManager sharedManager = SharedManager.getInstance(context);

                            BackupManager.getInstance(sharedManager).saveUser(userInfo, task1 -> {
                                if(task.isSuccessful())
                                {
                                    sharedManager.saveUser(userInfo);
                                    Repository.getInstance(context).restoreAllData();
                                }

                            });
                        } else {Exception exception = task.getException();
                           registerInterface.onFail(exception);
                        }
                    }
                });




    }
    private User getUserData(FirebaseUser user,String userName) {
        Log.i(TAG, "getUserData: username "+userName);
        return new User(user.getUid(), userName, "user.getPhotoUrl().toString()", user.getEmail(),AuthenticationFactory.EMAIL);
    }
    private User getUserData(FirebaseUser user) {

        return new User(user.getUid(),user.getDisplayName(), "user.getPhotoUrl().toString()", user.getEmail(),AuthenticationFactory.EMAIL);
    }
}
