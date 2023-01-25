package iti.android.foodplanner.ui.util;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iti.android.foodplanner.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.ui.features.category.CategoryFragmentDirections;

public class Utils {
    public static RecyclerView recyclerViewHandler(RecyclerView recyclerView, Context context){
        // Function to handle recyclerview and adjust its settings
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return recyclerView;
    }
    public static boolean isValidEmail(CharSequence email) {
        CharSequence target=email;
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        matcher = pattern.matcher(target);
        return (!TextUtils.isEmpty(target) && matcher.matches());
    }

    public static void loadImage(Context context, String path, ImageView imageView){
        Glide.with(context)
                .load(path)
                        .apply(new RequestOptions()
                        .override(400,300)
                        .placeholder(R.drawable.animated_vecotr)
                        .error(R.drawable.shippingback))
                .into(imageView);
    }
    public static void navigatorToSearchFragment(View view, int type,String searchQuerry){
        CategoryFragmentDirections.ActionNavigationCategoryToNavigationSearch action = CategoryFragmentDirections.actionNavigationCategoryToNavigationSearch(type);
        action.setSearch(searchQuerry);
        Navigation.findNavController(view).navigate(action);
    }

    public static boolean isValidPassword(CharSequence password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
