package iti.android.foodplanner.ui.features.details;

import static iti.android.foodplanner.R.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.List;


import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;

import iti.android.foodplanner.data.room.Week;
import iti.android.foodplanner.databinding.FragmentDetailsBinding;
import iti.android.foodplanner.ui.features.AddToPlanDialog.AddToPlanDailog;

public class DetailsFragment extends Fragment implements DetailsInterface{

    private YouTubePlayerView youTubePlayerView;
    private ImageView imageView;
    private TextView detailsName;
    private TextView categoryName;
    private TextView areaName;
    private RecyclerView ingridientsRV;
    private TextView instructionsTv;
    private AppCompatImageView addTofavBtn;
    private AppCompatButton addToPlan;
    private MealsItem mealsItem;
    private String mealId;
    private FragmentDetailsBinding binding;
    private DetailsPresenter presenter;
    private View root;
    private List<String> ingridients;
    private MealPlan mealPlan;
    private Button addToCalender;

    @Override
    public void onStart() {
        super.onStart();
        DetailsFragment.this.requireActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        initUi();
        presenter = new DetailsPresenter(getContext(),this);
        AddToPlanDailog addToPlanDailog=new AddToPlanDailog(requireContext());

        mealId=DetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        presenter.getMeal(mealId, new DataFetch<List<MealsItem>>() {


            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                if(!presenter.isUser())
                {
                    addToPlan.setVisibility(View.GONE);
                    addTofavBtn.setVisibility(View.GONE);
                }
                mealsItem=data.get(0);
                mealPlan=mealsItem.convertMealsItemToMealsPlan(mealsItem);
                Log.i("TAG", "meal plan obj: "+mealPlan.getStrCategory());
                detailsName.setText(mealsItem.getStrMeal());
                areaName.setText(mealsItem.getStrArea());
                categoryName.setText(mealsItem.getStrCategory());
                Log.i("TAG", "category: "+mealsItem.getStrCategory());
                instructionsTv.setText(mealsItem.getStrInstructions());
                Log.i("TAG", "instructions: "+mealsItem.getStrInstructions());
                ingridients=mealsItem.getIngredients();

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                ingridientsRV.setLayoutManager(linearLayoutManager);
                  IngredientAdapter ingredientAdapter=new IngredientAdapter(requireContext(),ingridients);
                    ingridientsRV.setAdapter(ingredientAdapter);


                Glide.with(requireContext()).
                        load(mealsItem.getStrMealThumb())
                        .apply(new RequestOptions().override(1920,1080).

                                placeholder(drawable.app_logo).error(drawable.app_logo)).
                        into(imageView);

                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String[] videoId = mealsItem.getStrYoutube().split("=");

                        try {
                            youTubePlayer.loadVideo(videoId[1], 0);
                        }catch (ArrayIndexOutOfBoundsException exception){
                            exception.printStackTrace();
                        }

         }
                    });
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });

      addToPlan.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              addToPlanDailog.createDialog(mealPlan,requireContext());

          }
      });

        addTofavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFav(mealsItem);
                Toast.makeText(requireContext(), "Meal added to your favorite successfully", Toast.LENGTH_SHORT).show();

            }
        });
        addToCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendarEvent = Calendar.getInstance();
                Intent i = new Intent(Intent.ACTION_EDIT);
                i.setType("vnd.android.cursor.item/event");
                i.putExtra("beginTime", calendarEvent.getTimeInMillis());
                i.putExtra("allDay", false);
                i.putExtra("rule", "FREQ=YEARLY");
                i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                i.putExtra("title", mealsItem.getStrMeal());
                requireContext().startActivity(i);
            }
        });
        return root;
    }

    public void initUi(){
        root = binding.getRoot();
        youTubePlayerView = root.findViewById(id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        detailsName=root.findViewById(id.nameDetailsTextView);
        categoryName=root.findViewById(id.categoryTextView);
        areaName=root.findViewById(id.countryTextView);
        addTofavBtn=root.findViewById(id.addToFavoriteButton);
        addToPlan=root.findViewById(id.addTOPlanButton);
        imageView=root.findViewById(id.mealImageView);
        ingridientsRV=root.findViewById(id.ingredientsRecycleView);
        instructionsTv=root.findViewById(id.instructionsTextView);
        addToCalender=root.findViewById(id.addTOCalender);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        youTubePlayerView.release();
        binding = null;
    }


    @Override
    public void addToPlan(MealPlan mealPlan) {
        presenter.addToPlan(mealPlan, new DataFetch<Void>() {
            @Override
            public void onDataSuccessResponse(Void data) {

            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });
    }

    @Override
    public void addToFav(MealsItem mealsItem) {
        presenter.addToFav(mealsItem, new DataFetch<Void>() {
            @Override
            public void onDataSuccessResponse(Void data) {

            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });
    }

    @Override
    public void deleteFromPlan(MealPlan mealPlan) {
        presenter.deleteFromPlan(mealPlan);
    }

    @Override
    public void deleteFromFav(MealsItem mealsItem) {
        presenter.deleteFromFav(mealsItem);
    }




}