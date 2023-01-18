package iti.android.foodplanner.ui.features.login;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface LoginInterface {
    public void onSuccess(FirebaseUser user);
    public void onFail(String task);
}
