package iti.android.foodplanner.ui.features.main;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import iti.android.foodplanner.R;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.internetConnection.InternetConnection;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.network.Network;
import iti.android.foodplanner.data.shared.SharedManager;
import iti.android.foodplanner.databinding.ActivityMainAppBinding;
import iti.android.foodplanner.ui.features.main.MainActivityPresenter;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignUpOrLoginActivity;
import iti.android.foodplanner.ui.util.Utils;

public class MainActivity extends AppCompatActivity {
    public static MutableLiveData<Boolean> netwrokConnectivity  = new MutableLiveData<>(false);

    private ActivityMainAppBinding binding;
    private MainActivityPresenter presenter;
    private NavController navController;

    private boolean isFirstOpen = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        isFirstOpen = true;
        presenter = new MainActivityPresenter(this);
        binding = ActivityMainAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigationUiSettings();
        actionBarSettings();

    }

    private void actionBarSettings() {
        SharedManager.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.profileTitle.setText(user.getName());
                Utils.loadImage(getApplicationContext(),user.getImageUrl(),binding.profileImage);
            }
        });
        User user = presenter.getUserData();
        binding.logoutBtn.setOnClickListener(view -> {
            logoutFromApp();
        });
        binding.profileTitle.setText(user.getName());
        Utils.loadImage(this,user.getImageUrl(),binding.profileImage);
    }

    @Override
    protected void onStart() {
        isFirstOpen = true;
        super.onStart();



    }

    @Override
    protected void onResume() {
        super.onResume();
        ConstraintLayout constraintLayout = findViewById(R.id.container);
        new InternetConnection(getApplicationContext()).observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean connected) {
                if (connected) {
                    if (isFirstOpen){
                        isFirstOpen = false;
                    }else{
                        Utils.snakeMessage(getApplicationContext(), constraintLayout, "Online", true).show();
                    }
                }else {
                    Utils.snakeMessage(getApplicationContext(), constraintLayout, "Offline", false).show();
                }
            }
        });
    }

    private void navigationUiSettings() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        int[] pages = {R.id.navigation_home,R.id.navigation_favorite,R.id.navigation_category, R.id.navigation_plan,R.id.navigation_details,R.id.navigation_onboarding,R.id.navigation_search};
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(pages).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_app);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId() == R.id.navigation_details||navDestination.getId()==R.id.navigation_profile) {
                    navView.setVisibility(View.GONE);
                } else {
                    navView.setVisibility(View.VISIBLE);
                }

            }
        });
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.navigation_profile);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.option_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.signout_option:
//                logoutFromApp();
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void logoutFromApp() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(getResources().getString(R.string.logout_title));
        alertDialog.setMessage(getResources().getString(R.string.logout_message_dialog));
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(getResources().getString(R.string.dialog_positive_button), (dialog, which) ->
        {


            int autProvider = SharedManager.getInstance(this).getUser().getAuthProvider();
            AuthenticationFactory.authenticationManager(autProvider)
                    .logout(this);
            startActivity(new Intent(this, SignUpOrLoginActivity.class));
            finish();
        });
        alertDialog.setNegativeButton(getResources().getString(R.string.dialog_negative_button), (dialog, which) -> dialog.cancel());

        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

    @Override
    public boolean onNavigateUp() {
        return navController.navigateUp() || super.onNavigateUp();
    }
}