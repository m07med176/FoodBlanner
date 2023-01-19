package iti.android.foodplanner.ui.features.favorite;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.databinding.FragmentFavoriteBinding;
import iti.android.foodplanner.ui.util.Utils;


public class FavoriteFragment extends Fragment implements FavoriteInterface{
    private static final String TAG = "FavoriteFragment";
    private FragmentFavoriteBinding binding;
    private FavoritePresenter presenter;
    private FavoriteAdapter favoriteAdapter;
    private List<MealsItem> mealsItemList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = Utils.recyclerViewHandler(binding.rvListFavorite,getContext());
        favoriteAdapter = new FavoriteAdapter(getContext(),mealsItemList, (item,position) -> {
                presenter.removeFavorite(item, new DataFetch<Void>() {
                    @Override
                    public void onDataSuccessResponse(Void data) {
                        mealsItemList.remove(item);
                        favoriteAdapter.notifyItemRemoved(position);
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
        });
        recyclerView.setAdapter(favoriteAdapter);
        presenter = new FavoritePresenter(getContext(),this);

        presenter.getFavorites(new DataFetch<List<MealsItem>>() {
            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                mealsItemList.clear();
                mealsItemList.addAll(data);
                favoriteAdapter.notifyDataSetChanged();
            }


            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}