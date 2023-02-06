package iti.android.foodplanner.data.authentication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

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
        mAuth=FirebaseAuth.getInstance();
        Repository.getInstance(context).deleteAllTable(Repository.DELETE_PLAN_AND_FAV);
        SharedManager.getInstance(context).clearAllData();
        mAuth.signOut();

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
                            FirebaseUser firebaseUser=mAuth.getCurrentUser();
                            loginInterface.onSuccess(firebaseUser);

                           User user= updateUserData(firebaseUser,firebaseUser.getDisplayName());
                           SharedManager sharedManager=SharedManager.getInstance(context);
                            BackupManager.getInstance(sharedManager).saveUser(user, task1 -> {
                                if (task1.isSuccessful()){
                                    sharedManager.saveUser(user);
                                    Repository.getInstance(context).restoreAllData();

                                }else{
                                 loginInterface.onFail(task1.getException().getMessage());
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
                            User userInfo= updateUserData(firebaseUser,userName);
                            Log.i(TAG, "userName: "+userName);
                            SharedManager sharedManager = SharedManager.getInstance(context);
                            sharedManager.saveUser(userInfo);
                            BackupManager.getInstance(sharedManager).saveUser(userInfo, task1 -> {
                            });
                        } else {Exception exception = task.getException();
                           registerInterface.onFail(exception);
                        }
                    }
                });




    }
    private User updateUserData(FirebaseUser user, String userName) {
        return new User(user.getUid(), userName, "", user.getEmail(),AuthenticationFactory.EMAIL);
    }
    private void updateUserData(FirebaseUser user) {
        BackupManager.getInstance(SharedManager.getInstance(context)).getUser(user.getUid(),documentSnapshot -> {
            User userFromServer = documentSnapshot.toObject(User.class);
            User userInfo = new User(user.getUid(),userFromServer.getName(),userFromServer.getImageUrl(), user.getEmail(),AuthenticationFactory.EMAIL);
            SharedManager sharedManager = SharedManager.getInstance(context);
            BackupManager.getInstance(sharedManager).saveUser(userInfo, task1 -> {
                if(task1.isSuccessful())
                {
                    sharedManager.saveUser(userInfo);
                    Repository.getInstance(context).restoreAllData();
                }

            });
        });
    }
}
