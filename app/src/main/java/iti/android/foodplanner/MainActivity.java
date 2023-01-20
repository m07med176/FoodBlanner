package iti.android.foodplanner;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashMap;
import java.util.Map;

import iti.android.foodplanner.data.TestActivity;
import iti.android.foodplanner.databinding.ActivityMainAppBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainAppBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        User user = new User("das","asda","asdas","asd");
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Alan");
//        user.put("middle", "Mathison");
//        user.put("last", "Turing");
//        user.put("born", 1912);

//                        .document()
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
//        db
//                .collection("users")
//                .add(user)
//                .addOnCompleteListener(task -> Toast.makeText(MainActivity.this, "Gooooolge", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this, "Errrorrrr", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnCanceledListener(new OnCanceledListener() {
//                    @Override
//                    public void onCanceled() {
//                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
//                    }
//                });

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_plan)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_app);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }



}