package iti.android.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.shared.SharedManager;
import iti.android.foodplanner.databinding.ActivityMainAppBinding;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignUpOrLoginActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainAppBinding binding;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigationUiSettings();
    }

    private void navigationUiSettings() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        int[] pages = {R.id.navigation_home,R.id.navigation_favorite,R.id.navigation_category, R.id.navigation_plan,R.id.navigation_details,R.id.navigation_onboarding,R.id.navigation_search};
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(pages).build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_app);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId() == R.id.navigation_details) {

                    navView.setVisibility(View.GONE);
                } else {
                    navView.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout_option:
                logoutFromApp();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

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