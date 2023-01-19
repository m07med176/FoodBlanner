package iti.android.foodplanner.ui.features.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class CategoryFragment extends Fragment implements CategoryInterface {
    private RecyclerView recyclerView;
    private CategoryPresenter categoryPresenter;

    private CategoryGridViewAdapter categoryGridViewAdapter;
    private List<MealsItem> mealsItem = new ArrayList<>();;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        categoryPresenter = new CategoryPresenter(getContext());

        categoryGridViewAdapter = new CategoryGridViewAdapter(getContext(),mealsItem,this);
        handleRecyclerView();

        categoryPresenter.getCategories(new DataFetch<List<MealsItem>>() {
            @Override
            public void onDataSuccessResponse(List<MealsItem> data) {
                mealsItem.clear();
                mealsItem.addAll(data);
                categoryGridViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });

    }

    private void handleRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(categoryGridViewAdapter);
    }

    @Override
    public void onClickItem() {
        Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
    }
}