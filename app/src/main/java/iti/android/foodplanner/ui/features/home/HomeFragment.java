package iti.android.foodplanner.ui.features.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.databinding.FragmentHomeBinding;
import iti.android.foodplanner.ui.features.favorite.FavoriteAdapter;
import iti.android.foodplanner.ui.util.Utils;

public class HomeFragment extends Fragment implements HomeInterface{

    private HomePresenter presenter;
    private FragmentHomeBinding binding;
    ViewPager2 viewPager2;
    private HomeFeedAdapter homeFeedAdapter;

    private List<MealsItem> mealsItemList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenter(getContext(),this);
        RecyclerView recyclerView = Utils.recyclerViewHandler(binding.rvListRandom,getContext());

        homeFeedAdapter = new HomeFeedAdapter(getContext(), mealsItemList, this);
        recyclerView.setAdapter(homeFeedAdapter);

        presenter.getRandomMeals(new DataFetch<List<MealsItem>>() {
            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                mealsItemList.clear();
                mealsItemList.addAll(data);
                homeFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });
        // TODO retrieve data from Daily inspiration
        // TODO retrieve data from Categories as ArrayList in Recycler View Adapter

//        viewPager2=view.findViewById(R.id.homeViewPager);
        //viewPager2.setAdapter(new CustomPagerAdapter(this));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onSavePlane(MealsItem item) {
presenter.saveFavorite(item, new DataFetch<Void>() {
    @Override
    public void onDataSuccessResponse(Void data) {
        Toast.makeText(getContext(), item.getStrMeal()+" Added To Favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataFailedResponse(String message) {
        Toast.makeText(getContext(), " Error Happened: "+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDataLoading() {

    }
});
    }

    @Override
    public void onSaveFavorite(MealsItem item) {

    }
}