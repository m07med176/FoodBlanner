package iti.android.foodplanner.ui.features.details;

import static iti.android.foodplanner.R.*;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.List;


import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;

import iti.android.foodplanner.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment implements DetailsInterface{
    // TODO Implementing Video Player From Youtube
    //  see https://github.com/PierfrancescoSoffritti/android-youtube-player

    // TODO Implementing Loading Photo using GLIDE
    //  see https://github.com/bumptech/glide

    private YouTubePlayerView youTubePlayerView;
    private ImageView imageView;
    private TextView detailsName;
    private TextView categoryName;
    private TextView areaName;
    private TextView ingridientsTv;
    private TextView instructionsTv;
    private AppCompatImageView addTofavBtn;
    private AppCompatButton addToPlan;
    private MealsItem mealsItem;
    private String mealId;
    private FragmentDetailsBinding binding;
    private DetailsPresenter presenter;
    private View root;
    private List<String> ingridients;
    MealPlan mealPlan;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        initUi();
        presenter = new DetailsPresenter(getContext(),this);

        mealId=DetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        presenter.getMeal(mealId, new DataFetch<List<MealsItem>>() {


            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                mealsItem=data.get(0);
                detailsName.setText(mealsItem.getStrMeal());
                areaName.setText(mealsItem.getStrArea());
                categoryName.setText(mealsItem.getStrCategory());
                Log.i("TAG", "category: "+mealsItem.getStrCategory());
                instructionsTv.setText(mealsItem.getStrInstructions());
                Log.i("TAG", "instructions: "+mealsItem.getStrInstructions());
                ingridients=mealsItem.getIngredients();

                for(int i=0;i<ingridients.size();i++)
                {
                    ingridientsTv.append("."+ingridients.get(i)+"\n");
                    Log.i("TAG", "ingridients: "+ingridients.get(i));
                }
                Glide.with(requireContext()).
                        load(mealsItem.getStrMealThumb())
                        .apply(new RequestOptions().override(400,300).
                                placeholder(drawable.app_logo).error(drawable.app_logo)).
                        into(imageView);

                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String[] videoId = mealsItem.getStrYoutube().split("=");

                        youTubePlayer.loadVideo(videoId[1], 0);
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
                mealPlan=mealsItem.convertMealsItemToMealsPlan(mealsItem);
                addToPlan(mealPlan);
              Toast.makeText(requireContext(),"Added to plan successfully",Toast.LENGTH_SHORT).show();
          }
      });

        addTofavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO check data fetch interface

                addToFav(mealsItem);
                Toast.makeText(requireContext(), "Meal added to your favorite successfully", Toast.LENGTH_SHORT).show();

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
        ingridientsTv=root.findViewById(id.ingredientsTextView);
        instructionsTv=root.findViewById(id.instructionsTextView);

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