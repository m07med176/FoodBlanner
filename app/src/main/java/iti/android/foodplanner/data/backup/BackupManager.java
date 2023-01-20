package iti.android.foodplanner.data.backup;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import iti.android.foodplanner.data.models.User;

public class BackupManager  {
    public static final String ROOT_KEY = "USERS";
    FirebaseFirestore firebaseFirestore;
    private BackupManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings =
                new FirebaseFirestoreSettings.Builder()
                        .build();
        firebaseFirestore.setFirestoreSettings(settings);
    }

    public static BackupManager backupManager = null;

    public static BackupManager getInstance(){
        if (backupManager==null)
            backupManager = new BackupManager();

        return backupManager;
    }

    public void saveUser(User user,OnCompleteListener<Void> onCompleteListener){
        firebaseFirestore
                .collection(ROOT_KEY)
                .document(user.getUID())
                .set(user)
                .addOnCompleteListener(onCompleteListener);
    }
}
