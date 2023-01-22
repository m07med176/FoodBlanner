package iti.android.foodplanner.data.backup;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import iti.android.foodplanner.data.models.BackupHolder;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.shared.SharedManager;

public class BackupManager  {
    public static final String ROOT_KEY = "USERS";
    public static final String FAV_KEY = "FAV";
    public static final String PLANE_KEY = "PLANE";
    private SharedManager sharedManager;
    FirebaseFirestore firebaseFirestore;
    private BackupManager(SharedManager sharedManager){
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings =
                new FirebaseFirestoreSettings.Builder()
                        .build();
        firebaseFirestore.setFirestoreSettings(settings);
        this.sharedManager =  sharedManager;
    }

    public static BackupManager backupManager = null;

    public static BackupManager getInstance(SharedManager sharedManager){
        if (backupManager==null)
            backupManager = new BackupManager(sharedManager);

        return backupManager;
    }

    public void saveUser(User user, OnCompleteListener<Void> onCompleteListener){
        firebaseFirestore
                .collection(ROOT_KEY)
                .document(user.getUID())
                .set(user)
                .addOnCompleteListener(onCompleteListener);
    }
    public void deleteFavorite(MealsItem mealsItem){
        firebaseFirestore
                .collection(ROOT_KEY)
                .document(sharedManager.getUser().getUID())
                .collection(FAV_KEY).document(mealsItem.getIdMeal()).delete();

    }
    public void saveFavorite(MealsItem mealsItem){
        firebaseFirestore
                .collection(ROOT_KEY)
                .document(sharedManager.getUser().getUID())
                .collection(FAV_KEY)
                .document(mealsItem.getIdMeal())
                .set(mealsItem);
    }

    public void restoreData(EventListener<DocumentSnapshot> onCompleteListener){

        firebaseFirestore
                .collection(ROOT_KEY)
                .document(sharedManager.getUser().getUID())
                .addSnapshotListener(onCompleteListener);
    }

    public void deletePlane(MealPlan mealPlan){
        firebaseFirestore
                .collection(ROOT_KEY)
                .document(sharedManager.getUser().getUID())
                .collection(PLANE_KEY).document(mealPlan.getIdMeal()).delete();

    }
    public void savePlane(MealPlan mealPlan){
        firebaseFirestore
                .collection(ROOT_KEY)
                .document(sharedManager.getUser().getUID())
                .collection(FAV_KEY)
                .document(mealPlan.getIdMeal())
                .set(mealPlan);
    }
}
