package iti.android.foodplanner.ui.features.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.databinding.FragmentHomeBinding;
import iti.android.foodplanner.ui.features.AddToPlanDialog.AddToPlanDailog;
import iti.android.foodplanner.ui.features.search.SearchInterface;
import iti.android.foodplanner.ui.util.Utils;

public class HomeFragment extends Fragment implements HomeInterface {

    private HomePresenter presenter;
    private FragmentHomeBinding binding;
    private RelativeLayout searchView;
    String mealId=null;
    AddToPlanDailog addToPlanDailog;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenter(getContext(), this);
        addToPlanDailog=new AddToPlanDailog(requireContext());
        recycleriewIngredientsSettings();

        recycleriewAreaSettings();

        recycleriewCategorySettings();
        randomMealCardSettings(view);
        navigateToSeeMore();
        searchView=view.findViewById(R.id.rand);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionNavigationHomeToNavigationDetails action=HomeFragmentDirections.actionNavigationHomeToNavigationDetails();
                action.setMealId(mealId);
                Navigation.findNavController(v).navigate(action);
            }
        });

//        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                binding.swipeRefresh.setRefreshing(false);
//                Navigation.findNavController(view).navigate(R.id.navigation_home);
//            }
//        });
    }

    private void recycleriewAreaSettings () {
        RecyclerView rvRandomArea = Utils.recyclerViewHandler(binding.rvRandomArea, getContext());
        HomeFeedAdapter homeFeedAdapterArea = new HomeFeedAdapter(getContext(), this);
        rvRandomArea.setAdapter(homeFeedAdapterArea);
        presenter.getRandomMeals(HomePresenter.AREA, new DataFetch<List<MealsItem>>() {
            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                homeFeedAdapterArea.setItemsList(data);
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });

    }

    private void recycleriewCategorySettings () {
        RecyclerView rvRandomCategory = Utils.recyclerViewHandler(binding.rvRandomCategory, getContext());
        HomeFeedAdapter homeFeedAdapterCategory = new HomeFeedAdapter(getContext(), this);
        rvRandomCategory.setAdapter(homeFeedAdapterCategory);

        presenter.getRandomMeals(HomePresenter.CATEGORY, new DataFetch<List<MealsItem>>() {
            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                homeFeedAdapterCategory.setItemsList(data);
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });
        homeFeedAdapterCategory.isHaveData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean haveData) {
                if (haveData) {

                } else {

                }
            }
        });
    }

    private void randomMealCardSettings (View view){
        ImageView imageViewSingleMeal = view.findViewById(R.id.image_thum);
        TextView foodSingleName = view.findViewById(R.id.food_name);
        TextView plane_btn = view.findViewById(R.id.plane_btn);
        ImageButton fav_btn = view.findViewById(R.id.fav_btn);


        presenter.getRandomMeals(HomePresenter.SINGLE, new DataFetch<List<MealsItem>>() {
            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                MealsItem mealsItem = data.get(0);
                mealId=data.get(0).getIdMeal();
                Glide.with(getContext())
                        .load(mealsItem.getStrMealThumb())
                        .apply(new RequestOptions()
                                .override(400, 300)
                                .placeholder(R.drawable.shippingback)
                                .error(R.drawable.ic_close_black_24dp))
                        .into(imageViewSingleMeal);

                foodSingleName.setText(mealsItem.getStrMeal());
                plane_btn.setOnClickListener(view1 -> {
                    onSavePlane(mealsItem);
                });

                fav_btn.setOnClickListener(view12 -> onSaveFavorite(mealsItem));
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });


    }
    private void recycleriewIngredientsSettings() {
        RecyclerView rvRandomIngredien = Utils.recyclerViewHandler(binding.rvRandomIngredien, getContext());
        HomeFeedAdapter homeFeedAdapterIngredien = new HomeFeedAdapter(getContext(), this);
        rvRandomIngredien.setAdapter(homeFeedAdapterIngredien);
        presenter.getRandomMeals(HomePresenter.INGREDIENT, new DataFetch<List<MealsItem>>() {
            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                binding.rvRandomIngredien.setVisibility(View.VISIBLE);
                binding.shimmerHomeIngredient.setVisibility(View.GONE);
                homeFeedAdapterIngredien.setItemsList(data);
            }

            @Override
            public void onDataFailedResponse(String message) {
                binding.rvRandomIngredien.setVisibility(View.VISIBLE);
                binding.shimmerHomeIngredient.setVisibility(View.GONE);
            }

            @Override
            public void onDataLoading() {
                binding.rvRandomIngredien.setVisibility(View.GONE);
                binding.shimmerHomeIngredient.setVisibility(View.VISIBLE);
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    private void navigateToSeeMore () {
        binding.seeMoreArea.setOnClickListener(view -> {
            Utils.navigatorHomeToSearchFragment(view, SearchInterface.AREA, "");
        });
        binding.seeMoreCategory.setOnClickListener(view -> {
            Utils.navigatorHomeToSearchFragment(view, SearchInterface.CATEGORY, "");
        });
        binding.seeMoreIngredients.setOnClickListener(view -> {
            Utils.navigatorHomeToSearchFragment(view, SearchInterface.INGREDIENT, "");
        });

    }

    @Override
    public void onSavePlane (MealsItem item){
        addToPlanDailog.createDialog(item.convertMealsItemToMealsPlan(item),requireContext());
        Toast.makeText(getContext(), item.getStrMeal() + " Will Be Add to plane", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveFavorite (MealsItem item){
        presenter.saveFavorite(item, new DataFetch<Void>() {
            @Override
            public void onDataSuccessResponse(Void data) {
                Toast.makeText(getContext(), item.getStrMeal() + " Added To Favorite", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataFailedResponse(String message) {
                Toast.makeText(getContext(), " Error Happened: " + message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDataLoading() {

            }
        });
    }

}