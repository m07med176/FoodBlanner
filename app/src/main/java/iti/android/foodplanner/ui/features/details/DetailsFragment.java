package iti.android.foodplanner.ui.features.details;

import static iti.android.foodplanner.R.*;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.databinding.FragmentDetailsBinding;
import iti.android.foodplanner.databinding.FragmentFavoriteBinding;
import iti.android.foodplanner.ui.features.home.HomeFragmentDirections;

public class DetailsFragment extends Fragment implements DetailsInterface{
    // TODO Implementing Video Player From Youtube
    //  see https://github.com/PierfrancescoSoffritti/android-youtube-player

    // TODO Implementing Loading Photo using GLIDE
    //  see https://github.com/bumptech/glide

    private YouTubePlayerView youTubePlayerView;
    private ImageView imageView;
    private TextView detailsTv;
    private TextView ingridientsTv;
    private TextView instructionsTv;
    private ToggleButton addTofavBtn;
    private Button addToPlan;
    private MealsItem mealsItem;
    private FragmentDetailsBinding binding;
    private DetailsPresenter presenter;
    private View root;
    private List<String> ingridients;
    private int backGroungFlag;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        backGroungFlag=0;
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        initUi();
        presenter = new DetailsPresenter(getContext(),this);

        mealsItem=DetailsFragmentArgs.fromBundle(getArguments()).getMealsItem();

        detailsTv.setText("Name "+mealsItem.getStrMeal()+"\t"+"Category "+mealsItem.getStrCategory()+"\n"+"Country "+mealsItem.getStrArea());
        instructionsTv.setText(mealsItem.getStrInstructions());

        ingridients=mealsItem.getIngredients();
        for(int i=0;i<ingridients.size();i++)
        {
            ingridientsTv.append(ingridients.get(i)+"\n");
        }
        Glide.with(requireContext()).
                load(mealsItem.getStrMealThumb())
                .apply(new RequestOptions().override(400,300).
                        placeholder(drawable.app_logo).error(drawable.app_logo)).
                into(imageView);

        addToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO insert mealplan
                //repository.insertPlanMeal(mealsItem,null);
            }
        });

        addTofavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO check data fetch interface
                if(backGroungFlag==0)
                {
                addTofavBtn.setBackgroundResource(R.drawable.favorite);
                addToFav(mealsItem);
                Toast.makeText(requireContext(), "Meal added to your favorite successfully", Toast.LENGTH_SHORT).show();
                backGroungFlag=1;

                }
                if(backGroungFlag==1)
                {
                    addTofavBtn.setBackgroundResource(drawable.heart);
                    deleteFromFav(mealsItem);
                    Toast.makeText(requireContext(), "Meal removed from your favorites successfully", Toast.LENGTH_SHORT).show();
                    backGroungFlag=0;

                }
            }
        });
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(mealsItem.getStrYoutube(), 0);
            }

        });

        return root;
    }

    public void initUi(){
        root = binding.getRoot();
        youTubePlayerView = root.findViewById(id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        detailsTv=root.findViewById(id.detailsTextView);
        addTofavBtn=root.findViewById(id.favButton);
        addToPlan=root.findViewById(id.addToPlanButton);
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
        presenter.addToPlan(mealPlan);
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